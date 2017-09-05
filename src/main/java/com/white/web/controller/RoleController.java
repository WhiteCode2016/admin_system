package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.entity.system.SysRole;
import com.white.service.SystemService;
import com.white.util.DataTablePage;
import com.white.util.ResultUtil;
import com.white.web.exception.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private SystemService systemService;

    @RequestMapping(value="/", method=RequestMethod.POST)
    public JsonResult addRole(SysRole sysRole) {
        systemService.addRole(sysRole);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public JsonResult updateRole(@PathVariable String id, SysRole sysRole) {
        systemService.updateRole(sysRole);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteRole(@PathVariable String id) {
        systemService.deleteRole(id);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult addAndupdateRoleMenuTree(@PathVariable String id, @RequestParam(value="menuIds[]") List<String> menuIds) {
        logger.info("========= " + menuIds.toString());
        systemService.addRoleAndMenu(id, menuIds);
        return ResultUtil.success();
    }

    /**
     * View视图
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView enterListRole() {
        return new ModelAndView("admin/role/role_list");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView enterEditRole(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        // 获取角色信息
        SysRole sysRole = systemService.getRoleAndMenu(id);
        modelAndView.addObject("sysRole",sysRole);
        modelAndView.setViewName("admin/role/role_edit");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView enterAddRole() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/role/role_add");
        return modelAndView;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView getUserDetail(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        // 获取角色的信息
        SysRole sysRole = systemService.getRole(id);
        modelAndView.addObject("sysRole",sysRole);
        modelAndView.setViewName("admin/role/role_detail");
        return modelAndView;
    }
}
