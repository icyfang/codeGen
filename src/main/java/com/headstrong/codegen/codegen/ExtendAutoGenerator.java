package com.headstrong.codegen.codegen;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hodur
 * @date 2021/12/10
 */
@Slf4j
public class ExtendAutoGenerator extends AutoGenerator {

    @Override
    public void execute() {
        log.debug("==========================准备生成文件...==========================");
        // 初始化配置
        if (null == config) {
            config = new ExtendConfigBuilder(getPackageInfo(), getDataSource(), getStrategy(), getTemplate(),
                    getGlobalConfig());
            if (null != injectionConfig) {
                injectionConfig.setConfig(config);
            }
        }
        if (null == getTemplateEngine()) {
            // 为了兼容之前逻辑，采用 Velocity 引擎 【 默认 】
            setTemplateEngine(new VelocityTemplateEngine());
        }
        // 模板引擎初始化执行文件输出
        getTemplateEngine().init(this.pretreatmentConfigBuilder(config)).mkdirs().batchOutput().open();
        log.debug("==========================文件生成完成！！！==========================");
    }

}
