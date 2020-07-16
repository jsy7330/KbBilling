package com.ntels.ccbs.common.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class KeyValuePair
{
  @XmlAttribute(name="key")
  private String key;
  @XmlElement(name="value")
  private String value;
  
  public KeyValuePair() {}
  
  public KeyValuePair(String key, String value)
  {
    this.key = key;
    this.value = value;
  }
  
  public String getKey()
  {
    return this.key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setValue(String value)
  {
    this.value = value;
  }
}
