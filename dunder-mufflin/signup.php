


<?php 

session_start();

	include("connection.php");
	include("functions.php");

	if($_SERVER['REQUEST_METHOD'] == "POST")
	{

		$user_name = htmlspecialchars($_POST['user_name'] , ENT_QUOTES ) ; 
		
		$email = htmlspecialchars($_POST['email'] , ENT_QUOTES ) ; 
	
		$password = htmlspecialchars($_POST['password'] , ENT_QUOTES) ;
	
		
		if(!empty($user_name) && !empty($password) && !is_numeric($user_name) && filter_var($email , FILTER_VALIDATE_EMAIL , FILTER_NULL_ON_FAILURE) !== null  )
		{
			
		
			$user_id = random_num(14);
			$query = "insert into users (user_id,user_name,email,password) values ('$user_id','$user_name', '$email', '$password') ";
			
			mysqli_query($con, $query);
			?> 
			<script>alert("your id : "+<?php echo $user_id ; ?>) ;location="login.php"</script>
			
		<?php
		
			die;
		}else
		{ ?>
			<script>alert("Please enter valid information!") location="signup.php"</script>
				<?php 
		}
	}



?>




<html>
<header>
<title>dunder mufflin SignUp</title>
<link rel="stylesheet" type="text/css" href="forms.css">
</header>

<body>
<div id="box">
		
		<form method="post">
			<div id="form">SignUp</div>

			<input class="text" type="text" name="user_name" placeholder="USER NAME"><br><br>

			<input class="text" type="email" name="email"  placeholder="EMAIL"><br><br>
            
			<input class="text" type="password" name="password"  placeholder="PASSWORD" ><br><br>

			<input id="button" type="submit" value="signup"><br><br>

			<a href="login.php">Click to login</a><br><br>
		</form>
	</div>

	<div id="info">
<p>we have <?php echo get_clients_number($con)?> clients</p>
<p>we have <?php echo get_products_number($con)?> products</p>

</div>
</body>
</html>
