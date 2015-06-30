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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gcit.library.datefactory.Timefactory;
import com.gcit.library.domain.*;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @12:52:24 PM
 * @Dao.java
 */
public class Dao {
	
	
	public  Timefactory tf=new Timefactory();
	
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
     * @throws SQLException 
     */
	public boolean checkout_book(int book_Id,int lib_Id,int BorrId) throws SQLException{
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
          if(check_Auth_book2(book_Id, lib_Id, BorrId)){
        	  
        	  System.out.println("inside");
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
        	  System.out.println("inside2");
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
				

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				conn.commit();
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
	public boolean check_Auth_book2(int book_Id,int lib_Id,int BorrId){
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
			
			if(rs.next()) 
				return true;
			return false;

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
System.out.println("BookId:"+book_Id+"  branch:"+lib_Id);
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

				stmt3.setInt(1, book_Id);
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

	
	public boolean Insertbook(Book b) throws SQLException{		
		
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("insert into tbl_book (title,pubId) values(?,?)");

		stmt.setString(1,b.getBookTitle() );
		if(b.getPublisherId()==0){
			stmt.setString(2, null);}else{
		    stmt.setString(2, Integer.toString(b.getPublisherId()));
		}
		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println(b.getPublisherId());
			System.out.println("ERROR: Insertion failed");
			System.out.println(e);
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
	} 
	
	public boolean InsertAuthor(Author a) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("insert into tbl_author values(,?)");

		stmt.setString(1, a.getAuthorName());
		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
		
	}
	
	public boolean InsertPublisher(Publisher p) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("insert into tbl_publisher(publisherName,publisherAddress,publisherPhone) values(?,?,?)");

		stmt.setString(1,p.getName() );
		stmt.setString(2, p.getAddress());
		stmt.setString(3,p.getPhone());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
		
	}
	public boolean InsertLibrary(Library l) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("insert into tbl_library_branch values(,?,?)");

		stmt.setString(1, l.getBranchName());
		stmt.setString(2,l.getBranchAddress() );
		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
		
	}
	public boolean InsertBorrowers(Borrower b) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("insert into tbl_borrower values(,?,?,?)");

		stmt.setString(1, b.getName());
		stmt.setString(2, b.getAddress());
		stmt.setString(3, b.getPhone());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
		
	}
	public boolean OverrideLoan(Loans l, java.sql.Date d) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
			PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_book_loans SET dueDate=? where (bookId=? and branchId =? and cardNo=? )");

		stmt.setDate(1, d);
		stmt.setInt(2, l.getBookId());
		stmt.setInt(3,l.getBranchId());
		stmt.setInt(4,l.getBorrId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
		
	}
    public boolean InsertGenre(Genre g) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("insert into tbl_genre (genre_name) values(?)");
		stmt.setString(1,g.getGenreName());		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			System.out.println(e);
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
    	
    }
    public boolean InsertBook_Genre(BookGenres bg) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("insert into tbl_book_genres values(?,?)");

		stmt.setInt(1, bg.getGenreId());
		stmt.setInt(2, bg.getBookId());
		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
    	
    }
    public boolean InsertBook_Author(BookAuthors ba) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("insert into tbl_book_authors values(?,?)");

		stmt.setInt(1,ba.getBookId() );
		stmt.setInt(2, ba.getAuthorId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Insertion successful!");
		return true;
    	
    }
    
    
    // update
    
    public boolean Updatebook(Book b) throws SQLException{		
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_book SET title=?, pubId=? where (bookId=?)");

		stmt.setString(1,b.getBookTitle() );
		stmt.setInt(2,b.getPublisherId() );
		stmt.setInt(3,b.getBookId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
		
	} 
    
    public boolean Updatebook1(Book b) throws SQLException{		
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_book SET title=? where (bookId=?)");

		stmt.setString(1,b.getBookTitle() );
		stmt.setInt(2,b.getBookId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
		
	} 
	
	public boolean UpdateAuthor(Author a) throws SQLException{
		
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_author SET authorName=? where (authorId=?)");

		stmt.setString(1, a.getAuthorName());
		stmt.setInt(2, a.getAuthorId());
		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
		
	}
	
	public boolean UpdatePublisher(Publisher p) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_publisher SET publisherName=?,publisherAddress=?, publisherPhone=? where (publisherId=?)");

		stmt.setString(1,p.getName() );
		stmt.setString(2,p.getAddress() );
		stmt.setString(3, p.getPhone());
		stmt.setInt(4, p.getPublisherId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
		
	}
	public boolean UpdateLibrary(Library l) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_library_branch SET branchName=?, branchAddress=? where ( branchId =? )");

		stmt.setString(1,l.getBranchName() );
		stmt.setString(2, l.getBranchAddress());
		stmt.setInt(3,l.getBranchId() );
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
		
	}
	public boolean UpdateBorrowers(Borrower b) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_borrower SET name=?, address=?, phone=? where (cardNo =? )");

		stmt.setString(1,b.getName());
		stmt.setString(2,b.getAddress());
		stmt.setString(3,b.getPhone());
		stmt.setInt(4, b.getBorrId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
		
	}
	
    public boolean UpdateGenre(Genre g) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_genre SET genre_name=? where (genre_id =? )");

		stmt.setString(1, g.getGenreName());
		stmt.setInt(2,g.getGenreId() );
		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
    }
    public boolean UpdateBook_Genre(BookGenres old,BookGenres newbg) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_book_genres SET genre_id=?,bookId=? where (bookId=? and genre_id =? )");

		stmt.setInt(1, newbg.getGenreId());
		stmt.setInt(2, newbg.getBookId());
		stmt.setInt(3, old.getBookId());
		stmt.setInt(4, old.getGenreId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
    }
    public boolean UpdateBook_Author(BookAuthors oldba,BookAuthors newba) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("UPDATE tbl_book_authors SET bookId=?,authorId=? where (bookId=? and authorId=?)");

		stmt.setInt(1, newba.getBookId());
		stmt.setInt(2, newba.getAuthorId());
		stmt.setInt(3,oldba.getBookId() );
		stmt.setInt(4, oldba.getAuthorId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("Update successful!");
		return true;
    }
    
    //delete
    
    public boolean Deletebook(Book b) throws SQLException{	
    	
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("DELETE FROM tbl_book  where (bookId=?)");

		stmt.setInt(1, b.getBookId());
		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			System.out.println();
			return false;
		}finally{
			conn.close();
		}
		System.out.println("deletion successful!");
		return true;
		
	} 
	
	public boolean DeleteAuthor(Author a) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("DELETE FROM tbl_author  where (authorId =?)");

		stmt.setInt(1,a.getAuthorId() );
		
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("deletion successful!");
		return true;
		
	}
	
	public boolean DeletePublisher(Publisher p) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("DELETE FROM tbl_publisher  where (publisherId =? )");

		stmt.setInt(1,p.getPublisherId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("deletion successful!");
		return true;
		
	}
	public boolean DeleteLibrary(Library l) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("DELETE FROM tbl_library_branch  where (branchId =? )");

		stmt.setInt(1, l.getBranchId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("deletion successful!");
		return true;
		
	}
	public boolean DeleteBorrowers(Borrower b) throws SQLException{
		Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("DELETE FROM tbl_borrower  where (cardNo =? )");

		stmt.setInt(1,b.getBorrId() );
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("deletion successful!");
		return true;
	}
	
    public boolean DeleteGenre(Genre g) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("DELETE FROM tbl_genre  where (genre_id=?)");

		stmt.setInt(1, g.getGenreId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("deletion successful!");
		return true;
    }
    public boolean DeleteBook_Genre(BookGenres bg) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("DELETE FROM tbl_book_genres  where (genre_id=? and bookId =?)");

		stmt.setInt(1, bg.getGenreId());
		stmt.setInt(2, bg.getBookId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("deletion successful!");
		return true;
    }
    public boolean DeleteBook_Author(BookAuthors ba) throws SQLException{
    	Connection conn=null;;
		try {
			conn =getconnection();
		PreparedStatement stmt=conn.prepareStatement("DELETE FROM tbl_book_authors  where (bookId=? and authorId =? )");

		stmt.setInt(1,ba.getBookId());
		stmt.setInt(2,ba.getAuthorId());
		stmt.executeUpdate();
		
		}catch(SQLException e){
			System.out.println("ERROR: An error has occured");
			return false;
		}finally{
			conn.close();
		}
		System.out.println("deletion successful!");
		return true;
    }
    
    //select
    
    
    public Map<Integer,String> list_genre(){

		Map<Integer,String> l=new HashMap<Integer,String>();
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "select * from tbl_genre";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery(); 

			while(rs.next()){
				l.put(rs.getInt("genre_id"), rs.getString("genre_name"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return l;
		}
		
		return l;

	}
    
    public Set<Publisher> list_publisher(){

		Set<Publisher> s=new HashSet<Publisher>();
		Publisher p;
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "select * from tbl_publisher";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery(); 

			while(rs.next()){
				p=new Publisher();
				p.setPublisherId(rs.getInt("publisherId"));
				p.setName(rs.getString("publisherName"));
				p.setAddress(rs.getString("publisherAddress"));
				p.setPhone(rs.getString("publisherPhone"));
				s.add(p);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return s;
		}
		
		return s;

	}

    public Map<Integer,Book> list_book(){

		Map<Integer,Book> m=new HashMap<Integer, Book>();
		Book b;
		Connection conn=null;;
		try {
			conn =getconnection();		
			Statement stmt = conn.createStatement();
			String selectQuery = "select * from tbl_book";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery(); 

			while(rs.next()){
				b=new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setBookTitle(rs.getString("title"));
				if(rs.getString("pubId")==null){
					b.setPublisherId(-1);
				}else{
				b.setPublisherId(rs.getInt("pubId"));	
				}
								
				m.put(b.getBookId(), b);
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return m;
		}
		
		return m;

	}
    
}
