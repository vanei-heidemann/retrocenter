package br.com.javanei.retrocenter.datafile.api;

import br.com.javanei.retrocenter.ErrorResponse;
import br.com.javanei.retrocenter.common.DatafileCatalogEnum;
import br.com.javanei.retrocenter.common.PaginatedResult;
import br.com.javanei.retrocenter.datafile.common.Datafile;
import br.com.javanei.retrocenter.datafile.common.DatafileObject;
import br.com.javanei.retrocenter.datafile.common.Parser;
import br.com.javanei.retrocenter.datafile.parser.DatafileParser;
import br.com.javanei.retrocenter.datafile.service.DatafileDTO;
import br.com.javanei.retrocenter.datafile.service.DatafileService;
import br.com.javanei.retrocenter.datafile.service.RetrocenterDatafileService;
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

@RestController
@RequestMapping(value = "/api/datafiles")
@CrossOrigin(value = "*", allowedHeaders = "*")
@Api(tags = {"Datafiles service"})
public class DatafileRest {
    private static final Logger LOG = LoggerFactory.getLogger(DatafileRest.class);

    @Autowired
    public DatafileService service;
    @Autowired
    RetrocenterDatafileService retrocenterDatafileService;

    private class CollectionResult extends PaginatedResult<DatafileDTO> {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a list of datafiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok", response = CollectionResult.class)
    })
    public ResponseEntity<PaginatedResult<DatafileDTO>> find(
            @ApiParam(name = "page", required = false) @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize", defaultValue = "100", required = true) @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
            @ApiParam(name = "name", required = false) @RequestParam(name = "name", required = false) String name,
            @ApiParam(name = "catalog", required = false) @RequestParam(name = "catalog", required = false) DatafileCatalogEnum catalog) {
        return ResponseEntity.ok(service.find(name, catalog, page, pageSize));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return the xml file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<DatafileDTO> findById(@PathVariable Long id) {
        DatafileDTO vo = service.findByIDFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Return the xml file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Datafile not found")
    })
    public ResponseEntity<String> downloadById(@PathVariable Long id) {
        DatafileDTO vo = service.findByIDFull(id);
        if (vo != null) {
            return ResponseEntity.ok(vo.toFile());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Parse and save datafile uploaded")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok"),
            @ApiResponse(code = 400, message = "File is empty")
    })
    public ResponseEntity<Datafile> upload(@RequestParam("file") MultipartFile uploadfile) throws Exception {
        if (uploadfile.isEmpty()) {
            LOG.info("File is empty");
            return new ResponseEntity(new ErrorResponse("File is empty"), HttpStatus.BAD_REQUEST);
        }
        LOG.info("File uploaded: " + uploadfile.getOriginalFilename() + ", size=" + uploadfile.getSize());

        Parser parser = new DatafileParser();
        LOG.info("Parser: " + parser.getClass());
        DatafileObject datafile = parser.parse(uploadfile.getInputStream());
        LOG.info("Datafile: " + datafile.getClass());
        Datafile r = service.create(datafile);
        LOG.info("Result: " + r.getClass());
        return new ResponseEntity(r, HttpStatus.CREATED);
    }
}
