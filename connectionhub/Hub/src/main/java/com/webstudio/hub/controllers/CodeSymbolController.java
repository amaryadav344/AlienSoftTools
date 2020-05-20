package com.webstudio.hub.controllers;

import com.business.utils.models.Entity.IAttribute;
import com.business.utils.models.Entity.IFile;
import com.webstudio.hub.common.Constants;
import com.webstudio.hub.common.SymbolProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(Constants.CodeSymbolRequestMapping.CODE)
public class CodeSymbolController {
    private SymbolProvider symbolProvider;

    @RequestMapping(value = Constants.CodeSymbolRequestMapping.GET_SYMBOLS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IAttribute[]> getSymbols(@RequestBody IFile file, @RequestParam(name = "query", required = false) String query, @RequestParam(name = "type", required = false) String SymbolType) throws IOException {
        return new ResponseEntity<>(symbolProvider.GetSymbols(file, query, SymbolType).toArray(new IAttribute[0]), HttpStatus.OK);
    }

    @RequestMapping(value = Constants.CodeSymbolRequestMapping.GET_SERVICES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getServices() {
        return new ResponseEntity<>(symbolProvider.GetAllServices().toArray(new String[0]), HttpStatus.OK);
    }

    @Autowired
    public void setSymbolProvider(SymbolProvider symbolProvider) {
        this.symbolProvider = symbolProvider;
    }

}

