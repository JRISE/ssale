package com.ssale.base;

import java.io.Serializable;
import java.util.Collection;

/**
 * 项目中service层的基础类接口,在编写此类的时候,注意与dao层解耦,以便轻松实现更换数据库连接层的框架
 * 
 * @author Tree
 * 
 */
public interface BaseService<T> {
	/**
	 * 
	 * @param t
	 *            新增的对象
	 */
	public void saveEntity(T t);

	/**
	 * 
	 * @param id
	 *            删除的对象id
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
	 *            对象的id
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
	 * @Description 查询数据条数
	 */
	/**
	 * 
	 * @return 所有数据条数
	 */
	public int getAllCount();

	/**
	 * 
	 * @param ids
	 *            id的数组
	 * @return 符合条件的对象数组
	 */
	public Collection<T> getEntrysByIDS(Serializable[] ids);

}
