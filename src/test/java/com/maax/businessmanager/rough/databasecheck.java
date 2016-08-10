package com.maax.businessmanager.rough;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databasecheck {

	public static void main(String[] args) {
		
		//import the  sql package
		//java.sql.*
		//add the external JDBC jar
		Connection conn=null;
		String url="jdbc:oracle:thin:@plxde601:1521:PBA2SB";
		//		String url="jdbc:mysql://localhost:3306/";
		String dbname="PBA2SB";
		String driver="oracle.jdbc.driver.OracleDriver";
		String username="bcjlbou";
		String pass="TempPassword21";
		//jdbc:oracle:thin:@10.156.157.237:1521:sgatest", "ppm_user","ppm_user"
		System.out.println("Strat");
		//trying the connection 
		
		try{
			//create the driver class object .. in this case mysql related driver object created
			Class.forName(driver).newInstance();
			conn=DriverManager.getConnection(url,username,pass);
			//connection reference established
			Statement stmt=conn.createStatement();
			PreparedStatement pspmt= conn.prepareStatement("select * from ENABLERS.Agreement_Header where agreement_type=? and agreement_id in(select max(agreement_id) from ENABLERS.Agreement_Header where agreement_type=?)");
			pspmt.setString(1, "64");
			pspmt.setString(2, "64");
			ResultSet rs=stmt.executeQuery("select * from ENABLERS.Agreement_Header where agreement_type=64 and agreement_id in(select max(agreement_id) from ENABLERS.Agreement_Header where agreement_type=64)");
			ResultSet rs_o=pspmt.executeQuery();
			//first row
			rs.next();
			rs_o.next();
			//prints the first row name column value
			System.out.println(rs.getString("AGREEMENT_ID"));
			System.out.println(rs_o.getString("AGREEMENT_ID"));
			
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
		
		
		
		
		
	
		// TODO Auto-generated method stub

	}

}
