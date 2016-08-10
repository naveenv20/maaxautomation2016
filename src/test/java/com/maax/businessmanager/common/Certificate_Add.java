package com.maax.businessmanager.common;

import java.util.Hashtable;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.maax.businessmanager.util.TestBase;
import com.maax.businessmanager.util.TestDataProvider;


public class Certificate_Add extends TestBase {
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteCommontestdata")
	public void certificateadd(Hashtable<String, String>data){
		WebDriver d1=null;
		d1=init("Certificateadd", "common", data, true);
		checkrunmodes("Common", "Certificateadd", data.get("Runmode"));
		navigate(d1);
		dologin(d1);
		hovermouse("grouppolicymainmenu", d1);
		waitforelementpresent("searchgrouplink", d1);
		click("searchgrouplink", d1);
		dynamicwait(d1);
		type("grouplegalname", data.get("Group_Legal_name"), d1);
		click("searchgroupbutton", d1);
		dynamicwait(d1);
		tableresultsearch("group-search_table_header", d1, data.get("Column_to_search"), "group-search_table_data", data.get("Group_Legal_name"));
		click("member_search_button", d1);
		dynamicwait(d1);
		click("Member_add_button", d1);
		dynamicwait(d1);
		click("person_add_button_member", d1);
		dynamicwait(d1);
		type("person_FS_name", data.get("Member_FirstName"), d1);
		type("person_LS_name", data.get("Member_LastName"), d1);
		select("person_sex_DP", data.get("Member_Gender"), d1);
		type("personDOB", data.get("Member_DOB"), d1);
		select("correspondence_laun_DP", data.get("correspondence_laun"), d1);
		select("Adress_type_DP", data.get("Adress_type"), d1);
		select("Province_DP", data.get("Province"), d1);
		type("Adreess_line_1", data.get("Address_line_1"), d1);
		type("city", data.get("City"), d1);
		type("postal_code", data.get("postal_code"), d1);
		click("person_add_save_button", d1);
		dynamicwait(d1);
		type("hiredate", data.get("hiredate"), d1);
		select("Employement_type_DP", data.get("Employment_type"), d1);
		type("Wrk_hours", data.get("Number_of_hours"), d1);
		type("Jobtitle_Text", data.get("job_titile"), d1);
		select("Occupationcode_DP", data.get("Occupation_code"), d1);
		select("RiskFactor_DP", data.get("RiskFactor"), d1);
		select("income_type_DP", data.get("Income_type"), d1);
		select("income_freq_DP", data.get("Income_freq"), d1);
		type("Amount", data.get("Sal_amount"), d1);
		click("Member_savestay_button", d1);
		dynamicwait(d1);
		click("Add_certificate_button", d1);
		dynamicwait(d1);
		if(iselementeditable("Policy_DP", d1)){
		select("Policy_DP", data.get("policy"), d1);
		dynamicwait(d1);
		}
		if(findelementpresent("Overwrite_Employer_button", d1)){
		click("Overwrite_Employer_button", d1);
		dynamicwait(d1);
		}
		select("Division_Dp", data.get("Division"), d1);
		select("Class_DP", data.get("Class"), d1);
		dynamicwait(d1);
		select("Plan_DP", data.get("Plan"), d1);
		select("Family_Category_DP", data.get("Family_Category"), d1);
		select("Country_DP", data.get("Country"), d1);
		dynamicwait(d1);
		select("Province_of_Billing_DP", data.get("Province_of_Billing"), d1);
		//giving time to select province of adjudication
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	type("Application_date", data.get("Application_date"), d1);
	if(iselementeditable("Certificate_number_textbox", d1)){
	type("Certificate_number_textbox", data.get("Certificate_number"), d1);
	}
	click("Certiificate_Savestay_Button", d1);
	dynamicwait(d1);
	click("Certificate_add_ok_button", d1);
	dynamicwait(d1);
	
	
	//********************Beneficary Part
	
	hovermouse("certificate_menu", d1);
	click("beneficiary", d1);
	dynamicwait(d1);
	click("member life_beneficiary", d1);
	dynamicwait(d1);
	click("beneficiary_add", d1);
	dynamicwait(d1);
	click("beneficiary_info_add", d1);
	dynamicwait(d1);
	click("beneficiary_search_button", d1);
	dynamicwait(d1);
	type("beneficairy_ls_name", data.get("beneficiary_ls"), d1);
	click("beneficiary_serch_person", d1);
	dynamicwait(d1);
	click("beneficiary_select", d1);
	dynamicwait(d1);
	type("bene_relation", data.get("beneficary_relation"), d1);
	type("bene_percentage", data.get("beneficary_percentage"), d1);
	type("designated_date", data.get("Designated date"), d1);
	click("bene_details_save_button", d1);
	dynamicwait(d1);
	click("Beneficary_add_save_button", d1);
	dynamicwait(d1);
	click("Beneficiary_cancel_button", d1);
	dynamicwait(d1);
	
	//************************units part
	hovermouse("certificate_menu", d1);
	click("ben_family_cate_link", d1);
	dynamicwait(d1);
	
	String[] benefits={"Member Life"};
	
	for(int i=0;i<benefits.length;i++){
		List<WebElement> ben_fam_tree=d1.findElements(By.xpath("//div[@id='benefitFamilyCategory:tree-d-rt']/div/div/div[2]/div/div/a"));
	//	List<WebElement> ben_fam_tree=findelementsonthepage("cert_bene_tree_root", d1);
	for(int j=0;j<ben_fam_tree.size();j++){
if(benefits[i].equalsIgnoreCase(ben_fam_tree.get(j).getText())){
	ben_fam_tree.get(j).click();
	dynamicwait(d1);
	click("frst_row_ben_fam_cater_table", d1);
	dynamicwait(d1);
	click("units_button", d1);
	dynamicwait(d1);
	type("number_of_units", data.get("number_of_units"), d1);
	click("units_save_btn", d1);
	dynamicwait(d1);
		break;
}
	}
	}
	click("ben_family_cancel_btn", d1);
	dynamicwait(d1);
	
	
//	click("Enrollstay_button", d1);
//	dynamicwait(d1);
	
	
	
	}
	

}
