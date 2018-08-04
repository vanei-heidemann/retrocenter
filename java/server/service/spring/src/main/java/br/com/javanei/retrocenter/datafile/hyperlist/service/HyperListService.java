package br.com.javanei.retrocenter.datafile.hyperlist.service;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListGame;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListHeader;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListMenu;
import br.com.javanei.retrocenter.datafile.hyperlist.entity.HyperListEntity;
import br.com.javanei.retrocenter.datafile.hyperlist.entity.HyperListGameEntity;
import br.com.javanei.retrocenter.datafile.hyperlist.persistence.HyperListDAO;
import br.com.javanei.retrocenter.datafile.hyperlist.persistence.HyperListGameDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HyperListService {
    private static final Logger LOG = LoggerFactory.getLogger(HyperListService.class);

    @Autowired
    private HyperListDAO datafileDAO;
    @Autowired
    private HyperListGameDAO gameDAO;
    @Autowired
    private HyperListService hyperListService;

    private static HyperListMenuDTO toVO(HyperListEntity entity, boolean detail) {
        HyperListMenuDTO r = new HyperListMenuDTO(
                new HyperListHeader(entity.getName(), entity.getLastUpdate(), entity.getVersion(),
                        entity.getExporterVersion()), entity.getId());
        if (detail) {
            for (HyperListGameEntity gameEntity : entity.getGames()) {
                r.addGame(new HyperListGame(gameEntity.getName(), gameEntity.getIndex(), gameEntity.getImage(),
                        gameEntity.description, gameEntity.cloneof, gameEntity.getCrc(), gameEntity.getManufacturer(),
                        gameEntity.getYear(), gameEntity.getGenre(), gameEntity.getRating()));
            }
        }
        return r;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public HyperListEntity create(HyperListEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ", lastUpdate=" + entity.getLastUpdate() + ", version=" + entity.getVersion() + ")");
        HyperListEntity old = datafileDAO.findByUnique(entity.getName(), entity.getLastUpdate(), entity.getVersion());
        if (old == null) {
            entity = datafileDAO.saveAndFlush(entity);
        } else {
            LOG.debug("Datafile already exist");
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public HyperListGameEntity createGame(HyperListGameEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ")");
        HyperListGameEntity old = gameDAO.findByDatafileAndUnique(entity.getDatafile().getName(),
                entity.getDatafile().getLastUpdate(), entity.getDatafile().getVersion(), entity.getName(),
                entity.getIndex(), entity.getImage());
        if (old == null) {
            entity = gameDAO.saveAndFlush(entity);
        } else {
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public HyperListMenuDTO create(HyperListMenu datafile) {
        LOG.info("create(name=" + datafile.getHeader().getListname()
                + ", lastUpdate=" + datafile.getHeader().getLastlistupdate()
                + ", version=" + datafile.getHeader().getListversion()
                + ", games=" + datafile.getGames().size()
                + ")");
        HyperListEntity entity = new HyperListEntity(datafile.getHeader().getListname(),
                datafile.getHeader().getLastlistupdate(), datafile.getHeader().getListversion(),
                datafile.getHeader().getExporterversion());
        entity = hyperListService.create(entity);

        int cont = 0;
        for (HyperListGame game : datafile.getGames()) {
            HyperListGameEntity gameEntity = new HyperListGameEntity(game.getName(), game.getIndex(), game.getImage(),
                    game.getDescription(), game.getCloneof(), game.getCrc(), game.getManufacturer(), game.getYear(),
                    game.getGenre(), game.getRating());
            gameEntity.setDatafile(entity);

            hyperListService.createGame(gameEntity);
            entity.getGames().add(gameEntity);

            cont++;
            if (cont % 100 == 0) {
                LOG.info("Saved game " + cont + " of " + datafile.getGames().size() + ": " + game.getName());
            }
        }

        return toVO(entity, true);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public HyperListMenuDTO findByUnique(String name, String lastUpdate, String version) {
        HyperListEntity entity = datafileDAO.findByUnique(name, lastUpdate, version);
        if (entity != null) {
            return new HyperListMenuDTO(
                    new HyperListHeader(entity.getName(), entity.getLastUpdate(), entity.getVersion(),
                            entity.getExporterVersion()), entity.getId()
            );
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public HyperListMenuDTO findByUniqueFull(String name, String lastUpdate, String version) {
        HyperListEntity entity = datafileDAO.findByUnique(name, lastUpdate, version);
        if (entity != null) {
            return toVO(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public HyperListMenuDTO findByIdFull(Long id) {
        HyperListEntity entity = datafileDAO.getOne(id);
        if (entity != null) {
            return toVO(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<HyperListMenuDTO> find(String name, int page, int pageSize) {
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<HyperListEntity> l;
        if (name != null) {
            l = datafileDAO.findByNameLike("%" + name + "%", pr);
        } else {
            l = datafileDAO.findAll(pr);
        }
        PaginatedResult<HyperListMenuDTO> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (HyperListEntity entity : l.getContent()) {
            result.add(toVO(entity, false));
        }
        return result;
    }
}
