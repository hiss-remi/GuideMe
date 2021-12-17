package org.guideme.guideme.util;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.graphics.Color;
import org.mozilla.javascript.NativeObject;

import java.util.Map;

public class HashCommandProcessor {

    private Map<String, Object> args = new CaseInsensitiveMap();
    private Map<String, HashParam> mapper;
    private static Logger logger = LogManager.getLogger();

    public HashCommandProcessor(Map<String, HashParam> mapper)
    {
        this.mapper = mapper;
    }


    public void parse(NativeObject input)
    {
        for (Object id: input.getIds()) {
            String key =  id.toString();
            if (mapper.containsKey(key))
            {
                args.put(key, mapper.get(key).parse(input.get(key)));
            }
            else
            {
                throw new IllegalArgumentException("HashCommandProcessor: " + key + " is not a valid parameter for this function.");
            }
        }
    }

    public String getString(String key, boolean useDefault)
    {
        logger.debug(key);
        if (args.containsKey(key))
            return (String) args.get(key);
        else if (useDefault)
            return (String) mapper.get(key).getStock();
        else
            return null;
    }

    public String getString(String key) { return getString(key, true); }

    public Integer getInt(String key, boolean useDefault)
    {
        if (args.containsKey(key))
            return (Integer) args.get(key);
        else if (useDefault)
            return (Integer) mapper.get(key).getStock();
        else
            return null;
    }

    public Integer getInt(String key)
    {
        return getInt(key, true);
    }

    public Double getNum(String key, boolean useDefault)
    {
        if (args.containsKey(key))
            return (Double) args.get(key);
        else if (useDefault)
            return (Double) mapper.get(key).getStock();
        else
            return null;
    }

    public Double getNum(String key)
    {
        return getNum(key, true);
    }

    public Color getColor(String key, boolean useDefault)
    {
        if (args.containsKey(key))
            return (Color) args.get(key);
        else if (useDefault)
            return (Color) mapper.get(key).getStock();
        else
            return null;
    }

    public Color getColor(String key)
    {
        return getColor(key, true);
    }

    public Boolean getBool(String key, boolean useDefault)
    {
        if (args.containsKey(key))
            return (Boolean) args.get(key);
        else if (useDefault)
            return (Boolean) mapper.get(key).getStock();
        else
            return null;
    }

    public Boolean getBool(String key) { return getBool(key, true); }
}
