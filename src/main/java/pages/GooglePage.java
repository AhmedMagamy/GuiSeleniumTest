package pages;

import org.openqa.selenium.*;

public class GooglePage {
    private WebDriver driver;

    //Locators
    private By searchField  = By.name("q");
    private By thirdSearchResult = By.xpath("(//div/a/h3)[3]");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForText(String keyword){
        WebElement searchFieldElem =  driver.findElement(searchField);
        // fill the field with search text
        searchFieldElem.sendKeys(keyword);
        //press enter
        searchFieldElem.sendKeys(Keys.ENTER);
    }
    public String getThirdResultText(){
        WebElement thirdSearchResElem = driver.findElement(thirdSearchResult);
        //scroll to the third result element
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView();", thirdSearchResElem);
        //read the text
        return driver.findElement(thirdSearchResult).getText();
    }

}
