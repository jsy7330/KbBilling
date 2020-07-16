package com.ntels.ccbs.charge.service.batchprocmng.autotransmng;

import java.util.List;

import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.MakeBillFileVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlivBillFileCrtVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00CommVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiAgreDataVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiApplReqVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpyRcptFileBatVO;

public interface NBlpy102Service {
	
	/**
	 * EDI 신청 파일 생성을 위한 대상 리스트를 조회한다.
	 * @param nBlpy00CommVO
	 * @return NBlpy00EdiApplReqVO
	 */
	List<NBlpy00EdiApplReqVO> getEdiApplFileList(NBlpy00CommVO nBlpy00CommVO);
	
	/**
	 * EDI 신청 동의자료 정보를 조회한다.
	 * @param
	 * @return NBlpy00EdiAgreDataVO
	 */
	NBlpy00EdiAgreDataVO getEdiApplAgreData(String ediApplSeqNo);
	
	/**
	 * EDI 신청 요청 테이블에 신청 요청 처리 상태를 UPDATE 한다.
	 * @param ediApplReqInfo
	 * @return
	 */
	int updateEdiApplReqProcStat(NBlpy00EdiApplReqVO ediApplReqInfo);

	/**
	 * 생성된 파일 정보를 수납 배치 파일 정보에 UPDATE 한다.
	 * @param batProcNo, procDt, rcptFileTp, totCnt
	 * @return
	 */
	int updateFileBatchInfo(NBlpyRcptFileBatVO nBlpyRcptFileBatVO) throws Exception;

	/**
	 * EDI 신청 접수 내역의 신청 처리 상태를 변경한다.-단건
	 * @param ediApplInfo
	 * @return
	 */
	int updateEdiApplProcStat(NBlpy00EdiApplVO ediApplInfo);

	/**
	 * 파일 생성 정보에 등록한다.
	 * @param nBlivBillFileCrtVO
	 * @return
	 */
	int insertBillFileCrt(NBlivBillFileCrtVO nBlivBillFileCrtVO);

	String makeFilePath(MakeBillFileVO makeBillFileVO);

	String makeFileName(MakeBillFileVO makeBillFileVO);

	MakeBillFileVO makeOnelineFile(MakeBillFileVO makeBillFileVO) throws Exception;

	MakeBillFileVO makeOnelineFile(MakeBillFileVO makeBillFileVO, String encoding) throws Exception;
}
