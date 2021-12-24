package org.guideme.guideme.util;

import org.apache.commons.collections.map.AbstractHashedMap;
import org.apache.commons.collections.map.CaseInsensitiveMap;

public class VeryInsensitiveMap extends CaseInsensitiveMap {
    //Did you ever notice how GuideMe uses dashes to separate words in node parameters, such as if-not-set,
    //while everything else uses camelCase (ifNotSet)?
    //Neither did I. So this map ignores cases and dashes in its keys, in order to keep from having to
    //still write twice the documentation.

    protected Object convertKey(Object key) {
        if (key != null) {
            return key.toString().toLowerCase().replace("-", "");
        } else {
            return AbstractHashedMap.NULL;
        }
    }
}
