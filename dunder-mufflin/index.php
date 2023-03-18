

<?php 

session_start() ; 

include("connection.php");
include("functions.php");

$user_data = check_login($con) ; 

?>

<html>
<header>
<title>dunder mufflin</title>
<link rel="stylesheet" type="text/css" href="product.css">
<script src="jquery-3.6.1.js"></script>
</header>

<body>
<p>
    
 hello <?php echo $user_data["user_name"] ?> 
<br><br>
<input id="myInput" type="text" placeholder="Search..">
</p>
<br><br>


<div id="products">

<?php   get_products($con) ?>

</div>




<a href="logout.php">LogOut</a><br><br>

<script src="js.js"></script>
</body>
</html>