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
        $surveyName = $json["surveyName"];

        $sql = "select QuestionID from ". $surveyName;

        $result = mysqli_query($db->getDb(), $sql);
        $quesIdSet = array();
        
        while($row = $result->fetch_assoc()) {
            // echo "id: " . $row["QuestionID"]. " - Name: " . $row["SurveyID"]. " " . $row["Question"]. "<br>";
            array_push($quesIdSet, $row["QuestionID"]);
        }
        var_dump($quesIdSet);
        $counter = count($quesIdSet);
       

        for( $i = 0; $i< $counter; $i++ ){
            $quesId = $quesIdSet[$i];
            $answer = $json[($i).""];
            echo($quesId." ". $userId. " ". $answer. "\n");
            $sql1 = "insert into answers(QuestionId, UserID, Answer) values ('$quesId', '$userId', '$answer')";
            $result = mysqli_query($db->getDb(), $sql1);
              if($result == 1){
                  echo "inserted";
              }else{
                    echo "failed";    
              }

        }

        
        
           
    }
    
  