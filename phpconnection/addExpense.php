<?php
//Get Heroku ClearDB connection information
$url = parse_url("mysql://b8eec51bd9fd22:0df54d43@us-cdbr-east-05.cleardb.net/heroku_11a3b0c8a73377e?reconnect=true");

$server = $url["host"];
$username = $url["user"];
$password = $url["pass"];
$db = substr($url["path"], 1);


// Connect to DB
$conn = new mysqli($server, $username, $password, $db);


$total = $_GET['total'];
$bill = $_GET['bill'];
$food = $_GET['food'];
$entertainment = $_GET['entertainment'];
$other = $_GET['other'];

$sql = "INSERT INTO broque_budgets (total, bill, food, entertainment, other) VALUES ($total, $bill, $food, $entertainment, $other)";
if (mysqli_query($conn, $sql)) {
      echo "Expenses Added Succesfully";
} else {
      echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>