/**
 * 
 */
package com.gcit.library.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.gcit.library.datefactory.Timefactory;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @12:52:24 PM
 * @Dao.java
 */
public class Dao {
	
	
	private  Timefactory tf=new Timefactory();
	
	 /**
     * Any User:
     * Lists all the library branches
     * @return Map containing the list of libraries.
     */
	public Map<Integer,String> list_Libr(){

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
	public boolean Upd_Libr(int IdLib,String name,String address){

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

	public Map<Integer,String> list_Book(){

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
	public int getBookNum( int id,int LibId){


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
	public boolean setBookNum( int no,int id,int LibId){


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
	public Map<Integer,String> list_Borrower(){

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
	public Map<String,String> list_Book_by_lib(int lib_Id){

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
	public boolean checkout_book(int book_Id,int lib_Id,int BorrId){
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
          if(check_Auth_book(book_Id, lib_Id, BorrId)){
        	  String Query = "UPDATE tbl_book_loans SET dateOut=curdate(), dueDate=?,dateIn=? where (bookId=? and branchId =? and cardNo=? )";		
				PreparedStatement pstmt1 = conn.prepareStatement(Query);
				pstmt1.setDate(1, tf.gettime().oneweekafter_d());
				pstmt1.setDate(2,null);
				pstmt1.setInt(3, book_Id);
				pstmt1.setInt(4, lib_Id);
				pstmt1.setInt(5, BorrId);
				pstmt1.executeUpdate();
        	  
				System.out.println();
				System.out.println("Update successful! ");	
				System.out.println("Please return book by: "+tf.gettime().oneweekafter_d());
          }else{
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

          }
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
	public boolean check_Auth_book(int book_Id,int lib_Id,int BorrId){
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
	public Map<String,String> list_Book_out(int borr_id){

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

	public boolean return_book(int book_Id,int lib_Id,int BorrId){


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
	public boolean return_book_incr(int book_Id,int lib_Id,int BorrId){


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
	public Connection getconnection(){

		try {
			return  DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "edson999");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


}
