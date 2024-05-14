package com.rpcgp.netty.sever;

import com.alibaba.fastjson.JSONObject;
import com.rpcgp.netty.handler.param.ServerRequest;
import com.rpcgp.netty.util.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		System.out.println("sever ctx=" + ctx);
//		ByteBuf buf = (ByteBuf) msg;
//		System.out.println("客户端消息" + buf.toString(CharsetUtil.UTF_8));
//		ctx.channel().writeAndFlush("is ok\r\n");
		ServerRequest request= JSONObject.parseObject(msg.toString(),ServerRequest.class);
		Response resp= new Response();
		resp.setId(request.getId());
		resp.setResult("is ok simpleserverhandler");
		ctx.channel().writeAndFlush(JSONObject.toJSONString(resp));
		ctx.channel().writeAndFlush("\r\n");

	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;
			if (event.state().equals(IdleState.READER_IDLE)) {
				System.out.println("ReadIdleState===");
				ctx.channel().close();}
			else if(event.state().equals(IdleState.WRITER_IDLE)){
				System.out.println("WriteIdleState===");	
				}
			else if(event.state().equals(IdleState.ALL_IDLE)){
				
				System.out.println("Writeand READIdleState===");
				ctx.channel().writeAndFlush("ping\r\n");
					
				}
			}
			
		}
	}
	

//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		// TODO Auto-generated method stub
//		ctx.writeAndFlush(Unpooled.copiedBuffer("hello,client",CharsetUtil.UTF_8));
//		
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		// TODO Auto-generated method stub
//		ctx.close();
//	}
	
	

