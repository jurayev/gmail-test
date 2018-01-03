package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import kz.epam.atm.gmailtestPF.utils.GActions;
import kz.epam.atm.gmailtestPF.utils.RandomNumberGenerator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;



public class GmailPage extends AbstractPage{

    private String subjectContent;

    @FindBy(xpath = "//div[@role='main']//table[@class='F cf zt']//tr[1]")
    private WebElement firstEmailInList;

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#drafts']")
    private WebElement draftFolderLink;

    @FindBy(css = "div.z0>div")
    private WebElement composeEmailButton;

    @FindBy(css = "textarea.vO")
    private WebElement emailRecipientsField;

    @FindBy(css = "div.az9>span")
    private WebElement emailRecipientsOutputTextElement;

    @FindBy(css = "div.LW-avf")
    private WebElement emailBodyField;

    @FindBy(css = "input.aoT")
    private WebElement emailSubjectField;

    @FindBy(css = "div.aYF")
    private WebElement emailSubjectOutputTextElement;

    @FindBy(css = "img.Ha")
    private WebElement emailWindowCloseButton;

    @FindBy(css = "div.T-I.J-J5-Ji.aoO.T-I-atl.L3")
    private WebElement emailSendButton;

    @FindBy(xpath = "//div[@role='main']//div[@class='yW']/font")
    private WebElement drafMailLabel;

    @FindBy(css = "table.cf.TB td.TC")
    private WebElement emptyEmailListSign; /// no usage

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#sent']")
    private WebElement sentFolderLink;

    @FindBy(css = "div[gh^='tm'] div[role^='presentation']")
    private WebElement selectAllEmailsCheckbox;

    @FindBy(css = "div[gh^='tm'] div.nX")
    private WebElement deleteEmailButton;

    @FindBy(css = "button.J-at1-atl")
    private WebElement deletionApplyButton;

    @FindBy(css = "div.vh>span.a8k")
    private WebElement mailSentPopupMessage;

    @FindBy(css = "div.J-N-JX.aDE.aDF")
    private WebElement contextDeleteEmailButton;

    @FindBy(css = "div.a5.aaA.aMZ")
    private WebElement addImageIcon;

    @FindBy(xpath = "//div[@id=':6']/div")
    private WebElement fromInternetTab;

    @FindBy(css = "input.Io-uq-Qc")
    private WebElement insertLinkField;

    @FindBy(css = "div.a-b-c.d-u.d-u-F.Io-tb-hp-enabled")
    private WebElement addImageButton;

    @FindBy(css = "div.LW-avf img")
    private WebElement imageInsideEmailBody;

    @FindBy(css = "iframe.KA-JQ")
    private WebElement downloadImageIFrame;

    public WebElement getChangesSavingSing() {
        return changesSavingSing;
    }

    @FindBy(css = "span.oG.aOy")
    private WebElement changesSavingSing;

    public WebElement getEmptyEmailListSign() {
        return emptyEmailListSign;
    }
    public WebElement getFirstEmailInList(){
        return this.firstEmailInList;
    }
    public WebElement getEmailRecipientsOutputTextElement(){
        return this.emailRecipientsOutputTextElement;
    }
    public WebElement getEmailSubjectOutputTextElement(){
        return this.emailSubjectOutputTextElement;
    }
    public String getFirstEmailSubjectText(){
        firstEmailInList.click();
        return this.emailSubjectOutputTextElement.getText();
    }
    public WebElement getEmailBodyField(){
        return this.emailBodyField;
    }
    public String getFirstEmailBodyText(){
        firstEmailInList.click();
        return this.emailBodyField.getText();
    }
    public WebElement getDrafMailLabel(){
        return this.drafMailLabel;
    }
    public WebElement getImageInsideEmailBody(){
        return imageInsideEmailBody;
    }
    public WebElement getDownloadImageIFrame(){
        return downloadImageIFrame;
    }

    public void clickComposeEmail(){
        composeEmailButton.click();
    }

    public void fillEmailRecipientsField(String recipients){
        ExplicitWait.explicitWaitVisibilityOfElement(emailRecipientsField);
        emailRecipientsField.click();
        emailRecipientsField.sendKeys(recipients);
        GActions.pressTabKey(emailRecipientsField);
    }
    public void fillEmailBodyField(String body){
        emailBodyField.click();
        emailBodyField.sendKeys(body);
    }
    public void fillEmailSubjectField(String subject){
        emailSubjectField.click();
        emailSubjectField.sendKeys(subject);
    }
    public void clickEmailWindowCloseButton() {
        emailWindowCloseButton.click();
    }
    public void clickDraftFolderLink(){
        ExplicitWait.explicitWaitUntilElementToBeClickable(draftFolderLink);
        draftFolderLink.click();
        /*Actions actions = new Actions(driver);
        actions.moveToElement(draftFolderLink).click().perform();*/
    }
    public void clickSentFolderLink(){
        //ExplicitWait.explicitWaitUntilElementToBeClickable(sentFolderLink);
        //ExplicitWait.explicitWaitVisibilityOfElement(sentFolderLink);
        //ExplicitWait.magicWaiter();
        try{
            ExplicitWait.explicitWaitUntilElementToBeClickable(sentFolderLink);
            sentFolderLink.click();
        }catch (WebDriverException e){
            GActions.moveToElementAndClick(sentFolderLink);
        }
    }
    public String getFirstEmailRecipientsText(){
        ExplicitWait.explicitWaitUntilElementToBeClickable(firstEmailInList);
        firstEmailInList.click();
        ExplicitWait.explicitWaitVisibilityOfElement(emailRecipientsOutputTextElement);
        return this.emailRecipientsOutputTextElement.getText();
    }


    public void composeEmail(Email email) {
        composeEmailButton.click();
        ExplicitWait.explicitWaitVisibilityOfElement(emailRecipientsField);
        emailRecipientsField.click();
        emailRecipientsField.sendKeys(email.getRecipients());
        GActions.pressTabKey(emailRecipientsField);
        emailSubjectField.click();
        subjectContent = buildSubjectString(email);
        emailSubjectField.sendKeys(subjectContent);
        emailBodyField.click();
        emailBodyField.sendKeys(email.getBody());


    }
    public String getSubjectContentString() {
        return subjectContent;
    }

    public String buildSubjectString(Email email){
        return email.getSubject() + "(" + RandomNumberGenerator.getRandomInt() + ")" ;
    }

    public void sendEmail(){
        emailSendButton.click();
    }

    public void addImageToEmailBodyFromWeb(Email email){
        addImageIcon.click();
        ExplicitWait.explicitWaitFrameToBeAvailableAndSwitchToIt(downloadImageIFrame);
        ExplicitWait.explicitWaitVisibilityOfElement(fromInternetTab);
        fromInternetTab.click();
        GActions.sendText(insertLinkField, email.getImage());
        ExplicitWait.explicitWaitUntilElementToBeClickable(addImageButton);
        addImageButton.click();
        driver.switchTo().defaultContent();
    }

    public void deleteAllEmailsFromFolder(){
        selectAllEmailsCheckbox.click();
        deleteEmailButton.click();
        deletionApplyButton.click();
    }
}
