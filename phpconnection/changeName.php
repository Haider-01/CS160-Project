<?php
//Get Heroku ClearDB connection information
$url = parse_url("mysql://b8eec51bd9fd22:0df54d43@us-cdbr-east-05.cleardb.net/heroku_11a3b0c8a73377e?reconnect=true");

$server = $url["host"];
$username = $url["user"];
$password = $url["pass"];
$db = substr($url["path"], 1);

// Connect to DB
$conn = new mysqli($server, $username, $password, $db);

$cur_user = $_GET['current_username'];
$new_user = $_GET['new_username'];

$sql = "UPDATE broque_users set username = $new_user where username = $cur_user";

if (mysqli_query($conn, $sql)) {
    echo "Username changed successfully";
} else {
    echo "Error";
}
mysqli_close($conn);
?> 