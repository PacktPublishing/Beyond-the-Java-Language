package com.packt.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.packt.daos.BankEmployeeDAO;
import com.packt.model.BankEmployee;
import com.packt.utils.EasyIn;

public class PacktBanksUI
{
	BankEmployeeDAO dao = null;
	EasyIn inputKBD = null;
	int choice = 0;
	boolean runMenu;

	public PacktBanksUI()
	{
		runMenu = true;
		dao = new BankEmployeeDAO();
		inputKBD = new EasyIn();
	}

	public void printMainMenu()
	{
		while (runMenu == true)
		{
			System.out.println("\t\t+--------------------------------+");
			System.out.println("\t\t|      PackBanks Admin Page      |");
			System.out.println("\t\t+--------------------------------+");
			System.out.println("\t\t|     1. List all employees      |");
			System.out.println("\t\t|     2. Search for employee     |");
			System.out.println("\t\t|     3. Add employees           |");
			System.out.println("\t\t|     4. Edit employees          |");
			System.out.println("\t\t|     5. Delete an employee      |");
			System.out.println("\t\t|     6. Exit the program        |");
			System.out.println("\t\t+--------------------------------+");

			System.out.println(
					"\t\tMake your selection from the choices shown above by selecting " + "the correct number.");
			System.out.print("\n\t\tEnter you choice now:  ");
			choice = inputKBD.readInt();
			switch (choice)
			{
			case 1:
				this.listEmployees();
				break;
			case 2:
				this.searchEmployee();
				break;
			case 3:
				this.addEmployees();
				break;
			case 4:
				this.editEmployee();
				break;
			case 5:
				this.deleteEmployee();
				break;
			case 6:
				this.exitProgram();
				break;
			default:
				System.out.println("\n\n\t\tPlease choose from 1 through 6.");
			}
		}
	}

	private void exitProgram()
	{
		System.out.println("\n\n\t\t-----Thank you for using Packt Banks Admin System.-----");
		dao.closeConnection();

		runMenu = false;

	}

	private void deleteEmployee()
	{
		System.out.println("\t\t+-----------------------------------------+");
		System.out.println("\t\t|      PacktBanks Delete an Employee      |");
		System.out.println("\t\t+-----------------------------------------+");
		System.out.print("\n\t\tEnter Employee's ID Number you wish to delete: ");
		int ID = inputKBD.readInt();
		boolean tf = dao.deleteEmployee(ID);
		if (tf == true)
		{
			System.out.print("\n\t\tEmployee with ID number " + ID + " has been successfully deleted\n\n");
			return;
		}
		System.out.print("\n\t\tEmployee with ID number " + ID + " has not been deleted\n\n");
	}

	private void editEmployee()
	{
		System.out.println("\t\t+-----------------------------------------+");
		System.out.println("\t\t|        PacktBanks Edit Employee         |");
		System.out.println("\t\t+-----------------------------------------+");

		BankEmployee temp = null;
		ArrayList<BankEmployee> employees = dao.listAllEmployees();
		for (int pos = 0; pos < employees.size(); pos++)
		{
			temp = employees.get(pos);
			System.out.print("\t\tID: " + temp.getID() + "\t\tFirst Name: " + temp.getFirstName() + "\t\tSurname: "
					+ temp.getSurname());
			System.out.println("\n\t\t______________________________________________________________________\n\n");

		}

		System.out.print("\n\t\tChoose one the employees shown above by ID to edit: ");
		int choiceID = inputKBD.readInt();
		for (int pos = 0; pos < employees.size(); pos++)
		{
			temp = employees.get(pos);
			if (temp.getID() == choiceID)
			{
				break;
			}

		}
		boolean editing = true;
		int choiceToEdit = 0;
		while (editing)
		{
			System.out.println("\n\t\tYou have chosen to edit:\n");
			System.out.print("\t\tID: " + temp.getID() + "\n\t\tFirst Name: " + temp.getFirstName() + "\n\t\tSurname: "
					+ temp.getSurname() + "\n\t\tAge: " + temp.getAge() + "\n\t\tSalary: " + temp.getSalary()
					+ "\n\t\tDesignation: " + temp.getDesignation() + "\n\t\tbranch: " + temp.getBranch() + "\n");
			System.out.println("\t\t         _________________________\n");
			System.out.println("\t\t+-------------------------------------------------------------+");
			System.out.println("\t\t|        Choose which characteristics of employee to edit.    |");
			System.out.println("\t\t+----------------------------------+--------------------------+");
			System.out.println("\t\t|           1. First Name          |");
			System.out.println("\t\t|           2. Surname             |");
			System.out.println("\t\t|           3. Age                 |");
			System.out.println("\t\t|           4. Salary              |");
			System.out.println("\t\t|           5. Designation         |");
			System.out.println("\t\t|           6. Branch              |");
			System.out.println("\t\t+----------------------------------+");

			System.out.print("\n\t\tChoose one the items shown above to edit: ");
			choiceToEdit = inputKBD.readInt();
			switch (choiceToEdit)
			{
			case 1:
				System.out.println("\t\tEdit First Name");
				System.out.print("\t\tEnter new First Name: ");
				String fName = inputKBD.readString();
				temp.setFirstName(fName);
				break;
			case 2:
				System.out.println("\t\tEdit Surname: ");
				System.out.print("\t\tEnter new Surname: ");
				String sName = inputKBD.readString();
				temp.setSurname(sName);
				break;
			case 3:
				System.out.println("\t\tEdit Age: ");
				System.out.print("\t\tEnter new Age: ");
				int ageE = inputKBD.readInt();
				temp.setAge(ageE);
				break;
			case 4:
				System.out.println("\t\tEdit Salary");
				System.out.print("\t\tEnter new Salary: ");
				double sal = inputKBD.readDouble();
				temp.setSalary(sal);
				break;
			case 5:
				System.out.println("\t\tEdit Designation");
				System.out.print("\t\tEnter new Designation: ");
				String desig = inputKBD.readString();
				temp.setDesignation(desig);
				break;
			case 6:
				System.out.println("\t\tEdit Branch: ");
				System.out.print("\t\tEnter new Branch: ");
				String branch = inputKBD.readString();
				temp.setBranch(branch);
				break;

			}// end switch block

			System.out.println("\t\tDo you need to edit any more information?  yes/no : ");
			System.out.println("\t\tPlease type in 'yes' or 'no' ");
			System.out.print("\n\t\tEnter your choice: yes/no:  ");
			String chYesNo = inputKBD.readString();
			chYesNo = chYesNo.toLowerCase();

			if (chYesNo.equalsIgnoreCase("no"))
			{
				editing = false;
				break;
			}
			if (chYesNo.equalsIgnoreCase("yes"))
			{
				editing = true;
				
			}

		} // end while (editing) loop
		System.out.println(temp.toString());
		dao.editBankEmployee(temp);

	}

	private void addEmployees()
	{
		BankEmployee temp = null;
		temp = new BankEmployee();
		System.out.println("\t\t+-----------------------------------------+");
		System.out.println("\t\t|        PacktBanks Add Employee           |");
		System.out.println("\t\t+-----------------------------------------+");

		System.out.print("\n\t\tEnter Employee's ID Number: ");
		temp.setID(inputKBD.readInt());
		System.out.print("\n\t\tEnter Employee's First Name: ");
		temp.setFirstName(inputKBD.readString());
		System.out.print("\n\t\tEnter Employee's Surname: ");
		temp.setSurname(inputKBD.readString());
		System.out.print("\n\t\tEnter Employee's age in years: ");
		temp.setAge(inputKBD.readInt());
		System.out.print("\n\t\tEnter Employee's Salary: ");
		temp.setSalary(inputKBD.readDouble());
		System.out.print("\n\t\tEnter Employee's Designation: ");
		temp.setDesignation(inputKBD.readString());
		System.out.print("\n\t\tEnter Employee's Branch: ");
		temp.setBranch(inputKBD.readString());
		
		dao.addNewEmployee(temp);

	}

	private void searchEmployee()
	{
		BankEmployee temp = null;
		System.out.println("\t\t+-----------------------------------------+");
		System.out.println("\t\t|      PacktBanks Search for Employee      |");
		System.out.println("\t\t+-----------------------------------------+");

		System.out.print("\t\tEnter the Employee's ID you are looking for: ");
		int id = inputKBD.readInt();
		temp = dao.searchEmployeeByID(id);
		if (temp == null)
		{
			System.out.println("\n\t\tNo such employee exists with that ID");
			return;
		}

		System.out.print("\t\tID: " + temp.getID() + "\n\t\tFirst Name: " + temp.getFirstName() + "\n\t\tSurname: "
				+ temp.getSurname() + "\n\t\tAge: " + temp.getAge() + "\n\t\tSalary: " + temp.getSalary()
				+ "\n\t\tDesignation: " + temp.getDesignation() + "\n\t\tbranch: " + temp.getBranch() + "\n");
		System.out.println("\t\t         _________________________\n\n");
	}

	private void listEmployees()
	{
		System.out.println("\n");
		System.out.println("\t\t+-----------------------------------------+");
		System.out.println("\t\t|      PacktBanks List all Employees      |");
		System.out.println("\t\t+-----------------------------------------+");
		ArrayList<BankEmployee> employees = dao.listAllEmployees();
		for (int pos = 0; pos < employees.size(); pos++)
		{
			BankEmployee temp = employees.get(pos);
			System.out.print("\t\tID: " + temp.getID() + "\n\t\tFirst Name: " + temp.getFirstName() + "\n\t\tSurname: "
					+ temp.getSurname() + "\n\t\tAge: " + temp.getAge() + "\n\t\tSalary: " + temp.getSalary()
					+ "\n\t\tDesignation: " + temp.getDesignation() + "\n\t\tbranch: " + temp.getBranch() + "\n");
			System.out.println("\t\t         _________________________\n");

		}
	}

	public static void main(String[] args)
	{
		PacktBanksUI pbUI = new PacktBanksUI();
		pbUI.printMainMenu();
	}
}
