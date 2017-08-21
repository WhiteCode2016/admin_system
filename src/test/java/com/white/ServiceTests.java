package com.white;

import com.white.entity.system.SysFile;
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
}
