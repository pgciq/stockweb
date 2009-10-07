
function add_onload_function(fn)
{
    var oe=window.onload;
    window.onload = function() { if (oe) oe(); fn(); }
}

function starts_with(str, what)
{
    return str.substr(0, what.length) === what;
}

function trim_leading_comments(str)
{
    // very basic. doesn't support /* ... */
    str = str.replace(/^(\s*\/\/[^\n]*\n)+/, '');
    str = str.replace(/^\s+/, '');
    return str;
}

function unpacker_filter(source)
{
        stripped_source = trim_leading_comments(source);

        if (starts_with(stripped_source.toLowerCase().replace(/ +/g, ''), 'eval(function(p,a,c,k')) {
            try {
                eval('var unpacked_source = ' + stripped_source.substring(4) + ';')
                return unpacker_filter(unpacked_source);
            } catch (error) {
                source = '// jsbeautifier: unpacking failed\n' + source;
            }
        }
    return source;

}



function do_js_beautify(obj_orig, obj_dest)
{	
//	  var obj_tmp = "";
//		obj_tmp = document.getElementById(obj_orig).contentWindow.document.body.innerHTML;
    var js_source = document.getElementById(obj_orig).value.replace(/^\s+/, '');
//    var js_source = obj_tmp.replace(/^\s+/, '');
    var indent_size = 4; //document.getElementById('tabsize').value;
    var indent_char = ' ';
    var preserve_newlines = "true";

    if (indent_size == 1) {
        indent_char = '\t';
    }


    if (js_source && js_source[0] === '<' && js_source.substring(0, 4) !== '<!--') {
        document.getElementById(obj_dest).value = style_html(js_source, indent_size, indent_char, 80);
    } else {
        document.getElementById(obj_dest).value =
        js_beautify(unpacker_filter(js_source), {indent_size: indent_size, indent_char: indent_char, preserve_newlines:preserve_newlines, space_after_anon_function:true});
    }

    return false;
}


function get_var( name )
{
    var res = new RegExp( "[\\?&]" + name + "=([^&#]*)" ).exec( window.location.href );
    return res ? res[1] : "";
}

// Serve para gestir o evento do textarea e formatar o codigo javascript
var lastkeypress;
function applyFormatter(event, obj_orig){
	if(((lastkeypress == 125) || (lastkeypress == 123)) && (event.which == 13)) {  
			do_js_beautify(obj_orig, obj_orig); 
	} 
	
	lastkeypress=event.which;
}

document.write("<script type='text/javascript' src='../js/indent/beautify.js' ></script>");
document.write("<script type='text/javascript' src='../js/indent/beautify-tests.js' ></script>");
document.write("<script type='text/javascript' src='../js/indent/HTML-Beautify.js' ></script>");




