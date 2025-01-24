package playwrightBeginner;


import java.nio.file.Paths;
import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RecordVideoSize;
import com.microsoft.playwright.options.ScreenshotCaret;

public class ScreenshotsAndVideos {

	public static void main(String[] args) {
		
		 Browser browser= Playwright.create().chromium().launch(new LaunchOptions().setHeadless(true));
		 //low resolution video recorded
		// BrowserContext context = browser.newContext(new NewContextOptions()
		//		 .setRecordVideoDir(Paths.get("videos/")));
		 
		 //high resolution video recorded
		 BrowserContext context = browser.newContext(new NewContextOptions()
							 .setRecordVideoDir(Paths.get("videos/highResolution/")).setRecordVideoSize(new RecordVideoSize(1280, 720)));
		 Page page=context.newPage();
		
		page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
		ScreenshotOptions sc = new ScreenshotOptions ();
		
		System.out.println(page.title());
		//screenshot
		page.screenshot(sc.setPath(Paths.get("./screenshots/src.png")));
		
		//full page screenshot
		page.screenshot(sc.setFullPage(true).setPath(Paths.get("./screenshots/fullScreen.jpg")));
		
		//locator screenshot
		Locator heading = page.locator("//h1[text()='Simple Form Demo']");
		heading.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./screenshots/locator.jpg")));
		
		//header screenshot
		Locator headerLoc = page.locator("#header");
		headerLoc.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./screenshots/header.jpg")));
		
		//locator masking
		Locator textInput =page.locator("#user-message").first();
		textInput.type("password");
		page.screenshot(new ScreenshotOptions()
				.setMask(Arrays.asList(textInput))
				.setPath(Paths.get("./screenshots/masked.jpg")));
		
		//show caret
		 textInput.clear();
		 textInput.screenshot(new Locator.ScreenshotOptions()
				 .setCaret(ScreenshotCaret.INITIAL)
				 .setPath(Paths.get("./screenshots/show caret.jpg")));
		 
		//hide caret
		 textInput.screenshot(new Locator.ScreenshotOptions()
				 .setCaret(ScreenshotCaret.HIDE)
				 .setPath(Paths.get("./screenshots/hide caret.jpg")));
		
		page.close();

	}

}
