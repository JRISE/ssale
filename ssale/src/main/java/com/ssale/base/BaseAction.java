package com.ssale.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * action基础类，实现ModelDriven
 * 
 * @author Tree
 * 
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 826683513481606641L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class class1 = (Class) type.getActualTypeArguments()[0];
		try {
			this.t = (T) class1.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private T t;

	@Override
	public T getModel() {
		return this.t;
	}

	public static final String LISTACTION = "listAction";

	public static final String UPDATE_UI = "updateUI";

	public static final String CREATE_UI = "createUI";

	public static final String ACTION2ACTION = "action2action";

	public static final String INDEX = "index";

	public static final String LOGIN = "login";

	public static final String TOERROR = "toerror";

}
