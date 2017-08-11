package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.entity.system.SysUser;
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
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SystemService systemService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<SysUser> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<SysUser> sysUsers = systemService.getAllUsers();
        return sysUsers;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute SysUser sysUser) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        systemService.addUser(sysUser);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public SysUser getUser(@PathVariable String id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return systemService.getUser(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable String id, @ModelAttribute SysUser sysUser) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        systemService.updateUser(sysUser);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        systemService.deleteUser(id);
        return "success";
    }

    @RequestMapping(value="/list", method= RequestMethod.POST)
    public DataTablePage<SysUser> getList(HttpServletRequest request) {
        //使用DataTables的属性接收分页数据
        DataTablePage<SysUser> dataTable = new DataTablePage<SysUser>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<SysUser> list = systemService.getAllUsers();
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

    @RequestMapping(value="/listByPage", method= RequestMethod.POST)
    public DataTablePage<SysUser> getListByPage(HttpServletRequest request,SysUser sysUser) {
        logger.info(sysUser.toString());
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
}
