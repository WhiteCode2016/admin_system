package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.entity.system.SysMenu;
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
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public JsonResult<SysMenu> addMenu(@ModelAttribute SysMenu sysMenu) {
        systemService.addMenu(sysMenu);
        return ResultUtil.success();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView getUser(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        SysMenu sysMenu = systemService.getMenu(id);
        List<SysMenu> sysMenuParents = systemService.getAllMenus();
        modelAndView.addObject("sysMenu",sysMenu);
        modelAndView.addObject("sysMenuParents",sysMenuParents);
        modelAndView.setViewName("admin/menu/menu_edit");
        return modelAndView;
    }

    @RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
    public ModelAndView getMenuDetail(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        SysMenu sysMenu = systemService.getMenu(id);
        modelAndView.addObject("sysMenu",sysMenu);
        modelAndView.setViewName("admin/menu/menu_detail");
        return modelAndView;
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public JsonResult<SysMenu> updateMenu(@ModelAttribute SysMenu sysMenu) {
        systemService.update(sysMenu);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResult<SysMenu> deleteMenu(@PathVariable String id) {
        systemService.deleteMenu(id);
        return ResultUtil.success();
    }

    @RequestMapping(value="/listByPage", method= RequestMethod.POST)
    public DataTablePage<SysMenu> getListByPage(HttpServletRequest request, SysMenu sysMenu) {
        //使用DataTables的属性接收分页数据
        DataTablePage<SysMenu> dataTable = new DataTablePage<SysMenu>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<SysMenu> list = systemService.getMenuListByCondition(sysMenu);
        //用PageInfo对结果进行包装
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        //返回数据到页面
        return dataTable;
    }

    @RequestMapping(value="/getAllMenus", method = RequestMethod.POST)
    public List<SysMenu> getAllMenus() {
        return systemService.getAllMenus();
    }

    @RequestMapping(value="/getMenusByRoleId/{id}", method = RequestMethod.GET)
    public List<SysMenu> getMenusByRoleId(@PathVariable String id) {
        return systemService.getMenusByRoleId(id);
    }

    @RequestMapping(value="/getMenuTree/{id}", method=RequestMethod.GET)
    public ModelAndView getMenuTree(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id",id);
        modelAndView.setViewName("admin/menu/menu_tree");
        return modelAndView;
    }
}
