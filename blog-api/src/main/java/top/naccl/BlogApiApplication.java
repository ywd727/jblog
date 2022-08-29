package top.naccl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"top"})
public class BlogApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(BlogApiApplication.class, args);

		/*Object redisServiceImpl = run.getBean("redisServiceImpl");

		System.out.println("redisServiceImpl = " + redisServiceImpl);*/


	}

}
