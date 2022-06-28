<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['worktype']) && isset($_POST['workload']) && isset($_POST['freshnessafterwork']) && isset($_POST['activityhour1'])
&& isset($_POST['activityhour2']) && isset($_POST['activityhour3']) && isset($_POST['childage']) && isset($_POST['mostpreferred']) && isset($_POST['secondpreferred'])
&& isset($_POST['thirdpreferred']) && isset($_POST['dislike'])) {
    if ($db->dbConnect()) {
        if ($db->input("userinput", $_POST['username'], $_POST['worktype'], $_POST['workload'], $_POST['freshnessafterwork'],
        $_POST['activityhour1'], $_POST['activityhour2'], $_POST['activityhour3'], $_POST['childage'], $_POST['mostpreferred'], 
        $_POST['secondpreferred'], $_POST['thirdpreferred'], $_POST['dislike'])) {
            echo "Your input has been saved";
        } else echo "Failed to save your input";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
