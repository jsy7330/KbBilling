package com.ntels.ifg.httpRest.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <pre>
 * Interface 표준전문에 사용되는 VO.
 * Response 를 표현하며, DataSet과 함께 message의 header 부분을 표현한다.
 * </pre>
 * <b> - XML Sample</b>
 * <xmp>
 * <IfResponse>
 *     <messageId>SAMPLE000000001</messageId>
 *     <caller>SAMPLE</caller>
 *     <applicationId>applicationId</applicationId>
 *     <serviceId>TVLECG0002</serviceId>
 *     <timestamp>20100423181500</timestamp>
 *     <dataSet>
 *         <data name="AUTH_YN">
 *             <value><![CDATA[Y]]></value>
 *         </data>
 *         <data name="CORP_STAT_CD">
 *             <value><![CDATA[1]]></value>
 *         </data>
 *     </dataSet>
 * </IfResponse>
 * </xmp>
 * 
 * @author JH YUN
 */
@XmlRootElement(name="IfResponse")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlCDATASupport({"value"})
@XmlType(name = "", propOrder = {
	    "messageId",
	    "caller",
	    "hashKey",
	    "applicationId",
	    "serviceId",
	    "timestamp",
	    "dataSet",
	    "error"
	})
public class ResponseVO extends BaseVO{


    /**
	 * 
	 */
    private static final long serialVersionUID = -243857741375001753L;

	/** The message id. */
    @XmlElement(name="messageId")
    private String messageId;
    
    /** The caller. */
    @XmlElement(name="caller")
    private String caller;
    
    /** The hash key. */
    @XmlElement(name="hashKey")
    private String hashKey;
    
    /** The application id. */
    @XmlElement(name="applicationId")
    private String applicationId;
    
    /** The service id. */
    @XmlElement(name="serviceId")
    private String serviceId;
    
    /** The timestamp. */
    @XmlElement(name = "timestamp")
    private String timestamp;
    
    /** The data set. */
    @XmlElement(name = "dataSet")
    private DataSetVO dataSet;
    
    /** The error. */
    @XmlElement(name="error")
    private ErrorVO error;

    /**
     * Gets the message id.
     * 
     * @return the message id
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the message id.
     * 
     * @param messageId the new message id
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets the caller.
     * 
     * @return the caller
     */
    public String getCaller() {
        return caller;
    }

    /**
     * Sets the caller.
     * 
     * @param caller the new caller
     */
    public void setCaller(String caller) {
        this.caller = caller;
    }

    /**
     * Gets the hash key.
     * 
     * @return the hash key
     */
    public String getHashKey() {
        return hashKey;
    }

    /**
     * Sets the hash key.
     * 
     * @param hashKey the new hash key
     */
    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    /**
     * Gets the application id.
     * 
     * @return the application id
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Sets the application id.
     * 
     * @param applicationId the new application id
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Gets the service id.
     * 
     * @return the service id
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the service id.
     * 
     * @param serviceId the new service id
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    
    /**
     * Gets the timestamp.
     * 
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     * 
     * @param timestamp the new timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the data set.
     * 
     * @return the data set
     */
    public DataSetVO getDataSet() {
        return dataSet;
    }

    /**
     * Sets the data set.
     * 
     * @param dataSet the new data set
     */
    public void setDataSet(DataSetVO dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * Gets the error.
     * 
     * @return the error
     */
    public ErrorVO getError() {
        return error;
    }

    /**
     * Sets the error.
     * 
     * @param error the new error
     */
    public void setError(ErrorVO error) {
        this.error = error;
    }
}
