package com.ntels.ccbs.common.consts;

/**
 * @brief 작업 타입 정의 
 * 
 * <PRE>
 * 1. ClassName: WorkTypeDefinition
 * 2. FileName : WorkTypeDefinition.java
 * 3. Package  : com.ntels.ccbs.system.domain.common
 * 4. Comment  :
 * 5. 작성자   : smyun@ntels.com
 * 6. 작성일   : 2014. 4. 28. 오후 1:33:17
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		smyun@ntels.com :	2014. 4. 28.	: 신규 개발.
 * </PRE>
 */
public interface WorkTypeDefinition {

	/** 검색. */
    static final String WORK_TYPE_SEARCH  = "0";
    
    /** 입력. */
    static final String WORK_TYPE_INSERT  = "1";
    
    /** 삭제. */
    static final String WORK_TYPE_DELETE  = "2";
    
    /** 변경. */
    static final String WORK_TYPE_UPDATE  = "3";
    
    /** 페이지 이동. */
    static final String WORK_TYPE_MOVE_PAGE   = "4";
}
