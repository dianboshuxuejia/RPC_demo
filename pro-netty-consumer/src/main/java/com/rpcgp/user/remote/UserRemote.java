package com.rpcgp.user.remote;

import java.util.List;

import com.rpcgp.client.param.Response;
import com.rpcgp.user.bean.User;



public interface UserRemote {
	public Response saveUser(User user);
	public Response saveUsers(List<User> userlist);
}

