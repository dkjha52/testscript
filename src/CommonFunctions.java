import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;


public class CommonFunctions 
{
	public static void wait(int n)
	{
		long t0,t1;
		t0=System.currentTimeMillis();
		do{
			t1=System.currentTimeMillis();
		}
		while(t1-t0<n);
	}
	public static WebDriver getWebDriver(Properties prop) throws IOException
	{
		WebDriver wd;
		File directory = new File (".");
		String browser_name=prop.getProperty("BROWSER");
		    switch (BrowserType.valueOf(browser_name))
		    {
		        case FIREFOX:
		            wd=new FirefoxDriver();
		            wd.manage().window().maximize();
		            return wd;
		        case IE:
		        	String iepath=directory.getCanonicalPath() + "/lib/" + "IEDriverServer.exe";
		        	//System.out.println(iepath);
		        	System.setProperty("webdriver.ie.driver",iepath);
		        	wd = new InternetExplorerDriver();
		        	wd.manage().window().maximize();
		            return wd;
		        case CHROME:
		        	String chromepath=directory.getCanonicalPath() + "/lib/" + "chromedriver.exe";
		        	//System.out.println(driverpath);
		        	System.setProperty("webdriver.chrome.driver", chromepath);
		        	wd = new ChromeDriver();
		        	CommonFunctions.wait(5000);
		        	wd.manage().window().maximize();
		            return wd;
		        case HTMLUNIT:
		        	wd = new HtmlUnitDriver();
		            return wd;
		        case ANDROID:
		        	wd = new AndroidDriver();
		            return wd;
		        case SAFARI:
		        	wd = new SafariDriver();
		        	wd.manage().window().maximize();
		            return wd;
		        default:
		            throw new RuntimeException("Browser type unsupported");
		    }
	}
		 
	public static enum BrowserType 
	{
		    FIREFOX, IE, CHROME, HTMLUNIT, ANDROID, SAFARI
	}
	public static boolean verifyTextPresent(WebDriver wd,String text)
	{
		return wd.getPageSource().contains(text);
	}

}
