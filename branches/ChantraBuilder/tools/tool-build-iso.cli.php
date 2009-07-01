#!/usr/bin/php
<?php

$cwdir = getcwd();
$cwdir = substr($cwdir,0,(strlen($cwdir))-5);

$version=getResName();

/** Initial Dir **/
$revpath="../release/rev-".$version;
$despath="../release/rev-".$version."/html/en/";

// copy disc dir
// mkisofs -r -J -l -d -allow-multidot -allow-leading-dots -no-bak -V "Chantra Dev" -o chantra-releasename.iso rev-releasename/

$cmd="mkisofs -r -J -l -d -allow-multidot -allow-leading-dots -no-bak -V \"Chantra\" -o ../release/chantra-".$version.".iso ../release/rev-".$version."/";
system($cmd);

?>
