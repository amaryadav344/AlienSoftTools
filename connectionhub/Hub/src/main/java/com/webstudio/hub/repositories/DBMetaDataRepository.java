package com.webstudio.hub.repositories;

import com.business.utils.models.Entity.IAttribute;
import com.google.common.base.CaseFormat;
import com.webstudio.hub.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DBMetaDataRepository {
    @Autowired
    @Qualifier("BusinessDB")
    JdbcTemplate jdbcTemplateBusinessDB;

    public List<String> getAllTables(String query) {
        List<String> tables;
        if (query.isEmpty()) {
            tables = jdbcTemplateBusinessDB.query("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES",
                    (rs, rowNum) -> rs.getString(1));
        } else {
            tables = jdbcTemplateBusinessDB.query("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE lower(TABLE_NAME) LIKE lower(?)",
                    new Object[]{query + "%"},
                    (rs, rowNum) -> rs.getString(1));
        }
        return tables;
    }

    public boolean getIsTablePresent(String tableName) {
        int Count = jdbcTemplateBusinessDB.queryForObject("SELECT count(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME =?"
                , new Object[]{tableName}, Integer.class);
        return Count != 0;
    }

    public List<IAttribute> getColumns(String tableName) {
        String q = "SELECT COLUMN_NAME as name,\n" +
                "       CASE\n" +
                "           WHEN (SELECT COLUMN_NAME\n" +
                "                 FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE\n" +
                "                 WHERE OBJECTPROPERTY(OBJECT_ID(CONSTRAINT_SCHEMA + '.' + QUOTENAME(CONSTRAINT_NAME)), 'IsPrimaryKey') =\n" +
                "                       1\n" +
                "                   AND TABLE_NAME = ?) = COLUMN_NAME\n" +
                "               THEN 'Y'\n" +
                "           ELSE\n" +
                "               'N'\n" +
                "           END     AS IS_PRIMERY_KEY,\n" +
                "       DATA_TYPE   as dataType\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "where TABLE_NAME = ?";
        return jdbcTemplateBusinessDB.query(q, new Object[]{tableName, tableName}, new ColumnRowMapper());

    }

    private String getDataTypeFromDBDataType(String DBDataType) {
        String DataType = "String";
        switch (DBDataType.toLowerCase()) {
            case "char":
            case "varchar":
            case "text":
            case "nchar":
            case "nvarchar":
            case "ntext":
                DataType = "String";
                break;
            case "bigint":
            case "int":
            case "smallint":
            case "tinyint":
            case "bit":
            case "numeric":
                DataType = "Integer";
                break;
            case "float":
                DataType = "Float";
                break;
            case "date":
            case "datetime":
            case "datetime2":
            case "smalldatetime":
            case "time":
                DataType = "DateTime";
                break;
        }
        return DataType;
    }

    class ColumnRowMapper implements RowMapper {
        @Override
        public IAttribute mapRow(ResultSet resultSet, int i) throws SQLException {
            return new IAttribute(
                    CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, resultSet.getString(1)),
                    CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, resultSet.getString(1)),
                    getDataTypeFromDBDataType(resultSet.getString(3)),
                    null, resultSet.getString(2).equals("Y"), Constants.AttributeTypes.PROPERTY);
        }
    }

}
