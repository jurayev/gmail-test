package kz.epam.atm.gmailtestPF.pages;

import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.EXPLICIT_WAIT_TIMEOUT;

public class GmailPage extends AbstractPage{

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

    @FindBy(id = ":6")
    private WebElement fromInternetTab;

    @FindBy(css = "input.Io-uq-Qc")
    private WebElement insertLinkField;

    @FindBy(css = "div.a-b-c.d-u.d-u-F.Io-tb-hp-enabled")
    private WebElement addImageButton;

    @FindBy(css = "div.LW-avf img")
    private WebElement imageInsideEmailBody;

    @FindBy(css = "iframe.KA-JQ")
    private WebElement downloadImageIFrame;

    public GmailPage(){
        super();
    }
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
    public WebElement getEmailBodyField(){
        return this.emailBodyField;
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
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, emailRecipientsField);
        emailRecipientsField.click();
        emailRecipientsField.sendKeys(recipients);
        new Actions(driver).sendKeys(emailRecipientsField, Keys.TAB).build().perform();
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
        ExplicitWait.explicitWaitVisibilityOfElement(EXPLICIT_WAIT_TIMEOUT, draftFolderLink);
        draftFolderLink.click();
    }
    public void clickSentFolderLink(){
        ExplicitWait.explicitWaitUntilElementToBeClickable(EXPLICIT_WAIT_TIMEOUT, sentFolderLink);
        sentFolderLink.click();
    }
    public void clickSendEmail(){
        emailSendButton.click();
    }
    public void clickSelectAllEmailsCheckbox(){
        selectAllEmailsCheckbox.click();
    }
    public void clickDeleteEmailButton(){
        deleteEmailButton.click();
    }
    public void clickDeletionApplyButton(){
        deletionApplyButton.click();
    }

    public void clickAddImageIcon(){
        addImageIcon.click();
    }

    public void clickFromInternetTab(){
        fromInternetTab.click();
    }

    public void fillInsertLinkField(String imageLink){
        new Actions(driver).sendKeys(insertLinkField, imageLink).build().perform();
    }
    public void clickAddImageButton(){
        //ExplicitWait.explicitWaitUntilElementToBeClickable(EXPLICIT_WAIT_TIMEOUT, addImageButton);
        addImageButton.click();
    }

}
