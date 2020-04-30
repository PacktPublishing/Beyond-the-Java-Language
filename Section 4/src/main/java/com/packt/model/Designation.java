package com.packt.model;

public class Designation
{
	private int designationID;
	private String description;
	
	public Designation( )
	{
		
	}
	public Designation(int designationID, String description)
	{
		super();
		this.designationID = designationID;
		this.description = description;
	}

	public int getDesignationID()
	{
		return designationID;
	}

	public void setDesignationID(int designationID)
	{
		this.designationID = designationID;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "Designation [designationID=" + designationID + ", description=" + description + "]";
	}
	
	
}
