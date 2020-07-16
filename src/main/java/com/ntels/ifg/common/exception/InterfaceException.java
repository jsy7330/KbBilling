
package com.ntels.ifg.common.exception;

import com.ntels.nisf.util.message.MessageUtil;

public class InterfaceException extends RuntimeException {
    private static final long serialVersionUID = -7047602654088901219L;
	private String code = null;
	
    /**
     * 생성자
     * @param code 오류코드 메시지명
     * @param message 오류메시지 메세지명
     * @param args 오류메시지 입력값
     */
    public InterfaceException(String code, Object... args) {
        super(MessageUtil.getMessage(code, args));
        this.code = code;
    }

    /**
     * 생성자
     * @param code 오류코드 메시지명
     * @param message 오류메시지 메세지명
     */
    public InterfaceException(String code) {
        super(MessageUtil.getMessage(code));
        this.code = code;
    }

    /**
     * 오류코드를 돌려준다.
     * @return 오류코드를 돌려준다.
     */
    public String getCode() {
        return code;
    }


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("******************** InterfaceException ");
		sb.append("[code=");
		sb.append(code);
		sb.append(", message=");
		sb.append(this.getLocalizedMessage());
		sb.append("]");
		return sb.toString();
	}
    
    
}
