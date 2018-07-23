package com.wyr.wx.generator;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/** 
 * @ClassName  MpGenerator
 * @author  泡面和尚
 * @date  2017年12月21日 下午12:42:05
 */
public class MpGenerator {
    
    public static void main(String[] ars){
        String packageName = "com.wyr.wx.user";
        generateByTables(packageName, new String[] { "wx_user" });
    }

    private static void generateByTables(String packageName, String... tableNames){
    	AutoGenerator mpg = new AutoGenerator();
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://192.168.1.139:3306/wx?characterEncoding=utf8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver")
                .setTypeConvert(new MySqlTypeConvert(){
		            public DbColumnType processTypeConvert(String fieldType) {
		                //"转换类型
		                return super.processTypeConvert(fieldType);
		            }
		        });
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix(new String[] { "t_", "sys_", "wx_" })
                .setInclude(tableNames); //要生成的表
        config.setActiveRecord(false)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setIdType(IdType.ID_WORKER)
                .setAuthor("yrwu@woyitech.com")
                .setOutputDir("F://project//wx//src//main//java")
                .setFileOverride(false);
        mpg.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("domain")
                                .setMapper("dao")
                                .setXml("mapper")
                );
        mpg.execute();
    }

}

