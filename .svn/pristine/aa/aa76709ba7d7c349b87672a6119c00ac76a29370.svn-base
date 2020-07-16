package com.ntels.ifg.httpRest.domain;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ntels.ccbs.common.util.MapAdapter;

/**
 * <pre>
 * Interface 표준전문에 사용되는 VO.
 * 전문에서 주고 받는 데이터를 표현할때 사용되는 VO로서 아래와 같은 유형의 데이터를 표현할 수 있다.
 * </pre>
 * <ul>
 * <li>Value type</li>
 * <li>List type</li>
 * <li>Map type</li>
 * <li>MapList type</li>
 * </ul><br/>
 * 
 * <b> - Value Type Sample XML</b>
 * <xmp>
 * <data name="PDT_ID">
 *     <value>0001_lite</value>
 * </data>
 * </xmp>
 * <b> - List Type Sample XML</b>
 * <xmp>
 * <data name="supportingCharsets">
 *     <list>
 *         <value>euc-kr</value>
 *         <value>utf-8</value>
 *         <value>ms949</value>
 *     </list>
 * </data>
 * </xmp>
 * <b> - Map Type Sample XML</b>
 * <xmp>
 * <data name="accountInfo">
 *     <map>
 *         <item key="bankNm">
 *             <value>국민</value>
 *         </item>
 *         <item key="accountNo">
 *             <value>1234567890</value>
 *         </item>
 *     </map>
 * </data>
 * </xmp>
 * <b> - MapList Type Sample XML</b>
 * <xmp>
 * <data name="REC">
 *     <maplist>
 *         <map>
 *             <item key="REG_SEQ">
 *                 <value>2860</value>
 *             </item>
 *             <item key="CHG_TIME">
 *                 <value>164740</value>
 *             </item>
 *             <item key="BANK_CD">
 *                 <value>99</value>
 *             </item>
 *             <item key="CHG_DATE">
 *                 <value>20100224</value>
 *             </item>
 *         </map>
 *         <map>
 *             <item key="REG_SEQ">
 *                 <value>2860</value>
 *             </item>
 *             <item key="CHG_TIME">
 *                 <value>164740</value>
 *             </item>
 *             <item key="BANK_CD">
 *                 <value>99</value>
 *             </item>
 *             <item key="CHG_DATE">
 *                 <value>20100224</value>
 *             </item>
 *         </map>
 *     </maplist>
 * </data> 
 * </xmp>
 * 
 * @author SangcheolRoh, HP
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataVO extends BaseVO{
    

    /**
	 * 
	 */
    private static final long serialVersionUID = 5325937220848522179L;

	/** The name. */
    @XmlAttribute(name = "name")
    private String name;
    
    /** The value. */
    @XmlElement(name = "value")
    private String value;
    
    /** The list. */
    @XmlElementWrapper(name = "list")
    @XmlElement(name = "value")
    private List<String> list;
    
    /** The map. */
    @XmlElement(name = "map")
    @XmlJavaTypeAdapter(value=MapAdapter.class)
    private Map<String, String> map;
    
    /** The map list. */
    @XmlElementWrapper(name = "maplist")
    @XmlElement(name = "map")
    @XmlJavaTypeAdapter(value=MapAdapter.class)
    private List<Map<String, String>> mapList;

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     * 
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the list.
     * 
     * @return the list
     */
    public List<String> getList() {
        return list;
    }

    /**
     * Sets the list.
     * 
     * @param list the new list
     */
    public void setList(List<String> list) {
        this.list = list;
    }

    /**
     * Gets the map.
     * 
     * @return the map
     */
    public Map<String, String> getMap() {
        return map;
    }

    /**
     * Sets the map.
     * 
     * @param map the map
     */
    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    /**
     * Gets the map list.
     * 
     * @return the map list
     */
    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    /**
     * Sets the map list.
     * 
     * @param mapList the map list
     */
    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

}
