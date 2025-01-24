package playwrightBeginner;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;


/* 
 * Command to open playwright inspector to record
 * 
 * mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://ecommerce-playground.lambdatest.io/index.php?route=common/home"
 * run in terminal
 * 
 */
public class RecordUsingPlaywrightInspector {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
					.setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
			//page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search For Products")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search For Products")).fill("hp");
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
			page.locator("#mz-filter-panel-0-0").getByPlaceholder("Maximum Price").click();
			page.locator("#mz-filter-panel-0-0").getByPlaceholder("Maximum Price").fill("1220");
			page.locator("#mz-filter-panel-0-0").getByPlaceholder("Minimum Price").click();
			page.locator("#mz-filter-panel-0-0").getByPlaceholder("Minimum Price").fill("1000");
			page.locator("#button-search").click();
			page.locator(".product-action > button:nth-child(2)").first().click();
			page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("wish list")).click();
			page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register")).click();
			page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
		}
	}




}
