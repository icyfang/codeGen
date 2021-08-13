package com.headstrong.codegen.model;

import lombok.Data;

/**
 * @author Hodur
 * @date 2021/8/12
 */
@Data
public class GlobalConfig {

    private String author;
    private String dateFormat;
    private String basePackage;
    private String excludeTablePattern;
    private String includeTablePattern;
    private String templatePath;
    private ModuleConfig controllerConfig;
    private ModuleConfig serviceConfig;
    private ModuleConfig repositoryConfig;
    private ModuleConfig poConfig;
    private ModuleConfig voConfig;
}
