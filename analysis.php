<?php
    include 'db_config.php';
    $con = mysqli_connect($mysql_hostname, $mysql_user, $mysql_password, $mysql_database) or die("Opps some thing went wrong"); 
	
	
	 $stmt=$con->prepare("select region, count(region) as total
         	from complaint
			group by region;");
			
	$stmt->execute();
	$stmt->bind_result($region,$total);
	
	$users=array();
	
	while($stmt->fetch())
	{
		
		$temp=array();
		$temp['region']=$region;
		$temp['total']=$total;
		
		array_push($users,$temp);
	}

	echo json_encode($users);
mysqli_close($con);
	           
	
	
	
?>