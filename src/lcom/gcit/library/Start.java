package lcom.gcit.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import datefactory.Timefactory;

public class Start {
	public static Timefactory tf=new Timefactory();
	public static void Main(){
		boolean exit_Main=false;
		do{
		System.out.println("1) Librarian");
		//System.out.println();
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
	public static void Lib1(){
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
	public static void Lib2(Map<Integer,String> m){//common to all libraries
		boolean exit_Lib2=false;
//		do{
//		System.out.println("Enter Branch to manage");
//		System.out.println("1) University Library, Boston");
//		System.out.println("2) State Library, New York");
//		System.out.println("3) Federal Library, Washington DC");
//		System.out.println("4) County Library, McLean VA");
//		System.out.println("5) Return");
//		Scanner scan2=new Scanner(System.in);
//		switch(scan2.nextLine().toString().trim().charAt(0)){
//			
//		case '1':
//			exit_Lib2=false; Lib3();break;
//		case '2':
//			exit_Lib2=false;break;
//		case '3':
//			exit_Lib2=false;break;
//		case '4':
//			exit_Lib2=false;break;
//		case '5':
//			exit_Lib2=true;break;
//		default :
//			exit_Lib2=false;break;		
//		}
//		}while(!exit_Lib2);
		
		int id;
		System.out.println("\nBranch ID | Branch Name");
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, String> map: m.entrySet()){
			
			System.out.println(map.getKey()+"\t    "+map.getValue());
		}
		do{
			System.out.println("Enter branch Id you want to manage:");
			//for loop to list the branch
			Scanner scan4=new Scanner(System.in);	
			try{          
				
				id = Integer.parseInt(scan4.nextLine().trim());
				
				if(m.containsKey(id))
				{Lib3(id);
				exit_Lib2=true;
				}
				
				//if id within the range call  Lib3(); and exit_Lib2=true
				
				
				//if not in range exit_Lib2=false
				
			}catch(Exception e){				
				System.out.println("INFO:Unknown branch!");				
				
			}
			
			
		}while(!exit_Lib2);
	}
	public static void Lib3(int Idlib){//common to all libraries
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
	public static void UpdLib(int IdLib){//common to all libraries
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
		
		
//		System.out.println("q) Returnn");
//		String option=(new Scanner(System.in).nextLine());
//		if(option.length()>0){
//			switch(option.charAt(0)){
//			
//		case 'q':
//			exit_Uplib=true;break;
//			
//		default :
//			exit_Uplib=false;break;		
//		}}
		}while(!exit_Uplib);
	}
	
	
	public static void AddCopies(int libId,Map<Integer,String> m){//common to all libraries
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
			//for loop to list the book
			Scanner scan4=new Scanner(System.in);	
			try{          
				
				id = Integer.parseInt(scan4.nextLine().trim());
				
				//if id within the range call  add()
				if(m.containsKey(id))
				{
				add(libId,id);
				exit_Add=true;}
			}catch(Exception e){				
				System.out.println("INFO:Should be an integer!");				
				 
			}
			
			
		}while(!exit_Add);
		
	}
	
	public static void add(int LibId,int bookId){
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
				
				//if good update db
				
				//if not?  do nothing
			}catch(Exception e){				
				System.out.println("INFO: Invalid number!");				
				 
			}
			
			
		}while(!exit_add);
	}
	
	public static void Borr(Map<Integer,String> m){
		boolean exit_Borr=false;
		do{
		System.out.println("Enter your Card Id:");
		Scanner scan2=new Scanner(System.in);
		int num;
		
		try{          
			
			num = Integer.parseInt(scan2.nextLine().trim());
			//check for existing Id
			   //System.out.println("INFO:Unknown id!"); iff bad
			//else  call borr1()
			if(m.containsKey(num))
			{
			
			Borr1(num);
			exit_Borr=true;}else{
				System.out.println("INFO:Unknown Card Id!");				
				
			}
			
		}catch(Exception e){				
			System.out.println("INFO:Unknown Card id!");				
			
		}
		System.out.println("q) Returnn");
		String option=(new Scanner(System.in).nextLine());
		if(option.length()>0){
			switch(option.charAt(0)){			
		case 'q':
			exit_Borr=true;break;
			
		default :
			exit_Borr=false;break;		
		}}
		}while(!exit_Borr);
		//System.out.println("You logged out");
	}
	public static void Borr1(int idBorr){//common to all libraries
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
	
	public static void CheckOut(int idBorr){//common to all libraries
		/**
		 * should create for loop to list libraries.
		 */
		boolean exit_Checkout=false;
//		do{
//			System.out.println("Pick the branch you want to check out from");
//			System.out.println("1) University Library, Boston");
//			System.out.println("2) State Library, New York");
//			System.out.println("3) Federal Library, Washington DC");
//			System.out.println("4) County Library, McLean VA");
//			System.out.println("5) Return");
//		Scanner scan2=new Scanner(System.in);
//		switch(scan2.nextLine().toString().trim().charAt(0)){
//			
//		case '1':
//			exit_Checkout=false;Checkout1(); break;
//		case '2':			
//			exit_Checkout=false;break;
//		case '3':			
//			exit_Checkout=false;break;
//		case '4':			
//			exit_Checkout=false;break;
//		case '5':			
//			exit_Checkout=true;break;
//		default :			
//			exit_Checkout=false;break;		
//		}
//		}while(!exit_Checkout);
		
		int id;
		System.out.println("\nBranch ID | Branch Name");
		System.out.println("---------------------------------------");
		Map<Integer,String> list=list_Libr();
		for(Map.Entry<Integer, String> map: list.entrySet()){
			
			System.out.println(map.getKey()+"\t    "+map.getValue());
		}
		do{
			System.out.println("Pick the branch you want to check out from:");
			//for loop to list the branch
			Scanner scan4=new Scanner(System.in);	
			try{          
				
				id = Integer.parseInt(scan4.nextLine().trim());
				//if id within the range call  Checkout1(); and exit_Checkout=true
				if(list.containsKey(id))
				{Checkout1(idBorr,id);
				exit_Checkout=true;
				}else{
					System.out.println("INFO:Unknown branch!");
				}
				
				//if not in range exit_Checkout=false
				
			}catch(Exception e){				
				System.out.println("INFO:Unknown branch!");				
				
			}
			
			
		}while(!exit_Checkout);
	}
	
	public static void Checkout1(int idBorr, int idbranch){//common to all libraries
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
			System.out.println(data[0]+"\t  "+data[1]+"\t  "+map.getValue());
		}
		do{
			System.out.println("Pick the Book you want to check out:");
			//for loop to list the book
			Scanner scan4=new Scanner(System.in);	
			try{          
				
				id = Integer.parseInt(scan4.nextLine().trim());
				good=true;
				if(s.contains(id))
				{checkout_book((int)id, idbranch,idBorr);
				exit_Checkout1=true;
				//System.out.println("INFO:Procedding to checkout!");
				}else{
					System.out.println("No book found with the given Id!");
				}		
								
			}catch(Exception e){				
				System.out.println("INFO:No book found with the given Id!");				
				good=false; 
			}
			
			
		}while(!exit_Checkout1);
		
	}
	
	public static void Return(int idBorr){//common to all libraries
		/**
		 * should create for loop to list libraries.
		 */
		boolean exit_Return=false;
//		do{
//			System.out.println("Pick the branch you want to return to");
//			System.out.println("1) University Library, Boston");
//			System.out.println("2) State Library, New York");
//			System.out.println("3) Federal Library, Washington DC");
//			System.out.println("4) County Library, McLean VA");
//			System.out.println("5) Return");
//		Scanner scan2=new Scanner(System.in);
//		switch(scan2.nextLine().toString().trim().charAt(0)){
//			
//		case '1':
//			exit_Return=false;Return1(); break;
//		case '2':			
//			exit_Return=false;break;
//		case '3':			
//			exit_Return=false;break;
//		case '4':			
//			exit_Return=false;break;
//		case '5':			
//			exit_Return=true;break;
//		default :			
//			exit_Return=false;break;		
//		}
//		}while(!exit_Return);
		
		int id;
		do{
			System.out.println("Pick the branch you want to return to:");
			//for loop to list the branch
			Scanner scan4=new Scanner(System.in);	
			try{          
				
				id = Integer.parseInt(scan4.nextLine().trim());
				
				//if id within the range call  Return1(); and exit_Checkout=true
				Return1();
				//if not in range exit_Checkout=false
				exit_Return=true;
			}catch(Exception e){				
				System.out.println("INFO:Unknown branch!");				
				
			}
			
			
		}while(!exit_Return);
	}
	
	public static void Return1(){//common to all libraries
		boolean exit_Return1=false;
		boolean good=false;
		int id;
		do{
			System.out.println("Pick the Book you want to return:");
			//for loop to list the book
			Scanner scan4=new Scanner(System.in);	
			try{          
				
				id = Integer.parseInt(scan4.nextLine().trim());
				good=true;
				//if not within range  continue
				//if id within the range sql_return()		exit_Return1=true;		
				
				System.out.println("INFO:Updated successfuly!");
				exit_Return1=true;
			}catch(Exception e){				
				System.out.println("INFO:No book found!");				
				good=false; 
			}
			
			
		}while(!exit_Return1);
		
	}
	
	public static Map<Integer,String> list_Libr(){
		
		Map<Integer,String> l=new HashMap<Integer,String>();
		Connection conn=null;;
		try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");		
		Statement stmt = conn.createStatement();
		String selectQuery = "select * from tbl_library_branch";
		
		PreparedStatement pstmt = conn.prepareStatement(selectQuery);
		ResultSet rs = pstmt.executeQuery(); 
				//stmt.executeQuery(selectQuery);
		while(rs.next()){
			l.put(rs.getInt("branchId"), rs.getString("branchName"));
			}
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(l);
		return l;
		
	}
	
public static boolean Upd_Libr(int IdLib,String name,String address){
		
		Map<Integer,String> l=new HashMap<Integer,String>();
		Connection conn=null;;
		try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");		
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
		System.out.print(l);
		return true;
		
	}

public static Map<Integer,String> list_Book(){
	
	Map<Integer,String> l=new HashMap<Integer,String>();
	Connection conn=null;;
	try {
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");		
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
	System.out.print("here:"+l.toString());
	return l;
	
}

public static int getBookNum( int id,int LibId){
	
	
	Connection conn=null;;
	try {
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");		
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

public static boolean setBookNum( int no,int id,int LibId){
	
	
	Connection conn=null;;
	try {
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");		
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

public static Map<Integer,String> list_Borrower(){
	
	Map<Integer,String> l=new HashMap<Integer,String>();
	Connection conn=null;;
	try {
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");		
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
	//System.out.print("here:"+l.toString());
	return l;
	
}


public static Map<String,String> list_Book_by_lib(int lib_Id){
	
	Map<String,String> l=new HashMap<String,String>();
	Connection conn=null;;
	try {
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");		
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
	//System.out.print("here:"+l.toString());
	return l;
	
}


public static boolean checkout_book(int book_Id,int lib_Id,int BorrId){
	
	Map<Integer,String> l=new HashMap<Integer,String>();
	Connection conn=null;;
	try {
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "pwd");		
	Statement stmt = conn.createStatement();
	//conn.setAutoCommit(false);
	String selectQuery = "UPDATE tbl_book_copies SET noOfCopies=noOfCopies-1 where (bookId=? and branchId =? )";		
	PreparedStatement pstmt = conn.prepareStatement(selectQuery);
	pstmt.setInt(1,book_Id);
	pstmt.setInt(2,lib_Id);
	pstmt.executeUpdate(); 
	System.out.println(selectQuery);
//	String setfkc="set foreign_key_checks=0";	
//	PreparedStatement pStatement=conn.prepareStatement(setfkc);
//	pStatement.execute();
	String selectQuery2 = "insert into tbl_book_loans values(bookId=?,branchId=?,cardNo=?,curdate(),?,null)";		
	PreparedStatement pstmt2 = conn.prepareStatement(selectQuery2);
	pstmt2.setInt(1,book_Id);
	pstmt2.setInt(2,lib_Id);
	pstmt2.setInt(3,BorrId);
	pstmt2.setDate(4,tf.gettime().onedayafter_d());
	pstmt2.executeUpdate(); 
//	String setfkc1="set foreign_key_checks=1";	
//	PreparedStatement pStatement1=conn.prepareStatement(setfkc);
//	pStatement.execute();
	
	
	
			System.out.println("Update successful:\n Please return by: "+tf.gettime().onedayafter_d());	
	conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	System.out.print(l);
	return true;
	
}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean exit_Main=false;
		boolean exit_Lib1=false;
		boolean exit_Lib2=false;
		boolean exit_Lib3=false;
		boolean exit_Op1=false;
		boolean exit=false;
		
		
			Main();
		
		
		

	}

}
