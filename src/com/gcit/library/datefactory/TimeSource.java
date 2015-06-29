package com.gcit.library.datefactory;

import java.sql.Date;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @2:27:39 PM
 * @TimeSource.java
 */
public interface TimeSource {

	  /** Return the system time. */  
	  long currentTimeMillis();
	  long onedaybefore();
	  long onedayafter();
	  
	  Date currentTimeMillis_d();
	  Date onedaybefore_d();
	  Date onedayafter_d();
	  Date oneweekafter_d();

	} 