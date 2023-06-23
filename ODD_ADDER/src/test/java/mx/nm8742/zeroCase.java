package mx.nm8742;

import static org.junit.Assert.*;

import org.junit.Test;

public class zeroCase {

	@Test
	public void test() {
		
		//no odd number all are even
		int[] numbers= {72,82,16,40};
        int expectedSum = 0;

        int actualSum = OddAdder.getSum(numbers);

        assertEquals(expectedSum, actualSum);
	}

}
