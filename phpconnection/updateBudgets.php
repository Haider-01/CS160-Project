<?php
//Get Heroku ClearDB connection information
$url = parse_url("mysql://b8eec51bd9fd22:0df54d43@us-cdbr-east-05.cleardb.net/heroku_11a3b0c8a73377e?reconnect=true");

$server = $url["host"];
$username = $url["user"];
$password = $url["pass"];
$db = substr($url["path"], 1);


// Connect to DB
$conn = new mysqli($server, $username, $password, $db);

$category = $_GET['category'];
$budget = $_GET['budget'];
$user = $_GET['username'];

if(strcmp($category, "\"bill\"") == 0){
    $query = "UPDATE broque_budgets set bill = $budget where username = $user";
} else if(strcmp($category, "\"food\"") == 0){
    $query = "UPDATE broque_budgets set food = $budget where username = $user";
} else if(strcmp($category, "\"entertainment\"") == 0){
    $query = "UPDATE broque_budgets set entertainment = $budget where username = $user";
} else if(strcmp($category, "\"other\"") == 0){
    $query = "UPDATE broque_budgets set other = $budget where username = $user";
}

if (mysqli_query($conn, $query)) {
    echo "Category updated successfully";
} else {
    echo "Error";
}

mysqli_close($conn);
?>