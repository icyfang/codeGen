package com.headstrong.codegen.model;

import com.headstrong.codegen.utils.ResourceHelper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hodur
 * @date 2021/8/13
 */
public class EntityParser {

    public EntityDefinition parseTable(String table) throws SQLException {
        String url = ResourceHelper.getProperty("datasource.url");
        String username = ResourceHelper.getProperty("datasource.url");
        String password = ResourceHelper.getProperty("datasource.url");
        Connection connection = DriverManager.getConnection(url, username, password);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, table, null);
        ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, table);

        List<FieldDefinition> fieldDefinitionList = new ArrayList<>();
        List<FieldDefinition> idFieldList = new ArrayList<>();
        List<String> idField = new ArrayList<>();
        while (primaryKeys.next()) {
            short keySeq = primaryKeys.getShort("KEY_SEQ");
            idField.add(keySeq, primaryKeys.getString("COLUMN_NAME"));
        }
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            String typeName = columns.getString("TYPE_NAME");
            int nullable = columns.getInt("NULLABLE");
            String remarks = columns.getString("REMARKS");
            String isAutoincrement = columns.getString("IS_AUTOINCREMENT");
            int columnSize = columns.getInt("COLUMN_SIZE");

            FieldDefinition field = new FieldDefinition();
            field.setName(columnName);
            field.setType(toJavaType(typeName));
            field.setLength(columnSize);
            field.setNullable(nullable == 1);
            field.setComment(remarks);
            field.setAutoIncrement("YES".equals(isAutoincrement));

            if (idField.contains(columnName)) {
                idFieldList.add(field);
            } else {
                fieldDefinitionList.add(field);
            }
        }
        EntityDefinition entityDefinition = new EntityDefinition();
        // todo append po suffix and convert case
        entityDefinition.setClassName(table);
        entityDefinition.setTableName(table);
        entityDefinition.setIdFields(idFieldList);
        entityDefinition.setFields(fieldDefinitionList);
        return entityDefinition;
    }

    String toJavaType(String typeName) {
        switch (typeName) {
            case "VARCHAR":
                return "java.lang.String";
            case "BIGINT":
                return "java.lang.Long";
            case "INT":
                return "java.lang.Integer";
            case "DATE":
                return "java.time.LocalDate";
            case "DATETIME":
                return "java.time.LocalDateTime";
            default:
                return "";
        }

    }
}
