package br.com.javanei.retrocenter.gamedb.launchbox.service;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxCompany;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxDatafile;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGame;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGameImage;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGenre;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxPlatform;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxRegion;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxCompanyEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileGameEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileGenreEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafilePlatformEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxDatafileRegionEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameGenreEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameImageEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGameRegionEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxGenreEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxPlatformEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.entity.LBoxRegionEntity;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxCompanyDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxDatafileDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxDatafileGameDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxDatafileGenreDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxDatafilePlatformDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxDatafileRegionDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxGameDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxGenreDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxPlatformDAO;
import br.com.javanei.retrocenter.gamedb.launchbox.persistence.LBoxRegionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class LBoxService {
    private static final Logger LOG = LoggerFactory.getLogger(LBoxService.class);

    @Autowired
    private LBoxPlatformDAO platformDAO;
    @Autowired
    private LBoxCompanyDAO companyDAO;
    @Autowired
    private LBoxRegionDAO regionDAO;
    @Autowired
    private LBoxGenreDAO genreDAO;
    @Autowired
    private LBoxGameDAO gameDAO;
    @Autowired
    private LBoxDatafileDAO datafileDAO;
    @Autowired
    private LBoxDatafileGameDAO datafileGameDAO;
    @Autowired
    private LBoxDatafilePlatformDAO datafilePlatformDAO;
    @Autowired
    private LBoxDatafileRegionDAO datafileRegionDAO;
    @Autowired
    private LBoxDatafileGenreDAO datafileGenreDAO;
    @Autowired
    private LBoxService lboxService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxDatafileDTO> findDatafiles(int page, int pageSize) {
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "version"));
        Page<LBoxDatafileEntity> l = datafileDAO.findAll(pr);
        PaginatedResult<LBoxDatafileDTO> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (LBoxDatafileEntity e : l) {
            result.add(new LBoxDatafileDTO(e.getId(), e.getVersion()));
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LBoxDatafileDTO save(LBoxDatafile datafile) throws Exception {
        LOG.info("save(" + datafile.getVersion() + ")");

        LBoxDatafileEntity entity = new LBoxDatafileEntity(datafile.getVersion());
        LBoxDatafileEntity exist = datafileDAO.findByVersion(datafile.getVersion());
        Map<Long, LBoxDatafilePlatformEntity> platforms = new HashMap<>();
        Map<Long, LBoxDatafileGenreEntity> genres = new HashMap<>();
        Map<Long, LBoxDatafileRegionEntity> regions = new HashMap<>();
        Map<String, LBoxDatafileGameEntity> games = new HashMap<>();
        Map<String, LBoxDatafileGameEntity> newGames = new HashMap<>();
        if (exist != null) {
            entity.setId(exist.getId());
            for (LBoxDatafileRegionEntity e : exist.getRegions()) {
                regions.put(e.getRegion().getId(), e);
            }
            for (LBoxDatafileGenreEntity e : exist.getGenres()) {
                genres.put(e.getGenre().getId(), e);
            }
            for (LBoxDatafilePlatformEntity e : exist.getPlatforms()) {
                platforms.put(e.getPlatform().getId(), e);
            }
            for (LBoxDatafileGameEntity e : exist.getGames()) {
                games.put(e.getGame().getDatabaseID(), e);
            }
        }

        LOG.info("save(" + datafile.getVersion() + ") - Saving genres (" + datafile.getGenres().size() + ")");
        for (LBoxGenre genre : datafile.getGenres()) {
            LBoxGenreEntity ge = lboxService.saveGenre(genre);
            LBoxDatafileGenreEntity dge = genres.get(ge.getId());
            if (dge == null) {
                dge = new LBoxDatafileGenreEntity(entity, ge);
            }
            entity.getGenres().add(dge);
        }

        LOG.info("save(" + datafile.getVersion() + ") - Saving regions (" + datafile.getRegions().size() + ")");
        for (LBoxRegion region : datafile.getRegions()) {
            LBoxRegionEntity re = lboxService.saveRegion(region);
            LBoxDatafileRegionEntity dre = regions.get(re.getId());
            if (dre == null) {
                dre = new LBoxDatafileRegionEntity(entity, re);
            }
            entity.getRegions().add(dre);
        }

        LOG.info("save(" + datafile.getVersion() + ") - Saving platforms (" + datafile.getPlatforms().size() + ")");
        for (LBoxPlatform platform : datafile.getPlatforms()) {
            LBoxPlatformEntity pe = lboxService.savePlatform(platform);
            LBoxDatafilePlatformEntity dpe = platforms.get(pe.getId());
            if (dpe == null) {
                dpe = new LBoxDatafilePlatformEntity(entity, pe);
            }
            entity.getPlatforms().add(dpe);
        }

        LOG.info("save(" + datafile.getVersion() + ") - saving games (" + datafile.getGames().size() + ")");
        for (LBoxGame game : datafile.getGames()) {
            LBoxGameEntity ge = lboxService.saveGame(game);
            LBoxDatafileGameEntity dge = games.get(ge.getDatabaseID());
            if (dge == null) {
                dge = new LBoxDatafileGameEntity(entity, ge);
            }
            entity.getGames().add(dge);
            if (entity.getGames().size() % 1000 == 0) {
                LOG.info("save(" + datafile.getVersion() + ") - saved games: "
                        + entity.getGames().size() + " of " + datafile.getGames().size()
                        + " : " + ge.getName() + " (" + ge.getDatabaseID() + ")");
            }
            newGames.put(ge.getDatabaseID(), dge);
        }

        LOG.info("save(" + datafile.getVersion() + ") - Saving datafile");
        entity = lboxService.saveDatafile(entity);
        LOG.info("save(" + datafile.getVersion() + ") - Saving datafile game");
        int count = 0;
        for (LBoxDatafileGameEntity e : newGames.values()) {
            e = lboxService.saveDatafileGame(e);
            count++;
            if (count % 1000 == 0) {
                LOG.info("save(" + datafile.getVersion() + ") - saved datafile games: "
                        + count + " of " + newGames.size()
                        + " : " + e.getGame().getName() + " (" + e.getGame().getDatabaseID() + ")");
            }
        }
        LOG.info("save(" + datafile.getVersion() + ") - Deleting games (" + games.size() + ")");
        for (LBoxDatafileGameEntity e : games.values()) {
            if (newGames.get(e.getGame().getDatabaseID()) == null) {
                datafileGameDAO.deleteById(e.getId());
            }
        }
        LOG.info("save(" + datafile.getVersion() + ")=" + entity.getId());

        return new LBoxDatafileDTO(entity.getId(), entity.getVersion());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxDatafileEntity saveDatafile(LBoxDatafileEntity entity) {
        entity = datafileDAO.saveAndFlush(entity);
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxDatafileGameEntity saveDatafileGame(LBoxDatafileGameEntity entity) {
        if (entity.getId() == null) {
            entity.setDatafile(datafileDAO.getOne(entity.getDatafile().getId()));
            entity.setGame(gameDAO.getOne(entity.getGame().getDatabaseID()));
            entity = datafileGameDAO.saveAndFlush(entity);
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxRegionEntity saveRegion(LBoxRegion region) {
        LOG.debug("saveRegion(region.name=" + region.getName() + ")");
        LBoxRegionEntity entity = regionDAO.findByName(region.getName());
        if (entity != null) {
            return entity;
        }
        entity = new LBoxRegionEntity(region.getName());
        return regionDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxGenreEntity saveGenre(LBoxGenre genre) {
        LOG.debug("saveGenre(genre.name=" + genre.getName() + ")");
        LBoxGenreEntity entity = genreDAO.findByName(genre.getName());
        if (entity != null) {
            return entity;
        }
        entity = new LBoxGenreEntity(genre.getName());
        return genreDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxCompanyEntity saveCompany(LBoxCompany company) {
        LOG.debug("saveCompany(company.name=" + company.getName() + ")");
        LBoxCompanyEntity entity = companyDAO.findByName(company.getName());
        if (entity != null) {
            return entity;
        }
        entity = new LBoxCompanyEntity(company.getName());
        return companyDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxPlatformEntity savePlatform(LBoxPlatform platform) {
        LOG.debug("savePlatform(platform.name=" + platform.getName() + ")");
        LBoxPlatformEntity entity = platformDAO.findByName(platform.getName());
        if (entity == null) {
            entity = new LBoxPlatformEntity();
        }
        entity.setName(platform.getName());
        entity.setReleaseDate(platform.getReleaseDate());
        entity.setAlternateNames(platform.getAlternateNames());
        if (platform.getManufacturer() != null) {
            entity.setManufacturer(lboxService.saveCompany(platform.getManufacturer()));
        }
        if (platform.getDeveloper() != null) {
            entity.setDeveloper(lboxService.saveCompany(platform.getDeveloper()));
        }
        return platformDAO.saveAndFlush(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LBoxGameEntity saveGame(LBoxGame game) {
        LOG.debug("saveGame(game.databaseID=" + game.getDatabaseID() + ", game.name=" + game.getName() + ")");
        Map<Long, LBoxGameGenreEntity> genres = new HashMap<>();
        Map<Long, LBoxGameRegionEntity> regions = new HashMap<>();
        Map<Integer, LBoxGameImageEntity> images = new HashMap<>();

        LBoxGameEntity entity = new LBoxGameEntity(game.getDatabaseID());
        LBoxGameEntity exist = gameDAO.getOne(game.getDatabaseID());
        if (exist != null) {
            for (LBoxGameGenreEntity e : exist.getGenres()) {
                genres.put(e.getGenre().getId(), e);
            }
            for (LBoxGameRegionEntity e : exist.getRegions()) {
                regions.put(e.getRegion().getId(), e);
            }
            for (LBoxGameImageEntity e : exist.getImages()) {
                images.put(e.hashCode(), e);
            }
        }

        entity.setFileNames(game.getFileNames());
        entity.setName(game.getName());
        entity.setReleaseDate(game.getReleaseDate());
        entity.setReleaseYear(game.getReleaseYear());
        entity.setPlatform(lboxService.savePlatform(game.getPlatform()));
        if (game.getDeveloper() != null) {
            entity.setDeveloper(lboxService.saveCompany(game.getDeveloper()));
        }
        if (game.getPublisher() != null) {
            entity.setPublisher(lboxService.saveCompany(game.getPublisher()));
        }
        if (!game.getGenres().isEmpty()) {
            for (LBoxGenre genre : game.getGenres()) {
                LBoxGenreEntity e = lboxService.saveGenre(genre);
                LBoxGameGenreEntity ee = genres.get(e.getId());
                if (ee == null) {
                    ee = new LBoxGameGenreEntity(entity, e);
                }
                entity.getGenres().add(ee);
            }
        }
        if (!game.getRegions().isEmpty()) {
            for (LBoxRegion region : game.getRegions()) {
                LBoxRegionEntity e = lboxService.saveRegion(region);
                LBoxGameRegionEntity ee = regions.get(e.getId());
                if (ee == null) {
                    ee = new LBoxGameRegionEntity(entity, e);
                }
                entity.getRegions().add(ee);
            }
        }
        if (!game.getImages().isEmpty()) {
            for (LBoxGameImage image : game.getImages()) {
                LBoxGameImageEntity e = new LBoxGameImageEntity(entity, image.getFileName(), image.getType(),
                        image.getCrc32());
                if (image.getRegion() != null) {
                    e.setRegion(lboxService.saveRegion(new LBoxRegion(image.getRegion())));
                }
                LBoxGameImageEntity ee = images.get(e.hashCode());
                entity.getImages().add(ee != null ? ee : e);
            }
        }
        entity = gameDAO.saveAndFlush(entity);
        return entity;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxGame> findGames(Map<String, Object> params, int page, int pageSize) {
        PageRequest paging = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        LOG.debug("findGames(" + params + ")");
        Page<LBoxGameEntity> l;
        if (params.get("name") != null) {
            l = gameDAO.findByNameLike("%" + params.get("name") + "%", paging);
        } else {
            l = gameDAO.findAll(paging);
        }
        PaginatedResult<LBoxGame> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (LBoxGameEntity e : l) {
            result.add(entityToGameVO(e));
        }
        LOG.debug("findGames(): " + l.getSize());
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxGame> findGamesByVersion(String version, Map<String, Object> params, int page, int pageSize) {
        PageRequest paging = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "game.name"));
        LOG.debug("findGamesByVersion(version=" + version + ", params=" + params + ")");

        Page<LBoxDatafileGameEntity> l;
        if (params.get("name") != null) {
            l = datafileGameDAO.findByDatafile_VersionAndGame_NameLike(version, "%" + params.get("name") + "%", paging);
        } else {
            l = datafileGameDAO.findByDatafile_Version(version, paging);
        }
        PaginatedResult<LBoxGame> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (LBoxDatafileGameEntity game : l) {
            result.add(entityToGameVO(game.getGame()));
        }
        LOG.debug("findGamesByVersion(version=" + version + "): " + l.getSize());
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxRegionDTO> findRegionsByVersion(String version, int page, int pageSize) {
        LOG.debug("findRegionsByVersion(version=" + version + ")");
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "region.name"));
        Page<LBoxDatafileRegionEntity> l = datafileRegionDAO.findByDatafile_Version(version, pr);
        PaginatedResult<LBoxRegionDTO> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (LBoxDatafileRegionEntity region : l.getContent()) {
            result.add(entityToRegionDTO(region.getRegion()));
        }
        LOG.debug("findRegionsByVersion(version=" + version + "): " + l.getSize());
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxGenreDTO> findGenresByVersion(String version, int page, int pageSize) {
        LOG.debug("findGenresByVersion(version=" + version + ", page=" + page + ", pageSize=" + pageSize + ")");
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "genre.name"));
        Page<LBoxDatafileGenreEntity> genres = datafileGenreDAO.findByDatafile_Version(version, pr);
        PaginatedResult<LBoxGenreDTO> result = new PaginatedResult<>(page > 0, genres.hasNext());
        for (LBoxDatafileGenreEntity entity : genres) {
            result.add(entityToGenreDTO(entity.getGenre()));
        }
        LOG.debug("findGenresByVersion(version=" + version + "): " + genres.getSize());
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxPlatformDTO> findPlatformsByVersion(String version, Long platformId,
                                                                   int page, int pageSize) {
        LOG.debug("findPlatformsByVersion(version=" + version + ", platformId=" + platformId + ")");
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "platform.name"));
        Page<LBoxDatafilePlatformEntity> l = platformId == null
                ? datafilePlatformDAO.findByDatafile_Version(version, pr)
                : datafilePlatformDAO.findByDatafile_VersionAndPlatform_PlatformId(version, platformId, pr);
        PaginatedResult<LBoxPlatformDTO> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (LBoxDatafilePlatformEntity p : l.getContent()) {
            result.add(entityToPlatformDTO(p.getPlatform()));
        }
        LOG.debug("findPlatformsByVersion(version=" + version + "): " + l.getSize());
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxPlatformDTO> findPlatforms(Long platformId, int page, int pageSize) {
        LOG.debug("findPlatforms()");
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<LBoxPlatformEntity> l = platformId == null ? platformDAO.findAll(pr) : platformDAO.findByPlatformId(platformId, pr);
        PaginatedResult<LBoxPlatformDTO> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (LBoxPlatformEntity e : l) {
            result.add(entityToPlatformDTO(e));
        }
        LOG.debug("findPlatforms(): " + l.getSize());
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxGenreDTO> findGenres(int page, int pageSize) {
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        LOG.debug("findGenres()");
        Page<LBoxGenreEntity> l = genreDAO.findAll(pr);
        PaginatedResult<LBoxGenreDTO> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (LBoxGenreEntity e : l) {
            result.add(entityToGenreDTO(e));
        }
        LOG.debug("findGenres(): " + l.getSize());
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<LBoxRegionDTO> findRegions(int page, int pageSize) {
        LOG.debug("findRegions(page=" + page + ", pageSize=" + pageSize + ")");
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<LBoxRegionEntity> l = regionDAO.findAll(pr);
        PaginatedResult<LBoxRegionDTO> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (LBoxRegionEntity e : l) {
            result.add(entityToRegionDTO(e));
        }
        LOG.debug("findRegions(): " + l.getSize());
        return result;
    }

    private LBoxPlatformDTO entityToPlatformDTO(LBoxPlatformEntity entity) {
        if (entity != null) {
            LBoxPlatformDTO p = new LBoxPlatformDTO(entity.getName(), entity.getId(), entity.getPlatformId());
            p.setManufacturer(entityToCompanyDTO(entity.getManufacturer()));
            p.setDeveloper(entityToCompanyDTO(entity.getDeveloper()));
            p.setAlternateNames(entity.getAlternateNames());
            return p;
        }
        return null;
    }

    private LBoxCompanyDTO entityToCompanyDTO(LBoxCompanyEntity entity) {
        if (entity != null) {
            return new LBoxCompanyDTO(entity.getName(), entity.getId());
        }
        return null;
    }

    private LBoxGenreDTO entityToGenreDTO(LBoxGenreEntity entity) {
        if (entity != null) {
            return new LBoxGenreDTO(entity.getName(), entity.getId());
        }
        return null;
    }

    private LBoxRegionDTO entityToRegionDTO(LBoxRegionEntity entity) {
        if (entity != null) {
            return new LBoxRegionDTO(entity.getName(), entity.getId());
        }
        return null;
    }

    private LBoxGameImage entityToGameImageVO(LBoxGameImageEntity entity) {
        if (entity != null) {
            return new LBoxGameImage(entity.getFileName(), entity.getType(),
                    entity.getRegion() != null ? entity.getRegion().getName() : null,
                    entity.getCrc32());
        }
        return null;
    }

    private LBoxGame entityToGameVO(LBoxGameEntity ge) {
        LBoxGame g = new LBoxGame(ge.getDatabaseID(), ge.getName());
        g.setReleaseYear(ge.getReleaseYear());
        g.setReleaseDate(ge.getReleaseDate());
        g.setPublisher(entityToCompanyDTO(ge.getPublisher()));
        g.setDeveloper(entityToCompanyDTO(ge.getDeveloper()));
        g.setPlatform(new LBoxPlatformDTO(ge.getPlatform().getName(), ge.getPlatform().getId(),
                ge.getPlatform().getPlatformId()));
        //g.setPlatform(entityToPlatformDTO(ge.getPlatform()));
        g.setFileNames(ge.getFileNames());
        for (LBoxGameImageEntity gi : ge.getImages()) {
            g.addImage(entityToGameImageVO(gi));
        }
        for (LBoxGameRegionEntity re : ge.getRegions()) {
            g.addRegion(entityToRegionDTO(re.getRegion()));
        }
        for (LBoxGameGenreEntity genre : ge.getGenres()) {
            g.addGenre(entityToGenreDTO(genre.getGenre()));
        }
        return g;
    }
}
