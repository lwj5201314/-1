package com.lll.class01;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

import com.lll.class02.Log;

public class BaiDuTest {
	//创建一个自封装的Log对象：按照自己需要打印日志
	private static Log log=new Log(BaiDuTest.class);
	public static void main(String[] args) {
		//设置：添加图片或者显示链接到reportng输出
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		//输出带样式的reportng日志
		Reporter.log("<span style=\"color:#16A05D\"><H2>BaiDuTest</H2>");
		
		WebDriver driver = null;
		driver = openWeb(driver,"http://www.baidu.com");
		driver = search(driver,"新梦想","新梦想_百度搜索");
		logOut(driver);
	}
	
	//打开网站
	public static WebDriver openWeb(WebDriver driver,String url){
		
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		//使用自己封装的log打印日志
		log.info("网站已打开："+url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	//搜索
	public static WebDriver search(WebDriver driver,String search,String expected){
		
		driver.findElement(By.cssSelector("input#kw")).sendKeys(search);
		driver.findElement(By.cssSelector("input#su")).click();
		log.info("搜索已完成！");

		wait(3);
		String title = driver.getTitle();
		assertEquals(title, expected);
		return driver;
	}
	//关闭浏览器
	public static void logOut(WebDriver driver){
		wait(3);
		driver.quit();
		log.info("浏览器已关闭！");

	}
	//封装等待时间
	public static void wait(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

