package com.ntels.ccbs.distribute.service.logistics.voucherMgt.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.crypto.AES256Cipher;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.ChargeMngVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.RchrgHistVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VeqtVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.mapper.logistics.voucherMgt.ChargeMngMapper;
import com.ntels.ccbs.distribute.mapper.logistics.voucherMgt.VoucherGenerateMapper;
import com.ntels.ccbs.distribute.service.logistics.voucherMgt.ChargeMngService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ChargeMngServiceImpl implements ChargeMngService {

	@Autowired
	private ChargeMngMapper chargeMngMapper;
	
	@Autowired
	private VoucherGenerateMapper voucherGenerateMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	private String convertTelNo(String telNo) {
		
		if (StringUtils.isEmpty(telNo) == true) {
			return telNo;
		}
		
		if (telNo.length() == 8) {
			return telNo.substring(0, 4) + "-" + telNo.substring(4, 8);
		}
		
		if (telNo.length() == 15) {
			return telNo;
		}
		
		if (telNo.length() > 8) {
			String[] temp = telNo.split("-");
			
			if (temp.length > 2) {
				return telNo;
			}
			
			return telNo.substring(0, 4) + "-" + telNo.substring(4, 7) + "-" + telNo.substring(7, telNo.length());
		} else {
			return telNo;
		}
	}
	
	@Override
	public int getCtrtInfoListCount(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true) {
			return 0;
		}
		
		if (StringUtils.isEmpty(chargeMngVO.getCustId()) == true) {
			return 0;
		}
		
		Integer count = chargeMngMapper.getCtrtInfoListCount(chargeMngVO);
		return count == null ? 0 : count;
	}
	
	@Override
	public List<ChargeMngVO> getCtrtInfoList(ChargeMngVO chargeMngVO) {
		
		int start = (chargeMngVO.getPage() -1 ) * chargeMngVO.getPerPage();
		int end = chargeMngVO.getPerPage();
		
		return chargeMngMapper.getCtrtInfoList(chargeMngVO, start, end);
	}
	
	@Override
	public int getCustInfoListCount(ChargeMngVO chargeMngVO) {

		// 검색조건이 없다면 검색하지 않는다. 에외 발생없이 검색결과 없음으로 돌린다.
		if (StringUtils.isEmpty(chargeMngVO.getCustNm()) == true && StringUtils.isEmpty(chargeMngVO.getCustId()) == true
				&& StringUtils.isEmpty(chargeMngVO.getSvcTelNo()) == true && StringUtils.isEmpty(chargeMngVO.getAcntNo()) == true
				&& StringUtils.isEmpty(chargeMngVO.getCorpRegNo()) == true && StringUtils.isEmpty(chargeMngVO.getRepNm()) == true
				&& StringUtils.isEmpty(chargeMngVO.getCustTp()) == true && StringUtils.isEmpty(chargeMngVO.getPymAcntId()) == true
				&& StringUtils.isEmpty(chargeMngVO.getCtrtId()) == true) {
			return 0;
		}
		
		if (StringUtils.isEmpty(chargeMngVO.getSvcTelNo()) == false) {
			// 서비스 번호로 검색
			// 이동전화번호로 CUST_ID를 가져와서 검색조건에 추가한다
			String custId = chargeMngMapper.getCustIdBySvcTelNo(chargeMngVO.getSvcTelNo());
			chargeMngVO.setSvcTelNo(custId);
		}
		
		if (StringUtils.isEmpty(chargeMngVO.getCtrtId()) == false) {
			// 계약으로 검색
			String custId = chargeMngMapper.getCustIdByCtrtId(chargeMngVO.getCtrtId());
			chargeMngVO.setCtrtId(custId);
		}
		
		if (StringUtils.isEmpty(chargeMngVO.getCorpRegNo()) == false) {
			// 주민/법인번호 암호화
			try {
				chargeMngVO.setCorpRegNo(AES256Cipher.AES_Encode(chargeMngVO.getCorpRegNo(), Consts.ENCODE_KEY));	
			} catch (Exception e) {
				throw new ServiceException("MSG.M15.MSG00001");
			}
		}
		
		if (StringUtils.isEmpty(chargeMngVO.getAcntNo()) == false) {
			// 계좌번호 암호화
			try {
				chargeMngVO.setAcntNo(AES256Cipher.AES_Encode(chargeMngVO.getAcntNo(), Consts.ENCODE_KEY));
			} catch (Exception e) {
				throw new ServiceException("MSG.M15.MSG00001");
			}
		}
		
		Integer count = chargeMngMapper.getCustInfoListCount(chargeMngVO);
		
		return count == null ? 0 : count;
	}
	
	@Override
	public List<ChargeMngVO> getCustInfoList(ChargeMngVO chargeMngVO) {
		
		int start = (chargeMngVO.getPage() -1 ) * chargeMngVO.getPerPage();
		int end = chargeMngVO.getPerPage();
		
		List<ChargeMngVO> custInfoList = chargeMngMapper.getCustInfoList(chargeMngVO, start, end);
		
		for (ChargeMngVO custInfo : custInfoList) {
			if (StringUtils.isEmpty(custInfo.getSvcTelNo()) == false) {
				custInfo.setSvcTelNo(convertTelNo(custInfo.getSvcTelNo()));
			}
			
			if (StringUtils.isEmpty(custInfo.getCorpRegNo()) == false) {
				// 주민/법인번호 암호화 해제
//				try {
//					custInfo.setCorpRegNo(AES256Cipher.AES_Decode(custInfo.getCorpRegNo(), Consts.ENCODE_KEY));
//				} catch (Exception e) {
//					throw new ServiceException("MSG.M15.MSG00001");
//				}
			}
		}
		
		return custInfoList;
	}
	
	@Override
	public int getChrgHistListCount(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true || StringUtils.isEmpty(chargeMngVO.getCtrtId()) == true) {
			return 0;
		}

		Integer count = chargeMngMapper.getChrgHistListCount(chargeMngVO);
		return count == null ? 0 : count;
	}
	
	@Override
	public List<ChargeMngVO> getChrgHistList(ChargeMngVO chargeMngVO) {
		
		int start = (chargeMngVO.getPage() -1 ) * chargeMngVO.getPerPage();
		int end = chargeMngVO.getPerPage();
		
		List<ChargeMngVO> chrgHistList = chargeMngMapper.getChrgHistList(chargeMngVO, start, end);
		
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
		
		for (ChargeMngVO chrgHist : chrgHistList) {
			try {
				Date rchrgDt = yyyyMMdd.parse(chrgHist.getRchrgDt());
				Calendar cal = Calendar.getInstance();
				cal.setTime(rchrgDt);
				cal.add(Calendar.MONTH, 6);
				cal.add(Calendar.DATE, -1);
				
				Date rchrgDt6m = cal.getTime();
				
				chrgHist.setRchrgDt6m(yyyyMMdd.format(rchrgDt6m));
				
				if (StringUtils.isEmpty(chrgHist.getRchrgChgDt()) == true) {
					cal = Calendar.getInstance();
					cal.setTime(rchrgDt);
					int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					cal.set(Calendar.DATE, lastDay);
					
					String rchrgChgDt = yyyyMMdd.format(cal.getTime());
					chrgHist.setRchrgChgDt(rchrgChgDt);
				}
			} catch (Exception e) {
				throw new ServiceException("MSG.M15.MSG00001");
			}
		}
		
		return chrgHistList;
	}
	
	@Override
	public int getRmnAmt(ChargeMngVO chargeMngVO) {
		
		String todayYM = new SimpleDateFormat("yyyyMM").format(new Date());
		chargeMngVO.setTodayYM(todayYM);
		
//		Integer amt = chargeMngMapper.getRmnAmt(chargeMngVO);
		Integer amt = 500;
		
		return amt == null ? 0 : amt;
	}
	
	@Override
	public ChargeMngVO getPreBasicProdYnInfo(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true
				|| StringUtils.isEmpty(chargeMngVO.getCtrtId()) == true) {
			return null;
		}
		
		Date now = new Date();
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
		
		String todayDt = yyyyMMddFormat.format(now);
		chargeMngVO.setTodayDt(todayDt);
		
		List<ChargeMngVO> preBasicProdInfoList = chargeMngMapper.getPreBasicProdInfo(chargeMngVO);
		
		if (preBasicProdInfoList.isEmpty() == true) {
			return null;
		}
		
		ChargeMngVO preBasicProdInfo = preBasicProdInfoList.get(0);
		
		Calendar cal = Calendar.getInstance();
		
		String termDt = null;
		String nextTermDt = null;
		
		if (StringUtils.isEmpty(preBasicProdInfo.getOpenDd()) == false) {
			
			Date openDdDate = null;
			
			try {
				openDdDate = yyyyMMddFormat.parse(preBasicProdInfo.getOpenDd());
			} catch (Exception e) {
				throw new ServiceException("MSG.M15.MSG00001");
			}
			
			cal.setTime(openDdDate);
			
			if (StringUtils.isEmpty(preBasicProdInfo.getEffPeriod()) == true) {
				nextTermDt = yyyyMMddFormat.format(openDdDate);
				cal.add(Calendar.DATE, -1);
				
				termDt = yyyyMMddFormat.format(cal.getTime());
			} else {
				int effPeriod = Integer.parseInt(preBasicProdInfo.getEffPeriod());
				cal.add(Calendar.DATE, effPeriod);
				
				nextTermDt = yyyyMMddFormat.format(cal.getTime());
				
				cal.add(Calendar.DATE, -1);
				
				termDt = yyyyMMddFormat.format(cal.getTime());
				
			}
			
			preBasicProdInfo.setTermDt(termDt);
			preBasicProdInfo.setNextTermDt(nextTermDt);
			preBasicProdInfo.setCurDt(todayDt);
		}
		
        int iTermDt = Integer.parseInt(preBasicProdInfo.getTermDt());
        int iCurDt = Integer.parseInt(preBasicProdInfo.getCurDt());
		
        // TERM_DT 세팅

        // Rating Group 구분에 따른 분기(RG_PROD_TP = 'X'인 경우 기존, '1'은 구매가능, '0'은 예약) '1', '0'은 K-Movie, Netflix 등이 해당됨
        // 1 : Volume + K-Movie, 0 : MUP + K-Movie
        // 유효기간 내에서 기본일반상품 잔여량이 소진된 경우(REMAIN_YN = 'N') Lite 및 Lite UL번들은 구매 가능
        // 할인대상 여부에 따른 분기(DISC_YN = 'N'인 경우 기존, 'Y'인 경우 할인대상)

        // 기본상품 종료시점이 현재시점 이후인 경우 (유효기간 존재)
        if (iTermDt > iCurDt) {

            // 기존
            if (preBasicProdInfo.getRgProdTp().equals("X")) {
                // 할인아님 (기존)
                if (preBasicProdInfo.getDiscYn().equals("N")) {
                    // PCRF 아닌 일반상품 디폴트 현재시점 TERM_DT 세팅
                    if (preBasicProdInfo.getPcrfYn().equals("N")) {
                        preBasicProdInfo.setTermDt(preBasicProdInfo.getCurDt());
                    }
                    // PCRF 이며, Lite 아닌 무한상품 (예약) TERM_DT 세팅
                    else if (preBasicProdInfo.getPcrfYn().equals("Y") && preBasicProdInfo.getLiteYn().equals("X")) {
                        preBasicProdInfo.setTermDt(preBasicProdInfo.getNextTermDt());
                    }
                    // PCRF 이며, Lite 상품 종료시점이후 TERM_DT 세팅
                    else if (preBasicProdInfo.getPcrfYn().equals("Y") && preBasicProdInfo.getLiteYn().equals("Y")) {
                        preBasicProdInfo.setTermDt(preBasicProdInfo.getNextTermDt());
                    }
                    // PCRF 이며, Lite UL 상품 종료시점이후 TERM_DT 세팅
                    else if (preBasicProdInfo.getPcrfYn().equals("Y") && preBasicProdInfo.getLiteYn().equals("N")) {
                        preBasicProdInfo.setTermDt(preBasicProdInfo.getNextTermDt());
                    }
                    // 이외..케이스 없음, 디폴트 현재시점
                    else {
                        preBasicProdInfo.setTermDt(preBasicProdInfo.getCurDt());
                    }
                }
                // 할인대상(Speed+Yearly, Small Bundle add-on)
                else if (preBasicProdInfo.getDiscYn().equals("Y")) {
                    // 할인대상은 예약없이 즉구 가능 (디폴트와 같음)
                    preBasicProdInfo.setTermDt(preBasicProdInfo.getCurDt());
                }
            }
            // Volume + K-Movie 바로 구매가능(단 PCRF_ID가 같은 경우)
            else if (preBasicProdInfo.getRgProdTp().equals("1")) {
                preBasicProdInfo.setTermDt(preBasicProdInfo.getCurDt());
            }
            // MUP + K-Movie 예약
            else if (preBasicProdInfo.getRgProdTp().equals("0")) {
                preBasicProdInfo.setTermDt(preBasicProdInfo.getNextTermDt());
            }
            
            String remainYn = chargeMngMapper.getPreBasicRemainInfo(preBasicProdInfo);
            preBasicProdInfo.setRemainYn(remainYn);
            
        } else {
        	preBasicProdInfo.setRemainYn("N");
        }
        
        return preBasicProdInfo;

	}
	
	/**
	 * CCBS 유효기간 내에 번들상품 잔량 유무 조회, (추가 : 번들구매 2개까지, PCRF여부 및 종료일자, 속도저하LITE상품여부)
	 */
	@Override
	public ChargeMngVO getPreBundleProdYnInfo(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true
				|| StringUtils.isEmpty(chargeMngVO.getCtrtId()) == true) {
			return null;
		}
		
		Integer cnt = chargeMngMapper.getPreBundleProdCount(chargeMngVO);
		cnt = cnt == null ? 0 : cnt;
		
		Date now = new Date();
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
		String todayDt = yyyyMMddFormat.format(now);
		
		chargeMngVO.setTodayDt(todayDt);
		
        // 구매건 없음, 번들 구매 가능
        // 기본 일반인 경우 번들 구매시 시작일자는 현재기준, 종료일자는 현재기준 + 유효기간
        // 기본 PCRF인 경우 번들 구매시 시작일자는 기본의 종료일자 다음날기준, 종료일자는 시작일자 + 유효기간
        if (cnt == 0) {
        	ChargeMngVO preBundleProdYnInfo = new ChargeMngVO();
        	preBundleProdYnInfo.setRemainYn("N");
        	preBundleProdYnInfo.setPcrfId("N");
        	preBundleProdYnInfo.setPcrfYn("N");
        	preBundleProdYnInfo.setLiteYn("N");
        	preBundleProdYnInfo.setTermDt(todayDt);
        	preBundleProdYnInfo.setRgProdTp("X");
        	
        	return preBundleProdYnInfo;
        }
        // 구매건 2개이상, 번들 구매 불가능
        else if (cnt >= 2) {
        	ChargeMngVO preBundleProdYnInfo = new ChargeMngVO();
        	preBundleProdYnInfo.setRemainYn("T");
        	preBundleProdYnInfo.setPcrfId("N");
        	preBundleProdYnInfo.setPcrfYn("N");
        	preBundleProdYnInfo.setLiteYn("N");
        	preBundleProdYnInfo.setTermDt(todayDt);
        	preBundleProdYnInfo.setRgProdTp("X");
            
        	return preBundleProdYnInfo;
        }
        // 구매건 1개, 번들 추가 구매 가능
        // 기본 일반, 번들 일반인 경우 번들 추가 구매시 시작일자는 현재기준, 종료일자는 현재기준 + 유효기간
        // 기본 일반, 번들 PCRF인 경우 번들 추가 구매시 시작일자는 번들의 종료일자 다음날기준, 종료일자는 시작일자 + 유효기간
        // 기본 PCRF, 번들 일반인 경우 번들 추가 구매시 시작일자는 기본 PCRF 종료일자 다음날 기준, 종료일자는 시작일자 + 유효기간
        // 기본 PCRF, 번들 PCRF인 경우 번들 추가 구매시 시작일자는 번들 PCEF 종료일자 다음날 기준, 종료일자는 시작일자 + 유효기간
        else {
        	List<ChargeMngVO> preBundleProdInfoList = chargeMngMapper.getPreBundleProdInfo(chargeMngVO);
        	
        	if (preBundleProdInfoList.isEmpty() == true) {
        		return null;
        	}
        	
        	ChargeMngVO preBundleProdInfo = preBundleProdInfoList.get(0);
        	
//            , TO_CHAR(TO_DATE(T2.RCHRG_TERM_DT,'YYYYMMDD') + 1, 'YYYYMMDD') AS NEXT_TERM_DT
//            , TO_CHAR(SYSDATE,'YYYYMMDD') AS CUR_DT
        	preBundleProdInfo.setCurDt(todayDt);
        	
        	Date rchrgTermDt = null;
        	
        	try {
        		rchrgTermDt = yyyyMMddFormat.parse(preBundleProdInfo.getRchrgTermDt());	
        	} catch (Exception e) {
        		throw new ServiceException("MSG.M15.MSG00001");
        	}
        	
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(rchrgTermDt);
        	cal.add(Calendar.DATE, 1);
        	
        	preBundleProdInfo.setNextTermDt(yyyyMMddFormat.format(cal.getTime()));

            // TERM_DT 세팅
            // Rating Group 구분에 따른 분기(RG_PROD_TP = 'X'인 경우 기존, '1'은 구매가능, '0'은 예약) '1', '0'은 K-Movie, Netflix 등이 해당됨
            // 1 : Volume + K-Movie, 0 : MUP + K-Movie
            // 할인대상 여부에 따른 분기(DISC_YN = 'N'인 경우 기존, 'Y'인 경우 할인대상)

            // 기존
            if (preBundleProdInfo.getRgProdTp().equals("X")) {
                // 할인아님 (기존)
                if (preBundleProdInfo.getDiscYn().equals("N")) {
                    // PCRF 아닌 일반상품 디폴트 현재시점 TERM_DT 세팅
                    if (preBundleProdInfo.getPcrfYn().equals("N") && preBundleProdInfo.getLiteYn().equals("X")) {
                        preBundleProdInfo.setTermDt(preBundleProdInfo.getCurDt());
                    }
                    // PCRF 이며, Lite 아닌 무한상품 (예약) TERM_DT 세팅
                    else if (preBundleProdInfo.getPcrfYn().equals("Y") && preBundleProdInfo.getLiteYn().equals("X")) {
                        preBundleProdInfo.setTermDt(preBundleProdInfo.getNextTermDt());
                    }
                    // PCRF 이며, Lite 상품 종료시점이후 TERM_DT 세팅
                    else if (preBundleProdInfo.getPcrfYn().equals("Y") && preBundleProdInfo.getLiteYn().equals("Y")) {
                        preBundleProdInfo.setTermDt(preBundleProdInfo.getNextTermDt());
                    }
                    // PCRF 이며, Lite UL 상품 종료시점이후 TERM_DT 세팅
                    else if (preBundleProdInfo.getPcrfYn().equals("Y") && preBundleProdInfo.getLiteYn().equals("N")) {
                        preBundleProdInfo.setTermDt(preBundleProdInfo.getNextTermDt());
                    }
                    // 이외..케이스 없음, 디폴트 현재시점
                    else {
                        preBundleProdInfo.setTermDt(preBundleProdInfo.getCurDt());
                    }
                }
                // 연차별 할인대상
                else if (preBundleProdInfo.getDiscYn().equals("Y")) {
                    // 연차별 할인대상은 예약없이 즉구 가능 (디폴트와 같음)
                    preBundleProdInfo.setTermDt(preBundleProdInfo.getCurDt());
                }
            }
            // Volume + K-Movie 바로 구매가능(단 PCRF_ID가 같은 경우)
            else if (preBundleProdInfo.getRgProdTp().equals("1")) {
                preBundleProdInfo.setTermDt(preBundleProdInfo.getCurDt());
            }
            // MUP + K-Movie 예약
            else if (preBundleProdInfo.getRgProdTp().equals("0")) {
                preBundleProdInfo.setTermDt(preBundleProdInfo.getNextTermDt());
            }
            
            chargeMngVO.setIfSucYn(preBundleProdInfo.getIfSucYn());
            // 만약 R인 상태의 번들이 있는 경우 REMAIN_YN은 Y로 간주한다.
            String remainYn = chargeMngMapper.getPreBundleRemainInfo(chargeMngVO);
            preBundleProdInfo.setRemainYn(remainYn);

            return preBundleProdInfo;
        }
	}
	
	@Override
	public ChargeMngVO getLatestBundleProdInfo(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true
				|| StringUtils.isEmpty(chargeMngVO.getCtrtId()) == true) {
			return null;
		}
		
		chargeMngVO.setTodayDt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		List<ChargeMngVO> latestBundleProdInfoList = chargeMngMapper.getLatestBundleProdInfo(chargeMngVO);
		
		if (latestBundleProdInfoList.isEmpty() == true) {
			return null;
		}
		
		ChargeMngVO latestBundleProdInfo = latestBundleProdInfoList.get(0);
		
		if (latestBundleProdInfo.getDedtAmt() == null) {
			latestBundleProdInfo.setDedtAmt("0");
		} else {
			double dedtAmt = Double.parseDouble(latestBundleProdInfo.getDedtAmt());
			dedtAmt = Math.round(dedtAmt * 10.0) / 10.0;
			latestBundleProdInfo.setDedtAmt(Double.toString(dedtAmt));
		}
		
		return latestBundleProdInfo;
	}
	
	@Override
	public ChargeMngVO getPromoCurrentInfo(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true
				|| StringUtils.isEmpty(chargeMngVO.getCtrtId()) == true) {
			return null;
		}
		
		Date now = new Date();
		String todayYm = new SimpleDateFormat("yyyyMM").format(now);
		
		String promoDt = chargeMngMapper.getPromoDtInfo(todayYm);
		
		chargeMngVO.setRchrgDt(promoDt);
		
		int purCnt = chargeMngMapper.getPromoPurCntInfo(chargeMngVO);
		
		ChargeMngVO promoCurrentInfo = new ChargeMngVO();
		promoCurrentInfo.setPromoDt(promoDt);
		promoCurrentInfo.setPurCnt(purCnt);
		
		return promoCurrentInfo;
	}
	
	@Override
	public int getNotResPVCount(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true
				|| StringUtils.isEmpty(chargeMngVO.getCtrtId()) == true) {
			return 0;
		}
		
		return chargeMngMapper.getNotResPVCount(chargeMngVO);
	}
	
	@Override
	public int getChrgVeqtListCount(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true) {
			return 0;
		}
		
		String todayDt = new SimpleDateFormat("yyyyMMdd").format(new Date());
		chargeMngVO.setTodayDt(todayDt);
		
		Integer count = chargeMngMapper.getChrgVeqtListCount(chargeMngVO);
		
		return count == null ? 0 : count;
	}
	
	@Override
	public List<ChargeMngVO> getChrgVeqtList(ChargeMngVO chargeMngVO) {
		
		String todayDt = new SimpleDateFormat("yyyyMMdd").format(new Date());
		chargeMngVO.setTodayDt(todayDt);
		
		int start = (chargeMngVO.getPage() -1 ) * chargeMngVO.getPerPage();
		int end = chargeMngVO.getPerPage();
		
		List<ChargeMngVO> chrgVeqtList = chargeMngMapper.getChrgVeqtList(chargeMngVO, start, end);
		
		for (ChargeMngVO chrgVeqt : chrgVeqtList) {
			if (chrgVeqt.getDedtAmt() == null) {
				chrgVeqt.setDedtAmt("0");
			} else {
				double dedtAmt = Double.parseDouble(chrgVeqt.getDedtAmt());
				dedtAmt = Math.round(dedtAmt * 10.0) / 10.0;
				chrgVeqt.setDedtAmt(Double.toString(dedtAmt));
			}
		}
		
		return chrgVeqtList;
	}
	
	@Override
	public int getChrgCtrtListCount(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true || StringUtils.isEmpty(chargeMngVO.getCustId()) == true) {
			return 0;
		}
		
		Integer count = chargeMngMapper.getChrgCtrtListCount(chargeMngVO);
		return count == null ? 0 : count;
	}
	
	@Override
	public List<ChargeMngVO> getChrgCtrtList(ChargeMngVO chargeMngVO) {
		
		int start = (chargeMngVO.getPage() -1 ) * chargeMngVO.getPerPage();
		int end = chargeMngVO.getPerPage();
		
		return chargeMngMapper.getChrgCtrtList(chargeMngVO, start, end);
	}
	
	@Override
	public int getChrgPopProdListCount(ChargeMngVO chargeMngVO) {
		
		if (StringUtils.isEmpty(chargeMngVO.getSoId()) == true
				|| StringUtils.isEmpty(chargeMngVO.getCtrtId()) == true) {
			return 0;
		}
		
		String todayDt = new SimpleDateFormat("yyyyMMdd").format(new Date());
		chargeMngVO.setTodayDt(todayDt);
		
		Integer count = chargeMngMapper.getChrgPopProdListCount(chargeMngVO);
		
		return count == null ? 0 : count;
	}
	
	@Override
	public List<ChargeMngVO> getChrgPopProdList(ChargeMngVO chargeMngVO) {

		int start = (chargeMngVO.getPage() -1 ) * chargeMngVO.getPerPage();
		int end = chargeMngVO.getPerPage();

		return chargeMngMapper.getChrgPopProdList(chargeMngVO, start, end);
	}
	
	@Override
	public void addCharge(ChargeMngVO chargeMngVO) {

		Date now = new Date();
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
        String todayYm = new SimpleDateFormat("yyyyMM").format(now);
        String orderId = null;
        String rcptId = null;
        
        if (StringUtils.isEmpty(chargeMngVO.getCtrtId()) == false) {
        	// New Order Id 채번 조회
        	int orderSeq = sequenceService.createNewSequence("DT013");
        	orderId = String.format("%010d", orderSeq);
        	
        	// New Rcpt Id 채번 조회
        	int rcptSeq = sequenceService.createNewSequence("DT014");
        	rcptId = String.format("%010d", rcptSeq);
        	
    		// 세금정보 조회
    		VissueVO tax = voucherGenerateMapper.getTaxMast(todayYm);
    		
    		// 세금 계산
    		int vatAmt = 0;
    		double amt = chargeMngVO.getChrgAmt() == null ? 0.0 : Double.parseDouble(chargeMngVO.getChrgAmt());
    		
    		vatAmt = (int) (amt * tax.getTaxRate() / 100.0);
    		
    		// TAX 무효, 총금액은 원금액으로 교체
    		chargeMngVO.setAddTax(0.0);
    		chargeMngVO.setTotAmt(Double.parseDouble(chargeMngVO.getChrgAmt()));
    		chargeMngVO.setTodayYM(todayYm);
    		

            /*
             * SO_ID (ISP_CD) : 사업자코드
             * 00 : ORN
             * 01 : MTN
             * 02 : Airtel
             * 03 : Tigo
             * 04 : VIP
             * 05 : ISP
             * 06 : LIQUID
             * 07 : ISPA
             * 08 : AXIOM
             * 09 : New Artel
             * 10 : BSC
             * 11 : 4G Networks
             */

            /*
             * APPR_TP (REQ_SYS_ID) : 시스템구분
             * 00 : 내부
             * 01 : Pivot Access
             * 02 : Debit Card
             * 03 : Credit Card
             * 06 : Maxcom
             * 07 : Tigo
             * 08 : SMSMedia
             * 09 : MTN
             * 10 : Airtel
             * 11 : BSC
             * 12 : 3GDirectPay
             * 추후 시스템 구분은 공통코드로 관리(DNDT043)
             */
    		
    		// 금액충전(내부 or 외부 access)
    		if ("100".equals(chargeMngVO.getRchrgTp()) == true) {
    			// 금액충전(내부 or 외부 access)
    			if ("00".equals(chargeMngVO.getApprTp()) == true) {
    				chargeMngVO.setApprNo(null); // 00 : 내부
    			} else if ("02".equals(chargeMngVO.getApprTp()) == true || "03".equals(chargeMngVO.getApprTp()) == true) {
    				// REQ_ID로 값을 설정하는데 그 값을 찾지못하겠음!
    				chargeMngVO.setApprNo(""); // 02 : DebitCard, 03 : CreditCard
    			} else {
    				// 01 : PIVOT, 06 : MAXCOM, 07: TIGO 기타 등등
    			}
    		} else if ("400".equals(chargeMngVO.getRchrgTp()) == true) {
    			// 선물하기(내부 or 외부 access)
                if ("00".equals(chargeMngVO.getApprTp()) == true) {
                	// 00 : 내부
                	chargeMngVO.setApprTp(null);
                	chargeMngVO.setApprNo(null);
                }
    		} else if ("600".equals(chargeMngVO.getRchrgTp()) == true) {
    			// 충전+Bundle상품구매(내부 or 외부 access)
    			if ("00".equals(chargeMngVO.getApprTp()) == true) {
    				// 00 : 내부
    				chargeMngVO.setApprNo(null);
    			}
    		} else if ("700".equals(chargeMngVO.getRchrgTp()) == true) {
    			// 바우쳐충전+Bundle상품구매(내부 or 외부 access)
    			if ("00".equals(chargeMngVO.getApprTp()) == true) {
    				// 00 : 내부
    				chargeMngVO.setApprNo(null);
    			}
    		} else {
            	chargeMngVO.setApprTp(null);
            	chargeMngVO.setApprNo(null);
    		}
    		
    		if ("500".equals(chargeMngVO.getRchrgTp()) == false
    				&& "600".equals(chargeMngVO.getRchrgTp()) == true
    				&& "700".equals(chargeMngVO.getRchrgTp()) == true) {
    			chargeMngVO.setQosAmt(null);
    			chargeMngVO.setEffPeriod(null);
    		}
    		
            // 1. 일회성요금 등록
            // 2. 충전이력 등록
            // 예약처리 : IF_SUC_YN = 'R'로 기록한다.
    		// new THRWY CHRG Id 채번 조회
        	int thrwyChrgSeq = sequenceService.createNewSequence("DT015");
        	String thrwyChrgId = String.format("%010d", thrwyChrgSeq);
        	
        	RchrgHistVO rchrgHistVO = new RchrgHistVO();
        	rchrgHistVO.setNewOrderId(orderId);
        	rchrgHistVO.setRchrgTp(chargeMngVO.getRchrgTp());
        	rchrgHistVO.setRchrgStat(chargeMngVO.getRchrgStat());
        	rchrgHistVO.setRchrgDt(chargeMngVO.getRchrgDt());
        	
        	Calendar cal = Calendar.getInstance();
        	
        	try {
        		cal.setTime(yyyyMMddFormat.parse(rchrgHistVO.getRchrgDt()));	
        	} catch (Exception e) {
        		throw new ServiceException("MSG.M15.MSG00001");
        	}
        	
        	String rchrgChgDt = cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH) + 1)
        			+ cal.getActualMaximum(Calendar.DATE);
        	
        	rchrgHistVO.setRchrgChgDt(rchrgChgDt);
        	rchrgHistVO.setCustId(chargeMngVO.getCustId());
        	rchrgHistVO.setCtrtId(chargeMngVO.getCtrtId());
        	rchrgHistVO.setSoId(chargeMngVO.getSoId());
        	rchrgHistVO.setProdCd(chargeMngVO.getProdCd());
        	rchrgHistVO.setThrwyChrgId(thrwyChrgId);
        	rchrgHistVO.setChrgAmt(Double.parseDouble(chargeMngVO.getChrgAmt()));
        	rchrgHistVO.setChrgAddTx(chargeMngVO.getAddTax());
        	rchrgHistVO.setChrgBillAmt(chargeMngVO.getTotAmt());
        	rchrgHistVO.setRegrId(chargeMngVO.getRegrId());
        	rchrgHistVO.setRegDate(now);
        	rchrgHistVO.setChgrId(chargeMngVO.getRegrId());
        	rchrgHistVO.setChgDate(now);
        	
        	if ("Y".equals(chargeMngVO.getPcrfYn())) {
        		rchrgHistVO.setIfSucYn(null);
        	} else {
        		rchrgHistVO.setIfSucYn("R");
        	}
        	
        	rchrgHistVO.setSvcTelNo(chargeMngVO.getSvcTelNo());
        	rchrgHistVO.setTrgtCtrtId(chargeMngVO.getTgtCtrtId());
        	rchrgHistVO.setTrgtCustId(chargeMngVO.getTgtCustId());
        	rchrgHistVO.setTrgtSvcTelNo(chargeMngVO.getTgtSvcTelNo());
        	rchrgHistVO.setVoSeqNo(chargeMngVO.getVoSeqNo());
        	rchrgHistVO.setVoBarCd(chargeMngVO.getVoBarCd());
        	rchrgHistVO.setApprNo(chargeMngVO.getApprNo());
        	rchrgHistVO.setApprTp(chargeMngVO.getApprTp());
        	rchrgHistVO.setDataQuota(chargeMngVO.getQosAmt());
        	
        	// RCHRG_TERM_DT
        	if ("100".equals(chargeMngVO.getRchrgTp()) == true
        			|| "300".equals(chargeMngVO.getRchrgTp()) == true
        			|| "400".equals(chargeMngVO.getRchrgTp()) == true) {
        		rchrgHistVO.setRchrgTermDt(null);
        	} else if ("500".equals(chargeMngVO.getRchrgTp()) == true
        			|| "600".equals(chargeMngVO.getRchrgTp()) == true
        			|| "700".equals(chargeMngVO.getRchrgTp()) == true) {
        		if ("0".equals(chargeMngVO.getEffPeriod()) == true) {
        			rchrgHistVO.setRchrgTermDt("99991231");
        		} else {
        			if ("N".equals(chargeMngVO.getPcrfYn()) == true) {
        				if ("D".equals(chargeMngVO.getDhType()) == true) {
        					if ("X".equals(chargeMngVO.getDnType()) == true) {
        						
        			        	try {
        			        		cal.setTime(yyyyMMddFormat.parse(rchrgHistVO.getRchrgDt()));	
        			        	} catch (Exception e) {
        			        		throw new ServiceException("MSG.M15.MSG00001");
        			        	}
        						
        						cal.add(Calendar.DATE, -1);
        						cal.add(Calendar.DATE, Integer.parseInt(chargeMngVO.getEffPeriod()));
        						Date rchrgTermDt = cal.getTime();
        						rchrgHistVO.setRchrgTermDt(yyyyMMddFormat.format(rchrgTermDt));
        					} else {
        						cal.setTime(now);
        						cal.add(Calendar.DATE, -1);
        						cal.add(Calendar.DATE, Integer.parseInt(chargeMngVO.getEffPeriod()));
        						Date rchrgTermDt = cal.getTime();
        						rchrgHistVO.setRchrgTermDt(yyyyMMddFormat.format(rchrgTermDt));
        					}
        				} else if ("H".equals(chargeMngVO.getDhType()) == true) {
        					cal.setTime(now);
        					cal.add(Calendar.DATE, 1);
        					cal.add(Calendar.HOUR, -1);
    						Date rchrgTermDt = cal.getTime();
    						rchrgHistVO.setRchrgTermDt(new SimpleDateFormat("yyyyMMddHHmmss").format(rchrgTermDt));
    						rchrgHistVO.setRchrgTermDt(rchrgHistVO.getRchrgTermDt().substring(0, 8));
        				}
        			} else if ("Y".equals(chargeMngVO.getPcrfYn()) == true) {
        				
			        	try {
			        		cal.setTime(yyyyMMddFormat.parse(rchrgHistVO.getRchrgDt()));
			        	} catch (Exception e) {
			        		throw new ServiceException("MSG.M15.MSG00001");
			        	}

						cal.add(Calendar.DATE, -1);
						cal.add(Calendar.DATE, Integer.parseInt(chargeMngVO.getEffPeriod()));
						Date rchrgTermDt = cal.getTime();
						rchrgHistVO.setRchrgTermDt(yyyyMMddFormat.format(rchrgTermDt));
        			}
        		}
        	}
        	
        	SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

        	// RCHRG_TM
        	if ("100".equals(chargeMngVO.getRchrgTp()) == true
        			|| "300".equals(chargeMngVO.getRchrgTp()) == true
        			|| "400".equals(chargeMngVO.getRchrgTp()) == true) {
        		rchrgHistVO.setRchrgTm(timeFormat.format(now));
        	} else if ("500".equals(chargeMngVO.getRchrgTp()) == true
        			|| "600".equals(chargeMngVO.getRchrgTp()) == true
        			|| "700".equals(chargeMngVO.getRchrgTp()) == true) {
        		if ("0".equals(chargeMngVO.getEffPeriod()) == true) {
        			rchrgHistVO.setRchrgTm(timeFormat.format(now));	
        		} else {
        			if ("N".equals(chargeMngVO.getPcrfYn()) == true) {
        				rchrgHistVO.setRchrgTm(timeFormat.format(now));
        			} else {
        				rchrgHistVO.setRchrgTm("000000");
        			}
        		}
        	}
        	
        	// RCHRG_TERM_TM
        	if ("100".equals(chargeMngVO.getRchrgTp()) == true
        			|| "300".equals(chargeMngVO.getRchrgTp()) == true
        			|| "400".equals(chargeMngVO.getRchrgTp()) == true) {
        		rchrgHistVO.setRchrgTermTm(null);
        	} else if ("500".equals(chargeMngVO.getRchrgTp()) == true
        			|| "600".equals(chargeMngVO.getRchrgTp()) == true
        			|| "700".equals(chargeMngVO.getRchrgTp()) == true) {
        		if ("0".equals(chargeMngVO.getEffPeriod()) == true) {
        			rchrgHistVO.setRchrgTermTm("235959");
        		} else {
        			if ("N".equals(chargeMngVO.getPcrfYn()) == true) {
        				if ("D".equals(chargeMngVO.getDhType()) == true) {
        					rchrgHistVO.setRchrgTermTm("235959");
        				} else if ("H".equals(chargeMngVO.getDhType()) == true) {
        					cal.setTime(now);
        					cal.add(Calendar.DATE, 1);
        					cal.add(Calendar.SECOND, -1);
        					rchrgHistVO.setRchrgTermTm(timeFormat.format(cal.getTime()));
        				}
        			} else if ("Y".equals(chargeMngVO.getPcrfYn()) == true) {
        				rchrgHistVO.setRchrgTermTm("235959");
        			}
        		}
        	}
        	
        	chargeMngMapper.insertRchrgHist(rchrgHistVO);
        	
            // 바우쳐
            // 바우쳐 사용완료 처리(VO_STAT_CD : 사용완료 70, LGST_PROC_STAT_CD : 판매출고 902)
        	if ("300".equals(chargeMngVO.getRchrgTp()) == true) {
        		VeqtVO veqtVO = new VeqtVO();
        		veqtVO.setSoId(chargeMngVO.getSoId());
        		veqtVO.setVoSeqNo(chargeMngVO.getVoSeqNo());
        		veqtVO.setVoStatCd("70");	// 바우쳐상태 : 사용완료 70
        		veqtVO.setLgstProcStatCd("902");	// 물류처리상태 : 판매출고 902
        		veqtVO.setLgstProcDttm(new SimpleDateFormat("yyyyMMddHHmmss").format(now));
        		veqtVO.setChgrId(chargeMngVO.getRegrId());
        		veqtVO.setChgDate(now);
        		veqtVO.setActTp("001");
        		
        		int uptCnt = chargeMngMapper.updateVeqtInfoVoStatLgst(veqtVO);
        		
        		if (uptCnt == 0) {
        			// 실패
        			throw new ServiceException("MSG.M15.MSG00006");
        		}
        		
        		String maxCrtSeq = chargeMngMapper.getMaxCrtSeqNo(veqtVO);
        		String newCrtSeq = String.format("%010d", Integer.parseInt(maxCrtSeq) + 1);
        		
        		veqtVO.setCrtSeqNo(newCrtSeq);
        		
        		chargeMngMapper.addVeqtTransInfoInit_voSeqNo(veqtVO);
        		
        		// Provisioning 처리
        		
        	} else {
        		if ("500".equals(chargeMngVO.getRchrgTp()) == true
        				|| "600".equals(chargeMngVO.getRchrgTp()) == true) {
        			// Bundle 상품 구매, 데이터충전&Bundle 상품 구매
        			
        			if ("Y".equals(chargeMngVO.getPcrfYn()) == true) {
        				// 예약처리 : Provisioning 처리를 하지 않는다.
        			} else if ("N".equals(chargeMngVO.getPcrfYn()) == true) {
        				// 기존대로 즉시 PV처리
        			}
        		} else if ("700".equals(chargeMngVO.getRchrgTp()) == true) {
            		VeqtVO veqtVO = new VeqtVO();
            		veqtVO.setSoId(chargeMngVO.getSoId());
            		veqtVO.setVoSeqNo(chargeMngVO.getVoSeqNo());
            		veqtVO.setVoStatCd("70");	// 바우쳐상태 : 사용완료 70
            		veqtVO.setLgstProcStatCd("902");	// 물류처리상태 : 판매출고 902
            		veqtVO.setLgstProcDttm(new SimpleDateFormat("yyyyMMddHHmmss").format(now));
            		veqtVO.setChgrId(chargeMngVO.getRegrId());
            		veqtVO.setChgDate(now);
            		veqtVO.setActTp("001");
            		
            		int uptCnt = chargeMngMapper.updateVeqtInfoVoStatLgst(veqtVO);
            		
            		if (uptCnt == 0) {
            			// 실패
            			throw new ServiceException("MSG.M15.MSG00006");
            		}
            		
            		String maxCrtSeq = chargeMngMapper.getMaxCrtSeqNo(veqtVO);
            		String newCrtSeq = String.format("%010d", Integer.parseInt(maxCrtSeq) + 1);
            		
            		veqtVO.setCrtSeqNo(newCrtSeq);
            		
            		chargeMngMapper.addVeqtTransInfoInit_voSeqNo(veqtVO);
            		
        			if ("Y".equals(chargeMngVO.getPcrfYn()) == true) {
        				// 예약처리 : Provisioning 처리를 하지 않는다.
        			} else if ("N".equals(chargeMngVO.getPcrfYn()) == true) {
        				// 기존대로 즉시 PV처리
        			}
        		} else {
        			// Provisioning 처리
        		}
        	}
        }
	}
	
	@Override
	public List<ChargeMngVO> getCtrtChrgCntList(ChargeMngVO chargeMngVO) {
		return chargeMngMapper.getCtrtChrgCntList(chargeMngVO);
	}
	
	@Override
	public String getOcsCancelYn(ChargeMngVO chargeMngVO) {
		return chargeMngMapper.getOcsCancelYn(chargeMngVO);
	}
	
	@Override
	public void cancelCharge(ChargeMngVO chargeMngVO) {
        
		Date now = new Date();
		SimpleDateFormat yyyyMMFormat = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
		String todayYm = yyyyMMFormat.format(now);
		
		String orderId = null; // new Order Id
        
        if (StringUtils.isEmpty(chargeMngVO.getRchrgSeqNo()) == false) {
        	// New Order Id 채번 조회
        	int orderSeq = sequenceService.createNewSequence("DT013");
        	orderId = String.format("%010d", orderSeq);
        	
    		// 세금정보 조회
    		VissueVO tax = voucherGenerateMapper.getTaxMast(todayYm);
    		
    		// 세금 계산
    		int vatAmt = 0;
    		double amt = chargeMngVO.getChrgAmt() == null ? 0.0 : Double.parseDouble(chargeMngVO.getChrgAmt());
    		
    		vatAmt = (int) (amt * tax.getTaxRate() / 100.0);
    		
    		// TAX 무효, 총금액은 원금액으로 교체
    		chargeMngVO.setAddTax(0.0);
    		chargeMngVO.setTotAmt(Double.parseDouble(chargeMngVO.getChrgAmt()));
    		
            if (StringUtils.isEmpty(chargeMngVO.getOrderId()) == false) {

            	// 충전이력 등록
            	RchrgHistVO rchrgHistVO = new RchrgHistVO();
            	
            	rchrgHistVO.setNewOrderId(orderId);
            	rchrgHistVO.setRchrgTp(chargeMngVO.getRchrgTp());
            	rchrgHistVO.setRchrgStat(chargeMngVO.getRchrgStat());
            	rchrgHistVO.setRchrgDt(chargeMngVO.getRchrgDt());

				Calendar cal = Calendar.getInstance();
				
				try {
					cal.setTime(yyyyMMddFormat.parse(rchrgHistVO.getRchrgDt()));	
				} catch (Exception e) {
					throw new ServiceException("MSG.M15.MSG00001");	
				}
				
				int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				cal.set(Calendar.DATE, lastDay);
				
				String rchrgChgDt = yyyyMMddFormat.format(cal.getTime());
				rchrgHistVO.setRchrgChgDt(rchrgChgDt);
				rchrgHistVO.setRchrgCnt(Integer.parseInt(chargeMngVO.getRchrgCnt()));
				rchrgHistVO.setCustId(chargeMngVO.getCustId());
				rchrgHistVO.setCtrtId(chargeMngVO.getCtrtId());
				rchrgHistVO.setSoId(chargeMngVO.getSoId());
				rchrgHistVO.setProdCd(chargeMngVO.getProdCd());
				rchrgHistVO.setThrwyChrgId(chargeMngVO.getThrwyChrgId());
				rchrgHistVO.setChrgAmt(Double.parseDouble(chargeMngVO.getChrgAmt()));
				rchrgHistVO.setChrgAddTx(chargeMngVO.getAddTax());
				rchrgHistVO.setChrgBillAmt(chargeMngVO.getTotAmt());
				
				if ("R".equals(chargeMngVO.getIfSucYn()) == true) {
					rchrgHistVO.setDelFlg("C");
					rchrgHistVO.setIfSucYn("C");
				} else {
					rchrgHistVO.setDelFlg(null);
					rchrgHistVO.setIfSucYn(null);
				}
				
				rchrgHistVO.setRegrId(chargeMngVO.getRegrId());
				rchrgHistVO.setRegDate(now);
				rchrgHistVO.setChgrId(chargeMngVO.getRegrId());
				rchrgHistVO.setChgDate(now);
				rchrgHistVO.setSvcTelNo(chargeMngVO.getSvcTelNo());
				rchrgHistVO.setVoSeqNo(chargeMngVO.getVoSeqNo());
				rchrgHistVO.setVoBarCd(chargeMngVO.getVoBarCd());
				rchrgHistVO.setApprNo(chargeMngVO.getApprNo());
				rchrgHistVO.setApprTp(chargeMngVO.getApprTp());
				rchrgHistVO.setRchrgTermDt(chargeMngVO.getRchrgTermDt());
				
				chargeMngMapper.insertRchrgHist(rchrgHistVO);
            }
            
            // PV처리(Provisioning)
            
            if ("300".equals(chargeMngVO.getRchrgTp()) == true
            		|| "700".equals(chargeMngVO.getRchrgTp()) == true) {
            	VeqtVO veqtVO = new VeqtVO();
            	veqtVO.setSoId(chargeMngVO.getSoId());
            	veqtVO.setVoSeqNo(chargeMngVO.getVoSeqNo());
            	
            	VeqtVO veqtTransInfo = chargeMngMapper.getVeqtTransInfo(veqtVO);
            	
            	veqtVO.setVoStatCd(veqtTransInfo.getVoStatCd());
            	veqtVO.setLgstProcStatCd(veqtTransInfo.getLgstProcStatCd());
            	veqtVO.setChgrId(chargeMngVO.getRegrId());
            	veqtVO.setActTp("002");
            	
            	chargeMngMapper.updateVeqtInfoVoStatLgst(veqtVO);
            	
        		String maxCrtSeq = chargeMngMapper.getMaxCrtSeqNo(veqtVO);
        		String newCrtSeq = String.format("%010d", Integer.parseInt(maxCrtSeq) + 1);
        		
        		veqtVO.setCrtSeqNo(newCrtSeq);
        		
        		chargeMngMapper.addVeqtTransInfoInit_voSeqNo(veqtVO);
            }
        }
	}
	
}
