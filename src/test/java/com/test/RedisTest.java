package com.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.midread.book.redis.StringTemplate;

@ContextConfiguration(locations = { "/applicationContext.xml","file:src/main/webapp/WEB-INF/spring-mvc.xml" })
public class RedisTest  extends AbstractJUnit4SpringContextTests {
	@Autowired
	StringTemplate stringTemplate;
	
	@Test
	public void test() {
		stringTemplate.delete("tabbar:images");
		stringTemplate.delete("tabbar:txts");
	}
}
