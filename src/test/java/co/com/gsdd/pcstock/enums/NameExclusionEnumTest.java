package co.com.gsdd.pcstock.enums;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameExclusionEnumTest {

	@Test
	void findByNameTest() {
		Stream.of(NameExclusionEnum.values())
				.forEach(type -> Assertions.assertEquals(type.getName(), NameExclusionEnum.findByName(type.getName())));
	}

	@Test
	void findByNameNullTest() {
		Assertions.assertNull(NameExclusionEnum.findByName("test"));
	}
}
