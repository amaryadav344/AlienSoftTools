package com.webstudio.hub.controllers;

import com.business.utils.models.Entity.IFile;
import com.business.utils.models.Entity.IObjectMethod;
import com.business.utils.models.Entity.ISymbol;
import com.webstudio.hub.common.Constants;
import com.webstudio.hub.common.SymbolProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constants.CodeSymbolRequestMapping.CODE)
public class CodeSymbolController {
    private SymbolProvider symbolProvider;

    @RequestMapping(value = Constants.CodeSymbolRequestMapping.GET_SYMBOLS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ISymbol[]> getSymbols(@RequestBody IFile file, @RequestParam(name = "query", required = false) String query, @RequestParam(name = "type", required = false) int SymbolType) throws IOException {
        List<ISymbol> symbols = new ArrayList<>();
        if (SymbolType == Constants.SymbolTypes.TYPE_OBJECT) {
            symbols.addAll(symbolProvider.GetObjectSymbols(file, query));
        } else if (SymbolType == Constants.SymbolTypes.TYPE_COLLECTION) {
            symbols.addAll(symbolProvider.GetObjectSymbols(file, query));
            symbols.addAll(symbolProvider.GetCollectionSymbols(file, query));
        } else if (SymbolType == Constants.SymbolTypes.TYPE_VARIABLE) {
            symbols.addAll(symbolProvider.GetObjectSymbols(file, query));
            symbols.addAll(symbolProvider.GetVariableSymbols(file, query));

        }
        return new ResponseEntity<>(symbols.toArray(new ISymbol[0]), HttpStatus.OK);
    }

    @RequestMapping(value = Constants.CodeSymbolRequestMapping.GET_OBJECT_METHODS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IObjectMethod[]> getSymbols(@RequestBody IFile file, @RequestParam(name = "query", required = false) String query) throws IOException {
        List<IObjectMethod> symbols = symbolProvider.ListObjectMethods(file, query);
        return new ResponseEntity<>(symbols.toArray(new IObjectMethod[0]), HttpStatus.OK);
    }

    @Autowired
    public void setSymbolProvider(SymbolProvider symbolProvider) {
        this.symbolProvider = symbolProvider;
    }
}
