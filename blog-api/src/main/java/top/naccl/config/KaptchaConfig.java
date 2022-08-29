package top.naccl.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ProjectName: blog-api
 * @Package: top.naccl.config
 * @ClassName: KaptchaConfig
 * @Author: 简鑫鑫
 * @Description:
 * @Date: 2022/8/24 15:02
 * @Version: 1.0
 */
@Configuration
public class KaptchaConfig {

    /*@Bean
    public Producer kaptchaProducer() {
        Properties properties = new Properties();
        //设置验证码的宽度
        properties.setProperty("kaptcha.image.width", "100");
        //设置宽度
        properties.setProperty("kaptcha.image.height", "40");
        //设置字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        //设置字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        //限定验证码中的字符
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //设置验证码的长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //设置添加噪声与否
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        //将配置装载到一个实例中
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        //将配置传入实例
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }*/

    /**
     * 生成验证码的配置
     * @return
     */
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        //验证码生成器
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        //配置
        Properties properties = new Properties();
        //是否有边框
        properties.setProperty("kaptcha.border", "yes");
        //设置边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        //边框粗细度，默认为1
        // properties.setProperty("kaptcha.border.thickness","1");
        //验证码
        properties.setProperty("kaptcha.session.key","code");
        //验证码文本字符颜色 默认为黑色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        //设置字体样式
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        //字体大小，默认40
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //验证码文本字符内容范围 默认为abced2345678gfynmnpwx
        // properties.setProperty("kaptcha.textproducer.char.string", "");
        //字符长度，默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字符间距 默认为2
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        //验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width", "100");
        //验证码图片高度 默认为40
        properties.setProperty("kaptcha.image.height", "40");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

    /*@Bean
    public Producer KaptchaProducer(){
        Properties properties = new Properties();
        //图片宽度
        properties.setProperty("kaptcha.image.width","100");
        //图片高度
        properties.setProperty("kaptcha.image.height","40");
        //字号
        properties.setProperty("kaptcha.textproducer.font.size","32");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color","black");
        //字符范围
        properties.setProperty("kaptcha.textproducer.char.string","0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //字符数量
        properties.setProperty("kaptcha.textproducer.char.length","4");
        //字符干扰模式
        //properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");//去掉字符干扰

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }*/
}
