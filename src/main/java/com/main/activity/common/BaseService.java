package com.main.activity.common;

import com.main.activity.common.utils.Response;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: service基类
 * @Date: 2019/4/12 14:49
 */
public interface BaseService<T> {

    Response<T> create(@Param("entity") T t); //创建数据

    Response<T> update(@Param("entity") T t);

    Response<Boolean> deleteLogicalById(T t); //根据id逻辑删除.

    Response<Boolean> deleteById(T t); //根据id删除.

    Response<T> getOneById(Long id); //根据id获取单条数据.

    Response<T> getOneByCondition(@Param("entity") T t); //根据条件获取单条数据.

    Response<Long> countByCondition(@Param("entity") T t); //根据条件获取数量.

    /**
     * 根据条件获取翻页数据.
     * 获取分页数据总数需要根据countByCondition方法来获取
     */
    Response<List<T>> getPageByCondition(@Param("pager") Pager<T> pager, @Param("entity") T t);


}
