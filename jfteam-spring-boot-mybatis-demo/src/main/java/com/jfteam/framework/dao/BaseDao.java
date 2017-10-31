package com.jfteam.framework.dao;

import com.jfteam.framework.page.PageBean;
import com.jfteam.framework.page.PageParam;
import com.jfteam.framework.vo.BaseVO;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @param <Id>
 * @author fengwenping
 * @desc 数据库操作基础类
 * @since 2016年5月14日 上午12:59:40
 */
public interface BaseDao<T extends BaseVO, Id extends Serializable> {

    /**
     * 插入单条数据
     *
     * @param entity
     * @return
     */
    int insertEntity(@Param("entity") T entity);

    /**
     * 批量插入数据
     *
     * @param entities
     */
    void insertEntities(@Param("entities") List<T> entities);

    /**
     * 根据主键删除数据
     *
     * @param id
     * @return
     */
    int deleteById(@Param("id") Id id);

    /**
     * 根据多个主键批量删除数据
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") List<? extends Id> ids);

    /**
     * 根据对象删除数据
     *
     * @param entity
     * @return
     */
    int deleteByEntity(@Param("entity") T entity);

    /**
     * 根据多个对象批量删除数据
     *
     * @param entities
     * @return
     */
    int deleteByEntities(@Param("entities") List<? extends T> entities);

    /**
     * 根据主键更新数据
     *
     * @param entity
     * @return
     */
    int updateById(@Param("entity") T entity);

    /**
     * 批量根据主键更新数据
     *
     * @param entities
     * @return
     */
    int updateEntities(@Param("entities") List<? extends T> entities);

    /**
     * 根据数据对象更新为目标对象
     *
     * @param source
     * @param target
     * @return
     */
    int updateByEntity(@Param("entity") T source, @Param("target") T target);

    /**
     * 根据主键查询单个对象
     *
     * @param id
     * @return
     */
    T findOneById(@Param("id") Id id);

    /**
     * 根据对象查询单个对象
     *
     * @param entity
     * @return
     */
    T findOneByEntity(@Param("entity") T entity);

    /**
     * 根据map查询单个对象
     *
     * @param paramMap
     * @return
     */
    T findOneByMap(@Param("entity") Map<String, Object> paramMap);

    /**
     * 获取所有记录数
     *
     * @return
     */
    int findCount();

    /**
     * 根据对象获取所有记录数
     *
     * @param entity
     * @return
     */
    int findCountByEntity(@Param("entity") T entity);

    /**
     * 根据map获取所有记录数
     *
     * @param paramMap
     * @return
     */
    int findCountByMap(@Param("entity") Map<String, Object> paramMap);

    /**
     * 根据分页对象返回分页列表
     *
     * @param pageParam
     * @return
     */
    PageBean<T> findPage(@Param("pageParam") PageParam pageParam);

    /**
     * 根据对象和分页对象返回分页列表
     *
     * @param entity
     * @param pageParam
     * @return
     */
    PageBean<T> findPageByEntity(@Param("entity") T entity, @Param("pageParam") PageParam pageParam);

    /**
     * 根据map对象和分页对象返回分页列表
     *
     * @param paramMap
     * @param pageParam
     * @return
     */
    PageBean<T> findPageByMap(@Param("entity") Map<String, Object> paramMap, @Param("pageParam") PageParam pageParam);

    /**
     * 返回所有数据列表
     *
     * @return
     */
    List<T> findList();

    /**
     * 根据多个主键查询对象列表
     *
     * @param ids
     * @return
     */
    List<T> findListByIds(@Param("ids") List<? extends Id> ids);

    /**
     * 根据对象返回所有数据列表
     *
     * @param entity
     * @return
     */
    List<T> findListByEntity(@Param("entity") T entity);

    /**
     * 根据map对象返回所有数据列表
     *
     * @param paramMap
     * @return
     */
    List<T> findListByMap(@Param("entity") Map<String, Object> paramMap);

    /**
     * 执行原生态SQL语句进行查询并返回数据列表
     *
     * @param sql
     * @return
     */
    List<?> nativeSqlQuery(@Param("sql") String sql);

    /**
     * 执行原生态SQL语句进行更新并返回所影响行数
     *
     * @param sql
     * @return
     */
    int nativeSqlUpdate(@Param("sql") String sql);

    /**
     * 执行原生态SQL语句进行删除并返回所影响行数
     *
     * @param sql
     * @return
     */
    int nativeSqlDelete(@Param("sql") String sql);
}
