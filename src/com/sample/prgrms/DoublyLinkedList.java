package com.sample.prgrms;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

public class DoublyLinkedList<T> implements Iterable<T> {

	private DoublyListNode<T> firstNode;

	private DoublyListNode<T> lastNode;

	private static int count;

	private static class DoublyListNode<T> {

		private DoublyListNode<T> previousNode;

		private T data;

		private DoublyListNode<T> nextNode;

		DoublyListNode(DoublyListNode<T> nextNode, T data, DoublyListNode<T> previousNode) {
			this.previousNode = previousNode;
			this.data = data;
			this.nextNode = nextNode;
		}

	}

	public Stream<T> stream() {
		return Stream.iterate(firstNode, Objects::nonNull, node -> node.nextNode).map(obj -> obj.data);
	}

	public int size() {
		return count;
	}

	public void clear() {
		firstNode = null;
		lastNode = null;
		count = 0;
	}

	public T get(int index) {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}

		T obj;
		DoublyListNode<T> currentNode = firstNode;
		if (index == 0) {
			obj = currentNode.data;
		} else {
			for (int i = 1; i <= index; i++) {
				currentNode = currentNode.nextNode;
			}
			obj = currentNode.data;
		}
		return obj;
	}

	public void add(T data) {
		DoublyListNode<T> last = lastNode;
		if (firstNode == null) {
			firstNode = new DoublyListNode<T>(null, data, null);
			count++;
			lastNode = firstNode;
		} else {
			last.nextNode = new DoublyListNode<T>(null, data, last);
			count++;
			lastNode = last.nextNode;
		}
	}

	public void add(int index, T data) {

		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}

		DoublyListNode<T> currNode = firstNode;

		if (index == 0) {
			firstNode = new DoublyListNode<T>(currNode, data, null);
			currNode.previousNode = firstNode;
			count++;
		} else {
			for (int i = 1; i < index; i++) {
				currNode = currNode.nextNode;
			}
			currNode.nextNode = new DoublyListNode<T>(currNode.nextNode, data, currNode);
		}
	}

	public void remove(int index) {
		if (index < 0 || index >= count)
			throw new IndexOutOfBoundsException();

		DoublyListNode<T> currentNode = firstNode;
		if (index == 0) {
			currentNode = currentNode.nextNode;
			currentNode.previousNode = null;
			firstNode = currentNode;
			count--;
		} else {
			for (int i = 1; i < index; i++) {
				currentNode = currentNode.nextNode;
			}
			
			DoublyListNode<T> temp = currentNode.nextNode.previousNode;
			currentNode.nextNode = currentNode.nextNode.nextNode;
			currentNode.nextNode.previousNode = temp;
			count--;
		}
	}

	@Override
	public String toString() {
		if (firstNode == null)
			return "[]";
		else {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("[");
			DoublyListNode<T> traverseNode = firstNode;
			stringBuffer.append(traverseNode.data);
			traverseNode = traverseNode.nextNode;
			while (traverseNode != null) {
				stringBuffer.append(", ");
				stringBuffer.append(traverseNode.data);
				traverseNode = traverseNode.nextNode;
			}
			stringBuffer.append("]");
			return stringBuffer.toString();
		}
	}

	public T getFirst() {
		if (firstNode == null)
			throw new NoSuchElementException();
		return firstNode.data;
	}

	public T getLast() {

		if (lastNode == null)
			throw new NoSuchElementException();
		return lastNode.data;
	}

	@Override
	public ListIterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements ListIterator<T> {

		DoublyListNode<T> currentNode = firstNode;
		private static int currentCount = -1;

		@Override
		public boolean hasNext() {
			return currentCount + 1 < count;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T data = currentNode.data;

			if (currentNode.nextNode != null) {
				currentNode = currentNode.nextNode;
			}
			currentCount++;
			return data;
		}

		@Override
		public boolean hasPrevious() {
			return currentCount > 0;
		}

		@Override
		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}

			if (currentNode.previousNode != null) {
				currentNode = currentNode.previousNode;
			}
			currentCount--;
			T data = currentNode.data;
			return data;
		}

		@Override
		public int nextIndex() {
			return currentCount + 1;
		}

		@Override
		public int previousIndex() {
			return currentCount - 1;
		}

		@Override
		public void remove() {

		}

		@Override
		public void set(T e) {

		}

		@Override
		public void add(T e) {

		}

	}
}
