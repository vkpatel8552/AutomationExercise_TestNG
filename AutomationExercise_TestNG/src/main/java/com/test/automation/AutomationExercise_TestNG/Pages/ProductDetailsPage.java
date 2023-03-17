package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends StartUpPage{

	@FindBy(css="main#maincontent div.product-info-main")
	public WebElement productDetailsPageHeader;
	
	@CacheLookup
	@FindBy(css="div.product-info-price span[data-price-type='finalPrice']>span")
	public WebElement productPrice;
	
	@FindBy(css="main#maincontent div.product-info-main h1.page-title>span")
	public WebElement productName;
	
	@FindBy(css="div.product-info-stock-sku span")
	public WebElement productStockStatus;
	
	@FindBy(css="div.attribute div[itemprop='sku']")
	public WebElement productCode;
	
	@FindBy(css="div#tab-label-description>a")
	public WebElement productDetails;
	
	@FindBy(css="div#description div.value>p:nth-of-type(1)")
	public WebElement productDetailContent;
	
	@FindBy(css="div#tab-label-additional>a")
	public WebElement productMoreInfo;
	
	@FindBy(css="div#tab-label-reviews>a")
	public WebElement productReviews;
	
	@FindBy(css="div#customer-reviews>div.block-title>strong")
	public WebElement customerReviewHeader;
	
	@FindBy(css="input[name='qty']")
	public WebElement productQty;
	
	@FindBy(css="button[title='Add to Cart']")
	public WebElement addToCartBtn;
	
	@FindBy(css="div[role='alert']>div.message>div")
	public WebElement productAddedMsg;
	
	@FindBy(css="span.counter-number")
	public WebElement cartItemsCount;
	
	@FindBy(css="div.product-social-links a[data-action='add-to-wishlist']")
	public WebElement addToWishListlnk;
	
	@FindBy(css="div.product-social-links a.tocompare")
	public WebElement addToComparelnk;
	
	@FindBy(css="div[role='alert']>div.message>div")
	public WebElement addToCartMsg;
	
	String xpathProductInfo = "//table[@id='product-attribute-specs-table']//th[contains(text(),'{PARAM1}')]/following-sibling::td";
	String xpathProductSize = "//div[contains(@aria-labelledby,'size')]//div[text()='{PARAM1}']";
	String xpathProductColor = "//div[contains(@aria-labelledby,'color')]//div[contains(@aria-label,'{PARAM1}')]";
	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public ProductDetailsPage waitForProductDetailsPageToLoad() throws Exception{
		ngHelper.waitTillElementIsVisible(productDetailsPageHeader, maxWaitTime);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}	
	
	public String getProductBasicDetails(String productAttr) throws Exception {
		String actualvalue = null;
		
		if(productAttr.equals("Name"))
			actualvalue = ngHelper.waitTillElementIsVisible(productName, maxWaitTime)
								  .getInnerText(productName).trim();
		else if(productAttr.equals("Price"))
			actualvalue = ngHelper.waitTillElementIsVisible(productPrice, maxWaitTime)
			  					  .getInnerText(productPrice).trim();
		else if(productAttr.equals("Status"))
			actualvalue = ngHelper.waitTillElementIsVisible(productStockStatus, maxWaitTime)
			  					  .getInnerText(productStockStatus).trim();
		else if(productAttr.equals("Code"))
			actualvalue = ngHelper.waitTillElementIsVisible(productCode, maxWaitTime)
								  .getInnerText(productCode).trim();

		return actualvalue;
	}
	
	public ProductDetailsPage clickOnProductDetailsTab() throws Exception{
		ngHelper.waitTillElementIsClickable(productDetails, maxWaitTime)
				.click(productDetails);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public String getProductDetails() throws Exception{
		return ngHelper.waitTillElementIsVisible(productDetailContent, maxWaitTime)
					   .getInnerText(productDetailContent).trim();
	}
	
	public ProductDetailsPage clickOnProductMoreInfoTab() throws Exception{
		ngHelper.waitTillElementIsClickable(productMoreInfo, maxWaitTime)
				.click(productMoreInfo);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public String getProductInfo(String productAttr) throws Exception{
		String actualvalue = null;
		
		By ele = By.xpath(CommonPage.generateDynamicXPath(xpathProductInfo, productAttr));
		actualvalue = ngHelper.waitTillElementIsVisible(ele, maxWaitTime)
							  .getInnerText(ele).trim();
		return actualvalue;
	}
	

	public ProductDetailsPage clickOnProductReviewsTab() throws Exception{
		ngHelper.waitTillElementIsClickable(productReviews, maxWaitTime)
				.click(productReviews);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public String getCustomerReviewHeader() throws Exception{
		return ngHelper.waitTillElementIsVisible(customerReviewHeader, maxWaitTime)
					   .getInnerText(customerReviewHeader).trim();
	}
	
	public ProductDetailsPage selectProductSize(String size) throws Exception{
		By ele = By.xpath(CommonPage.generateDynamicXPath(xpathProductSize, size));
		ngHelper.waitTillElementIsClickable(ele, maxWaitTime)
				.click(ele);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public ProductDetailsPage selectProductColor(String color) throws Exception{
		By ele = By.xpath(CommonPage.generateDynamicXPath(xpathProductColor, color));
		ngHelper.waitTillElementIsClickable(ele, maxWaitTime)
				.click(ele);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public ProductDetailsPage enterProductQty(String qty) throws Exception{
		ngHelper.waitTillElementIsVisible(productQty, maxWaitTime)
				.clickAndClear(productQty)
				.sendKeysTo(productQty, qty);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public ProductDetailsPage clickOnAddToCart() throws Exception{
		ngHelper.waitTillElementIsClickable(addToCartBtn, maxWaitTime)
				.click(addToCartBtn);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}

	public String getProductAddedMsg() throws Exception{
		return ngHelper.waitTillElementIsVisible(productAddedMsg, maxWaitTime)
				   .getInnerText(productAddedMsg).trim();
	}
	
	public String getShoppingCartItemsCount() throws Exception{
		return ngHelper.waitTillElementIsVisible(cartItemsCount, maxWaitTime)
					   .getInnerText(cartItemsCount).trim();
	}
	
	public LoginPage clickOnAddToWishListlnkWithoutLogin() throws Exception {
		ngHelper.waitTillElementIsClickable(addToWishListlnk, maxWaitTime)
				.click(addToWishListlnk);
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public MyWishListPage clickOnAddToWishListlnk() throws Exception {
		ngHelper.waitTillElementIsClickable(addToWishListlnk, maxWaitTime)
				.click(addToWishListlnk);
		return PageFactory.initElements(driver, MyWishListPage.class);
	}

	public ProductDetailsPage clickOnAddToCompareListlnk() throws Exception {
		ngHelper.waitTillElementIsClickable(addToComparelnk, maxWaitTime)
				.click(addToComparelnk);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public String getProductAddtoCartMsg() throws Exception {
		return ngHelper.waitTillElementIsVisible(addToCartMsg, maxWaitTime)
					   .getInnerText(addToCartMsg);
	}
	
	public String getProductAddtoCompareListMsg() throws Exception {
		return ngHelper.waitTillElementIsVisible(addToCartMsg, maxWaitTime)
					   .getInnerText(addToCartMsg);
	}
}
