/**
 * 
 */
package com.gcit.library.view;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.gcit.library.domain.*;
import com.gcit.library.persistence.Dao;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @11:12:31 PM
 * @Admin.java
 */
public class Admin {

	private Scanner sc=new Scanner(System.in);
private Dao dao=new Dao();
	
	/**
	 * This is the Main menu of our Application
	 * @throws SQLException 
	 */
	public void Menu() throws SQLException{
		boolean exit_Main=false;
		System.out.println("");
		do{
			System.out.println("1) Add/Update/Delete  Genre Publishers and Book");System.out.println();
			System.out.println("2) Add/Update/Delete Author");System.out.println();
			System.out.println("3) Add/Update/Delete Library Branches");System.out.println();
			System.out.println("4) Add/Update/Delete Borrowers");System.out.println();
			System.out.println("5) Over-ride Due Date for a Book Loan"); System.out.println();
			System.out.println("6) Exit");	

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					 Menu1(); break;
				case '2':
					break;
				case '3':
					 break;
				case '4':
					break;
				case '5':
					 break;
				case '6':
					exit_Main=true;break;
				default :
					exit_Main=false;break;		
				}}
		}while(!exit_Main);
		
	}
	private  void Menu1() throws SQLException{
		boolean exit_Menu1=false;
		System.out.println("Pick the element to process");
		do{
			System.out.println("1) Genre");
			System.out.println("2) Publisher");
			System.out.println("3) Book");
			System.out.println("4) ???"); 
			System.out.println("5) Exit");	

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					Genre1(); break;
				case '2':
					Publisher1();break;
				case '3':
					 Book1(); break;
				case '4':
					break;
				case '5':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		System.out.println("You logged out");
	}
	
	
	private  void Genre1() throws SQLException{
		boolean exit_Menu1=false;
		System.out.println("Pick the operation to carry out");
		do{
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("4) Exit");
				

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					Addgenre(); break;
				case '2':
					Updategenre();break;
				case '3':
					Deletegenre(); break;
				case '4':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		
	}
	
	
	
	private  void Addgenre() throws SQLException{
		boolean exit_Add=false;

		String name;
		Map<Integer,String> m=dao.list_genre();
		do{

			System.out.println("Enter the Genre Name:");			         

	        name = sc.nextLine();
				 
				if(!m.containsValue(name))
				{	Genre g=new Genre();
				g.setGenreName(name); dao.InsertGenre(g);
					exit_Add=true;
				}else{
					System.out.println("This Genre already exists.");	
				}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}

	private  void Updategenre() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer,String> m=dao.list_genre();
		System.out.println("\nGenre ID | Genre Name ");
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, String> map: m.entrySet()){
			
			System.out.println(map.getKey()+"\t  "+map.getValue());
		}
		do{

			System.out.println("Enter the Genre Id you want to Update:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					Updategenre2(id);		
				}else{
					System.out.println("This Id does not exist exists.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Updategenre2(int id) throws SQLException{
		boolean exit_Add=false;
		Map<Integer,String> m=dao.list_genre();
		String name;
		do{

			System.out.println("Enter the new Genre Name:");			         

	        name = sc.nextLine();
				 
				if(!m.containsKey(name))
				{	Genre g=new Genre();
				g.setGenreId(id);
				g.setGenreName(name); dao.UpdateGenre(g);
					exit_Add=true;
				}else{
					System.out.println("Update successful");	
				}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Deletegenre() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer,String> m=dao.list_genre();
		System.out.println("\nGenre ID | Genre Name ");
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, String> map: m.entrySet()){
			
			System.out.println(map.getKey()+"\t  "+map.getValue());
		}
		do{

			System.out.println("Enter the Genre Id you want to delete:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					Genre g=new Genre();
					g.setGenreId(id);dao.DeleteGenre(g);		
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	
	
	private  void Publisher1() throws SQLException{
		boolean exit_Menu1=false;
		System.out.println("Pick the operation to carry out");
		do{
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("4) Exit");
				

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					AddPublisher(); break;
				case '2':
					UpdatePublisher();break;
				case '3':
					Deletepublisher(); 
					break;
				case '4':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		
	}
	

  private   void AddPublisher() throws SQLException{
		boolean exit_Add=false;
        
		String name,Address,phone;
		Set<Publisher> s=dao.list_publisher();
		Publisher p=new Publisher();
		do{

			System.out.println("Enter the Publisher Name:");
			name = sc.nextLine();
			System.out.println("Enter the Publisher Address:");			         
	        Address = sc.nextLine();
	        System.out.println("Enter the Publisher Phone:");			         
	        phone = sc.nextLine();
	        p.setName(name);
	        p.setAddress(Address);
	        p.setPhone(phone);
	        if(name != null && !name.trim().isEmpty()) {
				if(!s.contains(p))
				{	System.out.println("name:"+name.toString()); dao.InsertPublisher(p);
					exit_Add=true;
				}else{
					System.out.println("Error: This publisher already exists.");	
				}}else{			
					System.out.println();
					System.out.println("Error:  Name cannot be empty");	
				}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}

	private  void UpdatePublisher() throws SQLException{
		boolean exit_Add=false;

		int id;
		Set<Publisher> set=dao.list_publisher();
		System.out.println("\nPublisher ID | Publisher Name | Publisher Address | Publisher Phone ");
		System.out.println("---------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Publisher s: set){
			sk.add(s.getPublisherId());
			System.out.println(s.getPublisherId()+"\t "+s.getName()+"\t "+s.getAddress()+ "\t "+s.getPhone());
		}
		do{

			System.out.println("Enter the publisher Id you want to Update:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(sk.contains(id))
				{
					Updatepublisher2(id);		
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Updatepublisher2(int id) throws SQLException{
    boolean exit_Add=false;
        
		String name,Address,phone;
		Set<Publisher> s=dao.list_publisher();
		Publisher p=new Publisher();
		do{

			System.out.println("Enter the new Publisher Name:");
			name = sc.nextLine();
			System.out.println("Enter the new Publisher Address:");			         
	        Address = sc.nextLine();
	        System.out.println("Enter the new Publisher Phone:");			         
	        phone = sc.nextLine();
	        p.setPublisherId(id);
	        p.setName(name);
	        p.setAddress(Address);
	        p.setPhone(phone);
	        if(name != null && !name.trim().isEmpty()) {
				if(!s.contains(p))
				{	dao.UpdatePublisher(p);
					exit_Add=true;
				}else{
					System.out.println("Update successful");	
				}}else{			
					System.out.println();
					System.out.println("Error:  Name cannot be empty");	
				}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Deletepublisher() throws SQLException{
		boolean exit_Add=false;

		int id;
		Set<Publisher> set=dao.list_publisher();
		System.out.println();
		System.out.println("\nPublisher ID | Publisher Name | Publisher Address | Publisher Phone ");
		System.out.println("---------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Publisher s: set){
			sk.add(s.getPublisherId());
			System.out.println(s.getPublisherId()+"\t "+s.getName()+"\t "+s.getAddress()+ "\t "+s.getPhone());
		}
		do{
			System.out.println();
			System.out.println("Enter the Publisher Id you want to delete:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(sk.contains(id))
				{
					Publisher p= new Publisher();
					p.setPublisherId(id);dao.DeletePublisher(p);		
				}else{
					System.out.println("This Id does not exist exists.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Book1() throws SQLException{
		boolean exit_Menu1=false;
		System.out.println("Pick the operation to carry out");
		do{
			System.out.println("1) Add book");
			System.out.println("2) Update book");
			System.out.println("3) Delete book");
			System.out.println("4) Exit");
			

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					Addbook(); break;
				case '2':
					Updatebook();break;
				case '3':
					Deletebook(); break;
				case '4':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		
	}
	
	
	private   void Addbook() throws SQLException{
		boolean exit_Add=false;
        
		String name;
		int pubId;
		Set<Publisher> set=dao.list_publisher();
		Book b=new Book();
		System.out.println("\nPublisher ID | Publisher Name | Publisher Address | Publisher Phone ");
		System.out.println("---------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Publisher s: set){
			sk.add(s.getPublisherId());
			System.out.println(s.getPublisherId()+"\t "+s.getName()+"\t "+s.getAddress()+ "\t "+s.getPhone());
		}
		do{
			System.out.println();
			System.out.println("Enter the Publisher Id from the list above:");			         
	       
        try{
			pubId =Integer.parseInt(sc.nextLine().trim());
			System.out.println("Enter the Title Name:");
			name = sc.nextLine();
	         
	        if(name != null && !name.trim().isEmpty()) {
				if(sk.contains(pubId))
				{	b.setBookTitle(name);
				    b.setPublisherId(pubId);
					 dao.Insertbook(b);
					exit_Add=true;
				}else{
					
					b.setBookTitle(name);
				   	dao.Insertbook(b);
					exit_Add=true;
					//System.out.println("Error: This publisher already exists.");	
					
				}}else{			
					System.out.println();
					System.out.println("Error: Title cannot be empty");	
				}
        }catch(SQLException e){
        	System.out.println(e);
        	e.printStackTrace();
        }
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}

	private  void Updatebook() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer, Book> m=dao.list_book();
		Book b=new Book();
		System.out.println("\nBook ID        |       Title        |      Publisher ID");
		System.out.println("----------------------------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Map.Entry<Integer, Book> map: m.entrySet()){
			b=map.getValue();
		System.out.println(map.getKey()+"  \t\t "+b.getBookTitle()+"  \t\t "+b.getPublisherId());
		}
		do{

			System.out.println("Enter the Book Id you want to Update:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					Updatebook2(id);		
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	private  void Updatebook2(int id) throws SQLException{
	    boolean exit_Add=false;
	        
			String title;
			int pubId;
			Map<Integer,Book> m=dao.list_book();
			Set<Publisher> set=dao.list_publisher();
			Book b=new Book();
			System.out.println("\nPublisher ID | Publisher Name | Publisher Address | Publisher Phone ");
			System.out.println("---------------------------------------");
			Set<Integer> sk=new HashSet<>();
			for(Publisher s: set){
				sk.add(s.getPublisherId());
				System.out.println(s.getPublisherId()+"\t "+s.getName()+"\t "+s.getAddress()+ "\t "+s.getPhone());
			}
			do{
				System.out.println();
				System.out.println("Enter the New Publisher Id from the list above:\n");			         
		       
	        try{
				pubId =Integer.parseInt(sc.nextLine().trim());
				System.out.println("Enter the New Title:\n");
				title = sc.nextLine();
		        if(title != null && !title.trim().isEmpty()) {
					if(sk.contains(pubId))
					{	b.setBookId(id);
					b.setBookTitle(title);
					b.setPublisherId(pubId);
						dao.Updatebook(b);
						exit_Add=true;
					}					
					else{
						
						b.setBookId(id);
						b.setBookTitle(title);
						//b.setPublisherId();
							dao.Updatebook1(b);
							exit_Add=true;
					}
							
					}else{			
						System.out.println();
						System.out.println("Error:  Name cannot be empty");	
					}
		        }catch(Exception e){				
						System.out.println("INFO:Should be an integer!");				

					}
				
				
				if(!exit_Add){
					System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
					String option=(sc.nextLine());
					if(option.length()>0){
						switch(option.charAt(0)){			
						case 'q':
							exit_Add=true;break;

						default :
							exit_Add=false;break;		
						}}}


			}while(!exit_Add);

		}
	
	
	private  void Deletebook() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer, Book> m=dao.list_book();
		Book b=new Book();
		System.out.println("\nBook ID        |       Title        |      Publisher ID");
		System.out.println("----------------------------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Map.Entry<Integer, Book> map: m.entrySet()){
			b=map.getValue();
		System.out.println(map.getKey()+"  \t\t "+b.getBookTitle()+"  \t\t "+b.getPublisherId());
		}
		do{

			System.out.println("Enter the Book Id you want to delete:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					b.setBookId(id);
					dao.Deletebook(b);		
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);


	}
}