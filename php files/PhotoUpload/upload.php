<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$image = $_POST['image'];
		
		require_once('dbConnect.php');
		
		$sql ="SELECT id FROM users ORDER BY id ASC";
		
		$res = mysqli_query($con,$sql);
		
		$id = 0;
		
		while($row = mysqli_fetch_array($res)){
				$id = $row['id'];
		}
		
		$path = "uploads/$id.png";
		
		$actualpath = "localhost/PhotoUpload/$path";
		
		$sql = "INSERT INTO photos (image) VALUES ('$actualpath')";
		
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));
			echo $path;
		}
		
		mysqli_close($con);
	}else{
		echo "Error";
	}