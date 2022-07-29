<?php

class DataBaseConfig
{
    public $servername;
    public $username;
    public $password;
    public $databasename;

    public function __construct()
    {

        $this->servername = 'togetherwegrow.cfq3tnuayhgb.ca-central-1.rds.amazonaws.com';
        $this->username = 'userjc';
        $this->password = 'capstone';
        $this->databasename = 'togetherwegrow';

    }
}

?>