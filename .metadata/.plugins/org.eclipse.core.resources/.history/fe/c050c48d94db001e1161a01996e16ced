package com.rpcgp.netty.user.remote;

import java.util.List;

import javax.annotation.Resource;

import com.rpcgp.netty.annotation.Remote;
import com.rpcgp.netty.user.bean.User;
import com.rpcgp.netty.user.service.UserService;
import com.rpcgp.netty.util.Response;
import com.rpcgp.netty.util.ResponseUtil;

@Remote
public class UserRemoteImpl implements UserRemote{
	
	@Resource
	private UserService userservice;
	
	public Response saveUser(User user) {
		UserService.save(user);
		
		return ResponseUtil.createSuccessResult(user);
		
	}
	public Response saveUsers(List<User> users) {
		UserService.saveList(users);
		
		return ResponseUtil.createSuccessResult(users);
		
	}

}
