package br.com.javanei.retrocenter.platform.api;

import br.com.javanei.retrocenter.ErrorResponse;
import br.com.javanei.retrocenter.RetroCenterApiConfig;
import br.com.javanei.retrocenter.common.ArtifactFileTypeEnum;
import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.platform.Platform;
import br.com.javanei.retrocenter.platform.service.PlatformArtifactFileDTO;
import br.com.javanei.retrocenter.platform.service.PlatformArtifactFileImportHistoryDTO;
import br.com.javanei.retrocenter.platform.service.PlatformArtifactFileSavedDTO;
import br.com.javanei.retrocenter.platform.service.PlatformArtifactFileService;
import br.com.javanei.retrocenter.platform.service.PlatformDTO;
import br.com.javanei.retrocenter.platform.service.PlatformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/platforms")
@CrossOrigin(value = "*", allowedHeaders = "*")
@Api(tags = {"Platforms service"}, produces = "application/json")
public class PlatformRest {
    private static final Logger LOG = LoggerFactory.getLogger(PlatformRest.class);

    @Autowired
    private PlatformService service;
    @Autowired
    private PlatformArtifactFileService fileService;
    @Autowired
    private RetroCenterApiConfig config;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find a platform by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = PlatformDTO.class),
            @ApiResponse(code = 400, message = "Platform Not Found")
    })
    public ResponseEntity<PlatformDTO> findByID(@PathVariable("id") Long id) throws Exception {
        PlatformDTO p = service.findPlatformByID(id);
        if (p != null) {
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list of platforms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = PlatformCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<PlatformDTO>> find(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "alternateName", required = false) String alternateName,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        PaginatedResult<PlatformDTO> platforms = service.findPlatform(name, alternateName, page, pageSize);
        return ResponseEntity.ok(platforms);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a new platform")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = PlatformDTO.class),
            @ApiResponse(code = 400, message = "Platform already exists")
    })
    public ResponseEntity<PlatformDTO> create(@RequestBody Platform platform) throws Exception {
        if (service.findPlatformByName(platform.getName()) != null) {
            return new ResponseEntity(new ErrorResponse("Platform already exists"), HttpStatus.BAD_REQUEST);
        }
        PlatformDTO vo = service.createPlatform(platform);
        return new ResponseEntity(vo, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/files", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Upload a artifact")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok"),
            @ApiResponse(code = 400, message = "File is empty"),
            @ApiResponse(code = 400, message = "Platform Not Found")
    })
    public ResponseEntity<List<PlatformArtifactFileSavedDTO>> upload(
            @PathVariable("id") Long id,
            @RequestParam(value = "importInfo", required = true) @NotNull String importInfo,
            @RequestParam("type") @NotNull ArtifactFileTypeEnum type,
            @RequestParam("file") @NotNull MultipartFile uploadfile,
            @RequestParam(value = "expandZip", defaultValue = "true") boolean expandZip,
            @RequestParam(value = "expandInternalZip", defaultValue = "true") boolean expandInternalZip) throws Exception {
        if (uploadfile.isEmpty()) {
            LOG.info("File is empty");
            return new ResponseEntity(new ErrorResponse("File is empty"), HttpStatus.BAD_REQUEST);
        }
        LOG.info("File uploaded: " + uploadfile.getOriginalFilename()
                + ", size=" + uploadfile.getSize()
                + ", importInto=" + importInfo);
        List<PlatformArtifactFileSavedDTO> result = fileService.importFile(id, config.getRepositoryBaseDir(),
                importInfo, uploadfile.getOriginalFilename(), type, expandZip, expandInternalZip,
                uploadfile.getBytes());
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/files", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list files of platforms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = PlatformArtifactFileCollectionResult.class),
            @ApiResponse(code = 400, message = "Platform Not Found")
    })
    public ResponseEntity<PaginatedResult<PlatformArtifactFileDTO>> listFilesOfPlatform(
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize,
            @RequestParam(value = "showInfo", defaultValue = "false") Boolean showInfo
    ) throws Exception {
        PaginatedResult<PlatformArtifactFileDTO> result = fileService.findFilesByPlatform(id, page, pageSize, showInfo);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}/import-history", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list import history of platforms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = PlatformArtifactFileImportHistoryResult.class),
            @ApiResponse(code = 400, message = "Platform Not Found")
    })
    public ResponseEntity<PaginatedResult<PlatformArtifactFileImportHistoryDTO>> listImportHistory(@PathVariable("id") Long id,
                                                                                                   @RequestParam(value = "page", defaultValue = "0", required = true) int page,
                                                                                                   @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize,
                                                                                                   @RequestParam(value = "showFiles", defaultValue = "false") Boolean showFiles
    ) throws Exception {
        PaginatedResult<PlatformArtifactFileImportHistoryDTO> result = fileService.findImportHistory(id, showFiles, page, pageSize);
        return ResponseEntity.ok(result);
    }

    private class PlatformCollectionResult extends PaginatedResult<PlatformDTO> {
    }

    private class PlatformArtifactFileCollectionResult extends PaginatedResult<PlatformArtifactFileDTO> {
    }

    private class PlatformArtifactFileImportHistoryResult extends PaginatedResult<PlatformArtifactFileImportHistoryDTO> {
    }
}
