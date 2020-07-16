package com.ntels.ccbs.charge.domain.batchprocmng.autotransmng;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MakeBillFileVO {
		
	private String fileGubun;
	private String filePath;
	private String fileName;
	private String fullName;
	private String fileCt;
	private List<String> arrFileCt;
	private long fileSize;
	private boolean isSuccess;
	
	public String getFileGubun() {
		return fileGubun;
	}
	public void setFileGubun(String fileGubun) {
		this.fileGubun = fileGubun;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileCt() {
		return fileCt;
	}
	public void setFileCt(String fileCt) {
		this.fileCt = fileCt;
	}
	public List<String> getArrFileCt() {
		return arrFileCt;
	}
	public void setArrFileCt(List<String> arrFileCt) {
		this.arrFileCt = arrFileCt;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
	@Override
	public String toString() {
		return "BillFileEntity [fileGubun=" + fileGubun + ", filePath=" + filePath + ", fileName=" + fileName + ", fileCt=" + fileCt + ", arrFileCt=" + arrFileCt + ", fileSize="
				+ fileSize + ", isSuccess=" + isSuccess + "]";
	}

	
    
}
