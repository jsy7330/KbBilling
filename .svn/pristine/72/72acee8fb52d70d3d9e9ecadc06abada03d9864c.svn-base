package com.ntels.ccbs.common.exception;

import com.ntels.nisf.util.message.MessageUtil;

public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4161950322046867885L;

	/**
	 * 입력변수.
	 */
	private Object[] args = null;
	
	/**
	 * 메세지ID
	 */
	private String messageId = null;

	public Object[] getArgs() {
		return args;
	}
	
	/**
	 * 프로퍼티 파일의 키를 지정한다.
	 *
	 * @param propKey String 메세지 프로퍼티 키
	 * @param messageId String 메세지ID
	 */
	public AppException(String propKey, String messageId) {
		super(MessageUtil.getMessage(propKey));
		this.messageId = messageId;
	}

	/**
	 * 프로퍼티 파일의 키, arg를 지정한다.
	 *
	 * @param propKey String 메세지 프로퍼티 키
	 * @param messageId String 메세지ID
	 * @param args Object[] 추가 인자
	 */
	public AppException(String propKey, String messageId,  Object[] args) {
		super(MessageUtil.getMessage(propKey, args));
		this.args = args;
		this.messageId = messageId;
	}
	
	/**
	 * 프로퍼티 파일의 키와 내포할 예외를 지정한다.
	 *
	 * 프로퍼티 파일의 템플릿 문자열 처리시 첫번째(그리고 단 하나의) arg는 t.getMessage()가 된다.
	 *
	 * @param propKey String 메세지 프로퍼티 키
	 * @param messageId String 메세지ID
	 * @param t Throwable
	 */
	public AppException(String propKey, String messageId, Throwable t) {
		super(MessageUtil.getMessage(propKey), t);
		this.args = new Object[]{t.getMessage()};
		this.messageId = messageId;
	}
	
	/**
	 * 프로퍼티 파일의 키, arg 배열, 내포할 예외를 지정한다.
	 *
	 * 프로퍼티 파일의 템플릿 문자열 처리시 Object[] args가 순서대로 들어가는데 더하여 
	 * 마지막 arg로 t.getMessage()를 추가해준다.
	 * @param propKey String 메세지 프로퍼티 키
	 * @param messageId String 메세지ID
	 * @param args Object[]
	 * @param t Throwable
	 */
	public AppException(String propKey, String messageId, Object[] args, Throwable t) {
		super(MessageUtil.getMessage(propKey), t);
		
		this.args = new Object[args.length + 1];
		System.arraycopy(args, 0, this.args, 0, args.length);
		this.args[args.length] = t.getMessage();
		this.messageId = messageId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
