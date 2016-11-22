package jdbc;

import java.sql.*;
import java.util.*;
import java.lang.Math;

class CS360_20130143_JDBC {
    public static void main(String[] args)  {
        Connection con = null;
        Connection con2 = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        
        Scanner sc = new Scanner(System.in);
        String model, ram, hd, price, screen, speed, maker, type, color;
        String query;
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection( "jdbc:oracle:thin:@dbclick.kaist.ac.kr:1521:orcl", "s20130143", "s20130143");
            con2 = DriverManager.getConnection( "jdbc:oracle:thin:@dbclick.kaist.ac.kr:1521:orcl", "s20130143", "s20130143");
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            
            while(true) {
            	
                System.out.print("[Press enter to continue]");
                sc.nextLine();
                
            	System.out.println("===================================");
	            System.out.println("CS360 assignment #4");
	            System.out.println("Choose the problem with number: ");
	            System.out.println("1. Search laptops with constraints");
	            System.out.println("2. Add a new PC");
	            System.out.println("3. Find a PC having closest price");
	            System.out.println("4. Search product with a manufacturer");
	            System.out.println("5. ");
	            System.out.println("0. Quit");
            	System.out.println("===================================");
	            System.out.print("Choose a number[default = 0]: ");
	            
	            String idx;
	            idx = sc.nextLine();
	            
	            switch(idx) {
	            
	            
	            case "1":
	            	
	            	System.out.println("Problem 1.");
	            	System.out.print("select maximum price: ");
	            	String maxprice = sc.nextLine();
	            	if (maxprice.isEmpty()) maxprice = "2000000000";
	            	System.out.print("select minimum price: ");
	            	String minprice = sc.nextLine();
	            	if (minprice.isEmpty()) minprice = "0";
	            	
	            	System.out.print("select maximum ram: ");
	            	String maxram = sc.nextLine();
	            	if (maxram.isEmpty()) maxram = "2000000000";
	            	System.out.print("select minimum ram: ");
	            	String minram = sc.nextLine();
	            	if (minram.isEmpty()) minram = "0";
	            	
	            	System.out.print("select maximum hd: ");
	            	String maxhd = sc.nextLine();
	            	if (maxhd.isEmpty()) maxhd = "2000000000";
	            	System.out.print("select minimum hd: ");
	            	String minhd = sc.nextLine();
	            	if (minhd.isEmpty()) minhd = "0";
	            	
	            	System.out.print("select maximum screen: ");
	            	String maxscr = sc.nextLine();
	            	if (maxscr.isEmpty()) maxscr = "2000000000";
	            	System.out.print("select minimum screen: ");
	            	String minscr = sc.nextLine();
	            	if (minscr.isEmpty()) minscr = "0";
	            	
	            	query = "select * from laptop where "
	            			+ "price <= " + maxprice
	            			+ " and price >= " + minprice
	            			+ " and ram <= " + maxram
	            			+ " and ram >= " + minram 
	            			+ " and hd <= " + maxhd
	            			+ " and hd >= " + minhd
	            			+ " and screen <= " + maxscr
	            			+ " and screen >= " + minscr
	            			+ "";
	            	//System.out.println(query);
	            	rs = stmt.executeQuery(query);
	            	
	            	System.out.println("(model, speed, ram, hd, screen, price)");

	            	while (rs.next()) {
	            	       model = rs.getString(1);
	            	       speed = rs.getString(2);
	            	       ram = rs.getString(3);
	            	       hd = rs.getString(4);
	            	       screen = rs.getString(5);
	            	       price = rs.getString(6);
	            	      System.out.println(model + ", "
	            	      		+ speed + ", "
	            	      		+ ram + ", "
	            	      		+ hd + ", "
	            	      		+ screen + ", "
	            	      		+ price);
	            	}
	            	
	            	break;
	            	
	            	
	            case "2":
	            	// problem 1. addition:
	                System.out.println("Problem 2.");
	                
	                System.out.print("Manufacturer name: ");
	                 maker = sc.nextLine();
	                
	                System.out.print("Speed: ");
	                 speed = sc.nextLine();

	                System.out.print("Model number: ");
	                 model = sc.nextLine();
	                
	                System.out.print("RAM size: ");
	                 ram = sc.nextLine();
	                
	                System.out.print("Hard drive size: ");
	                 hd = sc.nextLine();
	                
	                System.out.print("Price: ");
	                 price = sc.nextLine();
	                
	                rs = stmt.executeQuery("Select model from product "
	                		+ "where model = "
	                		+ model);
	                
	                if (rs.next()) {
                		System.out.println(rs.getString(1));
	                	System.out.println("Model number is already occupied.");
	                	System.out.println("Please try again.");
	                	break;
	                } 
	                else {
	                	stmt.executeQuery("insert into product values ('"
                			+ maker + "', "
                			+ model + ", "
                			+ "'pc'"
                			+ ")");
	                	
	                	stmt.executeQuery("insert into PC "
	                			+ "values ("
	                			+ model + ", "
	                			+ speed + ", " 
	                			+ ram + ", "
	                			+ hd + ", "
	                			+ price
	                			+ ")");
	                	
	                	stmt.executeQuery("select * from product");
	                	System.out.println("(maker, model, type)");
	                	while (rs.next()) {
		            	       maker = rs.getString(1);
		            	       model = rs.getString(2);
		            	       type = rs.getString(3);
		            	      System.out.println(maker + ", "
		            	      		+ model + ", "
		            	      		+ type );
		            	}
	                	stmt.executeQuery("select * from pc");
	                	System.out.println("(model, speed, ram, hd, price)");
	                	while (rs.next()) {
		            	       model = rs.getString(1);
		            	       speed = rs.getString(2);
		            	       ram = rs.getString(3);
		            	       hd = rs.getString(4);
		            	       price = rs.getString(5);
		            	      System.out.println(model + ", "
		            	      		+ speed + ", "
		            	      		+ ram + ", "
		            	      		+ hd + ", "
		            	      		+ price);
		            	}
		            	break;
	                }
	                
	            case "3":
	            	System.out.println("Problem 3.");
	            	
	            	System.out.println("Which price do you prefer: ");
	            	
	            	price = sc.nextLine();
	            	int refprice = Integer.parseInt(price);
	            	int diff = 2000000000;
	            	
	            	rs = stmt.executeQuery("select price from pc");
                	
                	while (rs.next()) {
                		 price = rs.getString(1);
                	     int itemprice = Integer.parseInt(price);
	            		 if (java.lang.Math.abs(refprice - itemprice) < diff)
	            			 diff = java.lang.Math.abs(refprice - itemprice);
	            	}
	            	
                	query = "select * from pc where price = "
                			+ (refprice + diff) + " or price = " + (refprice - diff);
                	System.out.println(query);
                	
                	rs = stmt.executeQuery(query);
                	
                	System.out.println("(model, speed, ram, hd, price)");
                	while (rs.next()) {
	            	       model = rs.getString(1);
	            	       speed = rs.getString(2);
	            	       ram = rs.getString(3);
	            	       hd = rs.getString(4);
	            	       price = rs.getString(5);
	            	      System.out.println(model + ", "
	            	      		+ speed + ", "
	            	      		+ ram + ", "
	            	      		+ hd + ", "
	            	      		+ price);
	            	}
	            	break;
	            	
	            	
	            case "4":
	            	System.out.println("Problem 4.");
	            	System.out.print("Type the manufacturer you prefer: ");
	            	maker = sc.nextLine();
	            	
	            	query = "select * from product where maker = '" + maker + "'";
	            	//System.out.println(query);
	            	rs = stmt.executeQuery(query);
	            	
	            	int t = 0;
	            	
	            	while (rs.next()) {
	            		
	            		t = 1;
	            		maker = rs.getString(1);
	            		model = rs.getString(2);
	            		type = rs.getString(3);
	            		
	            		switch(type) {
	            		case "pc":
	            			rs2 = stmt2.executeQuery("select * from pc where model = " + model);
	            			System.out.print("PC (model, speed, ram, hd, price) ");
	            			while (rs2.next()) {
	 	            	       model = rs2.getString(1);
	 	            	       speed = rs2.getString(2);
	 	            	       ram = rs2.getString(3);
	 	            	       hd = rs2.getString(4);
	 	            	       price = rs2.getString(5);
	 	            	      System.out.println(model + ", "
	 	            	      		+ speed + ", "
	 	            	      		+ ram + ", "
	 	            	      		+ hd + ", "
	 	            	      		+ price);
	            			}
	            			continue;
	            			
	            		case "laptop":
	            			rs2 = stmt2.executeQuery("select * from laptop where model = " + model); 
	            			System.out.print("Laptop (model, speed, ram, hd, screen, price) ");
	            			while (rs2.next()) {
		 	            	       model = rs2.getString(1);
		 	            	       speed = rs2.getString(2);
		 	            	       ram = rs2.getString(3);
		 	            	       hd = rs2.getString(4);
		 	            	       screen = rs2.getString(5);
		 	            	       price = rs2.getString(6);
		 	            	      System.out.println(model + ", "
		 	            	      		+ speed + ", "
		 	            	      		+ ram + ", "
		 	            	      		+ hd + ", "
		 	            	      		+ screen + ", "
		 	            	      		+ price);
		            		}
	            			continue;
	            			
	            		case "printer":
	            			rs2 = stmt2.executeQuery("select * from printer where model = " + model);
	            			System.out.println("Printer (model, colork, type, price) ");
	            			while (rs2.next()) {
		 	            	       model = rs2.getString(1);
		 	            	       color = rs2.getString(2);
		 	            	       type = rs2.getString(3);
		 	            	       price = rs2.getString(4);
		 	            	       System.out.println(model + ", "
		 	            	      		+ color + ", "
		 	            	      		+ type + ", "
		 	            	      		+ price);
		            		}
	            			continue;
	            		}
	            	}
	            	if (t == 0){
	            		System.out.println("There is no product with such maker.");
	            		System.out.println("Please try again.");
	            	}
	            	break;
	            	
	            	
	            case "5":
	            	System.out.println("Problem 5.");
	            	System.out.println("Total budget: ");
	            	int budget = Integer.parseInt(sc.nextLine());
	            	System.out.println("minimum pc speed: ");
	            	double minspeed = Double.parseDouble(sc.nextLine());
	            	
	            	query = "select pc.model, printer.model, pc.price, printer.price, pc.speed, color from pc, printer";
	            	rs = stmt.executeQuery(query);
	            	
	            	String recpc = "";
	            	String recprinter = "";
	            	// set unavailable initial value
	            	int reccolor = -1;
	            	int recprice = 2000000000;
	            	
	            	while (rs.next()) {
	            	       String pcmodel = rs.getString(1);
	            	       String printermodel = rs.getString(2);
	            	       int totalprice = Integer.parseInt(rs.getString(3)) + Integer.parseInt(rs.getString(4));
	            	       double pcspeed = Double.parseDouble(rs.getString(5));
	            	       int iscolor = Integer.parseInt(rs.getString(6));
	            	       
	            	       if (totalprice > budget) continue;
	            	       if (pcspeed < minspeed) continue;
	            	       //System.out.println(pcmodel + " " + printermodel + " " + pcspeed + " " + (pcprice + printerprice) + " " + iscolor);
	            	       
	            	       if (reccolor < iscolor) {
	            	    	   recpc = pcmodel;
	            	    	   recprinter = printermodel;
	            	    	   recprice = totalprice;
	            	    	   reccolor = iscolor;
	            	       } else if (reccolor == iscolor && totalprice < recprice) {
	            	    	   recpc = pcmodel;
	            	    	   recprinter = printermodel;
	            	    	   recprice = totalprice;
	            	    	   reccolor = iscolor;
	            	       }
	            	       
	            	       
	            	}
	            	
	            	if (reccolor >= 0) {
            	       System.out.println("recommended PC model: " + recpc);
            	       System.out.println("recommended printer model: " + recprinter);
            	       System.out.println("total price: " + recprice);
            	       System.out.println("color printing availability: " + reccolor);
        	        }
        	        else {
        	     	   System.out.println("There is no system satisfying the conditions.");
            		   System.out.println("Please try again.");
        	    	   
        	        }
	            	break;
	            	
	            	
	            case "":
	            case "0":
	            	System.out.println("Bye");
	            	return;
	            	
	            	
	            default:
	            	System.out.println("Incomprehensible input. Please try again.");
	            }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
                if (sc != null) sc.close();
            } catch (Exception e) { }
        }
    }
}

