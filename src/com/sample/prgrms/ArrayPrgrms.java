package com.sample.prgrms;

public class ArrayPrgrms {

	public static boolean isPolindrome(String s) {
		int i = 0;
		int j = s.length()-1;
		boolean polindrome = true;
		while(i<=j) {
			if(s.charAt(i) != s.charAt(j)) {
				polindrome = false;
				break;
			}
			i++;
			j--;
		}
		return polindrome;
	}
	
	public static int missingNumberInArray(int a[]) {
		 int n = a.length+1;
//		 int sum = n * (n+1)/2; //for sum of first n natural numbers
		 int a1 = findMin(a);
		 int an = findMax(a);
		 int sum = n * (a1 + an)/2; //for sum of natural numers in range
		 for(int num : a) {
			 sum-=num;
		 }
		return sum;
	}
	
	private static int findMin(int[] a) {
		int min = a[0];
		int i =1;
		while(i<a.length) {
			if(a[i]<min) {
				min = a[i];
			}
			i++;
		}
		return min;
	}
	
	private static int findMax(int[] a) {
		int max = a[0];
		int i = 1;
		while(i<a.length) {
			if(a[i]>max) {
				max = a[i];
			}
			i++;
		}
		return max;
	}
	
 	public static int[] resize(int[] a, int capacity) {
		int[] temp = new int[capacity];
		for(int i=0; i<a.length; i++) {
			temp[i] = a[i];
		}
		a = temp;
		return a;
	}
	
	public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] index = new int[128]; // Assuming ASCII characters
        
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            maxLength = Math.max(maxLength, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        
        return maxLength;
    }
	
	public static int[] moveZeroesToEnd(int[] a) {
        
		for(int i=0, j=0; i<a.length; i++) {
			if(a[i]!=0 && a[j]==0) {
				int temp = a[i];
				a[i]=a[j];
				a[j]=temp;
			}
			if(a[j]!=0) {
				j++;
			}
		}
        
        return a;
    }
}
