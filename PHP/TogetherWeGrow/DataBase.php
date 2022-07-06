<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            if ($dbusername == $username && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }
/*this function convert input string to double so that can be used to compare in table
* and convert the result to json format
*/ 
    function findActivity($table, $energy, $fresh, $age){
        $ansactivit = array();
        $energy = $this->prepareData($energy);
        $fresh = $this->prepareData($fresh);
        $energy = doubleval($energy);
        $fresh = doubleval($fresh);
        $this->sql = "select * from " . $table . " where energyReq <= '" . $energy . "' and freshReq <= '" . $fresh . "' and ageLw <= '" . $age . "' and ageUp >= '" . $age . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if($result){
            header("Content-Type: JSON");
            $i = 0;
            while($row = mysqli_fetch_assoc($result)){
                $ansactivit[$i]['activity'] = $row['activity'];
                $ansactivit[$i]['energy'] = $row['energyReq'];
                $ansactivit[$i]['fresh'] = $row['freshReq'];                
                $i++;
            }
            echo json_encode($ansactivit);
        }   
    }

    function signUp($table, $email, $username, $password)
    {
        
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (email, username, password) VALUES ('" . $email . "','" . $username . "','" . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function input($table, $username, $worktype, $workload, $freshnessafterwork, $activityhour1, $activityhour2, $activityhour3, $childage, $mostpreferred, $secondpreferred, $thirdpreferred, $dislike)
    {
        
        $username = $this->prepareData($username);
        $worktype = $this->prepareData($worktype);
        $workload = $this->prepareData($workload);        
        $freshnessafterwork = $this->prepareData($freshnessafterwork);
        $activityhour1 = $this->prepareData($activityhour1);
        $activityhour2 = $this->prepareData($activityhour2);
        $activityhour3 = $this->prepareData($activityhour3);
        $childage = $this->prepareData($childage);
        $mostpreferred = $this->prepareData($mostpreferred);
        $secondpreferred = $this->prepareData($secondpreferred);
        $thirdpreferred = $this->prepareData($thirdpreferred);
        $dislike = $this->prepareData($dislike);
        
        $this->sql =
            "INSERT INTO " . $table . " (username, worktype, workload, freshnessafterwork, activityhour1, activityhour2, activityhour3,
            childage, mostpreferred, secondpreferred, thirdpreferred, dislike) VALUES ('" . $username . "','" . $worktype . "','" . $workload . "','" . $freshnessafterwork . "','" . $activityhour1 . "','" . $activityhour2 . "'
            ,'" . $activityhour3 . "','" . $childage . "','" . $mostpreferred . "','" . $secondpreferred . "','" . $thirdpreferred . "','" . $dislike . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>
