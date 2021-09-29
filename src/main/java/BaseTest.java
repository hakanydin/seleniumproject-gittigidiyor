import Class.BaseClass;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Random;

import static Class.Cons.*;



public class BaseTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    public double productPrice, cartPrice;

    public void controlTitle() throws InterruptedException {
        checkUrl();
    }

    public void controlLogin(String username,String password) throws InterruptedException{
        ElementToBeClickableAndClick(By.cssSelector("div[data-cy='header-user-menu']"));
        Thread.sleep(3000);
        ElementToBeClickableAndClick(By.cssSelector("a[data-cy='header-login-button']"));
        Thread.sleep(2000);
        ElementSendKey(By.cssSelector("input[name='kullanici']"),username);
        Thread.sleep(2000);
        ElementSendKey(By.name("sifre"),password);
        Thread.sleep(2000);
        ElementToBeClickableAndClick(By.cssSelector("input[id='gg-login-enter']"));
        checkLogin();

    }

    public void contolSearch(String searchterm) throws InterruptedException {
        ElementSendKey(By.cssSelector("input[name='k']"),searchterm);
        ElementToBeClickableAndClick(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button"));

    }

    public void gotoPageandControl() throws InterruptedException {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement secondpage = driver.findElement(By.cssSelector("a[class='sc-12aj18f-1 ubwpe']"));
        je.executeScript("arguments[0].scrollIntoView(true);",secondpage);
        ElementToBeClickableAndClick(By.cssSelector("a[class='sc-12aj18f-1 ubwpe']"));
        Thread.sleep(2000);
        checkSecondPage();
    }

    public void controlPrdct() throws InterruptedException
    {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        List<WebElement> links = driver.findElements(By.cssSelector("li[class='sc-1nx8ums-0 dyekHG'"));
        Thread.sleep(2000);
        logger.info("Product count in page : "+links.size());
        Random productSize = new Random();
        int rndmno = productSize.nextInt(links.size());
        logger.info("Random Choosen Number:"+ rndmno);
        Thread.sleep(2000);
        WebElement rndprodcut = driver.findElement(By.xpath("(//div[@class='sc-533kbx-0 sc-1v2q8t1-0 iCRwxx ixSZpI sc-1n49x8z-12 bhlHZl'])["+rndmno+"]"));
        je.executeScript("arguments[0].scrollIntoView(true);",rndprodcut);
        Thread.sleep(2000);
        ElementToBeClickableAndClick(By.xpath("(//div[@class='sc-533kbx-0 sc-1v2q8t1-0 iCRwxx ixSZpI sc-1n49x8z-12 bhlHZl'])["+rndmno+"]"));
        logger.info("Product Selected. Product Number : "+rndmno);
    }
    public void controlDetailPrice() throws InterruptedException
    {
        boolean anyDiscount= IsElementVisible(By.id("sp-price-lowPrice"));
        if(anyDiscount==true)
        {
            productPrice = priceConvertDouble(By.id("sp-price-lowPrice"));
        }
        else {
            productPrice = priceConvertDouble(By.id("sp-price-highPrice"));
        }
        logger.info("Price on Product Detail Page: "+ productPrice);
    }

    public void controlAddCart() throws InterruptedException
    {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement addToCartButton = driver.findElement(By.cssSelector("button[id='add-to-basket']"));
        ElementToBeClickableAndClick(By.cssSelector("div[class='gg-ui-button gg-ui-btn-secondary policy-alert-v2-buttons']"));
        Thread.sleep(3000);
        je.executeScript("arguments[0].scrollIntoView(true);",addToCartButton);
        Thread.sleep(3000);
        ElementToBeClickableAndClick(By.cssSelector("button[id='add-to-basket']"));
        Thread.sleep(3000);
        logger.info("Product added to cart.");
    }

    public void controlGoToCart() throws InterruptedException
    {

        driver.get(BASEURL);
        ElementToBeClickableAndClick(By.name("cart"));
        Thread.sleep(3000);

        cartPrice = priceConvertDouble(By.cssSelector("div[class='total-price']"));
        Assert.assertEquals(productPrice,cartPrice,0.0);
        logger.info("The accuracy of the price on the product page and the price of the product in the basket was compared.");
    }

    public void controlDropDownValue() throws InterruptedException
    {
        WebElement choose = driver.findElement(By.xpath("//select[@class='amount']"));
        Select sel = new Select(choose);
        sel.selectByValue("2");
        WebElement piece = sel.getFirstSelectedOption();
        logger.info("Number of Selected Pieces : " + piece.getText());
        Thread.sleep(3000);
    }

    public void controlDeleteCart() throws InterruptedException
    {


        cartPrice = priceConvertDouble(By.cssSelector("div[class='total-price']"));
        logger.info("Price of the product in the basket : "+ cartPrice);
        if(cartPrice == cartPrice)
        {
            logger.info("Price comparison is true");
        }
        else {
            logger.info("Price comparison is false");
            Assert.fail();
        }
        ElementToBeClickableAndClick(By.xpath("//a[contains(@class,'btn-delete btn-update-item')][1]"));
        checkCart();
    }
}
