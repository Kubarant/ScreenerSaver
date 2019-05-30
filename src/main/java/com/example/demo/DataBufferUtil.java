package com.example.demo;

import java.nio.ByteBuffer;
import java.util.List;

import org.springframework.core.io.buffer.DataBuffer;

public class DataBufferUtil {
	private DataBufferUtil(){}

	public static ByteBuffer dataBufferListToByteBuffer(List<DataBuffer> list) {
		DataBuffer[] array = list.toArray(new DataBuffer[list.size()]);
		
		ByteBuffer buffer = ByteBuffer.allocate(bufferArrayTotalByteSize(array));
		for (int i = 0; i < array.length; i++) {
		buffer.put(array[i].asByteBuffer());
		}
		return buffer;
	}
	
	public static int bufferArrayTotalByteSize(DataBuffer[] array ) {
		int length=0;
		for (int i = 0; i < array.length; i++) {
			length += array[i].asByteBuffer().array().length;
			
		}
		return length;
	}
	
	
}
