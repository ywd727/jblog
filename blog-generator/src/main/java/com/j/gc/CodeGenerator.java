package com.j.gc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: myBlog-back
 * @Package: com.j.gc
 * @ClassName: CodeGenerator
 * @Author: 简鑫鑫
 * @Description: 基于mybatis-plus的代码生成器
 * @Date: 2022/7/7 15:39
 * @Version: 1.0
 */
public class CodeGenerator {
    public static void main(String[] args) {

        //创建一个集合，用来存储需要进行生成代码的表
        List<String> tables = new ArrayList<>();
        tables.add("blog_link_conversion");

        //create中用来书写数据库的配置信息
        FastAutoGenerator.create("jdbc:mysql://81.68.124.95:3306/nblog?characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai","root","162172jxx666")
                .globalConfig(builder -> {
                    builder.author("jxx")//设置作者名称
                            .outputDir(System.getProperty("user.dir")+"\\blog-api\\src\\main\\java")//指定的输出路径
                            .enableSwagger()//开启swagger模式
                            .commentDate("yyyy-MM-dd");//注释日期
//                            .fileOverride;//覆盖已生成文件（不建议开启，否者会将以前的文件及逆行覆盖）

                })
                .packageConfig(builder -> {
                    builder.parent("top")//设置父报名
                            .moduleName("jxx")//设置父包模块名
                            .entity("entity")//设置实体类生成的位置
                            .service("service")//设置服务层生成的位置
                            .serviceImpl("service.impl")//设置服务层实现类生成的位置
                            .controller("controller.tool")//设置控制层生成的位置
                            .mapper("mapper")//设置持久层生成的位置
//                            .xml("mapper")//因为xml一般是放在resource文件夹下，所以需要特殊指定下
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,System.getProperty("user.dir")+"\\blog-api\\src\\main\\resources\\mapper"));//设置xml文件生成位置
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)//设置需要生成的表名（我这里读取一个list集合，可以一次性生成几张表的代码）
                            .addTablePrefix("blog_")//设置需要过滤的表前缀（在生成对应的类的时候，会将这个表前缀去掉）
                            .serviceBuilder()//service配置策略
                            .formatServiceFileName("%sService")//service的类名，%s是用来进行适配的（根据表名进行生成对应的service类）
                            .formatServiceImplFileName("%sServiceImpl")//同上
                            .entityBuilder()
                            .enableLombok()//开启lombok
                            .logicDeleteColumnName("delFlag")//表明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()//属性加上说明注解
                            .controllerBuilder()
                            .enableHyphenStyle()//映射路径使用连字符格式，而不是驼峰
                            .formatFileName("%sController")
                            .enableRestStyle()//开启RestController
                            .mapperBuilder()
                            .enableBaseResultMap()//生成通用的resultMap
                            .superClass(BaseMapper.class)//指定生成的mapper接口继承那个父类
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()//开启Mapper注解
                            .formatXmlFileName("%sMapper");//指定xml名称，%s同上
                })
                .templateEngine(new FreemarkerTemplateEngine()) //使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
