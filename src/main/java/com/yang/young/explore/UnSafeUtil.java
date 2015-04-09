package com.yang.young.explore;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnSafeUtil {

	public static void main(String[] args) throws Throwable {
		Unsafe unsafe = getUnsafeByField();
		unsafe = getUnsafeByConstructor();
		System.out.println(unsafe);
	}
	
	public static Unsafe getUnsafeByField() {
		Unsafe unsafe = null;
		Field theUnsafe;
		try {
			theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			unsafe = (Unsafe)theUnsafe.get(null);
		} catch (Exception ex) {}
		
		return unsafe;
	}
	
	public static Unsafe getUnsafeByConstructor() {
		Unsafe unsafe = null;
		try {
			Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
			unsafeConstructor.setAccessible(true);
			unsafe = unsafeConstructor.newInstance();
		} catch (Exception ex) {}
		return unsafe;
	}
	
	
}
