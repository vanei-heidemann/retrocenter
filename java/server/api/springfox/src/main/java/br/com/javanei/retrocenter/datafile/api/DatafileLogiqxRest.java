package br.com.javanei.retrocenter.datafile.api;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.datafile.logiqx.service.LogiqxDatafileDTO;
import br.com.javanei.retrocenter.datafile.logiqx.service.LogiqxService;
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
@Api(tags = {"Datafiles service - Logiqx"})
public class DatafileLogiqxRest {
    @Autowired
    private LogiqxService logiqxService;

    @RequestMapping(value = "/logiqx", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list logiqx datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = CollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<LogiqxDatafileDTO>> findLogiqx(
            @ApiParam(name = "page", required = false) @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize", defaultValue = "100", required = true) @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
            @ApiParam(name = "name", required = false) @RequestParam(name = "name", required = false) String name) {
        return ResponseEntity.ok(logiqxService.find(name, page, pageSize));
    }

    @RequestMapping(value = "/logiqx/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return Logiqx data file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = LogiqxDatafileDTO.class),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<LogiqxDatafileDTO> findByLogiqxId(@PathVariable Long id) {
        LogiqxDatafileDTO vo = logiqxService.findByIdFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    private class CollectionResult extends PaginatedResult<LogiqxDatafileDTO> {
    }

    @RequestMapping(value = "/logiqx/{id}/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Return the Logiqx xml file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<String> downloadLogiqxById(@PathVariable Long id) {
        LogiqxDatafileDTO vo = logiqxService.findByIdFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo.toString());
        }
        return ResponseEntity.notFound().build();
    }
}
