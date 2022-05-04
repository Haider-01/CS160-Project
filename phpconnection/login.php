<?php
//Get Heroku ClearDB connection information
$url = parse_url("mysql://b8eec51bd9fd22:0df54d43@us-cdbr-east-05.cleardb.net/heroku_11a3b0c8a73377e?reconnect=true");

$server = $url["host"];
$username = $url["user"];
$password = $url["pass"];
$db = substr($url["path"], 1);


// Connect to DB
$conn = new mysqli($server, $username, $password, $db);

$username = $_POST['username'];
$result = mysqli_query($conn,"SELECT password FROM broque_users where 
username='$username'");
$row = mysqli_fetch_array($result);
$data = $row[0];

if($data){
  echo $data;
}

mysqli_close($conn);
?>