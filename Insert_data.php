<?php

include 'db_config.php';


 $con = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("Opps some thing went wrong"); 
 
       $name=$_POST['name'];
		$password=$_POST['password'];
		
		$district=$_POST['district'];
		$region=$_POST['region'];
		$state=$_POST['state'];
		
		$address=$_POST['address'];
		$aadhar=$_POST['aadhar'];
		$mobile=$_POST['mobile'];
		$metre=$_POST['metre'];
 
$Sql_Query = "insert into personal_data (Name,Aadhar_No,Address,Region,Metre_No,District,State,Mobile_No,Password) values('$name','$aadhar','$address','$region','$metre','$district','$state','$mobile','$password')";

 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'successful';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
?>