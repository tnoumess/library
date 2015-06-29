package com.gcit.library.datefactory;

import java.sql.Date;

/**
 * @author Thierry Edson Noumessi
 *
 * @date Jun 29, 2015
 * @2:27:16 PM
 * @TimeSrc.java
 */
public final class TimeSrc implements TimeSource {

	 /**The actual time.*/
	  @Override public long currentTimeMillis() {
	    return System.currentTimeMillis();
	  }
	  
	  private static final long ONE_DAY = 24*60*60*1000;

	@Override
	 /** One day before of the actual time.*/
	public long onedaybefore() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis() - ONE_DAY;
	}

	@Override
	 /** One day in advance of the actual time.*/
	public long onedayafter() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis() + ONE_DAY;
	}

	@Override
	public Date currentTimeMillis_d() {
		// TODO Auto-generated method stub
		return new Date(currentTimeMillis());
	}

	@Override
	public Date onedaybefore_d() {
		// TODO Auto-generated method stub
		return new Date(currentTimeMillis()-ONE_DAY);
	}

	@Override
	public Date onedayafter_d() {
		// TODO Auto-generated method stub
		return new Date(currentTimeMillis()+ONE_DAY);
	}
	
	/*
	public TimeSrc() {
		// TODO Auto-generated constructor stub
		System.out.println("in");
	}*/

	@Override
	public Date oneweekafter_d() {
		// TODO Auto-generated method stub
		return new Date(currentTimeMillis()+ONE_DAY*6);
	}

	} 
