package com.ntels.ccbs.charge.service.batchprocmng.billupdmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.billupdmng.BillMastVO;
import com.ntels.ccbs.charge.domain.batchprocmng.billupdmng.MthCtrtUpymStusVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.billupdmng.BillMastMapper;
import com.ntels.ccbs.charge.service.batchprocmng.billupdmng.BillMastService;

/**
 * 
 * <PRE>
 * 1. ClassName: BillMastServiceImpl
 * 2. FileName : BillMastServiceImpl.java
 * 3. Package  : com.ntels.ccbs.charge.service.batchprocmng.billupdmng
 * 4. Comment  : 청구마스터, 월계약미납현황 테이블 변경
 * 5. 작성자   : pkt
 * 6. 작성일   : 2017. 12. 3. 오후 6:40:11
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		com :	2017. 12. 3.	: 신규 개발.
 * </PRE>
 */
@Service
public class BillMastServiceImpl implements BillMastService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BillMastMapper billMastMapper;

	/**
	 * 청구마스터, 월계약미납현황 테이블 변경 처리한다.
	 * 
	 * @param soId-사업ID,
	 *            billYymm-청구월, pymAcntId-납부계정ID, workId-작업자ID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void billMastUpdate(String soId, String billYymm, String billSeqNo, String pymAcntId, String workId) throws Exception {
		List<BillMastVO> billMastList = new ArrayList<BillMastVO>();

		// 청구마스터 List 를 조회한다.
		logger.debug("====================================");
		logger.debug("  getForBillMastUpdt Start...  ");
		logger.debug("====================================");

		billMastList = billMastMapper.getForBillMastUpdt(soId, billYymm, billSeqNo, pymAcntId);

		int loopCnt1 = billMastList.size();

		for (int i = 0; i < loopCnt1; i++) {
			BillMastVO billMastInfo = new BillMastVO();

			billMastInfo = billMastList.get(i);

			// 청구내역 Update
			logger.debug("====================================");
			logger.debug("  updateBillMast Start...  ");
			logger.debug("====================================");
			billMastInfo.setChgrId(workId);
			billMastInfo.setChgDate(new java.sql.Timestamp(new java.util.Date().getTime()));

			int updCnt1 = billMastMapper.updateBillMast(billMastInfo);

			if (updCnt1 < 0) {
				logger.debug("FAIL UPDATE TBLIV_BILL_MAST");
				logger.debug("청구마스터  변경 실패");
				throw new Exception("FAIL UPDATE TBLIV_BILL_MAST");
			}
			logger.debug("====================================");
			logger.debug("  updateBillMast End...  ");
			logger.debug("====================================");

			// 연선납(03)일 경우 청구선납청구적용 테이블의 수납여부를 Update
//			if (billMastInfo.getPymCycl().equals("03")) {
//				logger.debug("====================================");
//				logger.debug("  updateprepdBillAply Start...  ");
//				logger.debug("====================================");
//
//				int updCnt11 = billMastMapper.updateprepdBillAply(billMastInfo);
//
//				if (updCnt11 < 0) {
//					logger.debug("FAIL UPDATE TBLIV_BILL_PREPD_BILL_APLY");
//					logger.debug("청구선납청구적용  변경 실패");
//					throw new Exception("FAIL UPDATE TBLIV_BILL_PREPD_BILL_APLY");
//				}
//			}
		}

//		List<MthCtrtUpymStusVO> mthCtrtUpymStusList = new ArrayList<MthCtrtUpymStusVO>();
//
//		// 월계약미납현황 List 를 조회한다.
//		logger.debug("====================================");
//		logger.debug("  getForMthCtrtUpymStusUpdt Start...  ");
//		logger.debug("====================================");
//		// 20180706.해당납부계정의 월계약미납현황의 최종청구월을 가져오게 변경
//		// String chkBillYymm = billMastMapper.getMaxYymm(soId); /* 최종월의 데이터로 변경하여 처리한다. */
//		String chkBillYymm = billMastMapper.getMaxUpymYymm(soId, pymAcntId); /* 최종월의 데이터로 변경하여 처리한다. */
//		mthCtrtUpymStusList = billMastMapper.getForMthCtrtUpymStusUpdt(soId, chkBillYymm, pymAcntId);
//
//		int loopCnt2 = mthCtrtUpymStusList.size();
//
//		for (int i = 0; i < loopCnt2; i++) {
//			MthCtrtUpymStusVO mthCtrtUpymStusInfo = new MthCtrtUpymStusVO();
//
//			mthCtrtUpymStusInfo = mthCtrtUpymStusList.get(i);
//
//			// 월계약미납현황 Update
//			logger.debug("====================================");
//			logger.debug("  updateMthCtrtUpymStus Start...  ");
//			logger.debug("====================================");
//			mthCtrtUpymStusInfo.setChgrId(workId);
//			mthCtrtUpymStusInfo.setChgDate(new java.sql.Date(new java.util.Date().getTime()));
//
//			int updCnt2 = billMastMapper.updateMthCtrtUpymStus(mthCtrtUpymStusInfo);
//
//			if (updCnt2 < 0) {
//				logger.debug("FAIL UPDATE MTH_CTRT_UPYM_STUS");
//				logger.debug("월계약미납현황  변경 실패");
//				throw new Exception("FAIL UPDATE MTH_CTRT_UPYM_STUS");
//			}
//			logger.debug("====================================");
//			logger.debug("  updateMthCtrtUpymStus End...  ");
//			logger.debug("====================================");
//		}
	}
}
