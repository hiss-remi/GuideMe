package org.guideme.guideme.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

import org.guideme.guideme.settings.ComonFunctions;
import org.guideme.guideme.util.HashCommandProcessor;
import org.guideme.guideme.util.HashParam;
import org.guideme.guideme.util.HashParam.Type;
import org.guideme.guideme.util.VeryInsensitiveMap;
import org.mozilla.javascript.NativeObject;

public class Video
{
	private String id;
	private String startAt;
	private String stopAt;
	private String target;
	private String ifSet;
	private String ifNotSet;
	private String set;
	private String unSet;
	private String repeat;
	private String jscript;
	private LocalTime ifBefore; //Time of day must be before this time
	private LocalTime ifAfter; //Time of day must be after this time
	private ComonFunctions comonFunctions = ComonFunctions.getComonFunctions();
	private String scriptVar;
	private int volume;  //integer between 0 and 100 

	public Video(String id, String startAt, String stopAt, String target, String ifSet, String ifNotSet, String set, String unSet, String repeat, String jscript, String ifAfter, String ifBefore, String scriptVar, int volume)
	{
		this.id = id;
		this.startAt = startAt;
		this.stopAt = stopAt;
		this.target = target;
		this.ifSet = ifSet;
		this.ifNotSet = ifNotSet;
		this.set = set;
		this.unSet = unSet;
		this.repeat = repeat;
		this.jscript = jscript;
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
		this.volume = volume;

	}

	public Video(NativeObject params)
	{
		HashCommandProcessor processor = new HashCommandProcessor(mapper);
		processor.parse(params);
		this.id = processor.getString("id");
		this.startAt = processor.getString("startAt");
		this.stopAt = processor.getString("stopAt");
		this.target = processor.getString("target");
		this.ifNotSet = processor.getString("ifNotSet");
		this.ifSet = processor.getString("ifSet");
		this.set = processor.getString("set");
		this.unSet = processor.getString("unset");
		this.repeat = processor.getString("repeat");
		if (this.repeat == "")
			this.repeat = processor.getString("loops");
		String temp = processor.getString("jScript");
		if (temp == "")
		{
			temp = processor.getString("script");
			if (temp == "")
				temp = processor.getString("javaScript");
		}
		this.jscript = temp;

		temp = processor.getString("ifBefore");
		if (temp == "")
			this.ifBefore = null;
		else
			this.ifBefore = LocalTime.parse(temp);

		temp = processor.getString("ifAfter");
		if (temp == "")
			this.ifAfter = null;
		else
			this.ifAfter = LocalTime.parse(temp);

		this.scriptVar = processor.getString("scriptVar");
		this.volume = processor.getInt("volume");
	}

	private static Map<String, HashParam> mapper = createMapper();

	private static Map<String, HashParam> createMapper() {
		Map<String, HashParam> temp = new VeryInsensitiveMap();
		temp.put("id", new HashParam(""));
		temp.put("startAt", new HashParam(""));
		temp.put("stopAt", new HashParam(""));
		temp.put("target", new HashParam(""));
		temp.put("ifNotSet", new HashParam(""));
		temp.put("ifSet", new HashParam(""));
		temp.put("set", new HashParam(""));
		temp.put("unset", new HashParam(""));
		temp.put("repeat", new HashParam(""));
		temp.put("loops", new HashParam(""));
		temp.put("javaScript", new HashParam(""));
		temp.put("jScript", new HashParam(""));
		temp.put("script", new HashParam(""));
		temp.put("ifBefore", new HashParam(""));
		temp.put("ifAfter", new HashParam(""));
		temp.put("scriptVar", new HashParam(""));
		temp.put("volume", new HashParam(Type.INTEGER, 100));
		return temp;
	}

	public String getId() {
		return this.id;
	}

	public String getStartAt() {
		return this.startAt;
	}

	public String getStopAt() {
		return this.stopAt;
	}

	public String getTarget() {
		return this.target;
	}

	public boolean canShow(ArrayList<String> setList) {
		boolean retVal = comonFunctions.canShowTime(ifBefore, ifAfter);
		if (retVal) {
			retVal =  comonFunctions.canShow(setList, ifSet, ifNotSet);
		}
		return retVal;
	}

	public void setUnSet(ArrayList<String> setList) {
		comonFunctions.SetFlags(this.set, setList);
		comonFunctions.UnsetFlags(this.unSet, setList);
	}

	public String getRepeat() {
		return this.repeat;
	}

	public String getJscript() {
		return jscript;
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

	public int getVolume() {
		return volume;
	}


}