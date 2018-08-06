package br.com.javanei.retrocenter.datafile.service;

import br.com.javanei.retrocenter.common.PlatformNotFoundException;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifact;
import br.com.javanei.retrocenter.datafile.common.DatafileArtifactFile;
import br.com.javanei.retrocenter.datafile.common.Release;
import br.com.javanei.retrocenter.datafile.entity.DatafileArtifactEntity;
import br.com.javanei.retrocenter.datafile.entity.DatafileArtifactFileEntity;
import br.com.javanei.retrocenter.datafile.entity.DatafileEntity;
import br.com.javanei.retrocenter.datafile.entity.DatafileReleaseEntity;
import br.com.javanei.retrocenter.datafile.persistence.DatafileArtifactDAO;
import br.com.javanei.retrocenter.datafile.persistence.DatafileArtifactFileDAO;
import br.com.javanei.retrocenter.datafile.persistence.DatafileDAO;
import br.com.javanei.retrocenter.datafile.persistence.DatafileReleaseDAO;
import br.com.javanei.retrocenter.platform.entity.PlatformEntity;
import br.com.javanei.retrocenter.platform.service.PlatformDTO;
import br.com.javanei.retrocenter.platform.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RetrocenterDatafileService {
    private static final Logger LOG = LoggerFactory.getLogger(RetrocenterDatafileService.class);

    @Autowired
    private DatafileDAO datafileDAO;
    @Autowired
    private DatafileArtifactDAO artifactDAO;
    @Autowired
    private DatafileArtifactFileDAO artifactFileDAO;
    @Autowired
    private DatafileReleaseDAO releaseDAO;
    @Autowired
    private RetrocenterDatafileService retrocenterDatafileService;
    @Autowired
    private PlatformService platformService;

    @Transactional(propagation = Propagation.REQUIRED)
    public DatafileEntity create(Datafile datafile) throws PlatformNotFoundException {
        LOG.info("create(" + datafile.getName() + ", " + datafile.getCatalog() + ", " + datafile.getVersion() + ")");
        DatafileEntity entity = new DatafileEntity(datafile.getName(), datafile.getCatalog(), datafile.getVersion(),
                datafile.getDescription(), datafile.getAuthor(), datafile.getDate(), datafile.getEmail(),
                datafile.getHomepage(), datafile.getUrl(), datafile.getComment());
        if (datafile.getPlatformName() != null) {
            PlatformDTO platform = platformService.findPlatform(datafile.getName());
            if (platform == null) {
                throw new PlatformNotFoundException(datafile.getName());
            }
            entity.setPlatform(new PlatformEntity(platform.getId()));
        }

        entity = retrocenterDatafileService.create(entity);

        int cont = 0;
        for (DatafileArtifact game : datafile.getArtifacts()) {
            DatafileArtifactEntity gameEntity = new DatafileArtifactEntity(game.getName(), game.getDescription(),
                    game.getYear(), game.getComment(), game.getFields());
            gameEntity.setDatafile(entity);
            for (DatafileArtifactFile gameFile : game.getFiles()) {
                DatafileArtifactFileEntity gameFileEntity = new DatafileArtifactFileEntity(gameFile.getType(), gameFile.getName(),
                        gameFile.getSize(), gameFile.getCrc(), gameFile.getSha1(), gameFile.getMd5(),
                        gameFile.getDate(), gameFile.getFields());
                gameEntity.getFiles().add(gameFileEntity);
            }
            for (Release release : game.getReleases()) {
                DatafileReleaseEntity releaseEntity = new DatafileReleaseEntity(release.getName(), release.getRegion(),
                        release.getLanguage(), release.getDate(), release.getDefault());
                gameEntity.getReleases().add(releaseEntity);
            }

            gameEntity = retrocenterDatafileService.createArtifact(gameEntity);
            entity.getArtifacts().add(gameEntity);

            cont++;
            if (cont % 100 == 0) {
                LOG.info("created artifact " + cont + " of " + datafile.getArtifacts().size() + ": " + game.getName());
            }
        }

        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DatafileEntity create(DatafileEntity entity) {
        LOG.debug("create(name=" + entity.getName() + ", catalog=" + entity.getCatalog()
                + ", version=" + entity.getVersion() + ")");
        boolean changePlatforms = false;
        List<Long> ids = datafileDAO.findPlatformIDsByNameAndCatalog(entity.getName(), entity.getCatalog());
        if (entity.getPlatform() == null) {
            // Tenta adivinhar a plataforma
            if (ids.size() == 1) {
                // Todos os datafiles com o mesmo nome que possuem plataforma, estão na mesma plataforma,
                // então assume essa plataforma
                entity.setPlatform(new PlatformEntity(ids.get(0)));
            } else if (ids.size() > 1) {
                // Há mais de uma plataforma definida para catalogos do mesmo nome, não é possível determinar
                // qual o correto.
                LOG.warn("Impossivel determinar a plataforma, pois foram encontradas " + ids.size()
                        + " plataformas");
            }
        } else {
            // Tentar alterar a plataforma dos Datafiles já existentes.
            if (ids.isEmpty()) {
                // Não há nenhum datafile com plataforma definida, então assume que todas pertencerão a plataforma
                // informada.
                changePlatforms = true;
            } else if (ids.size() == 1 && ids.get(0).longValue() == entity.getPlatform().getId().longValue()) {
                // Todas os datafiles com mesmo nome estão sem plataforma ou pertencem a plataforma informada,
                // então iguala todos sem plataforma à plataforma informada.
                changePlatforms = true;
            }
        }

        DatafileEntity old = datafileDAO.findByUnique(entity.getName(), entity.getCatalog(), entity.getVersion());
        if (old == null) {
            entity = datafileDAO.saveAndFlush(entity);
        } else {
            LOG.debug("Datafile already exist");
            entity.setId(old.getId());
        }

        if (changePlatforms) {
            datafileDAO.updatePlatformFromNameAndCatalog(entity.getName(), entity.getCatalog(), entity.getPlatform());
        }

        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DatafileArtifactEntity createArtifact(DatafileArtifactEntity gameEntity) {
        LOG.debug("createArtifact(" + gameEntity.getName() + ")");
        DatafileArtifactEntity old = artifactDAO.findByDatafileAndName(gameEntity.getDatafile().getName(),
                gameEntity.getDatafile().getCatalog(), gameEntity.getDatafile().getVersion(), gameEntity.getName());
        if (old != null) {
            LOG.debug("Artifact already exist");
            gameEntity.setId(old.getId());
            return gameEntity;
        }
        gameEntity = artifactDAO.saveAndFlush(gameEntity);
        for (DatafileArtifactFileEntity gameFileEntity : gameEntity.getFiles()) {
            gameFileEntity.setArtifact(gameEntity);
            artifactFileDAO.saveAndFlush(gameFileEntity);
        }

        for (DatafileReleaseEntity releaseEntity : gameEntity.getReleases()) {
            releaseEntity.setArtifact(gameEntity);
            releaseDAO.saveAndFlush(releaseEntity);
        }

        return gameEntity;
    }
}
