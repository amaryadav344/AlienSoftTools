package com.webstudio.hub.common;

import com.business.utils.models.Entity.IObjectMethod;
import com.business.utils.models.Entity.IObjectParameter;
import com.business.utils.models.Entity.ISymbol;
import spoon.JarLauncher;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtReference;
import spoon.reflect.reference.CtTypeReference;

import java.util.ArrayList;
import java.util.Date;
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

    public List<ISymbol> getObjectSymbols(String FullQualifiedName, String Query) {
        CtTypeReference<?> LastType = factory.Type().get(FullQualifiedName).getReference();
        String LastSymbol = "";
        String[] Symbols = Query.split("\\.");
        for (String symbol : Symbols) {

            if (LastType.getAllFields().stream().anyMatch(ctFieldReference -> HasBaseSuperClass(ctFieldReference.getType())
                    && ctFieldReference.getSimpleName().equals(symbol))) {
                LastType = LastType.getAllFields().stream().filter(ctFieldReference -> HasBaseSuperClass(ctFieldReference.getType())
                        && ctFieldReference.getSimpleName().equals(symbol)).findFirst().get().getType();
            } else {
                LastSymbol = symbol;
            }
        }
        String finalLastSymbol = LastSymbol.toLowerCase();

        return LastType.getAllFields()
                .stream().filter(ctFieldReference -> ctFieldReference.getSimpleName().toLowerCase().contains(finalLastSymbol) && HasBaseSuperClass(ctFieldReference.getType())).map(ctFieldReference -> {
                    ISymbol symbol = new ISymbol();
                    symbol.setName(ctFieldReference.getSimpleName());
                    symbol.setObjectType(ctFieldReference.getType().getSimpleName());
                    symbol.setType(Constants.SymbolTypes.TYPE_OBJECT);
                    return symbol;
                }).collect(Collectors.toList());
    }

    public List<ISymbol> getCollectionSymbols(String FullQualifiedName, String Query) {
        CtTypeReference<?> LastType = factory.Type().get(FullQualifiedName).getReference();
        String LastSymbol = "";
        String[] Symbols = Query.split("\\.");
        for (String symbol : Symbols) {
            if (LastType.getAllFields().stream().anyMatch(ctFieldReference ->
                    ctFieldReference.getType().getActualTypeArguments().size() != 0 &&
                            HasBaseSuperClass((ctFieldReference.getType().getActualTypeArguments().get(0)))
                            && ctFieldReference.getSimpleName().equals(symbol)
                            && ctFieldReference.getType().getQualifiedName().equals(List.class.getCanonicalName()))) {
                LastType = LastType.getAllFields().stream()

                        .filter(ctFieldReference ->
                                ctFieldReference.getType().getActualTypeArguments().size() != 0
                                        && HasBaseSuperClass((ctFieldReference.getType().getActualTypeArguments().get(0)))
                                        && ctFieldReference.getType().getQualifiedName().equals(List.class.getCanonicalName())
                                        && ctFieldReference.getSimpleName().equals(symbol)).findFirst().get().getType();
            } else {
                LastSymbol = symbol;
            }
        }
        String finalLastSymbol = LastSymbol.toLowerCase();

        return LastType.getAllFields()
                .stream().filter(ctFieldReference ->
                        ctFieldReference.getType().getActualTypeArguments().size() != 0
                                && ctFieldReference.getSimpleName().toLowerCase().contains(finalLastSymbol)
                                && HasBaseSuperClass((ctFieldReference.getType().getActualTypeArguments().get(0)))
                                && ctFieldReference.getType().getQualifiedName().equals(List.class.getCanonicalName())).map(ctFieldReference -> {
                    ISymbol symbol = new ISymbol();
                    symbol.setName(ctFieldReference.getSimpleName());
                    symbol.setObjectType(ctFieldReference.getType().getActualTypeArguments().get(0).getSimpleName());
                    symbol.setType(Constants.SymbolTypes.TYPE_COLLECTION);
                    return symbol;
                }).collect(Collectors.toList());
    }

    public List<ISymbol> getVariableSymbols(String FullQualifiedName, String Query) {
        CtTypeReference<?> LastType = factory.Type().get(FullQualifiedName).getReference();
        String LastSymbol = "";
        String[] Symbols = Query.split("\\.");

        for (String symbol : Symbols) {
            if (LastType.getAllFields().stream().anyMatch(ctFieldReference ->
                    ctFieldReference.getType().getActualTypeArguments().size() != 0 &&
                            HasBaseSuperClass((ctFieldReference.getType().getActualTypeArguments().get(0)))
                            && ctFieldReference.getSimpleName().equals(symbol)
                            && ctFieldReference.getType().getQualifiedName().equals(List.class.getCanonicalName()))) {
                LastType = LastType.getAllFields().stream()

                        .filter(ctFieldReference ->
                                ctFieldReference.getType().getActualTypeArguments().size() != 0
                                        && HasBaseSuperClass((ctFieldReference.getType().getActualTypeArguments().get(0)))
                                        && ctFieldReference.getType().getQualifiedName().equals(List.class.getCanonicalName())
                                        && ctFieldReference.getSimpleName().equals(symbol)).findFirst().get().getType();
            } else {
                LastSymbol = symbol;
            }
        }

        String finalLastSymbol = LastSymbol.toLowerCase();


        return LastType.getAllFields()
                .stream().filter(ctFieldReference ->
                        ctFieldReference.getType().getActualTypeArguments().size() != 0
                                && ctFieldReference.getSimpleName().toLowerCase().contains(finalLastSymbol)
                                && HasBaseSuperClass((ctFieldReference.getType().getActualTypeArguments().get(0)))
                                && (ctFieldReference.getType().getQualifiedName().equals(Date.class.getCanonicalName()))
                                || (ctFieldReference.getType().getQualifiedName().equals(String.class.getCanonicalName()))
                                || (ctFieldReference.getType().getQualifiedName().equals(Integer.class.getCanonicalName()))).map(ctFieldReference -> {
                    ISymbol symbol = new ISymbol();
                    symbol.setName(ctFieldReference.getSimpleName());
                    symbol.setObjectType(ctFieldReference.getType().getSimpleName());
                    symbol.setType(Constants.SymbolTypes.TYPE_VARIBLE);
                    return symbol;
                }).collect(Collectors.toList());
    }

    public List<IObjectMethod> getAllMethods(String FullQualifiedName, String Query) {
        CtTypeReference<?> Type = factory.Type().get(FullQualifiedName).getReference();
        return Type.getDeclaredExecutables().stream().filter(ctExecutableReference -> !ctExecutableReference.isConstructor()).map(ctExecutableReference -> {
            CtExecutable executable = ctExecutableReference.getDeclaration();
            IObjectMethod method = new IObjectMethod();
            method.setName(executable.getSimpleName());
            method.setReturnType(executable.getType().getSimpleName());
            List<IObjectParameter> objectParameters = new ArrayList<>();
            List<CtParameter<?>> parameters = executable.getParameters();
            for (CtParameter parameter : parameters) {
                IObjectParameter iObjectParameter = new IObjectParameter();
                iObjectParameter.setName(parameter.getSimpleName());
                iObjectParameter.setDataType(parameter.getType().getSimpleName().toString());
                objectParameters.add(iObjectParameter);
            }
            method.setObjectParameters(objectParameters.toArray(new IObjectParameter[objectParameters.size()]));
            return method;
        }).collect(Collectors.toList());
    }

    public boolean HasBaseSuperClass(CtTypeReference ctTypeReference) {
        CtTypeReference superClass = ctTypeReference.getSuperclass();
        if (superClass == null) {
            return false;
        }
        if (superClass.getSimpleName().equals("BusBase")) {
            return true;
        }

        return HasBaseSuperClass(superClass);
    }
}
