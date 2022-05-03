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

$full = $_GET['fullname'];
$user = $_GET['username'];
$pass = $_GET['password'];
$phone = $_GET['phonenumber'];

$sql = "INSERT INTO broque_users (fullname, username, password, phonenumber) VALUES ($full, $user, $pass, $phone)";
if (mysqli_query($conn, $sql)) {
      echo "New record created successfully";
} else {
      //echo "Error: " . $sql . "<br>" . mysqli_error($conn);
      echo "Error";
}
mysqli_close($conn);
?>