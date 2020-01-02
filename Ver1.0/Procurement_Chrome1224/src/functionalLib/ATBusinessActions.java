package functionalLib;

import java.io.File;




import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Reporting.Report;
//import Reporting.Report;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class ATBusinessActions {
	WebDriver BDriver;
	String sTDFileName;
	String Navtextfiles;
	uiObjInfo.UILocators oUIObj = new uiObjInfo.UILocators();
	public String oParentBrw;
	String Screenshotpath;
	File scrFile;
	String appURL;
	String browser;
	String devicename;
	String platformname;
	String devc;
	String platformversion;
	Report selectReport;
	String Navigationtextfiles;
	public String[] arrMenuPag;
	public String[] arrMenuFilt;
	public String[] arrSortByFilt;
	public static int ChtestcaseNo;

	//Constructor
	public ATBusinessActions(WebDriver BDriver, String strURL, String devicename, String platformname, String devc,
			String platformversion, String browser, Report report) throws IOException {
		sTDFileName = ExcelRead.exlPath();		
		Navtextfiles = report.Navigationtextfiles;
		Screenshotpath = report.strScreenshotPath;
		this.BDriver = BDriver;
		this.appURL = strURL;
		this.devicename = devicename;
		this.platformname = platformname;
		this.devc = devc;
		this.platformversion = platformversion;
		this.browser = browser;
		selectReport = report;
	}

	/*
	 * TC_openApplication Created By GRK Usage: Opening Spirit app in UAT
	 * environment from Excel sheet
	 * 
	 * @Information will read form Excel sheet
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void openApplication() throws IOException, InterruptedException, BiffException, WriteException 
	{
		System.out.println("---" + appURL + "---");

		if(browser.equalsIgnoreCase("Firefox"))
		{
			System.out.println("Inside firefox");
			selectReport.iSleep=500;

			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\Resources\\geckodriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			//capabilities.setVersion("45.0.2");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setCapability("marionette", false);
			try
			{
				BDriver= new FirefoxDriver(capabilities);
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}

			BDriver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			System.out.println("Inside Chrome");
			selectReport.iSleep=500;
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/Resources/chromedriver.exe" );
			BDriver= new ChromeDriver( );
			BDriver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			selectReport.iSleep=2000;
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "/Resources/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			// this line of code is to resolve protected mode issue capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("unexpectedAlertBehaviour", "accept");
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability("enablePersistentHover", false);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("nativeEvents", false);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("requireWindowFocus", true);
			InternetExplorerOptions options = new InternetExplorerOptions(capabilities).requireWindowFocus();
			BDriver = new InternetExplorerDriver(options);			
			BDriver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("Safari"))
		{
			selectReport.iSleep=2000;
			SafariOptions safariOpts = new SafariOptions();
			DesiredCapabilities cap = DesiredCapabilities.safari();

			//((Object) safariOpts).setUseCleanSession(true);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "dismiss");
			cap.setCapability(CapabilityType.SUPPORTS_ALERTS,true);
			cap.setCapability(SafariOptions.CAPABILITY, safariOpts);
			cap.setBrowserName("safari");
			cap.setPlatform(Platform.MAC);

			BDriver = new SafariDriver();

		}

		selectReport.gbDriver = BDriver;
		//System.out.println("Before andoid");
		BDriver.get(appURL);
		System.out.println(BDriver.getTitle());
		if (!browser.contains("IE")) {
			waitforpageload();} else {
				Thread.sleep(5000);}
		if (browser.contains("iOS")) {
			Thread.sleep(12000);}
		selectReport.ReportStep("Pass", "Open Application", "Application should open",
				"Opened " + appURL + " application successfully");
		System.out.println("after report step");
		if (browser.equalsIgnoreCase("IE")) {
			System.out.println("inside IE");
			try {
				BDriver.findElement(By.xpath("//a[contains(@id,'overridelink')]")).click();
				Thread.sleep(4000);
			} catch (Exception ex) {
				System.out.println("");
			}
		}
		new File(Navtextfiles).mkdir();
	}

	/*
	 * waitforpageload Created By GRK Usage: Wait up to load complete page
	 * 
	 * @Information will save into database
	 */
	public void waitforpageload() {
		System.out.println("inside wait load");
		JavascriptExecutor js = (JavascriptExecutor) BDriver;
		for (int i = 0; i < 5000; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	// Implicit wait
	public void pImplicitWait() {
		System.out.println("applying Implict wait");
		BDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	/*
	 * TC_closeApplication Created by GRK Usage: Closing all browsers
	 * 
	 * @Information will read form Excel sheet
	 */
	public void closeApplication() throws InterruptedException {
		Thread.sleep(2000);
		BDriver.quit();
		if (selectReport.curntTestStatus.equalsIgnoreCase("pass")
				&& selectReport.curntVerifysteptStatus.equalsIgnoreCase("pass")) {
			selectReport.noTestsPassed++;
		} else {
			selectReport.noTestsFailed++;
		}
		try {
			selectReport.CloseTestReport();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/*
	 * TC_captureScreenshotall Created by GRK Usage: Capturing Screenshots of the
	 * page
	 * 
	 * @Information For the validation purpose
	 */

	public void captureScreenshotall(String img) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy_HHmmss");
		TakesScreenshot oScn = (TakesScreenshot) BDriver; // Type casting

		//screenshot info will be saved in oScnshot variable (of type file)
		File oScnshot = oScn.getScreenshotAs(OutputType.FILE);

		//Creating a empty image file
		File oDest = new File(Screenshotpath, img + "_" + sdf.format(new Date()) + ".png");
		//Save in a Physical location
		org.apache.maven.surefire.shade.org.codehaus.plexus.util.FileUtils.copyFile(oScnshot, oDest);

	}

	/*
	 * TC_setText Created By GRK Usage: Generaic function for Entering value in
	 * textbox
	 * 
	 * @Information: Reading the value from the Excel Sheet
	 **/
	public void setText(String strLocator, String strvalue) {
		try {
			getElement(strLocator).clear();
			highLight(BDriver, strLocator);
			getElement(strLocator).click();
			getElement(strLocator).sendKeys(strvalue);
			System.out.println("After enter value");
			selectReport.ReportStep("Pass", "Input Text", "Set Text", "Value Entered as " + strvalue);
		} catch (Exception e) {
			System.out.println("Failed to find element " + strLocator); // + strLocator);
			selectReport.ReportStep("Fail", "Input Text", "Set Text", "Text not entered " + strvalue);
			//sreenshotSoftLine(Global.gstrResultPath + "\\setText" + strvalue);
		}
	}

	/*
	 * Error Validation Created By GRK Usage: Generaic function for validation
	 * errors
	 **/
	public void ErrorValidation(String strLocator, String ErrorText) {
		try {
			WebElement ErrorMsg = getElement(strLocator);
			ErrorMsg.isDisplayed();
			//			if (ErrorMsg.isDisplayed()) {
			//				System.out.println("Error validation displayed for given value");
			//				selectReport.ReportStep("Fail", "Validating Message", "Error Text", "Validation as " + ErrorText);
			//				Thread.sleep(3000);
			//				BDriver.close();
			//			} else {
			//				selectReport.ReportStep("Pass", "Validating Message", "No Validation Text",
			//						"Validation Successful ");
			//			}
			//			
			System.out.println("Error validation displayed for given value");
			selectReport.ReportStep("Fail", "Validating Message", "Error Text", "Validation displayed as " + ErrorText);
			//BDriver.close();
			closeApplication();

		} catch (Exception e) {
			System.out.println("No validation displayed and provided data is valid"); // + strLocator);
			selectReport.ReportStep("Pass", "Validating Message", "Error Text", "Validation displayed as " + ErrorText);
		}
	}

	/*
	 * controlClick Created By GRK Usage: Generic function for clicking on the
	 * control
	 * 
	 * @Information:
	 **/
	public void controlClick(String strLocator, String strObjName) {
		WebDriverWait wait = new WebDriverWait(BDriver, 30);
		try {
			wait.until(ExpectedConditions.visibilityOf(getElement(strLocator)));
			highLight(BDriver, strLocator);
			getElement(strLocator).click();
			Thread.sleep(1000);
			System.out.println("Clicked on element " + strObjName);
			selectReport.ReportStep("Pass", "Click Control", "Click Control", "Clicked on control " + strObjName);
		} catch (Exception e) {
			System.err.println("Failed to find element " + e.getMessage());
			selectReport.ReportStep("Fail", "Click Control", "Click Control", "Unable to click(element not found) " + strObjName);
		}
	}

	/*
	 * TC_movetoElement Created By GRK
	 */
	public void MoveToElement(String strLocator, String strObjName) {
		Actions a = new Actions(BDriver);
		try {
			a.moveToElement(getElement(strLocator)).build().perform();
			System.out.println("Hover on the Element " + strObjName);
			selectReport.ReportStep("Pass", "Mouse Hover", "Mouse Hover", "Moved to Element" + strObjName);
		} catch (Exception e) {
			System.err.println("Failed to hover on element " + e.getMessage());
		}
	}

	/*
	 * selectValueforAutoSearch Created By GRK Usage: Generaic function for clicking
	 * on the desired value for autosearch dropdown
	 */
	public void selectFromAutosearch(String strLocator, String value) {
		WebDriverWait wait = new WebDriverWait(BDriver, 30);
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(strLocator)));
			List<WebElement> autoresult = BDriver.findElements(By.xpath(strLocator));
			int size = autoresult.size();
			String eleValue = null;
			for (int i = 0; i <= size; i++) {
				eleValue = autoresult.get(i).getText();
				if (eleValue.equalsIgnoreCase(value)) {
					autoresult.get(i).click();
					break;
				}
				System.out.println(eleValue);
				selectReport.ReportStep("Pass", "autosearchlist", "selected a value", value);
			}
		} catch (Exception e) {
			System.err.println("Failed to select the value from autosearchlist" + e.getMessage());
			selectReport.ReportStep("Fail", "autosearchlist", "Not found", value);
		}

	}

	/*
	 * Dropdown Created by GRK Usage: Generic function to dropdown
	 * 
	 * @Information:
	 **/
	@SuppressWarnings("unused")
	public void SelectValueFromDropdown(String drpdown_xp, String strSelectVal) {
		System.out.println(drpdown_xp);
		System.out.println(strSelectVal);
		Select xsSelectafter = null;
		int iVal = 0;
		try {
			if (BDriver.findElement(By.xpath(drpdown_xp)).isDisplayed()) {
				System.out.println("inside");
				xsSelectafter = new Select(BDriver.findElement(By.xpath(drpdown_xp)));
				List<WebElement> selectitemValues = xsSelectafter.getOptions();
				System.out.println("List of value get from the dropdown");
				System.out.println(selectitemValues);
				for (WebElement itemValues : selectitemValues) {
					if (itemValues.getText().contains(strSelectVal)) {
						//xsSelectafter.selectByValue(itemValues.getText() );
						xsSelectafter.selectByIndex(iVal);
						break;
					}
					iVal = iVal + 1;
				}
				if (false) {
					System.out.println("Specified Value " + strSelectVal + " is not found in the dropdown");
				}

			}
		} catch (Exception e) {
			System.out.println("No dropdown to select " + strSelectVal + "is available");
			selectReport.ReportStep("Fail", "Dropdown selection", "Dropdown found", drpdown_xp);
		}

	}

	/*
	 * Dropdownselection Created By GRK Usage: Generaic function for clicking
	 * on the desired value for autosearch dropdown
	 */

	public void DropdownSelection(String strObject, String listvalue) {
		try {
			Select dropdown= new Select (getElement(strObject));
			System.out.println(dropdown);
			dropdown.selectByVisibleText(listvalue);
			selectReport.ReportStep("Pass", "DropdownSelection", "selected a value", listvalue);

		} catch(Exception e) {
			System.out.println("No dropdown to select " + listvalue + "is available");
			selectReport.ReportStep("Fail", "DropdownSelection", "Value not matched to list", listvalue);
		}

	}

	/*
	 * getElement Created By GRK Usage: Generaic function for finding element bases
	 * on xpath sent
	 * 
	 * @Information:Capturing element
	 **/
	private WebElement getElement(String strLocator) {
		WebElement element = null;
		if (strLocator.startsWith("//")) {
			element = BDriver.findElement(By.xpath(strLocator));
		}
		if (strLocator.startsWith("id")) {
			strLocator = strLocator.substring(strLocator.indexOf('=') + 1, strLocator.length());
			element = BDriver.findElement(By.id(strLocator));
		}
		if (strLocator.toLowerCase().startsWith("name")) {
			strLocator = strLocator.substring(strLocator.indexOf('=') + 1, strLocator.length());
			element = BDriver.findElement(By.name(strLocator));
		}

		if (strLocator.toLowerCase().startsWith("css")) {
			strLocator = strLocator.substring(strLocator.indexOf('=') + 1, strLocator.length());
			element = BDriver.findElement(By.cssSelector(strLocator));
		}

		if (strLocator.toLowerCase().startsWith("link")) {
			strLocator = strLocator.substring(strLocator.indexOf('=') + 1, strLocator.length());
			element = BDriver.findElement(By.linkText(strLocator));
		}

		return element;

	}

	/*
	 * switchchildBrowser Created By GRK Usage: Validate the Switch to another
	 * Browser
	 * 
	 * @Information will read form Excel sheet
	 */
	public void switchchildBrowser() throws Exception {
		oParentBrw = BDriver.getWindowHandle();
		// Get the All Browsers Info
		Set<String> oAllBrws = BDriver.getWindowHandles();
		for (String oEachBrw : oAllBrws) {
			if (!(oEachBrw.equals(oParentBrw))) {
				BDriver.switchTo().window(oEachBrw);
				Thread.sleep(1000);
				System.out.println(BDriver.switchTo().window(oEachBrw).getTitle());

				// BDriver.close();
			}
		}

	}


	/*
	 * Scrolling to element Created By GRK Usage: Generic function for an element to
	 * bring in visible screen
	 */
	public void scrollToEle(String strlocator, String strObjName) {
		JavascriptExecutor js = (JavascriptExecutor) BDriver;
		WebElement element = getElement(strlocator);
		try {
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			System.err.println("Failed to find element " + strObjName + e.getMessage());
		}
	}

	/*
	 * * closeerrorpopup Created By GRK Usage: Entering user name and password from
	 * Excel spread sheet
	 */
	public void closeerrorpopup() throws Exception {
		List<WebElement> we1 = BDriver.findElements(By.xpath("//button[@ng-show='!message.disableCloseButton']"));
		for (int i = 0; i < we1.size(); i++) {
			we1.get(i).click();
			Thread.sleep(500);
		}
	}

	/*
	 * switchToIframe Created By GRK Usage: Checking the ifames and switch to the
	 * iframe
	 */
	public void switchFrame(String strLocator) throws Exception {
		// Fetching iframes count
		int iframes = BDriver.findElements(By.tagName("iframe")).size();
		// Iterating through switching frames o find the required element
		for (int i = 0; i <= iframes; i++) {
			String ftitle = BDriver.switchTo().frame(i).getTitle();
			System.out.println(ftitle);
			BDriver.switchTo().frame(i);
			if (getElement(strLocator).isDisplayed()) {
				System.out.println("Switch to ifame " + "IfameTitle:" + ftitle);
				break;
			} else {
				continue;
			}
		}
	}

	/*
	 * Highlight the element
	 * Created by GRK 
	 * Usage: A generic function for highlighting the control where action is going to perform
	 */
	public void highLight(WebDriver driver, String StrLocator) {
		// Javascriptexecutor to highlight the focused element
		WebElement ele = getElement(StrLocator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: lightyellow; border: 1px solid red;');", ele);
	}
	/*
	 * selectCheckbox Created By GRK 
	 * Usage: Generic function for selecting checkbox
	 **/
	public void selectchk(String strLocator, String strObjName) {
		try {
			if (!getElement(strLocator).isSelected()) {
				Thread.sleep(1000);
				System.out.println("Clicked on element " + strObjName);
				selectReport.ReportStep("Pass", "Click Control", "Click Control", "Clicked on control " + strObjName);
			}
			highLight(BDriver, strLocator);
			getElement(strLocator).click();
		} catch (Exception e) {
			System.err.println("Failed to find element " + strObjName + e.getMessage());
			selectReport.ReportStep("Fail", "Click Control", "Click Control", "Check box not selected " + strObjName);
		}
	}
	//Generic method to select from list
	public void selectFromList(String strLocator, String value) {

		try {

			List<WebElement> autoresult = BDriver.findElements(By.xpath(strLocator));
			int size = autoresult.size();
			String eleValue = null;
			for (int i = 0; i <= size; i++) {
				eleValue = autoresult.get(i).getText();
				if (eleValue.equalsIgnoreCase(value)) {
					autoresult.get(i).click();
					break;
				}
				System.out.println(eleValue);
				selectReport.ReportStep("Pass", "autosearchlist", "selected a value", value);
			}
		}catch (Exception e) {
			System.err.println("Failed to select the value from autosearchlist"+e.getMessage());
			selectReport.ReportStep("Fail", "autosearchlist", "Value not matched", value);
		}
	}

	/*
	 * * TC_Login Created By GRK Usage: Getting user name and password from Excel
	 * spread sheet
	 */
	public void LogIn() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "Login");
		System.out.println("ATTestSC sheet read");
		setText(oUIObj.username_id, oExcelP2P.getCellData("UserName", 1));
		System.out.println("User name entered in username field");
		setText(oUIObj.password_id, oExcelP2P.getCellData("Password", 1));
		System.out.println("Password entered in Password field");
		System.out.println("ATTestSC sheet read");
		controlClick(oUIObj.signin_id, "Sign In");
		System.out.println("clicked Signin button");
	}

	/*
	 * * TC_Logout Created By GRK Usage: Logging out from the site and closing the
	 * driver instance spread sheet
	 */
	/*
	 * public void LogoutClose() throws Exception { pImplicitWait();
	 * controlClick(oUIObj.Logout_xp, "User arrow down");
	 * controlClick(oUIObj.Signout_xp, "Sign Out");
	 * System.out.println("clicked Signout button"); controlClick(oUIObj.Confirm_xp,
	 * "Signing Out confirmation"); System.out.println("Confirmed Signout");
	 * BDriver.close(); }
	 */

	
	
	/*
	 *Generic function to select and Click on on specific record read from
	 * the ADF table or Array/list of values  searched out results.
	 *  
	 */
	public void selectFromTablelist(String strLocator, String Arwlocator1, String Arwlocator2, String value) {
		try {
			List<WebElement> TableList = BDriver.findElements(By.xpath(strLocator));
			int size = TableList.size();
			System.out.println(size);
			String eleValue = null;
			String lastElement = Arwlocator1+((size-1)+1)+Arwlocator2;
			System.out.println(lastElement);
			for (int i = 0; i <size; i++) {			
				eleValue = TableList.get(i).getText();
				System.out.println(eleValue);
				if (eleValue.equalsIgnoreCase(value)) {
					//Payments.get(i).click();
					String arrow=Arwlocator1+(i+1)+Arwlocator2;
					System.out.println(arrow);
					BDriver.findElement(By.xpath(arrow)).click();
					selectReport.ReportStep("Pass", "Select Value", "Clicked on", value);
					break;
				} else {
					System.out.println("Given value:  "+value+" not matched with any of listed values");
				}
				System.out.println("Outside if");
			}
		}
		catch (Exception e) {
			System.err.println("Failed to select the payment from the list" + e.getMessage());
			selectReport.ReportStep("Fail", "Select Payments", "Desired Not found", value);
		}
	}
	

	
	//=======================PO Test Cases======================//

	
	// PRC01- Procurement Simple Requisition entry for yourselves(Goods)
	public void GoodsRequisitionForYourselves() throws Exception {		
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "GoodsRequisition");
		System.out.println("POTestSc_GoodsRequisition sheet read");
		controlClick(oUIObj.home_id, "Home Icon");						
		//controlClick(oUIObj.Procurement_id, "Click Procurement Icon");
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");						
		scrollToEle(oUIObj.GoodsRequisitionLink_xp,"scroll to Goods Requisition Link ");
		//click on goods requisition link
		controlClick(oUIObj.GoodsRequisitionLink_xp, "Click on Goods Requisition Link ");		
		System.out.println("Tesssssssst1");			
		Thread.sleep(1000);
		///Enter all required input fields
		// select Request Type	
		SelectValueFromDropdown(oUIObj.RequisitionType_xp,oExcelP2P.getCellData("RequisitionType", 1));			
		Thread.sleep(1000);
		// Item Description testbox
		setText(oUIObj.ItemDescription_xp, oExcelP2P.getCellData("ItemDescription", 1));						
		Thread.sleep(1000);
		// Category Name
		setText(oUIObj.CategoryName_xp, oExcelP2P.getCellData("CategoryName", 1));
		Thread.sleep(3000);
		// Currency	
		setText(oUIObj.Currency_xp, oExcelP2P.getCellData("Currency", 1));			
		Thread.sleep(1000);
		//Project
		setText(oUIObj.Project_xp, oExcelP2P.getCellData("Project", 1));					
		Thread.sleep(1000);
		// Price			                                              
		setText(oUIObj.Price_xp, oExcelP2P.getCellData("Price", 1));		//for goods	
		Thread.sleep(1000);			
		// Quantity
		setText(oUIObj.Quantity_xp, oExcelP2P.getCellData("Quantity", 1));	
		Thread.sleep(1000);
		// UOM name
		setText(oUIObj.UOMName_xp, oExcelP2P.getCellData("UOMName", 1));			
		Thread.sleep(1000);			
		//Supplier
		setText(oUIObj.Supplier_xp, oExcelP2P.getCellData("Supplier", 1));
		Thread.sleep(1000);						
		// AddtoCart Button
		controlClick(oUIObj.AddtoCartButton_xp, "Click on Add to Cart button ");		
		Thread.sleep(1000);
		//click on open cart 
		controlClick(oUIObj.OpenCart_xp, "Click on Cart to open the added items");			
		Thread.sleep(1000);
		//click on row to be reviewed		
		controlClick(oUIObj.SelectRowspan_xp,"click on row to be reviewed from Cart");	
		//click on Reveiw button
		controlClick(oUIObj.ReviewButton_xp," click on review button from cart");		
		Thread.sleep(1000);
		//click on SaveAndCloseDownArrow			
		controlClick(oUIObj.SaveandCloseDD_xp,"click on SaveAndCloseDownArrow");		
		Thread.sleep(1000);
		//click on SaveAndClose button			
		controlClick(oUIObj.SaveandCloseBtn_xp,"click on SaveAndClose Button");		
		Thread.sleep(1000);
		// click on purchase Requisition Submit
		//controlClick(oUIObj.PurchaseReqSubmitButton_xp," click on review button from cart");				 
	}

	// PRC02- Procurement Simple Requisition entry for yourselves(Services)
	public void ServicesRequisitionForYourselves() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ServicesRequisition");
		System.out.println("POTestSc_ServicesRequisition sheet read");
		controlClick(oUIObj.home_id, "Home Icon");							
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");			
		scrollToEle(oUIObj.ServicesRequisitionLink_xp,"scroll to Services Requisition Link ");
		//click on goods requisition link
		controlClick(oUIObj.ServicesRequisitionLink_xp, "Click on Services Requisition Link ");				
		Thread.sleep(1000);
		//Enter all required input fields /
		// select Request Type			
		setText(oUIObj.RequisitionType_xp, oExcelP2P.getCellData("RequisitionType", 1));						
		Thread.sleep(1000);
		// Item Description testbox
		setText(oUIObj.ItemDescription_xp, oExcelP2P.getCellData("ItemDescription", 1));				
		Thread.sleep(1000);
		// Category Name
		setText(oUIObj.CategoryName_xp, oExcelP2P.getCellData("CategoryName", 1));					
		Thread.sleep(1000);			
		// Currency	
		setText(oUIObj.Currency_xp, oExcelP2P.getCellData("Currency", 1));				
		Thread.sleep(1000);
		//Project
		setText(oUIObj.Project_xp, oExcelP2P.getCellData("Project", 1));					
		Thread.sleep(1000);
		// Price/
		setText(oUIObj.PurchaseReq_ServicesAmount_xp, oExcelP2P.getCellData("Amount", 1));        // for services 		
		Thread.sleep(1000);						
		//Supplier
		setText(oUIObj.Supplier_xp, oExcelP2P.getCellData("Supplier", 1));			
		Thread.sleep(1000);		 			 
		// AddtoCart Button
		controlClick(oUIObj.AddtoCartButton_xp, "Click on Add to Cart button ");		
		Thread.sleep(1000);
		//click on open cart 
		controlClick(oUIObj.OpenCart_xp, "Click on Cart to open the added items");				
		Thread.sleep(1000);
		//click on row to be reviewed
		controlClick(oUIObj.SelectRowspan_xp,"click on row to be reviewed from Cart");			
		//click on Reveiw button			
		controlClick(oUIObj.ReviewButton_xp," click on review button from cart");			
		Thread.sleep(1000);
		//click on SaveAndCloseDownArrow			
		controlClick(oUIObj.SaveandCloseDD_xp,"click on SaveAndCloseDownArrow");				
		Thread.sleep(1000);
		//click on SaveAndClose button			
		controlClick(oUIObj.SaveandCloseBtn_xp,"click on SaveAndClose Button");				
		Thread.sleep(1000);
		// click on purchase Requisition Submit
		//controlClick(oUIObj.PurchaseReqSubmitButton_xp," click on review button from cart");		
	}			

	// PRC03- Create Requisition on someone else's behalf. 
	public void RequisitionOnOthersBehalf() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "RequisitionOnOthersBehalf");
		System.out.println("POTestSc_ServicesRequisition sheet read");
		controlClick(oUIObj.home_id, "Home Icon");						
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");			
		scrollToEle(oUIObj.ServicesRequisitionLink_xp,"scroll to Services Requisition Link ");
		//click on goods requisition link
		controlClick(oUIObj.ServicesRequisitionLink_xp, "Click on Services Requisition Link ");			
		Thread.sleep(1000);
		//Enter all required input fields
		// select Request Type			
		setText(oUIObj.RequisitionType_xp, oExcelP2P.getCellData("RequisitionType", 1));					
		System.out.println("Tesssssssst2");
		Thread.sleep(1000);	
		setText(oUIObj.ItemDescription_xp, oExcelP2P.getCellData("ItemDescription", 1));					
		Thread.sleep(1000);
		// Category Name
		setText(oUIObj.CategoryName_xp, oExcelP2P.getCellData("CategoryName", 1));				
		Thread.sleep(1000);			
		// Currency	
		setText(oUIObj.Currency_xp, oExcelP2P.getCellData("Currency", 1));					
		Thread.sleep(1000);
		//Project
		setText(oUIObj.Project_xp, oExcelP2P.getCellData("Project", 1));					
		Thread.sleep(1000);
		// Price/
		setText(oUIObj.PurchaseReq_ServicesAmount_xp, oExcelP2P.getCellData("Amount", 1));        // for services 	
		Thread.sleep(1000);						
		//Supplier
		setText(oUIObj.Supplier_xp, oExcelP2P.getCellData("Supplier", 1));				
		Thread.sleep(1000);			 
		// AddtoCart Button
		controlClick(oUIObj.AddtoCartButton_xp, "Click on Add to Cart button ");				
		Thread.sleep(1000);
		//click on open cart 
		controlClick(oUIObj.OpenCart_xp, "Click on Cart to open the added items");					
		Thread.sleep(1000);
		//click on row to be reviewed
		controlClick(oUIObj.SelectRowspan_xp,"click on row to be reviewed from Cart");			
		//click on Reveiw button			
		controlClick(oUIObj.ReviewButton_xp," click on review button from cart");			
		Thread.sleep(1000);
		// Change the requester (select employee for whom you are creating requisition)
		setText(oUIObj.RequesterInput_xp, oExcelP2P.getCellData("ChangeRequesterName", 1));
		//click on SaveAndCloseDownArrow			
		controlClick(oUIObj.SaveandCloseDD_xp,"click on SaveAndCloseDownArrow");			
		Thread.sleep(1000);
		//click on SaveAndClose button			
		controlClick(oUIObj.SaveandCloseBtn_xp,"click on SaveAndClose Button");			
		Thread.sleep(1000);
		// click on purchase Requisition Submit
		//controlClick(oUIObj.PurchaseReqSubmitButton_xp," click on review button from cart");		
	}		

	// PROC08:Create PO manually against an approved requisition
	public void CreatePurchaseOrder() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "CreatePurchaseOrder");
		System.out.println("POTestSC_CreatePurchaseOrder sheet read");
		controlClick(oUIObj.home_id, "Home Icon");					
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");
		controlClick(oUIObj.POIcon_xp, "Click on PO Icon");							
		controlClick(oUIObj.POTasks_xp, "Click on Tasks bar under PO ");					
		controlClick(oUIObj.ProcessRequisitions_xp, "Click on ProcessRequisitions from PO Tasks bar");					
		setText(oUIObj.RequisitionInput_xp,oExcelP2P.getCellData("RequisitionNumber",1));				
		controlClick(oUIObj.SearchRequsitionButton_xp, "Click on the Searchbutton");
		scrollToEle(oUIObj.ScrolltoLastcolumn, "scroll to last column");		
		Thread.sleep(3000);
		selectFromTablelist(oUIObj.StrLocator,oUIObj.Arwlocator1,oUIObj.Arwlocator2,oExcelP2P.getCellData("RequisitionNumber",1));
		waitforpageload();
		controlClick(oUIObj.ClickOKbutton_xp, "Click OK button"); 
		Thread.sleep(3000);
		controlClick(oUIObj.ClickCreateButton_xp, "Click Create button"); 
		Thread.sleep(3000);
		controlClick(oUIObj.ClickOKonPopupCOnfirm_xp, "Click OK OnConfirmation Create"); 
		Thread.sleep(5000);
		controlClick(oUIObj.ClickSubmitButton_xp, "Click Submit button"); 
		Thread.sleep(3000);	
		controlClick(oUIObj.ClickOKAfterSubmitbutton_xp, "Click OK confirm Submit"); 
	}		
	
	
	
	
	//PROC13: cancel the Requisition
	public void CancelPurchaseRequisition() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "CancelRequisition");
		System.out.println("POTestSc_CancelRequisition sheet read");
		controlClick(oUIObj.home_id, "Home Icon");						
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");	
		controlClick(oUIObj.ManageRequisitions_xp, "Click Manage Requisitions Link");	
		Thread.sleep(3000);	
		setText(oUIObj.RequisitionNumInput_xp, oExcelP2P.getCellData("RequisitionNumber",1));
		setText(oUIObj.EnteredByInput_xp, oExcelP2P.getCellData("EnteredBy",1));	
		Thread.sleep(3000);
		Thread.sleep(3000);
		SelectValueFromDropdown(oUIObj.StatusLOV_xp, oExcelP2P.getCellData("ReqStatus",1));
		Thread.sleep(3000);
		controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
		Thread.sleep(5000);
		controlClick(oUIObj.ReqNumLine1_xp, "select the record line"); 
		Thread.sleep(3000);
		controlClick(oUIObj.ReqNumLink_xp, "click the record value");		
		Thread.sleep(5000);
		controlClick(oUIObj.ClickActions_xp, "click the Actions link"); 
		Thread.sleep(3000);
		controlClick(oUIObj.CancelRequisition_xp, "Cancel Requisition link"); 
		setText(oUIObj.EnterCancelReason_xp,oExcelP2P.getCellData("CancelReason",1));
		controlClick(oUIObj.OkbuttonCancelReason_xp, "OK to close reason box"); 						
		Thread.sleep(3000);
		controlClick(oUIObj.ConfirmButtonOK_xp, "click ConfirmOK to cancel requisition");
		Thread.sleep(3000);
		String StatusVerified = BDriver.findElement(By.xpath(oUIObj.VerifyStatus_xp)).getText();		
		System.out.println("Status After cancelling the requisition: "+ StatusVerified);	
		controlClick(oUIObj.ClickDone_xp, "click Done"); 	
	}
	
	
	
	//PROC14:  Buyer able to view all Requisitions
	public void SearchAndViewRequisitions() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "CancelRequisition");
		System.out.println("POTestSc sheet read");
		controlClick(oUIObj.home_id, "Home Icon");						
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");	
		controlClick(oUIObj.ManageRequisitions_xp, "Click Manage Requisitions Link");			
		Thread.sleep(3000);	
		//Requisition number
		setText(oUIObj.RequisitionNumInput_xp, oExcelP2P.getCellData("RequisitionNumber",1));
		Thread.sleep(3000);			
		//enteredBy
		setText(oUIObj.EnteredByInput_xp, oExcelP2P.getCellData("EnteredBy",1));
		Thread.sleep(3000);
		SelectValueFromDropdown(oUIObj.StatusLOV_xp, oExcelP2P.getCellData("ReqStatus",1));
		Thread.sleep(3000);
		controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
		Thread.sleep(5000);		
		scrollToEle(oUIObj.EndofManageReqTableResults, "scroll to last column");	
		List<WebElement> TableList = BDriver.findElements(By.xpath(oUIObj.ManageReqTableResults1));							
		int size = TableList.size();
		System.out.println("Number of records searchedout are  :" + size);			
		Thread.sleep(3000);
		controlClick(oUIObj.ClickDone_xp, "click Done");
	}

		
		
	// PRC04- Simple Requisition entry for yourselves. For a 'New' supplier.
	public void RequisitionWithNewSupplier() throws Exception {		
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ReqWithNewSupplier");
		System.out.println("POTestSc sheet read");
		controlClick(oUIObj.home_id, "Home Icon");									
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");						
		scrollToEle(oUIObj.GoodsRequisitionLink_xp,"scroll to Goods Requisition Link ");
		//click on goods requisition link
		controlClick(oUIObj.GoodsRequisitionLink_xp, "Click on Goods Requisition Link ");						
		Thread.sleep(1000);
		//Enter all required input fields
		// select Request Type	
		SelectValueFromDropdown(oUIObj.RequisitionType_xp,oExcelP2P.getCellData("RequisitionType", 1));						
		Thread.sleep(1000);
		// Item Description testbox
		setText(oUIObj.ItemDescription_xp, oExcelP2P.getCellData("ItemDescription", 1));							
		Thread.sleep(1000);
		// Category Name
		setText(oUIObj.CategoryName_xp, oExcelP2P.getCellData("CategoryName", 1));			
		Thread.sleep(3000);
		// Currency	
		setText(oUIObj.Currency_xp, oExcelP2P.getCellData("Currency", 1));						
		Thread.sleep(1000);
		//Project
		setText(oUIObj.Project_xp, oExcelP2P.getCellData("Project", 1));						
		Thread.sleep(1000);
		// Price			                                              
		setText(oUIObj.Price_xp, oExcelP2P.getCellData("Price", 1));	
		Thread.sleep(1000);			
		// Quantity
		setText(oUIObj.Quantity_xp, oExcelP2P.getCellData("Quantity", 1));			
		Thread.sleep(1000);
		// UOM name
		setText(oUIObj.UOMName_xp, oExcelP2P.getCellData("UOMName", 1));			
		Thread.sleep(1000);								
		//select NewSupplier check box
		selectchk(oUIObj.NewSupplierlabel_xp, "Select the New supplier check box");		
		Thread.sleep(5000);	
		//enter Supplier
		setText(oUIObj.NewSupplierinputbox_xp, oExcelP2P.getCellData("NewSupplier", 1));
		Thread.sleep(3000);	
		System.out.println("enter New supplier name ");
		//enter SupplierSite
		setText(oUIObj.SupplierSite_xp, oExcelP2P.getCellData("SupplierSite", 1));
		System.out.println("enter supplierSite name ");									
		Thread.sleep(3000);						
		// AddtoCart Button
		controlClick(oUIObj.AddtoCartButton_xp, "Click on Add to Cart button ");					
		Thread.sleep(1000);
		//click on open cart 
		controlClick(oUIObj.OpenCart_xp, "Click on Cart to open the added items");						
		Thread.sleep(1000);
		//click on row to be reviewed		
		controlClick(oUIObj.SelectRowspan_xp,"click on row to be reviewed from Cart");			
		//click on Review button
		controlClick(oUIObj.ReviewButton_xp," click on review button from cart");				
		Thread.sleep(1000);
		//click on SaveAndCloseDownArrow			
		controlClick(oUIObj.SaveandCloseDD_xp,"click on SaveAndCloseDownArrow");					
		Thread.sleep(1000);
		//click on SaveAndClose button			
		controlClick(oUIObj.SaveandCloseBtn_xp,"click on SaveAndClose Button");			
		Thread.sleep(5000);
		// click on purchase Requisition Submit
		//controlClick(oUIObj.PurchaseReqSubmitButton_xp," click on submit button from cart");				
		controlClick(oUIObj.ConfirmButtonOK_xp2, "Click OK confirm "); 
	}

	
	//PRC017-  Re-Assign one or Multiple Requisition to different Preparer to process	
	public void ReAssignPurchaseRequisition() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ReAssignRequisition");
		System.out.println("POTestSc sheet read");
		controlClick(oUIObj.home_id, "Home Icon");						
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");	
		controlClick(oUIObj.ManageRequisitions_xp, "Click Manage Requisitions Link");	
		Thread.sleep(3000);
		setText(oUIObj.EnteredByInput_xp, oExcelP2P.getCellData("EnteredBy",1));			
		Thread.sleep(3000);				
		SelectValueFromDropdown(oUIObj.StatusLOV_xp, oExcelP2P.getCellData("ReqStatus",1));
		Thread.sleep(3000);	
		setText(oUIObj.RequisitionNumInput_xp, oExcelP2P.getCellData("RequisitionNumber",1));
		Thread.sleep(5000);			
		controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
		Thread.sleep(5000);
		controlClick(oUIObj.ReqNumLine1_xp, "select the record line");				 
		Thread.sleep(3000);
		controlClick(oUIObj.ReqNumLink_xp, "click the record value");												
		Thread.sleep(5000);
		controlClick(oUIObj.ClickActions_xp, "click the Actions link"); 
		Thread.sleep(3000);
		controlClick(oUIObj.ReaasignInActions_xp, "Click Reassign Requisition "); 
		Thread.sleep(3000);
		setText(oUIObj.InputReAssignTo_xp,	oExcelP2P.getCellData("ReAssignTo",1));
		BDriver.findElement(By.xpath(oUIObj.InputReAssignTo_xp)).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(5000);
		controlClick(oUIObj.ClickOKReassign_xp, "OK to close reassign popup box"); 						
		Thread.sleep(3000);
		String ConfirmationText = BDriver.findElement(By.xpath(oUIObj.ConfirmationDialog_xp)).getText();		
		System.out.println(ConfirmationText);
		Thread.sleep(3000);
		controlClick(oUIObj.ConfirmButtonOK_xp2, "click Confirm OK");				
		Thread.sleep(3000);
		controlClick(oUIObj.ClickDone_xp, "click Done"); 	
	}

		
	//PROC47:  Validation of Status and all Reagents fields data in a Requisitions chemcart 

	public void ValidateReagentsRequisition() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ReagentsRequisition");
		System.out.println("POTestSc sheet read");
		controlClick(oUIObj.home_id, "Home Icon");						
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");	
		controlClick(oUIObj.ManageRequisitions_xp, "Click Manage Requisitions Link");					
		Thread.sleep(3000);		
		//Requisition number
		setText(oUIObj.RequisitionNumInput_xp, oExcelP2P.getCellData("RequisitionNumber",1));
		Thread.sleep(3000);			
		SelectValueFromDropdown(oUIObj.StatusLOV_xp, oExcelP2P.getCellData("ReqStatus",1));
		Thread.sleep(3000);
		//enteredBy
		setText(oUIObj.EnteredByInput_xp, oExcelP2P.getCellData("EnteredBy",1));	
		Thread.sleep(3000);
		controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
		Thread.sleep(5000);
		controlClick(oUIObj.ReqNumLine1_xp, "select the record line");			 
		Thread.sleep(3000);
		controlClick(oUIObj.ReqNumLink_xp, "click the record value");					
		//verify status of requisition
		String StatusVerified = BDriver.findElement(By.xpath(oUIObj.VerifyStatus_xp)).getText();		
		System.out.println("Status of the requisition: "+ StatusVerified);	
		Thread.sleep(3000);
		scrollToEle(oUIObj.Reagents_xp, "scroll to last Regents fields");
		Thread.sleep(3000);											
		List<WebElement> TableList = BDriver.findElements(By.xpath(oUIObj.Reagents_xp));
		int size = TableList.size();
		System.out.println("Number of reagent fields searchedout are  :" + size);					
		java.util.Iterator<WebElement> i = TableList.iterator();
		while(i.hasNext()) {
			WebElement Reagentfield = i.next();
			//System.out.println(row);
			System.out.println("Reagentfield "+Reagentfield.getText());
		}
		Thread.sleep(3000);
		controlClick(oUIObj.ClickDone_xp, "click Done"); 	
	}


	//PROC19:  Able to receive the goods through PO  when Quantity is exactly as desired Qty
	//PROC44:  able to receive the goods through PO  when Quantity is within ToleranceLimits
	public void ReceiveGoodsThroughPO() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ReceiveWithin");
		System.out.println("POTestSc sheet read");
		controlClick(oUIObj.home_id, "Home Icon");						
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");	
		Thread.sleep(3000);
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");
		Thread.sleep(5000);
		controlClick(oUIObj.MyReceiptsIcon_xp, "Click on My Receipts Icon");	
		Thread.sleep(3000);
		setText(oUIObj.ReceiveItems_RequestorInput_xp, oExcelP2P.getCellData("EnteredBy",1));
		Thread.sleep(3000);
		//setText(oUIObj.ReceiveItems_POInput_xp, oExcelP2P.getCellData("PONumber",1));
		Thread.sleep(3000);
		setText(oUIObj.ReceiveItems_Requisition_xp, oExcelP2P.getCellData("RequisitionNumber",1));
		Thread.sleep(3000);
		controlClick(oUIObj.ReceiveItems_SearchButton_xp, "Click On search button");
		Thread.sleep(3000);		
		controlClick(oUIObj.ReqNumLine1_xp, "select the record line");							 
		Thread.sleep(3000);
		//controlClick(oUIObj.ReceiveItems_ReqRecordLink_xp, "click the record value");
		//controlClick(oUIObj.ReceiveItems_PORecordLink_xp, "click the record value");
		String RequisitionNum = BDriver.findElement(By.xpath(oUIObj.ReceiveItems_ReqRecordLink_xp)).getText();
		String POnumber = BDriver.findElement(By.xpath(oUIObj.ReceiveItems_PORecordLink_xp)).getText();
		System.out.println("Requisition Number: "+ RequisitionNum);				
		System.out.println("Purchase Order NUmber:  "+ POnumber);
		Thread.sleep(5000);
		controlClick(oUIObj.ClickReceive_xp, "Click Receive button");	
		setText(oUIObj.EnterQuantity_xp, oExcelP2P.getCellData("ReceiptQuantity",1));
		//setText(oUIObj.TxnDate_xp, "12/4/19 9:30 PM");
		Thread.sleep(3000);
		controlClick(oUIObj.CreateReceipts_SubmitButton_xp, "Click Submit Button");
		Thread.sleep(3000);
		String ReceiptsConfirmMessage = BDriver.findElement(By.xpath(oUIObj.Confirm_dialog_xp)).getText();
		System.out.println("Receipts Confirm Message : "+ ReceiptsConfirmMessage);	
		Thread.sleep(3000);
		controlClick(oUIObj.ClickConfirmOK_xp, "Click OK on confirm message");	
	}
			

	//PROC21:  validate for an warning message when receive the goods through PO  OutsideToleranceLimits
	public void ReceiveGoodsOutsideToleranceLimits() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ReceiveOutside");
		System.out.println("POTestSc sheet read");
		controlClick(oUIObj.home_id, "Home Icon");						
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");	
		Thread.sleep(3000);
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");
		Thread.sleep(5000);
		controlClick(oUIObj.MyReceiptsIcon_xp, "Click on My Receipts Icon");	
		Thread.sleep(3000);
		setText(oUIObj.ReceiveItems_RequestorInput_xp, oExcelP2P.getCellData("EnteredBy",1));
		Thread.sleep(3000);
		//setText(oUIObj.ReceiveItems_POInput_xp, oExcelP2P.getCellData("PONumber",1));
		Thread.sleep(3000);
		setText(oUIObj.ReceiveItems_Requisition_xp, oExcelP2P.getCellData("RequisitionNumber",1));
		Thread.sleep(3000);
		controlClick(oUIObj.ReceiveItems_SearchButton_xp, "Click On search button");				
		Thread.sleep(5000);
		controlClick(oUIObj.ReqNumLine1_xp, "select the record line");							 
		Thread.sleep(3000);
		//controlClick(oUIObj.ReceiveItems_ReqRecordLink_xp, "click the record value");
		//controlClick(oUIObj.ReceiveItems_PORecordLink_xp, "click the record value");
		String RequisitionNum = BDriver.findElement(By.xpath(oUIObj.ReceiveItems_ReqRecordLink_xp)).getText();
		String POnumber = BDriver.findElement(By.xpath(oUIObj.ReceiveItems_PORecordLink_xp)).getText();
		System.out.println("Requisition Number: "+ RequisitionNum);				
		System.out.println("Purchase Order NUmber:  "+ POnumber);
		Thread.sleep(5000);
		controlClick(oUIObj.ClickReceive_xp, "Click Receive button");	
		setText(oUIObj.EnterQuantity_xp, oExcelP2P.getCellData("ReceiptQuantity",1));
		//setText(oUIObj.TxnDate_xp, "12/4/19 9:30 PM");
		Thread.sleep(3000);
		controlClick(oUIObj.CreateReceipts_SubmitButton_xp, "Click Submit Button");
		Thread.sleep(3000);
		String ReceiptsWarningMessage = BDriver.findElement(By.xpath(oUIObj.ReceiveWarningMessge1_xp)).getText();
		System.out.println("Receipts Confirm Message : "+ ReceiptsWarningMessage);	
		Thread.sleep(3000);
		controlClick(oUIObj.ClickOK_ReceiveWarningMessge1_xp, "Click OK on confirm message to close it");	
		controlClick(oUIObj.CreateReceipts_CancelButton_xp, "Click Cancel button of receiptsTxn");	
	}

	//PROC20:  validate for an warning message when receive the goods through PO  OutsideToleranceLimits
	public void ReturntheGoods() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ReturntheGoods");
		System.out.println("POTestSc sheet read");
		controlClick(oUIObj.home_id, "Home Icon");	
		Thread.sleep(3000);
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");	
		Thread.sleep(3000);
		controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");
		Thread.sleep(5000);
		controlClick(oUIObj.MyReceiptsIcon_xp, "Click on My Receipts Icon");	
		Thread.sleep(3000);		
		controlClick(oUIObj.MyReceiptsTasks_xp, "Click on Tasks bar under My Receipts ");
		Thread.sleep(3000);		
		controlClick(oUIObj.ManageReceipts_xp, "Click on Manage Receipts under Tasks bar");
		Thread.sleep(3000);		
		setText(oUIObj.ReceiptsNumInput_xp, oExcelP2P.getCellData("ReceiptNumber",1)); 
		Thread.sleep(3000);		
		//setText(oUIObj.PONumInput_xp, oExcelP2P.getCellData("PONumber",1)); 
		Thread.sleep(3000);		
		controlClick(oUIObj.SearchButtonReceipts_xp, "Click on Search for rceipts");
		Thread.sleep(3000);		
		controlClick(oUIObj.resultsArray_xp, "select first record from Searchout receipts");
		Thread.sleep(3000);	
		controlClick(oUIObj.ReturnButton_xp, "click on Returns button");
		Thread.sleep(3000);	
		setText(oUIObj.ReturnQty_xp, oExcelP2P.getCellData("ReturnQuantity",1));  
		Thread.sleep(3000);	
		setText(oUIObj.ReturnReason_xp, oExcelP2P.getCellData("ReturnReason",1));   
		Thread.sleep(3000);	
		controlClick(oUIObj.SubmitButton_xp, "click on Submit returns button");
		Thread.sleep(3000);	
		String ReturnsConfirmMessage = BDriver.findElement(By.xpath(oUIObj.ConfimationDialog_xp)).getText();
		System.out.println("Returns Confirm Message : "+ ReturnsConfirmMessage);
		Thread.sleep(3000);	
		controlClick(oUIObj.OKButton_xp, "click on Ok on confirmation page");
		Thread.sleep(3000);	
		controlClick(oUIObj.ClickDone_xp, "click on Done button");			
	}		
	
	//PROC25 --Create an adhoc PO
	public void CreateAnAdhocPO() throws Exception {
		pImplicitWait();
		ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "CreateAnAdhocPO");
		System.out.println("POTestSC sheet read");
		controlClick(oUIObj.home_id, "Home Icon");					
		controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");
		controlClick(oUIObj.POIcon_xp, "Click on PO Icon");							
		controlClick(oUIObj.POTasks_xp, "Click on Tasks bar under PO ");	
		Thread.sleep(3000);
		controlClick(oUIObj.CreateOrder_xp, "Click on Create Order from PO Tasks bar");	
		Thread.sleep(3000);		
		setText(oUIObj.clickStyleLOV_xp,oExcelP2P.getCellData("POStyle", 1));
		Thread.sleep(3000);
		BDriver.findElement(By.xpath(oUIObj.clickStyleLOV_xp)).sendKeys(Keys.ARROW_DOWN);
		BDriver.findElement(By.xpath(oUIObj.clickStyleLOV_xp)).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		SelectValueFromDropdown(oUIObj.CreatePOProcurementBU_xp,oExcelP2P.getCellData("ProcurementBU", 1));
		Thread.sleep(3000);
		SelectValueFromDropdown(oUIObj.CreatePORequisitionBU_xp,oExcelP2P.getCellData("RequisitionBU", 1));
		Thread.sleep(3000);
		controlClick(oUIObj.CreatePO_createButton_xp, "Click on the Createbutton");
		Thread.sleep(3000);		
		setText(oUIObj.CreatePO_supplierInput_xp,oExcelP2P.getCellData("Supplier", 1));
		BDriver.findElement(By.xpath(oUIObj.CreatePO_supplierInput_xp)).sendKeys(Keys.ARROW_DOWN);
		BDriver.findElement(By.xpath(oUIObj.CreatePO_supplierInput_xp)).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		setText(oUIObj.CreatePO_Currency_xp,oExcelP2P.getCellData("Currency", 1));	
		Thread.sleep(3000);
		setText(oUIObj.CreatePO_supplierSiteInput_xp,oExcelP2P.getCellData("SupplierSite", 1));
		Thread.sleep(3000);				
		setText(oUIObj.CreatePO_BuyerName_xp,oExcelP2P.getCellData("BuyerName", 1));
		BDriver.findElement(By.xpath(oUIObj.CreatePO_BuyerName_xp)).sendKeys(Keys.ARROW_DOWN);
		BDriver.findElement(By.xpath(oUIObj.CreatePO_BuyerName_xp)).sendKeys(Keys.ENTER);
		Thread.sleep(3000);	
		scrollToEle(oUIObj.ClcikAddIcon_xp, "scroll to add lines "); 
		Thread.sleep(3000);
		int NumOfPOLinestoAdd = oExcelP2P.Row_Count-1;
		System.out.println("number of PO lines to add: "+NumOfPOLinestoAdd);		
		for(int i=1; i<=NumOfPOLinestoAdd;i++) {			
		System.out.println("adding line "+i+" details");
		controlClick(oUIObj.ClcikAddIcon_xp, "Click on AddLines icon");
		Thread.sleep(3000);		
		//enter the line details input 
		setText(oUIObj.LineType_xp, oExcelP2P.getCellData("LineType", i));
		Thread.sleep(3000);	
		BDriver.findElement(By.xpath(oUIObj.LineType_xp)).sendKeys(Keys.ARROW_DOWN);
		BDriver.findElement(By.xpath(oUIObj.LineType_xp)).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		setText(oUIObj.LineDescription_xp, oExcelP2P.getCellData("LineDescription", i));
		Thread.sleep(3000);
		//setText(oUIObj.LineSupplierItem_xp, ""); // not mandatory field
		//Thread.sleep(3000);
		setText(oUIObj.LineQuantity_xp, oExcelP2P.getCellData("LineQuantity", i)); 
		Thread.sleep(3000);
		scrollToEle(oUIObj.BasePrice_xp, "scroll to Base Price filed ");	
		Thread.sleep(5000);
		setText(oUIObj.LineUOM_xp, oExcelP2P.getCellData("LineUOM", i)); 			
		Thread.sleep(3000);	
		BDriver.findElement(By.xpath(oUIObj.LineUOM_xp)).sendKeys(Keys.ARROW_DOWN);
		BDriver.findElement(By.xpath(oUIObj.LineUOM_xp)).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		setText(oUIObj.BasePrice_xp, oExcelP2P.getCellData("BasePrice", i));
		Thread.sleep(2000);		
		setText(oUIObj.LineCategory_xp, oExcelP2P.getCellData("LineCategory", i));
		Thread.sleep(5000);	
		BDriver.findElement(By.xpath(oUIObj.LineCategory_xp)).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		BDriver.findElement(By.xpath(oUIObj.LineCategory_xp)).sendKeys(Keys.ENTER);
		//Thread.sleep(4000);			
		//scrollToEle(oUIObj.Location_xp, "scroll to Location filed "); 
		//setText(oUIObj.Location_xp, ""); // not mandatory field
		Thread.sleep(6000);
		//setText(oUIObj.ShipToOrg_xp, ""); // not mandatory field
		//Thread.sleep(3000);
		//scrollToEle(oUIObj.DeliverToLoc_xp, "scroll to DeliverToLoc filed "); 
		//setText(oUIObj.DeliverToLoc_xp, ""); // not mandatory field
		//Thread.sleep(3000);
		//setText(oUIObj.MatchApprovalsLevel_xp, "");  // not mandatory field
		//Thread.sleep(3000);
		scrollToEle(oUIObj.POChanrgeAccount_xp, "scroll to POChanrgeAccount  filed "); 
		//setText(oUIObj.InvoiceMatchOption_xp, ""); // not mandatory field
		Thread.sleep(3000);
		//setText(oUIObj.POChanrgeAccount_xp, "");// not mandatory field		
		scrollToEle(oUIObj.LineType_xp, "scroll to LineType filed again ");	
		Thread.sleep(3000);	
		}		
		controlClick(oUIObj.ClickSaveButton_xp,"click on SaveButton");	
		String EditPOdocHeader = BDriver.findElement(By.xpath(oUIObj.EditPODocHeader_xp)).getText();
		String CreatePONumber= BDriver.findElement(By.xpath(oUIObj.EditPODocHeader_xp)).getText().substring(32);
		System.out.println("PO Header title : "+ EditPOdocHeader);
		System.out.println("PO Number Created : "+ CreatePONumber);
		oExcelP2P.writeToExistingExcel(sTDFileName, "CreateAnAdhocPO", 20, 1, CreatePONumber);		
		//click on SaveAndClose button		
		controlClick(oUIObj.SaveDD_xp,"click on SaveButton downArrow");	
		Thread.sleep(3000);
		controlClick(oUIObj.SaveandCloseBtn_xp,"click on SaveAndClose Button");	
		Thread.sleep(3000);
		controlClick(oUIObj.ConfirmButtonOK_xp2,"click Ok Button on confirmation");			
		//finally submit the PO
		//Thread.sleep(3000);  //Not submitting now
		controlClick(oUIObj.ClickSubmitButton_xp, "Click Submit button"); 
		//Thread.sleep(3000);	
		controlClick(oUIObj.ClickOKAfterSubmitbutton_xp, "Click OK confirm Submit"); 
	}		
	
	
	//PROC10:  Communicate PO
		public void CommunicatePO() throws Exception {
			pImplicitWait();
			ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "CommunicatePO");
			System.out.println("POTestSc sheet read");
			controlClick(oUIObj.home_id, "Home Icon");						
			controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");
			controlClick(oUIObj.POIcon_xp, "Click on PO Icon");							
			controlClick(oUIObj.POTasks_xp, "Click on Tasks bar under PO ");	
			Thread.sleep(3000);
			controlClick(oUIObj.ManageOrder_xp, "Click on Mange Order from PO Tasks bar");	
			Thread.sleep(3000);		
			//Enter Search criteria PO# or status or buyer
			//Requisition number
			setText(oUIObj.PurchaseOrderInput_xp, oExcelP2P.getCellData("PONumber", 1)); 
			Thread.sleep(3000);									
			//enteredBy
			setText(oUIObj.BuyerInput_xp, oExcelP2P.getCellData("BuyerName", 1)); 
			Thread.sleep(4000);
			SelectValueFromDropdown(oUIObj.Status_xp, oExcelP2P.getCellData("POStatus", 1)); 
			Thread.sleep(4000);
			SelectValueFromDropdown(oUIObj.ProcuremntBUInput_xp, oExcelP2P.getCellData("ProcuremntBU", 1)); 			
			Thread.sleep(4000);
			controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
			Thread.sleep(5000);		
			//scrollToEle(oUIObj.EndofManageReqTableResults, "scroll to last column");
			//Thread.sleep(3000);
			controlClick(oUIObj.ResultsFirstRow_xp, "select the first row of results");
			Thread.sleep(3000);
			controlClick(oUIObj.PONumLink_xp, "click on the PO number link to open from results");
			Thread.sleep(3000);
			controlClick(oUIObj.ClickActions_xp, "click on Actions ");
			Thread.sleep(3000);
			controlClick(oUIObj.POCommunicate_xp, "click on Communicate link ");
			Thread.sleep(3000);
			SelectValueFromDropdown(oUIObj.SelectCommuMethodEMail_xp, oExcelP2P.getCellData("CommuMethod", 1));  
			Thread.sleep(3000);
			if(oExcelP2P.getCellData("CommuMethod", 1).equalsIgnoreCase("E-Mail")) {
				setText(oUIObj.EnterEmail_xp, oExcelP2P.getCellData("EmailID", 1));
				Thread.sleep(3000);
			}
			else if(oExcelP2P.getCellData("CommuMethod", 1).equalsIgnoreCase("Fax")) {
				setText(oUIObj.EnterFax_xp, oExcelP2P.getCellData("FaxNum", 1));  // not mandatory
				Thread.sleep(3000);
			}			
			controlClick(oUIObj.ClickOK_xp, "click on OK");
			Thread.sleep(3000);	
			String ConfirmationMessage= BDriver.findElement(By.xpath(oUIObj.ConfirmationMessage_xp)).getText();
			System.out.println("PO Header title : "+ ConfirmationMessage);		
			Thread.sleep(3000);
			controlClick(oUIObj.ConfirmButtonOK_xp2,"click Ok Button on confirmation");			
		}
	
		//PROC09:  View Order life Cycle for a PO
		public void ViewPOLifeCycle() throws Exception {
			pImplicitWait();
			ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ViewPOLifeCycle");
			System.out.println("POTestSc sheet read");
			controlClick(oUIObj.home_id, "Home Icon");						
			controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");
			controlClick(oUIObj.POIcon_xp, "Click on PO Icon");							
			controlClick(oUIObj.POTasks_xp, "Click on Tasks bar under PO ");	
			Thread.sleep(3000);
			controlClick(oUIObj.ManageOrder_xp, "Click on Mange Order from PO Tasks bar");	
			Thread.sleep(3000);		
			//Enter Search criteria PO# or status or buyer
			//Requisition number
			setText(oUIObj.PurchaseOrderInput_xp, oExcelP2P.getCellData("PONumber", 1)); 
			Thread.sleep(3000);									
			//enteredBy
			setText(oUIObj.BuyerInput_xp, oExcelP2P.getCellData("BuyerName", 1)); 
			Thread.sleep(4000);
			SelectValueFromDropdown(oUIObj.Status_xp, oExcelP2P.getCellData("POStatus", 1)); 
			Thread.sleep(4000);
			SelectValueFromDropdown(oUIObj.ProcuremntBUInput_xp, oExcelP2P.getCellData("ProcuremntBU", 1)); 			
			Thread.sleep(4000);
			controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
			Thread.sleep(5000);		
			//scrollToEle(oUIObj.EndofManageReqTableResults, "scroll to last column");
			//Thread.sleep(3000);
			controlClick(oUIObj.ResultsFirstRow_xp, "select the first row of results");
			Thread.sleep(3000);
			controlClick(oUIObj.PONumLink_xp, "click on the PO number link to open from results");
			Thread.sleep(3000);				
			controlClick(oUIObj.POLifeCycle_xp, "click on Order lifecycle header");
			Thread.sleep(3000);
			controlClick(oUIObj.ViewDetailsBtn_xp, "click on View Details button of Order Life Cycle");			
			String OrderedAmount= BDriver.findElement(By.xpath(oUIObj.OrderedAmountText_xp)).getText();
			System.out.println("PO Amount :"+ OrderedAmount);	
			Thread.sleep(3000);
			controlClick(oUIObj.ClickDone_xp, "click Done on the PO life cycle page");
		}

		
		
		
		//PROC15:  Cancel PO line
		public void CancelPOline() throws Exception {
			pImplicitWait();
			ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "CancelPOline");
			System.out.println("POTestSc sheet read");
			controlClick(oUIObj.home_id, "Home Icon");						
			controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");
			controlClick(oUIObj.POIcon_xp, "Click on PO Icon");							
			controlClick(oUIObj.POTasks_xp, "Click on Tasks bar under PO ");	
			Thread.sleep(3000);
			controlClick(oUIObj.ManageOrder_xp, "Click on Mange Order from PO Tasks bar");	
			Thread.sleep(3000);		
			//Enter Search criteria PO# or status or buyer
			//Requisition number
			setText(oUIObj.PurchaseOrderInput_xp, oExcelP2P.getCellData("PONumber", 1)); 
			Thread.sleep(3000);									
			//enteredBy
			setText(oUIObj.BuyerInput_xp, oExcelP2P.getCellData("BuyerName", 1)); 
			Thread.sleep(4000);
			SelectValueFromDropdown(oUIObj.Status_xp, oExcelP2P.getCellData("POStatus", 1)); 
			Thread.sleep(4000);
			SelectValueFromDropdown(oUIObj.ProcuremntBUInput_xp, oExcelP2P.getCellData("ProcuremntBU", 1)); 			
			Thread.sleep(4000);
			controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
			Thread.sleep(5000);		
			controlClick(oUIObj.ResultsFirstRow_xp, "select the first row of results");
			Thread.sleep(3000);
			controlClick(oUIObj.PONumLink_xp, "click on the PO number link to open from results");
			Thread.sleep(3000);									
			scrollToEle(oUIObj.POLines_All_xp, "scroll to PO lines Tab");
			Thread.sleep(3000);									
			selectFromTablelist(oUIObj.POLinesNum_All_xp,oUIObj.POLinesNumlocator1,oUIObj.POLinesNumlocator2,oExcelP2P.getCellData("POLineNumber",1));
			Thread.sleep(3000);						
			controlClick(oUIObj.ClickLinesActions_xp, "Click Lines Actions"); 
			Thread.sleep(3000);		
			controlClick(oUIObj.ClickCancelLine_xp, "Click Cancel Line");		
			if(BDriver.findElement(By.xpath(oUIObj.WarningMessage1_xp)).isDisplayed()) 
			{	
				// warning message if only single line is there
				String WarningMessage= BDriver.findElement(By.xpath(oUIObj.WarningMessage1_xp)).getText();													
				if(WarningMessage.contains("This line is the only active line on the document. Canceling the line will also cancel the document"))
				{
					System.out.println("POLine cancel WarningMessage :"+ WarningMessage);
					controlClick(oUIObj.YesWarningMessage1_xp, "Click Yes on WarningMessage to cancel line"); 
					Thread.sleep(3000);							
				}
				else {
					// if any other warning message when single or multi lines
					System.out.println("POLine cancel Warningssage :"+ WarningMessage);
					controlClick(oUIObj.NoWarningMessage1_xp, "Click No on WarningMessage to cancel line");
					Thread.sleep(3000);	
					System.out.println("Unable to Cancel the specified PO line as there is some pending issue exists on this line");																
					controlClick(oUIObj.ClickDone_xp, "click Done on the PO life cycle page");
					Thread.sleep(3000);	
				}						
			}	
			// enter cancel reason
			setText(oUIObj.InputReasonforCancel_xp, "testSlenium is Reason4cancel line"); 
			Thread.sleep(3000);
			controlClick(oUIObj.OkOnCancelDokPopup_xp, "Click Ok On Cancel Popup"); 
			Thread.sleep(3000);			
			controlClick(oUIObj.ClickDone_xp, "click Done on the PO life cycle page");
		}

		//PROC16:  Cancel the PO 
				public void CancelThePO() throws Exception {
					pImplicitWait();
					ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "CancelThePO");
					System.out.println("POTestSc sheet read");
					controlClick(oUIObj.home_id, "Home Icon");						
					controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");
					controlClick(oUIObj.POIcon_xp, "Click on PO Icon");							
					controlClick(oUIObj.POTasks_xp, "Click on Tasks bar under PO ");	
					Thread.sleep(3000);
					controlClick(oUIObj.ManageOrder_xp, "Click on Mange Order from PO Tasks bar");	
					Thread.sleep(3000);		
					//Enter Search criteria PO# or status or buyer
					//Requisition number
					setText(oUIObj.PurchaseOrderInput_xp, oExcelP2P.getCellData("PONumber", 1)); 
					Thread.sleep(3000);									
					//enteredBy
					setText(oUIObj.BuyerInput_xp, oExcelP2P.getCellData("BuyerName", 1)); 
					Thread.sleep(4000);
					SelectValueFromDropdown(oUIObj.Status_xp, oExcelP2P.getCellData("POStatus", 1)); 
					Thread.sleep(4000);
					SelectValueFromDropdown(oUIObj.ProcuremntBUInput_xp, oExcelP2P.getCellData("ProcuremntBU", 1)); 			
					Thread.sleep(4000);
					controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
					Thread.sleep(5000);		
					controlClick(oUIObj.ResultsFirstRow_xp, "select the first row of results");
					Thread.sleep(3000);
					controlClick(oUIObj.PONumLink_xp, "click on the PO number link to open from results");
					Thread.sleep(3000);						
					controlClick(oUIObj.ClickActions_xp, "click on Actions ");
					Thread.sleep(3000);
					controlClick(oUIObj.CancelDocument_xp, "click on Cancel Document Link ");				
					// enter cancel reason
					setText(oUIObj.InputReasonforCancel_xp, "testSlenium is Reason4cancel line"); 
					Thread.sleep(3000);
					controlClick(oUIObj.OkOnCancelDokPopup_xp, "Click Ok On Cancel Popup_xp"); 
					Thread.sleep(3000);			
					controlClick(oUIObj.ClickDone_xp, "click Done on the PO life cycle page");
				}
		
// PROC43:Create PO manually with exceeded price tolerance
				public void CreatePOwithPriceOutsideTolerance() throws Exception {
					pImplicitWait();
					ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "POPriceOutsideTolerance");
					System.out.println("POTestSC sheet read");
					controlClick(oUIObj.home_id, "Home Icon");					
					controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");
					controlClick(oUIObj.POIcon_xp, "Click on PO Icon");							
					controlClick(oUIObj.POTasks_xp, "Click on Tasks bar under PO ");					
					controlClick(oUIObj.ProcessRequisitions_xp, "Click on ProcessRequisitions from PO Tasks bar");					
					setText(oUIObj.RequisitionInput_xp,oExcelP2P.getCellData("RequisitionNumber",1));				
					controlClick(oUIObj.SearchRequsitionButton_xp, "Click on the Searchbutton");
					scrollToEle(oUIObj.ScrolltoLastcolumn, "scroll to last column");		
					Thread.sleep(3000);
					selectFromTablelist(oUIObj.StrLocator,oUIObj.Arwlocator1,oUIObj.Arwlocator2,oExcelP2P.getCellData("RequisitionNumber",1));
					waitforpageload();
					Thread.sleep(2000);
					controlClick(oUIObj.AddToDocBuilder_xp, "Click AddToDocBuilder button"); 
					Thread.sleep(3000);
					//controlClick(oUIObj.ClickOKbutton_xp, "Click OK button"); 
					Thread.sleep(3000);
					//controlClick(oUIObj.ClickCreateButton_xp, "Click Create button"); 
					Thread.sleep(3000);
					//controlClick(oUIObj.ClickOKonPopupCOnfirm_xp, "Click OK OnConfirmation Create"); 
					Thread.sleep(5000);
					//controlClick(oUIObj.ClickSubmitButton_xp, "Click Submit button"); 
					Thread.sleep(3000);	
					//controlClick(oUIObj.ClickOKAfterSubmitbutton_xp, "Click OK confirm Submit"); 
				}			
				
				

//PROC11:  create change order By Requestor and submit for approvals
				public void ChangeOrderCreationByRequestor() throws Exception {
					pImplicitWait();
					ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ChangeOrderByRequestor");
					System.out.println("POTestSc sheet read");
					controlClick(oUIObj.home_id, "Home Icon");						
					controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");			
					controlClick(oUIObj.RequisitionsIcon_xp, "Click PurchaseRequisitions Icon");	
					controlClick(oUIObj.ManageRequisitions_xp, "Click Manage Requisitions Link");	
					Thread.sleep(3000);	
					setText(oUIObj.RequisitionNumInput_xp, oExcelP2P.getCellData("RequisitionNumber",1));
					setText(oUIObj.EnteredByInput_xp, oExcelP2P.getCellData("EnteredBy",1));	
					Thread.sleep(3000);
					Thread.sleep(3000);
					SelectValueFromDropdown(oUIObj.StatusLOV_xp, oExcelP2P.getCellData("ReqStatus",1));
					Thread.sleep(3000);
					controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
					Thread.sleep(5000);
					controlClick(oUIObj.ReqNumLine1_xp, "select the record line"); 
					Thread.sleep(3000);
					controlClick(oUIObj.ReqNumLink_xp, "click the record value");		
					Thread.sleep(5000);
					controlClick(oUIObj.ClickActions_xp, "click the Actions link"); 
					Thread.sleep(3000);
					controlClick(oUIObj.EditOrder_xp, "click Edit Order link");
					Thread.sleep(3000);		
					if(BDriver.findElement(By.xpath(oUIObj.WarningChangeOrder_xp)).isDisplayed()) 
					{	
						// warning message if only PO is in pending approval status already
						String WarningMessage= BDriver.findElement(By.xpath(oUIObj.WarningChangeOrder_xp)).getText();													
						if(WarningMessage.contains("You cannot edit the change order because it is pending approval or pending supplier acknowledgment"))
						{
							System.out.println("PO change order WarningMessage :"+ WarningMessage);
							controlClick(oUIObj.ClickOKbutton_xp, "Click OK on WarningMessage to close"); 
							Thread.sleep(3000);							
						}	

						else if (WarningMessage.contains("This action will create a change order on the document. Do you want to continue?"))
						{					
							controlClick(oUIObj.YesbuttonWaring1_xp, "click Yes on Warning message for ChangeOrder"); 					
							Thread.sleep(3000);
							setText(oUIObj.EnterEditOrderDesc_xp,oExcelP2P.getCellData("EditReason",1));
							Thread.sleep(3000);					
							controlClick(oUIObj.ClickSubmitButton_xp, "Click Submit button"); 
							Thread.sleep(3000);	
							controlClick(oUIObj.ClickOKAfterSubmitbutton_xp, "Click OK confirm Submit"); 	
						}
					}
					
					Thread.sleep(3000);	
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
					Thread.sleep(5000);
					controlClick(oUIObj.ReqNumLine1_xp, "select the record line"); 
					Thread.sleep(3000);
					controlClick(oUIObj.ReqNumLink_xp, "click the record value");		
					Thread.sleep(5000);
					
					// View Document History
					Thread.sleep(3000);														
					controlClick(oUIObj.ClickActions_xp, "click the Actions link"); 
					Thread.sleep(3000);
					controlClick(oUIObj.ViewDocumentHistory_xp, "click ViewDocumentHistory link");
					Thread.sleep(9000);						
					List<WebElement> TableList = BDriver.findElements(By.xpath(oUIObj.ArrayList_xp));
					int size = TableList.size();	
					System.out.println("Total Number of Actions Performed on this Order are: " +size);	
					for (int i = 0; i <size; i++) {			
						String RequiredElement_xp =oUIObj.ActionPerformedBy1_xp+i+oUIObj.ActionPerformedBy2_xp;
						//System.out.println(RequiredElement_xp);
						String Value = BDriver.findElement(By.xpath(RequiredElement_xp)).getText();
						System.out.println((i+1)+": Action Performed By is:   "+Value);

					}
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
					Thread.sleep(3000);	
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
					Thread.sleep(3000);
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
				}

	
//PROC12:  create change order By Buyer and submit for approvals
				public void ChangeOrderCreationByBuyer() throws Exception {
					pImplicitWait();
					ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "ChangeOrderByBuyer");
					System.out.println("POTestSc sheet read");
					controlClick(oUIObj.home_id, "Home Icon");						
					controlClick(oUIObj.ProcurementIcon_xp, "Click Procurement Icon");						
					controlClick(oUIObj.POIcon_xp, "Click on PO Icon");						
					controlClick(oUIObj.POTasks_xp, "Click on Tasks bar under PO ");	
					Thread.sleep(3000);
					controlClick(oUIObj.ManageOrder_xp, "Click on Mange Order from PO Tasks bar");	
					Thread.sleep(3000);		
					//Enter Search criteria PO# or status or buyer
					//Requisition number
					setText(oUIObj.PurchaseOrderInput_xp, oExcelP2P.getCellData("PONumber", 1)); 
					Thread.sleep(3000);									
					//enteredBy
					setText(oUIObj.BuyerInput_xp, oExcelP2P.getCellData("BuyerName", 1)); 
					Thread.sleep(4000);
					SelectValueFromDropdown(oUIObj.Status_xp, oExcelP2P.getCellData("POStatus", 1)); 
					Thread.sleep(4000);
					SelectValueFromDropdown(oUIObj.ProcuremntBUInput_xp, oExcelP2P.getCellData("ProcuremntBU", 1)); 			
					Thread.sleep(4000);
					controlClick(oUIObj.SearchButton_xp, "Click the Search Button");
					Thread.sleep(5000);		
					controlClick(oUIObj.ResultsFirstRow_xp, "select the first row of results");
					Thread.sleep(3000);
					controlClick(oUIObj.PONumLink_xp, "click on the PO number link to open from results");
					Thread.sleep(3000);						
					
					controlClick(oUIObj.ClickActions_xp, "click on Actions ");
					Thread.sleep(3000);					
					controlClick(oUIObj.EditOrderbyBuyer_xp, "click on Edit link");
					Thread.sleep(5000);		
					if(BDriver.findElement(By.xpath(oUIObj.WarningChangeOrder_xp)).isDisplayed()) 
					{	
						// warning message if only PO is in pending approval status already
						String WarningMessage= BDriver.findElement(By.xpath(oUIObj.WarningChangeOrder_xp)).getText();													
						if(WarningMessage.contains("You cannot edit the change order because it is pending approval or pending supplier acknowledgment"))
						{
							System.out.println("PO change order WarningMessage :"+ WarningMessage);
							controlClick(oUIObj.ClickOKbutton_xp, "Click OK on WarningMessage to close"); 
							Thread.sleep(3000);							
						}	
						else if (WarningMessage.contains("This action will create a change order on the document. Do you want to continue?"))
						{					
							controlClick(oUIObj.YesbuttonWaring1_xp, "click Yes on Warning message for ChangeOrder"); 					
							Thread.sleep(3000);
							setText(oUIObj.EnterEditOrderDesc_xp,oExcelP2P.getCellData("EditReason",1));
							Thread.sleep(3000);					
							controlClick(oUIObj.ClickSubmitButton_xp, "Click Submit button"); 
							Thread.sleep(3000);	
							controlClick(oUIObj.ClickOKAfterSubmitbutton_xp, "Click OK confirm Submit"); 	
						}
					}
					else if(BDriver.findElement(By.xpath(oUIObj.EnterEditOrderDesc_xp)).isDisplayed()) {
						setText(oUIObj.EnterEditOrderDesc_xp,oExcelP2P.getCellData("EditReason",1));
						Thread.sleep(3000);					
						controlClick(oUIObj.ClickSubmitButton_xp, "Click Submit button"); 
						Thread.sleep(3000);	
						controlClick(oUIObj.ClickOKAfterSubmitbutton_xp, "Click OK confirm Submit"); 
						
					}	
					
					Thread.sleep(3000);	
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
					
					Thread.sleep(5000);		
					controlClick(oUIObj.ResultsFirstRow_xp, "select the first row of results");
					Thread.sleep(3000);
					controlClick(oUIObj.PONumLink_xp, "click on the PO number link to open from results");

					// View Document History
					Thread.sleep(3000);														
					controlClick(oUIObj.ClickActions_xp, "click the Actions link"); 
					Thread.sleep(3000);
					scrollToEle(oUIObj.ViewDocumentHistory_xp, "Scroll to ViewDocumentHistory link");
					Thread.sleep(3000);
					controlClick(oUIObj.ViewDocumentHistory_xp, "click ViewDocumentHistory link");
					Thread.sleep(3000);
					controlClick(oUIObj.View_xp, "click View link");
					Thread.sleep(3000);
					controlClick(oUIObj.CollapseAll_xp, "click collapse all from view link");					
					Thread.sleep(5000);						
					List<WebElement> TableList1 = BDriver.findElements(By.xpath(oUIObj.ActionPerformed_xp)); 
					int size = TableList1.size();	
					System.out.println("Total Number of Actions Performed on this Order are: " +size);					
					java.util.Iterator<WebElement> i = TableList1.iterator();
					while(i.hasNext()) {	
						WebElement ActionPerformed = i.next();						
						System.out.println(ActionPerformed);						
						System.out.println("Action Performed is: "+ActionPerformed.getText());				
					}
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
					Thread.sleep(3000);	
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
					Thread.sleep(3000);
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
				}
				
//PROC46:  Query PO  Receipts  From Inventory Management
				public void QueryPOReceiptsFromInventory() throws Exception {
					pImplicitWait();
					ExcelRead oExcelP2P = new ExcelRead(sTDFileName, "QueryPOReceipts");
					System.out.println("POTestSc sheet read");
					controlClick(oUIObj.home_id, "Home Icon");						
					controlClick(oUIObj.InvManagementIcon_xp, "Click invenotory Management Icon");							
					controlClick(oUIObj.InvQuickSearch_xp, "Click on Quick Search Icon");						
					SelectValueFromDropdown(oUIObj.InvSelectRecLines_xp, "Received Lines");	
					Thread.sleep(3000);					
					controlClick(oUIObj.InvAdavanceLink_xp, "Click on Advanced Link");	
					Thread.sleep(3000);		
					//Enter Search criteria Receipts#, PO# 
					//setText(oUIObj.InvReceiptInput_xp, oExcelP2P.getCellData("ReceiptNumber", 1)); 
					Thread.sleep(3000);	
					//PO number
					setText(oUIObj.InvPOInput_xp, oExcelP2P.getCellData("PONumber", 1)); 
					Thread.sleep(3000);														 
					controlClick(oUIObj.InvSearchButton_xp, "Click on Search button");				
					Thread.sleep(5000);						
					List<WebElement> TableList1 = BDriver.findElements(By.xpath(oUIObj.InvLinesResLineStatus_xp)); 
					List<WebElement> TableList2 = BDriver.findElements(By.xpath(oUIObj.InvLinesResLineQty_xp)); 
					int size = TableList1.size();	
					System.out.println("Total Number of Lines in this Order are: " +size);					
					java.util.Iterator<WebElement> i = TableList1.iterator();
					java.util.Iterator<WebElement> j = TableList2.iterator();
					while(i.hasNext()) {	
						WebElement LineStatus = i.next();	
						WebElement LineQuantity = j.next();
						//System.out.println(LineStatus);		
						String Status = LineStatus.getText();
						String Quantity = LineQuantity.getText();
						System.out.println("Line Status is: "+Status);
						if(Status.equalsIgnoreCase("Delivered")) 
						{
							System.out.println("Line Quantity is: "+Quantity);							
						}						
					}
					Thread.sleep(3000);
					controlClick(oUIObj.ClickDone_xp, "click Done"); 
				}
				
				
	/*
	 * * 
	 * spread sheet
	 * @Information will save into database
	 */	
	 
}