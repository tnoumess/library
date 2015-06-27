package datefactory;

import java.sql.Date;

public interface TimeSource {

	  /** Return the system time. */  
	  long currentTimeMillis();
	  long onedaybefore();
	  long onedayafter();
	  
	  Date currentTimeMillis_d();
	  Date onedaybefore_d();
	  Date onedayafter_d();

	} 