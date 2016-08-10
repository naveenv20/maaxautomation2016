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

public class plan_copy extends TestBase {
	
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteCommontestdata")
	public void plan_copy_test(Hashtable<String, String>data) throws InterruptedException{
		WebDriver d1=null;
		try{
					
		 //RemoteWebDriver d1=null;
	//d1=init("Provisioncopy", "Common",data,false);
	d1=init("plan_copy_test", "Common",data,false);
	
	
		checkrunmodes("Common", "plan_copy_test", data.get("Runmode"));
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
		
	Thread.sleep(2000);
	click("business_manager_link", d1);
	dynamicwait(d1);
	click("plan_link", d1);
	waitforelementpresent("plan_code_tb", d1);
	type("plan_code_tb", data.get("plan_code_tb"), d1);
	click("plan_search_btn", d1);
	dynamicwait(d1);
	click("plan_copy_btn", d1);
	dynamicwait(d1);
	type("plan_add_code_tb", data.get("plan_add_code_tb"), d1);
	type("plan_add_description_tb", data.get("plan_add_description_tb"), d1);
	click("plan_savestay_btn", d1);
	dynamicwait(d1);

	//String[] benefits={"Member Life","Dependent Life","Member AD&D","Dental","EHC","Health Assessment","EFAP","Medical 2nd Opinion","Hospital","Travel","Drug","STD","LTD"};
	String[] benefits={"Dental","EHC","Travel","Drug","STD","LTD"};
	for(int i=0;i<benefits.length;i++){
			
		List<WebElement> benefit_list=d1.findElements(By.xpath("//*[@id='plan:incPlan:benefit-d-root-c']/div/div[2]/div/div/a"));
	for(int j=0;j<benefit_list.size();j++){
		System.out.println("****"+benefit_list.get(j).getText());
if(benefits[i].equalsIgnoreCase(benefit_list.get(j).getText())){
		
		benefit_list.get(j).click();
		
		dynamicwait(d1);
		click("plan_benfit_edit_btn", d1);
		dynamicwait(d1);
		click("provision_add_btn", d1);
		dynamicwait(d1);
		click("provision_search_btn", d1);
		dynamicwait(d1);
		type("provision_code_tb", data.get("provision_code_tb"), d1);
		click("provision_plan_search_btn", d1);
		dynamicwait(d1);
		click("provision_select_btn", d1);
		dynamicwait(d1);
		select("provision_status", data.get("provision_status"), d1);
		click("add_to_list_btn", d1);
		Thread.sleep(3000);
		click("provision_savestay_btn", d1);
		dynamicwait(d1);
		hovermouse("management_link", d1);
		click("ste_status_link", d1);
		Thread.sleep(4000);
		select("plan_benefit_status",  data.get("plan_benefit_status"), d1);
		type("effective_date_tb", data.get("effective_date_tb"), d1);
		click("plan_ben_set_status_btn", d1);
		dynamicwait(d1);
		click("save_plan_benefit_btn", d1);
		dynamicwait(d1);
break;
	
}	

	}
	


	}
	
	select("plan_status_dp", data.get("plan_status_dp"), d1);
	click("plan_save_btn", d1);
	dynamicwait(d1);
		takescreenshot(d1);
		teardown(d1);
		
		}catch(Throwable e){
			takescreenshot(d1);
		teardown(d1);
			ErrorUtil.addVerificationFailure(e);
		}
	}

}
