package org.guideme.guideme.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.guideme.guideme.settings.ComonFunctions;
import org.guideme.guideme.util.HashCommandProcessor;
import org.guideme.guideme.util.HashParam;
import org.mozilla.javascript.NativeObject;

public class Timer {
	private String delay;
	private String jScript;
	private Calendar timerEnd;
	private String imageId; //file name of image
	private String text; //text to display on right
	private String ifSet;
	private String ifNotSet;
	private String set;
	private String unSet;
	private LocalTime ifBefore; //Time of day must be before this time
	private LocalTime ifAfter; //Time of day must be after this time
	private String id;
	private String target;
	private String repeat = null;
	private ComonFunctions comonFunctions = ComonFunctions.getComonFunctions();
	
	public Timer(String delay, String jScript) {
		this(delay, jScript, "", "", "", "", "", "", "", "", "", "");
	}

	public Timer(String delay, String jScript, String imageId, String text, String ifSet, String ifNotSet, String set, String unSet, String ifAfter, String ifBefore, String id)
	{
		this(delay, jScript, imageId, text, ifSet, ifNotSet, set, unSet, ifAfter, ifBefore, id, "");
	}
	
	public Timer(String delay, String jScript, String imageId, String text, String ifSet, String ifNotSet, String set, String unSet, String ifAfter, String ifBefore, String id, String target) {
		this.delay = delay;
		this.jScript = jScript;
		this.imageId = imageId;
		this.text = text;
		this.ifSet = ifSet;
		this.ifNotSet  =ifNotSet;
		this.set = set;
		this.unSet = unSet;
		this.target = target;
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
		if (id.equals("")) {
			this.id = java.util.UUID.randomUUID().toString();
		} else {
			this.id = id;
		}
	}

	public Timer(NativeObject params) {
		HashCommandProcessor processor = new HashCommandProcessor(mapper);
		processor.parse(params);
		this.delay = processor.getString("delay");
		this.imageId = processor.getString("imageId");
		this.text = processor.getString("text");
		this.target = processor.getString("target");
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

		this.setRepeat(processor.getString("repeat"));
	}

	protected static Map<String, HashParam> mapper = createMapper();

	protected static Map<String, HashParam> createMapper() {
		Map<String, HashParam> temp = new CaseInsensitiveMap();
		temp.put("delay", new HashParam(HashParam.Type.RANGE, ""));
		temp.put("javaScript", new HashParam(""));
		temp.put("jScript", new HashParam(""));
		temp.put("script", new HashParam(""));
		temp.put("imageId", new HashParam(""));
		temp.put("text", new HashParam(""));
		temp.put("ifNotSet", new HashParam(""));
		temp.put("ifSet", new HashParam(""));
		temp.put("set", new HashParam(""));
		temp.put("unset", new HashParam(""));
		temp.put("target", new HashParam(""));
		temp.put("ifBefore", new HashParam(""));
		temp.put("ifAfter", new HashParam(""));
		temp.put("id", new HashParam(""));
		temp.put("repeat", new HashParam(HashParam.Type.RANGE, ""));
		return temp;
	}

	public int getTimerMSec() { return (int) (comonFunctions.getRandomDouble(delay) * 1000); }

	public String getjScript() {
		return jScript;
	}

	public Calendar getTimerEnd() {
		return timerEnd;
	}

	public void setTimerEnd(Calendar timerEnd) {
		this.timerEnd = timerEnd;
	}

	public String getImageId() {
		return imageId;
	}

	public String getText() {
		return text;
	}

	public String getIfSet() {
		return ifSet;
	}

	public String getIfNotSet() {
		return ifNotSet;
	}

	public String getTarget() {
		return target;
	}

	public boolean canShow(ArrayList<String> setList) {
		boolean retVal = comonFunctions.canShowTime(ifBefore, ifAfter);
		if (retVal) {
			retVal =  comonFunctions.canShow(setList, ifSet, ifNotSet);
		}
		return retVal;
	}

	public String getSet() {
		return set;
	}

	public String getUnSet() {
		return unSet;
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

	public String getId() {
		return id;
	}

	public void setRepeat(String repeat) {
		if (repeat == "") {
			this.repeat = null;
		} else {
			this.repeat = repeat;
		}
	}

	public String getRepeat() { return repeat; }

	public boolean isRepeating() { return repeat != null;}

	public int getRepeatMSec() {
		if (this.repeat == null) {
			return Integer.MAX_VALUE;
		}
		else {
			return (int) (comonFunctions.getRandomDouble(repeat) * 1000);
		}
	}

}