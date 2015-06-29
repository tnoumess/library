package com.gcit.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.gcit.library.datefactory.Timefactory;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 28, 2015
 * @8:40:50 PM
 * @Start.java
 */
public class Library {
	
	private  Timefactory tf=new Timefactory();
	
	/**
	 * This is the Main menu of our Application
	 */
	public void Menu(){
		boolean exit_Main=false;
		do{
			System.out.println("1) Librarian");
			System.out.println("2) Administrator");
			System.out.println("3) Borrower");
			System.out.println("4) Exit");	

			String option=(new Scanner(System.in).nextLine());
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					exit_Main=false; Lib1();break;
				case '2':
					exit_Main=false;break;
				case '3':
					exit_Main=false; Borr(list_Borrower());break;
				case '4':
					exit_Main=true;break;
				default :
					exit_Main=false;break;		
				}}
		}while(!exit_Main);
		System.out.println("You logged out");
	}
	
	
	
	/**
	 * Librarian main menu
	 */
	private  void Lib1(){
		boolean exit_Lib1=false;
		do{
			System.out.println("1) Manage");
			System.out.println("2) Return");
			String option=(new Scanner(System.in).nextLine());
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					exit_Lib1=false; Lib2(list_Libr());break;
				case '2':
					exit_Lib1=true; break;
				default :
					exit_Lib1=false;break;		
				}}
		}while(!exit_Lib1);
		//System.out.println("You logged out");
	}
	
	
	/**
	 * Librarian
	 * 
	 * This method prints the list of library branches to choose from.
	 * @param m : Map containing the list of libraries 
	 */
	private  void Lib2(Map<Integer,String> m){//common to all libraries
		boolean exit_Lib2=false;
		
		int id;
		System.out.println("\nBranch ID | Branch Name");
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, String> map: m.entrySet()){

			System.out.println(map.getKey()+"\t    "+map.getValue());
		}
		do{
			System.out.println("Enter branch Id you want to manage:");
			Scanner scan4=new Scanner(System.in);	
			try{          

				id = Integer.parseInt(scan4.nextLine().trim());

				if(m.containsKey(id))
				{Lib3(id);
				exit_Lib2=true;
				}
 
			}catch(Exception e){				
				System.out.println("INFO:Unknown branch!");				

			}
			if(!exit_Lib2){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(new Scanner(System.in).nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Lib2=true;break;

					default :
						exit_Lib2=false;break;		
					}}}


		}while(!exit_Lib2);
	}
	
	/**
	 * Librarian:
	 * 
	 * Action to perform on a given the library.
	 * @param Idlib : Id of the library to manage
	 */
	private  void Lib3(int Idlib){
		boolean exit_Lib3=false;
		do{
			System.out.println("1) Update details of library");
			System.out.println("2) Add copies of Book to the branch");
			System.out.println("3) Return");
			String option=(new Scanner(System.in).nextLine());
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					exit_Lib3=false; UpdLib(Idlib);break;
				case '2':
					exit_Lib3=false; AddCopies(Idlib,list_Book());break;
				case '3':
					exit_Lib3=true;break;
				default :
					exit_Lib3=false;break;		
				}}
		}while(!exit_Lib3);
	}
	/**
	 * Librarian:
	 * This method Receive the new Branch name and new address of the library with id @param 
	 * @param IdLib :Id of the library to manage
	 */
	private  void UpdLib(int IdLib){
		boolean exit_Uplib=false;
		do{
			System.out.println("You have chosen to update the Branch with Branch Id:"+IdLib);

			System.out.println(" Enter the new branch name  or enter N/A for no change");
			Scanner scan2=new Scanner(System.in);
			String newName=scan2.nextLine().toString(); 

			System.out.println(" Enter the new branch address  or enter N/A for no change");
			Scanner scan3=new Scanner(System.in);
			String newAddress=scan3.nextLine().toString();


			if((!newName.trim().equals("")||(!newAddress.trim().equals("")))&&((!newName.equals("N/A")||(!newAddress.equals("N/A"))))){

				if(newName.trim().equals(""))
				{newName=null;}
				if(newAddress.trim().equals(""))
				{newAddress=null;}
				Upd_Libr(IdLib, newName, newAddress);
				System.out.println("update applied");
				exit_Uplib=true;
			}else{
				System.out.println("No update applied");
			}


			if(!exit_Uplib){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(new Scanner(System.in).nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Uplib=true;break;

					default :
						exit_Uplib=false;break;		
					}}}
		}while(!exit_Uplib);
	}

/**
 * Librarian:
 * 
 * This method prints the list of book available
 * and asks the user to pick the book to update.
 * @param libId: Id of the library to add book copy to.
 * @param m : List of available book from all branches
 */
	private  void AddCopies(int libId,Map<Integer,String> m){
		boolean exit_Add=false;

		int id;
		System.out.println("\nBook ID | Book Title         | Publisher Id ");
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, String> map: m.entrySet()){
			String[] data = map.getValue().split("\\|");
			System.out.println(map.getKey()+"\t  "+data[0]+"\t  "+data[1]);
		}
		do{

			System.out.println("Enter the Book Id you want to add copies of, to your branch:");
			
			Scanner scan4=new Scanner(System.in);	
			try{          

				id = Integer.parseInt(scan4.nextLine().trim());
				if(m.containsKey(id))
				{
					add(libId,id);
					exit_Add=true;}
			}catch(Exception e){				
				System.out.println("INFO:Should be an integer!");				

			}
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(new Scanner(System.in).nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}

	
	/**
	 * Librarian
	 * : This method receives the new quantity of the book of Id @param
	 * @param LibId: library Id
	 * @param bookId: BookId
	 */
	private  void add(int LibId,int bookId){
		boolean exit_add=false;
		int num;

		int no=getBookNum(bookId, LibId);
		do{ 


			System.out.println("Existing number of copies: "+no);
			System.out.println("Enter new number of copies");
			Scanner scan4=new Scanner(System.in);	
			try{          

				num = Integer.parseInt(scan4.nextLine().trim());

				if(num>=0){

					if(setBookNum(num, bookId, LibId)){
						System.out.println("Number of book updated");}
					exit_add=true;
				}else{

					System.out.println("INFO: Invalid number!");
				}

			}catch(Exception e){				
				System.out.println("INFO: Invalid number!");				

			}

			if(!exit_add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(new Scanner(System.in).nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_add=true;break;

					default :
						exit_add=false;break;		
					}}}
		}while(!exit_add);
	}
	
	
/**
 * Borrower:
 * This method ask the user to enter his Card ID
 * 
 * @param m: Id of the borrower.
 */
	private  void Borr(Map<Integer,String> m){
		boolean exit_Borr=false;
		do{
			System.out.println("Enter your Card Id:");
			Scanner scan2=new Scanner(System.in);
			int num;

			try{          

				num = Integer.parseInt(scan2.nextLine().trim());
				
				if(m.containsKey(num))
				{

					Borr1(num);
					exit_Borr=true;}else{
						System.out.println("INFO:Unknown Card Id!");				

					}

			}catch(Exception e){				
				System.out.println("INFO:Unknown Card id!");				

			}
			if(!exit_Borr){
			System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
			String option=(new Scanner(System.in).nextLine());
			if(option.length()>0){
				switch(option.charAt(0)){			
				case 'q':
					exit_Borr=true;break;

				default :
					exit_Borr=false;break;		
				}}}
		}while(!exit_Borr);
		
	}
	
	/**
	 * Borrower:
	 * 
	 * This is the main menu of the the borrower
	 * @param idBorr: Id of the borrower
	 */
	private  void Borr1(int idBorr){
		boolean exit_Borr1=false;
		do{
			System.out.println("1) Check out book");
			System.out.println("2) Returning a Book");
			System.out.println("3) RETURN");
			String option=(new Scanner(System.in).nextLine());
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					exit_Borr1=false;CheckOut(idBorr); break;
				case '2':
					exit_Borr1=false;Return(idBorr);break;
				case '3':
					exit_Borr1=true;break;
				default :
					exit_Borr1=false;break;		
				}}
		}while(!exit_Borr1);
	}
/**
 * Borrower:
 * This method prints the library to checkout book from.
 * 
 * @param idBorr: Id borrower.
 */
	private  void CheckOut(int idBorr){
		/**
		 * should create for loop to list libraries.
		 */
		boolean exit_Checkout=false;
		

		int id;
		System.out.println("\nBranch ID | Branch Name");
		System.out.println("---------------------------------------");
		Map<Integer,String> list=list_Libr();
		for(Map.Entry<Integer, String> map: list.entrySet()){

			System.out.println(map.getKey()+"\t    "+map.getValue());
		}
		do{
			System.out.println("Pick the branch you want to check out from:");
			
			Scanner scan4=new Scanner(System.in);	
			try{          

				id = Integer.parseInt(scan4.nextLine().trim());
				if(list.containsKey(id))
				{Checkout1(idBorr,id);
				exit_Checkout=true;
				}else{
					System.out.println("INFO:Unknown branch!");
				}

			}catch(Exception e){				
				System.out.println("INFO:Unknown branch!");				

			}

			if(!exit_Checkout){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(new Scanner(System.in).nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Checkout=true;break;

					default :
						exit_Checkout=false;break;		
					}}}
		}while(!exit_Checkout);
	}
    /**
     * Borrower:
     * Lists the book to checkout from library @param2
     * @param1 idBorr: Borrower Id
     * @param2 idbranch: Branch Id
     */
	private  void Checkout1(int idBorr, int idbranch){
		boolean exit_Checkout1=false;
		boolean good=false;
		int id;
		String[] data;
		Set<Integer> s=new HashSet<>();
		System.out.println("\nBook ID | Branch Id   | num of copies");
		System.out.println("---------------------------------------");
		for(Map.Entry<String, String> map: list_Book_by_lib(idbranch).entrySet()){
			data = map.getKey().split("\\|");
			s.add(Integer.parseInt(data[0]));
			System.out.println(data[0]+"\t  "+data[1]+"\t        "+map.getValue());
		}
		do{

			try{      				

				good=true;
				if(s.size()>0){

					System.out.println("Pick the Book you want to check out:");
					//for loop to list the book
					Scanner scan4=new Scanner(System.in);
					id = Integer.parseInt(scan4.nextLine().trim());
					if(s.contains(id))
					{checkout_book((int)id, idbranch,idBorr);
					exit_Checkout1=true;
					
					}else{
						System.out.println("No book found with the given Id!");
					}}else{	
						System.out.println("No book in this library!"); exit_Checkout1=true;
					}

			}catch(Exception e){				
				System.out.println("INFO:No book found with the given Id!");				
				good=false; 
			}
			if(!exit_Checkout1){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(new Scanner(System.in).nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Checkout1=true;break;

					default :
						exit_Checkout1=false;break;		
					}}}

		}while(!exit_Checkout1);

	}
    /**
     * Borrower:
     * Lists the branch to return the book to and 
     * asks the user to pick one branch.
     * @param idBorr: Borrower Id
     */
	private  void Return(int idBorr){
		/**
		 * should create for loop to list libraries.
		 */
		boolean exit_Return=false;


		int lib_id;
		System.out.println("\nBranch ID | Branch Name");
		System.out.println("---------------------------------------");
		Map<Integer,String> list=list_Libr();
		for(Map.Entry<Integer, String> map: list.entrySet()){

			System.out.println(map.getKey()+"\t    "+map.getValue());
		}
		do{
			System.out.println("Pick the branch you want to return to:");
			//for loop to list the branch
			Scanner scan4=new Scanner(System.in);	
			try{          

				lib_id = Integer.parseInt(scan4.nextLine().trim());
				if(list.containsKey(lib_id))
				{Return1(idBorr,lib_id);
				exit_Return=true;
				}else{
					System.out.println("INFO:Unknown branch!");
				}

			}catch(Exception e){				
				System.out.println("INFO:Unknown branch!");				

			}
			if(!exit_Return){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(new Scanner(System.in).nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Return=true;break;

					default :
						exit_Return=false;break;		
					}}}

		}while(!exit_Return);
	}
    /**
     * Borrower:
     * Lists the books loaned by user @param1 and
     * asks the user to pick the book to return.
     * @param1 borrId: Borrower Id
     * @param2 libId: Branch Id
     */
	private  void Return1(int borrId,int libId){
		boolean exit_Return1=false;
		boolean good=false;
		int id;

		String[] data;
		Set<Integer> s=new HashSet<>();
		System.out.println("");
		System.out.println("");
		System.out.println("List of books you have loaned:");
		System.out.println("");
		System.out.println("\nBook ID | Branch Id   | dueDate");
		System.out.println("---------------------------------------");
		for(Map.Entry<String, String> map: list_Book_out(borrId).entrySet()){
			data = map.getKey().split("\\|");
			s.add(Integer.parseInt(data[0]));
			System.out.println(data[0]+"\t  "+data[1]+"\t        "+map.getValue());
		}


		do{
			System.out.println("Pick the Id of the book you want to return:");
			Scanner scan4=new Scanner(System.in);	
			try{          

				id = Integer.parseInt(scan4.nextLine().trim());

				if(s.contains(id)){
					return_book(id, libId, borrId);
					return_book_incr(id, libId, borrId);

					exit_Return1=true;
				}else{
					System.out.println("INFO:No book found!"); 
				}


			}catch(Exception e){				
				System.out.println("INFO:No book found!");				
				good=false; 
			}
			if(!exit_Return1){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(new Scanner(System.in).nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Return1=true;break;

					default :
						exit_Return1=false;break;		
					}}}

		}while(!exit_Return1);

	}
    /**
     * Any User:
     * Lists all the library branches
     * @return Map containing the list of libraries.
     */
	private  Map<Integer,String> list_Libr(){

		Map<Integer,String> l=new HashMap<Integer,String>();
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "select * from tbl_library_branch";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()){
				l.put(rs.getInt("branchId"), rs.getString("branchName"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

	/**
	 * This method update the library with its new Name and Address
	 * @param IdLib: Library Id
	 * @param name: New name of library
	 * @param address New address of library
	 * @return True if the update was successful and False otherwise
	 */
	private  boolean Upd_Libr(int IdLib,String name,String address){

		Map<Integer,String> l=new HashMap<Integer,String>();
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "UPDATE tbl_library_branch SET branchName = ?, branchAddress=? WHERE branchId = ? ";		
			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(3, IdLib);
			pstmt.setString(1,name);
			pstmt.setString(2,address);
			pstmt.executeUpdate(); 
			System.out.println("branch updated");

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;

	}
	/**
	 * Print the list of books 
	 * @return Map containing the list of books
	 */

	private  Map<Integer,String> list_Book(){

		Map<Integer,String> l=new HashMap<Integer,String>();
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "select * from tbl_book";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);

			ResultSet rs = pstmt.executeQuery(); 

			while(rs.next()){
				l.put(rs.getInt("bookId"), rs.getString("title")+"|"+rs.getString("pubId"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;

	}
/**
 * This method returns the quantity of the book
 * having the id @param1
 * @param1 id: Id of the book
 * @param2 LibId: Id of the Library
 * @return number of book or -1 if error occurs
 */
	private  int getBookNum( int id,int LibId){


		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "select noOfCopies from tbl_book_copies where bookId=? and branchId=?";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, id);
			pstmt.setInt(2, LibId);
			ResultSet rs = pstmt.executeQuery(); 


			if(rs != null && rs.next()){
				return rs.getInt("noOfCopies");}
			return 0;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return -1;
		}


	}

	/**
	 * Update the number of copies of a given book
	 * from a given library.
	 * @param no: New quantity
	 * @param id: Book Id
	 * @param LibId: Library Id
	 * @return True if the book is in the given library 
	 * or False otherwise
	 */
	private  boolean setBookNum( int no,int id,int LibId){


		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();

			String checkexist = "select * from tbl_book_copies where (bookId=? and branchId=?)";
			PreparedStatement pstmt = conn.prepareStatement(checkexist);
			pstmt.setInt(1, id);
			pstmt.setInt(2, LibId);
			ResultSet rs= pstmt.executeQuery(); 

			if(rs != null && rs.next()){


				String selectQuery = "update tbl_book_copies set noOfCopies=? where (bookId=? and branchId=?)";

				PreparedStatement pstmt2 = conn.prepareStatement(selectQuery);
				pstmt2.setInt(1, no);
				pstmt2.setInt(2, id);
				pstmt2.setInt(3, LibId);
				pstmt2.executeUpdate(); 	

			}else{

				String selectQuery = "insert into tbl_book_copies values (?,?,?)";

				PreparedStatement pstmt2 = conn.prepareStatement(selectQuery);
				pstmt2.setInt(1, id);
				pstmt2.setInt(2, LibId);
				pstmt2.setInt(3,no );
				pstmt2.executeUpdate();

			}

			return true;	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return false;
		}


	}
    /**
     * Return the list of borrowers.
     * Used to authenticate borrowers.
     * @return Map containing the list of borrowers.
     */
	private  Map<Integer,String> list_Borrower(){

		Map<Integer,String> l=new HashMap<Integer,String>();
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "select * from tbl_borrower";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);

			ResultSet rs = pstmt.executeQuery(); 

			while(rs.next()){
				l.put(rs.getInt("cardNo"), rs.getString("name"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;

	}

    /**
     * List the books present in a given Library
     * @param lib_Id: Library Id
     * @return Map containing the list of book presents in
     * Library @param
     */
	private  Map<String,String> list_Book_by_lib(int lib_Id){

		Map<String,String> l=new HashMap<String,String>();
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "select * from tbl_book_copies where branchId=? and noOfCopies > 0";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, lib_Id);
			ResultSet rs = pstmt.executeQuery(); 

			while(rs.next()){
				l.put(rs.getInt("bookId")+"|"+rs.getInt("branchId"), rs.getString("noOfCopies"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;

	}

    /**
     * Check out a book from a given library by
     * a given Borrower. 
     * @param book_Id: Book Id
     * @param lib_Id: Library Id
     * @param BorrId: Borrower Id
     * @return True if the check out is successful
     * or false otherwise.
     */
	private  boolean checkout_book(int book_Id,int lib_Id,int BorrId){
		if(check_Auth_book(book_Id, lib_Id, BorrId)){
			Map<Integer,String> l=new HashMap<Integer,String>();
			Connection conn=null;;
			try {
				conn =getconnection();		
				Statement stmt = conn.createStatement();
				conn.setAutoCommit(false);

				String selectQuery = "UPDATE tbl_book_copies SET noOfCopies=noOfCopies-1 where (bookId=? and branchId =? )";		
				PreparedStatement pstmt = conn.prepareStatement(selectQuery);
				pstmt.setInt(1,book_Id);
				pstmt.setInt(2,lib_Id);
				pstmt.executeUpdate(); 

				String setfkc="set foreign_key_checks=0";	
				PreparedStatement pStatement=conn.prepareStatement(setfkc);
				pStatement.execute();

				String selectQuery2 = "insert into tbl_book_loans values(?,?,?,curdate(),?,null)";		
				PreparedStatement pstmt2 = conn.prepareStatement(selectQuery2);
				pstmt2.setInt(1,book_Id);
				pstmt2.setInt(2,lib_Id);
				pstmt2.setInt(3,BorrId);
				pstmt2.setDate(4,tf.gettime().oneweekafter_d());
				pstmt2.executeUpdate(); 

				String setfkc1="set foreign_key_checks=1";	
				PreparedStatement pStatement1=conn.prepareStatement(setfkc);
				pStatement.execute();

				System.out.println();
				System.out.println("Update successful! ");	
				System.out.println("Please return book by: "+tf.gettime().oneweekafter_d());


				conn.commit();

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

			return true;}
		else{
			System.out.println("Warning: You cannot checkout this book since you already have it!");
			return false;
		}

	}
	/**
	 * Verifies if the Borrower is allowed to check out
	 * a given book from a given library.
	 * The rule is that you cannot borrow another copy of 
	 * the same book(from library @param2)
	 * If you have have not returned (to library @param2)
	 * the initial copy you borrowed.
	 * @param book_Id: Book Id
	 * @param2 lib_Id: Library Ib
	 * @param BorrId: Borrower Id
	 * @return False if he already has the book
	 * and has not returned yet. from the  
	 */
	private  boolean check_Auth_book(int book_Id,int lib_Id,int BorrId){
		Connection conn=null;
		try {
			conn =getconnection();
			Statement stmt = conn.createStatement();

			/**
			 * Is the user has the book already? is so return non empty result
			 */
			String selectQuery2 = "select*from tbl_book_loans where (bookId=? and branchId=? and cardNo=?)";		
			PreparedStatement pstmt2 = conn.prepareStatement(selectQuery2);
			pstmt2.setInt(1,book_Id);
			pstmt2.setInt(2,lib_Id);
			pstmt2.setInt(3,BorrId);
			ResultSet rs=pstmt2.executeQuery(); 
			
			if(rs.next()&&rs.getString("dateIn")==null) 
				return false;
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception");
			e.printStackTrace();

			return false;
		}


	}

 /**
  * Prints the list of book borrowed by User @param
  * @param borr_id: borrower Id
  * @return Map containing the list of books borrowed 
  */
	private  Map<String,String> list_Book_out(int borr_id){

		Map<String,String> l=new HashMap<String,String>();
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery2 = "select*from tbl_book_loans where cardNo=?";		
			PreparedStatement pstmt2 = conn.prepareStatement(selectQuery2);
			pstmt2.setInt(1, borr_id);	
			ResultSet rs=pstmt2.executeQuery(); 
			

			while(rs.next()) {

				if(rs.getString("dateIn")==null)
					l.put(rs.getInt("bookId")+"|"+rs.getInt("branchId"), rs.getString("dueDate"));

			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return l;
		}
		return l;

	}
    /**
     * Updates the date when the book was return
     * @param book_Id: Book Id
     * @param lib_Id: Library Id
     * @param BorrId: Borrower Id
     * @return True if the update was successful or false otherwise
     */

	private  boolean return_book(int book_Id,int lib_Id,int BorrId){


		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();


			//	String setfkc="set foreign_key_checks=0";	
			//	PreparedStatement pStatement=conn.prepareStatement(setfkc);
			//	pStatement.execute();
			System.out.println("by update return");
			//String selectQuery2 = "UPDATE tbl_book_loans SET dateIn=curdate() where bookId="+book_Id+" and branchId="+lib_Id+" and cardNo="+BorrId+"";		
			Statement st=conn.createStatement();
			//	PreparedStatement pstmt3 = conn.prepareStatement(selectQuery2);
			//	pstmt3.setInt(1, book_Id);
			//	pstmt3.setInt(2, lib_Id);
			//	pstmt3.setInt(3, BorrId);
			System.out.println(st.executeUpdate("UPDATE tbl_book_loans SET dateIn=curdate() where bookId="+book_Id+" and branchId="+lib_Id+" and cardNo="+BorrId));
			System.out.println("after update return");
			//	String setfkc1="set foreign_key_checks=1";	
			//	PreparedStatement pStatement1=conn.prepareStatement(setfkc);
			//	pStatement1.execute();

			System.out.println();
			System.out.println("Returned successfully! ");		

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;

	}
    /**
     * Increment the number of copies of a book 
     * after it is returned.
     * @param book_Id: Book Id
     * @param lib_Id: Library Id
     * @param BorrId: Borrower Id
     * @return True if the Increment was successful 
     * or False otherwise.
     */
	private  boolean return_book_incr(int book_Id,int lib_Id,int BorrId){


		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			Statement stmt2=conn.createStatement();

			ResultSet rs= stmt2.executeQuery("select * from tbl_book_copies where (bookId="+book_Id+" and branchId ="+lib_Id+")");

			if(rs.next()){

				System.out.println("by increment");
				String selectQuery = "UPDATE tbl_book_copies SET noOfCopies=noOfCopies+ 1 where (bookId=? and branchId =? )";		
				PreparedStatement pstmt = conn.prepareStatement(selectQuery);
				pstmt.setInt(1,book_Id);
				pstmt.setInt(2,lib_Id);
				System.out.println(pstmt.executeUpdate()); 
				System.out.println("after increment");
				conn.close();
			}else{
				System.out.println("by insert with no copy");
				PreparedStatement stmt3=conn.prepareStatement("insert into tbl_book_copies values(?,?,?)");

				stmt3.setInt(1, BorrId);
				stmt3.setInt(2, lib_Id);
				stmt3.setInt(3, 1);
				stmt3.executeUpdate();
				System.out.println("after insert with no copy");
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;


	}

    /**
     * Create the connection to the DataBase
     * @return Connection object.
     */
	private  Connection getconnection(){

		try {
			return  DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "edson999");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
