#!/usr/bin/php
<?php
 mkdir("tmp",0777);
$fp=file("src.list.txt");
foreach ($fp as $item) {
    $item=trim($item);
    if (!empty($item)){
        mkdir("tmp/".$item,0777);
    }
}
 
 
?>
