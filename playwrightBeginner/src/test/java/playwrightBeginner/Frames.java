package playwrightBeginner;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class Frames {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		
		page.navigate("https://www.lambdatest.com/selenium-playground/iframe-demo/");
		List<Frame> frames = page.frames();
		System.out.println(frames.size());
		
		FrameLocator frame1 = page.frameLocator("#iFrame1");
		Locator body = frame1.locator(".rsw-ce");
		body.clear();
		body.type("sangavi QA Tester");
		
		FrameLocator frame2 = page.frameLocator("#iFrame2");
		Locator searchBox =frame2.locator("//button[@aria-label='Search']");
		searchBox.click();
		Locator nestedSearch = frame2.locator("#docsearch-input");
		nestedSearch.click();
		nestedSearch.type("My Project Demo");
		
		page.close();
		browser.close();
		playwright.close();
	}

}
