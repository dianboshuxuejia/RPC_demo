package com.rpcgp.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.rpcgp.netty.client.DefaultFuture;
import com.rpcgp.netty.util.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if ("ping".equals(msg.toString())) {
			ctx.channel().writeAndFlush("ping\r\n");
			return ;
			
		}

		ctx.channel().attr(AttributeKey.valueOf("sssss")).set(msg);
		Response response = JSONObject.parseObject(msg.toString(),Response.class);
		//将 msg.toString() 这个 JSON 字符串解析成 Response 类型的对象，并将结果赋给 response 变量
		System.out.println("接收服务器返回数据"+JSONObject.toJSONString(response));
		DefaultFuture.receive(response);
		System.out.println("receive the info from server: " + msg.toString());
		//ctx.channel().close();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		super.userEventTriggered(ctx, evt);
	}


	
	}
	
