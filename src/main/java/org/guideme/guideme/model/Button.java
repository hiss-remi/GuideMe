package org.guideme.guideme.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.graphics.Color;
import org.guideme.guideme.settings.ComonFunctions;
import org.guideme.guideme.util.HashCommandProcessor;
import org.guideme.guideme.util.HashParam;
import org.guideme.guideme.util.HashParam.*;
import org.mozilla.javascript.NativeObject;

public class Button  implements Comparable<Button>
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

		this.target = processor.getString("target");
		this.text = processor.getString("text");
		this.ifNotSet = processor.getString("ifnotset");
		this.ifSet = processor.getString("ifset");
		this.set = processor.getString("set");
		this.unSet = processor.getString("unset");
		String temp = processor.getString("jscript");
		if (temp == "")
		{
			temp = processor.getString("script");
			if (temp == "")
				temp = processor.getString("javascript");
		}
		this.jScript = temp;
		this.image = processor.getString("image");
		this.hotKey = processor.getString("hotkey");
		this.fontName = processor.getString("fontname");
		this.fontHeight = processor.getString("fontheight");
		this.sortOrder = processor.getInt("sortorder");

		Color temp2 = processor.getColor("bgcolor1", false);
		if (temp2 == null)
			temp2 = comonFunctions.getColor("white");
		this.bgColor1 = temp2;

		temp2 = processor.getColor("bgcolor2", false);
		if (temp2 == null)
			temp2 = this.bgColor1;
		this.bgColor2 = temp2;

		temp2 = processor.getColor("fontcolor", false);
		if (temp2 == null)
			temp2 = comonFunctions.getColor("black");
		this.fontColor = temp2;

		temp = processor.getString("ifbefore");
		if (temp == "")
			this.ifBefore = null;
		else
			this.ifBefore = LocalTime.parse(temp);

		temp = processor.getString("ifafter");
		if (temp == "")
			this.ifAfter = null;
		else
			this.ifAfter = LocalTime.parse(temp);

		this.disabled = processor.getBool("disabled");
		this.id = processor.getString("id");
		this.scriptVar = processor.getString("scriptvar");
		this.setDefaultBtn(processor.getBool("default") || processor.getBool("defaultbtn") || processor.getBool("defaultbutton"));
	}

	protected static HashMap<String, HashParam> mapper = createMapper();

	protected static HashMap<String, HashParam> createMapper() {
		HashMap<String, HashParam> temp = new HashMap<String, HashParam>();
		temp.put("target", new HashParam(""));
		temp.put("text", new HashParam(""));
		temp.put("ifnotset", new HashParam(""));
		temp.put("ifset", new HashParam(""));
		temp.put("set", new HashParam(""));
		temp.put("unset", new HashParam(""));
		temp.put("javascript", new HashParam(""));
		temp.put("jscript", new HashParam(""));
		temp.put("script", new HashParam(""));
		temp.put("image", new HashParam(""));
		temp.put("hotkey", new HashParam(""));
		temp.put("fontname", new HashParam(""));
		temp.put("fontheight", new HashParam(""));
		temp.put("sortorder", new HashParam(Type.INTEGER, 1));
		temp.put("bgcolor1", new HashParam(Type.COLOR, null));
		temp.put("bgcolor2", new HashParam(Type.COLOR, null));
		temp.put("fontcolor", new HashParam(Type.COLOR, null));
		temp.put("ifbefore", new HashParam(""));
		temp.put("ifafter", new HashParam(""));
		temp.put("disabled", new HashParam(Type.BOOLEAN, false));
		temp.put("id", new HashParam(""));
		temp.put("scriptvar", new HashParam(""));
		temp.put("default", new HashParam(Type.BOOLEAN, false));
		temp.put("defaultbtn", new HashParam(Type.BOOLEAN, false));
		temp.put("defaultbutton", new HashParam(Type.BOOLEAN, false));
		return temp;
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


	public void setDefaultBtn(boolean defaultBtn) {
		this.defaultBtn = defaultBtn;
	}


}