#!/usr/bin/php
<?php
 
$fp=file("../software.list.txt");
foreach ($fp as $item) {
    $item=trim($item);
    if (!empty($item)){
        $fp=fopen("../short-desc/".$item,"w");
        fwrite($fp,"<description>\n");
        fclose($fp);
    }
}
 
 
?>
