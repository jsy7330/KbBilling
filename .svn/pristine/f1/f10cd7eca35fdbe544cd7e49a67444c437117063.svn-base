package com.ntels.ccbs.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter
  extends XmlAdapter<KeyValuePair[], Map<String, String>>
{
  public KeyValuePair[] marshal(Map<String, String> v)
    throws Exception
  {
    if (v == null) {
      return null;
    }
    List<KeyValuePair> list = new ArrayList<KeyValuePair>();
    for (Iterator<String> it = v.keySet().iterator(); it.hasNext();)
    {
      //String key = (String)it.next();
      String key = String.valueOf(it.next());
      //String value = (String)v.get(key);
      String value = String.valueOf(v.get(key));
      list.add(new KeyValuePair(key, value));
    }
    return (KeyValuePair[])list.toArray(new KeyValuePair[0]);
  }
  
  public Map<String, String> unmarshal(KeyValuePair[] v)
    throws Exception
  {
	  Map<String, String> contentMap = new HashMap<String, String>();
    KeyValuePair[] arrayOfKeyValuePair;
    int j = (arrayOfKeyValuePair = v).length;
    for (int i = 0; i < j; i++)
    {
      KeyValuePair pair = arrayOfKeyValuePair[i];
      contentMap.put(pair.getKey(), pair.getValue());
    }
    return contentMap;
  }
}
