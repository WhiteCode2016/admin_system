package com.white.mapper;

import com.white.dao.CrudDao;
import com.white.entity.system.SysFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件Dao接口
 */
@Mapper
public interface SysFileMapper extends CrudDao<SysFile> {
    // 根据userId删除用户的文件信息
    void deleteByUserId(String userId);

}
