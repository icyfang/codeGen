package com.headstrong.codegen.model;

import lombok.Data;

/**
 * @author Hodur
 * @date 2021/8/12
 */
@Data
public class FieldDefinition {

    private String name;
    private Boolean nullable;
    private Boolean unique;
    private String type;
    private String comment;
    private Integer length;
}
