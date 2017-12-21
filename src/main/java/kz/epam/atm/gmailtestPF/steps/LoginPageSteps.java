package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;


public class LoginPageSteps extends AbstractSteps{

    private static final String LOGIN_FAIL_ERR_MSG = "Login failed.";

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//div[@id='identifierNext']/content/span")
    private WebElement nextButtonEmailTab;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@id='passwordNext']/content/span")
    private WebElement nextButtonPasswordTab;

    @FindBy(css = "span.gbii")
    private WebElement googleAccountIcon; /// no usage

    @FindBy(id = "gb_71")
    private WebElement logoutBotton;

    public LoginPageSteps(WebDriver driver){
        super(driver);
    }

    public LoginPageSteps openLoginPage(String url){
        driver.get(url);
        return this;
    }

    public GmailPage authorization(String login, String password){
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, emailField );
        emailField.sendKeys(login);
        nextButtonEmailTab.click();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, passwordField );
        passwordField.sendKeys(password);
        nextButtonPasswordTab.click();
        Assert.assertTrue(DOMElementPresence.isElementPresent(logoutBotton),LOGIN_FAIL_ERR_MSG);
        return new GmailPage(driver);
    }

    public void logout() {
        googleAccountIcon.click();
        ExplicitWait.explicitWaitVisibilityOfElement(driver, EXPLICIT_WAIT_TIMEOUT, logoutBotton);
        logoutBotton.click();
    }
}
