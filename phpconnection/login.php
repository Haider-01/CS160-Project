<?php
//Get Heroku ClearDB connection information
$url = parse_url("mysql://b8eec51bd9fd22:0df54d43@us-cdbr-east-05.cleardb.net/heroku_11a3b0c8a73377e?reconnect=true");

$server = $url["host"];
$username = $url["user"];
$password = $url["pass"];
$db = substr($url["path"], 1);


// Connect to DB
$conn = new mysqli($server, $username, $password, $db);
// if (!$conn) {
//     echo "Connection failed";
// } else {
//     echo "Connection success";
// }

$username = $_POST['username'];
$password = $_POST['password'];
$result = mysqli_query($conn,"SELECT username, password FROM broque_users where 
username='$username' and password='$password'");
$row = mysqli_fetch_array($result);
$data = $row[0];

if($data){
  echo $data;
}

mysqli_close($conn);
?>