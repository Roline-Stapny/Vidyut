<?php
   include 'db_config.php';
   
     $con = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("Opps some thing went wrong"); 
	 
	 $stmt=$con->prepare("select Name,Aadhar_No,Address,Region,Metre_No,District,State,Mobile_No,Password from personal_data;");
	 $stmt->execute();
	 $stmt->bind_result($Name,$Aadhar_No,$Address,$Region,$Metre_No,$District,$State,$Mobile_No,$Password);
	 $products=array();
	 
	 
	 while($stmt->fetch())
	 {
		 $temp=array();
		 $temp['Name']=$Name;
		 $temp['Aadhar_No']=$Aadhar_No;
		 $temp['Address']=$Address;
		 $temp['Region']=$Region;
		 $temp['Metre_No']=$Metre_No;
		 $temp['District']=$District;
		 $temp['State']=$State;
		 $temp['Mobile_No']=$Mobile_No;
		 $temp['Password']=$Password;
		 
		 array_push($products,$temp);
	 }
	 echo json_encode($products);
	 
	 mysqli_close($con);
?>