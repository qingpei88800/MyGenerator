package com.example.demo.mybatisPlus;

import java.sql.SQLException;
import java.util.Collections;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig.Builder;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.query.SQLQuery;

/**
 * <p>
 * 快速生成
 * </p>
 *
 * @author lanjerry
 * @since 2021-09-16
 */
public class FastAutoGeneratorTest {

    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        ge2();
    }

    public static void ge2() {
        Builder dataSourceConfigBuilder =
                new DataSourceConfig.Builder("jdbc:mysql://cluster01.proxysql.staging.internal:6032/gifshow",
                        "gifshow_48376_v1_rw",
                        "Kv1E3LaWT2P9qisVjunIlt8meF4Sdpxy")
                        .schema("gifshow")
                        .databaseQueryClass(SQLQuery.class)
                        .typeConvert(new MySqlTypeConvertCustom());
        // 数据源配置
        FastAutoGenerator.create(dataSourceConfigBuilder)
                .globalConfig(builder -> {
                    builder.author("liuqingpei")        // 设置作者
                            .disableOpenDir()       // 禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.kuaishou.oco.lever.dal.entity.commodityCard") // 父包模块名
                            .controller("controller")   //Controller 包名 默认值:controller
                            .entity("entity")           //Entity 包名 默认值:entity
                            .service("service")         //Service 包名 默认值:service
                            .mapper("mapper")           //Mapper 包名 默认值:mapper
                            .moduleName("model")
                            //.moduleName("xxx")        // 设置父包模块名 默认值:无
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                    //默认存放在mapper的xml下
                })
                .strategyConfig(builder -> {
                    builder.addInclude(
                                    "oversea_commodity_card_group_relation,oversea_commodity_card_group,"
                                            + "oversea_commodity_card_photo") //
                            // 设置需要生成的表名 可边长参数“user”, “user1”
                            .addTablePrefix("tb_", "gms_") // 设置过滤表前缀
                            .serviceBuilder()//service策略配置
                            .enableFileOverride()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()// 实体类策略配置
                            .enableFileOverride()
                            .idType(IdType.AUTO)//主键策略  雪花算法自动生成的id
                            //                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            // 自动填充配置
                            //                            .addTableFills(new Property("update_time", FieldFill
                            //                            .INSERT_UPDATE))
                            .enableLombok() //开启lombok
                            //                    .logicDeleteColumnName("deleted")// 说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()// 属性加上注解说明
                            .controllerBuilder() //controller 策略配置
                            .enableFileOverride()
                            .formatFileName("%sController")
                            .enableRestStyle() // 开启RestController注解
                            .mapperBuilder()// mapper策略配置
                            .enableFileOverride()
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()//@mapper注解开启
                            .formatXmlFileName("%sMapper");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }


}
