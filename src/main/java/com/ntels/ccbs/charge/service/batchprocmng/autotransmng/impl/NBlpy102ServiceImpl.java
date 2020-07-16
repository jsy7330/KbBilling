package com.ntels.ccbs.charge.service.batchprocmng.autotransmng.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.MakeBillFileVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlivBillFileCrtVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00CommVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiAgreDataVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiApplReqVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpyRcptFileBatVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.autotransmng.NBlpy102Mapper;
import com.ntels.ccbs.charge.service.batchprocmng.autotransmng.NBlpy102Service;
import com.ntels.nisf.util.StringUtil;

@Service
public class NBlpy102ServiceImpl implements NBlpy102Service {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${file.path.billing}") 
	private String file_path_billing;	

	@Autowired
	private NBlpy102Mapper nBlpy102Mapper;

	/**
	 * EDI 신청 파일 생성을 위한 대상 리스트를 조회한다.
	 * @param
	 * @return NBlpy00EdiApplReqVO
	 */
	@Override
	public List<NBlpy00EdiApplReqVO> getEdiApplFileList(NBlpy00CommVO nBlpy00CommVO) {
		return nBlpy102Mapper.getEdiApplFileList(nBlpy00CommVO);
	}

	/**
	 * EDI 신청 동의자료 정보를 조회한다.
	 * @param
	 * @return NBlpy00EdiAgreDataVO
	 */
	@Override
	public NBlpy00EdiAgreDataVO getEdiApplAgreData(String ediApplSeqNo) {
		return nBlpy102Mapper.getEdiApplAgreData(ediApplSeqNo);
	}

	/**
	 * EDI 출금 요청 테이블에 출금 요청 처리 상태를 UPDATE 한다.
	 * @param ediWtdrawReqInfo
	 * @return
	 */
	@Override
	public int updateEdiApplReqProcStat(NBlpy00EdiApplReqVO ediApplReqInfo) {
		return nBlpy102Mapper.updateEdiApplReqProcStat(ediApplReqInfo);
	}

	/**
	 * 생성된 파일 정보를 수납 배치 파일 정보에 UPDATE 한다.
	 * @param nBlpyRcptFileBatVO, inptMenuId, workId
	 * @return
	 */
	@Override
	public int updateFileBatchInfo(NBlpyRcptFileBatVO nBlpyRcptFileBatVO) throws Exception {

		int procFlag = 1;

		int updCnt = nBlpy102Mapper.updateRcptFileBatProcRslt(nBlpyRcptFileBatVO);
		if(updCnt <= 0) {
			procFlag = -1;
			throw new Exception("FAIL UPDATE TBLPY_RCPT_FILE_BAT_PROC_RSLT");
		}

		return procFlag;
	}

	/**
	 * EDI 신청 접수 내역의 신청 처리 상태를 변경한다.-단건
	 * @param ediApplInfo
	 * @return
	 */
	@Override
	public int updateEdiApplProcStat(NBlpy00EdiApplVO ediApplInfo) {
		return nBlpy102Mapper.updateEdiApplProcStat(ediApplInfo);
	}

	/**
	 * 파일 생성 정보에 등록한다.
	 * @param nBlivBillFileCrtVO
	 * @return
	 */
	@Override
	public int insertBillFileCrt(NBlivBillFileCrtVO nBlivBillFileCrtVO) {
		return nBlpy102Mapper.insertBillFileCrt(nBlivBillFileCrtVO);
	}

	@Override
	public String makeFilePath(MakeBillFileVO makeBillFileVO) {
		
		logger.debug("file_path_billing>>>>>>>>>"+file_path_billing);
		
		StringBuffer sb = new StringBuffer();
		sb.append(file_path_billing);	
		
		String gubun = "";
		if(makeBillFileVO != null && makeBillFileVO.getFileGubun() != null) {
			gubun = makeBillFileVO.getFileGubun();
		}
				
		if(!StringUtil.isEmpty(gubun) && "01".equals(gubun) ){ 		
			sb.append("giro/"); //지로
		}else if(!StringUtil.isEmpty(gubun) && "02".equals(gubun) ){ 		
			sb.append("edi/");  //EDI	
		}else {  			
			sb.append("etc/"); //기타
		}
		
		//디렉토리가 존재하지 않을 경우 새로 생성
		File file = new File(sb.toString());
		if(!file.isDirectory()) {
			file.mkdir();
		}		
		
		return sb.toString();
	}	

	@Override
	public String makeFileName(MakeBillFileVO makeBillFileVO) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(makeBillFileVO.getFileName());
		//sb.append("."+makeBillFileVO.getFileExtn());
		return sb.toString();
	}		

	@Override
	public MakeBillFileVO makeOnelineFile(MakeBillFileVO makeBillFileVO) throws Exception {
		return makeOnelineFile(makeBillFileVO, "EUC-KR");
	}

	@Override
	public MakeBillFileVO makeOnelineFile(MakeBillFileVO makeBillFileVO, String encoding) throws Exception {

		StringBuffer sb = new StringBuffer("");

		String filePath = makeFilePath(makeBillFileVO);
		String fileName = makeFileName(makeBillFileVO);
		String fullName = filePath + fileName;
		
		System.out.println("filePath>>>>>"+filePath);
		System.out.println("fileName>>>>>"+fileName);
		System.out.println("fullName>>>>>"+fullName);

		List<String> list = makeBillFileVO.getArrFileCt();
		String oriStr = null;
		if(list==null || list.size()==0) {
//			sb.append(makeBillFileVO.getFileCt());
			// 원본문자열에 존재하는 '?'를 ' '으로 치환한다.
			// unicode에만 존재하는 문자열을 '?'로 만드는 encodeing의 특성을 이용해서, encoding된 결과에서 '?'를 '　'으로 치환하는것으로 2byte체계를 유지
			oriStr = makeBillFileVO.getFileCt();
			oriStr = oriStr.replaceAll("\\?", " ");
			oriStr = new String(oriStr.getBytes(encoding), encoding);
	        oriStr = oriStr.replaceAll("\\?", "　");

	        sb.append(oriStr);
		} else {
			for (int i = 0; i < list.size(); i++) {
//				sb.append(list.get(i));
				// 원본문자열에 존재하는 '?'를 ' '으로 치환한다.
				// unicode에만 존재하는 문자열을 '?'로 만드는 encodeing의 특성을 이용해서, encoding된 결과에서 '?'를 '　'으로 치환하는것으로 2byte체계를 유지
				oriStr = list.get(i);
				oriStr = oriStr.replaceAll("\\?", " ");
		        oriStr = new String(oriStr.getBytes(encoding), encoding);
		        oriStr = oriStr.replaceAll("\\?", "　");
		        sb.append(oriStr);
			}
		}

		FileOutputStream fos = null;
		try {
			byte[] byteArr = sb.toString().getBytes(encoding);

			fos = new FileOutputStream(fullName, true);
			fos.write(byteArr);
		} finally {
			if(fos != null) {
				fos.close();
			}
		}

		//파일 생성 여부		
		File rsFile = new File(fullName);

		MakeBillFileVO rsEntity = makeBillFileVO;
		rsEntity.setSuccess(rsFile.exists());
		rsEntity.setFileSize(rsFile.length());
		rsEntity.setFilePath(filePath);
		rsEntity.setFileName(fileName);		
		rsEntity.setFullName(fullName);

		return rsEntity;
	}
}
