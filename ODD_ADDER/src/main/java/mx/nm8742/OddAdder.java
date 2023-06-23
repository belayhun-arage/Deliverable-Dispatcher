package mx.nm8742;

public class OddAdder {
	
	public static void main(String[] args) {
		int[] numbers1= {1,2,3};
		int[] numbers2= {8,3,3,4};
		int[] numbers3= {2,4,6};
		int[] numbers4= {73,82,16,47};
		
		int sum1=getSum(numbers1);
		int sum2=getSum(numbers2);
		int sum3=getSum(numbers3);
		int sum4=getSum(numbers4);
		
		System.out.println(sum1);
		System.out.println(sum2);
		System.out.println(sum3);
		System.out.println(sum4);
	}
	
	//implement the adder function
	public static int getSum(int[] x) {
		int total=0;
		//check for preconditions length has to be in range of 1-50
		if(x.length<1||x.length>50) {
			throw new IllegalArgumentException("X has to be in range of 1 and 50");
		}
		//each item must be in range of 1-100
		for(int element:x) {
			if(element<0 || element>100) {
				throw new IllegalArgumentException("Element of integer array has tto be min 1 and max 100");
			}
		}
		//iterate through each element
		for(int element:x) {
			//check for number modules of two to check for even and odd if 0 it is even otherwise it is odd and add to total
			if(element%2!=0) {
				total=total+element;
			}
		}
		//return the final sum
		return total;
	}
}
