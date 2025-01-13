package playwrightBeginner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;;

public class LambdaTestWebsiteDemo {

	public static void main(String[] args) {
	
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://ecommerce-playground.lambdatest.io/");
		Locator megaMenu = page.locator(" //*[contains(text(),' Mega Menu')]");
		megaMenu.hover();
		page.locator(" //*[@title='Headphones']").click();
		assertThat(page).hasTitle("Components");
		page.close();
		browser.close();
		playwright.close();

	}

}
