<?php
     

		 
    include 'db_config.php';


    $con = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("Opps some thing went wrong"); 
	
	
	
	$metre_no=$_POST['metre_no'];
	$password=$_POST['password'];
	
//	$metre_no="43";
//	$password="pp";
	
	
	$query="select * from personal_data where Metre_No='$metre_no' and Password='$password'";
	
	$check = mysqli_fetch_array(mysqli_query($con,$query));

	if(isset($check)){

		echo "Data Matched";
	}
	else{
		echo "Invalid Username or Password Please Try Again !";
	}

mysqli_close($con);
	
	
?>