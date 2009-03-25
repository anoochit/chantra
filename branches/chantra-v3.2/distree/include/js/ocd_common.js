

/* ScreenShot() takes three arguments:
   X is the width of the screenshot that is to be shown.
   Y is the height of the screenshot to be shown.
   source is the path to the screenshot.

It also measures the resolution of the screen. If the screen is as large or larger
than the screenshot to be shown, ScreenShot() pops up a window that big. If the
screen is smaller than the screenshot, ScreenShot() pops up a window as large as
the screen itself, and enables scroll bars.*/

function ScreenShot(ShotX, ShotY, source){
   // Width and Height of new window;
   var ChildX, ChildY;
   // User can resize/scroll new window?  Default no.
   var resizability = "no";
   var scrollable = "no";
   // Available width and height in pixels -- doesn't count task bar.
   var ScreenX = screen.availWidth;
   var ScreenY = screen.availHeight;
    var buffer = 20;
    
   // Scrollbars appear even if explicitly disabled unless the new window
   // is at least 16 pixels wider than the screenshot in both dimensions.
   // The scrollbars themselves are 16 px.  Could be a moz bug.
   // If the window would bigger than or equal to the screensize after these
   // pixels are added, instead use the screen dimensions minus 100 and make
   // the window resizeable and scrollable.
   if ((ShotX + buffer) >= ScreenX){
      ChildX = ScreenX - 100;
      resizability = "yes";
      scrollable = "yes";
   } else { ChildX = ShotX + buffer;  }
   
   if ((ShotY + buffer) >= ScreenY){
      ChildY = ScreenY - 100;
      resizability = "yes";
      scrollable = "yes";
   } else { ChildY = ShotY + buffer; }

   Width = screen.width;
   Height = screen.height;
   xPos = (Width - ChildX) / 2;
   yPos = (Height - ChildY) / 2;

   // Assemble the list of features of the window.
   features = "titlebar=yes,status=no";
   features += ",resizable="+resizability;
   features += ",scrollbars="+scrollable;
   features += ",width="+ChildX;
   features += ",height="+ChildY;
   features += ",top="+yPos;
   features += ",left="+xPos;

   source_line = '<img border="0" alt="screenshot image" src="'+source+'" />';

   // Open the window.
   // window.open(source, "Thumbnail", features);
   // Open the window.
   // window.open(source, "Thumbnail", features);
    screen_window = window.open("", "ScreenCaptureImage", features);
    screen_window.document.write("<html><title>คลิกที่รูปเพื่อปิดวินโดว์</title>");
    screen_window.document.write("<body bgcolor='gray' onblur=window.close()>");
    screen_window.document.write('<div style="display:block" class="screen_block">');
    screen_window.document.write('<div class="thumb_frame"><a href="javascript:window.close();">');  
    screen_window.document.write(source_line);
    screen_window.document.write("</a></div></div></body></html>");
    screen_window.focus();

// If you just want to open an existing HTML page in the 
// new window, you can replace win()'s coding above with:
// window.open("page.html","","height=200,width=200,left=300,top=100");
}

/*PositionWindow() measures the resolution of the host screen, and uses that information to center the
browser on the screen.  NOTE: check to see if it's possible to make K-Meleon do this itself, so that the
screen doesn't shift noticeably on start.  This is called from the onLoad event.*/

function PositionWindow() {
   if (!document.URL.match("home_desc.html")) return;
   var Width, Height, xPos, yPos;
   Width = screen.width;
   Height = screen.height;
   xPos = (Width - 800) / 2;
   yPos = (Height - 600) / 2;
   window.moveTo(xPos, yPos);
}

/*function RunURL(filename, param) {
    var shell = new ActiveXObject("WScript.Shell");
    shell.run( "\"" + filename + "\" " + param, 1, false);
}*/
/*
function Sound(file)
{
	if (navigator.appVersion.search("Windows 9") == -1) {
		document.all.music.src = "../include/themes/sounds/"+file;
	}
}

function ParentSound(file)
{
	if (navigator.appVersion.search("Windows 9") == -1) {
		parent.document.getElementById('music').src = "disctree/include/themes/sounds/"+file;
	}
}
*/
function Run()
{
	var filename = arguments[0];
	//var param = "";
	//if (arguments.length == 2) param = arguments[1];
	try {
		netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	} catch (e) {
		alert("Permission to xpconnect was denied.");
	}

	var file = Components.classes["@mozilla.org/file/directory_service;1"]
                     .getService(Components.interfaces.nsIProperties)
                     .get("CurProcD", Components.interfaces.nsIFile);
    file = file.QueryInterface(Components.interfaces.nsILocalFile);
	file.appendRelativePath(filename);
	if ( !file.exists()  ) {
		alert("File "+filename+" does not exist");
	}
	// create an nsIProcess
	var process = Components.classes["@mozilla.org/process/util;1"]
                        .createInstance(Components.interfaces.nsIProcess);
	process.init(file);
	var args = new Array();
	for (var i = 1; i < arguments.length; i++) {
		args[i-1] = arguments[i];
	}
	process.run(false, args, args.length);
}

function InstallThenSurvey(filename, app)
{
    //Sound("install.wav");
    Launch(filename);
    Run("startcmd.exe", "survey.hta", app);
}

function Launch(filename)
{
	if ( filename.substr(0, 4) == "http") {
		Run("startcmd.exe", filename);
	} else {
	    filename = filename.replace(/\//g, "\\");
		try {
			netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("Permission to xpconnect was denied.");
		}
		var file = Components.classes["@mozilla.org/file/directory_service;1"]
						 .getService(Components.interfaces.nsIProperties)
						 .get("CurProcD", Components.interfaces.nsIFile);
		file = file.QueryInterface(Components.interfaces.nsILocalFile);
		file.appendRelativePath(filename);
		if ( !file.exists()  ) {
			alert("File "+filename+" does not exist");
		}
		file.launch();
	}
}
