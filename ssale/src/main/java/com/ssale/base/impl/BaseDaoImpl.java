package com.ssale.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssale.base.BaseDao;
import com.ssale.util.HbmUtil;
import com.ssale.util.TreeUtil;

/**
 * 浣跨敤hibernate妗嗘灦鏃�鍩虹DAO鐨勫疄鐜扮被,鍏朵粬DAO鐨刬mpl闇�缁ф壙姝ょ被
 * 
 * @author Tree
 * 
 */
public class BaseDaoImpl<T> extends HibernateTemplate implements BaseDao<T> {

	private Class<?> clazz;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<?>) type.getActualTypeArguments()[0];
	}

	@Override
	public void saveEntity(T t) {
		if (t != null) {
			this.save(t);
		}
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		this.saveOrUpdate(t);
	}

	@Override
	public void deleteEntity(Serializable id) {
		Object t = this.get(this.clazz, id);
		if (t != null) {
			this.delete(t);
		}
	}

	@Override
	public void updateEntity(T t) {
		if (t != null)
			this.update(t);
	}

	@Override
	public T getEntityById(Serializable id) {
		@SuppressWarnings("unchecked")
		T t = (T) this.get(this.clazz, id);
		return t;
	}

	@Override
	public Collection<T> getEntityPerPage(int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(this.clazz);
		criteria.addOrder(Order.asc("id"));
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.findByCriteria(criteria, firstResult, maxResults);
		return list;
	}

	@Override
	public Collection<T> getAllEntity() {
		@SuppressWarnings("unchecked")
		List<T> collection = (List<T>) this.find("from " + this.clazz.getName() + " clazz order by clazz.id");
		return collection;
	}

	@Override
	public int getAllCount() {
		String sql = "select count(" + HbmUtil.getPrimaryName(this.clazz) + ") from "
				+ this.clazz.getName();
		Query query = this.getSession().createQuery(sql);
		int count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public Collection<T> getEntrysByIDS(Serializable[] ids) {
		String hql = "from " + this.clazz.getName() + " where "
				+ HbmUtil.getPrimaryName(this.clazz) + " in(" + TreeUtil.arrays2String(ids) + ")";
		// 鎷兼帴hql
		@SuppressWarnings("unchecked")
		List<T> set = (List<T>) this.find(hql);
		System.out.println("dao");
		return set;
	}

	/**
	 * 
	 * @param sql
	 *            閫氳繃sql鏉′欢鏌ヨ瀵硅薄,鏀寔? 渚嬪 id==99 or id==999
	 * @param value
	 *            sql涓�瀵瑰簲鐨勬潯浠�
	 * @return 绗﹀悎鏉′欢鐨勬暟鎹潯鏁�
	 */
	protected int getCount(String sql, Serializable... value) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(" + HbmUtil.getPrimaryName(this.clazz) + ") from "
				+ this.clazz.getSimpleName() + " where " + sql);
		for (Object o : value) {
			if (o instanceof String) {
				sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, "'" + o + "'");
			} else {
				sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, o.toString());
			}
		}

		Query query = this.getSession().createQuery(sb.toString());
		int count = ((Long) query.uniqueResult()).intValue();
		return count;
	}

	/**
	 * 
	 * @param sql
	 *            閫氳繃sql鏉′欢鏌ヨ,鏀寔? 渚嬪 id=? or id=999
	 * @param value
	 *            sql涓�瀵瑰簲鐨勬潯浠�
	 * @return 绗﹀悎鏉′欢鐨勬暟鎹�
	 */
	protected Collection<T> getEntityByHQL(String sql, Object... value) {
		// 鎷兼帴sql
		StringBuffer sb = new StringBuffer();
		sb.append("from " + this.clazz.getName() + " where " + sql);
		for (Object o : value) {
			if (o instanceof String) {
				sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, "'" + o + "'");
			} else {
				sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, o.toString());
			}
		}

		// 鎵цhql璇彞
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.find(sb.toString());
		return list;
	}

	/**
	 * 
	 * @param sql
	 *            閫氳繃sql鏉′欢鏌ヨ,鏀寔? 渚嬪 id=? or id=999
	 * @param name
	 *            鎸夌収name灞炴�鍗囧簭鎺掑垪
	 * @param firstResult
	 *            浠庣firstResult鏉℃暟鎹紑濮�
	 * @param maxResults
	 *            鏈�ぇ鏄剧ずmaxResults鏉℃暟鎹�
	 * @param value
	 *            sql涓�瀵瑰簲鐨勬潯浠�
	 * @return
	 */
	protected Collection<T> getPageByHQLAsc(String sql, String name, int firstResult,
			int maxResults, Object... value) {
		DetachedCriteria criteria = getCriteria(sql, value);
		criteria.addOrder(Order.asc(name));
		@SuppressWarnings("unchecked")
		List<T> collection = (List<T>) this.findByCriteria(criteria, firstResult, maxResults);
		return collection;
	}

	/**
	 * 
	 * @param sql
	 *            閫氳繃sql鏉′欢鏌ヨ,鏀寔? 渚嬪 id=? or id=999
	 * @param name
	 *            鎸夌収name灞炴�鍗囧簭鎺掑垪
	 * @param firstResult
	 *            浠庣firstResult鏉℃暟鎹紑濮�
	 * @param maxResults
	 *            鏈�ぇ鏄剧ずmaxResults鏉℃暟鎹�
	 * @param value
	 *            sql涓�瀵瑰簲鐨勬潯浠�
	 * @return
	 */
	protected Collection<T> getPageByHQLDesc(String sql, String name, int firstResult,
			int maxResults, Object... value) {
		DetachedCriteria criteria = getCriteria(sql, value);
		criteria.addOrder(Order.desc(name));
		@SuppressWarnings("unchecked")
		List<T> collection = (List<T>) this.findByCriteria(criteria, firstResult, maxResults);
		return collection;
	}

	/**
	 * 
	 * @param sql
	 *            閫氳繃sql鏉′欢鏌ヨ瀵硅薄,鏀寔? 渚嬪 id=? or id=999
	 * @param value
	 *            sql涓�瀵瑰簲鐨勬潯浠�
	 * @return
	 */
	private DetachedCriteria getCriteria(String sql, Object... value) {
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		for (Object o : value) {
			if (o instanceof String) {
				sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, "'" + o + "'");
			} else {
				sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, o.toString());
			}
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(this.clazz);
		criteria.add(Restrictions.sqlRestriction(sb.toString()));
		return criteria;
	}

}
