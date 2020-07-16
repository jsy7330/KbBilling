package com.ntels.ccbs.charge.mapper.batchprocmng.autotransmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlivBillFileCrtVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00CommVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiAgreDataVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiApplReqVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpy00EdiApplVO;
import com.ntels.ccbs.charge.domain.batchprocmng.autotransmng.NBlpyRcptFileBatVO;

@Component
public interface NBlpy102Mapper {

	/**
	 * EDI 신청 파일 생성을 위한 대상 리스트를 조회한다.
	 * @param
	 * @return List<NBlpy00EdiApplReqVO>
	 */
	List<NBlpy00EdiApplReqVO> getEdiApplFileList(@Param("nBlpy00CommVO") NBlpy00CommVO nBlpy00Comm);

	/**
	 * EDI 신청 동의자료 정보를 조회한다.
	 * @param
	 * @return NBlpy00EdiAgreDataVO
	 */
	NBlpy00EdiAgreDataVO getEdiApplAgreData(@Param("ediApplSeqNo") String ediApplSeqNo);

	/**
	 * EDI 신청 요청 테이블에 신청 요청 처리 상태를 UPDATE 한다.
	 * @param nBlpy00EdiApplReqVO
	 * @return
	 */
	int updateEdiApplReqProcStat(@Param("nBlpy00EdiApplReqVO") NBlpy00EdiApplReqVO nBlpy00EdiApplReqVO);

	/**
	 * EDI 신청 접수 내역의 신청 처리 상태를 변경한다.
	 * @param nBlpy00EdiApplVO
	 * @return
	 */
	int updateEdiApplProcStat(@Param("nBlpy00EdiApplVO") NBlpy00EdiApplVO nBlpy00EdiApplVO);

	/**
	 * 수납 파일 배치 처리 결과 테이블에 UPDATE 한다.
	 * @param nBlpyRcptFileBatVO
	 * @return
	 */
	int updateRcptFileBatProcRslt(@Param("nBlpyRcptFileBatVO") NBlpyRcptFileBatVO nBlpyRcptFileBatVO);

	/**
	 * 파일 생성 정보에 등록한다.
	 * @param nBlivBillFileCrtVO
	 * @return
	 */
	int insertBillFileCrt(@Param("nBlivBillFileCrtVO") NBlivBillFileCrtVO nBlivBillFileCrtVO);
}
