package br.com.javanei.retrocenter.platform.service;

import br.com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.common.PlatformNotFoundException;
import br.com.javanei.retrocenter.common.RetrocenterException;
import br.com.javanei.retrocenter.platform.entity.PlatformArtifactFileEntity;
import br.com.javanei.retrocenter.platform.entity.PlatformArtifactFileImportHistoryEntity;
import br.com.javanei.retrocenter.platform.entity.PlatformArtifactFileInfoEntity;
import br.com.javanei.retrocenter.platform.entity.PlatformArtifactFileInfoHistoryEntity;
import br.com.javanei.retrocenter.platform.entity.PlatformEntity;
import br.com.javanei.retrocenter.platform.persistence.PlatformArtifactFileDAO;
import br.com.javanei.retrocenter.platform.persistence.PlatformArtifactFileImportHistoryDAO;
import br.com.javanei.retrocenter.platform.persistence.PlatformArtifactFileInfoDAO;
import br.com.javanei.retrocenter.platform.persistence.PlatformArtifactFileInfoHistoryDAO;
import br.com.javanei.retrocenter.platform.persistence.PlatformDAO;
import br.com.javanei.retrocenter.util.FileUtil;
import br.com.javanei.retrocenter.util.StringUtil;
import br.com.javanei.retrocenter.util.ZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PlatformArtifactFileService {
    private static final Logger LOG = LoggerFactory.getLogger(PlatformArtifactFileService.class);

    @Autowired
    private PlatformDAO platformDAO;

    @Autowired
    private PlatformArtifactFileDAO fileDAO;
    @Autowired
    private PlatformArtifactFileInfoDAO infoDAO;
    @Autowired
    private PlatformArtifactFileImportHistoryDAO historyDAO;
    @Autowired
    private PlatformArtifactFileInfoHistoryDAO fileInfoHistoryDAO;

    private static File calculaDestFile(File baseDir, String fileName) {
        File r = new File(
                new File(
                        new File(
                                new File(
                                        new File(
                                                new File(
                                                        new File(baseDir, "REPO"),
                                                        fileName.substring(0, 1) + "0000000"),
                                                fileName.substring(8, 9) + "0000000"),
                                        fileName.substring(16, 17) + "0000000"),
                                fileName.substring(24, 25) + "0000000"),
                        fileName.substring(32, 33) + "0000000"),
                fileName);
        if (!r.getParentFile().exists()) {
            r.getParentFile().mkdirs();
        }
        return r;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PlatformArtifactFileSavedDTO> importFile(Long platformId, String repositoryBaseDir, String importInfo,
                                                         String originalArtifactFileName, ArtifactFileTypeEnum type,
                                                         Boolean expandZip, Boolean expandInternalZip,
                                                         byte[] fileContent)
            throws NoSuchAlgorithmException, IOException, RetrocenterException {
        LOG.info("importFile(platformId=" + platformId + ", repositoryBaseDir=" + repositoryBaseDir
                + ", importInfo=" + importInfo + ", originalArtifactFileName=" + originalArtifactFileName
                + ", type=" + type + ", fileContent=" + fileContent.length + ")"
        );
        List<PlatformArtifactFileSavedDTO> result = new LinkedList<>();

        PlatformEntity platform = platformDAO.getOne(platformId);
        if (platform == null) {
            throw new PlatformNotFoundException(platformId);
        }
        LOG.info("Platform: " + platform);
        if (expandZip.booleanValue() && ZipUtil.isZip(fileContent)) {
            LOG.info("  Is zip file");
            Map<String, byte[]> files = ZipUtil.extractToByteArray(fileContent);
            for (String name : files.keySet()) {
                result.addAll(importFile(platform, repositoryBaseDir, importInfo, name, type,
                        expandInternalZip.booleanValue(), files.get(name)));
            }
        } else {
            result.addAll(importFile(platform, repositoryBaseDir, importInfo, originalArtifactFileName, type,
                    expandInternalZip.booleanValue(), fileContent));
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PaginatedResult<PlatformArtifactFileDTO> findFilesByPlatform(Long platformId,
                                                                        int page,
                                                                        int pageSize,
                                                                        boolean showInfo) throws PlatformNotFoundException {
        PlatformEntity platform = platformDAO.getOne(platformId);
        if (platform == null) {
            throw new PlatformNotFoundException(platformId);
        }
        PageRequest paging = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "name"));
        Page<PlatformArtifactFileEntity> l = fileDAO.findByPlatform_id(platformId, paging);
        PaginatedResult<PlatformArtifactFileDTO> r = new PaginatedResult<>(page > 0, l.hasNext());
        for (PlatformArtifactFileEntity e : l) {
            PlatformArtifactFileDTO vo = new PlatformArtifactFileDTO(e.getId(), e.getName(), e.getType(), e.getSize(),
                    e.getCrc(), e.getMd5(), e.getSha1());
            r.add(vo);
            if (showInfo) {
                List<PlatformArtifactFileInfoEntity> li = infoDAO.findByPlatformArtifactFile_id(e.getId());
                for (PlatformArtifactFileInfoEntity ie : li) {
                    vo.addInfo(ie.getInfo());
                }
            }
        }
        return r;
    }

    public PaginatedResult<PlatformArtifactFileImportHistoryDTO> findImportHistory(Long platformId,
                                                                                   boolean showFiles,
                                                                                   int page,
                                                                                   int pageSize) throws PlatformNotFoundException {
        PlatformEntity platform = platformId != null ? platformDAO.getOne(platformId) : null;
        if (platformId != null && platform == null) {
            throw new PlatformNotFoundException(platformId);
        }
        PaginatedResult<PlatformArtifactFileImportHistoryDTO> result = new PaginatedResult<>();
        Page<PlatformArtifactFileImportHistoryEntity> l;
        PageRequest paging = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "description"));
        if (platform != null) {
            l = historyDAO.findByPlatform_id(platformId, paging);
        } else {
            l = historyDAO.findAll(paging);
        }
        for (PlatformArtifactFileImportHistoryEntity e : l) {
            PlatformArtifactFileImportHistoryDTO dto = new PlatformArtifactFileImportHistoryDTO(e.getId(),
                    e.getDescription(), e.getPlatform().getId(), e.getPlatform().getName());
            if (showFiles) {
                List<PlatformArtifactFileInfoHistoryEntity> lfih = fileInfoHistoryDAO.findByPlatformArtifactFileImportHistory_id(e.getId());
                for (PlatformArtifactFileInfoHistoryEntity efih : lfih) {
                    PlatformArtifactFileInfoEntity efi = efih.getPlatformArtifactFileInfo();
                    PlatformArtifactFileEntity ef = efi.getPlatformArtifactFile();
                    PlatformArtifactFileHistoryDTO historyDTO = new PlatformArtifactFileHistoryDTO(efih.getId(),
                            efi.getId(), ef.getId(), efi.getInfo(), ef.getName(), ef.getType(), ef.getSize(),
                            ef.getCrc(), ef.getMd5(), ef.getSha1());
                    dto.addFileHistory(historyDTO);
                }
            }
            result.add(dto);
        }
        return result;
    }

    private List<PlatformArtifactFileSavedDTO> importFile(PlatformEntity platform, String repositoryBaseDir, String importInfo,
                                                          String originalArtifactFileName, ArtifactFileTypeEnum type,
                                                          boolean expandZipContent,
                                                          byte[] fileContent) throws NoSuchAlgorithmException, IOException {
        LOG.info("  importFile(originalArtifactFileName=" + originalArtifactFileName + ")");
        List<PlatformArtifactFileSavedDTO> lResult = new LinkedList<>();
        if (expandZipContent && ZipUtil.isZip(fileContent)) {
            LOG.info("    It's a zip, extract content");
            Map<String, byte[]> files = ZipUtil.extractToByteArray(fileContent);
            for (String name : files.keySet()) {
                lResult.addAll(importFile(platform, repositoryBaseDir, importInfo, name, type,
                        expandZipContent, files.get(name)));
            }
            return lResult;
        }

        PlatformArtifactFileSavedDTO result = new PlatformArtifactFileSavedDTO();
        result.setPlatformId(platform.getId());
        result.setPlatformName(platform.getName());
        result.setFileName(originalArtifactFileName);
        result.setType(type.name());

        PlatformArtifactFileEntity entity = new PlatformArtifactFileEntity();
        entity.setType(type.name());
        entity.setCrc(StringUtil.toStringCRC(FileUtil.getCRC(fileContent)));
        entity.setMd5(StringUtil.toHexString(FileUtil.getMD5(fileContent)));
        entity.setSha1(StringUtil.toHexString(FileUtil.getSHA1(fileContent)));
        entity.setName(entity.getSha1());
        entity.setSize(new Long(fileContent.length));
        entity.setPlatform(platform);
        List<PlatformArtifactFileEntity> l = fileDAO.findByPlatform_idAndSha1(platform.getId(),
                entity.getSha1());
        for (PlatformArtifactFileEntity e : l) {
            if (entity.getCrc().equals(e.getCrc())
                    && entity.getMd5().equals(e.getMd5())
                    && entity.getSize().longValue() == e.getSize().longValue()) {
                LOG.info("  Already existis (" + e.getId() + ")");
                entity = e;
                break;
            }
        }

        if (entity.getId() == null) {
            // Ainda não existe
            PlatformArtifactFileEntity e = fileDAO.findByPlatform_idAndName(platform.getId(),
                    entity.getName());
            if (e != null) {
                // Já existe, concatena o CRC no nome
                e = fileDAO.findByPlatform_idAndName(platform.getId(),
                        entity.getName() + "-" + entity.getCrc());
                if (e != null) {
                    // Também já existe, tenta concatenar o size
                    e = fileDAO.findByPlatform_idAndName(platform.getId(),
                            entity.getName() + "-" + entity.getSize());
                    if (e != null) {
                        // Também já existe! Usa um número qualquer
                        entity.setName(entity.getName() + "-" + System.currentTimeMillis());
                    } else {
                        entity.setName(entity.getName() + "-" + entity.getSize());
                    }
                } else {
                    entity.setName(entity.getName() + "-" + entity.getCrc());
                }
            }
        }

        File file = new File(repositoryBaseDir);
        file = calculaDestFile(file, entity.getName() + ".zip");
        if (!file.exists()) {
            LOG.info("  Saving file: " + file.getAbsolutePath());
            ZipUtil.addFileToZip(file, entity.getName(), originalArtifactFileName, fileContent);
        } else {
            LOG.info("  File already exists: " + file.getAbsolutePath());
        }

        entity = fileDAO.save(entity);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PlatformArtifactFileImportHistoryEntity history = new PlatformArtifactFileImportHistoryEntity();
        history.setPlatform(platform);
        if (importInfo == null || importInfo.trim().isEmpty()) {
            history.setDescription(platform.getName() + ": " + df.format(new Date()));
        } else {
            history.setDescription(importInfo);
        }
        PlatformArtifactFileImportHistoryEntity eHistory = historyDAO.findByPlatform_idAndDescription(platform.getId(),
                history.getDescription());
        if (eHistory != null) {
            history = eHistory;
        }
        history.setLastDate(df.format(new Date()));
        history = historyDAO.save(history);
        result.setImportInfo(history.getDescription());

        String artifactFileName = originalArtifactFileName.contains("/")
                ? originalArtifactFileName.substring(originalArtifactFileName.lastIndexOf("/") + 1)
                : originalArtifactFileName;

        PlatformArtifactFileInfoEntity info = infoDAO.findByPlatformArtifactFile_idAndInfo(entity.getId(),
                artifactFileName);
        if (info == null) {
            info = new PlatformArtifactFileInfoEntity();
            info.setInfo(artifactFileName);
            info.setPlatformArtifactFile(entity);
            info = infoDAO.save(info);
            result.setFileName(info.getInfo());
        }

        PlatformArtifactFileInfoHistoryEntity fileInfoHistory = fileInfoHistoryDAO.findByPlatformArtifactFileInfo_idAndPlatformArtifactFileImportHistory_id(info.getId(), history.getId());
        if (fileInfoHistory == null) {
            fileInfoHistory = new PlatformArtifactFileInfoHistoryEntity();
            fileInfoHistory.setPlatformArtifactFileImportHistory(history);
            fileInfoHistory.setPlatformArtifactFileInfo(info);
            fileInfoHistory = fileInfoHistoryDAO.save(fileInfoHistory);
        }


        lResult.add(result);
        return lResult;
    }
}
