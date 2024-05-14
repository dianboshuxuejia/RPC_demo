package com.rpcgp.netty.medium;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.rpcgp.netty.handler.param.ServerRequest;
import com.rpcgp.netty.util.Response;

public class Media {
	public static Map<String, BeanMethod>beanMap;
	static {
		beanMap = new HashMap<String,BeanMethod>();
	}
	
	private static Media m=null;
	private Media() {
		
	}//在单例模式中，将构造方法私有化的目的是为了防止外部直接实例化该类，从而确保只能通过类内部的机制来控制对象的创建。
	
	public static Media newInstance() {
		if (m==null) {
			m=new Media();
			
		}
		
		return m;
	}

	
	//反射处理业务
	public Response process(ServerRequest request) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Response result= null;
		try {
			String command =request.getCommand();
			BeanMethod beanMethod = beanMap.get(command);
			if (beanMethod==null) {
				return null;
			}
			
			Object bean=beanMethod.getBean();
			Method m = beanMethod.getMethod();
			Class paramType= m.getParameterTypes()[0];//获取方法 m 的第一个参数的类型，然后将其存储在 paramTypeClass 变量中
			Object content=request.getContent();
			
			Object args=JSONObject.parseObject(JSONObject.toJSONString(content),paramType);
			result = (Response) m.invoke(bean, args); 
			result.setId(request.getId());
//					表示通过反射调用了对象 bean 的方法 m，
//					并传递了参数 args，方法的返回值被存储在 result 变量中。
//					这种方式使得在运行时动态调用方法成为可能，而不需要在编译时确定具体的方法和参数。

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
