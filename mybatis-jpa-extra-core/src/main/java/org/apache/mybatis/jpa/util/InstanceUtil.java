/**
 * 
 */
package org.apache.mybatis.jpa.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Crystal
 *
 */
public class InstanceUtil {

	/**
	 * 
	 */
	public InstanceUtil() {
	}


	public static Object newInstance(String className) {
		Class<?> cls;
		try {
			cls = Class.forName(className);
			Constructor<?> constructor = cls.getConstructor();
			return constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Object newInstance(Class<?> cls) {
		try {
			Constructor<?> constructor = cls.getConstructor();
			return constructor.newInstance();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object newInstance(String className, Object[] args) {
		Class<?> newClass;
		try {
			newClass = Class.forName(className);
			Class[] argsClass = new Class[args.length];

			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}

			Constructor<?> cons = newClass.getConstructor(argsClass);
			return cons.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;

	}

	@SuppressWarnings("rawtypes")
	public static <T> Object newInstance(Class<T> cls, Object[] args) {
		try {
			Class[] argsClass = new Class[args.length];

			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}

			Constructor<T> cons = cls.getConstructor(argsClass);
			return cons.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	

	@SuppressWarnings("rawtypes")
	public static Object invokeMethod(Object bean, String methodName,Object[] args) throws Exception {
		Class<? extends Object> beanClass = bean.getClass();
		Class[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
			//LogFactory.getLog(MethodInvoke.class).debug("invokeMethod args : "+args[i]+" argsClass:"+argsClass[i]);
		}

		Method method = beanClass.getMethod(methodName, argsClass);
		//LogFactory.getLog(MethodInvoke.class).debug("invokeMethod methodName:"+methodName);
		return method.invoke(bean, args);
	}

	public static Object invokeMethod(Object bean, String methodName)throws Exception {
		Class<? extends Object> beanClass = bean.getClass();
		Method method = beanClass.getMethod(methodName);
		//LogFactory.getLog(MethodInvoke.class).debug("invokeMethod methodName:"+methodName);
		return method.invoke(bean, new Object[] {});
	}

	@SuppressWarnings("rawtypes")
	public static Object invokeStaticMethod(Class<?> beanClass, String methodName,Object[] args) throws Exception {
		Class[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
			//LogFactory.getLog(MethodInvoke.class).debug("invokeStaticMethod args : "+args[i]+" argsClass:"+argsClass[i]);
		}

		Method method = beanClass.getMethod(methodName, argsClass);
		//LogFactory.getLog(MethodInvoke.class).debug("invokeStaticMethod methodName:"+methodName);
		return method.invoke(null, args);
	}

	public static Object invokeStaticMethod(Class<?> beanClass, String methodName)
			throws Exception {
		Method method = beanClass.getMethod(methodName);
		//LogFactory.getLog(MethodInvoke.class).debug("invokeStaticMethod methodName:"+methodName);
		return method.invoke(null, new Object[] {});
	}
}
