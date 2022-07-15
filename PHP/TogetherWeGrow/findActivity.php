<?php
require "DataBase.php";
$db = new DataBase();

    if ($db->dbConnect()) {
        $db->findActivity("activities", $_REQUEST['energy'], $_REQUEST['fresh'], $_REQUEST['age'], $_REQUEST['dislike']);       
    } else echo "Error: Database connection";

?>
