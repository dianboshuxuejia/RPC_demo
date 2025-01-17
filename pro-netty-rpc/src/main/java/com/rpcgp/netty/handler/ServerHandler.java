package com.rpcgp.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.rpcgp.netty.handler.param.ServerRequest;
import com.rpcgp.netty.medium.Media;
import com.rpcgp.netty.util.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		System.out.println("sever ctx=" + ctx);
//		ByteBuf buf = (ByteBuf) msg;
//		System.out.println("客户端消息" + buf.toString(CharsetUtil.UTF_8));
//		ctx.channel().writeAndFlush("is ok\r\n");
		ServerRequest request= JSONObject.parseObject(msg.toString(),ServerRequest.class);
		
		
		Media media= Media.newInstance(); 
		Response result = media.process(request);
		
		
		//response是通信对象，在serverhandler中先new一个，作为响应客户端消息的对象
		//然后用它接受request里客户端和服务器传输的数据，
		//通过ServerRequest request= JSONObject.parseObject(msg.toString(),ServerRequest.class);
		//将msg转化为request
		//服务器处理的数据，返回为request的result，
		
		
//		Response resp= new Response();
//		resp.setId(request.getId());
//		resp.setResult("is ok sever handler");
		
		
		
		ctx.channel().writeAndFlush(JSONObject.toJSONString(result));
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
	
	

