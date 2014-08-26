/********************************************************************
 * openWYSIWYG settings file Copyright (c) 2006 openWebWare.com
 * Contact us at devs@openwebware.com
 * This copyright notice MUST stay intact for use.
 *
 * $Id: wysiwyg-settings.js,v 1.4 2007/01/22 23:05:57 xhaggi Exp $
 ********************************************************************/

var content = new WYSIWYG.Settings();
content.Width = "410px"; 
content.Height = "406px";
content.ImagesDir = "images/openwysiwyg/";
content.CSSFile = "css/wysiwyg.css";
content.Toolbar[0] = new Array("font", "fontsize", "bold", "italic", "underline");
content.Toolbar[1] = "";
//content.ContextMenu = true;
content.StatusBarEnabled = false;
/*
var content1 = new WYSIWYG.Settings();
content1.Width = "410px"; 
content1.Height = "100%";
content1.ImagesDir = "images/openwysiwyg/";
content1.CSSFile = "css/wysiwyg.css";
content1.Toolbar[0] = new Array("font", "fontsize", "bold", "italic", "underline");
content1.Toolbar[1] = "";
content1.ContextMenu = false;
content1.StatusBarEnabled = false;
*/
var list = new WYSIWYG.Settings();
list.Width = "410px"; 
list.Height = "406px";
list.ImagesDir = "images/openwysiwyg/";
list.CSSFile = "css/wysiwyg.css";
list.clearToolbar();
list.StatusBarEnabled = false;
list.ContextMenu = false;

