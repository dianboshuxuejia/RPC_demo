package com.rpcgp.netty.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.rpcgp.netty.user.bean.User;
import com.rpcgp.netty.user.service.UserService;
import com.rpcgp.netty.util.Response;
import com.rpcgp.netty.util.ResponseUtil;
@Controller
public class UseController {
	
	@Resource
	private UserService userservice;
	
	public Response saveUsers(List<User> users) {
		UserService.saveList(users);
		
		return ResponseUtil.createSuccessResult(users);
		
	}
	


}
