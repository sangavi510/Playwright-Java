package playwrightBeginner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MultipleInputFileds {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser= playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://practice.expandtesting.com/inputs");
		page.locator("#input-number").fill("12764905");
		//locator #input indicates css locator  for id
		//fill will not inertact with event listeners
		page.locator("#input-text").type("Sanguh98");
		page.locator("#input-password").type("Secret123*");
		
		page.locator("#btn-display-inputs").click();
		assertThat(page.locator("#output-number")).hasText("12764905");
		assertThat(page.locator("#output-text")).hasText("Sanguh98");
		assertThat(page.locator("#output-password")).hasText("Secret123*");
		
		
		page.navigate("https://practice.expandtesting.com/form-validation");
		//retrieve input value from field
		String getText = page.locator("#validationCustom01").inputValue();
		System.out.println(getText);
		//clear input field
		page.locator("#validationCustom01").clear();
		System.out.println(getText);
		//get placeholder
		Locator place = page.locator("//input[@id='validationCustom05' and @name ='contactnumber']");
		assertThat(place).hasAttribute("placeholder" , "012-3456789");
		
		//radio buttons
		page.navigate("https://practice.expandtesting.com/radio-buttons");
		Locator red =page.locator("#red");
		assertThat(red).not().isChecked();
		red.check();
		assertThat(red).isChecked();
		
		
		
		playwright.close();
		
		

	}

}
