<?php
    
     
    require_once 'db-connect.php';
     $data = "";
    
        
    if(isset($_POST['data'])){
        
        $data = $_POST['data']; 
        
    }
    

    $db = new DbConnect();

    $result = mysqli_query($db->getDb(), 'SELECT * FROM quesansvillage'.$data);
    
    $fp = fopen($data.'.csv', 'w');

    
    while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
        fputcsv($fp, $row);
    }

    fclose($fp);
    
?>
    
  