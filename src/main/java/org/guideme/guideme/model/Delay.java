package org.guideme.guideme.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

import org.guideme.guideme.settings.ComonFunctions;
import org.guideme.guideme.util.HashCommandProcessor;
import org.guideme.guideme.util.HashParam;
import org.guideme.guideme.util.HashParam.*;
import org.guideme.guideme.util.VeryInsensitiveMap;
import org.mozilla.javascript.NativeObject;

public class Delay {
	private String ifSet;
	private String ifNotSet;
	private String set;
	private String unSet;
	private String delay;
	private String target;
	private String startWith;
	private String style;
	private String jScript;
	private LocalTime ifBefore; //Time of day must be before this time
	private LocalTime ifAfter; //Time of day must be after this time
	private ComonFunctions comonFunctions = ComonFunctions.getComonFunctions();
	private String scriptVar;
	
	
	public Delay(String target, String delay, String ifSet, String ifNotSet, String startWith, String style, String set, String unSet, String jScript, String ifAfter, String ifBefore, String scriptVar) {
		this.target = target;
		this.delay = delay;
		this.ifNotSet = ifNotSet;
		this.ifSet = ifSet;
		this.startWith = startWith;
		this.style = style;
		this.set = set;
		this.unSet = unSet;
		this.jScript = jScript;
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
		this.scriptVar = scriptVar;

	}

	public Delay(NativeObject params) {
		HashCommandProcessor processor = new HashCommandProcessor(mapper);
		processor.parse(params);
		this.target = processor.getString("target");
		this.delay = processor.getString("delay");
		this.startWith = processor.getString("startWith");
		this.style = processor.getString("style");
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
		this.scriptVar = processor.getString("scriptVar");

		this.ifBefore = processor.getTime("ifBefore");
		this.ifAfter = processor.getTime("ifAfter");

		this.ifNotSet = processor.getString("ifNotSet");
		this.ifSet = processor.getString("ifSet");
	}

	protected static Map<String, HashParam> mapper = createMapper();

	private static Map<String, HashParam> createMapper() {
		Map<String, HashParam> temp = new VeryInsensitiveMap();
		temp.put("target", new HashParam(""));
		temp.put("delay", new HashParam(Type.RANGE, ""));
		temp.put("startWith", new HashParam(""));
		temp.put("style", new HashParam("N"));
		temp.put("set", new HashParam(""));
		temp.put("unset", new HashParam(""));
		temp.put("javaScript", new HashParam(""));
		temp.put("jScript", new HashParam(""));
		temp.put("script", new HashParam(""));
		temp.put("ifNotSet", new HashParam(""));
		temp.put("ifSet", new HashParam(""));
		temp.put("ifBefore", new HashParam(Type.TIME, null));
		temp.put("ifAfter", new HashParam(Type.TIME, null));
		temp.put("scriptVar", new HashParam(""));
		return temp;
	}

	public boolean canShow(ArrayList<String> setList) {
		boolean retVal = comonFunctions.canShowTime(ifBefore, ifAfter);
		if (retVal) {
			retVal =  comonFunctions.canShow(setList, ifSet, ifNotSet);
		}
		return retVal;
	}

	public int getDelaySec() {
		return comonFunctions.getRandom(delay);
	}

	public String getTarget() {
		return target;
	}

	public String getStartWith() {
		return startWith;
	}

	public String getstyle() {
		return style;
	}
	
	public void setUnSet(ArrayList<String> setList) {
		//pass in the current flags and either add or remove the ones for this delay
		comonFunctions.SetFlags(set, setList);
		comonFunctions.UnsetFlags(unSet, setList);
	}

	public String getSet() {
		return set;
	}

	public String getUnSet() {
		return unSet;
	}

	public String getjScript() {
		return jScript;
	}

	public String getIfSet() {
		return ifSet;
	}

	public String getIfNotSet() {
		return ifNotSet;
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

	public String getScriptVar() {
		return scriptVar;
	}



}