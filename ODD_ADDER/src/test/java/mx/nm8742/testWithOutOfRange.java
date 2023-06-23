package mx.nm8742;

import static org.junit.Assert.*;

import org.junit.Test;

public class testWithOutOfRange {

	@Test
	public void test() {
		
		//length is zero
		int[] numbers = {};
        assertThrows(IllegalArgumentException.class, () -> {
            OddAdder.getSum(numbers);
        });
	}

}
