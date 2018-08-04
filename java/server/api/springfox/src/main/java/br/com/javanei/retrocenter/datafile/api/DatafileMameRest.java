package br.com.javanei.retrocenter.datafile.api;

import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.datafile.mame.MameMachine;
import br.com.javanei.retrocenter.datafile.mame.service.MameDTO;
import br.com.javanei.retrocenter.datafile.mame.service.MameService;
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
@Api(tags = {"Datafiles service - MAME"})
public class DatafileMameRest {
    @Autowired
    private MameService mameService;

    @RequestMapping(value = "/mame", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list MAME datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = MameCollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<MameDTO>> findMame(
            @ApiParam(name = "page", required = false) @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize", defaultValue = "100", required = true) @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize) {
        return ResponseEntity.ok(mameService.find(page, pageSize));
    }

    @RequestMapping(value = "/mame/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return MAME data file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = MameDTO.class),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<MameDTO> findById(@PathVariable Long id) {
        MameDTO vo = mameService.findByIdFull(id, false);
        if (vo != null) {
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/mame/{id}/machines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return MAME Machines")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = MameMachineCollectionResult.class),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<PaginatedResult<MameMachine>> findMachinesByMameId(@PathVariable Long id,
                                                                             @ApiParam(name = "page", required = false) @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                             @ApiParam(name = "pageSize", defaultValue = "100", required = true) @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
                                                                             @ApiParam(name = "fullDefails", required = true) @RequestParam(name = "fullDefails", defaultValue = "false") Boolean fullDefails) {
        PaginatedResult<MameMachine> result = mameService.findMachinesByMameId(id, page, pageSize, fullDefails);
        return ResponseEntity.ok(result);
    }

    private class MameCollectionResult extends PaginatedResult<MameDTO> {
    }

    @RequestMapping(value = "/mame/{id}/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Return the MAME xml file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<String> download(@PathVariable Long id) {
        MameDTO vo = mameService.findByIdFull(id, true);
        if (vo != null) {
            return ResponseEntity.ok(vo.toString());
        }
        return ResponseEntity.notFound().build();
    }

    private class MameMachineCollectionResult extends PaginatedResult<MameMachine> {
    }
}
