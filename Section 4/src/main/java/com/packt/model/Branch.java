package com.packt.model;

public class Branch
{
	private int branchID;
	private String branchName;
	
	public Branch( )
	{
		
	}
	public Branch(int branchID, String branchName)
	{
		super();
		this.branchID = branchID;
		this.branchName = branchName;
	}
	public int getBranchID()
	{
		return branchID;
	}
	public void setBranchID(int branchID)
	{
		this.branchID = branchID;
	}
	public String getBranchName()
	{
		return branchName;
	}
	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}
	@Override
	public String toString()
	{
		return "Branch [branchID=" + branchID + ", branchName=" + branchName + "]";
	}
	
}
