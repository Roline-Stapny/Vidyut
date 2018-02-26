<?php
   include 'db_config.php';


    $con = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("Opps some thing went wrong"); 
	
	
	
	$metre_no=$_POST['metre_no'];
	$region=$_POST['region'];
	 $time=date("Y-m-d h:i:sa");
	
	
	
	    /* $query ="insert into complaint (metre_no,region,time) values ('101','shirva',' ') ";
			 insert into complaint (metre_no,region,time) values ('102','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('103','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('104','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('105','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('106','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('107','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('108','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('109','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('110','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('111','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('112','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('113','shirva',' ')
			 insert into complaint (metre_no,region,time) values ('114','shirva',' ')
			  insert into complaint (metre_no,region,time) values ('115','belman',' ')
			   insert into complaint (metre_no,region,time) values ('116','belman',' ')
			  insert into complaint (metre_no,region,time) values ('117','belman',' ')
			   insert into complaint (metre_no,region,time) values ('118','belman',' ')
			   insert into complaint (metre_no,region,time) values ('119','belman',' ')
			   insert into complaint (metre_no,region,time) values ('120','belman',' ')
			   insert into complaint (metre_no,region,time) values ('121','belman',' ')
			   insert into complaint (metre_no,region,time) values ('122','belman',' ')
			   insert into complaint (metre_no,region,time) values ('123','belman',' ')
			   insert into complaint (metre_no,region,time) values ('124','belman',' ')
			   insert into complaint (metre_no,region,time) values ('125','belman',' ')
			   insert into complaint (metre_no,region,time) values ('126','belman',' ')
			   insert into complaint (metre_no,region,time) values ('127','belman',' ')
			   insert into complaint (metre_no,region,time) values ('128','belman',' ') 
			   insert into complaint (metre_no,region,time) values ('129','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('130','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('131','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('132','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('133','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('134','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('135','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('136','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('137','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('138','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('139','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('140','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('141','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('142','nitte',' ')
			   insert into complaint (metre_no,region,time) values ('143','nitte',' ')";*/
	           // $query="update complaint set time='$time'";
	
	
   $query ="insert into complaint (metre_no,region,time) values ('$metre_no','$region','$time')";
  
   
   if(mysqli_query($con,$query)){
 
        echo 'Done';
 
	}
	else{
 
		echo 'Error (complaint is already registered)';
 
		}
		mysqli_close($con);
?>