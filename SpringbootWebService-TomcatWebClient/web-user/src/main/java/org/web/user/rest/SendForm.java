package org.web.user.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web.user.service.OsobaService;
import org.web.user.vm.OsobaVM;

import java.io.IOException;

@RestController
@RequestMapping(value = "/form")
public class SendForm {

    @Autowired
    private OsobaService osobaService;

    @PostMapping(value = "/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OsobaVM> saveOsoba(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("vm") String osobaString) throws IOException {
        OsobaVM osobaVM;

        ObjectMapper objectMapper = new ObjectMapper();
        osobaVM = objectMapper.readValue(osobaString, OsobaVM.class);

        osobaVM.setFile(file);
        osobaVM = osobaService.save(osobaVM);

        return new ResponseEntity<>(osobaVM, HttpStatus.OK);
    }

    @PutMapping(value = "/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> updateOsoba(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("vm") String osobaString) throws IOException {
        OsobaVM osobaVM;

        ObjectMapper objectMapper = new ObjectMapper();
        osobaVM = objectMapper.readValue(osobaString, OsobaVM.class);

        osobaVM.setFile(file);
        HttpStatus httpStatus = osobaService.update(osobaVM);

        return httpStatus == HttpStatus.OK ? ResponseEntity.ok().build() : new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOsoba(@PathVariable("id") Long id) {
        HttpStatus httpStatus = osobaService.delete(id);

        return httpStatus == HttpStatus.OK ? ResponseEntity.ok().build() : new ResponseEntity<>(httpStatus);
    }

}
