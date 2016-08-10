package com.maax.businessmanager.common;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.maax.businessmanager.util.ErrorUtil;
import com.maax.businessmanager.util.TestBase;
import com.maax.businessmanager.util.TestDataProvider;

public class person_add_test extends TestBase{
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteCommontestdata")
	public void person_add_test(Hashtable<String, String>data) throws InterruptedException{
		WebDriver d1=null;
		try{
		
		
			
			
		 //RemoteWebDriver d1=null;
	//d1=init("Provisioncopy", "Common",data,false);
	d1=init("person_add_test", "Common",data,false);
	
	
		checkrunmodes("Common", "person_add_test", data.get("Runmode"));
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
		click("contact_link",d1);
		waitforelementpresent("person_radio", d1);
		click("person_radio", d1);
		dynamicwait(d1);
		Thread.sleep(2000);
		type("lastname", data.get("lastname"), d1);
		click("person_search", d1);
		dynamicwait(d1);
		click("add_person_button", d1);
		dynamicwait(d1);
		type("person_FS_name", data.get("Member_FirstName"), d1);
		type("person_LS_name", data.get("Member_LastName"), d1);
		select("person_sex_DP", data.get("Member_Gender"), d1);
		type("personDOB", data.get("Member_DOB"), d1);
		select("correspondence_laun_DP", data.get("correspondence_laun"), d1);
		select("Adress_type_DP", data.get("Adress_type"), d1);
		System.out.println("*****************"+ data.get("Province"));
		select("Province_DP", data.get("Province"), d1);
		type("Adreess_line_1", data.get("Address_line_1"), d1);
		type("city", data.get("City"), d1);
		type("postal_code", data.get("postal_code"), d1);
		click("person_add_save_button", d1);
		dynamicwait(d1);
		
		takescreenshot(d1);
		teardown(d1);
		
		}catch(Throwable e){
			takescreenshot(d1);
		//	teardown(d1);
			ErrorUtil.addVerificationFailure(e);
		}
	}

}
