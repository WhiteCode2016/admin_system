package com.white.mapper;

import com.white.dao.CrudDao;
import com.white.entity.system.SysFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件Dao接口
 */
@Mapper
public interface SysFileMapper extends CrudDao<SysFile> {
}
