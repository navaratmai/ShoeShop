package M;

import java.sql.*;
import java.util.ArrayList;

import common.GlobalData;

public class CustomerManager
{
	public static ArrayList<CustomerDB> getAllCustomer()
	{
		ArrayList<CustomerDB> list = new ArrayList<CustomerDB>();

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM customer2";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("id");
				String firstName = rs.getString("name");
				String lastName = rs.getString("surname");
				String phone = rs.getString("phone");
				
				CustomerDB cc = new CustomerDB(id,firstName,lastName,phone);
				list.add(cc);
				// print the results
				System.out.format("%s, %s, %s, %s,\n", id, firstName, lastName, phone);
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static void saveNewCustomer(CustomerDB x)
	{
		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			
			Connection conn = DriverManager.getConnection(myUrl , GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);//สร้างคอนเนทชั่น
		
			
			String query = "INSERT INTO customer2 VALUES (0, '"+x.name+"'"
					+ ", '"+x.surname+"', '"+x.phone+"')";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void editCustomer(CustomerDB x)
	{
		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			
			Connection conn = DriverManager.getConnection(myUrl , GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);//สร้างคอนเนทชั่น
		
			
			String query = "UPDATE customer2 SET name = '"+x.name
					+"', surname = '"+x.surname
					+"', phone   = '"+x.phone
					+"'WHERE id  = " + x.id + "";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void deleteCustomer(CustomerDB x)
	{
		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			
			Connection conn = DriverManager.getConnection(myUrl , GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);//สร้างคอนเนทชั่น
		
			
			String query = "DELETE FROM customer2 WHERE id  = " + x.id + "";
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static ArrayList<CustomerDB> searchCustomer(String s)
	{
		ArrayList<CustomerDB> list = new ArrayList<CustomerDB>();

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM customer2 WHERE name LIKE '"+s+"' OR surname LIKE '"+s+"' ";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("id");
				String firstName = rs.getString("name");
				String lastName = rs.getString("surname");
				String phone = rs.getString("phone");
				
				CustomerDB cc = new CustomerDB(id,firstName,lastName,phone);
				list.add(cc);
				// print the results
				System.out.format("%s, %s, %s, %s,\n", id, firstName, lastName, phone);
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	private static void mysql_query(String string)
	{
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] arg)//เมธอดนี้ไว้เทส ดูว่าได้ไหม
	{
		ArrayList<CustomerDB> ll = CustomerManager.getAllCustomer();
		System.out.println(ll.size());
		
	}
}
