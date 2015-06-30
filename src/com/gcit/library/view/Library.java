package com.gcit.library.view;

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
import com.gcit.library.persistence.Dao;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 28, 2015
 * @8:40:50 PM
 * @Start.java
 */
public class Library {
	private Admin admin=new Admin();
	private Dao dao=new Dao();
	
	/**
	 * This is the Main menu of our Application
	 * @throws SQLException 
	 */
	public void Menu() throws SQLException{
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
					exit_Main=false;admin.Menu(); break;
				case '3':
					exit_Main=false; Borr(dao.list_Borrower());break;
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
					exit_Lib1=false; Lib2(dao.list_Libr());break;
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
	 * Action to perform on a given library.
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
					exit_Lib3=false; AddCopies(Idlib,dao.list_Book());break;
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
				dao.Upd_Libr(IdLib, newName, newAddress);
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

		int no=dao.getBookNum(bookId, LibId);
		do{ 


			System.out.println("Existing number of copies: "+no);
			System.out.println("Enter new number of copies");
			Scanner scan4=new Scanner(System.in);	
			try{          

				num = Integer.parseInt(scan4.nextLine().trim());

				if(num>=0){

					if(dao.setBookNum(num, bookId, LibId)){
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
		Map<Integer,String> list=dao.list_Libr();
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
		for(Map.Entry<String, String> map: dao.list_Book_by_lib(idbranch).entrySet()){
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
					{dao.checkout_book((int)id, idbranch,idBorr);
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
		Map<Integer,String> list=dao.list_Libr();
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
		for(Map.Entry<String, String> map: dao.list_Book_out(borrId).entrySet()){
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
					dao.return_book(id, libId, borrId);
					dao.return_book_incr(id, libId, borrId);

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
   
	
}
