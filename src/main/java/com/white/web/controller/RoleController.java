package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.entity.system.SysRole;
import com.white.entity.system.SysUser;
import com.white.service.SystemService;
import com.white.util.DataTablePage;
import com.white.util.ResultUtil;
import com.white.web.exception.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value="/", method=RequestMethod.POST)
    public Result<Object> postUser(@ModelAttribute SysRole sysRole) {
        // 处理"/api/role/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        systemService.addRole(sysRole);
        return ResultUtil.success();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView getUser(@PathVariable String id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        ModelAndView modelAndView = new ModelAndView();
        SysRole sysRole = systemService.getRole(id);
        modelAndView.addObject("sysRole",sysRole);
        modelAndView.setViewName("admin/role/role_edit");
        return modelAndView;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public Result<Object> putUser1(@PathVariable String id, @ModelAttribute SysRole sysRole) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        systemService.updateRole(sysRole);
        return ResultUtil.success();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Result<Object> deleteUser(@PathVariable String id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        systemService.deleteRole(id);
        return ResultUtil.success();
    }

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
