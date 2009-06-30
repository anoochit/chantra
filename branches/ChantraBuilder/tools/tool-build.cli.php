#!/usr/bin/php
<?php

/** the software.list.txt is prepare for build program directories, description files and etc.**/

$version="4.1";

$cwdir = getcwd();
$cwdir = substr($cwdir,0,(strlen($cwdir))-5);

/** Initial Dir **/
$revpath="../release/rev-".$version;
$despath="../release/rev-".$version."/html/en/";
// create dir in release
mkdir($revpath,0777,true);
mkdir($despath,0777,true);

/** stupid functions **/

function downloadSetupFile($prgname,$url){
    global $cwdir,$revpath;
    $cmd="mkdir ".$revpath."/html/programs/".$prgname;
    system($cmd);
    // wget setup file and fall back to background mode
    $cmd="wget -b ".$url." -O ".$revpath."/html/programs/".$prgname."/setup.exe";
    system($cmd);    
}

function getDesc($filename) {
    global $cwdir;
    $res="";
    $fname=$cwdir."desc/".trim($filename);
    $farr=file($fname);
    foreach ($farr as $item) {
        $res.=$item;
    }
    return $res;
}


function getVer($filename) {
    global $cwdir;
    $res=array();
    $fname=$cwdir."src/".trim($filename);
    $farr=file($fname);
    foreach ($farr as $item) {
        if (ereg("version:",$item)) {
            $version=trim(substr($item,8,strlen($item)));            
        } 
        if (ereg("url:",$item)) {
            $url=trim(substr($item,4,strlen($item)));            
        }
    }
    $res=array("version" => $version, "url" => $url);
    return $res;
}

/** stupid functions **/

// defind category which match the category name, load category
$dir=$cwdir."category";
$category_arr=array();
if ($dh = opendir($dir)) {
    while (($file = readdir($dh)) !== false) {
        if ((is_dir($file))!=true) {
            array_push($category_arr,$file);
        }
    }
    closedir($dh);
}

//var_dump($category_arr);

// load header
echo "Load category template ...\n";
$cateheader=file("../template/categories.html");
$catebody=file("../template/categories-body.html");
$catebodystr="";
foreach ($catebody as $item) {
    $catebodystr.=$item;
}
$cateheaderstr="";
foreach ($cateheader as $item) {
    $cateheaderstr.=$item;
}
echo "Load program template ...\n";
$swstr="";
$swarr=file("../template/program.html");
foreach ($swarr as $item) {
    $swstr.=$item;
}

//echo $catebodystr;

// copy disc structure
echo "Copy disc structure ...\n";
$cmd="cp -rf ../disc/* ../release/rev-".$version."/";
system($cmd);

// build category file
foreach ($category_arr as $catitem) {
    $catloop="";
    // load item in category 
    $catstr="";
    $catarr=file("../category/".$catitem);
    foreach ($catarr as $item) {
        // open short description
        $desitem=file("../short-desc/".trim($item));
        // replace string for item in each category
        $catloop=str_replace("%NAME%",ucwords(trim($item)),$catebodystr);
        $catloop=str_replace("%DESCRIPTION%",trim($desitem[0]),$catloop);
        $catstr.=$catloop;
        // replace for s/w desc
        $swver=getVer($item);
        $swdes=getDesc($item);
        $swloop=str_replace("%NAME%",ucwords(trim($item)),$swstr);
        $swloop=str_replace("%VERSION%",trim($swver['version']),$swloop);
        $swloop=str_replace("%ONELINE%",trim($desitem[0]),$swloop);
        $swloop=str_replace("%DESCRIPTION%",trim($swdes),$swloop);
        // build program list and send signal to download setup file
        echo "Create item description ".ucwords(trim($item))."...\n";
        $fp=fopen($despath.trim($item).".html","w");
        fwrite($fp,$swloop);
        fclose($fp);
        // download signal
        echo "Send download signal description ".ucwords(trim($item))."...\n";
	if ($swver['url']!="") {
		downloadSetupFile(trim($item),$swver['url']);
	}
    }
    echo "Create category description ".ucwords(trim($catitem))."...\n";
    $catebodycontent=str_replace("%CATEGORY%",ucwords(trim($catitem)),$cateheaderstr);
    $catebodycontent=str_replace("%BODY%",trim($catstr),$catebodycontent);
    $fp=fopen($despath.$catitem.".html","w");
    fwrite($fp,$catebodycontent);
    fclose($fp);
}

// copy disc dir 




 
?>