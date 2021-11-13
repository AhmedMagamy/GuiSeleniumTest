package utilites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ReportsGenerator implements ITestListener {
    public static ExtentHtmlReporter htmlReporter = null;
    public static ExtentReports extent = new ExtentReports();
    public static ExtentTest test = null;


    public void onFinish(ITestContext result) {
        System.out.println("test is completed  : " + result.getName());
        test.info("Automated tests run is finished ");
        extent.flush();


    }

    public void onStart(ITestContext result) {
        // TODO Auto-generated method stub
        try {

            String filename = result.getStartDate().toString().replaceAll(":", "-");
            String projPath = System.getProperty("user.dir");
            htmlReporter = new ExtentHtmlReporter(projPath + "\\Reports\\" + filename + ".html");
            extent.attachReporter(htmlReporter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

        test.fail(result.getName() + "TestFailedButWithinSuccessPercentage");


    }

    public void onTestFailure(ITestResult result) {


        System.out.println(result.getInstanceName());
        System.out.println("test is failed : " + result.getName());
        test.fail("test is failed : " + result.getName());
        test.fail(result.getThrowable());


    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("test is skipped : " + result.getName());
        test.skip("test is skipped : " + result.getName());

    }

    public void onTestStart(ITestResult result) {
        System.out.println("test started : " + result.getName());
        test = extent.createTest(result.getName());

    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("test is successful : " + result.getName());
        test.pass("test is successful : " + result.getName());

    }

    public static void takeScreenShotAndAddTOReport(WebDriver driver) throws IOException {
        String projPath = System.getProperty("user.dir");
     //   String  screenshotPath=projPath+"\\screenshots\\"+result.getName()+Math.random()+".png" ;
        String  screenshotPath=projPath+"\\screenshots\\"+Math.random()+".png" ;
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(screenshotPath));
        test.addScreenCaptureFromPath(screenshotPath);
    }
}