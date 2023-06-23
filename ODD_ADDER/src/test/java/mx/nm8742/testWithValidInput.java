package mx.nm8742;

import static org.junit.Assert.*;

import org.junit.Test;

public class testWithValidInput {

	@Test
	public void test() {
		int[] numbers4= {73,82,16,47};
        int expectedSum = 120;

        int actualSum = OddAdder.getSum(numbers4);

        assertEquals(expectedSum, actualSum);
	}

}
