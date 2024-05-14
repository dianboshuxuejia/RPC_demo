package com.rpcgp.user.remote;

import java.util.List;

import com.rpcgp.user.model.User;
public interface UserRemote {
	public Object saveUser(User user);
	public Object saveUsers(List<User> userlist);
}

