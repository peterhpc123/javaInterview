package com.example.jdkproxy;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.util.Proxys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class JdkProxyApplication {

	interface Foo{
		void foo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
		void bar();
	}
	interface InvocationHandler{
		void invoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
	}
	static class Target implements Foo{

		@Override
		public void foo() {
			System.out.println("This is target class!");
		}

		@Override
		public void bar() {
			System.out.println("This is bar method");
		}
	}
	public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//		Proxy p=new Proxy(new InvocationHandler() {
//			@Override
//			public void invoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
//				System.out.println("before target method ...");
//				method.invoke(new Target(),new Object[0]);
//			}
//		});
//		p.foo();
//		p.bar();

		java.net.Proxy proxy= Proxys.http("jp.60cdn.com",10321);
		ChatGPT chatGPT=ChatGPT.builder()
				.apiKey("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")
				.proxy(proxy)
				.build()
				.init();
		String res=chatGPT.chat("如何找到女朋友");
		System.out.println(res);

	}

}
