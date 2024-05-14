package com.rpcgp.pro_netty_rpc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rpcgp.netty.client.ClientRequest;
import com.rpcgp.netty.user.bean.User;

public class TestTCP {
	@Test
	public void testGetResponse() {
		ClientRequest request = new ClientRequest();
		request.setContent("测试tcp长连接");
		//Response resp= TCPClient.send(request);
		//System.out.println(resp.getResult());
	}
	
	
	@Test
	public void testSaveUsers() {
		ClientRequest request = new ClientRequest();
		List<User> users = new ArrayList<User>();
		User u = new User();
		u.setId(1);
		u.setName("gao");
		users.add(u);
		request.setCommand("com.rpcgp.netty.user.controller.UseController.saveUsers");
		request.setContent(users);

		//Response resp= TCPClient.send(request);
		//System.out.println(resp.getResult());
	}
}