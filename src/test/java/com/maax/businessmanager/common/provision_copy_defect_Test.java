package com.maax.businessmanager.common;

import java.util.Hashtable;
import java.util.List;






import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.maax.businessmanager.util.ErrorUtil;
import com.maax.businessmanager.util.TestBase;
import com.maax.businessmanager.util.TestDataProvider;

public class provision_copy_defect_Test extends TestBase{
	
	
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteCommontestdata")
	public void Provisioncopy(Hashtable<String, String>data) throws InterruptedException{
		WebDriver d1=null;
		try{
		
		
			
			
		 //RemoteWebDriver d1=null;
	//d1=init("Provisioncopy", "Common",data,false);
	d1=init("basicnavigation", "Common",data,true);
	
	
		checkrunmodes("Common", "Provisioncopy", data.get("Runmode"));
		APPLICATION_LOG.debug((data.get("Browser")));
		
		//openbrowsergrid(data.get("Browser"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		navigate(d1);
		
		dologin(d1);
		
		Thread.sleep(4000);
		click("BusinessManagerlink",d1);
		waitforelementpresent("provisiontree", d1);
		click("provisiontree",d1);
		waitforelementpresent("LifeLOB", d1);
		List<WebElement> LOBS=d1.findElements(By.xpath("//div[@id='businessmanagerGateway:provisonTree-d-root']/div[2]/div"));
		String lobxpath="//*[text()=\'"+data.get("LOB")+"']";
		
		System.out.println(LOBS.size());
		for(WebElement e:LOBS){
			
			if(e.getText().equalsIgnoreCase(data.get("LOB"))){
				WebElement requiredLOB=d1.findElement(By.xpath(lobxpath));	
				requiredLOB.click();
				Thread.sleep(5000);
				String benexpath="//*[text()=\'"+data.get("Benefit")+"']";
				requiredLOB=d1.findElement(By.xpath(lobxpath));
				WebElement reqbenefit=requiredLOB.findElement(By.xpath(benexpath));
				reqbenefit.click();
				waitforelementpresent("provisionsearchbutton", d1);
				
			//System.out.println(e.getText());
			
			type("searchcodefield", "*", d1);
			click("provisionsearchbutton", d1);
			dynamicwait(d1);
			List<WebElement> searchresults=d1.findElements(By.xpath(objdata.get("searchresults")));
			searchresults.get(0).click();
			dynamicwait(d1);
			click("copybutton", d1);
			dynamicwait(d1);
			type("copypagecode", data.get("copypagecode"), d1);
			type("copypagedesc", data.get("copypagedesc"), d1);
			click("copysavestaybuton", d1);
			dynamicwait(d1);
			select("copypagestatus", data.get("copypagestatus"), d1);
			dynamicwait(d1);
			click("copysavestaybuton", d1);
			dynamicwait(d1);
			String actualmess=d1.findElement(By.xpath(objdata.get("Successmess"))).getText();
			String expectedmess=data.get("Successmess");
		
			//Assert.assertEquals(expectedmess, actualmess);
			System.out.println("END");
			
			break;
	
			}	
			
			
			
		}
		takescreenshot(d1);
		teardown(d1);
		
		}catch(Throwable e){
			takescreenshot(d1);
			teardown(d1);
			ErrorUtil.addVerificationFailure(e);
		}
	}
		
}


