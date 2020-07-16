package com.ntels.ccbs.system.service.bulletin.bulletinMng.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.common.util.FileUtil;
import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.mapper.bulletin.bulletinMng.BulletinMapper;
import com.ntels.ccbs.system.service.bulletin.bulletinMng.BulletinMngService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class BulletinMngServiceImpl implements BulletinMngService {

	/** InquiryHistMapper Autowired. */
	@Autowired
	private BulletinMapper bulletinMapper;
	/** InquiryHist Service  */
	BulletinMngService bulletinMngService;
	@Autowired
	private SequenceService sequenceSevice;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${file.path}")
	private String filePath;
	
	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> list(String sidx, String sord, int page, int rows, String lng, BulletinMngVO bulletin){
		Map<String,Object> bulleintInfo = new HashMap<String,Object>();
		
		Integer totalCount= bulletinMapper.count(bulletin);
		
		if(totalCount.intValue() == 0){
			bulleintInfo.put("totalCount", totalCount);
			bulleintInfo.put("totalPages", new Integer(0));
			bulleintInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<BulletinMngVO> bulletinList = bulletinMapper.list(sidx, sord, start, end, lng,bulletin);
			for(int i = 0;i < bulletinList.size();i++){
				StringBuilder authGrpNm = new StringBuilder();
				StringBuilder authGrpId = new StringBuilder();
				StringBuilder fileInfo = new StringBuilder();
				
				bulletin.setNoticeId(bulletinList.get(i).getNoticeId());
				//권한 정보 조회
				List<BulletinMngVO> authList = bulletinMapper.authList(sidx, sord,bulletin);
				for(int j=0;j<authList.size();j++){

					authGrpId.append(authList.get(j).getAuthGroup());
					authGrpNm.append(authList.get(j).getUserGroupName());
					if(j < authList.size()-1){
						authGrpId.append(",");
						authGrpNm.append(",");
					}
				}
				bulletinList.get(i).setUserGroupId(authGrpId.toString());
				bulletinList.get(i).setUserGroupName(authGrpNm.toString());
				
				//첨부파일 정보 조회
				List<Map<String,Object>> fileList =  bulletinMapper.getFileList(bulletinList.get(i).getNoticeId());
				for(int k = 0; k < fileList.size();k++){
					fileInfo.append(fileList.get(k).get("FILE_NM"));
					fileInfo.append(":");
					fileInfo.append(fileList.get(k).get("FILE_UUID"));
					if(k < fileList.size() -1){
						fileInfo.append(",");
					}
				}
				bulletinList.get(i).setFileNm(fileInfo.toString());
			}
			
			bulleintInfo.put("bulletinList", bulletinList);
			bulleintInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			bulleintInfo.put("totalPages", totalPages);
			bulleintInfo.put("page", new Integer(page));
		}

		return bulleintInfo;
	}

	@Override
	public int insert(BulletinMngVO bulletin,MultipartHttpServletRequest request) {
		SessionUser session_user = (SessionUser) request.getSession().getAttribute("session_user");
		bulletin.setRegrId(session_user.getUserId());
		bulletin.setNoticeId(sequenceSevice.createNewSequence(Consts.SEQ_CODE.SEQ_TSYCO_NOTICE).toString());	
		bulletin.setSysToDate(DateUtil.sysdate());
       
		int result=bulletinMapper.insert(bulletin);
		
		String[] groupSize=bulletin.getUserGroupId().split(",");
		
		for(int i=0;i<groupSize.length;i++){
			bulletin.setUserGroupId(groupSize[i]);
			bulletin.setAuthId(sequenceSevice.createNewSequence(Consts.SEQ_CODE.SEQ_TSYCO_NOTICE_AUTH).toString());	
			bulletinMapper.insertAuth(bulletin);
		}
		
		List<MultipartFile> multipartFile = request.getFiles("fileName");
		
		if(!multipartFile.isEmpty()){
			int fileSeq=1;
			for(MultipartFile mf : multipartFile){
				
				String uuid;
				try {
					if(StringUtils.isEmpty(mf.getOriginalFilename())){
						continue;
					}
					uuid = FileUtil.writeUploadedFile(filePath,mf);
					logger.debug("ORG FILE NAME \t : {}",mf.getOriginalFilename());
					logger.debug("SERVER PATH \t : {}",filePath);
					logger.debug("UUID \t\t : {}",uuid);
					bulletin.setFileNm(mf.getOriginalFilename());
					bulletin.setFilePath(filePath);
					bulletin.setFileUuid(uuid);
					bulletin.setFileSeq(fileSeq++);
					bulletinMapper.insertFile(bulletin);
				} catch (Exception e) {
					logger.debug("FILE MAKE ERROR \t : {}",e.getMessage());
				}
			}
		}
		
		return result;
	}

	@Override
	public int updateBulletinAction(BulletinMngVO bulletin, MultipartHttpServletRequest request) {
		SessionUser session_user = (SessionUser) request.getSession().getAttribute("session_user");
		bulletin.setChgrId(session_user.getUserId());
		bulletin.setRegrId(session_user.getUserId());
		bulletin.setSysToDate(DateUtil.sysdate());
		int result=bulletinMapper.updateBulletinAction(bulletin);
		
		String[] groupSize=bulletin.getUserGroupId().split(",");
		
		bulletinMapper.deleteAuth(bulletin);
		
		for(int i=0;i<groupSize.length;i++){
			bulletin.setAuthId(sequenceSevice.createNewSequence(Consts.SEQ_CODE.SEQ_TSYCO_NOTICE_AUTH).toString());
			bulletin.setUserGroupId(groupSize[i]);
			bulletinMapper.insertAuth(bulletin);
		}
		
		List<MultipartFile> multipartFile = request.getFiles("fileName");
		
		if(!multipartFile.isEmpty()){
			int fileSeq = bulletinMapper.getMaxFileSeq(bulletin.getNoticeId());
			for(MultipartFile mf : multipartFile){
				
				String uuid;
				try {
					if(StringUtils.isEmpty(mf.getOriginalFilename())){
						continue;
					}
					uuid = FileUtil.writeUploadedFile(filePath,mf);
					logger.debug("ORG FILE NAME \t : {}",mf.getOriginalFilename());
					logger.debug("SERVER PATH \t : {}",filePath);
					logger.debug("UUID \t\t : {}",uuid);
					bulletin.setFileNm(mf.getOriginalFilename());
					bulletin.setFilePath(filePath);
					bulletin.setFileUuid(uuid);
					bulletin.setFileSeq(++fileSeq);
					bulletinMapper.insertFile(bulletin);
				} catch (Exception e) {
					logger.debug("FILE MAKE ERROR \t : {}",e.getMessage());
				}
			}
		}
		
		
		return result;
	}


	@Override
	public int deleteBulletin(BulletinMngVO bulletin) {
		int result=bulletinMapper.deleteBulletin(bulletin);
		bulletinMapper.deleteAuth(bulletin);
		
		List<Map<String,Object>> fileList =  bulletinMapper.getFileList(bulletin.getNoticeId());
		for(Map<String,Object> file : fileList){
			File deleteFile = new File((String)file.get("FILE_PATH") + (String)file.get("FILE_UUID"));
			
			try{
				deleteFile.delete();
			}catch(Exception e){
				logger.error("File Delete Error : " + (String)file.get("FILE_PATH") + (String)file.get("FILE_UUID"));
			}finally {
				deleteFile = null;
			}
			bulletinMapper.deleteFile(bulletin.getNoticeId(), (String)file.get("FILE_UUID"));
		}
		
		
		return result;
	}

	@Override
	public String bulletinDeleteFile(String uuid, String noticeId) {
		
		Map<String,Object> targetFileInfo = bulletinMapper.getFileInfo(noticeId, uuid);
		
		if(targetFileInfo != null && !StringUtils.isEmpty((String)targetFileInfo.get("FILE_NM"))){
			File deleteFile = new File((String)targetFileInfo.get("FILE_PATH") + uuid);
			
			try{
				deleteFile.delete();
			}catch(Exception e){
				logger.error("File Delete Error : " + (String)targetFileInfo.get("FILE_PATH") + uuid);
			}finally {
				deleteFile = null;
			}
			bulletinMapper.deleteFile(noticeId, uuid);
		}
		 
		List<Map<String,Object>> fileList =  bulletinMapper.getFileList(noticeId);
		StringBuilder fileInfo = new StringBuilder();
		for(int k = 0; k < fileList.size();k++){
			fileInfo.append(fileList.get(k).get("FILE_NM"));
			fileInfo.append(":");
			fileInfo.append(fileList.get(k).get("FILE_UUID"));
			if(k < fileList.size() -1){
				fileInfo.append(",");
			}
		}
		
		return fileInfo.toString();
	}

}