package com.ssale.util;

import java.util.List;

import javassist.NotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HbmUtil {

	/**
	 * @param path
	 *            xml的路径
	 * 
	 * @return xml的Document.
	 */
	public static Document getDocument(String path) {
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			saxReader.setEncoding("UTF-8");
			document = saxReader.read(path);
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param path
	 *            xml的路径
	 * @param feature
	 *            DTD验证的路径,默认不进行DTD验证
	 * @return xml的Document.
	 */
	public static Document getDocument(String path, String feature) {
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			saxReader.setFeature(feature, false);
			saxReader.setEncoding("UTF-8");
			document = saxReader.read(path);
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return hibernate的配置文件Document
	 */
	private static Document getHibernateDoc() {
		String path = getRootPath() + "hibernate.cfg.xml";
		String feature = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
		return getDocument(path, feature);
	}

	/**
	 * @param clazz
	 *            需要查找xml文件Entity的Class
	 * @return Entity对应xml的Document的根元素Element
	 */
	private static Element getEntityEle(Class<?> clazz) {
		String feature = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
		Element root = getHibernateDoc().getRootElement();

		// 获取所有的映射信息,对映射信息进行比对
		@SuppressWarnings("unchecked")
		List<Element> elements = root.element("session-factory").elements(
				"mapping");
		for (Element e : elements) {
			String path = e.attribute("resource").getValue();
			if (path.endsWith(clazz.getSimpleName() + ".hbm.xml")) {
				Document document = getDocument(getRootPath() + path, feature);
				Element element = document.getRootElement();
				return element;
			}
		}

		return null;
	}

	/**
	 * @param clazz
	 *            需要处理Entity的Class
	 * @return Entity主键的属性名
	 */
	public static String getPrimaryName(Class<?> clazz) {
		Element element = getEntityEle(clazz).element("class").element("id");
		String PrimaryName = element.attributeValue("name");
		if (PrimaryName != null) {
			return PrimaryName;
		} else {
			try {
				throw new NotFoundException(clazz.getSimpleName() + "的主键属性名未找到");
			} catch (NotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * @param clazz
	 *            需要处理Entity的Class
	 * @return Entity数据库表主键列名
	 */
	public static String getPrimaryCol(Class<?> clazz) {
		Element element = getEntityEle(clazz).element("class").element("id");
		String PrimaryName = element.attributeValue("column");
		if (PrimaryName != null) {
			return PrimaryName;
		} else {
			try {
				throw new NotFoundException(clazz.getSimpleName() + "对应的数据库主键未找到");
			} catch (NotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * @param clazz
	 *            需要处理Entity的Class
	 * @param name
	 *            需要处理Entity的属性名
	 * @return Entity属性对应的列名
	 */
	@SuppressWarnings("unchecked")
	public static String getPropertyCol(Class<?> clazz, String name) {
		Element element = getEntityEle(clazz).element("class");
		// 查找属性
		List<Element> list = element.elements("property");
		for (Element e : list) {
			if (name.equals(e.attributeValue("name"))) {
				return e.attributeValue("column");
			}
		}
		// 查找多对一的关系
		list = element.elements("many-to-one");
		for (Element e : list) {
			if (name.equals(e.attributeValue("name"))) {
				return e.attributeValue("column");
			}
		}
		try {
			throw new NotFoundException(clazz.getSimpleName() + "的属性" + name
					+ "没有对应的数据库字段");
		} catch (NotFoundException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * @return 获取webroot/WEB-INF/classes/路径
	 */
	private static String getRootPath() {
		String cl = HbmUtil.class.getResource("").toString();
		String rootPath = cl.substring(0, cl.indexOf("/classes/"))
				+ "/classes/";
		return rootPath;
	}
}
