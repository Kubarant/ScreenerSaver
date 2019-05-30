package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ImageGetServer extends WebSocketServer{
	
	public ImageGetServer() {
		super( new InetSocketAddress("localhost",1700));
	
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		
	}
	@Override
	public void onMessage(WebSocket conn, ByteBuffer message) {
		System.out.println("Coś tu jest");
		super.onMessage(conn, message);
		try {
			new File("C:\\Users\\Kuba\\Desktop\\image.png").createNewFile();
			FileOutputStream stream = new FileOutputStream("C:\\Users\\Kuba\\Desktop\\image.png");
			stream.write(message.array());
			stream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		
	}

	@Override
	public void onStart() {
		
	}



}
