package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.entity.system.SysRole;
import com.white.service.SystemService;
import com.white.util.DataTablePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by White on 2017/8/3.
 */

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private SystemService systemService;
    
    @RequestMapping(value="/listByPage", method= RequestMethod.POST)
    public DataTablePage<SysRole> getListByPage(HttpServletRequest request,SysRole sysRole) {
        //使用DataTables的属性接收分页数据
        DataTablePage<SysRole> dataTable = new DataTablePage<SysRole>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<SysRole> list = systemService.getRoleListByCondition(sysRole);
        //用PageInfo对结果进行包装
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        //返回数据到页面
        return dataTable;
    }
}
