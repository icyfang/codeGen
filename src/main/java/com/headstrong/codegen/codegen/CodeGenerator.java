package com.headstrong.codegen.codegen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.headstrong.codegen.basic.BaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CodeGenerator {

    public static String moduleName = "sample";
    public static final String[] INCLUDES = new String[]{"t_sample"};

    public static final String SOURCE = "/src/main/java";
    public static final String BASIC_PATH = "D:/IdeaProjects/codeGen";

    public static final String SCHEMA_NAME = "common";
    public static final String URL = "";
    public static final String USERNAME = "";
    public static final String PASSWORD = "";
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {
        genVO();
        genClientType();
        genMapperType();
    }

    private static void genMapperType() {

        AutoGenerator boMpg = buildAutoGenerator();
        boMpg.getGlobalConfig().setOutputDir(BASIC_PATH + SOURCE);
        boMpg.getPackageInfo().setParent("com.headstrong.codegen.business")
                .setMapper("repository")
                .setEntity("entity");
        boMpg.getTemplate().setEntity("templates/mapper/entity.java")
                .setMapper("templates/mapper/mapper.java")
                .setController("templates/controller.java")
                .setService("templates/service.java")
                .setServiceImpl("templates/mapper/serviceImpl.java");

        boMpg.execute();
    }

    private static void genClientType() {

        AutoGenerator boMpg = buildAutoGenerator();
        boMpg.getGlobalConfig().setOutputDir(BASIC_PATH + SOURCE)
                .setMapperName("%sClient")
                .setEntityName("%s")
                .setServiceImplName("%sClientServiceImpl");
        boMpg.getPackageInfo().setParent("com.headstrong.codegen.business");
        boMpg.getTemplate().setMapper("templates/client/client.java")
                .setServiceImpl("templates/client/serviceImpl.java");

        boMpg.execute();
    }

    private static void genVO() {
        AutoGenerator boMpg = buildAutoGenerator();
        boMpg.getGlobalConfig().setEntityName("%s");
        boMpg.getPackageInfo().setParent("com.headstrong.codegen.business")
                .setEntity("vo");

        List<FileOutConfig> focList = boMpg.getCfg().getFileOutConfigList();
        String outputDir = BASIC_PATH + SOURCE + "/com/headstrong/codegen/business/";
        focList.add(new FileOutConfig("/templates/vo.java.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return outputDir + moduleName + "/vo/" + tableInfo.getEntityName()
                        + "VO" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/voAddParam.java.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return outputDir + moduleName + "/vo/" + tableInfo.getEntityName()
                        + "AddParamVO" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/voUpdateParam.java.vm") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return outputDir + moduleName + "/vo/" + tableInfo.getEntityName()
                        + "UpdateParamVO" + StringPool.DOT_JAVA;
            }
        });

        boMpg.execute();
    }

    private static ExtendAutoGenerator buildAutoGenerator() {
        ExtendAutoGenerator boMpg = new ExtendAutoGenerator();
        boMpg.setCfg(injectionConfig())
                .setStrategy(strategyConfig())
                .setGlobalConfig(globalConfig())
                .setDataSource(dataSourceConfig())
                .setPackageInfo(packageConfig())
                .setTemplate(templateConfig())
                .setTemplateEngine(new VelocityTemplateEngine());

        return boMpg;
    }

    private static StrategyConfig strategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy
                // database/entity related strategy
                .setInclude(INCLUDES)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass(BaseEntity.class)
                .setVersionFieldName("hVersion")
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityLombokModel(true)
                .setChainModel(true)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setEntitySerialVersionUID(true)
                .setTablePrefix("t_")
                // controller related strategy
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(false);
        return strategy;
    }

    private static InjectionConfig injectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                setMap(new HashMap<>(8));
                getMap().put("schemaName", SCHEMA_NAME);

                // entity example, for @ApiModelProperty.example
                getMap().put("oid", 1);
                getMap().put("vendorNo", 1);
                getMap().put("categoryId", 1);
                getMap().put("solutionName", "baseFusion");
                getMap().put("introductionFilePath", "/baseFusion.html");
                getMap().put("status", 1);
            }

            @Override
            public void initTableMap(TableInfo tableInfo) {
                super.initTableMap(tableInfo);

                getMap().put("domainName", getDomainName(tableInfo));
            }

            private String getDomainName(TableInfo tableInfo) {
                StrategyConfig config = getConfig().getStrategyConfig();
                String[] tablePrefix = config.getTablePrefix();
                INameConvert nameConvert = config.getNameConvert();
                String domainName;

                if (null != nameConvert) {
                    // 自定义处理实体名称
                    domainName = nameConvert.entityNameConvert(tableInfo);
                } else {
                    domainName = NamingStrategy
                            .capitalFirst(processName(tableInfo.getName(), config.getNaming(), tablePrefix));
                }
                return domainName;
            }

            private String processName(String name, NamingStrategy strategy, String[] prefix) {
                boolean removePrefix = prefix != null && prefix.length != 0;
                String propertyName;
                if (removePrefix) {
                    if (strategy == NamingStrategy.underline_to_camel) {
                        // 删除前缀、下划线转驼峰
                        propertyName = NamingStrategy.removePrefixAndCamel(name, prefix);
                    } else {
                        // 删除前缀
                        propertyName = NamingStrategy.removePrefix(name, prefix);
                    }
                } else if (strategy == NamingStrategy.underline_to_camel) {
                    // 下划线转驼峰
                    propertyName = NamingStrategy.underlineToCamel(name);
                } else {
                    // 不处理
                    propertyName = name;
                }
                return propertyName;
            }
        };

        cfg.setFileOutConfigList(new ArrayList<>());
        return cfg;
    }

    private static TemplateConfig templateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity(null)
                .setController(null)
                .setService(null)
                .setServiceImpl(null)
                .setMapper(null)
                .setXml(null);

        return templateConfig;
    }

    private static PackageConfig packageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null)
                .setModuleName(moduleName)
                .setController(null)
                .setService(null)
                .setServiceImpl(null)
                .setEntity("entity")
                .setMapper(null);
        return pc;
    }

    private static DataSourceConfig dataSourceConfig() {

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL)
                .setDriverName(DRIVER_NAME)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setSchemaName(SCHEMA_NAME);
        return dsc;
    }

    private static GlobalConfig globalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(BASIC_PATH + SOURCE)
                .setFileOverride(true)
                .setOpen(false)
                .setAuthor("Hodur")
                .setSwagger2(true)
                .setEntityName("%s")
                .setMapperName("%sRepository")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sRest")
                .setIdType(IdType.AUTO);
        return gc;
    }

}

