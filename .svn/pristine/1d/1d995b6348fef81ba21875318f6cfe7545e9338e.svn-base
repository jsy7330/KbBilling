package com.ntels.ifg.httpRest.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * Interface 표준전문에서 Error 나타내는 VO.
 * </pre>
 * <b> - XML Sample</b>
 * <xmp>
 * <error>
 *     <code>error code</code>
 *     <message>error message</message>
 * </error>
 * </xmp>
 * 
 * @author JH YUN
 */
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorVO extends BaseVO{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 5474478890111452897L;

	/** The code. */
    @XmlElement
    private String code;
    
    /** The message. */
    @XmlElement
    private String message;
    
    /**
     * Gets the code.
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Sets the code.
     * 
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Gets the message.
     * 
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Sets the message.
     * 
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    

}
