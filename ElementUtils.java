package com.chevron.tests.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementUtils {	
	
	public static enum Type { XPATH, SELECTOR, CLASSNAME, TAGNAME, LINK_TEXT, ID }
	
	
	public static WebElement hover(final WebDriver driver, final String hoverPath, final Type type) throws NoSuchElementException{
		final Actions actions = new Actions(driver);
		final WebElement webElement = ElementUtils.findElement(driver, hoverPath, type);		
		actions.moveToElement(webElement).perform();	
		pause();
		return webElement;
	}
	
	public static void click(final WebDriver driver, final String lookup, final Type type) throws NoSuchElementException{
		final Actions actions = new Actions(driver);
		final WebElement webElement = ElementUtils.findElement(driver, lookup, type);
		actions.moveToElement(webElement).perform();
		actions.click().build().perform();
		pause();		
	}
	
	public static void hoverAndClick(final WebDriver driver, String hoverLookup, final Type type) throws NoSuchElementException{
		final WebElement webElement = hover(driver, hoverLookup, type);
		if(webElement != null){
			final Actions actions = new Actions(driver);		
			actions.moveToElement(webElement).perform();
			actions.click().build().perform();
		}else{
			click(driver, hoverLookup, type);
		}
	}
	
	public static WebElement findElement(final WebDriver driver, final String lookup, final Type type) throws NoSuchElementException{
		WebElement element;
		switch(type){
			case ID: 
				element = driver.findElement(By.id(lookup)); 
				break;
			case CLASSNAME: 
				element = driver.findElement(By.className(lookup)); 
				break;
			case LINK_TEXT: 
				element = driver.findElement(By.linkText(lookup)); 
				break;
			case SELECTOR: 
				element = driver.findElement(By.cssSelector(lookup)); 
				break;
			case TAGNAME: 
				element = driver.findElement(By.tagName(lookup)); 
				break;
			case XPATH: 
			default: 
				element = driver.findElement(By.xpath(lookup)); 
				break;
		}
		return element;
	}
	
	public static List<WebElement> findElements(final WebDriver driver, final String lookup, final Type type) throws NoSuchElementException{
		final List<WebElement> elements = new ArrayList<WebElement>();
		switch(type){
			case CLASSNAME: 
				elements.addAll(driver.findElements(By.className(lookup))); 
				break;
			case SELECTOR: 
				elements.addAll(driver.findElements(By.cssSelector(lookup))); 
				break;
			case TAGNAME: 
				elements.addAll(driver.findElements(By.tagName(lookup))); 
				break;
			case XPATH: 
			default: 
				elements.addAll(driver.findElements(By.xpath(lookup))); 
				break;
		}
		return elements;	
	}

	public static void pause(){
		pause(1);
	}
	public static void pause(final int second){
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Failed to add pause for thread " + Thread.currentThread().getName());
		}
	}
}
