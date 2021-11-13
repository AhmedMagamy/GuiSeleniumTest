package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploadPage {
    private WebDriver driver;

    private By fileInput = By.id("file-upload");
    private By uploadBtn = By.id("file-submit");
    private By fileUploaded = By.id("uploaded-files");


    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInputWithImagePath(String imagePath){
        driver.findElement(fileInput).sendKeys(imagePath);
    }
    public void clickUpload(){
        driver.findElement(uploadBtn).click();
    }
    public Boolean isFileUploaded(){
        return driver.findElement(fileUploaded).isDisplayed();
    }
}
