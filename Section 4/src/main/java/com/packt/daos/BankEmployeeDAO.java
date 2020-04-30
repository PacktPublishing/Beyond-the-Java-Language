package com.packt.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.packt.model.BankEmployee;

public class BankEmployeeDAO
{
	private Connection connection = null;
	private ArrayList<BankEmployee> bankEmployees = null;
	
	public static final String  FIND_EMPLOYEE = "Select * from employeetbl where id = ?";
	public static final String ADD_EMPLOYEE = "insert into employeetbl values(?,?,?,?,?,?,?);";
	public static final String DELETE_EMPLOYEE = "delete from employeetbl where id = ?;";
	public static final String EDIT_EMPLOYEE = "UPDATE employeetbl SET firstName = ?, surname = ?, " + 
											   "age = ?, salary = ?, designation = ?, branch = ?" +
											   " where ID = ?";
	
	public BankEmployeeDAO()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("\t\tDriver Loaded..>>>");
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/bankdb";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("\n\t\tConnection made to bankdb...>>>>");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void closeConnection()
	{
		if(connection != null)
		{
			try
			{
				connection.close();
				System.out.println("\n\t\tConnection to bankdb closed...>>>");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<BankEmployee> listAllEmployees( )
	{
		ArrayList<BankEmployee> employees = new ArrayList<BankEmployee>();
		try
		{
			Statement stmt = connection.createStatement();
			String query = "SELECT * FROM employeetbl;";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String surname = rs.getString(3);
				int age = rs.getInt(4);
				double salary = rs.getDouble(5);
				String designation = rs.getString(6);
				String branch = rs.getString(7);
				
				BankEmployee b = new BankEmployee(id, firstName, 
							surname, age, salary, designation, branch);
				employees.add(b);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return employees;
	}
	
	public BankEmployee searchEmployeeByID(int ID)
	{
		BankEmployee foundEmployee = new BankEmployee();
		try
		{
			PreparedStatement pstmt = connection.prepareStatement(FIND_EMPLOYEE);
			pstmt.setInt(1, ID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next() == false)
			{
				return null;
			} 
			foundEmployee.setID(ID);
			String firstName = rs.getString(2);
			foundEmployee.setFirstName(firstName);
			String surname = rs.getString(3);
			foundEmployee.setSurname(surname);
			int age = rs.getInt(4);
			foundEmployee.setAge(age);
			double salary = rs.getDouble(5);
			foundEmployee.setSalary(salary);
			String designation = rs.getNString(6);
			foundEmployee.setDesignation(designation);
			String branch = rs.getString(7);
			foundEmployee.setBranch(branch);
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return foundEmployee;
	}
	
	public boolean addNewEmployee(BankEmployee newBankEmp)
	{
		//Let us first check for a duplicate.
		BankEmployee be = this.searchEmployeeByID(newBankEmp.getID());
		if(be != null)
		{
			System.out.println("\n\t\tBank Employee already exists.");
			return false;
		}
		PreparedStatement pstmt;
		try
		{
			pstmt = connection.prepareStatement(ADD_EMPLOYEE);
			pstmt.setInt(1, newBankEmp.getID());
			pstmt.setString(2, newBankEmp.getFirstName());
			pstmt.setString(3, newBankEmp.getSurname());
			pstmt.setInt(4, newBankEmp.getAge());
			pstmt.setDouble(5, newBankEmp.getSalary());
			pstmt.setString(6, newBankEmp.getDesignation());
			pstmt.setString(7, newBankEmp.getBranch());
			
			int success = pstmt.executeUpdate();
			if(success < 1)
			{
				return false;
			}
			
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean deleteEmployee(int ID)
	{
		BankEmployee be = this.searchEmployeeByID(ID);
		if(be == null)
		{
			//System.out.println("\n\t\tCannot find employee to delete.");
			return false;
		}
		PreparedStatement pstmt;
		try
		{
			pstmt = connection.prepareStatement(DELETE_EMPLOYEE);
			pstmt.setInt(1, ID);
			pstmt.execute();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean editBankEmployee(BankEmployee emp)
	{
		try
		{
			PreparedStatement pstmt = connection.prepareStatement(EDIT_EMPLOYEE);
			pstmt.setString(1, emp.getFirstName());
			pstmt.setString(2, emp.getSurname());
			pstmt.setInt(3, emp.getAge());
			pstmt.setDouble(4, emp.getSalary());
			pstmt.setString(5, emp.getDesignation());
			pstmt.setString(6, emp.getBranch());
			pstmt.setInt(7, emp.getID());
			
			pstmt.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
}
