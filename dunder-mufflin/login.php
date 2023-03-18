<?php 

session_start();

	include("connection.php");
	include("functions.php");


	if($_SERVER['REQUEST_METHOD'] == "POST")
	{
		
		$user_name = htmlspecialchars($_POST['user_name'] , ENT_QUOTES ) ; 
		$id =(int) filter_var($_POST["id"] , FILTER_VALIDATE_INT)  ; 
		$password = htmlspecialchars($_POST['password'] , ENT_QUOTES) ;

		if(!empty($user_name) && !empty($password) && !is_numeric($user_name))
		{

			
			$query = "select * from users where user_name = '$user_name' AND user_id = '$id'limit 1";
			$result = mysqli_query($con, $query);

			if($result)
			{
				if($result && mysqli_num_rows($result) > 0)
				{

					$user_data = mysqli_fetch_assoc($result);
					
					if($user_data['password'] === $password)
					{

						$_SESSION['user_id'] = $user_data['user_id'];
						header("Location: index.php");
						die;
					}
				}
			}

			?>
			
			<script>alert("wrong username or ID or password!") location="login.php"</script>
			
			<?php 
			
		}else
		{
			?>
			<script>alert("wrong username or ID or password!") location="login.php" </script>
			<?php 
			
		}
	}
	?>







<html>
<header>
<title>dunder mufflin Login</title>
<link rel="stylesheet" type="text/css" href="forms.css">
</header>

<body>
<div id="box">
		
		<form method="post">
			<div id="form">Login</div>

			<input class="text" type="text" name="user_name"  placeholder="USER NAME"><br><br>

			<input class="text" type="number" name="id" placeholder="ID"> <br><br>
            
			<input class="text" type="password" name="password"  placeholder="PASSWORD"><br><br>

			<input id="button" type="submit" value="Login"><br><br>

			<a href="signup.php">Click to Signup</a><br><br>
			
		</form>
	
</div>

<div id="info">
<p>we have <?php echo get_clients_number($con)?> clients</p>
<p>we have <?php echo get_products_number($con)?> products</p>

</div>
	
</body>
</html>
