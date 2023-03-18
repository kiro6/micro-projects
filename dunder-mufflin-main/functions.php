<?php 

function check_login($con){

if(isset($_SESSION["user_id"])){

$user_id =  $_SESSION["user_id"] ; 
$query = "Select * from users where user_id = '$user_id' limit 1" ; 

$result = mysqli_query($con , $query  ) ; 

if ($result && mysqli_num_rows($result) > 0 ) {
    $user_data = mysqli_fetch_assoc($result) ; 
    return $user_data ; 
}

}else{
    header("Location: login.php") ; 
    die() ; 
}


};

function get_products($con){

	if(isset($_SESSION["user_id"])){
	
		$query = "SELECT * FROM products WHERE 1" ; 

		$result = mysqli_query($con , $query); 
		
		echo "<table border='1'><tr><th>product_id</th><th>product_name</th><th>price</th><th>image</th></tr> <tbody id='myTable'>";
		

		if($result && mysqli_num_rows($result) > 0){
			while($row = mysqli_fetch_assoc($result))
			{
			echo "<tr>";
			echo "<td>" . $row['product_id'] . "</td>";
			echo "<td>" . $row['product_name'] . "</td>";
			echo "<td>" . $row['price'] . "</td>";
			echo "<td>" ."<img src=".$row['image_location'].">" . "</td>";
			
			echo "</tr>";
			}
		 echo "</tbody >"  ; 
		  echo "</table>";
		}else{
			echo "error" ; 
		}

		
		
		
	
	};

}


function get_products_number($con){

	
		$query = "SELECT * FROM products WHERE 1" ; 

		$result = mysqli_query($con , $query); 
	
		$products = mysqli_num_rows($result) ; 		
		
		return $products ; 
		
	
	};



function get_clients_number($con){

	
	$query = "SELECT * FROM users WHERE 1" ; 

	$result = mysqli_query($con , $query); 

	$clients = mysqli_num_rows($result) ; 		
	
	return $clients ; 
	

};





function random_num($length)
{

	$text = "";
	if($length < 5)
	{
		$length = 5;
	}



	for ($i=0; $i < $length; $i++) { 
		# code...

		$text .= rand(0,9);
	}

	return $text;
}




?>