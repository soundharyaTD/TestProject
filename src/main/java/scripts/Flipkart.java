package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import atu.testng.reports.logging.LogAs;
import commonMethods.Keywords;
import commonMethods.Utils;

public class Flipkart extends Keywords {

	String iphone = Utils.getDataFromTestData("Flipkart", "iPhone");
	  String pincode = Utils.getDataFromTestData("Flipkart", "Pincode");
	  String flipUsername = Utils.getDataFromTestData("Flipkart", "UserName");
	  String flipPassword = Utils.getDataFromTestData("Flipkart", "PassWord");
	  String Productprice;
	  String Productcartprice;
	  
	
	public void productSearch(WebDriver driver,String URL) throws InterruptedException {
		
		navigateUrl(driver,URL);
		escape(driver);
			  waitForElement(driver, searchBox);
			  sendKeys(driver, searchBox, iphone);
			  enter(driver);
			 
	}
	public void productSelect(WebDriver driver) throws InterruptedException
	{	  
		if(isDisplayed(driver, iPhoneSelect))
		{
		waitForElement(driver, iPhoneSelect);
			  click(driver,iPhoneSelect);
			  wait(driver, "3");
			  switchtotab(driver, 1);
			  waitForElement(driver, addToCart);
			  verifyElementIsPresent(driver, addToCart);
			  verifyElementIsPresent(driver, iPhonePrice);
			  Productprice=getText(driver, iPhonePrice);
			  waitForElement(driver,pinCode);
			  sendKeys(driver,pinCode, pincode);
			  enter(driver);
				refreshPage(driver);
		}
		
		else
		{
		waitForElement(driver, selectiPhone);
			  click(driver,selectiPhone);
			  wait(driver, "3");
			  switchtotab(driver, 1);
			  waitForElement(driver, addToCart);
			  verifyElementIsPresent(driver, addToCart);
			  verifyElementIsPresent(driver, iPhonePrice);
			  Productprice=getText(driver, iPhonePrice);
			  waitForElement(driver,pinCode);
			  sendKeys(driver,pinCode, pincode);
			  enter(driver);
				refreshPage(driver);
		}
			  
	}
	public void productShopping(WebDriver driver) throws InterruptedException
	{
			  waitForElement(driver,addToCart);
			  click(driver,addToCart);
			  
			  waitForElement(driver,continueShopping);
			  verifyElementIsPresent(driver, continueShopping);
			  click(driver,continueShopping);
			  	}
	public void productCart(WebDriver driver)
	{
			  click(driver,cart);
			  waitForElement(driver,placeOrder);
			  click(driver,placeOrder);
	}
	public void Login(WebDriver driver)
	{
			  waitForElement(driver,emailORmobileNumber);
			  click(driver,emailORmobileNumber);
			  sendKeys(driver,emailORmobileNumber, flipUsername);
			  click(driver,continueProcess);
			  waitForElement(driver, flipkartPassword);
			  click(driver, flipkartPassword);
			  sendKeys(driver,flipkartPassword, flipPassword);
			  waitForElement(driver, flipkartLogin);
			  click(driver,flipkartLogin);
	}
	public void productDeliver(WebDriver driver)
	{
			  waitForElement(driver, deliveryHere);
			  click(driver,deliveryHere);
			  waitForElement(driver, orderSumaryContinue);
			  click(driver,orderSumaryContinue);
			  
			  
			  
			 }
	public void productRemove(WebDriver driver)
	{
		goBack(driver);
		  //System.out.println("Page is getting back");
		  waitForElement(driver, myAccount);
		  //click(driver, myAccount);
		  mouseOver(driver, myAccount);
		  waitForElement(driver, myProfile);
		  click(driver, myProfile);
		  //System.out.println("Clicked on MyProfile");
		  waitForElement(driver, flipCart);
		  click(driver, flipCart);
		  waitForElement(driver, flipRemove);
		  if(isDisplayed(driver, iPhoneSelect))
		  {
			  verifyElementIsPresent(driver, iPhoneCart);
			  verifyElementIsPresent(driver, iPhoneCartPrice);
			  Productcartprice=getText(driver, iPhoneCartPrice);
				
				if(Productprice.equalsIgnoreCase(Productcartprice)){
				    add(driver, "Price '" + Productprice + "' is verified ", LogAs.PASSED, true, Productcartprice);
				    
				   }
				else {
					 add1(driver, "Price is Not Present " + Productprice, LogAs.FAILED, true, Productcartprice);
				}
		  }
		  else
		  {
			  verifyElementIsPresent(driver, selectiPhoneCart);
			  verifyElementIsPresent(driver, iPhoneCartPrice);
			  Productcartprice=getText(driver, iPhoneCartPrice);
				
				if(Productprice.equalsIgnoreCase(Productcartprice)){
				    add(driver, "Price '" + Productprice + "' is verified ", LogAs.PASSED, true, Productcartprice);
				    
				   }
				else {
					 add1(driver, "Price is Not Present " + Productprice, LogAs.FAILED, true, Productcartprice);
				}
		  }
		  
		  
		  
		  click(driver, flipRemove);
		  waitForElement(driver, cartEmpty);
		  getText(driver, cartEmpty);

	}

	
	
	public void Logout(WebDriver driver)
	{
		  waitForElement(driver, myAccount);
		  mouseOver(driver, myAccount);
		  waitForElement(driver, logout);
		  System.out.println("Waiting for Logout");
		  click(driver, logout);
	}

			
	
	
}
	
