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
$date = $_GET['date'];
$total = $_GET['total'];


$query = "UPDATE broque_transactions set totalSpent = $total where username = $user and date = $date";

if (mysqli_query($conn, $query)) {
    echo "Transaction updated successfully";
} else {
    echo "Error";
}

mysqli_close($conn);
?>