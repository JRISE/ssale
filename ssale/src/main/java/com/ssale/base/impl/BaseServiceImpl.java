package com.ssale.base.impl;

import java.io.Serializable;
import java.util.Collection;

import com.ssale.base.BaseDao;
import com.ssale.base.BaseService;

/**
 * 项目中service层的impl实现类,在编写此类的时候,注意与dao层解耦,以便轻松实现更换数据库连接层的框架
 * 
 * @author Tree
 * 
 */
public class BaseServiceImpl<T, K extends BaseDao<T>> implements BaseService<T> {

	private K baseDao;

	public void setBaseDao(K baseDao) {
		this.baseDao = baseDao;
	}

	public K getBaseDao() {
		return baseDao;
	}

	@Override
	public void saveEntity(T t) {
		this.getBaseDao().saveEntity(t);
	}

	@Override
	public void deleteEntity(Serializable id) {
		this.getBaseDao().deleteEntity(id);
	}

	@Override
	public void updateEntity(T t) {
		this.getBaseDao().updateEntity(t);
	}

	@Override
	public Collection<T> getAllEntity() {
		return this.getBaseDao().getAllEntity();
	}

	@Override
	public T getEntityById(Serializable id) {
		return this.getBaseDao().getEntityById(id);
	}

	@Override
	public Collection<T> getEntityPerPage(int firstResult, int maxResults) {
		return this.getBaseDao().getEntityPerPage(firstResult, maxResults);
	}

	@Override
	public int getAllCount() {
		return this.getBaseDao().getAllCount();
	}

	@Override
	public Collection<T> getEntrysByIDS(Serializable[] ids) {
		Collection<T> collection = this.getBaseDao().getEntrysByIDS(ids);
		return collection;
	}

}
