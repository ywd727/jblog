package top.jxx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.jxx.service.RedisService;

@SpringBootTest
class BlogApiApplicationTests {

	@Autowired
	private RedisService redisService;

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		redisService.saveObjectToValue("demo","666");
	}

}
