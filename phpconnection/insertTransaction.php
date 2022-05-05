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


$sql = "INSERT INTO broque_transactions (username, date, totalspent) VALUES ($user, $date, $total)";
if (mysqli_query($conn, $sql)) {
      echo "New record created successfully";
} else {
      echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}


mysqli_close($conn);
?>