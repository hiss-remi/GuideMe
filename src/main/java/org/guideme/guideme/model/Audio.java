package org.guideme.guideme.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import org.guideme.guideme.settings.ComonFunctions;
import org.guideme.guideme.util.HashCommandProcessor;
import org.guideme.guideme.util.HashParam;
import org.guideme.guideme.util.VeryInsensitiveMap;
import org.mozilla.javascript.NativeObject;

public class Audio {
	private String id; //file name of the audio file
	private String startAt; //time to start audio at
	private String stopAt; //time to stop audio at
	private String target; //page to go to when the audio finishes
	private String set; //flags to set when the audio finishes
	private String unSet; //flags to unset when the audio finishes
	private String repeat; //number of times to repeat the audio
	private String ifSet; //only play the audio if theses flags are set
	private String ifNotSet; //don't play the audo if these flags are set
	private String jscript; //javascript function to run on audio finish
	private LocalTime ifBefore; //Time of day must be before this time
	private LocalTime ifAfter; //Time of day must be after this time
	private ComonFunctions comonFunctions = ComonFunctions.getComonFunctions();
	private String scriptVar;
	private int volume;  //integer between 0 and 100 
	

	public Audio(String id, String startAt, String stopAt, String target, String ifSet, String ifNotSet, String set, String unSet, String repeat, String jscript, String ifAfter, String ifBefore, String scriptVar, int volume) {
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

	public Audio(NativeObject params)
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
		if (Objects.equals(this.repeat, ""))
			this.repeat = processor.getString("loops");
		this.jscript = processor.getScript();

		this.ifBefore = processor.getTime("ifBefore");
		this.ifAfter = processor.getTime("ifAfter");

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
		temp.put("ifBefore", new HashParam(HashParam.Type.TIME, null));
		temp.put("ifAfter", new HashParam(HashParam.Type.TIME, null));
		temp.put("scriptVar", new HashParam(""));
		temp.put("volume", new HashParam(HashParam.Type.INTEGER, 100));
		return temp;
	}

	public String getId() {
		return id;
	}

	public String getStartAt() {
		return startAt;
	}

	public String getStopAt() {
		return stopAt;
	}

	public String getTarget() {
		return target;
	}
	
	//pass the current flags and check if we can play this audio
	public boolean canShow(ArrayList<String> setList) {
		boolean retVal = comonFunctions.canShowTime(ifBefore, ifAfter);
		if (retVal) {
			retVal =  comonFunctions.canShow(setList, ifSet, ifNotSet);
		}
		return retVal;
	}

	//pass the current flags and do the set / unset on it
	public void setUnSet(ArrayList<String> setList) {
		comonFunctions.SetFlags(set, setList);
		comonFunctions.UnsetFlags(unSet, setList);
	}

	public String getRepeat() {
		return repeat;
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
