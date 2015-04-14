package com.yang.young.explore;

import java.lang.reflect.Constructor;

import org.junit.Assert;
import org.junit.Test;

import sun.reflect.ReflectionFactory;

@SuppressWarnings("restriction")
public class ClassWithExpensiveConstructorTest {
	
	@Test
	public void testObjectCreation() throws Exception {
		
		ClassWithExpensiveConstructor instance = (ClassWithExpensiveConstructor)
				UnSafeUtil.getUnsafeByConstructor().allocateInstance(ClassWithExpensiveConstructor.class);
		Assert.assertEquals(0, instance.getValue());
		
	}
	
	@Test
	public void testDirectIntArray() throws Exception {
		long maximum = Integer.MAX_VALUE + 1L;
		DirectIntArray directIntArray = new DirectIntArray(maximum);
		directIntArray.setValue(0L, 10);
		directIntArray.setValue(maximum, 20);
		Assert.assertEquals(10, directIntArray.getValue(0L));
		Assert.assertEquals(20, directIntArray.getValue(maximum));
		directIntArray.destroy();
	}
	
	@Test
	public void testReflectionFaction() throws Exception {
		
		@SuppressWarnings("unchecked")
		Constructor<ClassWithExpensiveConstructor> silentConstructor = (Constructor<ClassWithExpensiveConstructor>)ReflectionFactory.getReflectionFactory().newConstructorForSerialization(ClassWithExpensiveConstructor.class, Object.class.getConstructor());
		silentConstructor.setAccessible(true);
		Assert.assertEquals(0, silentConstructor.newInstance().getValue());
	}
	
	@Test
	public void testStrangeReflectionFactory() throws Exception {
		
		@SuppressWarnings("unchecked")
		Constructor<ClassWithExpensiveConstructor> silentConstructor = (Constructor<ClassWithExpensiveConstructor>)ReflectionFactory.getReflectionFactory().newConstructorForSerialization(ClassWithExpensiveConstructor.class, OtherClass.class.getDeclaredConstructor());
		silentConstructor.setAccessible(true);
		ClassWithExpensiveConstructor instance = silentConstructor.newInstance();
		Assert.assertEquals(10, instance.getValue());
		Assert.assertEquals(ClassWithExpensiveConstructor.class, instance.getClass());
		Assert.assertEquals(Object.class, instance.getClass().getSuperclass());
	}
	
	@Test
	public void testMallaciousAllocation() throws Exception {
		long address = UnSafeUtil.getUnsafeByConstructor().allocateMemory(2L*4);
		UnSafeUtil.getUnsafeByConstructor().setMemory(address, 8L, (byte)0);
		Assert.assertEquals(0, UnSafeUtil.getUnsafeByConstructor().getInt(address));
		Assert.assertEquals(0, UnSafeUtil.getUnsafeByConstructor().getInt(address + 4));
		UnSafeUtil.getUnsafeByConstructor().putInt(address + 1, 0xffffffff);
		Assert.assertEquals(0xffffff00, UnSafeUtil.getUnsafeByConstructor().getInt(address));
		Assert.assertEquals(0x000000ff, UnSafeUtil.getUnsafeByConstructor().getInt(address + 4));
	}


}
