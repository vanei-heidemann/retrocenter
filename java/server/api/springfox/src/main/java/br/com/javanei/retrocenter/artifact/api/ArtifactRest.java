package br.com.javanei.retrocenter.artifact.api;

import br.com.javanei.retrocenter.ErrorResponse;
import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.platform.Artifact;
import br.com.javanei.retrocenter.platform.service.ArtifactDTO;
import br.com.javanei.retrocenter.platform.service.ArtifactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/artifacts")
@Api(tags = {"Artifacts service"}, produces = "application/json")
public class ArtifactRest {
    @Autowired
    private ArtifactService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find a artifact by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = ArtifactDTO.class),
            @ApiResponse(code = 400, message = "Artifact Not Found")
    })
    public ResponseEntity<ArtifactDTO> findByID(@PathVariable("id") Long id) throws Exception {
        ArtifactDTO vo = service.findArtifactById(id);
        if (vo != null) {
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list of artifacts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = CollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<ArtifactDTO>> find(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = true) int page,
            @RequestParam(value = "pageSize", defaultValue = "100", required = true) int pageSize)
            throws Exception {
        PaginatedResult<ArtifactDTO> result = service.findArtifacts(null, code, name, page, pageSize);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a new artifact")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = ArtifactDTO.class),
            @ApiResponse(code = 400, message = "Artifact already exists")
    })
    public ResponseEntity<ArtifactDTO> create(@RequestBody Artifact artifact) throws Exception {
        if (service.findArtifactByCode(artifact.getCode()) != null) {
            return new ResponseEntity(new ErrorResponse("Artifact already exists"), HttpStatus.BAD_REQUEST);
        }
        ArtifactDTO vo = service.createArtifact(artifact);
        return new ResponseEntity(vo, HttpStatus.CREATED);
    }

    private class CollectionResult extends PaginatedResult<ArtifactDTO> {
    }
}
