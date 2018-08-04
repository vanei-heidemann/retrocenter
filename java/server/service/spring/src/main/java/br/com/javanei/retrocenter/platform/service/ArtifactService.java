package br.com.javanei.retrocenter.platform.service;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.platform.Artifact;
import br.com.javanei.retrocenter.platform.entity.ArtifactEntity;
import br.com.javanei.retrocenter.platform.persistence.ArtifactDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtifactService {
    private static final Logger LOG = LoggerFactory.getLogger(ArtifactService.class);

    @Autowired
    private ArtifactDAO artifactDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ArtifactDTO findArtifactById(Long id) {
        ArtifactEntity entity = artifactDAO.getOne(id);
        if (entity != null) {
            return new ArtifactDTO(entity.getCode(), entity.getName(), entity.getId());
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ArtifactDTO findArtifactByCode(String code) {
        ArtifactEntity entity = artifactDAO.findByCode(code);
        if (entity != null) {
            return new ArtifactDTO(entity.getCode(), entity.getName(), entity.getId());
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<ArtifactDTO> findArtifactByName(String name) {
        List<ArtifactEntity> l = artifactDAO.findByNameLike("%" + name + "%");
        PaginatedResult<ArtifactDTO> result = new PaginatedResult<>();
        for (ArtifactEntity entity : l) {
            result.add(new ArtifactDTO(entity.getCode(), entity.getName(), entity.getId()));
        }
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<ArtifactDTO> findArtifactByNamePaging(String name, PageRequest paging) {
        Page<ArtifactEntity> l = artifactDAO.findByNameLike("%" + name + "%", paging);
        PaginatedResult<ArtifactDTO> result = new PaginatedResult<>(paging.getPageNumber() > 0, l.hasNext());
        for (ArtifactEntity entity : l.getContent()) {
            result.add(new ArtifactDTO(entity.getCode(), entity.getName(), entity.getId()));
        }
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<ArtifactDTO> findArtifacts(Long id, String code, String name, int page, int pageSize) {
        PageRequest paging = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));

        if (id != null) {
            ArtifactDTO entity = this.findArtifactById(id);
            if (entity != null) {
                PaginatedResult<ArtifactDTO> r = new PaginatedResult<>();
                r.add(entity);
                return r;
            }
            return new PaginatedResult<>();
        }
        if (code != null) {
            ArtifactDTO entity = this.findArtifactByCode(code);
            if (entity != null) {
                PaginatedResult<ArtifactDTO> r = new PaginatedResult<>();
                r.add(entity);
                return r;
            }
            return new PaginatedResult<>();
        }
        if (name != null) {
            return this.findArtifactByNamePaging(name, paging);
        }
        Page<ArtifactEntity> l = artifactDAO.findAll(paging);
        PaginatedResult<ArtifactDTO> result = new PaginatedResult<>(page > 0, l.hasNext());
        for (ArtifactEntity entity : l.getContent()) {
            result.add(new ArtifactDTO(entity.getCode(), entity.getName(), entity.getId()));
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArtifactDTO createArtifact(Artifact artifact) {
        ArtifactEntity entity = new ArtifactEntity(artifact.getCode(), artifact.getName());
        entity = artifactDAO.saveAndFlush(entity);
        return new ArtifactDTO(artifact.getCode(), artifact.getName(), entity.getId());
    }
}
