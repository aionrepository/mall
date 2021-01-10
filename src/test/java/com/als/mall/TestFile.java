package com.als.mall;


import java.io.File;

import org.junit.jupiter.api.Test;

class TestFile {

	@Test
	void test() {
		File file = new File("src/main/resources/test.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		String fileName = file.getName();
		System.out.println(fileName);
		String[] strings = fileName.split("\\.");
		System.out.println(strings[strings.length-1]);
	}

}
