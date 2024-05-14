package com.rpcgp.user.remote;

import com.rpcgp.client.param.Response;
import com.rpcgp.user.bean.User;

public interface TestRemote {
	public Response testUser(User user);
}