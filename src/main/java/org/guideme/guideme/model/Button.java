package org.guideme.guideme.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.graphics.Color;
import org.guideme.guideme.settings.ComonFunctions;
import org.guideme.guideme.util.HashCommandProcessor;
import org.guideme.guideme.util.HashParam;
import org.guideme.guideme.util.HashParam.*;
import org.guideme.guideme.util.VeryInsensitiveMap;
import org.mozilla.javascript.NativeObject;

public class Button implements Comparable<Button>
{
	private String ifSet;
	private String ifNotSet;
	private String set;
	private String unSet;
	private String text;
	private String target;
	private String jScript;
	private String image;
	private String hotKey;
	private String fontName;
	private String fontHeight;
	private int sortOrder;
	private LocalTime ifBefore; //Time of day must be before this time
	private LocalTime ifAfter; //Time of day must be after this time
	private boolean disabled;
	private String id;
	private org.eclipse.swt.graphics.Color bgColor1;
	private org.eclipse.swt.graphics.Color bgColor2;
	private org.eclipse.swt.graphics.Color fontColor;
	private ComonFunctions comonFunctions = ComonFunctions.getComonFunctions();
	private String scriptVar;
	private boolean defaultBtn; //button activated by enter
	protected HashCommandProcessor changeParams;
	private static Logger logger = LogManager.getLogger();

	public Button (String target, String text)
	{
		this(target, text, "", "", "", "", "", "", "");
	}

	public Button(String target, String text, String ifSet, String ifNotSet, String set, String unSet, String jScript, String image, String hotKey)
	{
		this(target, text, ifSet, ifNotSet, set, unSet, jScript, image, hotKey, "", "", "", "", "", 1, "", "", false, "", "", false);
	}

	
	public Button(String target, String text, String ifSet, String ifNotSet, String set, String unSet, String jScript, String image, String hotKey, String fontName, String fontHeight, String fontColor, String bgColor1, String bgColor2, int sortOrder, String ifAfter, String ifBefore, boolean disabled, String id, String scriptVar, boolean defaultBtn)
	{
		this.target = target;
		this.text = text;
		this.ifNotSet = ifNotSet;
		this.ifSet = ifSet;
		this.set = set;
		this.unSet = unSet;
		this.jScript = jScript;
		this.image = image;
		this.hotKey = hotKey;
		this.fontName = fontName;
		this.fontHeight = fontHeight;
		this.sortOrder = sortOrder;
		
		if (bgColor1 == "") {
			this.bgColor1 = comonFunctions.getColor("white");
        } else if (bgColor1.startsWith("#")) {
        	this.bgColor1 = comonFunctions.decodeHexColor(bgColor1);
        } else {
			this.bgColor1 = comonFunctions.getColor(bgColor1);
		}
		
		if (bgColor2 == "") {
			this.bgColor2 = this.bgColor1;
        } else if (bgColor2.startsWith("#")) {
        	this.bgColor2 = comonFunctions.decodeHexColor(bgColor2);
		} else {
			this.bgColor2 = comonFunctions.getColor(bgColor2);
		}
		
		if (fontColor == "") {
			this.fontColor = comonFunctions.getColor("black");
        } else if (fontColor.startsWith("#")) {
        	this.fontColor = comonFunctions.decodeHexColor(fontColor);
		} else {
			this.fontColor = comonFunctions.getColor(fontColor);
		}
		
		if (ifBefore.equals("")) {
			this.ifBefore = null;
		} else {
			this.ifBefore = LocalTime.parse(ifBefore);
		}
		if (ifAfter.equals("")) {
			this.ifAfter = null;
		} else {
			this.ifAfter = LocalTime.parse(ifAfter);
		}
		this.disabled = disabled;
		this.id = id;
		this.scriptVar = scriptVar;
		this.setDefaultBtn(defaultBtn);
	}

	public Button(NativeObject params) {
		HashCommandProcessor processor = new HashCommandProcessor(mapper);
		processor.parse(params);
		processParams(processor);
	}

	public Button() {
	}

	protected void processParams(HashCommandProcessor processor) {
		this.id = processor.getString("id");

		//Premature optimization?
		//if (isChangeOrder) {
			changeParams = processor;
		//	return;
		//}

		this.target = processor.getString("target");
		this.text = processor.getString("text");
		this.ifNotSet = processor.getString("ifNotSet");
		this.ifSet = processor.getString("ifSet");
		this.set = processor.getString("set");
		this.unSet = processor.getString("unset");
		String temp = processor.getString("jScript");
		if (temp == "")
		{
			temp = processor.getString("script");
			if (temp == "")
				temp = processor.getString("javaScript");
		}
		this.jScript = temp;
		this.image = processor.getString("image");
		this.hotKey = processor.getString("hotKey");
		this.fontName = processor.getString("fontName");
		this.fontHeight = processor.getString("fontHeight");
		this.sortOrder = processor.getInt("sortOrder");

		Color temp2 = processor.getColor("bgColor1", false);
		if (temp2 == null)
			temp2 = comonFunctions.getColor("white");
		this.bgColor1 = temp2;

		temp2 = processor.getColor("bgColor2", false);
		if (temp2 == null)
			temp2 = this.bgColor1;
		this.bgColor2 = temp2;

		temp2 = processor.getColor("fontColor", false);
		if (temp2 == null)
			temp2 = comonFunctions.getColor("black");
		this.fontColor = temp2;

		this.ifBefore = processor.getTime("ifBefore");
		this.ifAfter = processor.getTime("ifAfter");

		this.disabled = processor.getBool("disabled");

		this.scriptVar = processor.getString("scriptVar");
		this.setDefaultBtn(processor.getBool("default") || processor.getBool("defaultBtn") || processor.getBool("defaultButton"));


	}

	protected static Map<String, HashParam> mapper = createMapper();

	protected static Map<String, HashParam> createMapper() {
		Map<String, HashParam> temp = new VeryInsensitiveMap();
		temp.put("target", new HashParam(""));
		temp.put("text", new HashParam(""));
		temp.put("ifNotSet", new HashParam(""));
		temp.put("ifSet", new HashParam(""));
		temp.put("set", new HashParam(""));
		temp.put("unset", new HashParam(""));
		temp.put("javaScript", new HashParam(""));
		temp.put("jScript", new HashParam(""));
		temp.put("script", new HashParam(""));
		temp.put("image", new HashParam(""));
		temp.put("hotKey", new HashParam(""));
		temp.put("fontName", new HashParam(""));
		temp.put("fontHeight", new HashParam(""));
		temp.put("sortOrder", new HashParam(Type.INTEGER, 1));
		temp.put("bgColor1", new HashParam(Type.COLOR, null));
		temp.put("bgColor2", new HashParam(Type.COLOR, null));
		temp.put("fontColor", new HashParam(Type.COLOR, null));
		temp.put("ifBefore", new HashParam(Type.TIME, null));
		temp.put("ifAfter", new HashParam(Type.TIME, null));
		temp.put("disabled", new HashParam(Type.BOOLEAN, false));
		temp.put("id", new HashParam(""));
		temp.put("scriptVar", new HashParam(""));
		temp.put("default", new HashParam(Type.BOOLEAN, false));
		temp.put("defaultBtn", new HashParam(Type.BOOLEAN, false));
		temp.put("defaultButton", new HashParam(Type.BOOLEAN, false));

		//GlobalButton's parameters are here to simplify the constructor situation.
		//On a regular button, they just won't do anything.
		//Unless actions are implemented that apply to Button as well...
		temp.put("placement", new HashParam(HashParam.Type.ENUM, GlobalButton.Placement.class));
		temp.put("action", new HashParam(HashParam.Type.ENUM, GlobalButton.Action.class));

		//WebcamButton's parameters, same reason.
		temp.put("type", new HashParam(""));
		temp.put("file", new HashParam(""));
		return temp;
	}

	public void merge(Button changeOrder)
	{
		//if (changeParams != null)
			merge(changeOrder.changeParams);
	}

	protected void merge(HashCommandProcessor processor)
	{
		//Seems like there ought to be a less verbose way to do things than this, but I couldn't find one that seemed reliable enough.
		for (String key: processor.getKeys()) {
			switch(key) {
				case "target":
					this.target = processor.getString("target");
					break;
				case "text":
					this.text = processor.getString("text");
					break;
				case "ifnotset":
					this.ifNotSet = processor.getString("ifNotSet");
					break;
				case "ifset":
					this.ifSet = processor.getString("ifSet");
					break;
				case "set":
					this.set = processor.getString("set");
					break;
				case "unset":
					this.unSet = processor.getString("unset");
					break;
				case "jscript":
					this.jScript = processor.getString("jScript");
					break;
				case "script":
					this.jScript = processor.getString("script");
					break;
				case "javascript":
					this.jScript = processor.getString("javascript");
					break;
				case "image":
					this.image = processor.getString("image");
					break;
				case "hotkey":
					this.hotKey = processor.getString("hotKey");
					break;
				case "fontname":
					this.fontName = processor.getString("fontName");
					break;
				case "fontheight":
					this.fontHeight = processor.getString("fontHeight");
					break;
				case "sortorder":
					this.sortOrder = processor.getInt("sortOrder");
					break;
				case "bgcolor1":
					this.bgColor1 = processor.getColor("bgColor1", false);
					break;
				case "bgcolor2":
					this.bgColor2 = processor.getColor("bgColor2", false);
					break;
				case "fontcolor":
					this.fontColor = processor.getColor("fontColor", false);
					break;
				case "ifbefore":
					this.ifBefore = processor.getTime("ifBefore");
					break;
				case "ifafter":
					this.ifAfter = processor.getTime("ifAfter");
					break;
				case "disabled":
					this.disabled = processor.getBool("disabled");
					break;
				case "scriptvar":
					this.scriptVar = processor.getString("scriptVar");
					break;
				//Don't do this, it breaks subclasses.
				//default:
				//	throw new IllegalArgumentException("Invalid key " + key + " for button merge.");
			}
		}
	}

	public void setUnSet(ArrayList<String> setList)
	{
		comonFunctions.SetFlags(this.set, setList);
		comonFunctions.UnsetFlags(this.unSet, setList);
	}

	public String getSet() {
		return this.set;
	}

	public String getUnSet() {
		return this.unSet;
	}

	public boolean canShow(ArrayList<String> setList)
	{
		boolean retVal = comonFunctions.canShowTime(ifBefore, ifAfter);
		if (retVal) {
			retVal =  comonFunctions.canShow(setList, ifSet, ifNotSet);
		}
		return retVal;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}


	public String getjScript() {
		return jScript;
	}

	public String getImage() {
		return image;
	}

	public String getHotKey() {
		return hotKey;
	}

	public String getIfSet() {
		return ifSet;
	}

	public String getIfNotSet() {
		return ifNotSet;
	}


	public String getFontName() {
		return fontName;
	}


	public void setFontName(String fontName) {
		this.fontName = fontName;
	}


	public String getFontHeight() {
		return fontHeight;
	}


	public void setFontHeight(String fontHeight) {
		this.fontHeight = fontHeight;
	}


	public org.eclipse.swt.graphics.Color getbgColor1() {
		return bgColor1;
	}


	public void setbgColor1(String bgColor1) {
		this.bgColor1.dispose();
		if (bgColor1 == "") {
			this.bgColor1 = comonFunctions.getColor("white");
        } else if (bgColor1.startsWith("#")) {
        	this.bgColor1 = comonFunctions.decodeHexColor(bgColor1);
		} else {
			this.bgColor1 = comonFunctions.getColor(bgColor1);
		}
	}


	public org.eclipse.swt.graphics.Color getbgColor2() {
		return bgColor2;
	}


	public void setbgColor2(String bgColor2) {
		this.bgColor2.dispose();
		if (bgColor2 == "") {
			this.bgColor2 = comonFunctions.getColor("white");
        } else if (bgColor2.startsWith("#")) {
        	this.bgColor2 = comonFunctions.decodeHexColor(bgColor2);
		} else {
			this.bgColor2 = comonFunctions.getColor(bgColor2);
		}
	}


	public org.eclipse.swt.graphics.Color getfontColor() {
		return fontColor;
	}


	public void setfontColor(String fontColor) {
		this.fontColor = comonFunctions.getColor(fontColor);
		this.fontColor.dispose();
		if (fontColor == "") {
			this.fontColor = comonFunctions.getColor("black");
        } else if (fontColor.startsWith("#")) {
        	this.fontColor = comonFunctions.decodeHexColor(fontColor);
		} else {
			this.fontColor = comonFunctions.getColor(fontColor);
		}
	}


	public int getSortOrder() {
		return sortOrder;
	}

	public LocalTime getIfBefore() {
		return ifBefore;
	}

	public void setIfBefore(String ifBefore) {
		if (ifBefore.equals("")) {
			this.ifBefore = null;
		} else {
			this.ifBefore = LocalTime.parse(ifBefore);
		}
	}

	public LocalTime getIfAfter() {
		return ifAfter;
	}

	public void setIfAfter(String ifAfter) {
		if (ifAfter.equals("")) {
			this.ifAfter = null;
		} else {
			this.ifAfter = LocalTime.parse(ifAfter);
		}
	}



	@Override
	public int compareTo(Button compareButton) {
		int compareOrder = compareButton.getSortOrder();
		return compareOrder-this.sortOrder;
	}


	public Boolean getDisabled() {
		return disabled;
	}


	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}


	public String getId() {
		return id;
	}


	public String getScriptVar() {
		return scriptVar;
	}


	public boolean isDefaultBtn() {
		return defaultBtn;
	}

	//TODO: This currently does nothing. Fix if possible.
	public void setDefaultBtn(boolean defaultBtn) {
		this.defaultBtn = defaultBtn;
	}


}