<?php
//Get Heroku ClearDB connection information
$url = parse_url("mysql://b8eec51bd9fd22:0df54d43@us-cdbr-east-05.cleardb.net/heroku_11a3b0c8a73377e?reconnect=true");

$server = $url["host"];
$username = $url["user"];
$password = $url["pass"];
$db = substr($url["path"], 1);

// Connect to DB
$conn = new mysqli($server, $username, $password, $db);

$user = $_GET['username'];
$pass = $_GET['password'];

$sql = "UPDATE broque_users set password = $pass where username = $user";

if (mysqli_query($conn, $sql)) {
    echo "Password changed successfully";
} else {
    echo "Error";
}
mysqli_close($conn);
?>