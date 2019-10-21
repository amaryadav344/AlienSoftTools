package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IValidation {
    @JacksonXmlElementWrapper(localName = "rules")
    @JacksonXmlProperty(localName = "rule")
    IVRule[] rules;
    @JacksonXmlElementWrapper(localName = "initialLoads")
    @JacksonXmlProperty(localName = "initialLoad")
    IRule[] initialLoad;
    @JacksonXmlElementWrapper(localName = "softErrors")
    @JacksonXmlProperty(localName = "softError")
    IRule[] softErrors;
    @JacksonXmlElementWrapper(localName = "hardErrors")
    @JacksonXmlProperty(localName = "hardError")
    IRule[] hardErrors;
    @JacksonXmlElementWrapper(localName = "updateRules")
    @JacksonXmlProperty(localName = "updateRule")
    IRule[] updateRules;
    @JacksonXmlElementWrapper(localName = "deleteRules")
    @JacksonXmlProperty(localName = "deleteRule")
    IRule[] deleteRules;
    @JacksonXmlElementWrapper(localName = "groupRules")
    @JacksonXmlProperty(localName = "groupRule")
    IGroup[] groupRules;

    public IValidation() {
    }

    public IValidation(IVRule[] rules, IRule[] initialLoad, IRule[] softErrors, IRule[] hardErrors, IRule[] updateRules, IRule[] deleteRules, IGroup[] groupRules) {
        this.rules = rules;
        this.initialLoad = initialLoad;
        this.softErrors = softErrors;
        this.hardErrors = hardErrors;
        this.updateRules = updateRules;
        this.deleteRules = deleteRules;
        this.groupRules = groupRules;
    }

    public IVRule[] getRules() {
        return rules;
    }

    public void setRules(IVRule[] rules) {
        this.rules = rules;
    }

    public IRule[] getSoftErrors() {
        return softErrors;
    }

    public void setSoftErrors(IRule[] softErrors) {
        this.softErrors = softErrors;
    }

    public IRule[] getInitialLoad() {
        return initialLoad;
    }

    public void setInitialLoad(IRule[] initialLoad) {
        this.initialLoad = initialLoad;
    }

    public IRule[] getHardErrors() {
        return hardErrors;
    }

    public void setHardErrors(IRule[] hardErrors) {
        this.hardErrors = hardErrors;
    }

    public IRule[] getUpdateRules() {
        return updateRules;
    }

    public void setUpdateRules(IRule[] updateRules) {
        this.updateRules = updateRules;
    }

    public IRule[] getDeleteRules() {
        return deleteRules;
    }

    public void setDeleteRules(IRule[] deleteRules) {
        this.deleteRules = deleteRules;
    }

    public IGroup[] getGroupRules() {
        return groupRules;
    }

    public void setGroupRules(IGroup[] groupRules) {
        this.groupRules = groupRules;
    }
}
