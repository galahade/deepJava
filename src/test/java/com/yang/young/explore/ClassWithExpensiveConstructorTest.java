package com.yang.young.explore;

import org.junit.Assert;
import org.junit.Test;
@SuppressWarnings("restriction")
public class ClassWithExpensiveConstructorTest {
	
	@Test
	public void testObjectCreation() throws Exception {
		
		ClassWithExpensiveConstructor instance = (ClassWithExpensiveConstructor)
				UnSafeUtil.getUnsafeByConstructor().allocateInstance(ClassWithExpensiveConstructor.class);
		Assert.assertEquals(0, instance.getValue());
	}

}
