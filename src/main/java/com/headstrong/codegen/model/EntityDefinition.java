package com.headstrong.codegen.model;

import lombok.Data;

import java.util.List;

/**
 * @author Hodur
 * @date 2021/8/12
 */
@Data
public class EntityDefinition {

    private String className;
    private String tableName;
    private List<FieldDefinition> idFields;
    private List<FieldDefinition> fields;

}
