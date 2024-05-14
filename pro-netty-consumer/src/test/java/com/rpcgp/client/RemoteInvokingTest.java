package com.rpcgp.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rpcgp.client.annotation.RemoteInvoke;
import com.rpcgp.user.bean.User;
import com.rpcgp.user.remote.TestRemote;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RemoteInvokingTest.class)
@ComponentScan("com.rpcgp.pro_netty_rpc")
public class RemoteInvokingTest {

	@RemoteInvoke
	public static TestRemote userRemote;
	public static User user;
	
	static{
		
		user = new User();
		user.setId(1000);
		user.setName("张三");
	}
	@Test
	public void testSaveUser() {
		//User user = new User();
		user.setId(100);
		user.setName("gao");
		userRemote.testUser(user);
		System.out.println(user.getName());
	}
	

}
