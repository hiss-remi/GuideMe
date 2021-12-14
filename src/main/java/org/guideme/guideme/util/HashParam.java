package org.guideme.guideme.util;

import org.eclipse.swt.graphics.Color;
import org.guideme.guideme.settings.ComonFunctions;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;

public class HashParam {

    public enum Type {
        INTEGER,
        NUMBER,
        NUMSTRING, //Numbers that are expected/required to be in a string later. (Does this ever behave differently than string?)
        STRING,
        COLOR,
        RANGE, //A special case of string - used for timers that can randomize within a range.
        BOOLEAN
    }

    private Type type;
    private Object stock; //"default", except default is a reserved word.

    private ComonFunctions comonFunctions = ComonFunctions.getComonFunctions();

    public HashParam(Type type, Object stock) {
        this.type = type;
        this.stock = stock;
    }

    public HashParam(Object stock) {
        this.type = Type.STRING;
        this.stock = stock;
    }

    public Object getStock() {
        return stock;
    }

    public Object parse(Object obj) {
        if (type == Type.NUMSTRING || type == Type.STRING)
            return Context.jsToJava(obj, String.class);
        else if (type == Type.INTEGER)
            return Context.jsToJava(obj, Integer.class);
        else if (type == Type.NUMBER)
            return Context.jsToJava(obj, Double.class);
        else if (type == Type.COLOR) {
            String color = (String) Context.jsToJava(obj, String.class);
            if (color == "")
                return stock;
            else if (color.startsWith("#"))
                return comonFunctions.decodeHexColor(color);
            else
                return comonFunctions.getColor(color.toLowerCase(), (Color) stock);
        } else if (type == Type.RANGE) {
            if (obj instanceof NativeArray) {
                NativeArray arr = (NativeArray) obj;
                return Context.jsToJava(arr.get(0), String.class) + ".." + Context.jsToJava(arr.get(1), String.class);
            } else
                return Context.jsToJava(obj, String.class);
        } else if (type == Type.BOOLEAN)
            return Context.jsToJava(obj, Boolean.class);
        else
            //Should never happen, but if all the branches are not closed off the compiler will scream.
            throw new IllegalArgumentException("HashParam parse error: " + type + "is not supported.");
    }
}
