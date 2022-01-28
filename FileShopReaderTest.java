package com.orange.shop;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import com.orange.shop.models.Line;
import com.orange.shop.utils.FileShopReader;
import org.junit.Test;

public class FileShopReaderTest {

	@Test
	public void testName() throws Exception {
		FileShopReader fileReader = new FileShopReader();
		List<Line> setAllLine = fileReader.setAllLine();

		assertEquals(setAllLine.size(), 226);
		assertEquals(setAllLine.get(0).getField1(), "-1.51815");
		assertEquals(setAllLine.get(0).getField2(), "43.49507");
		assertEquals(setAllLine.get(0).getField3(), "[Orange] 64 Anglet (Avenue Belle Marion)");
		assertEquals(setAllLine.get(0).getField4(), "5");
		assertEquals(setAllLine.get(0).getField5(), "7");
		assertEquals(setAllLine.get(0).getField6(), "10");

	}
}
