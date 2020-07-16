package com.ntels.ccbs.system.domain.main;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * 
 * <PRE>
 * 1. ClassName: InquiryHist
 * 2. FileName : InquifryHist.java
 * 3. Package  : com.ntels.ccbs.system.domain.authorization
 * 4. Comment  :
 * 5. 작성자   : yu_hyung_min
 * 6. 작성일   : 2015. 9. 15. 오후 5:46:58
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     yu_hyung_min :    2015. 9. 15.    : 신규개발
 * </PRE>
 */

@XStreamAlias("inquiry")
public class InquiryHistVO extends PagingValue {
	
	// 공지구분, 공지분류
	private String notice_type_nm;
	private String notice_type;
	private String inquiry_notice_type;
	private String insert_notice_type;
	
	// 공지기간
	private String date_c;

	// 번호
	private String rownum;
	private String rnum;
	// 제목
	private String title;
	// 내용
	private String contents;
	// 첨부파일, 파일
	private String filename;
	private String fileSrc;
	private String orgname;
	private String newname;
	
	private String regip;
	private int count;
	// 공지 시작일
	private String effect_start_dt;
	// 공지 종료일
	private String effect_end_dt;
	
	private String startDate;
	private String startHour;
	private String startMin;

	private String endDate;
	private String endHour;
	private String endMin;
	
	private String regDate;
	private String regHour;
	private String regMin;
	

	private String new_start_date;	
	private String new_end_date;
	// 등록일
	private String reg_dt;

	private String chg_dt;
	
	// 조회수
	private int view_cnt;

	private String commodity;
	
	private String isp;
	
	private String user_id;
	
	private String bulletin_id;
	
    //callback URL
    private String callback;
    //콜백함수??
    private String callback_func;
 
	private String notice_boundary;
	
	private String popup_yn;
	
	private String del_yn;
	
	private String updatecron;
	
	private String search_type;
	private String searchCount;
	private String subscriber;
	private String search_name;
	
	
	private Integer file_seq;
	private String file_path;
	private String file_nm;
	private String orgl_file_nm;
	private String file_yn;
	private CommonsMultipartFile file_uuid;
	
	
	public String getBulletin_id() {
		return bulletin_id;
	}
	public void setBulletin_id(String bulletin_id) {
		this.bulletin_id = bulletin_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getCirculation() {
		return circulation;
	}
	public void setCirculation(String circulation) {
		this.circulation = circulation;
	}
	private String circulation;
	public String separator;
	private String sevletContext;
	
	public String getNotice_type() {
		return notice_type;
	}
	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}
	public String getDate_c() {
		return date_c;
	}
	public void setDate_c(String date_c) {
		this.date_c = date_c;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getEffect_start_dt() {
		return effect_start_dt;
	}
	public void setEffect_start_dt(String effect_start_dt) {
		this.effect_start_dt = effect_start_dt;
	}
	public String getEffect_end_dt() {
		return effect_end_dt;
	}
	public void setEffect_end_dt(String effect_end_dt) {
		this.effect_end_dt = effect_end_dt;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public Integer getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(Integer file_seq) {
		this.file_seq = file_seq;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_nm() {
		return file_nm;
	}
	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}
	public String getOrgl_file_nm() {
		return orgl_file_nm;
	}
	public void setOrgl_file_nm(String orgl_file_nm) {
		this.orgl_file_nm = orgl_file_nm;
	}
	public CommonsMultipartFile getFile_uuid() {
		return file_uuid;
	}
	public void setFile_uuid(CommonsMultipartFile file_uuid) {
		this.file_uuid = file_uuid;
	}
	public String getSevletContext() {
		return sevletContext;
	}
	public void setSevletContext(String sevletContext) {
		this.sevletContext = sevletContext;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getInquiry_notice_type() {
		return inquiry_notice_type;
	}
	public void setInquiry_notice_type(String inquiry_notice_type) {
		this.inquiry_notice_type = inquiry_notice_type;
	}
	public String getNotice_type_nm() {
		return notice_type_nm;
	}
	public void setNotice_type_nm(String notice_type_nm) {
		this.notice_type_nm = notice_type_nm;
	}
	public String getInsert_notice_type() {
		return insert_notice_type;
	}
	public void setInsert_notice_type(String insert_notice_type) {
		this.insert_notice_type = insert_notice_type;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}
	public String getNew_start_date() {
		return new_start_date;
	}
	public void setNew_start_date(String new_start_date) {
		this.new_start_date = new_start_date;
	}
	public String getNew_end_date() {
		return new_end_date;
	}
	public void setNew_end_date(String new_end_date) {
		this.new_end_date = new_end_date;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRegHour() {
		return regHour;
	}
	public void setRegHour(String regHour) {
		this.regHour = regHour;
	}
	public String getRegMin() {
		return regMin;
	}
	public void setRegMin(String regMin) {
		this.regMin = regMin;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getCallback_func() {
		return callback_func;
	}
	public void setCallback_func(String callback_func) {
		this.callback_func = callback_func;
	}
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	public String getNotice_boundary() {
		return notice_boundary;
	}
	public void setNotice_boundary(String notice_boundary) {
		this.notice_boundary = notice_boundary;
	}
	public String getPopup_yn() {
		return popup_yn;
	}
	public void setPopup_yn(String popup_yn) {
		this.popup_yn = popup_yn;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getUpdatecron() {
		return updatecron;
	}
	public void setUpdatecron(String updatecron) {
		this.updatecron = updatecron;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(String searchCount) {
		this.searchCount = searchCount;
	}
	public String getFileSrc() {
		return fileSrc;
	}
	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}
	public String getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}
	
	public String getSearch_name() {
		return search_name;
	}
	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getChg_dt() {
		return chg_dt;
	}
	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}
	public String getFile_yn() {
		return file_yn;
	}
	public void setFile_yn(String file_yn) {
		this.file_yn = file_yn;
	}
	
}
