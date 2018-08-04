package br.com.javanei.retrocenter.platform.service;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.platform.Platform;
import br.com.javanei.retrocenter.platform.entity.PlatformAltNameEntity;
import br.com.javanei.retrocenter.platform.entity.PlatformEntity;
import br.com.javanei.retrocenter.platform.persistence.PlatformAltNameDAO;
import br.com.javanei.retrocenter.platform.persistence.PlatformDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class PlatformService {
    private static final Logger LOG = LoggerFactory.getLogger(PlatformService.class);

    @Autowired
    private PlatformDAO platformDAO;
    @Autowired
    private PlatformAltNameDAO platformAltNameDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public PlatformDTO createPlatform(Platform platform) {
        PlatformEntity entity = new PlatformEntity(platform.getName(), platform.getShortName(), platform.getStorageFolder());
        if (!platform.getAlternateNames().isEmpty()) {
            Set<PlatformAltNameEntity> alts = new HashSet<>();
            for (String s : platform.getAlternateNames()) {
                PlatformAltNameEntity e = new PlatformAltNameEntity(s);
                e.setPlatform(entity);
                alts.add(e);
            }
            entity.setAlternateNames(alts);
        }
        entity = platformDAO.saveAndFlush(entity);
        return new PlatformDTO(entity.getName(), entity.getShortName(), entity.getStorageFolder(), entity.getId());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PlatformDTO findPlatformByName(String name) {
        PlatformEntity entity = platformDAO.findByName(name);
        if (entity != null) {
            PlatformDTO p = new PlatformDTO(entity.getName(), entity.getShortName(), entity.getStorageFolder(), entity.getId());
            Set<PlatformAltNameEntity> alts = entity.getAlternateNames();
            if (!alts.isEmpty()) {
                for (PlatformAltNameEntity alt : alts) {
                    p.addAlternateName(alt.getAlternateName());
                }
            }
            return p;
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PlatformDTO findPlatformByID(Long id) {
        PlatformEntity entity = platformDAO.getOne(id);
        if (entity != null) {
            PlatformDTO p = new PlatformDTO(entity.getName(), entity.getShortName(), entity.getStorageFolder(), entity.getId());
            Set<PlatformAltNameEntity> alts = entity.getAlternateNames();
            if (!alts.isEmpty()) {
                for (PlatformAltNameEntity alt : alts) {
                    p.addAlternateName(alt.getAlternateName());
                }
            }
            return p;
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PlatformDTO findPlatform(String name) {
        PlatformEntity entity = platformDAO.findByName(name);
        if (entity == null) {
            PlatformAltNameEntity altEntity = platformAltNameDAO.findByAlternateName(name);
            if (altEntity != null) {
                entity = altEntity.getPlatform();
            }
        }
        if (entity != null) {
            PlatformDTO p = new PlatformDTO(entity.getName(), entity.getShortName(), entity.getStorageFolder(), entity.getId());
            Set<PlatformAltNameEntity> alts = entity.getAlternateNames();
            if (!alts.isEmpty()) {
                for (PlatformAltNameEntity alt : alts) {
                    p.addAlternateName(alt.getAlternateName());
                }
            }
            return p;
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<PlatformDTO> findPlatform(String name, String alternateName, int page, int pageSize) {
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        if (alternateName != null) {
            PlatformDTO p = this.findPlatform(alternateName);
            if (p != null) {
                PaginatedResult<PlatformDTO> r = new PaginatedResult<>();
                r.add(p);
                return r;
            }
            return new PaginatedResult<>();
        } else if (name != null) {
            PlatformDTO p = this.findPlatformByName(name);
            if (p != null) {
                PaginatedResult<PlatformDTO> r = new PaginatedResult<>();
                r.add(p);
                return r;
            }
            return new PaginatedResult<>();
        }
        return findAllPlatforms(page, pageSize);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaginatedResult<PlatformDTO> findAllPlatforms(int page, int pageSize) {
        PageRequest pr = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<PlatformEntity> entities = platformDAO.findAll(pr);
        PaginatedResult<PlatformDTO> result = new PaginatedResult<>(page > 0, entities.hasNext());
        for (PlatformEntity entity : entities.getContent()) {
            PlatformDTO p = new PlatformDTO(entity.getName(), entity.getShortName(), entity.getStorageFolder(), entity.getId());
            Set<PlatformAltNameEntity> alts = entity.getAlternateNames();
            if (!alts.isEmpty()) {
                for (PlatformAltNameEntity alt : alts) {
                    p.addAlternateName(alt.getAlternateName());
                }
            }
            result.add(p);
        }
        return result;
    }
}
