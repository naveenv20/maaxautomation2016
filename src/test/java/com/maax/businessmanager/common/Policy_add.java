package com.maax.businessmanager.common;

import java.util.Hashtable;
import java.util.List;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.maax.businessmanager.util.TestBase;
import com.maax.businessmanager.util.TestDataProvider;

public class Policy_add extends TestBase{
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteCommontestdata")
	public void policyadd(Hashtable<String, String>data){
		
		WebDriver d1=null;
		d1=init("policyadd", "common", data, false);
		navigate(d1);
		dologin(d1);
		waitforelementpresent("grouppolicymainmenu", d1);
		hovermouse("grouppolicymainmenu", d1);
		waitforelementpresent("searchgrouplink", d1);
		click("searchgrouplink", d1);
		dynamicwait(d1);
		type("grouplegalname", data.get("grouplegalname"), d1);
		click("searchgroupbutton", d1);
		dynamicwait(d1);
		tableresultsearch("group-search_table_header", d1, data.get("Column_to_search"), "group-search_table_data", data.get("grouplegalname"));
	click("add_policy_button", d1);
	dynamicwait(d1);
	click("carrier_search_btn", d1);
	dynamicwait(d1);
	type("carrier_code_tb", data.get("carrier_code_tb"), d1);
	click("carrier_search_bttnn", d1);
	dynamicwait(d1);
	tableresultsearch("carrier_table_header", d1, data.get("Carrier_srch_Column_to_search"), "carrier_table_data", data.get("carrier_code_tb"));
	click("carrier_select_btn", d1);
//	waitforelementpresent("pol_effec_date", d1);
	dynamicwait(d1);
	type("pol_code_tb", data.get("pol_code_tb"), d1);
	select("billing_method_dp", data.get("billing_method_dp"), d1);
	select("sales_region_dp", data.get("sales_region_dp"), d1);
	type("policy_desc_tb", data.get("policy_desc_tb"), d1);
	type("minor_term_age_tb", data.get("minor_term_age_tb"), d1);
	select("term_age_cal_dp", data.get("term_age_cal_dp"), d1);
	select("term_age_cal_dp", data.get("term_age_cal_dp"), d1);
	dynamicwait(d1);
	select("industry_dp", data.get("industry_dp"), d1);
	dynamicwait(d1);
	select("category_dp", data.get("category_dp"), d1);
	dynamicwait(d1);
	select("sb_category_dp", data.get("sb_category_dp"), d1);
	select("occup_code_dp", data.get("occup_code_dp"), d1);
	select("Country", data.get("Country"), d1);
	dynamicwait(d1);
	select("Province", data.get("Province"), d1);
	select("waiting_period_dp", data.get("waiting_period_dp"), d1);
	type("Effective_date_tb", data.get("Effective_date_tb"), d1);
	
	click("Renewal_tab", d1);
	dynamicwait(d1);
	type("Curent_Ren_period_tb", data.get("Curent_Ren_period_tb"), d1);
	select("Curent_Ren_period_dp", data.get("Curent_Ren_period_dp"), d1);
	type("sun_renw_period_tb", data.get("sun_renw_period_tb"), d1);
	select("sun_renw_period_dp", data.get("sun_renw_period_dp"), d1);
	type("ren_advice_period_tb", data.get("ren_advice_period_tb"), d1);
	select("ren_advice_period_dp", data.get("ren_advice_period_dp"), d1);
	type("ren_reporting_period_tb", data.get("ren_reporting_period_tb"), d1);
	select("ren_reporting_period_dp", data.get("ren_reporting_period_dp"), d1);
	
	click("Certificate_num", d1);
	dynamicwait(d1);
	click("certi_options", d1);
	dynamicwait(d1);
	type("cert_num_numeric_charc", data.get("cert_num_numeric_charc"), d1);
	type("cert_num_numeric_fst_ch", data.get("cert_num_numeric_fst_ch"), d1);
	select("service_level", data.get("service_level"), d1);
	
	
	click("Policy_savestay", d1);
	
	dynamicwait(d1);
	//***************DIVISION ADD*******************
	click("divsubdclass_btn", d1);
	dynamicwait(d1);
	click("div_add_btn", d1);
	dynamicwait(d1);
	type("div_code_tb", data.get("div_code_tb"), d1);
	type("div_desc_tb", data.get("div_desc_tb"), d1);
	type("div_eff_date_date", data.get("div_eff_date_date"), d1);
	click("div_cor_search_btn", d1);
	dynamicwait(d1);
	waitforelementpresent("div_to_search", d1);
	type("div_to_search", data.get("div_to_search"), d1);
	click("div_search_btn", d1);
	dynamicwait(d1);
	tableresultsearch("div_cor_search_header", d1, data.get("div_cor_search_Column_to_search"), "div_cor_search_table_body", data.get("div_to_search"));
	click("div_select_btn", d1);
	dynamicwait(d1);
	click("div_save_btn", d1);
	dynamicwait(d1);
	click("cancel_divclass_btn", d1);
	dynamicwait(d1);
	//***************Class ADD*******************
	click("divsubdclass_btn", d1);
	dynamicwait(d1);
	click("class_add_btn", d1);
	dynamicwait(d1);
	type("class_code_tb", data.get("class_code_tb"), d1);
	type("class_desc_dp", data.get("class_desc_dp"), d1);
	type("class_date", data.get("class_date"), d1);
	click("class_save_btn", d1);
	dynamicwait(d1);
	
	}

}
