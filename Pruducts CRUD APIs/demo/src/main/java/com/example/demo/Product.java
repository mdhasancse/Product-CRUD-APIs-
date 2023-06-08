package com.example.demo;

public class Product {

	public Product() {}


	public Product(
		Integer no, Integer id,
		String name, Integer price
		)
	{

		super();

		this.no = no;
		this.id = id;
		this.name = name;
		this.price = price;
		
		
		

		 
	}

	 private Integer no;
	 private Integer id;
	 private String name;
	 private Integer price;
	 
	 
	 

	// Overriding the toString method
	// to find all the values
	@Override
 public String toString()
	
 {
		return "Product [no="
			+ no + ", id=" + id + ", name=" + name + ", price="
			+ price + "]";

		 
	}

	// Getters and setters of
	// the properties
	public Integer getNo()
	{

		return no;
	}

	public Integer getId()
	{

		return id;
	}


	public String getName()
	{

		return name;
	}


	public Integer getPrice()
	{

		return price;
	}
	
	
}

