package com.white;

import com.white.entity.system.SysFile;
import com.white.entity.system.SysUser;
import com.white.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {
    @Autowired
    private SystemService systemService;

    @Test
    public void getFileListByCondition() {
        SysFile sysFile = new SysFile();
        sysFile.setSuffix("");
        System.out.println(systemService.getFileListByCondition(sysFile));
    }

    @Test
    public void insert() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("root");
		sysUser.setPassword("123");
		sysUser.setEnabled(true);
		sysUser.setRemarks("This is Test");
		systemService.addUserIncludeFile(sysUser,null);
    }
}
