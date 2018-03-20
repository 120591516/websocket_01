package com.llvision.test;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class HelloWorld {
	@WebMethod
	public String sayHello(String str) {
		System.out.println("get Message...");
		String result = "Hello World, " + str;
		return result;
	}

	public static void main(String[] args) {
		// System.out.println("server is running");
		// String address = "http://localhost:9000/HelloWorld";
		// Object implementor = new HelloWorld();
		// Endpoint.publish(address, implementor);

		String path = "https://wap.goldnurse.com/activity/bfm.html";
		int startIndex = path.indexOf("://");
		int endIndex = path.indexOf(".goldnurse");
		String packageName = path.substring(startIndex + 3, endIndex);
		// 文件路径为 base_path + packageName
		String filePath = "/data/nginx/logs" + "/" + packageName;
		// 获取要匹配的url
		// https://wap.goldnurse.com/activity/bfm.html
		// /activity/bfm.html
		int urlSuffixIndex = path.indexOf(".com");
		String urlSuffix = path.substring(urlSuffixIndex + 4);
		String fileName = filePath + "/access_" + ".log";
		System.out.println(fileName);
		System.out.println(urlSuffix);
		String str ="123.125.67.221 - - [10/Mar/2018:01:38:48 +0800] "GET /khnurse/a_index.html HTTP/1.1" 200 5587 "-" "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.93 Safari/537.36" "-"";
	}

}
