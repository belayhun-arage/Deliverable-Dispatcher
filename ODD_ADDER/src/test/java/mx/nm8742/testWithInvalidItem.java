package mx.nm8742;

import static org.junit.Assert.*;

import org.junit.Test;

public class testWithInvalidItem {

	@Test
	public void test() {
		
		//one item is out of rang
		int[] numbers = {1,2,3,1000};
        assertThrows(IllegalArgumentException.class, () -> {
            OddAdder.getSum(numbers);
        });
	}
}
