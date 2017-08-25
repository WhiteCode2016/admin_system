package com.white.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.common.SysConstants;
import com.white.entity.system.SysFile;
import com.white.entity.system.SysMenu;
import com.white.entity.system.SysRole;
import com.white.mapper.SysFileMapper;
import com.white.service.SystemService;
import com.white.util.DataTablePage;
import com.white.util.ResultUtil;
import com.white.web.exception.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by White on 2017/8/21.
 */
@RestController
@RequestMapping("/api/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private SystemService systemService;

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String createFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("文件名：" + fileName);
        // 获取文件的后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        logger.info("后缀名：" + suffix);
        // 文件大小
        long size = file.getSize();
        logger.info("文件大小：" + size);
        // 文件类型
        String contentType = file.getContentType();
        logger.info("文件类型：" + contentType);

        SysFile sysFile = new SysFile();
        sysFile.preInsert();
        sysFile.setOriginalFileName(fileName);
        sysFile.setSuffix(suffix);
        sysFile.setSize(size);
        sysFile.setContentType(contentType);
        sysFileMapper.insert(sysFile);

        // 文件上传后的路径
        String filePath = SysConstants.UPLOAD_PATH;
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public DataTablePage<SysFile> getListByPage(HttpServletRequest request, SysFile sysFile) {
        //使用DataTables的属性接收分页数据
        DataTablePage<SysFile> dataTable = new DataTablePage<SysFile>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<SysFile> list = systemService.getFileListByCondition(sysFile);
        //用PageInfo对结果进行包装
        PageInfo<SysFile> pageInfo = new PageInfo<SysFile>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        //返回数据到页面
        return dataTable;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteMenu(@PathVariable String id) {
        systemService.deleteFile(id);
        return ResultUtil.success();
    }

    /**
     * View视图
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView enterEditFile(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        SysFile sysFile = systemService.getFile(id);
        modelAndView.addObject("sysFile",sysFile);
        modelAndView.setViewName("admin/file/file_detail");
        return modelAndView;
    }
}
