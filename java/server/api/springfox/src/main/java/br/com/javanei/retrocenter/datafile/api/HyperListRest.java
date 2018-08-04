package br.com.javanei.retrocenter.datafile.api;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.datafile.hyperlist.service.HyperListMenuDTO;
import br.com.javanei.retrocenter.datafile.hyperlist.service.HyperListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/datafiles")
@CrossOrigin(value = "*", allowedHeaders = "*")
@Api(tags = {"Datafiles service - HyperList"})
public class HyperListRest {
    @Autowired
    private HyperListService service;

    @RequestMapping(value = "/hyperlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list HyperList datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = CollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<HyperListMenuDTO>> find(
            @ApiParam(name = "page", required = false) @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize", defaultValue = "100", required = true) @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
            @ApiParam(name = "name", required = false) @RequestParam(name = "name", required = false) String name) {
        return ResponseEntity.ok(service.find(name, page, pageSize));
    }

    @RequestMapping(value = "/hyperlist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return HyperList data file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = HyperListMenuDTO.class),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<HyperListMenuDTO> findById(@PathVariable Long id) {
        HyperListMenuDTO vo = service.findByIdFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/hyperlist/{id}/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Return the Hyperlist xml file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<String> downloadById(@PathVariable Long id) {
        HyperListMenuDTO vo = service.findByIdFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo.toString());
        }
        return ResponseEntity.notFound().build();
    }

    private class CollectionResult extends PaginatedResult<HyperListMenuDTO> {
    }
}
