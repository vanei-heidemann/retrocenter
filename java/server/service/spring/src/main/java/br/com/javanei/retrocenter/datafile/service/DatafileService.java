package br.com.javanei.retrocenter.datafile.service;

import br.com.javanei.retrocenter.common.DatafileCatalogEnum;
import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.common.PlatformNotFoundException;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDatafile;
import br.com.javanei.retrocenter.datafile.clrmamepro.service.CMProService;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifact;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifactFile;
import br.com.javanei.retrocenter.datafile.common.DatafileObject;
import br.com.javanei.retrocenter.datafile.common.Release;
import br.com.javanei.retrocenter.datafile.entity.DatafileArtifactEntity;
import br.com.javanei.retrocenter.datafile.entity.DatafileArtifactFileEntity;
import br.com.javanei.retrocenter.datafile.entity.DatafileEntity;
import br.com.javanei.retrocenter.datafile.entity.DatafileReleaseEntity;
import br.com.javanei.retrocenter.datafile.hyperlist.HyperListMenu;
import br.com.javanei.retrocenter.datafile.hyperlist.service.HyperListService;
import br.com.javanei.retrocenter.datafile.logiqx.LogiqxDatafile;
import br.com.javanei.retrocenter.datafile.logiqx.service.LogiqxService;
import br.com.javanei.retrocenter.datafile.mame.Mame;
import br.com.javanei.retrocenter.datafile.mame.service.MameService;
import br.com.javanei.retrocenter.datafile.persistence.DatafileDAO;
import br.com.javanei.retrocenter.platform.entity.PlatformEntity;
import br.com.javanei.retrocenter.platform.service.PlatformDTO;
import br.com.javanei.retrocenter.platform.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DatafileService {
    private static final Logger LOG = LoggerFactory.getLogger(DatafileService.class);

    @Autowired
    private MameService mameService;
    @Autowired
    private CMProService cmProService;
    @Autowired
    private LogiqxService logiqxService;
    @Autowired
    private HyperListService hyperListService;
    @Autowired
    private RetrocenterDatafileService retrocenterDatafileService;
    @Autowired
    private DatafileDAO datafileDAO;
    @Autowired
    private PlatformService platformService;

    private static DatafileDTO toVO(DatafileEntity entity) {
        DatafileDTO datafile = new DatafileDTO(entity.getName(), entity.getCatalog(), entity.getVersion(),
                entity.getDescription(), entity.getAuthor(), entity.getDate(), entity.getEmail(),
                entity.getHomepage(), entity.getUrl(), entity.getComment(), entity.getId());
        if (entity.getPlatform() != null) {
            datafile.setPlatformName(entity.getPlatform().getName());
        }

        for (DatafileArtifactEntity gameEntity : entity.getArtifacts()) {
            DatafileArtifact g = new DatafileArtifact(gameEntity.getName(), gameEntity.getDescription(),
                    gameEntity.getYear(), gameEntity.getComment(), gameEntity.getFields());
            datafile.addArtifact(g);

            for (DatafileArtifactFileEntity gameFileEntity : gameEntity.getFiles()) {
                DatafileArtifactFile gf = new DatafileArtifactFile(gameFileEntity.getType(), gameFileEntity.getName(),
                        gameFileEntity.getSize(), gameFileEntity.getCrc(), gameFileEntity.getSha1(),
                        gameFileEntity.getMd5(), gameFileEntity.getDate(), gameFileEntity.getFields());
                g.addFile(gf);
            }

            for (DatafileReleaseEntity releaseEntity : gameEntity.getReleases()) {
                Release r = new Release(releaseEntity.getName(), releaseEntity.getRegion(),
                        releaseEntity.getLanguage(), releaseEntity.getDate(), releaseEntity.getDefault());
                g.addRelease(r);
            }
        }

        return datafile;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Datafile create(DatafileObject datafileObject) throws PlatformNotFoundException {
        LOG.info("create(" + datafileObject.getClass() + ")");
        Datafile datafile;
        if (datafileObject instanceof Mame) {
            datafile = mameService.create((Mame) datafileObject).toDatafile();
        } else if (datafileObject instanceof CMProDatafile) {
            datafile = cmProService.create((CMProDatafile) datafileObject).toDatafile();
        } else if (datafileObject instanceof LogiqxDatafile) {
            datafile = logiqxService.create((LogiqxDatafile) datafileObject).toDatafile();
        } else if (datafileObject instanceof HyperListMenu) {
            datafile = hyperListService.create((HyperListMenu) datafileObject).toDatafile();
        } else {
            datafile = (Datafile) datafileObject;
        }
        LOG.info("datafile=" + datafile.getClass());
        return toVO(retrocenterDatafileService.create(datafile));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Datafile findByUnique(String name, String catalog, String version) {
        DatafileEntity entity = datafileDAO.findByUnique(name, catalog, version);
        if (entity != null) {
            return new Datafile(entity.getName(), entity.getCatalog(), entity.getVersion(), entity.getDescription(),
                    entity.getAuthor(), entity.getDate(), entity.getEmail(), entity.getHomepage(), entity.getUrl(),
                    entity.getComment());
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public DatafileDTO findByUniqueFull(String name, String catalog, String version) {
        DatafileEntity entity = datafileDAO.findByUnique(name, catalog, version);
        if (entity != null) {
            return toVO(entity);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public DatafileDTO findByIDFull(Long id) {
        DatafileEntity entity = datafileDAO.getOne(id);
        if (entity != null) {
            return toVO(entity);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<DatafileDTO> findAll() {
        List<DatafileEntity> l = datafileDAO.findAll();
        List<DatafileDTO> r = new ArrayList<>(l.size());
        for (DatafileEntity entity : l) {
            r.add(entityToDTO(entity));
        }
        return r;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<DatafileDTO> find(String name, DatafileCatalogEnum catalog, String platformName,
                                             int page, int pageSize, String sort) {
        LOG.info("find(name=" + name + ", catalog=" + catalog + ", platformName=" + platformName + ", page=" + page
                + ", pageSize=" + pageSize + ")");
        boolean filterPlatform = false;
        PlatformEntity platform = null;
        if (platformName != null && !"null".equalsIgnoreCase(platformName)) {
            PlatformDTO p = platformService.findPlatform(platformName);
            if (p != null) {
                platform = new PlatformEntity(p.getId());
            }
            filterPlatform = true;
        } else if ("null".equalsIgnoreCase(platformName)) {
            filterPlatform = true;
        }

        Direction sortDirection = Direction.ASC;
        String sortField;
        if (sort != null) {
            switch (sort) {
                case "id":
                    sortField = "id";
                    break;
                case "-id":
                    sortField = "id";
                    sortDirection = Direction.DESC;
                    break;
                case "catalog":
                    sortField = "catalog";
                    break;
                case "-catalog":
                    sortField = "catalog";
                    sortDirection = Direction.DESC;
                    break;
                case "platformName":
                    sortField = "platform.name";
                    break;
                case "-platformName":
                    sortField = "platform.name";
                    sortDirection = Direction.DESC;
                    break;
                case "-name":
                    sortField = "name";
                    sortDirection = Direction.DESC;
                    break;
                default:
                    sortField = "name";
            }
        } else {
            sortField = "name";
        }
        PageRequest paging = PageRequest.of(page, pageSize, new Sort(sortDirection, sortField));
        Page<DatafileEntity> l;
        if (name != null) {
            if (catalog != null) {
                if (filterPlatform) {
                    l = datafileDAO.findByCatalogAndNameLikeAndPlatform(catalog.name(), "%" + name + "%", platform, paging);
                } else {
                    l = datafileDAO.findByCatalogAndNameLike(catalog.name(), "%" + name + "%", paging);
                }
            } else {
                if (filterPlatform) {
                    l = datafileDAO.findByNameLikeAndPlatform("%" + name + "%", platform, paging);
                } else {
                    l = datafileDAO.findByNameLike("%" + name + "%", paging);
                }
            }
        } else if (catalog != null) {
            if (filterPlatform) {
                l = datafileDAO.findByCatalogAndPlatform(catalog.name(), platform, paging);
            } else {
                l = datafileDAO.findByCatalog(catalog.name(), paging);
            }
        } else {
            if (filterPlatform) {
                l = datafileDAO.findByPlatform(platform, paging);
            } else {
                l = datafileDAO.findAll(paging);
            }
        }
        PaginatedResult<DatafileDTO> r = new PaginatedResult<>(page > 0, l.hasNext());
        for (DatafileEntity entity : l.getContent()) {
            r.add(entityToDTO(entity));
        }
        return r;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DatafileDTO changeDatafilePlatform(Long id, String platformName) throws PlatformNotFoundException {
        return entityToDTO(retrocenterDatafileService.changeDatafilePlatform(id, platformName));
    }

    private DatafileDTO entityToDTO(DatafileEntity entity) {
        DatafileDTO dto = new DatafileDTO(entity.getName(), entity.getCatalog(), entity.getVersion(), entity.getDescription(),
                entity.getAuthor(), entity.getDate(), entity.getEmail(), entity.getHomepage(), entity.getUrl(),
                entity.getComment(), entity.getId());
        if (entity.getPlatform() != null) {
            dto.setPlatformName(entity.getPlatform().getName());
        }
        return dto;
    }
}
