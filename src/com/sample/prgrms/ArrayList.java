package com.sample.prgrms;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayList<T> implements Iterable<T>{

	private Object[] objects;
	private int size;
    private static final int DEFAULT_CAPACITY = 10; 
    
    ArrayList() {
    	objects = new Object[DEFAULT_CAPACITY];
    	size = 0;
    }
    
    
    
	@SuppressWarnings("unchecked")
	public Stream<T> stream() {
		
		return Arrays.stream(objects, 0, size) .map(object -> (T)object);
	}
    
    public void add(T data) {
    	if(size >= objects.length) {
    		Object[] temp = new Object[objects.length*2];
    		for(int i = 0; i < objects.length; i++) {
    			temp[i] = objects[i];
    			size++;
    		}
    		objects = temp;
    	}
    	objects[size++] = data;
    }
    
    public void add(int index, T data) {
    	
    	if(index < 0 || index >= size)
    		throw new ArrayIndexOutOfBoundsException();
    	
    	if(size >= objects.length) {
    		objects = increaseCapacity();
    	}
    	
    	for(int i = size-1; i >= index; i--) {
    		objects[i+1] = objects[i];
    	}
    	
    	objects[index] = data;
    	size++;
    }
    
	private Object[] increaseCapacity() {
		Object[] temp = new Object[objects.length * 2];
		for (int i = 0; i < objects.length; i++) {
			temp[i] = objects[i];
			size++;
		}
		return temp;
	}
	
    public void remove(int index) {
    	
    	if(index < 0 || index >= size)
    		throw new ArrayIndexOutOfBoundsException();
    	
    	for(int i = index+1; i < objects.length; i++) {
    		objects[i-1] = objects[i];
    	}
    }
    
    @SuppressWarnings("unchecked")
	public T get(int index) {
    	
    	if(index < 0 || index >= size)
    		throw new ArrayIndexOutOfBoundsException();
    	
    	Object o = objects[index];
    	T data;
    	
    	if(o != null) {
    		try {
    			data = (T)o;
    		}catch (Exception e) {
				throw new ClassCastException();
			}
    	}else {
    		data = null;
    	}
    	
    	return data;
    }
    
    public void clear() {
    	objects = new Object[DEFAULT_CAPACITY];
    	size = 0;
    }
    
    public int size() {
    	return size;
    }
    
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		if(size == 0) {
			stringBuffer.append("[]");
		}else {
			stringBuffer.append("[");
			stringBuffer.append(objects[0]);
			for (int i = 1; i < size; i++) {
				stringBuffer.append(", "+objects[i]);
			}
			stringBuffer.append("]");
		}
		return stringBuffer.toString();
	}



	
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T> {

		private int cursor = 0;
		
		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			T data = (T)objects[cursor];
			cursor++;
			return data;
		}
		
	}
}
