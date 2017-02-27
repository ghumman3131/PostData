<?php

$json = file_get_contents('php://input');

$decoded_data = json_decode($json,true);

$name = $decoded_data['name'];
$pass = $decoded_data['pass'];

$connection = mysqli_connect('localhost' , 'root' , '');

mysqli_select_db($connection , 'library');

$result = mysqli_query($connection , "insert into user (name , password ) values ('$name' , '$pass' )" );


if($result)
{
	$response['r'] = "done";
	
	echo json_encode($response);
	
}
else {
	
	$response['r'] = "not done";
	
	echo json_encode($response);
}

?>
