package com.ssale.test;

import org.junit.Test;

import com.ssale.util.SpringUtils;

public class SessionFactoryTest extends SpringUtils {

	@Test
	public void testSessionFactory() {
		context.getBean("sessionFactory");
	}
}
