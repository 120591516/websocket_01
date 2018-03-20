package com.llvision.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {
	public static void main(String[] args) {
		// 2018-02-28 上午 10:27:41
		// 2018-02-28 下午 01:32:00
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		System.out.println(sdf.format(new Date()));
	}
}
