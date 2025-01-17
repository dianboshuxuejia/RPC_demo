package com.rpcgp.netty.medium;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.rpcgp.netty.annotation.Remote;

@Component
public class InitialMedium implements BeanPostProcessor{
//	在Spring中，如果一个类没有显式定义构造器，Spring容器仍然可以通过默认构造器实例化该类。
//	所以，即使 InitialMedium 没有显式的构造器，Spring容器仍然可以成功实例化它。
//	在Spring框架中，使用 @Component 注解标记的类，如 InitialMedium，会由Spring容器负责实例化和管理。实例化的过程如下：
//
//	扫描： 在应用程序启动时，Spring容器会扫描类路径或指定的包，寻找被注解标记的类。
//
//	识别注解： 如果找到了被 @Component 标记的类，Spring容器会将其识别为一个候选的Bean。
//
//	创建实例： Spring容器使用反射机制创建被注解标记类的实例（对象）。
//
//	注册到容器： 创建的实例会被注册到Spring容器中，成为Spring容器所管理的Bean。
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean.getClass().isAnnotationPresent(Remote.class)) {
			//System.out.println(bean.getClass().getName());
			Method[] methods= bean.getClass().getDeclaredMethods();
			for(Method m:methods) {
				String key = bean.getClass().getInterfaces()[0].getName()+"."+m.getName();
				Map<String, BeanMethod> beanMap=Media.beanMap;
				BeanMethod BeanMethod = new BeanMethod();
				BeanMethod.setBean(bean);
				BeanMethod.setMethod(m);
				beanMap.put(key, BeanMethod );
			}
			
		}
		return bean;
	}
	
	
	

}
