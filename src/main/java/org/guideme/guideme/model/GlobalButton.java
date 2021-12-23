package org.guideme.guideme.model;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.guideme.guideme.util.HashCommandProcessor;
import org.guideme.guideme.util.HashParam;
import org.mozilla.javascript.NativeObject;

import java.lang.reflect.GenericDeclaration;
import java.util.Map;

public class GlobalButton extends Button {
    private Placement placement = Placement.BOTTOM;
    private Action action = Action.NONE;
    private boolean hidden = false;

    public GlobalButton(String id, String target, String text) {
        super(target, text, "", "", "", "", "", "", "", "", "", "", "", "", 1, "", "", false, id, "", false);
    }

    public GlobalButton(String id, String target, String text, Placement placement, Action action) {
        super(target, text, "", "", "", "", "", "", "", "", "", "", "", "", 1, "", "", false, id, "", false);
        this.placement = placement;
        this.action = action;
    }

    public GlobalButton(String id, String target, String text, String ifSet, String ifNotSet, String set, String unSet, String jScript, String image, String hotKey, Placement placement, Action action) {
        super(target, text, ifSet, ifNotSet, set, unSet, jScript, image, hotKey, "", "", "", "", "", 1, "", "", false, id, "", false);
        this.placement = placement;
        this.action = action;
    }

    public GlobalButton(String id, String target, String text, String ifSet, String ifNotSet, String set, String unSet, String jScript, String image, String hotKey, int sortOrder, Placement placement, Action action) {
        super(target, text, ifSet, ifNotSet, set, unSet, jScript, image, hotKey, "", "", "", "", "", sortOrder, "", "", false, id, "", false);
        this.placement = placement;
        this.action = action;
    }

    public GlobalButton(String id, String target, String text, String ifSet, String ifNotSet, String set, String unSet, String jScript, String image, String hotKey, String fontName, String fontHeight, String fontColor, String bgColor1, String bgColor2, int sortOrder, String ifAfter, String ifBefore, boolean disabled, String scriptVar, boolean defaultBtn, Placement placement, Action action) {
        super(target, text, ifSet, ifNotSet, set, unSet, jScript, image, hotKey, fontName, fontHeight, fontColor, bgColor1, bgColor2, sortOrder, ifAfter, ifBefore, disabled, id, scriptVar, defaultBtn);
        this.placement = placement;
        this.action = action;
    }

    public GlobalButton(NativeObject params, Action action) {
        this.action = action;
        HashCommandProcessor processor = new HashCommandProcessor(mapper);
        processor.parse(params);
        processParams(processor);
        Object temp = processor.getEnum("placement");
        if (temp != null)
            this.placement = (Placement) temp;
        temp = processor.getEnum("action");
        if (temp != null)
            this.action = (Action) temp;
    }

    public void merge(GlobalButton changeOrder)
    {
        //if (changeParams != null)
            merge(changeOrder.changeParams);
    }

    protected void merge(HashCommandProcessor processor)
    {
        super.merge(processor);
        Object temp = processor.getEnum("placement");
        if (temp != null)
            this.placement = (Placement) temp;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public enum Placement {
        TOP,
        BOTTOM
    }

    public enum Action {
        ADD,
        REMOVE,
        CHANGE,
        HIDE,
        SHOW,
        NONE
    }
}
