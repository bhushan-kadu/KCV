<?php
    
    include_once 'db-connect.php';
    
    class User{
        
        private $db;
        
        private $db_table = "users";
        
        public function __construct(){
            $this->db = new DbConnect();
        }
        
        
        public function isLoginExist($username, $password){
            
            $query = "select * from ".$this->db_table." where username = '$username' AND password = '$password' Limit 1";
            
            $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){
                
               
                
                
                return true;
                
            }
            
            mysqli_close($this->db->getDb());
            
            return false;
            
        }
        
        public function isEmailUsernameExist($username, $email){
            
            $query = "select * from ".$this->db_table." where username = '$username' AND email = '$email'";
            
            $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){
               
            
            mysqli_close($this->db->getDb());
                
                return true;
                
            }
            
            
            return false;
            
        }
        
        public function isValidEmail($email){
            return filter_var($email, FILTER_VALIDATE_EMAIL) !== false;
        }
        
        
        
        public function createNewRegisterUser($username, $password, $email, $first_name,
                                             $last_name, $profile_pic, $mobile, $gender, $birthdate, $state,
                                              $district, $taluka, $village, $pincode){
            
            
            $isExisting = $this->isEmailUsernameExist($username, $email);
            
            
            if($isExisting){
                
                $json['success'] = 0;
                $json['message'] = "Error in registering. Probably the username/email already exists1";
                
            }
            
            else{
                
            $isValid = $this->isValidEmail($email);
                
                if($isValid)
                {
                $query = "insert into ".$this->db_table." (username, password, email, first_name, last_name,
                                                        profile_pic, mobile, gender, birthdate, state, district, taluka,
                                                        village, pincode, created_at, updated_at) values
                                                        ('$username', '$password', '$email', '$first_name','$last_name', 
                                                        '$profile_pic', '$mobile', '$gender', '$birthdate', '$state','$district', 
                                                        '$taluka', '$village', '$pincode', NOW(), NOW())";
                
                $inserted = mysqli_query($this->db->getDb(), $query);
                
                
                if($inserted == 1){
                    $currentUserId = $this->get_current_user_id($username, $email);
                    $json['success'] = 1;
                    $json['message'] = "Successfully registered the user";
                    $json['userId'] = $currentUserId;
                    
                }else{
                    
                    $json['success'] = 0;
                    $json['message'] = "Error in registering. Probably the username/email already exists2";
                    
                }
                
                mysqli_close($this->db->getDb());
                }
                else{
                    $json['success'] = 0;
                    $json['message'] = "Error in registering. Email Address is not valid";

                
                }
                
            }
            
            return $json;
            
        }

        public function get_current_user_id($username, $email){

            $query = "select id from ".$this->db_table." where username = '$username' AND email = '$email' Limit 1";
            $result = mysqli_query($this->db->getDb(), $query);
            $row = mysqli_fetch_array($result);
            $name = $row['id'];
           
            return $name;
        }
        
        public function loginUsers($username, $password){
            
            $json = array();
            
            $canUserLogin = $this->isLoginExist($username, $password);
            
            if($canUserLogin){
                $query = "select * from ".$this->db_table." where username = '$username' AND password = '$password' Limit 1";
                $result1 = mysqli_query($this->db->getDb(), $query);
                $row = mysqli_fetch_array($result1);
                $id = $row['id'];
                $user_first_name = $row['first_name'];
                $user_last_name = $row['last_name'];
                $user_email = $row['email'];
                $user_mobile = $row['mobile'];
                $user_type = $row['user_type'];
                mysqli_close($this->db->getDb());
                
                $json['success'] = 1;
                $json['message'] = "Successfully logged in";
                $json['userId'] = $id;
                $json['first_name'] = $user_first_name;
                $json['last_name'] = $user_last_name;
                $json['email'] = $user_email;
                $json['mobile'] = $user_mobile;
                $json['user_type'] = $user_type;
                
                
            }else{
                $json['success'] = 0;
                $json['message'] = "Incorrect details";
            }
            return $json;
        }
    }
    ?>