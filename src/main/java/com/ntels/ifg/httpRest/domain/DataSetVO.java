package com.ntels.ifg.httpRest.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * <pre>
 * Interface 표준전문에 사용되는 VO.
 * 전문에서 주고 받는 데이터를 표현할때 사용되는 DataVO를 List 형으로 가지고 있다.
 * </pre>
 * <b> - XML Sample</b>
 * <xmp>
 * <dataSet>
 *     <data name="PDT_ID">
 *         <value>0001_lite</value>
 *     </data>
 *     <data name="SRH_DATE">
 *         <value>20100224</value>
 *     </data>
 * </dataSet>
 * </xmp>
  * @author JH YUN
 */
@XmlRootElement(name = "dataSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataSetVO extends BaseVO {
    
    
    /**
	 * 
	 */
    private static final long serialVersionUID = -7489585661458832831L;
	/** The datas. */
    @XmlElement(name="data")
    private List<DataVO> datas;
    
    /**
     * Gets the datas.
     * 
     * @return the datas
     */
    public List<DataVO> getDatas() {
        return datas;
    }
    
    /**
     * Sets the datas.
     * 
     * @param datas the new datas
     */
    public void setDatas(List<DataVO> datas) {
        this.datas = datas;
    }
}
