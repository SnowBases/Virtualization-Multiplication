package improvement;

import static java.lang.System.out;

public class Main {
	private static int i = 0;

	public static void main(String[] args) {
		int[] A = getArrays("99");
		int[] B = getArrays("2147483647");
		out.println();
		getAdditionalValues(A, B, i);

		int firstPosition = A[0]*B[0]; // compute the first position, A[first] x B[first]
		int[] upperside = getSymmetricalResult(A, B, "upper"); // compute the upperside 
		int[] lowerside = getSymmetricalResult(A, B, "lower"); // compute the lowerside
		int[] middlePosition = getMiddlePosition(upperside, lowerside); // upperside + lowerside, their corresponding will be sum
		int lastPosition = A[A.length-1]*B[B.length-1]; // compute the last position, A[last] x B[last]
		
		int[] fragment = putIntoArrays(firstPosition, lastPosition, middlePosition);
		
		out.println("before shifting fragment:");
		printArrays(fragment);
		//out.println("er"+fragment[1]);
		int[] newFragment = shiftingCorresponding(fragment);
		
		out.println("after shifting fragment:");
		printArrays(fragment);
		
		out.println("after shifting fragment (last digit):");
		printArrays(newFragment);
	}
	
	public static int getNumberOfDigits(int num) {
		int count = 0;

        while(num != 0)
        {
            num /= 10;
            ++count;
        }
        return count;
	}
	
	public static int[] shiftingCorresponding(int[] fragment) {
		int[] newFragment = new int[fragment.length]; // create new arrays to store the last digits (actual result)
		
		for(int i=fragment.length-1; i>0 ; i--) {
			newFragment[i] = getLastDigit(fragment[i]);

			int shift = getNumberOfDigits(fragment[i]);
			if(shift == 3) {
				int A = getFirstDigit(fragment[i]);
				int B = getCenterDigit(fragment[i]);
				int frontDigits = concatenateDigits(A, B);
				
				fragment[i-1] = fragment[i-1] + frontDigits;
			} else if(shift == 2) {
				fragment[i-1] = fragment[i-1] + getFirstDigit(fragment[i]);
			} else if(shift == 1) {
				newFragment[i] = getLastDigit(fragment[i]);
			}
		}
		newFragment[0] = fragment[0];
		
		return newFragment;
	}
	
    
	public static int concatenateDigits(int... digits) {
	   String result = "";
	   for (int digit : digits) {
	     result += digit;
	   }
	   return Integer.parseInt(result);
	}

	public static int getCenterDigit(int num) {
		String number = String.valueOf(num);
		
		int j = Character.digit(number.charAt(1), 10);
		return j;
	}

	public static void getAdditionalValues(int[] A, int B[], int i) {
		/**			
	    List<StringBuilder> list = new LinkedList<StringBuilder>();
		StringBuilder[] string = new StringBuilder[i+1];
		
		for(int j = 0; j < i; j++) {
		    string[i] = new StringBuilder();
		    string[i].append(A[j]);
//			for( int a : B ) {
//				
//				//list.add(o[i]);
//			}
		    list.add(string[i]);
		} 
		
		for (StringBuilder number : list) {
		    System.out.print(number);
		}
		**/
		
		StringBuilder A1 = new StringBuilder();	
		
		for( int a : A ) {
			A1.append(a);
		}
		
		StringBuilder B1 = new StringBuilder();
		
		for( int b : B ) {
			B1.append(b);
		}
		
		out.println("Actual Value: \n" + Integer.parseInt(A1.toString()) * Integer.parseInt(B1.toString()) + "\n");

	}
	
	// this method convert String into int arrays
	public static int[] getArrays(String in) {
		out.println("in : " + in);
		String[] digits = in.split("");
		int[] fn = new int[ digits.length ];
		
		for(int i = 0; i < digits.length; i++) {
			fn[i] = Integer.parseInt(digits[i]); 
//			out.print(fn[i]);
		}
		//out.println("\n");
		i+=1;
		
//		int i = 0; 
//		for ( String value : digits ) {
//			fn[i] = Integer.parseInt(value); 
//			out.println(fn[i]);
//			i++; 
//		} 
		return fn;
	}
	
	public static int[] putIntoArrays(int firstPosition, int lastPosition, int[] middlePosition) {
		int[] fragment = new int[middlePosition.length+2]; // create arrays to store first + middle + last
		
		fragment[0] = firstPosition; 
		fragment[fragment.length-1] = lastPosition;
		
		int m = 0;  // 0 because middle position starts with [0] ~ [middlePosition.length];
		for(int i=1; i<fragment.length-1; i++) { // put the middle position into fragment
			fragment[i] = middlePosition[m];
			m++;
		}
		return fragment;
	}
	
	public static int[] getSymmetricalResult(int[] A, int[] B, String mode) {
		int[] results = new int[B.length-1];
		int u = 1; // upper
		int l = 0; // lower
		for(int j=0; j<B.length-1; j++) {
			if(mode.equals("upper")) {
				results[j] = A[0]*B[u];
				u++;
			} else if(mode.equals("lower")) {
				results[j] = A[1]*B[l];
				l++;
			} else {
				break;
			}
		}
		return results;
	}
	
	public static int[] getMiddlePosition(int[] upperside, int[] lowerside) {
		int[] middlePosition = new int[upperside.length];
		for(int i=0; i<upperside.length; i++) {
			middlePosition[i] = upperside[i]+lowerside[i]; 
		}
		return middlePosition;
	}
	
	public static int getLastDigit(int n) { 
		return (n % 10);
	} 
	
	public static int getFirstDigit(int n) { 
        while (n >= 10)  
            n /= 10; 
        return n; 
    } 
	
	// this method print all arrays
	public static void printArrays(int[] A) {
		for( int value : A ) {
			out.print(value + " ");
		}
		out.println("\n");
//		for(int i=0; i<A.length ; i++) {
//			out.println(A[i]);
//		}
	}
	
	

}
