package com.example.jdkproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class JdkProxyApplication {

	interface Foo{
		void foo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
	}
	interface InvocationHandler{
		void invoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
	}
	static class Target implements Foo{

		@Override
		public void foo() {
			System.out.println("This is target class!");
		}
	}
	public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		Proxy p=new Proxy(new InvocationHandler() {
			@Override
			public void invoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
				System.out.println("before target method ...");
				method.invoke(new Target(),new Object[0]);
			}
		});
		p.foo();
	}

}
