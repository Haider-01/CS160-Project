<?php
//Get Heroku ClearDB connection information
$url = parse_url("mysql://b8eec51bd9fd22:0df54d43@us-cdbr-east-05.cleardb.net/heroku_11a3b0c8a73377e?reconnect=true");

$server = $url["host"];
$username = $url["user"];
$password = $url["pass"];
$db = substr($url["path"], 1);

// Connect to DB
$conn = new mysqli($server, $username, $password, $db);

$username = $_GET['user'];

$query = mysqli_query($conn,"SELECT username, total, bill, food, entertainment, other 
FROM broque_budgets WHERE username = $user");


while($row = mysqli_fetch_array($query, MYSQLI_NUM) ){
    echo $row[0].'<br>';
    echo $row[1].'<br>';
    echo $row[2].'<br>';
    echo $row[3].'<br>';
    echo $row[4].'<br>';
    echo $row[5].'<br>';
}

$query->close();
mysqli_close($conn);
?>
