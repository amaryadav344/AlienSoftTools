package com.webstudio.hub.repositories;

import com.business.utils.models.Entity.IColumn;
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
                    new Object[]{query},
                    (rs, rowNum) -> rs.getString(1));
        }
        return tables;
    }

    public boolean getIsTablePresent(String tableName) {
        int Count = jdbcTemplateBusinessDB.queryForObject("SELECT count(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME =?"
                , new Object[]{tableName}, Integer.class);
        return Count != 0;
    }

    public List getColumns(String tableName) {
        String q = "SELECT COLUMN_NAME as name,DATA_TYPE as dataType,IS_NULLABLE as canBeNull,CAST(COALESCE (CHARACTER_MAXIMUM_LENGTH, NUMERIC_PRECISION,'') AS VARCHAR) as maxLength,COLUMN_NAME as objectField FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
        return jdbcTemplateBusinessDB.query(q, new Object[]{tableName}, new ColumnRowMapper());

    }

    public String getConnectionString() {
        String db_name = "";
        try {
            db_name = jdbcTemplateBusinessDB.getDataSource().getConnection().getMetaData().getURL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return db_name;
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
        public IColumn mapRow(ResultSet resultSet, int i) throws SQLException {
            return new IColumn(resultSet.getString(1),
                    resultSet.getString(5),
                    getDataTypeFromDBDataType(resultSet.getString(2)),
                    resultSet.getString(4), resultSet.getString(3));
        }
    }

}
