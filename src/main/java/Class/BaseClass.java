package Class;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static Class.Cons.*;

public class BaseClass {
    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    @Before

    public void main(){
        WebDriverWait webDriverWait;
        ChromeOptions options = new ChromeOptions();
        String baseUrl= BASEURL;
        System.setProperty("webdriver.chrome.driver", DRIVERPATH);
        options.setExperimentalOption("w3c", false);
        options.addArguments("--ignore-certificate-errors","disable-popup-blocking","--disable-notifications");
        driver = new ChromeDriver(options);
        webDriverWait = new WebDriverWait(driver, 45, 150);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    public void checkUrl(){
        Assert.assertTrue(BASEURL.contains("https://www.gittigidiyor.com/"));
       logger.info("Homepage is : "+ BASEURL);
    }

    public void checkLogin()
    {
        Assert.assertNotNull(LOGINCONTROL);
        logger.info("Access Controlled. "+ LOGINCONTROL);
    }

    public void checkSecondPage()
    {
        Assert.assertTrue(SEARCHPAGE.contains("https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2"));
        logger.info("Second Page Access Controlled."+ SEARCHPAGE);
    }
    public void checkCart()
    {
        Assert.assertTrue(EMPTYBASKET.contains("Sepetinizde ürün bulunmamaktadır."));
        logger.info("Cart is empty. Text Checked"+ EMPTYBASKET);
    }
    public void ElementToBeClickableAndClick(By element)
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        logger.info(element+" ");
    }

    public void ElementSendKey(By element,String sendkey)
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(sendkey);
        logger.info(element+"The page was expected to be clickable and clicked.");
    }

    public boolean IsElementVisible(By element)
    {

        try
        {
            WebDriverWait wait = new WebDriverWait(driver,1);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return false;
        }
        catch (Exception e)
        {
            return true;
        }

    }
    private WebElement findElement(By element)
    {

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        WebElement webElement = webDriverWait.
                until(ExpectedConditions.presenceOfElementLocated(element));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;

    }

    public double priceConvertDouble(By priceText)
    {

        String[] elementStringList = findElement(priceText).getText().trim().split(" ");
        String elementString = elementStringList[0].replaceAll(",", "");
        return Double.parseDouble(elementString);

    }


    @After
    public void tearDown(){

        driver.quit();

    }
}
