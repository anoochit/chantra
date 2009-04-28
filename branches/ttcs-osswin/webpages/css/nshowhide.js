/*

This code is based on a code example from the article "Javascript navigation - cleaner, not meaner" by Christian Heilmann
URL: http://www.evolt.org/article/Javascript_navigation_cleaner_not_meaner/17/60273/index.html
and http://www.bobbyvandersluis.com/articles/unobtrusiveshowhide.php
*/

// If there is enough W3C DOM support for all our show/hide behavior:
// 1. Call the stylesheet that by default hides all toggleable sections 
// 2. Apply the show/hide behavior by calling the initialization function
if (document.getElementById && document.getElementsByTagName && document.createTextNode) {
	document.write('<link rel="stylesheet" type="text/css" href="css/js_hide.css">');
	window.onload = function() {
       rollup ();
       initShowHide ();
    }
}


/*

function initShowHide() {
	if (document.getElementById && document.getElementsByTagName && document.createTextNode) {
		hide();
		var toggle = document.getElementById('toggle');
		var as = toggle.getElementsByTagName('a');
		for (var i = 0; i < as.length; i++) {
			as[i].onclick = function() {
				show(this);
				return false;
			}
		}
        show(as[0]);			
	}
}

*/

function initShowHide() {
	if (document.getElementById && document.getElementsByTagName && document.createTextNode) {
		hide();
		var toggle = document.getElementById('toggle');
		var as = toggle.getElementsByTagName('a');
		for (var i = 0; i < as.length; i++) {
			as[i].onclick = function() {
				show(this);
				return false;
			}
		}
        show(as[0]);			
	}
}


function show(s) {
	hide();
    // Try to get a reference to an element with the id 'current' (a previously active tab header)
 	var oldCurrent = document.getElementById('current');
	// If this element exists, remove its ID attribute
	if (oldCurrent) oldCurrent.removeAttribute('id');
	// Add the ID attribute with value 'current' to the newly active tab header (LI element)
	s.parentNode.setAttribute('id', 'current');
    
    
	var id = s.href.match(/#(\w.+)/)[1];
	document.getElementById(id).style.display = 'block';
    if (s = "intro")  {
       var imagepresent = document.getElementById('image');
       if (imagepresent) document.getElementById('image').style.display = 'block';
    }
}

function hide() {
	var toggleable = document.getElementById('toggleable').getElementsByTagName('div');
	for (var i = 0; i < toggleable.length; i++) {
		toggleable[i].style.display = 'none';
	}
}
