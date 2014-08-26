package com.ssale.base;

import java.io.Serializable;
import java.util.Collection;

/**
 * 使用hibernate框架时,DAO基础类接口,所有Dao接口继承此类
 * 
 * @author Tree
 * 
 */
public interface BaseDao<T> {

	/**
	 * 
	 * @param t
	 *            新增的对象
	 */
	public void saveEntity(T t);
	
	/**
	 * 
	 * @param t 新增或者更新
	 */
	public void saveOrUpdateEntity(T t);

	/**
	 * 
	 * @param id
	 *            删除对象的id
	 */
	public void deleteEntity(Serializable id);

	/**
	 * 
	 * @param t
	 *            更新的对象
	 */
	public void updateEntity(T t);

	/**
	 * 
	 * @param id
	 *            通过id查询对象
	 * @return
	 */
	public T getEntityById(Serializable id);

	/**
	 * 
	 * @param firstResult
	 *            从第firstResult条数据开始
	 * @param maxResults
	 *            最大显示maxResults条数据
	 * @return 范围内对象的集合
	 */
	public Collection<T> getEntityPerPage(int firstResult, int maxResults);

	/**
	 * 
	 * @return 所有对象的集合
	 */
	public Collection<T> getAllEntity();

	/**
	 * 
	 * @return 所有数据的条数
	 */
	public int getAllCount();

	/**
	 * 
	 * @param ids
	 *            id的数组
	 * @return 根据id查找的对象集合
	 */
	public Collection<T> getEntrysByIDS(Serializable[] ids);


}
