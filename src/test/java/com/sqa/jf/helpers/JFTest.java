package com.sqa.jf.helpers;

import org.testng.annotations.*;
import org.testng.annotations.Test;

public class JFTest {
	@Test(dataProvider = "getJFData")
	public void fun(String name, String count) {
		// for (int row = 0; row < this.myData.length; row++) {
		// for (int col = 0; col < this.myData[row].length; col++) {
		// System.out.println(this.myData[row][col]);
		// }
		// }
		System.out.println(name);
	}

	@DataProvider
	private String[][] getJFData() {
		System.out.println("I am a statement that doesnt do anything");
		return new String[][] { { "ja", "1" }, { "jb", "2" }, { "jc", "3" }, { "jd", "4" }, { "je", "5" } };
	}
}
