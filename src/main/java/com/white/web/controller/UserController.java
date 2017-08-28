package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.white.entity.system.SysRole;
import com.white.entity.system.SysUser;
import com.white.service.SystemService;
import com.white.util.DataTablePage;
import com.white.util.ResultUtil;
import com.white.web.exception.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public JsonResult addUser(SysUser sysUser) {
        systemService.addUserAndRole(sysUser);
        return ResultUtil.success();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public JsonResult updateUserAndFile(@PathVariable String id, SysUser sysUser, MultipartFile file) {
        systemService.updateUserAndRoleAndFile(sysUser,file);
        return ResultUtil.success();
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public JsonResult updateUser(@PathVariable String id, SysUser sysUser) {
        systemService.updateUserAndRole(sysUser);
        return ResultUtil.success();
    }*/

    @RequestMapping(value = "/icon/{id}", method = RequestMethod.POST)
    public JsonResult updateEditIcon(@PathVariable String id, MultipartFile file) {
        systemService.updateFile(id,file);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public DataTablePage<SysUser> getListByPage(HttpServletRequest request,SysUser sysUser) {
        //使用DataTables的属性接收分页数据
        DataTablePage<SysUser> dataTable = new DataTablePage<SysUser>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<SysUser> list = systemService.getUserListByCondition(sysUser);
        //用PageInfo对结果进行包装
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        //返回数据到页面
        return dataTable;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteUser(@PathVariable String id) {
        systemService.deleteUser(id);
        return ResultUtil.success();
    }

    /**
     * View视图
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView enterListUser() {
        return new ModelAndView("admin/user/user_list");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView enterEditUser(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        // 获取用户的信息
        SysUser sysUser = systemService.getUserAndRole(id);
        // 获取角色列表动态显示
        List<SysRole> roles = systemService.getAllRoles();
        modelAndView.addObject("roles",roles);
        modelAndView.addObject("sysUser",sysUser);
        modelAndView.setViewName("admin/user/user_edit");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView enterAddUser() {
        ModelAndView modelAndView = new ModelAndView();
        // 获取角色列表动态显示
        List<SysRole> roles = systemService.getAllRoles();
        modelAndView.addObject("roles",roles);
        modelAndView.setViewName("admin/user/user_add");
        return modelAndView;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView getUserDetail(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        // 获取用户的信息
        SysUser sysUser = systemService.getUserAndRole(id);
        modelAndView.addObject("sysUser",sysUser);
        modelAndView.setViewName("admin/user/user_detail");
        return modelAndView;
    }

    @RequestMapping(value = "/icon/{id}", method = RequestMethod.GET)
    public ModelAndView enterEditIcon(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        SysUser sysUser = systemService.getUser(id);
        modelAndView.addObject("sysUser",sysUser);
        modelAndView.setViewName("admin/user/user_icon");
        return modelAndView;
    }
}
