package kz.epam.atm.gmailtest.steps;

import kz.epam.atm.gmailtest.utils.DOMElementPresence;
import kz.epam.atm.gmailtest.utils.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Random;

public class GmailPageSteps {

    private WebDriver driver;
    private By eMailLocator = By.xpath("//div[@role='main']//table[@class='F cf zt']//tr[1]");
    private int subjectID = generateRandomInt();

    public GmailPageSteps(WebDriver driver){
        this.driver = driver;
    }

    public void composeEMail(String recipients,String subject,String body) {
        driver.findElement(By.cssSelector("div.z0>div")).click();
        By emailField = By.cssSelector("textarea.vO");
        ExplicitWait.explicitWaitForElement(driver, 10, emailField);
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(recipients);
        By subjectField = By.cssSelector("input.aoT");
        driver.findElement(subjectField).click();
        driver.findElement(subjectField).sendKeys(subject + "(" + subjectID + ")");
        By bodyField = By.cssSelector("div.LW-avf");
        driver.findElement(bodyField).click();
        driver.findElement(bodyField).sendKeys(body);
        driver.findElement(By.cssSelector("img.Ha")).click();
    }
    private void navigateToMailBoxFolder(By by){
        driver.findElement(by).click();
    }

    private String getDraftMailAttributeText(By mailLocator,By attributeLocator){
        ExplicitWait.explicitWaitForClickableElement(driver, 10, By.xpath("//div[@class='nH']/div[@role='main']//tbody/tr[1]"));
        ExplicitWait.explicitWaitForElement(driver, 10, By.xpath("//div[@class='nH']/div[@role='main']//tbody/tr[1]"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(mailLocator).click();
        return driver.findElement(attributeLocator).getText();

    }
    public void sendMail(){
        driver.findElement(By.cssSelector("div.T-I.J-J5-Ji.aoO.T-I-atl.L3")).click();

    }

    public void verifyDraftMailExistence(String recipients,String subject,String body){
        navigateToMailBoxFolder(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#drafts']"));
        Assert.assertTrue(DOMElementPresence.isElementPresent(driver,eMailLocator),"Draft mail not found.");
        Assert.assertEquals(getDraftMailAttributeText(eMailLocator,By.cssSelector("div.az9>span")), recipients,"Recipient is not equal.");
        Assert.assertEquals(getDraftMailAttributeText(eMailLocator,By.cssSelector("div.aYF")), subject + "(" + subjectID + ")","Subject is not equal.");
        Assert.assertEquals(getDraftMailAttributeText(eMailLocator,By.cssSelector("div.LW-avf")), body,"Mail body is not equal.");

    }
    public void verifyDraftMailAbsence(){
        navigateToMailBoxFolder(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#drafts']"));
        ExplicitWait.explicitWaitForElement(driver,5,By.cssSelector("table.cf.TB td.TC"));
        Assert.assertFalse(DOMElementPresence.isElementPresent(driver,eMailLocator),"Found draft mail in the draft folder.");
    }
    public void verifySentMailExistence(){
        navigateToMailBoxFolder(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#sent']"));
        Assert.assertTrue(DOMElementPresence.isElementPresent(driver,eMailLocator),"Sent folder is empty.");
    }
    public void deleteEmail(By by){
        driver.findElement(by).click();
        driver.findElement(By.cssSelector("div[gh^='tm'] div.nX")).click();
        driver.findElement(By.cssSelector("button.J-at1-atl")).click();
        Assert.assertFalse(DOMElementPresence.isElementPresent(driver,eMailLocator));
    }

    public int generateRandomInt(){
        Random random = new Random();
        return random.nextInt();
    }
}