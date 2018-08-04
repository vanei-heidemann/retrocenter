package br.com.javanei.retrocenter.datafile.logiqx.service;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxArchive;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxBiosset;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDatafile;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDisk;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxGame;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxHeader;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxRelease;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxRom;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxSample;
import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxArchiveEntity;
import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxBiossetEntity;
import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxDatafileEntity;
import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxDiskEntity;
import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxGameEntity;
import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxReleaseEntity;
import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxRomEntity;
import br.com.javanei.retrocenter.datafile.logiqx.entity.LogiqxSampleEntity;
import br.com.javanei.retrocenter.datafile.logiqx.persistence.LogiqxDatafileDAO;
import br.com.javanei.retrocenter.datafile.logiqx.persistence.LogiqxGameDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class LogiqxService {
    private static final Logger LOG = LoggerFactory.getLogger(LogiqxService.class);

    @Autowired
    private LogiqxDatafileDAO datafileDAO;
    @Autowired
    private LogiqxGameDAO gameDAO;
    @Autowired
    private LogiqxService logiqxService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LogiqxDatafileEntity create(LogiqxDatafileEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ", catalog=" + entity.getCatalog() + ", version=" + entity.getVersion() + ")");
        LogiqxDatafileEntity old = datafileDAO.findByUnique(entity.getName(), entity.getCatalog(), entity.getVersion());
        if (old == null) {
            entity = datafileDAO.saveAndFlush(entity);
        } else {
            LOG.debug("Datafile already exist");
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LogiqxGameEntity createGame(LogiqxGameEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ")");
        LogiqxGameEntity old = gameDAO.findByDatafileAndName(entity.getDatafile().getName(),
                entity.getDatafile().getCatalog(), entity.getDatafile().getVersion(), entity.getName());
        if (old == null) {
            entity = gameDAO.saveAndFlush(entity);
        } else {
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LogiqxDatafile create(LogiqxDatafile datafile) {
        LOG.info("create(name=" + datafile.getHeader().getName()
                + ", catalog=" + datafile.getHeader().getCatalog()
                + ", version=" + datafile.getHeader().getVersion()
                + ", games=" + datafile.getGames().size()
                + ")");
        LogiqxDatafileEntity entity = new LogiqxDatafileEntity(datafile);
        entity = logiqxService.create(entity);

        int cont = 0;
        for (LogiqxGame game : datafile.getGames()) {
            LogiqxGameEntity gameEntity = new LogiqxGameEntity(game);
            gameEntity.setDatafile(entity);

            for (LogiqxRelease release : game.getReleases()) {
                LogiqxReleaseEntity releaseEntity = new LogiqxReleaseEntity(release);
                releaseEntity.setGame(gameEntity);
                gameEntity.getReleases().add(releaseEntity);
            }

            for (LogiqxBiosset biosset : game.getBiossets()) {
                LogiqxBiossetEntity biossetEntity = new LogiqxBiossetEntity(biosset);
                biossetEntity.setGame(gameEntity);
                gameEntity.getBiossets().add(biossetEntity);
            }

            for (LogiqxRom rom : game.getRoms()) {
                LogiqxRomEntity romEntity = new LogiqxRomEntity(rom);
                romEntity.setGame(gameEntity);
                gameEntity.getRoms().add(romEntity);
            }

            for (LogiqxDisk disk : game.getDisks()) {
                LogiqxDiskEntity diskEntity = new LogiqxDiskEntity(disk);
                diskEntity.setGame(gameEntity);
                gameEntity.getDisks().add(diskEntity);
            }

            for (LogiqxSample sample : game.getSamples()) {
                LogiqxSampleEntity sampleEntity = new LogiqxSampleEntity(sample);
                sampleEntity.setGame(gameEntity);
                gameEntity.getSamples().add(sampleEntity);
            }

            for (LogiqxArchive archive : game.getArchives()) {
                LogiqxArchiveEntity archiveEntity = new LogiqxArchiveEntity(archive);
                archiveEntity.setGame(gameEntity);
                gameEntity.getArchives().add(archiveEntity);
            }

            logiqxService.createGame(gameEntity);
            entity.getGames().add(gameEntity);

            cont++;
            if (cont % 100 == 0) {
                LOG.info("Saved game " + cont + " of " + datafile.getGames().size() + ": " + game.getName());
            }
        }

        return entity.toVO();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public LogiqxDatafileDTO findByUnique(String name, String catalog, String version) {
        LogiqxDatafileEntity entity = datafileDAO.findByUnique(name, catalog, version);
        if (entity != null) {
            return this.entityToVo(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public LogiqxDatafileDTO findByUniqueFull(String name, String catalog, String version) {
        LogiqxDatafileEntity entity = datafileDAO.findByUnique(name, catalog, version);
        if (entity != null) {
            return this.entityToVo(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public LogiqxDatafileDTO findByIdFull(Long id) {
        LogiqxDatafileEntity entity = datafileDAO.getOne(id);
        if (entity != null) {
            return this.entityToVo(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LogiqxDatafileDTO> findAll() {
        List<LogiqxDatafileEntity> l = datafileDAO.findAll();
        List<LogiqxDatafileDTO> result = new LinkedList<>();
        for (LogiqxDatafileEntity entity : l) {
            result.add(entityToVo(entity, false));
        }
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LogiqxDatafileDTO> find(String name, int page, int pageSize) {
        PageRequest pageable = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<LogiqxDatafileEntity> p;
        if (name != null) {
            p = datafileDAO.findByNameLike("%" + name + "%", pageable);
        } else {
            p = datafileDAO.findAll(pageable);
        }
        PaginatedResult<LogiqxDatafileDTO> result = new PaginatedResult<>(page > 0, p.hasNext());
        for (LogiqxDatafileEntity entity : p.getContent()) {
            result.add(entityToVo(entity, false));
        }
        return result;
    }

    private LogiqxDatafileDTO entityToVo(LogiqxDatafileEntity entity, boolean full) {
        LogiqxDatafileDTO datafile = new LogiqxDatafileDTO(entity.getBuild(), entity.getDebug(), entity.getId());
        LogiqxHeader header = new LogiqxHeader(entity.getName(), entity.getCatalog(), entity.getVersion(),
                entity.getDescription(), entity.getCategory(), entity.getDate(), entity.getAuthor(), entity.getEmail(),
                entity.getHomepage(), entity.getUrl(), entity.getComment(), entity.getHeader(),
                entity.getForcemerging(), entity.getForcenodump(), entity.getForcepacking(), entity.getPlugin(),
                entity.getRommode(), entity.getBiosmode(), entity.getSamplemode(), entity.getLockrommode(),
                entity.getLockbiosmode(), entity.getLocksamplemode());
        datafile.setHeader(header);

        if (full) {
            for (LogiqxGameEntity game : entity.getGames()) {
                datafile.addGame(game.toVO());
            }
        }
        return datafile;
    }
}
