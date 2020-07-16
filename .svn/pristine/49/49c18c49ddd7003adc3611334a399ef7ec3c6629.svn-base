package com.ntels.ifg.httpRest.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Value Object의 최상위 클래스<br>
 * equals, hasCode, toString는 apache commons-lang API의 Builder를 사용해 Override 됨.
 * 
 * @author JH YUN
 * @version 1.0
 */
public abstract class BaseVO implements Serializable {

	/**
	 * 
	 */
    private static final long serialVersionUID = -1096783577299562469L;

	/**
     * Equals.
     * 
     * @param obj the obj
     * 
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
    
    /**
     * Hash code.
     * 
     * @return the int
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    /**
     * To string.
     * 
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
