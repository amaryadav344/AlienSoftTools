package com.webstudio.hub.common;

import com.business.utils.HelperFunctions;
import com.business.utils.models.Entity.IAttribute;
import com.business.utils.models.Entity.IEntity;
import com.business.utils.models.Entity.IFile;
import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spoon.IncrementalLauncher;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.factory.Factory;
import spoon.reflect.path.CtRole;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.AnnotationFilter;
import spoon.support.reflect.declaration.CtClassImpl;

import javax.swing.text.Element;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SymbolProvider {
    private XMLStore xmlStore;
    private Branch DefaultBranch;
    private Factory factory;

    private SymbolProvider() {
    }

    public void LoadJar(String PathToSources) {
        final File cache = new File(HelperFunctions.getExecutableHomePath(SymbolProvider.class, "cache"));
        Set<File> inputResources = Collections.singleton(new File(PathToSources));
        Set<String> sourceClasspath = Collections.emptySet();
        Launcher launcher = new IncrementalLauncher(inputResources, sourceClasspath, cache);
        launcher.buildModel();
        this.factory = launcher.getFactory();
    }

    public List<IAttribute> GetSymbols(IFile file, String query, String type) {
        List<IAttribute> attributes = new ArrayList<>();
        if (query == null) {
            query = "";
        }
        IEntity entity = (IEntity) xmlStore.GetXml(file);
        String ClassPath = HelperFunctions.GetFullyQualifiedModelName(file, entity.getBusinessObject(), DefaultBranch.getXMLPath(), DefaultBranch.getPackageName());
        String[] props = query.split("\\.");
        CtClass ctClass = factory.Class().get(ClassPath);
        for (String prop : props) {
            Optional<CtFieldReference<?>> optionalCtFieldReference = ctClass.getAllFields().stream().filter(ctFieldReference -> ctFieldReference.getSimpleName().equals(prop))
                    .findFirst();
            if (optionalCtFieldReference.isPresent()) {
                if (!optionalCtFieldReference.get().getType().getQualifiedName().equals(List.class.getCanonicalName())) {
                    ctClass = factory.Class().get(optionalCtFieldReference.get().getType().getQualifiedName());
                    query = query.replace(prop, "");
                    if (query.startsWith(".")) {
                        query = query.substring(1);
                    }
                }
            }
        }
        if (type.isEmpty()) {
            attributes.addAll(GetPropertySymbols(ctClass, query));
            attributes.addAll(GetObjectSymbols(ctClass, query));
            attributes.addAll(GetCollectionSymbols(ctClass, query));
        }
        return attributes;
    }

    public List<IAttribute> GetObjectSymbols(CtClass ctClass, String Query) {
        return ctClass.getAllFields().stream().filter(
                ctFieldReference ->
                        HasBaseSuperClass(ctFieldReference.getType()) &&// Object
                                ctFieldReference.getSimpleName().toLowerCase().startsWith(Query.toLowerCase()))
                .map(ctFieldReference -> {
                    IAttribute attribute = new IAttribute();
                    attribute.setType(Constants.AttributeTypes.OBJECT);
                    attribute.setObjectField(ctFieldReference.getSimpleName());
                    String entity = xmlStore.getEntityNameByModelName(ctFieldReference.getType().getSimpleName());
                    attribute.setEntity(entity);
                    attribute.setPrimaryKey(false);
                    return attribute;
                }).collect(Collectors.toList());

    }

    public List<IAttribute> GetCollectionSymbols(CtClass ctClass, String Query) {
        return ctClass.getAllFields().stream().filter(
                ctFieldReference ->
                        ctFieldReference.getType().getQualifiedName().equals(List.class.getCanonicalName()) &&
                                HasBaseSuperClass(ctFieldReference.getType().getActualTypeArguments().get(0)) &&
                                ctFieldReference.getSimpleName().toLowerCase().startsWith(Query.toLowerCase()))
                .map(ctFieldReference -> {
                    IAttribute attribute = new IAttribute();
                    attribute.setType(Constants.AttributeTypes.COLLECTION);
                    attribute.setObjectField(ctFieldReference.getSimpleName());
                    String entity = xmlStore.getEntityNameByModelName(ctFieldReference.getType().getActualTypeArguments().get(0).getSimpleName());
                    attribute.setEntity(entity);
                    attribute.setPrimaryKey(false);
                    return attribute;
                }).collect(Collectors.toList());

    }

    public List<IAttribute> GetPropertySymbols(CtClass ctClass, String Query) {
        return ctClass.getAllFields().stream().filter(
                ctFieldReference ->
                        IsPrimitive(ctFieldReference.getType()) &&
                                ctFieldReference.getSimpleName().toLowerCase().startsWith(Query.toLowerCase()))
                .map(ctFieldReference -> {
                    IAttribute attribute = new IAttribute();
                    attribute.setType(Constants.AttributeTypes.PROPERTY);
                    attribute.setDataType(GetDataType(ctFieldReference.getType()));
                    attribute.setObjectField(ctFieldReference.getSimpleName());
                    attribute.setPrimaryKey(false);
                    return attribute;
                }).collect(Collectors.toList());

    }

    private String GetDataType(CtTypeReference<?> type) {
        String DataType = "";
        if (type.getQualifiedName().equals(String.class.getCanonicalName())) {
            DataType = Constants.DataTypes.STRING_TYPE;

        } else if (type.getQualifiedName().equals(Date.class.getCanonicalName())) {
            DataType = Constants.DataTypes.DATETIME_TYPE;

        } else if (type.getQualifiedName().equals(Integer.class.getCanonicalName())) {
            DataType = Constants.DataTypes.INTEGER_TYPE;

        } else if (type.getQualifiedName().equals(Boolean.class.getCanonicalName())) {
            DataType = Constants.DataTypes.BOOLEAN_TYPE;

        }
        return DataType;
    }

    private boolean IsPrimitive(CtTypeReference<?> type) {
        return (type.getQualifiedName().equals(Date.class.getCanonicalName()))
                || (type.getQualifiedName().equals(String.class.getCanonicalName()))
                || (type.getQualifiedName().equals(Integer.class.getCanonicalName()) ||
                (type.getQualifiedName().equals(Boolean.class.getCanonicalName())));
    }

    public boolean HasBaseSuperClass(CtTypeReference ctTypeReference) {
        CtTypeReference superClass = ctTypeReference.getSuperclass();
        if (superClass == null) {
            return false;
        }
        if (superClass.getQualifiedName().equals(DefaultBranch.getBusinessObjectBase())) {
            return true;
        }

        return HasBaseSuperClass(superClass);
    }

    public List<String> GetAllServices() {
        return factory.getModel().getElements(new AnnotationFilter<CtClassImpl>(Service.class))
                .stream().map(CtClassImpl::getQualifiedName).collect(Collectors.toList());
    }

    @Autowired
    @Qualifier(Constants.ApplicationBeans.DEFAULT_BRANCH)
    public void setDefaultBranch(Branch defaultBranch) {
        DefaultBranch = defaultBranch;
    }

    @Autowired
    public void setXmlStore(XMLStore xmlStore) {
        this.xmlStore = xmlStore;
    }

}
