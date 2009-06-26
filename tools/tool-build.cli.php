#!/usr/bin/php
<?php

/** the software.list.txt is prepare for build program directories, description files and etc.**/


/**initial stupid functions **/

function downloadSetupFile($prgname,$url){
    // make dir
    $cmd="mkdir release/tmp/".$prgname;
    system($cmd);
    // wget setup file and fall back to background mode
    $cmd="wget ".$url." -b -O release/tmp/".$prgname."/setup.exe";
    system($cmd);    
}

// defind category which match the category name

// load category

// build category

// build program list and send signal to download setup file


 
?>