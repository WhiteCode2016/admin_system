package com.white;

import com.white.mapper.SysMenuMapper;
import com.white.mapper.SysRoleMapper;
import com.white.mapper.SysUserMapper;
import com.white.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EhcacheTests {
	private static final Logger logger = LoggerFactory.getLogger(EhcacheTests.class);

	@Autowired
	private SystemService systemService;

	@Autowired
	private CacheManager cacheManager;

	@Test
	public void systemServiceTest() throws InterruptedException {
		System.out.println("第一次查询:" + systemService.getUserByUserName("admin"));
		Thread.sleep(2000);
		System.out.println("第二次查询:" + systemService.getUserByUserName("admin"));
		Thread.sleep(11000);
		System.out.println("第三次查询:" + systemService.getUserByUserName("admin"));
	}
}
