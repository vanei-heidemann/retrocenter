package br.com.javanei.retrocenter.datafile.clrmamepro.service;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDatafile;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProDisk;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProGame;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProHeader;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProResource;
import br.com.javanei.retrocenter.datafile.clrmamepro.CMProRom;
import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProDatafileEntity;
import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProDiskEntity;
import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProGameEntity;
import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProGameRomEntity;
import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProResourceEntity;
import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProResourceRomEntity;
import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProSampleEntity;
import br.com.javanei.retrocenter.datafile.clrmamepro.entity.CMProSampleofEntity;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProDatafileDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProDiskDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProGameDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProGameRomDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProResourceDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProResourceRomDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProSampleDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProSampleofDAO;
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
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CMProService {
    private static final Logger LOG = LoggerFactory.getLogger(CMProService.class);

    @Autowired
    private CMProDatafileDAO datafileDAO;
    @Autowired
    private CMProDiskDAO diskDAO;
    @Autowired
    private CMProGameDAO gameDAO;
    @Autowired
    private CMProGameRomDAO gameRomDAO;
    @Autowired
    private CMProResourceDAO resourceDAO;
    @Autowired
    private CMProResourceRomDAO resourceRomDAO;
    @Autowired
    private CMProSampleDAO sampleDAO;
    @Autowired
    private CMProSampleofDAO sampleofDAO;
    @Autowired
    private CMProService cmProService;

    private static CMProDatafileEntity datafileToEntity(CMProDatafile datafile) {
        CMProDatafileEntity entity = new CMProDatafileEntity(datafile.getHeader().getName(),
                datafile.getHeader().getCatalog(), datafile.getHeader().getVersion(),
                datafile.getHeader().getCategory(),
                datafile.getHeader().getDescription(), datafile.getHeader().getAuthor(),
                datafile.getHeader().getHomepage(), datafile.getHeader().getUrl(),
                datafile.getHeader().getForcemerging(), datafile.getHeader().getForcezipping());
        for (CMProGame game : datafile.getGames()) {
            entity.getGames().add(gameToEntity(game));
        }
        for (CMProResource resource : datafile.getResources()) {
            entity.getResources().add(resourceToEntity(resource));
        }
        entity.setCustomFields(datafile.getHeader().getCustomFields());

        return entity;
    }

    private static CMProGameEntity gameToEntity(CMProGame game) {
        CMProGameEntity entity = new CMProGameEntity(game.getName(), game.getDescription(), game.getYear(),
                game.getManufacturer(), game.getCloneof(), game.getRomof(), game.getSerial());

        for (CMProRom rom : game.getRoms()) {
            entity.getRoms().add(gameromToEntity(rom));
        }

        for (CMProDisk disk : game.getDisks()) {
            entity.getDisks().add(diskToEntity(disk));
        }

        for (String sampleof : game.getSampleof()) {
            entity.getSampleof().add(new CMProSampleofEntity(sampleof));
        }

        for (String sample : game.getSamples()) {
            entity.getSamples().add(new CMProSampleEntity(sample));
        }

        return entity;
    }

    private static CMProGameRomEntity gameromToEntity(CMProRom rom) {
        return new CMProGameRomEntity(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(),
                rom.getRegion(), rom.getFlags());
    }

    private static CMProDiskEntity diskToEntity(CMProDisk disk) {
        return new CMProDiskEntity(disk.getName(), disk.getSha1(), disk.getMd5());
    }

    private static CMProResourceEntity resourceToEntity(CMProResource resource) {
        CMProResourceEntity entity = new CMProResourceEntity(resource.getName(), resource.getDescription(),
                resource.getYear(), resource.getManufacturer());
        for (CMProRom rom : resource.getRoms()) {
            entity.getRoms().add(resourceromToEntity(rom));
        }

        return entity;
    }

    private static CMProResourceRomEntity resourceromToEntity(CMProRom rom) {
        return new CMProResourceRomEntity(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(),
                rom.getRegion(), rom.getFlags());
    }

    private static CMProDatafileDTO entityToDatafile(CMProDatafileEntity datafile, boolean details) {
        CMProDatafileDTO entity = new CMProDatafileDTO(new CMProHeader(datafile.getName(), datafile.getCatalog(),
                datafile.getVersion(), datafile.getDescription(), datafile.getCategory(),
                datafile.getAuthor(), datafile.getHomepage(), datafile.getUrl(),
                datafile.getForcemerging(), datafile.getForcezipping()), datafile.getId());
        if (details) {
            for (CMProGameEntity game : datafile.getGames()) {
                entity.addGame(entityToGame(game));
            }
            for (CMProResourceEntity resource : datafile.getResources()) {
                entity.addResource(entityToResource(resource));
            }
            entity.getHeader().setCustomFields(datafile.getCustomFields());
        }

        return entity;
    }

    private static CMProGame entityToGame(CMProGameEntity game) {
        CMProGame entity = new CMProGame(game.getName(), game.getDescription(), game.getYear(),
                game.getManufacturer(), game.getCloneof(), game.getRomof(), game.getSerial());

        for (CMProGameRomEntity rom : game.getRoms()) {
            entity.addRom(entityToGamerom(rom));
        }

        for (CMProDiskEntity disk : game.getDisks()) {
            entity.addDisk(entityToDisk(disk));
        }

        for (CMProSampleofEntity sampleof : game.getSampleof()) {
            entity.addSampleOf(sampleof.getSampleof());
        }

        for (CMProSampleEntity sample : game.getSamples()) {
            entity.addSample(sample.getSample());
        }

        return entity;
    }

    private static CMProRom entityToGamerom(CMProGameRomEntity rom) {
        return new CMProRom(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(),
                rom.getRegion(), rom.getFlags());
    }

    private static CMProDisk entityToDisk(CMProDiskEntity disk) {
        return new CMProDisk(disk.getName(), disk.getSha1(), disk.getMd5());
    }

    private static CMProResource entityToResource(CMProResourceEntity resource) {
        CMProResource entity = new CMProResource(resource.getName(), resource.getDescription(),
                resource.getYear(), resource.getManufacturer());
        for (CMProResourceRomEntity rom : resource.getRoms()) {
            entity.getRoms().add(entityToResourceROM(rom));
        }

        return entity;
    }

    private static CMProRom entityToResourceROM(CMProResourceRomEntity rom) {
        return new CMProRom(rom.getName(), rom.getSize(), rom.getCrc(), rom.getSha1(), rom.getMd5(),
                rom.getRegion(), rom.getFlags());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CMProDatafileEntity create(CMProDatafileEntity entity) {
        LOG.debug("create(name=" + entity.getName()
                + ", catalog=" + entity.getCatalog()
                + ", version=" + entity.getVersion() + ")");
        CMProDatafileEntity old = datafileDAO.findByUnique(entity.getName(), entity.getCatalog(), entity.getVersion());
        if (old == null) {
            entity = datafileDAO.saveAndFlush(entity);
        } else {
            LOG.debug("Datafile already exist");
            entity.setId(old.getId());
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CMProGameEntity createGame(CMProGameEntity gameEntity) {
        LOG.debug("create - game(" + gameEntity.getName() + ")");
        CMProGameEntity old = gameDAO.findByDatafileAndName(gameEntity.getDatafile().getName(),
                gameEntity.getDatafile().getCatalog(), gameEntity.getDatafile().getVersion(), gameEntity.getName());
        if (old != null) {
            LOG.debug("Game already exist");
            gameEntity.setId(old.getId());
            return gameEntity;
        }
        gameEntity = gameDAO.saveAndFlush(gameEntity);

        Set<CMProGameRomEntity> roms = gameEntity.getRoms();
        Set<CMProDiskEntity> disks = gameEntity.getDisks();
        Set<CMProSampleofEntity> samplesof = gameEntity.getSampleof();
        Set<CMProSampleEntity> samples = gameEntity.getSamples();

        for (CMProGameRomEntity rom : roms) {
            rom.setGame(gameEntity);
            gameRomDAO.saveAndFlush(rom);
        }

        for (CMProDiskEntity disk : disks) {
            disk.setGame(gameEntity);
            diskDAO.saveAndFlush(disk);
        }

        for (CMProSampleofEntity sampleof : samplesof) {
            sampleof.setGame(gameEntity);
            sampleofDAO.saveAndFlush(sampleof);
        }

        for (CMProSampleEntity sample : samples) {
            sample.setGame(gameEntity);
            sampleDAO.saveAndFlush(sample);
        }
        return gameEntity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CMProResourceEntity createResource(CMProResourceEntity resourceEntity) {
        LOG.debug("create - resource(" + resourceEntity.getName() + ")");
        CMProResourceEntity old = resourceDAO.findByDatafile_NameAndDatafile_CatalogAndDatafile_VersionAndName(resourceEntity.getDatafile().getName(),
                resourceEntity.getDatafile().getCatalog(), resourceEntity.getDatafile().getVersion(),
                resourceEntity.getName());
        if (old != null) {
            resourceEntity.setId(old.getId());
            LOG.debug("Resource already exist");
            return resourceEntity;
        }
        Set<CMProResourceRomEntity> roms = resourceEntity.getRoms();

        resourceEntity = resourceDAO.saveAndFlush(resourceEntity);

        for (CMProResourceRomEntity rom : roms) {
            rom.setResource(resourceEntity);
            resourceRomDAO.saveAndFlush(rom);
        }
        return resourceEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CMProDatafile create(CMProDatafile datafile) {
        LOG.info("create(name=" + datafile.getHeader().getName()
                + ", catalog=" + datafile.getHeader().getCatalog()
                + ", version=" + datafile.getHeader().getVersion() + ")");
        CMProDatafileEntity entity = datafileToEntity(datafile);
        Map<String, String> customFields = entity.getCustomFields();
        Set<CMProGameEntity> games = entity.getGames();
        Set<CMProResourceEntity> resources = entity.getResources();

        entity = cmProService.create(entity);

        int cont = 0;
        for (CMProGameEntity gameEntity : games) {
            gameEntity.setDatafile(entity);
            gameEntity = cmProService.createGame(gameEntity);
            cont++;
            if (cont % 100 == 0) {
                LOG.info("created game " + cont + " of " + games.size() + " : " + gameEntity.getName());
            }
        }

        cont = 0;
        for (CMProResourceEntity resourceEntity : resources) {
            resourceEntity.setDatafile(entity);
            resourceEntity = cmProService.createResource(resourceEntity);
            cont++;
            if (cont % 100 == 0) {
                LOG.info("created resource " + cont + " of " + resources.size() + " : " + resourceEntity.getName());
            }
        }

        return entityToDatafile(entity, true);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public CMProDatafileDTO findByUnique(String name, String catalog, String version) {
        CMProDatafileEntity entity = datafileDAO.findByUnique(name, catalog, version);
        if (entity != null) {
            return new CMProDatafileDTO(new CMProHeader(entity.getName(), entity.getCatalog(), entity.getVersion(),
                    entity.getDescription(), entity.getCatalog(),
                    entity.getAuthor(), entity.getHomepage(), entity.getUrl(),
                    entity.getForcemerging(), entity.getForcezipping()), entity.getId());
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public CMProDatafileDTO findByUniqueFull(String name, String catalog, String version) {
        CMProDatafileEntity entity = datafileDAO.findByUniqueFull(name, catalog, version);
        if (entity != null) {
            return entityToDatafile(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CMProDatafileDTO> findAll() {
        List<CMProDatafileEntity> l = datafileDAO.findAll();
        List<CMProDatafileDTO> result = new LinkedList<>();
        for (CMProDatafileEntity datafile : l) {
            result.add(new CMProDatafileDTO(new CMProHeader(datafile.getName(), datafile.getCatalog(),
                    datafile.getVersion(), datafile.getDescription(), datafile.getCategory(),
                    datafile.getAuthor(), datafile.getHomepage(), datafile.getUrl(),
                    datafile.getForcemerging(), datafile.getForcezipping()), datafile.getId()));
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public CMProDatafileDTO findByIdFull(Long id) {
        CMProDatafileEntity entity = datafileDAO.getOne(id);
        if (entity != null) {
            return entityToDatafile(entity, true);
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<CMProDatafileDTO> find(String name, int page, int pageSize) {
        PageRequest paging = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<CMProDatafileEntity> p;
        if (name != null) {
            p = datafileDAO.findByNameLike("%" + name + "%", paging);
        } else {
            p = datafileDAO.findAll(paging);
        }
        PaginatedResult<CMProDatafileDTO> result = new PaginatedResult<>(page > 0, p.hasNext());
        for (CMProDatafileEntity entity : p.getContent()) {
            result.add(entityToDatafile(entity, false));
        }
        return result;
    }
}
