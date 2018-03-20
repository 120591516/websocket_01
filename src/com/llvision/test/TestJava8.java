package com.llvision.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJava8 {
	public static void main(String args[]) {
		List names = new ArrayList();

		names.add("Google");
		names.add("Runoob");
		names.add("Taobao");
		names.add("Baidu");
		names.add("Sina");

		names.forEach(System.out::println);
		Car car = Car.create(Car::new);
		List<Car> cars = Arrays.asList(car);
		cars.forEach(Car::collide);
		TestJava8 java8tester = new TestJava8();
		java8tester.testZonedDateTime();
		List<String> asList = Arrays.asList("a", "e", "d");
		Arrays.asList(1, 2, 3).forEach(e -> addStr(e));
		asList.sort((e1, e2) -> e1.compareTo(e2));
		asList.sort((e1, e2) -> {
			int result = e1.compareTo(e2);
			return result;
		});
		asList.forEach(f -> System.out.println(f));
	}

	public static void addStr(Integer s) {
		System.out.println(s + "--");
	}
	public void testZonedDateTime() {

		// 获取当前时间日期
		ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.out.println("date1: " + date1);

		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);

		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("当期时区: " + currentZone);
		// 获取当前的日期时间
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("当前时间11: " + currentTime);

		LocalDate date2 = currentTime.toLocalDate();
		System.out.println("date2: " + date2);

		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();

		System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);

		LocalDateTime date3 = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("date3: " + date3);

		// 12 december 2014
		LocalDate date6 = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println("date6: " + date6);

		// 22 小时 15 分钟
		LocalTime date4 = LocalTime.of(22, 15);
		System.out.println("date4: " + date4);

		// 解析字符串
		LocalTime date5 = LocalTime.parse("20:15:30");
		System.out.println("date5: " + date5);
	}
}
