package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicLoadingPage {

    private WebDriver driver;

    By example2Link = By.linkText("Example 2: Element rendered after the fact");
    By startBTn = By.xpath("//div[@id='start']/button");
    By loadingBar = By.id("loading");
    By helloTxt = By.xpath("//div[@id='finish']/h4");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openExample2(){
       driver.findElement(example2Link).click();
    }
    public void clickStart(){
        driver.findElement(startBTn).click();
    }
    public Boolean isTextDisplayed(){
        WebElement loadingBarElem = driver.findElement(loadingBar);
        //wait for the loading bar to be invisible
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(loadingBarElem));
        return driver.findElement(helloTxt).isDisplayed();


    }
}
