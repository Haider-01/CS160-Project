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
$category = $_GET['category'];
$amount = $_GET['amount'];

if(strcmp($category, "\"bills\"") == 0){
    $query = "UPDATE broque_expenses set bill_expenses = $amount where username = $user";
} else if(strcmp($category, "\"food\"") == 0){
    $query = "UPDATE broque_expenses set food_expenses = $amount where username = $user";
} else if(strcmp($category, "\"entertainment\"") == 0){
    $query = "UPDATE broque_expenses set entertainment_expenses = $amount where username = $user";
} else if(strcmp($category, "\"other\"") == 0){
    $query = "UPDATE broque_expenses set other_expenses = $amount where username = $user";
}

if (mysqli_query($conn, $query)) {
    echo "Expense updated successfully";
} else {
    echo "Error";
}

mysqli_close($conn);
?>



