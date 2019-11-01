package com.webstudio.connectionhub.common;

import spoon.JarLauncher;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtReference;
import spoon.reflect.reference.CtTypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class SymbolProvider {
    private static SymbolProvider mSymbolProvider = null;
    private Factory factory;
    private HashMap<String, List<CtFieldReference<?>>> SymbolStore;

    private SymbolProvider() {
        this.SymbolStore = new HashMap<>();
    }

    public static SymbolProvider getInstance() {
        if (mSymbolProvider == null) {
            mSymbolProvider = new SymbolProvider();
        }
        return mSymbolProvider;
    }

    public void LoadJar(String JarFilePath) {
        JarLauncher launcher = new JarLauncher(JarFilePath);
        launcher.buildModel();
        this.factory = launcher.getFactory();
    }

    private List<CtFieldReference<?>> getSymbols(CtTypeReference<?> type) {
        return (List<CtFieldReference<?>>) type.getAllFields();
    }

    public List<String> getMatchingSymbols(String FullQualifiedName, String Query) {
        CtTypeReference<?> LastType = factory.Type().get(FullQualifiedName).getReference();
        String[] Symbols = Query.split("\\.");
        for (String symbol : Symbols) {
            List<CtFieldReference<?>> fieldReferences;
            if (SymbolStore.containsKey(LastType.getQualifiedName())) {
                fieldReferences = SymbolStore.get(LastType.getQualifiedName());
            } else {
                fieldReferences = getSymbols(LastType);
                SymbolStore.put(LastType.getQualifiedName(), fieldReferences);
            }
            if (fieldReferences.stream().anyMatch(ctField -> ctField.getSimpleName().equals(symbol))) {
                LastType = fieldReferences.stream().filter(ctField -> ctField.getSimpleName().equals(symbol)).findFirst().get().getType();
            }
        }
        List<CtFieldReference<?>> fieldReferences = SymbolStore.containsKey(LastType.getQualifiedName()) ?
                SymbolStore.get(LastType.getQualifiedName()) : getSymbols(LastType);
        return fieldReferences.stream().map(CtReference::getSimpleName).collect(Collectors.toList());
    }
}
