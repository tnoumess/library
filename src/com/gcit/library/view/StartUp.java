/**
 * This class is used to launch the application
 */
package com.gcit.library.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 28, 2015
 * @8:50:36 PM
 * @StartUp.java
 */
public class StartUp {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Library s=new Library();
		s.Menu();
		
		
	}

}
