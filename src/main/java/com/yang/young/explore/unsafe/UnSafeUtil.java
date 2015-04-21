package com.yang.young.explore.unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
	
	public static long sizeOf(Class<?> clazz) {
		long maximumOffset = 0;
		do{
			for(Field f : clazz.getDeclaredFields()) {
				if(!Modifier.isStatic(f.getModifiers())) {
					maximumOffset = Math.max(maximumOffset, getUnsafeByConstructor().objectFieldOffset(f));
				}
			}
		} while ((clazz = clazz.getSuperclass()) != null) ;
		
		return maximumOffset + 8;
	}
	
	public static Object read(Class clazz, long address) throws Exception {
		
		
		return null;
	}
	
	public static void place(Object o, long address) throws Exception {
		Class<?> clazz = o.getClass();
		Unsafe unsafe = getUnsafeByConstructor();
		do {
			for(Field f : clazz.getDeclaredFields()) {
				if(!Modifier.isStatic(f.getModifiers())) {
					long offset = unsafe.objectFieldOffset(f);
					if(f.getType() == long.class) {
						unsafe.putLong(address + offset, unsafe.getLong(o, offset));
					} else if(f.getType() == int.class) {
						unsafe.putInt(address + offset, unsafe.getInt(o, offset));
					} else {
						throw new UnsupportedOperationException();
					}
				}
			}
		} while((clazz = clazz.getSuperclass()) != null);
	}
	
}
