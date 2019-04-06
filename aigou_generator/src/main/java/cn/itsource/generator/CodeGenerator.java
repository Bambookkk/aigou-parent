package cn.itsource.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

public class CodeGenerator {

    public static void main(String[] args) throws InterruptedException {
        //1.读取代码生成器的配置文件
        ResourceBundle rb = ResourceBundle.getBundle("generator");

        //2.配置代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //2.1 常规配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(rb.getString("OutputDir"));//设置输出位置
        gc.setFileOverride(true);//开启文件覆盖
        gc.setActiveRecord(true);// 开启activeRecord 模式
        gc.setEnableCache(false);// 开启XML二级缓存
        gc.setBaseResultMap(true);// 开启XMLesultMap
        gc.setBaseColumnList(false);// 开启XMLcolumList
        gc.setAuthor(rb.getString("author"));//设置作者
        gc.setOpen(false);//生成完毕后是否打开文件夹
        mpg.setGlobalConfig(gc);
        //2.2 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);//数据库类型的设置
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName(rb.getString("jdbc.driver"));
        dsc.setUsername(rb.getString("jdbc.user"));
        dsc.setPassword(rb.getString("jdbc.pwd"));
        dsc.setUrl(rb.getString("jdbc.url"));
        mpg.setDataSource(dsc);
        //2.3 生成策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[] { "t_" });//去掉表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);//表名生成策略：下划线转驼峰
        strategy.setInclude(new String[]{"t_product_ext"}); //需要生成的表:字符串数组，自己增加
        mpg.setStrategy(strategy);
        //2.4 拼接domain、mapper...的包路径
        PackageConfig pc = new PackageConfig();
        pc.setParent(rb.getString("parent"));//读取自定义包路径
        pc.setEntity("domain");//拼接实体类包路径
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("web.controller");
        mpg.setPackageInfo(pc);
        //2.5 注入自定义配置，可以在 VM 中使用 cfg.abc（可以不要）
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                this.setMap(map);
            }
        };
        //2.6 调整各个文件的生成目录
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/entity.java.vm") {//调整 domain生成目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirBase")+ "/cn/itsource/aigou/domain/" + tableInfo.getEntityName() + ".java";
            }
        });
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") { //调整 mapper.xml生成目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirXml")+ "/cn/itsource/aigou/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        focList.add(new FileOutConfig("/templates/controller.java.vm") {//调整controller生成目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDir")+ "/cn/itsource/aigou/web/controller/" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        focList.add(new FileOutConfig("/templates/query.java.vm") {//调整query生成目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirBase")+ "/cn/itsource/aigou/query/" + tableInfo.getEntityName() + "Query.java";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //2.7 自定义模板配置，模板文件位置默认在本项目src/main/resources/templates下
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity(null); //任何一个模块如果设置空或Null将不生成该模块。
        tc.setXml(null);
        tc.setMapper("/templates/mapper.java.vm");
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        tc.setController(null);
        mpg.setTemplate(tc);

        //3.执行生成
        mpg.execute();
    }

}
