package com.example;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

public class MyBatisPlusGenerator {
    String[] tableNames = {
//            "sys_task",
//            "sys_task_log",
//            "sys_attach",
//            "sys_attach_relation",
//            "sys_role_menu",
//            "sys_menu",
//            "sys_role",
//            "sys_user",
//            "sys_user_ext",
//            "sys_user_role",
//            "sys_dept",
            "product",
            "order",
            "dispatch"
    };

    @Test
    public void generateCode() {
        String packageName = "com.example";
        //user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = false;
        generateByTables(serviceNameStartWithI, packageName, tableNames);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://116.62.198.241:3306/test")
                .setUsername("root")
                .setPassword("Ak902078.")
                .setDriverName("org.mariadb.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
//                .setSuperControllerClass("com.hrhb.sdk.core.BaseController")
                .setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper")
//                .setSuperEntityClass("com.hrhb.sdk.core.BaseModel")
                .setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService")
                .setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")
//                .setTablePrefix("cl_")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor("Akang")
                .setOutputDir("src/test/java")
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        config.setEnableCache(false);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName)
                .setController("controller")
                .setEntity("model")
//                .setXml("removme_to_resource_mapper")
//                .setMapper("model.dao")
        ;

        TemplateConfig templateConfig = new TemplateConfig();

        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setTemplate(templateConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setPackageInfo(packageConfig)
                .execute();
    }

}
