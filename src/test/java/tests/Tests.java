package tests;

import base.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utilites.ExcelUtility;
import utilites.ReportsGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Date;


@Listeners(utilites.ReportsGenerator.class)
public class Tests extends Base {
    ExcelUtility excel = new ExcelUtility("TestData.xlsx");
    private TheInternetPage theInternetPage ;
    private FileUploadPage fileUploadPage ;
    private DynamicLoadingPage dynamicLoadingPage;
    private GooglePage googlePage ;

    //in this method we call all needed pages for tests
    @BeforeMethod
    public void callPages() {
    theInternetPage = new TheInternetPage(driver);
    fileUploadPage = new FileUploadPage(driver);
    dynamicLoadingPage = new DynamicLoadingPage(driver);
    googlePage= new GooglePage(driver);
    }

    @Test(priority = 1)
    public void tc01_uploadImage() throws IOException {

        //Read the path of the image to upload
        String imagePath = System.getProperty("user.dir") + "\\Images\\" + "test.jpg";

        //Navigate to the internet page
        driver.navigate().to("https://the-internet.herokuapp.com/");
        //Click upload file
        theInternetPage.clickOnFileUpload();
        //File the input file with image
        fileUploadPage.fillInputWithImagePath(imagePath);
        //click upload
        fileUploadPage.clickUpload();
        //Verify it's successfully uploaded
        Assert.assertTrue(fileUploadPage.isFileUploaded());
    }
    @Test(priority = 2)
    public void tc02_dynamicLoading() throws IOException {

        //Navigate to the internet page
        driver.navigate().to("https://the-internet.herokuapp.com/");
        //click on the Dynamic loading link
        theInternetPage.clickOnDynamicLoading();
        //open example 2
        dynamicLoadingPage.openExample2();
        //click start
        dynamicLoadingPage.clickStart();
        //Verify the text is displayed
        Assert.assertTrue(dynamicLoadingPage.isTextDisplayed());

    }
    @Test(priority = 3)
    public void tc03_googleTest() throws IOException {

        //Read test data from external excel file
        String searchText = excel.getCellDataString("Data",1,0);
        //Navigate to google page
        driver.navigate().to("https://www.google.com/ncr");
        //Submit search text
        googlePage.searchForText(searchText);
        //Read the third search result
        String thirdResult = googlePage.getThirdResultText();
        //Verify it contains the expected result
        Assert.assertTrue(thirdResult.contains("What is Selenium WebDriver?"));

    }

    @AfterMethod
    public void takeScreenShot() throws IOException {
        //Take screenshot at the end of any test
        ReportsGenerator.takeScreenShotAndAddTOReport(driver);
    }



}
