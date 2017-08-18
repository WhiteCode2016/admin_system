package com.white;

import com.white.entity.system.SysMenu;
import com.white.entity.system.SysUser;
import com.white.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTests {
	private static final Logger logger = LoggerFactory.getLogger(MenuTests.class);

	@Autowired
	private SystemService systemService;

	@Autowired
	private CacheManager cacheManager;

	@Test
	public void systemServiceTest() throws InterruptedException {
		SysUser sysUser = systemService.getUserByUserName("admin");
		List<SysMenu> menuParents = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : sysUser.getMenus()) {
			if (sysMenu.getParentId() == null || sysMenu.getParentId().equals("")) {
				// 获取所有的父节点
				menuParents.add(sysMenu);
			}
		}
		for (SysMenu menuParent : menuParents) {
			for (SysMenu sysMenu : sysUser.getMenus()) {
				if (menuParent.getId().equals(sysMenu.getParentId())) {
					// 为父节点添加子节点
					menuParent.addChild(sysMenu);
				}
			}
		}
		Collections.sort(menuParents, new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {

                return o1.getSort().compareTo(o2.getSort());
            }
        });
        System.out.println(menuParents);
	}

	@Test
	public void deleteMenu() {
		systemService.deleteMenu("923");
	}

	@Test
	public void insert() {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setId("12345");
		sysMenu.setMenuName("test");
		sysMenu.setSort(3);
		systemService.addMenu(sysMenu);
	}

}
