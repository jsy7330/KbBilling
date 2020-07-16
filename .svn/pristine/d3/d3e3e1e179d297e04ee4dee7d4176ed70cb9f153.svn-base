package com.ntels.ccbs.system.domain.common.service;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 사용자.
 * 
 * @author smyun@ntels.com
 */
@XStreamAlias("user")
public class User extends PagingValue {
	
	/** 사용자 ID. */
	@NotEmpty
	@Length(min=4,max=20)
	private String user_id;
	
	/** 비밀번호. */
	private String password;
	
	/** 비밀번호 확인. */
	private String passwordRe;
	
	/** 사용자 명. */
	@NotEmpty
	private String user_name;
	
	/** 사용자 그룹 ID. */
	@NotEmpty
	private String user_group_id;
	
	/** 사용자 그룹 명. */
	private String user_group_name;
	
	/** 사용자 부서 명. */
	private String dept_name;
	
	/** 사용자 전화번호. */
	private String tel_no;
	
	/** 사용자 Email. */
	@Email
	private String e_mail;
	
	/** 직원번호. */
	private String emp_no;
	
	/** IP 대역. */
	@NotEmpty
	private String ip_bandwidth;
	
	/** 로그인 실패 횟수. */
	private int    login_fail_count = 0;
	
	/** 비밀번호 만료 일자. */
	@NotEmpty
	private String password_due_date;
	
	/** 비밀번호 변경 기간. */
	@NotEmpty
	private String password_change_period = "30";
	
	/** 최종 로그인 일자. */
	private String last_login_date;
	
	/** 최종 로그인 시간. */
	private String last_login_time;
	
	/** 계좌 잠김 여부. */
	private String account_lock;

	/** 로그인 IP. */
	private String login_gateway_ip;
	
	/** 변경 사용자 그룹. */
	private String user_group_id_c;

	
	/** 비밀번호. */
	private String password1;
	
	/** 비밀번호 확인.*/
	private String password2;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRe() {
		return passwordRe;
	}

	public void setPasswordRe(String passwordRe) {
		this.passwordRe = passwordRe;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_group_id() {
		return user_group_id;
	}

	public void setUser_group_id(String user_group_id) {
		this.user_group_id = user_group_id;
	}

	public String getUser_group_name() {
		return user_group_name;
	}

	public void setUser_group_name(String user_group_name) {
		this.user_group_name = user_group_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getTel_no() {
		return tel_no;
	}

	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getIp_bandwidth() {
		return ip_bandwidth;
	}

	public void setIp_bandwidth(String ip_bandwidth) {
		this.ip_bandwidth = ip_bandwidth;
	}

	public int getLogin_fail_count() {
		return login_fail_count;
	}

	public void setLogin_fail_count(int login_fail_count) {
		this.login_fail_count = login_fail_count;
	}

	public String getPassword_due_date() {
		return password_due_date;
	}

	public void setPassword_due_date(String password_due_date) {
		this.password_due_date = password_due_date;
	}

	public String getPassword_change_period() {
		return password_change_period;
	}

	public void setPassword_change_period(String password_change_period) {
		this.password_change_period = password_change_period;
	}

	public String getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}

	public String getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLogin_gateway_ip() {
		return login_gateway_ip;
	}

	public void setLogin_gateway_ip(String login_gateway_ip) {
		this.login_gateway_ip = login_gateway_ip;
	}

	public String getAccount_lock() {
		return account_lock;
	}

	public void setAccount_lock(String account_lock) {
		this.account_lock = account_lock;
	}

	public String getUser_group_id_c() {
		return user_group_id_c;
	}

	public void setUser_group_id_c(String user_group_id_c) {
		this.user_group_id_c = user_group_id_c;
	}
	
	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", password=" + password
				+ ", passwordRe=" + passwordRe + ", user_name=" + user_name
				+ ", user_group_id=" + user_group_id + ", user_group_name="
				+ user_group_name + ", dept_name=" + dept_name + ", tel_no="
				+ tel_no + ", e_mail=" + e_mail + ", emp_no=" + emp_no
				+ ", ip_bandwidth=" + ip_bandwidth + ", login_fail_count="
				+ login_fail_count + ", password_due_date=" + password_due_date
				+ ", password_change_period=" + password_change_period
				+ ", last_login_date="
				+ last_login_date + ", last_login_time=" + last_login_time
				+ ", login_gateway_ip=" + login_gateway_ip + "]";
	}
	

}
