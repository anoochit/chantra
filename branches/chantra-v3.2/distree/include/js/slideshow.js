//set duration for each image
var duration = 3;
//Please do not edit below
var thumbs=[]; 
var ct=0;

function SwitchThumb() {
	if (document["ThumbImage"]) {
		var n = (ct+1) % screenShots.length;
		if (thumbs[n] && (thumbs[n].complete || thumbs[n].complete==null)) {
			document["ThumbImage"].src = thumbs[ct=n].src;
		}
		n = (ct+1) % screenShots.length;
		thumbs[n] = new Image;
		thumbs[n].src = screenShots[n].t;
		setTimeout("SwitchThumb()", duration*1000);
	}
}

function ShowScreenShot() {
	ScreenShot(screenShots[ct].x, screenShots[ct].y, screenShots[ct].i);
}
