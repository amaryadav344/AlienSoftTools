package com.webstudio.hub.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.webstudio.hub.models.Branch;
import com.webstudio.hub.models.Database;
import org.springframework.beans.factory.DisposableBean;

import java.util.List;
import java.util.logging.Logger;

@JacksonXmlRootElement(localName = "HubConfig")
public class HubConfig implements DisposableBean {
    private static Logger logger = Logger.getLogger(HubConfig.class.getName());
    private com.webstudio.hub.models.Database Database;
    private List<Branch> branches;

    @JacksonXmlElementWrapper(localName = "Database")
    public Database getDatabaseConnection() {
        return Database;
    }

    public void setDatabaseConnection(Database databaseConnection) {
        Database = databaseConnection;
    }

    @JacksonXmlElementWrapper(localName = "Branches")
    @JacksonXmlProperty(localName = "Branch")
    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public void destroy() {
        logger.info("stopping the application please note it down");
    }
}
