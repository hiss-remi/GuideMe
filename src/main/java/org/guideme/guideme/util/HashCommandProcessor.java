package org.guideme.guideme.util;

import org.guideme.guideme.settings.ComonFunctions;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;

import java.util.HashMap;

public class HashCommandProcessor {
    public enum Type {
        INTEGER,
        //NUMBER,
        NUMSTRING, //Numbers that are expected/required to be in a string later. (Does this ever behave differently than string?)
        STRING,
        COLOR,
        RANGE
    }

    public class HashParam {

        private Type type;
        private Object stock; //"default", except default is a reserved word.

        private ComonFunctions comonFunctions = ComonFunctions.getComonFunctions();

        public HashParam(Type type, Object stock)
        {
            this.type = type;
            this.stock = stock;
        }

        public HashParam(Object stock)
        {
            this.type = Type.STRING;
            this.stock = stock;
        }

        public Object getStock() { return stock; }

        public Object parse(Object obj) {
            if (type == Type.NUMSTRING || type == Type.STRING)
                return Context.jsToJava(obj, String.class);
            else if (type == Type.INTEGER)
                return Context.jsToJava(obj, Integer.class);
            else if (type == Type.COLOR)
            {
                String color = (String) Context.jsToJava(obj, String.class);
                if (color == "")
                    return stock;
                else if (color.startsWith("#"))
                    return comonFunctions.decodeHexColor(color);
                else
                    return comonFunctions.getColor(color.toLowerCase(), (org.eclipse.swt.graphics.Color) stock);
            }
            else if (type == Type.RANGE)
            {
                if (obj instanceof NativeArray) {
                    NativeArray arr = (NativeArray) obj;
                    return Context.jsToJava(arr.get(0), String.class) + ".." + Context.jsToJava(arr.get(1), String.class);
                }
                else
                    return Context.jsToJava(obj, String.class);
            }
            else
                //Should never happen, but if all the branches are not closed off the compiler will scream.
                throw new IllegalArgumentException("HashParam parse error: " + type + "is not supported.");
        }

        /*public int parseInt(Object obj)
        {
            if (obj instanceof Byte || obj instanceof Short || obj instanceof Integer)
                return (int) obj;
            else if (obj instanceof String)
            {
                try {
                    return parseInt(obj);
                }
                catch (NumberFormatException e)
                {
                    throw new IllegalArgumentException("String '" + obj.toString() + "' is not valid input for an integer parameter.");
                }
            }
            else if (obj instanceof Double)
            {

            }
            return -1;
        }*/


    }


    private HashMap<String, Object> args = new HashMap();
    private HashMap<String, HashParam> mapper;

    private void parse(NativeObject input, HashMap<String, HashParam> mapper)
    {
        for (Object id: input.getIds()) {
            String key =  id.toString();
            if (mapper.containsKey(key))
            {
                args.put(key, mapper.get(key).parse(input.get(key)));
            }
        }
        this.mapper = mapper;
    }

    public String getString(String key, boolean useDefault)
    {
        if (args.containsKey(key))
            return (String) args.get(key);
        else if (useDefault)
            return (String) mapper.get(key).getStock();
        else
            return null;
    }

    public String getString(String key)
    {
        return getString(key, true);
    }
}
