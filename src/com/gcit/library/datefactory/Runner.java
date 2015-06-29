package com.gcit.library.datefactory;

import java.sql.Date;



public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Timefactory tf=new Timefactory();
		
		System.out.println(tf.gettime().onedaybefore());
		System.out.println(tf.gettime().currentTimeMillis());
		System.out.println(tf.gettime().onedayafter());
		
		System.out.println(tf.gettime().onedaybefore_d());
		System.out.println(tf.gettime().currentTimeMillis_d());
		System.out.println(tf.gettime().onedayafter_d());
		System.out.println(tf.gettime().oneweekafter_d());

	}

}
