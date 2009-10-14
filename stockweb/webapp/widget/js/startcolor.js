function PritisnjenEnter(e,form){
    var key=e.keyCode || e.which;
    if (key==13)
    {
        checkcolor();
    }
}
	function checkcolor()
	{
		var newcolor=document.getElementById("startcolor").value.replace('#', '');
		
		var error = "";
		
		if (newcolor == "") {
		   error = "You didn't enter a password.\n";
		}
		
		if (newcolor.length != 6) {
		   error = "Color code shoud cointain 6 characters.\n";
		}
		
		var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]ghijklmnoprstuvzwxyqGHIJKLMNOPRSTUVZQWXY]/
		if (newcolor.match(illegalChars)) {
		   error = "Color code contains illegal characters.\n";
		}

		if (error=="")
		{
			dela();
		}
		else
		{
			alert(error);
			document.getElementById("startcolor").select();
		}   
	}
	
	

	function dela() {
		var newcolor=document.getElementById("startcolor").value.replace('#', '');
	
		R = HexToR(newcolor);
		G = HexToG(newcolor);
		B = HexToB(newcolor);
		
		var hsvcolor=rgbToHsv(R,G,B);
		var slider=180-(hsvcolor.h/2);
		var newx=Math.round(hsvcolor.s*1.8);
		var newy=Math.round(180-hsvcolor.v*1.8);
		
		//alert(R+','+G+','+B+':'+hsvcolor.h+','+hsvcolor.s+','+hsvcolor.v);
		//alert(newx+','+newy);
		
		hue.setValue(slider);
		picker.setRegionValue(newx,newy);

		
		document.getElementById("pickerDiv").style.backgroundColor =
			"rgb(" + R + ", " + G + ", " + B + ")";
		
		document.getElementById("pickerhval").value = Math.round(hsvcolor.h *10)/10;
		document.getElementById("pickersval").value = Math.round(hsvcolor.s *10)/10;
		document.getElementById("pickervval").value = Math.round(hsvcolor.v *10)/10;
		
		document.getElementById("pickerSwatch").style.backgroundColor =
			"rgb(" + R + ", " + G + ", " + B + ")";
		
		document.getElementById("pickerrval").value = R;
		document.getElementById("pickergval").value = G;
		document.getElementById("pickerbval").value = B;
		
		var hexvalue = document.getElementById("pickerhexval").value ='#'+
			YAHOO.util.Color.rgb2hex(R, G, B);
			ddcolorposter.initialize(R, G, B, hexvalue)
			
		//document.getElementById("vnos").value ="vneseno";
			
	}
	
	
	function HexToR(h) {return parseInt((cutHex(h)).substring(0,2),16)}
	function HexToG(h) {return parseInt((cutHex(h)).substring(2,4),16)}
	function HexToB(h) {return parseInt((cutHex(h)).substring(4,6),16)}
	function cutHex(h) {return (h.charAt(0)=="#") ? h.substring(1,7):h}
	
	function rgbToHsv(red, green, blue)
	{
		r = red / 255; g = green / 255; b = blue / 255; // Scale to unity.

		var minVal = Math.min(r, g, b);
		var maxVal = Math.max(r, g, b);
		var delta = maxVal - minVal;
		var HSVh, HSVs, HSVv;
	
		HSVv = maxVal;
	
		if (delta == 0) {
			HSVh = 0;
			HSVs = 0;
		} else {
			HSVs = delta / maxVal;
			var del_R = (((maxVal - r) / 6) + (delta / 2)) / delta;
			var del_G = (((maxVal - g) / 6) + (delta / 2)) / delta;
			var del_B = (((maxVal - b) / 6) + (delta / 2)) / delta;
	
			if (r == maxVal) {HSVh = del_B - del_G;}
			else if (g == maxVal) {HSVh = (1 / 3) + del_R - del_B;}
			else if (b == maxVal) {HSVh = (2 / 3) + del_G - del_R;}
			
			if (HSVh < 0) {HSVh += 1;}
			if (HSVh > 1) {HSVh -= 1;}
		}
		HSVh = (HSVh * 360);
		HSVs = (HSVs * 100);
		HSVv = (HSVv * 100);
		return {
			h: HSVh,
			s: HSVs,
			v: HSVv
		};
	}