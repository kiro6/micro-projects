<?php 


$host="127.0.0.1" ; 
$user="root" ; 
$pass="" ; 
$dbname="printa_hub" ;

$con = mysqli_connect($host , $user  , $pass , $dbname) ; 

if(mysqli_connect_errno()){
    echo "failed to connect" ; 
    die("failed to connect") ; 
}

?>