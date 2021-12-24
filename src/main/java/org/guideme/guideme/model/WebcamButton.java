package org.guideme.guideme.model;

import org.guideme.guideme.util.HashCommandProcessor;
import org.mozilla.javascript.NativeObject;

public class WebcamButton extends Button {
	
	private String _type;
	private String _destination;
	public WebcamButton(String type, String destination, String target, String text, String ifSet, String ifNotSet, String set, String unSet, String jScript, String image, String hotKey) {
		super(target, text, ifSet, ifNotSet, set, unSet, jScript, image, hotKey);
		SetValues(type, destination);
	}
	
	
	public WebcamButton(String type, String destination, String target, String text, String ifSet, String ifNotSet, String set, String unSet, String jScript, String image, String hotKey, String fontName, String fontHeight, String fontColor, String bgColor1, String bgColor2, int sortOrder, String ifAfter, String ifBefore, boolean disabled, String id, String scriptVar, boolean defaultBtn)
	{
		super(target, text, ifSet, ifNotSet, set, unSet, jScript, image, hotKey, fontName, fontHeight, fontColor, bgColor1, bgColor2, sortOrder, ifAfter, ifBefore, disabled, id, scriptVar, defaultBtn);
		SetValues(type, destination);
	}

	public WebcamButton(NativeObject params)
	{
		HashCommandProcessor processor = new HashCommandProcessor(mapper);
		processor.parse(params);
		processParams(processor);
		SetValues(processor.getString("type"), processor.getString("file"));
	}

	private void SetValues(String type, String destination)
	{
		switch (type.toLowerCase())
		{
			case "capture":
			default:
				_type = "Capture";
				break;
		}
		_destination = destination;
	}
	
	
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_destination() {
		return _destination;
	}
	public void set_destination(String _destination) {
		this._destination = _destination;
	}

}
