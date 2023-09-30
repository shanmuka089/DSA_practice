package com.sample.prgrms;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class SinglyLinkedList<T> implements Iterable<T>{
	
	private ListNode<T> headNode;
	private int count;
	
	private static class ListNode<T>{
		
		private T data;
		private ListNode<T> nextNode;
		
		ListNode(T data, ListNode<T> nextNode) {
			this.data = data;
			this.nextNode = nextNode;
		}
	}
	
	public T add(T data) {
		if(headNode == null) {
			headNode = new ListNode<T>(data, null);
			count = 1;
			return data;
		}
		
		ListNode<T> currentNode =headNode;
		while(currentNode.nextNode != null) {
			currentNode = currentNode.nextNode;
		}
		
		currentNode.nextNode = new ListNode<T>(data, null);
		count++;
		
		return data;
	}
	
	public void add(int index, T data) {
		if(index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			headNode = new ListNode<T>(data, headNode);
			count++;
		}else {
			ListNode<T> currentNode = headNode;
			for(int i = 1; i < index; i++) {
				currentNode = currentNode.nextNode;
			}
			
			 currentNode.nextNode = new ListNode<T>(data, currentNode.nextNode);
			 count++;
		}
	}
	
	public void clear() {
		headNode = null;
		count = 0;
	}
	
	public void remove(int index) {
		if(index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			headNode = headNode.nextNode;
			count--;
			return;
		}else {
			
			ListNode<T> currentNode = headNode;
			
			for(int i = 1; i < index; i++) {
				currentNode = currentNode.nextNode;
			}
			
			currentNode.nextNode = currentNode.nextNode.nextNode;
			count--;
		}
	}

	/*
	 * 	headNode: This is the initial value provided to start the iteration.
	 *	Objects::nonNull: This is the second argument to Stream.iterate(). It's a predicate (a function that returns a boolean value) that determines whether the stream should continue or not. In this case, it checks if the current node is not null.
	 *	node -> node.nextNode: This is the third argument to Stream.iterate(). It's a function that generates the next element in the stream. In this case, it takes a node and returns its nextNode.
	 *	This means that the stream will continue as long as the nextNode of the current node is not null.
	 * 	map(node -> node.data): This is an intermediate operation on the stream. It transforms each element in the stream by applying the given function (node -> node.data). This function extracts the data from each node.
	 *	This means that the resulting stream will contain the data of each node.
	 */
	public Stream<T> stream() {
		return Stream.iterate(headNode, Objects::nonNull, node -> node.nextNode)
				.map(node -> node.data);
	}
	
	public int size() {
		return this.count;
	}
	
	public T get(int index) {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}

		ListNode<T> currentNode = headNode;

		for (int i = 1; i <= index; i++) {
			currentNode = currentNode.nextNode;
		}

		return currentNode.data;
	}
	
	@Override
	public String toString() {
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[");
		ListNode<T> currentNode = headNode;
		if(currentNode != null) {
			while(currentNode != null) {
				if(currentNode.nextNode != null) {
					stringBuffer.append((currentNode.data)+ ", ");
				}else {
					stringBuffer.append((currentNode.data));
				}
				
				currentNode = currentNode.nextNode;
			}
		}
		stringBuffer.append("]");
		
		return stringBuffer.toString();
	}

	
	@Override
	public Iterator<T> iterator() {
		return new MyListIterator();
	}
	
	private class MyListIterator implements Iterator<T> {

		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < count;
		}

		@Override
		public T next() {
			ListNode<T> currentNode = headNode;

			for (int i = 1; i <= index; i++) {
				currentNode = currentNode.nextNode;
			}
			
			index++;
			return currentNode.data;
		}
		
	}
}
