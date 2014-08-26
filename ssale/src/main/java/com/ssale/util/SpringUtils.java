package com.ssale.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
	public ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
}
