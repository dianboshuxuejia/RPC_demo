package com.rpcgp.user.remote;

import java.util.List;

import javax.annotation.Resource;

import com.rpcgp.netty.annotation.Remote;
import com.rpcgp.netty.user.service.UserService;
import com.rpcgp.netty.util.ResponseUtil;
import com.rpcgp.user.model.User;
@Remote
public class UserRemoteImpl implements UserRemote{
	
	@Resource
	private UserService userservice;
	
	public Object saveUser(User user) {
		UserService.save(user);
		
		return ResponseUtil.createSuccessResult(user);
		
	}
	public Object saveUsers(List<User> users) {
		UserService.saveList(users);
		
		return ResponseUtil.createSuccessResult(users);
		
	}

}
