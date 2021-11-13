package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TheInternetPage {
    private WebDriver driver;

    private By fileUpload = By.linkText("File Upload");
    private By dynamicLoading = By.linkText("Dynamic Loading");


    public TheInternetPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFileUpload(){
        driver.findElement(fileUpload).click();
    }
    public void clickOnDynamicLoading(){
        driver.findElement(dynamicLoading).click();
    }
}
