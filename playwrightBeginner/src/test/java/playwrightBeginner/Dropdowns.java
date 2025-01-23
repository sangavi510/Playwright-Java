package playwrightBeginner;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

public class Dropdowns {

	public static void main(String[] args) {
	
		Page page = Playwright.create().chromium().launch(new LaunchOptions().setHeadless(false)).newPage();
		page.navigate("https://practice.expandtesting.com/dropdown");
		Locator loc1 = page.locator("select#dropdown");
		//select by label
		loc1.selectOption("Option 1");
		assertThat(loc1).containsText("Option 1");
		Locator loc2 = page.locator("select#elementsPerPageSelect");
		//select by value
		loc2.selectOption(new SelectOption().setValue("50"));
		Locator loc3= page.locator("select#country");
		System.out.println(loc3.count());
		//select by index
		loc3.selectOption(new SelectOption().setIndex(5));
		assertThat(loc3).containsText("American Samoa");
		List<String> country = loc3.allInnerTexts();
		country.forEach(n->System.out.println(n));
		
		page.navigate("https://practice.expandtesting.com/jqueryui/menu");
		Locator disabled = page.locator("a#ui-id-1"); 
		assertThat(disabled).hasText("Disabled");
		Locator enabled = page.locator("a#ui-id-2 ul li");
		assertThat(enabled).hasText("Enabled");
		enabled.click();
		Locator download = page.locator("a#ui-id-4");
		download.click();
		Locator pdf = page.locator("a#ui-id-6");
		pdf.click();
		
		page.close();
		
	}

}
