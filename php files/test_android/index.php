<?php
    
    require_once 'user.php';
    
    $username = "";
    
    $password = "";
    
    $email = "";

    $first_name = "";
    $last_name = "";
    $mobile = "";
    $gender = "";
    $birthdate = "";
    $state = "";
    $district = "";
    $taluka = "";
    $village = "";
    $pincode = "";
    $profile_pic = "";

    
    if(isset($_POST['username'])){
        
        $username = $_POST['username'];
        
    }
    
    if(isset($_POST['password'])){
        
        $password = $_POST['password'];
        
    }
    if(isset($_POST['email'])){
        
        $email = $_POST['email'];
        
    }
    if(isset($_POST['first_name'])){
        
        $first_name = $_POST['first_name'];
        
    }
    if(isset($_POST['last_name'])){
        
        $last_name = $_POST['last_name'];
        
    }
    if(isset($_POST['mobile'])){
        
        $mobile = $_POST['mobile'];
        
    }
    if(isset($_POST['gender'])){
        
        $gender = $_POST['gender'];
        
    }
    if(isset($_POST['birthdate'])){
        
        $birthdate = $_POST['birthdate'];
        
    }
    if(isset($_POST['state'])){
        
        $state = $_POST['state'];
        
    }
    if(isset($_POST['district'])){
        
        $district = $_POST['district'];
        
    }
    if(isset($_POST['taluka'])){
        
        $taluka = $_POST['taluka'];
        
    }
    if(isset($_POST['village'])){
        
        $village = $_POST['village'];
        
    }
    if(isset($_POST['pincode'])){
        
        $pincode = $_POST['pincode'];
        
    }
    if(isset($_POST['profile_pic'])){
        
        $profile_pic = $_POST['profile_pic'];
        
    }
    
    
    
    $userObject = new User();
    
    // Registration
    
    if(!empty($username) && !empty($password) && !empty($email) 
   && !empty($first_name) && !empty($last_name) && !empty($mobile)
    && !empty($gender) && !empty($birthdate) && !empty($state) 
    &&!empty($district) && !empty($taluka)&& !empty($village)&& !empty($pincode)){
        
        $hashed_password = md5($password);
        
        $json_registration = $userObject->createNewRegisterUser($username, $hashed_password, $email, $first_name,
                                                                $last_name, $profile_pic, $mobile, $gender, $birthdate, $state,
                                                                 $district, $taluka, $village, $pincode);
        
        echo json_encode($json_registration);
        
    }
    
    // Login
    
    if(!empty($username) && !empty($password) && empty($email)){
        
        $hashed_password = md5($password);
        
        $json_array = $userObject->loginUsers($username, $hashed_password);
        
        echo json_encode($json_array);
    }
    ?>