import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class InitScripts 
{
	WebDriver driver = null;
	WebElement element;
	WebDriverWait wdw;
	Properties properties = new Properties();
	@BeforeTest(alwaysRun=true)
    public void setup() throws IOException
    {
		try {
			properties.load(new FileInputStream("testlink.properties"));
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}
		driver=CommonFunctions.getWebDriver(properties);
		driver.manage().deleteAllCookies();
		wdw = new WebDriverWait(driver, 50);
		driver.get("http://localhost/testlink/index.php");
		CommonFunctions.wait(200);
		driver.findElement(By.xpath(properties.getProperty("TXT_UNAME"))).sendKeys("admin");
 	    driver.findElement(By.xpath(properties.getProperty("TXT_PWD"))).sendKeys("admin");
 	    driver.findElement(By.xpath(properties.getProperty("BTN_Login"))).click();
 	    CommonFunctions.wait(500);
    }
	@Test(description="Verify Home Page")
	public void test_HP1()
	{
		System.out.println("@Test-1: Verify Sections on home page ");
		Verify_HomePageSections.testCase1_home(driver, wdw, properties);
	}
	@Test(description="Verify Home Page")
	public void test_HP2()
	{
		System.out.println("@Test-2: Verify Test Project Section");
		Verify_HomePageSections.testCase2_testproject(driver, wdw, properties);
	}
	@Test(description="Verify Home Page")
	public void test_HP3()
	{
		System.out.println("@Test-3: Verify Test Plan Section");
        Verify_HomePageSections.testCase3_testplan(driver, wdw, properties);
	}
	public void test_HP4()
	{
		System.out.println("@Test-4: Verify User Management Section");
		Verify_HomePageSections.testCase4_usermanagement(driver, wdw, properties);
	}
	@Test(description="Verify Home Page")
	public void test_HP5()
	{
		System.out.println("@Test-5: Verify Requirement Specification Section");
		Verify_HomePageSections.testCase5_requirements(driver, wdw, properties);
	}
	@Test(description="Verify Home Page")
	public void test_HP6()
	{
		System.out.println("@Test-6: Verify Test Specification Section");
		Verify_HomePageSections.testCase6_testspecification(driver, wdw, properties);
	}
	@Test(description="Verify Home Page")
	public void test_HP7()
	{
		System.out.println("@Test-7: Verify Documentation Section");
		Verify_HomePageSections.testCase7_menu_combo(driver, wdw, properties);
	}
	@Test(description="Verify Home Page")
	public void test_HP8()
	{
		System.out.println("@Test-8: Verify Test Execution Section");
		Verify_HomePageSections.testCase8_testexecution(driver, wdw, properties);
	}
	@Test(description="Verify Home Page")
	public void test_HP9()
	{
		System.out.println("@Test-9: Verify Test Plan Contents Section");
		Verify_HomePageSections.testCase9_testplancontents(driver, wdw, properties);
	}
	
	@AfterTest(alwaysRun=true)
    public void afterTest() 
    {
 	   driver.switchTo().defaultContent();
       driver.switchTo().frame("titlebar");
       wdw.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getProperty("BTN_Logout"))));
       driver.findElement(By.xpath(properties.getProperty("BTN_Logout"))).click();
       driver.close();
    }

}
