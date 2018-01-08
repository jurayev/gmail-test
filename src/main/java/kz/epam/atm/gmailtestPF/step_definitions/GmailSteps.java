package kz.epam.atm.gmailtestPF.step_definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import kz.epam.atm.gmailtestPF.bo.Email;
import kz.epam.atm.gmailtestPF.pages.GmailPage;
import kz.epam.atm.gmailtestPF.pages.LoginPage;
import kz.epam.atm.gmailtestPF.steps.GmailPageSteps;
import kz.epam.atm.gmailtestPF.utils.DOMElementPresence;
import kz.epam.atm.gmailtestPF.utils.ExplicitWait;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static kz.epam.atm.gmailtestPF.property.GlobalConstants.*;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.IMAGE_ABSENCE_ERR_MSG;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.INCORRECT_BODY_ERR_MSG;

public class GmailSteps {

    private GmailPage gmailPage;
    private Email email;
    private GmailPageSteps gmailPageSteps = new GmailPageSteps();

    public GmailSteps(){
        gmailPage = new GmailPage();
    }

    @When("^the user clicks compose the email button and fills recipients, the subject, the body$")
    public void compose_new_email(List<String> values){
        email = new Email
                .EmailBuilder(values.get(0),values.get(1))
                .setBody(values.get(2))
                .build();
        gmailPage.composeEmail(email);
    }

    @When("^the user clicks compose the email button and fills recipients, the subject, the body, the image$")
    public void compose_new_email(String recipients,String subject,String body, String image){
        email = new Email
                .EmailBuilder(recipients,subject)
                .setBody(body)
                .setImage(image)
                .build();
        gmailPage.composeEmail(email);
    }

    @And("^the user adds an image to the email body$")
    public void add_image_to_email_body() {
        gmailPage.addImageToEmailBodyFromWeb(email);
    }


    @Then("^the email should be displayed as the first in the draft folder$")
    public void verify_draft_email_is_displayed_as_first_in_draft_folder() {
        gmailPage.clickDraftFolderLink();
        ExplicitWait.explicitWaitVisibilityOfElement(gmailPage.getDraftMailLabel());
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()), DRAFT_EMAIL_ABSENCE_ERR_MSG);
    }

    @And("^the email fields should be displayed with the equal values as user has entered$")
    public void verify_draft_email_equality() {
        Assert.assertEquals(gmailPage.getFirstEmailRecipientsText(), email.getRecipients(), INCORRECT_RECIPIENT_ERR_MSG);
        Assert.assertEquals(gmailPage.getFirstEmailSubjectText(), gmailPageSteps.getSubjectContent(), INCORRECT_SUBJECT_ERR_MSG);
        Assert.assertEquals(gmailPage.getFirstEmailBodyText(), email.getBody(), INCORRECT_BODY_ERR_MSG);
        try {
            Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getImageInsideEmailBody()), IMAGE_ABSENCE_ERR_MSG);
        }catch (NoSuchElementException e){
            System.out.println(IMAGE_ABSENCE_ERR_MSG);
        }
    }

    @Given("^draft email is opened and ready for sending$")
    public void open_draft_email() {
        ExplicitWait.explicitWaitUntilElementToBeClickable(gmailPage.getFirstEmailInList());
        gmailPage.openFirstEmailInList();
    }

    @When("^user clicks send button$")
    public void send_email() {
        gmailPage.sendEmail();
    }

    @Then("^the email shouldn't be displayed in the draft folder$")
    public void verify_email_absence_in_draft_folder() {
        gmailPage.clickDraftFolderLink();
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getEmptyEmailListSign()),DRAFT_EMAIL_PRESENCE_ERR_MSG);
    }

    @And("^the email should be displayed in the sent folder$")
    public void verify_email_existence_in_sent_folder() {
        gmailPage.clickSentFolderLink();
        Assert.assertTrue(DOMElementPresence.isElementPresent(gmailPage.getFirstEmailInList()),EMPTY_SENT_FOLDER_ERR_MSG);
    }
}
