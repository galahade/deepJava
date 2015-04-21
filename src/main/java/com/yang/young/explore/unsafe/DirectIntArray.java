package com.yang.young.explore.unsafe;
@SuppressWarnings("restriction")
public class DirectIntArray {
	
	private final static long INT_SIZE_IN_BYTES = 4;
	
	private final long startIndex;
	
	
	public DirectIntArray(long size) {
		startIndex = UnSafeUtil.getUnsafeByConstructor().allocateMemory(size * INT_SIZE_IN_BYTES);
		UnSafeUtil.getUnsafeByConstructor().setMemory(startIndex, size*INT_SIZE_IN_BYTES, (byte)0);
	}
	
	public void setValue(long index, int value) {
		UnSafeUtil.getUnsafeByConstructor().putInt(index(index), value);
	}
	
	public int getValue(long index) {
		return UnSafeUtil.getUnsafeByConstructor().getInt(index(index));
	}
	
	private long index(long offset) {
		return startIndex + offset * INT_SIZE_IN_BYTES;
	}
	
	public void destroy() {
		UnSafeUtil.getUnsafeByConstructor().freeMemory(startIndex);
	}

}
