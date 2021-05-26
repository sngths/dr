package com.tianxing.dr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		String a= "aaa";
		a.getBytes(StandardCharsets.UTF_8);
		ByteBuffer buffer = ByteBuffer.allocateDirect(10);
		byte b = 0b110110;
		buffer.put(b);
		buffer.array();
	}


	public static void main(String[] args) {
		StringBuilder buffer = new StringBuilder();
		Map<String,String> map = new HashMap<>();
		for (int i = 0; i < 1000000; i++) {
			buffer.append("JXDH2103305752608422,1,2,3,4,5;");
		}
		System.out.println(buffer.length());
		long start = System.currentTimeMillis();
		Arrays.stream(buffer.toString().split(";"))
				.flatMap(new Function<String, Stream<String>>() {
					@Override
					public Stream<String> apply(String s) {
						return Arrays.stream(s.split(","));
					}
				}).forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				map.put(s,s);
			}
		});
		System.out.println(System.currentTimeMillis() - start);
	}


}
