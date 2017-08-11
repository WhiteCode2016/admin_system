package com.white.dao;

import java.util.List;
import java.util.Map;

public interface CrudDao<T> extends BaseDao {

    // 获取单条数据
    T get(String id);
    T get(T entity);

    // 查询数据列表
    List<T> findList(T entity);
    List<T> queryList(Map<String, Object> queryMap);

    // 查询所有数据列表
    List<T> findAllList();

    // 插入数据
    int insert(T entity);

    // 更新数据
    int update(T entity);

    // 删除数据
    int delete(T entity);
    int deleteById(String id);

}
