package com.ssale.util;

import java.io.File;

/**
 * 判断是否符合条件 如果符合条件则返回true 如果不符合条件则在控制台打印信息,并返回false;
 * 
 */
public class TreeJudgement {

	/**
	 * 判断所有的对象是否为非空,是要一个为空,则返回false; 字符串则判断是否为null或者为"";
	 * 
	 * @param object
	 *            目标对象
	 * @return 如果为false,控制台进行提示
	 */
	@SuppressWarnings("unused")
	public static boolean isNotNull(Object... object) {
		for (Object o : object) {
			// 判断是否为String
			if (o instanceof String) {
				if (o == null || "".equals(o)) {
					if (o == null) {
						System.out.println("错误:字符串为null");
						return false;
					} else {
						System.out.println("错误:字符串为\"\"");
						return false;

					}
				}
			}

			if (o == null) {
				System.out.println("错误:对象为空");
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断文件是否存在,只要有一个路径的文件不存在,则返回false
	 * 
	 * @param path
	 *            文件路径
	 * @param message
	 *            抛出的异常信息
	 * @return 如果为false,控制台提示
	 */
	public static boolean isFileExists(String... path) {
		for (String s : path) {
			if (!new File(s).exists()) {
				System.out.println("错误:找不到指定的文件");
				return false;
			}
		}
		return true;
	}
}
