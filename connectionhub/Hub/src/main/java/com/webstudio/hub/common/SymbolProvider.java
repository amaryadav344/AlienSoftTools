package com.webstudio.hub.common;

import com.business.utils.FileHelper;
import com.business.utils.models.Entity.*;
import com.business.utils.models.IXMLBase;
import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spoon.JarLauncher;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtReference;
import spoon.reflect.reference.CtTypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SymbolProvider {
    @Autowired
    XMLStore xmlStore;
    @Autowired
    @Qualifier("DefaultBranch")
    Branch DefaultBranch;
    private Factory factory;
    private HashMap<String, List<CtFieldReference<?>>> SymbolStore;

    private SymbolProvider() {
        this.SymbolStore = new HashMap<>();
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

    public List<ISymbol> GetObjectSymbols(IFile file, String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(file.getPath());
        IEntity value = (IEntity) xmlStore.getXMLObjectFromString(xmlString);
        String ClassPath = GetFullyQualifiedModelName(file, value.getModelName());
        List<ISymbol> symbols = getObjectSymbols(ClassPath, query);
        for (ISymbol symbol : symbols) {
            String ModelName = xmlStore.getEntityNameByModelName(symbol.getObjectType());
            symbol.setEntityName(ModelName);
        }
        return symbols;
    }

    public List<ISymbol> GetCollectionSymbols(IFile file, String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(file.getPath());
        IEntity value = (IEntity) xmlStore.getXMLObjectFromString(xmlString);
        String ClassPath = GetFullyQualifiedModelName(file, value.getModelName());
        List<ISymbol> symbols = getCollectionSymbols(ClassPath, query);
        for (ISymbol symbol : symbols) {
            String ModelName = xmlStore.getEntityNameByModelName(symbol.getObjectType());
            symbol.setEntityName(ModelName);
        }
        return symbols;
    }

    public List<ISymbol> GetVariableSymbols(IFile file, String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(file.getPath());
        IEntity value = (IEntity) xmlStore.getXMLObjectFromString(xmlString);
        String ClassPath = GetFullyQualifiedModelName(file, value.getModelName());
        List<ISymbol> symbols = getVariableSymbols(ClassPath, query);
        return symbols;
    }

    public String GetFullyQualifiedModelName(IFile file, String modelName) {
        String RelativeFilePath = file.getPath().replace(DefaultBranch.getXMLPath(), "");
        String ModelRelativePath = RelativeFilePath.replace(file.getName(), modelName);
        return DefaultBranch.getPackageName() + ModelRelativePath.replace("\\", ".");

    }

    public List<IObjectMethod> ListObjectMethods(IFile file, String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(file.getPath());
        IEntity value = (IEntity) xmlStore.getXMLObjectFromString(xmlString);
        String ClassPath = GetFullyQualifiedModelName(file, value.getModelName());
        List<IObjectMethod> symbols = getAllMethods(ClassPath, query);
        return symbols;
    }
    /*public IXMLBase GetXml(IFile file) {
        String name = "";
        if (file.getType() == 0) {
            name = file.getName().replace(".ent.xml", "");
        } else if (file.getType() == 1) {
            name = file.getName().replace(".form.xml", "");
        }
        return xmlStore.GetXml(name);
    }*/

}