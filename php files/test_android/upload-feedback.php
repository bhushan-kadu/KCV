<?php
    
     
    require_once 'db-connect.php';
    
    $data = "";
    
        
    if(isset($_POST['data'])){
        
        $data = $_POST['data'];
        
    }

    $db = new DbConnect();
    
    if(!empty($data)){

        $json = json_decode($data, true);
        $userId = $json["userId"];
        $feedback = $json["feedback"];

        $sql1 = "insert into user_feedback( UserID, feedback) values ( '$userId', '$feedback')";
        $result = mysqli_query($db->getDb(), $sql1);
          if($result == 1){
              echo "inserted";
          }else{
                echo "failed";    
          }
           
    }
    
    ?>
  