// Importing package to code module
package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
// Importing required classes
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@PostMapping(
	        path = "",
	        consumes = "application/json",
	        produces = "application/json")
	public ResponseEntity<String> addProduct(@RequestBody Product product, HttpServletResponse response)
	{
		HttpHeaders headers = new HttpHeaders();
        
        
		String url = "jdbc:mysql://localhost:3306/product";
        String username = "root";
        String password = "";
        CallableStatement cstmt = null;
        try {
             
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);

			String SQL = "{call sp_insert_productDetails (?, ?, ?, ?)}";
			cstmt = connection.prepareCall (SQL);
			cstmt.setInt(1, product.getNo());
			cstmt.setInt(2, product.getId());
			cstmt.setString(3, product.getName());
			cstmt.setInt(4, product.getPrice());
			cstmt.execute();
           
            connection.close();
        }  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			
			return ResponseEntity.internalServerError().headers(headers).body(e.getMessage());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		// Print statement
        

        return ResponseEntity.ok().headers(headers).body(product.toString());
	}
	
	@GetMapping(
	        path = "",
	        produces = "application/json")
	public ArrayList<Product> getProduct()
	{
		String url = "jdbc:mysql://localhost:3306/product";
        String username = "root";
        String password = "";
        CallableStatement cstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);

			String SQL = "{call sp_fetch_productDetails()}";
			cstmt = connection.prepareCall (SQL);
			ResultSet resultSet = cstmt.executeQuery();
			ArrayList<Product> products = new ArrayList<>();
		
			while (resultSet.next()) {
				Integer productSno = resultSet.getInt("s_no");
				Integer p_Id = resultSet.getInt("p_id");
			    String p_name = resultSet.getString("p_name");
			    Integer P_price = 	resultSet.getInt("p_price");
			    
			    Product product = new Product(productSno,  p_Id, p_name, P_price);
	            products.add(product);
			    // Do something with the values
			}

            connection.close();
            
            return products;
            
        }  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			

		}catch(Exception e)
		{
			e.printStackTrace();
		}
//		// Print statement
		
		return null;
	}
	
	@PutMapping("/{no}")
	public String UpdateProduct(@RequestBody Product product, @PathVariable Integer no) {
		String url = "jdbc:mysql://localhost:3306/product";
        String username = "root";
        String password = "";
        CallableStatement cstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);
            String SQL = "{call sp_update_productDetails (?, ?, ?,?)}";
			cstmt = connection.prepareCall (SQL);
			cstmt.setInt(1,product.getNo());
			cstmt.setInt(2, product.getId()); //can't update bcs its unique cant update
			cstmt.setString(3, product.getName());
			cstmt.setInt(4, product.getPrice());
			cstmt.execute();
	           
            connection.close();
        }  catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			return e.getMessage();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		// Print statement
		return "Product updated succesfully. "+product.toString();
	}

	  @DeleteMapping("/{no}")
	  public void deleteProduct(@PathVariable Integer no) {
		  String url = "jdbc:mysql://localhost:3306/product";
	        String username = "root";
	        String password = "";
	        CallableStatement cstmt = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	
	            Connection connection = DriverManager.getConnection(url,username,password);
	            String SQL = "{call sp_delete_productDetails (?)}";
				cstmt = connection.prepareCall (SQL);
				cstmt.setInt(1,no);
				cstmt.execute();
	            connection.close();
	        }  catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
	
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	  } 
}



