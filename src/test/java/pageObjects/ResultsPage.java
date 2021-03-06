package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.util.Locators;

public class ResultsPage {
	WebDriver driver;
	Actions actions;
	
	public ResultsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.XPATH, using = Locators.FIRST_PRODUCT_FOUND)
	WebElement productLink;
	@FindBy(how = How.XPATH, using = Locators.PRODUCT_GALLERY_HEADER)
	WebElement resultHeaderText;
	@FindBy(how = How.XPATH, using = Locators.GET_QUOTE_BUTTON)
	WebElement getQuoteButton;
	@FindBy(how = How.XPATH, using = Locators.PAGINATION_ARROW)
	WebElement paginationLink;
	
	public void open_firstResult() {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(productLink));
			if (productLink.isDisplayed()) {
				productLink.click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(resultHeaderText));
				if (resultHeaderText.isDisplayed()) {
					System.out.println("Product opened successfully");
				}
			}
			else {
				System.out.println("No product was found");
			}
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void click_GetQuote() {
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(getQuoteButton));
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(getQuoteButton));
			if (getQuoteButton.isEnabled() && getQuoteButton.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getQuoteButton);
				Thread.sleep(3000);
				getQuoteButton.click();
			}
			else {
				System.out.println("Get quote button not found");
				throw new TimeoutException();
			}
		}
		catch(Exception ex) {
			try {
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(paginationLink));
				if (paginationLink.isEnabled()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paginationLink);
					Thread.sleep(1500);
					paginationLink.click();
					
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(getQuoteButton));
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(getQuoteButton));
					if (getQuoteButton.isEnabled() && getQuoteButton.isDisplayed()) {
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getQuoteButton);
						Thread.sleep(1500);
						getQuoteButton.click();
					}
					else {
						System.out.println("Get quote button not found");
						throw new TimeoutException();
					}
				}
				else {
					System.out.println("Get quote button not found");
					throw new TimeoutException();
				}
			}
			catch(Exception ex1) {
				System.out.println("Get quote button not found");
				throw new TimeoutException();
			}
		}
		
	}

}
