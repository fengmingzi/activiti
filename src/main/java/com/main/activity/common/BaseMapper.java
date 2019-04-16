package com.main.activity.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: Mapper基类
 * @Date: 2019/4/12 14:39
 */
public interface BaseMapper<T> {

    /**创建数据*/
    Boolean create(T t);

    /**更新数据*/
    Boolean update(T t);

    /**根据id逻辑删除.*/
    Boolean deleteLogicalById(T t);

    /**根据id删除.*/
    Boolean deleteById(T t);

    /**根据id获取单条数据.*/
    T getOneById(Long id);

    /**根据条件获取单条数据.*/
    T getOneByCondition(@Param("entity") T t);

    /**根据条件获取数量.*/
    Long countByCondition(@Param("entity") T t);

    /**
     * 根据条件获取翻页数据.
     * 获取分页数据总数需要根据countByCondition方法来获取
     */
    List<T> getPageByCondition(@Param("pager") Pager<T> pager, @Param("entity") T t);
}
