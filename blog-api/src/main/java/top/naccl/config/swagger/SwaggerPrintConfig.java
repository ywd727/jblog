package top.naccl.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @ProjectName: blog
 * @Package: top.naccl.config.swagger
 * @ClassName: SwaggerPrintConfig
 * @Author: 简鑫鑫
 * @Description:
 * @Date: 2022/8/30 10:28
 * @Version: 1.0
 */
@Component
@Slf4j
public class SwaggerPrintConfig implements ApplicationListener<WebServerInitializedEvent> {

    /**
     * 在项目启动的时候在控制台上打印出swagger的相关的信息
     * @param event
     */
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            // 获取IP
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            // 获取端口号
            int port = event.getWebServer().getPort();
            // 获取应用名
            String applicationName = event.getApplicationContext().getApplicationName();
            // 打印 swagger 文档地址
            log.info("项目启动启动成功！swagger 接口文档地址: http://" + hostAddress + ":" + port + applicationName + "/swagger-ui.html");
            // 打印 swagger2 文档地址
            log.info("项目启动启动成功！swagger2 接口文档地址: http://" + hostAddress + ":" + port + applicationName + "/doc.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
