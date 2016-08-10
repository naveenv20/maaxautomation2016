package com.maax.businessmanager.util;


import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;



public class TestBase {
	
	WebDriver d1_backupfirefox= null;
	Robot robot;
	
	public static Logger APPLICATION_LOG;

	//going to use this objdata ( for reading the object propertie of a test case
	public Hashtable<String,String> objdata;
//	 public static  WebDriver d1; 

	public WebDriver init(String testcasename,String Suite,Hashtable<String,String> data,Boolean grid){
		WebDriver d2; 
		  APPLICATION_LOG=Logger.getLogger("devpinoyLogger");
		objdata=util.getobjectdata(testcasename, Suite);
		String currentrunmode=data.get("Runmode");
		checkrunmodes(Suite, testcasename, currentrunmode);
		if(grid)
			d2=openbrowsergrid(data.get("Browser"));
		else
		d2=openbrowser(data.get("Browser"));
		
		
		return d2;
		
	}
	
	
	

	public static void checkrunmodes(String Suitename,String testcasename,String currentrunmode){
		
		boolean suiterunmode=util.istestsuiterunnable(Suitename);
		boolean testcaserunmode=util.istestcaserunnable(testcasename, Suitename);
		boolean currrunmode=false;
		if(currentrunmode.equalsIgnoreCase("Y"))
			currrunmode=true;
		if(!(suiterunmode&&testcaserunmode&&currrunmode)){
			APPLICATION_LOG.debug("Test case is configured as No"+"     Suitename ::"+Suitename+"  testcasename::"+ testcasename+"  currentrunmode::"+ currentrunmode);
			throw new SkipException("Test case is configured as No");
		}
		
		else
			APPLICATION_LOG.debug("Running the test case"+"     Suitename ::"+Suitename+"  testcasename::"+ testcasename+"  currentrunmode::"+ currentrunmode);	
		
		
	}
	
	
	public void click(String identifier, WebDriver d1){
		
		d1.findElement(By.xpath(objdata.get(identifier))).click();
		
	}
	
	public WebDriver openbrowser(String browsername){

		WebDriver d1=null;
		
		if(browsername.equalsIgnoreCase("Firefox")){	
			 
			//System.setProperty("webdriver.gecko.driver", "C:\\Seli\\geko\\geckodriver.exe ");
		  d1=new FirefoxDriver();	
			//String scriptpath="C:\\autoit_myscripts\\pasesolution_IE.exe";
			//executeScript(scriptpath);
			
			
	
		}
		if(browsername.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\browsers\\chromedriver.exe");
				d1=new ChromeDriver();
			String scriptpath="C:\\autoit_myscripts\\auth_chrome.exe";
			//executeScript(scriptpath);
			
		}
		if(browsername.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\browsers\\IEDriverServer.exe");
			
				 d1=new InternetExplorerDriver();
		}
		//Implicit wait time out
	//	d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		//Browser maximising
		d1.manage().window().maximize();
		return d1;
	}
	
	public RemoteWebDriver openbrowsergrid(String browsername){
		  DesiredCapabilities cap=null;
		RemoteWebDriver d1=null;
		if(browsername.equalsIgnoreCase("Firefox")){	
			 cap=DesiredCapabilities.firefox();	
			 cap.setBrowserName("firefox");
			cap.setPlatform(Platform.ANY);
			
			//String scriptpath="C:\\autoit_myscripts\\pasesolution_IE.exe";
			//executeScript(scriptpath);
			}
		if(browsername.equalsIgnoreCase("Chrome")){
			cap=DesiredCapabilities.chrome();	
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
			
			String scriptpath="C:\\autoit_myscripts\\auth_chrome.exe";
			//executeScript(scriptpath);
			
		}
		if(browsername.equalsIgnoreCase("IE")){
			
			cap=DesiredCapabilities.internetExplorer();	
			cap.setBrowserName("iexplore");
			cap.setPlatform(Platform.WINDOWS);
		
		}
	
		try {
				d1=new RemoteWebDriver(new URL("http://10.15.2.63:4444/wd/hub"),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		d1.manage().window().maximize();
		return d1;
			
	}
	
	public void navigate(WebDriver d1){
		d1.get(objdata.get("URL"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void type(String identifier, String datatotype,WebDriver d1){
		d1.findElement(By.xpath(objdata.get(identifier))).clear();
		d1.findElement(By.xpath(objdata.get(identifier))).sendKeys(datatotype);
	}
	
	public void select(String identifier, String valuetoselect,WebDriver d1){
	WebElement 	e=d1.findElement(By.xpath(objdata.get(identifier)));
	Select element= new Select(e);
	element.selectByVisibleText(valuetoselect);
	}
	
	public void selectradio(String identifier, String valuetoselect,WebDriver d1){
		//not implemented fully , check this *****
		d1.findElement(By.xpath(objdata.get(identifier))).click();
	}
	
	public void executeScript(String scriptPath) 
	{
	//scriptPath is the path of the executable
	try {
		Runtime.getRuntime().exec(scriptPath);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	public void teardown(WebDriver d2){
		if(d2!=null){
			d2.quit();
		}
	}
	
	public void takescreenshot(WebDriver d1){
		
		String scrsht_filename=String.valueOf(System.currentTimeMillis())+".jpg";
		File scrFile = ((TakesScreenshot)d1).getScreenshotAs(OutputType.FILE);
try {
	FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+scrsht_filename));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	
	
	public void hovermouse(String identifier,WebDriver d1){
		
		System.out.println(d1.findElement(By.xpath(objdata.get(identifier))).getLocation().x);
		System.out.println(d1.findElement(By.xpath(objdata.get(identifier))).getLocation().y);
		Actions act= new Actions(d1);
	
		act.moveToElement(d1.findElement(By.xpath(objdata.get(identifier)))).build().perform();
//	try{	
//	robot=new Robot();
//	}catch(AWTException e){
//		e.printStackTrace();
//	}
//	robot.mouseMove(260, 90);
	
	}
	
	
	public String getcurrentselectedvale(String identifier, WebDriver d1){
		WebElement 	e=d1.findElement(By.xpath(objdata.get(identifier)));
		Select element= new Select(e);
		return element.getFirstSelectedOption().toString();
		
	}
	
	public boolean findelementpresent(String identifier, WebDriver d1){
		
	
		int count=d1.findElements(By.xpath(objdata.get(identifier))).size();
	 	List<WebElement> element_array=d1.findElements(By.xpath(objdata.get(identifier)));
	 	if (count>0){
	 		return true;
	 	}
	 	return false;
	}
	
	
	public List<WebElement> findelementsonthepage(String identifier, WebDriver d1){
		
		List<WebElement> elemetsonpage=d1.findElements(By.xpath(objdata.get(identifier)));
		return elemetsonpage;// *****chances of NULL pointer
		
		
	}
	
	public boolean iselementeditable(String identifier, WebDriver d1){
		boolean result=false;
		result=d1.findElement(By.xpath(objdata.get(identifier))).isEnabled();
		return result;
	}
	
	//***********************************************
	//***********PROJECT SPECIFIC FUNCTIONS**********
	//************************************************
	
	public void dologin(WebDriver d1){
	
		waitforelementpresent("loginid",d1);
		
		d1.findElement(By.xpath(objdata.get("loginid"))).sendKeys("othnvun");
		d1.findElement(By.xpath(objdata.get("password"))).sendKeys("M@@xtest786");
		d1.findElement(By.xpath(objdata.get("loginbtn"))).click();
	}
	
	public static void dynamicwait(WebDriver d1){
		
		/*int maaxtimeout=90;int counter=0;
		WebElement bubbles=d1.findElement(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]"));
		List<WebElement> bubexistence=d1.findElements(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]"));
		while(bubexistence.size()!=0&&counter<maaxtimeout){
			 bubexistence=d1.findElements(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]"));
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 counter++;
		}
		System.out.println("********:::"+counter);
		bubbles=null;*/
		
		WebDriverWait obj=new WebDriverWait(d1, 300);
		WebElement Element=d1.findElement(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]"));
		obj.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]")));
		
	}
	
	
	 public  void waitforelementpresent(String Element_xpath,WebDriver d1)  
	 {
	 	int sec=0;

	 while (sec<120)
	 {
	 	int count=d1.findElements(By.xpath(objdata.get(Element_xpath))).size();
	 	List<WebElement> element_array=d1.findElements(By.xpath(objdata.get(Element_xpath)));
	 	if (count==0){
	 		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}sec++;}
	 	else
	 	{
	 	APPLICATION_LOG.debug("Object Found::::"+Element_xpath);	
	 	break;}
	 	
	 	
	 }

	 }
	 
	 
	 public void tableresultsearch(String table_header_identifier, WebDriver d1,String Column_to_search,String table_body_identifier,String data_to_search){
	
		List<WebElement> header_elements=d1.findElements(By.xpath(objdata.get(table_header_identifier)));
for(int i=0;i<header_elements.size();i++){
	
	if(header_elements.get(i).getText().equalsIgnoreCase(Column_to_search)){
		
		List<WebElement> body_data_rows= d1.findElements(By.xpath(objdata.get(table_body_identifier)+"/td["+(i+1)+"]"));
		System.out.println("^&&^&^&^&"+body_data_rows.size());
		for(WebElement f:body_data_rows){
//			System.out.println("&&&&&&&&&&&&&&"+data_to_search);
//			System.out.println("&&&&&&&&&&&&&&"+f.getText());
			if(f.getText().equalsIgnoreCase(data_to_search)){
				f.click();
//				System.out.println("*********************");
				dynamicwait(d1);
				break;
			}
		}
	}
}
		 
		 
		 
	 }
	 
	 
	 
	 
	 public String queryit(){
		 
	 		 Connection conn=null;String agreevalue=null;
			String url="jdbc:oracle:thin:@plxde601:1521:PBA2SB";
			//		String url="jdbc:mysql://localhost:3306/";
			String dbname="PBA2SB";
			String driver="oracle.jdbc.driver.OracleDriver";
			String username="bcjlbou";
			String pass="TempPassword21";
			//jdbc:oracle:thin:@10.156.157.237:1521:sgatest", "ppm_user","ppm_user"
	
			//trying the connection 
			
			try{
				//create the driver class object .. in this case mysql related driver object created
				Class.forName(driver).newInstance();
				conn=DriverManager.getConnection(url,username,pass);
				//connection reference established
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from ENABLERS.Agreement_Header where agreement_type=64 and agreement_id in(select max(agreement_id) from ENABLERS.Agreement_Header where agreement_type=64)");
				//first row
				rs.next();
				//prints the first row name column value
				System.out.println(rs.getString("AGREEMENT_ID"));
				agreevalue=rs.getString("AGREEMENT_ID");	
				
			}catch(Exception e){
			System.out.println("error");
			e.printStackTrace();
			}
			finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 }
			return agreevalue;
	 
}
	 
	 public void addperson(){
		 
	 }
	 
}
