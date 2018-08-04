package br.com.javanei.retrocenter.gamedb.launchbox.api;

import br.com.javanei.retrocenter.ErrorResponse;
import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxDatafile;
import br.com.javanei.retrocenter.gamedb.launchbox.LBoxGame;
import br.com.javanei.retrocenter.gamedb.launchbox.parser.LaunchBoxParser;
import br.com.javanei.retrocenter.gamedb.launchbox.service.LBoxDatafileDTO;
import br.com.javanei.retrocenter.gamedb.launchbox.service.LBoxGenreDTO;
import br.com.javanei.retrocenter.gamedb.launchbox.service.LBoxPlatformDTO;
import br.com.javanei.retrocenter.gamedb.launchbox.service.LBoxRegionDTO;
import br.com.javanei.retrocenter.gamedb.launchbox.service.LBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/gamedb/launchbox")
@CrossOrigin(value = "*", allowedHeaders = "*")
@Api(tags = {"LaunchBox service"}, produces = "application/json")
public class LaunchBoxRest {
    private static final Logger LOG = LoggerFactory.getLogger(LaunchBoxRest.class);

    @Autowired
    public LBoxService service;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = CollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxDatafileDTO>> findDatafiles(
            @ApiParam(name = "page", required = false) @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize", defaultValue = "100", required = true) @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize) {
        return ResponseEntity.ok(service.findDatafiles(page, pageSize));
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List games")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = GameCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxGame>> findGames(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        Map<String, Object> params = new HashMap<>();
        if (name != null && !name.trim().isEmpty()) {
            params.put("name", name.trim());
        }
        return ResponseEntity.ok(service.findGames(params, page, pageSize));
    }

    @RequestMapping(value = "/{version:.+}/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List games by LaunchBox version")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = GameCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxGame>> findGamesByVersion(
            @PathVariable(value = "version", required = true) String version,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        Map<String, Object> params = new HashMap<>();
        if (name != null && !name.trim().isEmpty()) {
            params.put("name", name.trim());
        }
        return ResponseEntity.ok(service.findGamesByVersion(version, params, page, pageSize));
    }

    @RequestMapping(value = "/{version:.+}/platforms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List platforms by LaunchBox version")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = PlatformCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxPlatformDTO>> findPlatformsByVersion(
            @PathVariable(value = "version", required = true) String version,
            @ApiParam(name = "platformId") @RequestParam(value = "platformId", required = false) Long platformId,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        return ResponseEntity.ok(service.findPlatformsByVersion(version, platformId, page, pageSize));
    }

    @RequestMapping(value = "/platforms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List platforms")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = PlatformCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxPlatformDTO>> findPlatforms(
            @ApiParam(name = "platformId") @RequestParam(value = "platformId", required = false) Long platformId,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        LOG.info("::::::::: findPlatforms(" + platformId + ", " + page + ", " + pageSize + ")");
        return ResponseEntity.ok(service.findPlatforms(platformId, page, pageSize));
    }

    @RequestMapping(value = "/{version:.+}/regions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List regions by LaunchBox version")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = RegionCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxRegionDTO>> findRegionsByVersion(@PathVariable(value = "version", required = true) String version,
                                                                               @RequestParam(value = "page", defaultValue = "0", required = true) int page,
                                                                               @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        return ResponseEntity.ok(service.findRegionsByVersion(version, page, pageSize));
    }

    @RequestMapping(value = "/{version:.+}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Parse and save LaunchBox Datafiles")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok"),
            @ApiResponse(code = 400, message = "Datafile is empty")
    })
    public ResponseEntity<LBoxDatafileDTO> upload(
            @PathVariable(value = "version", required = true) String version,
            @RequestParam(value = "metadataXML", required = true) MultipartFile metadataXML,
            @RequestParam(value = "filesXML", required = true) MultipartFile filesXML) throws Exception {
        if (metadataXML.isEmpty()) {
            LOG.info("metadataXML is empty");
            return new ResponseEntity(new ErrorResponse("File is empty"), HttpStatus.BAD_REQUEST);
        }
        LOG.info("MetadataXML.xml uploaded: " + metadataXML.getOriginalFilename() + ", size=" + metadataXML.getSize());
        LOG.info("Files.xml uploaded: " + filesXML.getOriginalFilename() + ", size=" + filesXML.getSize());

        LaunchBoxParser parser = new LaunchBoxParser();
        LBoxDatafile datafile = parser.parse(metadataXML.getInputStream(), filesXML.getInputStream());
        datafile.setVersion(version);
        LOG.info("Datafile: " + datafile);
        LBoxDatafileDTO result = service.save(datafile);
        LOG.info("Result: " + result);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/regions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List regions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = RegionCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxRegionDTO>> findRegions(
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        return ResponseEntity.ok(service.findRegions(page, pageSize));
    }

    @RequestMapping(value = "/{version:.+}/genres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List genres by LaunchBox version")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = GenreCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxGenreDTO>> findGenresByVersion(@PathVariable(value = "version", required = true) String version,
                                                                             @RequestParam(value = "page", defaultValue = "0", required = true) int page,
                                                                             @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        return ResponseEntity.ok(service.findGenresByVersion(version, page, pageSize));
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List genres")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = GenreCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LBoxGenreDTO>> findGenres(
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize) {
        return ResponseEntity.ok(service.findGenres(page, pageSize));
    }

    private class CollectionResult extends PaginatedResult<LBoxDatafileDTO> {
    }

    private class GameCollectionResult extends PaginatedResult<LBoxGame> {
    }

    private class PlatformCollectionResult extends PaginatedResult<LBoxPlatformDTO> {
    }

    private class RegionCollectionResult extends PaginatedResult<LBoxRegionDTO> {
    }

    private class GenreCollectionResult extends PaginatedResult<LBoxGenreDTO> {
    }
}
