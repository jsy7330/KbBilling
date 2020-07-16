package com.ntels.ccbs.product.service.service.serviceMgt.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.service.serviceMgt.ServiceMgt;
import com.ntels.ccbs.product.mapper.service.serviceMgt.ServiceMgtMapper;
import com.ntels.ccbs.product.service.service.serviceMgt.ServiceMgtService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ServiceMgtServiceImpl implements ServiceMgtService {
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** the mapper. */
	@Autowired
	private ServiceMgtMapper serviceMgtMapper;	
	
	@Autowired
	private SequenceService sequenceService;	
	
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
	
    public static Date getPreviousDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
          
        cal.add(Calendar.DATE, -1);  
          
        return cal.getTime();  
    } 	
    
    public static String getMessage( String[] cnt, String[] msg )
    {
        String result = "";
        for ( int i = 0; i < cnt.length; i++ )
        {
            String strCnt = (String) cnt[i];
            if ( strCnt == null || strCnt.equals( "0" ) )
            {
            }
            else
            {
                result += msg[i];
            }
        }
        return result;
    }    
        
    
	
	@Override
	public List<ServiceMgt> getListMenu(String sessionLanguage,
			List<Map<String, Object>> soAuthList, ServiceMgt serviceMgt) {
		return serviceMgtMapper.list(sessionLanguage, soAuthList, serviceMgt);
	}

	@Override
	public int getServiceMgtComListCount(ServiceMgt serviceMgt,
			List<Map<String, Object>> soAuthList) {
		return serviceMgtMapper.serviceMgtComListCount(serviceMgt, soAuthList);
	}

	@Override
	public List<ServiceMgt> getServiceMgtComList(ServiceMgt serviceMgt,
			List<Map<String, Object>> soAuthList, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return serviceMgtMapper.serviceMgtComList(serviceMgt, soAuthList, start, end);
	}

	@Override
	public String getNextDispPriNo(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getNextDispPriNo(serviceMgt);
	}

	@Override
	public int getDualSvcNmCnt(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getDualSvcNmCnt(serviceMgt);
	}

	@Override
	public int insertServiceMgt(ServiceMgt serviceMgt) {
		return serviceMgtMapper.insertServiceMgt(serviceMgt);
	}

	@Override
	public ServiceMgt getService(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getService(serviceMgt);
	}

	@Override
	public String updateServiceMgt(ServiceMgt serviceMgt) {
		
		int svcCnt = serviceMgtMapper.getDualSvcNmCnt(serviceMgt);
		logger.debug("svcCnt : {}", svcCnt);
		int result = -1;
		String returnMsg = "";
		
		if (svcCnt == 0){
			if (serviceMgt.getInActYn().equals(Consts.SVC_MGT_CODE.EXPIRE_NO)) //EXPIRE_NO : 1 => 변경 / 2 -> 종료
			{
				//변경하고자 하는 적용시작일자와 기존의 적용시작일자가 동일할 경우
				if (serviceMgt.getActDt().equals(serviceMgt.getBaseActDt())) {
					
					result = serviceMgtMapper.updateServiceMgt(serviceMgt);
				} else {
					serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
					//종료 일자를 유효시작일자 전 날로 설정
					Date tmpInactDt = null;
					try {
						logger.debug("getActDt : {}", serviceMgt.getActDt());
						tmpInactDt = transFormat.parse(serviceMgt.getActDt());
					} catch (ParseException e) {
						logger.error("error", e);
					}
					logger.debug("tmpInactDt : {}", tmpInactDt);
					String baseInactDt = serviceMgt.getInactDt();
					serviceMgt.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
					result = serviceMgtMapper.updateSeriveMgtInactDt(serviceMgt);
					serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
					serviceMgt.setInactDt(baseInactDt);
					result = serviceMgtMapper.insertServiceMgt(serviceMgt);
				}
				
			} else {	//종료 선택 시
                String[] cnt = new String[7];
                //조회 테이블, 컬럼
                String[] msg = {
	        		Consts.SVC_MGT_CODE.TPMPD_SVC_RATE_ITM_TYP,
	        		//Consts.SVC_MGT_CODE.TP_SVC_POINT_SAVE_DTL,
	        		//Consts.SVC_MGT_CODE.TP_SVC_CMPS_ITM,
	        		Consts.SVC_MGT_CODE.TPMSV_SVC,
	        		//Consts.SVC_MGT_CODE.TPMPD_SUPRT_EQUIP,
	        		Consts.SVC_MGT_CODE.TPMBI_SALE_ITM,
	        		//Consts.SVC_MGT_CODE.TPMBI_RATE_ITM,
	        		Consts.SVC_MGT_CODE.TPMPD_PROD_SVC,
	        		Consts.SVC_MGT_CODE.TPMPD_D_SUPRT_EQUIP,
	        		
	        		Consts.SVC_MGT_CODE.TPMPD_D_RATE_ITM,
	        		Consts.SVC_MGT_CODE.TPMPD_D_PROD_SVC
	        		//Consts.SVC_MGT_CODE.TP_BAND 
	        		};

                String[] tableNm = { "TPMPD_SVC_RATE_ITM_TYP", //서비스과금항목유형
                        //"TP_SVC_POINT_SAVE_DTL", //서비스포인트산출상세
                        //"TP_SVC_CMPS_ITM", //서비스구성항목
                        "TPMSV_SVC", //서비스
                        //"TPMPD_SUPRT_EQUIP", //상품제공장비
                        "TPMBI_SALE_ITM", //매출항목
                        //"TPMBI_RATE_ITM", //과금항목
                        "TPMPD_PROD_SVC", //상품서비스
                        "TPMPD_D_SUPRT_EQUIP", //상품제공장비(개발)
                        "TPMPD_D_RATE_ITM", //과금항목(개발)
                        "TPMPD_D_PROD_SVC" //상품서비스(개발)
                        //"TP_BAND" 
                        }; //대역
	            String[] colmnNm = {
	                "SVC_CD",
	                //"SVC_CD",
	                //"SVC_CD",
	                "UPR_SVC_CD",
	                //"SVC_CD",
	                "SVC_CD",
	                //"SVC_CD",
	                "SVC_CD",
	                "SVC_CD",
	                "SVC_CD",
	                "SVC_CD"
	                //"SVC_CD" 
	                };
                //해당 요소와 연관된 정보의 적용종료일 중 종료하고자 하는 적용종료일 이후 건수 조회
                //종료할 서비스코드가 현재 사용중인 다른 테이블이 있는지 체크한다.
                Map<String,String> conTableMap = new HashMap<String,String>();
                for ( int inx = 0; inx < 7; inx++ )
                {
                    conTableMap.put( "CON_TABLE", tableNm[inx] );
                    conTableMap.put( "CON_COLUMN", colmnNm[inx] );
                    conTableMap.put( "SVC_CD", serviceMgt.getSvcCd() );
                    conTableMap.put( "INACT_DT", serviceMgt.getActDt() );
                    
                    Map<String,String> tableCntMap = serviceMgtMapper.getConTableDtCnt(conTableMap);
                    cnt[inx] = String.valueOf(tableCntMap.get("DATA_CNT"));
                }
                
                returnMsg = getMessage( cnt, msg );

                if (returnMsg.equals("") || returnMsg==null){
                	result = serviceMgtMapper.updateServiceInactDt(serviceMgt);
                }
                else 
                {
                   	result = -2;
                    String[] returnException = { returnMsg };
                    //throw new ServiceException( "MSG.M14.MSG00015", returnException );                	
                }
                
			}
			
		} 
		else
		{
			//throw new ServiceException( "MSG.M09.MSG00051" );
			result = -1;
		}		
		
		
		return String.valueOf(result);
	}

	@Override
	public int updateSeriveMgtInactDt(ServiceMgt serviceMgt) {
		return serviceMgtMapper.updateSeriveMgtInactDt(serviceMgt);
	}

	@Override
	public Map<String, String> getConTableDtCnt(Map<String, String> conTableMap) {
		return serviceMgtMapper.getConTableDtCnt(conTableMap);
	}

	@Override
	public int updateServiceInactDt(ServiceMgt serviceMgt) {
		return serviceMgtMapper.updateServiceInactDt(serviceMgt);
	}

	@Override
	public int getServiceMgtSaleItmListCount(ServiceMgt serviceMgt,
			List<Map<String, Object>> soAuthList) {
		return serviceMgtMapper.getServiceMgtSaleItmListCount(serviceMgt, soAuthList);
	}

	@Override
	public List<ServiceMgt> getServiceMgtSaleItmList(ServiceMgt serviceMgt,
			List<Map<String, Object>> soAuthList, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return serviceMgtMapper.getServiceMgtSaleItmList(serviceMgt, soAuthList, start, end);
	}

	@Override
	public List<ServiceMgt> getRateItmTypComboList(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getRateItmTypComboList(serviceMgt);
	}

	@Override
	public int getDualSaleItmNmCnt(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getDualSaleItmNmCnt(serviceMgt);
	}

	@Override
	public int insertServiceMgtSaleItm(ServiceMgt serviceMgt) {
		return serviceMgtMapper.insertServiceMgtSaleItm(serviceMgt);
	}

	@Override
	public ServiceMgt getSaleItm(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getSaleItm(serviceMgt);
	}

	@Override
	public int updateSaleItm(ServiceMgt serviceMgt) {
		return serviceMgtMapper.updateSaleItm(serviceMgt);
	}

	@Override
	public int updateSaleItmInactDt(ServiceMgt serviceMgt) {
		return serviceMgtMapper.updateSaleItmInactDt(serviceMgt);
	}

	@Override
	public int updateServiceMgtSaleItmInactDt(ServiceMgt serviceMgt) {
		return serviceMgtMapper.updateServiceMgtSaleItmInactDt(serviceMgt);
	}

	@Override
	public int getServiceMgtSvcRateItmTypListCount(ServiceMgt serviceMgt,
			List<Map<String, Object>> soAuthList) {
		return serviceMgtMapper.getServiceMgtSvcRateItmTypListCount(serviceMgt, soAuthList);
	}

	@Override
	public List<ServiceMgt> getServiceMgtSvcRateItmTypList(
			ServiceMgt serviceMgt, List<Map<String, Object>> soAuthList,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;		
		
		return serviceMgtMapper.getServiceMgtSvcRateItmTypList(serviceMgt, soAuthList, start, end);
	}

	@Override
	public List<ServiceMgt> getRateItmTypComboListBySvc(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getRateItmTypComboListBySvc(serviceMgt);
	}

	@Override
	public List<ServiceMgt> getInvItmComboList(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getInvItmComboList(serviceMgt);
	}

	@Override
	public List<ServiceMgt> getSaleItmComboList(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getSaleItmComboList(serviceMgt);
	}

	@Override
	public List<ServiceMgt> getSvcRateItmTypAttrList(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getSvcRateItmTypAttrList(serviceMgt);
	}

	@Override
	public List<ServiceMgt> getSvcRateItmTypAttrList2(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getSvcRateItmTypAttrList2(serviceMgt);
	}

	@Override
	public List<ServiceMgt> getSvcRateItmTypFctrList(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getSvcRateItmTypFctrList(serviceMgt);
	}

	@Override
	public Map<String, String> getTableData(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getTableData(serviceMgt);
	}

	@Override
	public List<ServiceMgt> getTotalFactorListMergedeSvc(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getTotalFactorListMergedeSvc(serviceMgt);
	}

	@Override
	public int addSvcRateItmTypFctr(ServiceMgt serviceMgt) {
		return serviceMgtMapper.addSvcRateItmTypFctr(serviceMgt);
	}

	@Override
	public String addSvcRateItmTyp(ServiceMgt serviceMgt) {
		
		String dispPriNo = serviceMgt.getDispPriNo();
		String ratePriNo = serviceMgt.getRatePriNo();
		int saleItmCnt = serviceMgtMapper.getSvcRateItmTypNmDupCnt(serviceMgt);
		int count = -1;
		if (saleItmCnt == 0) {
			String nextCd = sequenceService.createNewSequence(Consts.SEQ_CODE. PD_TPMPD_SVC_RATE_ITM_TYP, "SR", 5);
			
			serviceMgt.setSvcRateItmTypCd(nextCd);
			if(dispPriNo.length() < 3){	
				if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_N)) {
					serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_31 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
				} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_R)) {
					serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_32 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
				} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_U)) {
					serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_33 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
				} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_A)) {
					serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_41 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
				} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_M)) {
					serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_42 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
				} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_D)) {
					serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_43 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
				} else {
					serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_91 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
				}
			}
			
			count = serviceMgtMapper.addSvcRateItmTyp(serviceMgt);			
		}
		return String.valueOf(count);
	}

	@Override
	public ServiceMgt getSvcRateItmTyp(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getSvcRateItmTyp(serviceMgt);
	}

	@Override
	public String modSvcRateItmTyp(ServiceMgt serviceMgt) {
	
		int count = -1;
		
		String dispPriNo = serviceMgt.getDispPriNo();
		if(dispPriNo.length() < 3){	
			if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_N)) {
				serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_31 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
			} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_R)) {
				serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_32 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
			} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_U)) {
				serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_33 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
			} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_A)) {
				serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_41 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
			} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_M)) {
				serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_42 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
			} else if (serviceMgt.getChrgCtgry().equals(Consts.PROD_MGT_CODE.CHRG_CTGRY_D)) {
				serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_43 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
			} else {
				serviceMgt.setDispPriNo(Consts.PROD_MGT_CODE.ATTR_TYP_91 + CommonUtil.leftPadding(dispPriNo, 3, "0"));
			}
		}
		int rateItmCnt = serviceMgtMapper.getSvcRateItmTypNmDupCnt(serviceMgt);
		
		if (rateItmCnt == 0) {
			if (serviceMgt.getInActYn().equals(Consts.SVC_MGT_CODE.EXPIRE_NO)) //EXPIRE_NO : 1 => 변경 / 2 -> 종료
			{
				if (serviceMgt.getActDt().equals(serviceMgt.getBaseActDt())){
					count = serviceMgtMapper.modSvcRateItmTyp(serviceMgt);
				} else {
					serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
					//종료 일자를 유효시작일자 전 날로 설정
					Date tmpInactDt = null;
					try {
						tmpInactDt = transFormat.parse(serviceMgt.getActDt());
					} catch (ParseException e) {
						logger.error("error", e);
					}
					logger.debug("tmpInactDt : {}", tmpInactDt);
					String baseInactDt = serviceMgt.getInactDt();
					serviceMgt.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
					count = serviceMgtMapper.modSvcRateItmTypInactDt(serviceMgt);
					serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
					serviceMgt.setInactDt(baseInactDt);
					count = serviceMgtMapper.addSvcRateItmTyp(serviceMgt);					
				}
					
					
				
			} else {
                String[] cnt = new String[8];
                //조회 테이블, 컬럼
                String[] msg = {
                		//Consts.SVC_MGT_CODE.TP_ACCUM_SVC_RATE_ITM_TYP,
                		Consts.SVC_MGT_CODE.TPMPD_DISC_EXCL,
                		Consts.SVC_MGT_CODE.TPMPD_DISC_SVC_RATE_ITM_TYP,
                		//Consts.SVC_MGT_CODE.TP_D_ACCUM_SVC_RATE_ITM_TYP,
                		Consts.SVC_MGT_CODE.TPMPD_D_DISC_SVC_RT_ITM_TYP,
                		Consts.SVC_MGT_CODE.TPMPD_D_DISC_EXCL,
                		Consts.SVC_MGT_CODE.TPMPD_D_RATE_ITM,
                		Consts.SVC_MGT_CODE.TPMPD_RATE_ITM,
                		//Consts.SVC_MGT_CODE.TP_SVC_POINT_SAVE_DTL,
                		//Consts.SVC_MGT_CODE.TP_STTL_SVC_RATE_ITM_TYP,
                		Consts.SVC_MGT_CODE.TPMPD_SVC_RATE_ITM_TYP_ATTR,
                		Consts.SVC_MGT_CODE.TPMPD_SVC_RATE_ITM_TYP_FCTR };

                String[] tableNm = { //"TP_ACCUM_SVC_RATE_ITM_TYP", //누적대상서비스과금항목유형
                        "TPMPD_DISC_EXCL", //할인배타
                        "TPMPD_D_DISC_SVC_RT_ITM_TYP", //할인대상서비스과금항목유형
                        //"TP_D_ACCUM_SVC_RATE_ITM_TYP", //누적대상서비스과금항목유형(개발)
                        "TPMPD_DISC_SVC_RATE_ITM_TYP", //할인대상서비스과금항목유형(개발)
                        "TPMPD_D_DISC_EXCL", //할인배타(개발)
                        "TPMPD_D_RATE_ITM", //과금항목(개발)
                        "TPMPD_RATE_ITM", //과금항목
                        //"TP_SVC_POINT_SAVE_DTL", //서비스포인트산출상세
                        //"TP_STTL_SVC_RATE_ITM_TYP", //정산대상서비스과금항목유형
                        "TPMPD_SVC_RATE_ITM_TYP_ATTR", //서비스과금항목유형속성
                        "TPMPD_SVC_RATE_ITM_TYP_FCTR" }; //서비스과금항목유형요소
	            String[] colmnNm = {
	                    //"SVC_RATE_ITM_TYP_CD",
	                    "SVC_RATE_ITM_TYP_CD",
	                    "SVC_RATE_ITM_TYP_CD",
	                    //"SVC_RATE_ITM_TYP_CD",
	                    "SVC_RATE_ITM_TYP_CD",
	                    "SVC_RATE_ITM_TYP_CD",
	                    "SVC_RATE_ITM_TYP_CD",
	                    "SVC_RATE_ITM_TYP_CD",
	                    //"SVC_RATE_ITM_TYP_CD",
	                    //"SVC_RATE_ITM_TYP_CD",
	                    "SVC_RATE_ITM_TYP_CD",
	                    "SVC_RATE_ITM_TYP_CD" };
                //해당 요소와 연관된 정보의 적용종료일 중 종료하고자 하는 적용종료일 이후 건수 조회
                //종료할 서비스코드가 현재 사용중인 다른 테이블이 있는지 체크한다.
                Map<String,String> conTableMap = new HashMap<String,String>();
                for ( int inx = 0; inx < 8; inx++ )
                {
                    conTableMap.put( "CON_TABLE", tableNm[inx] );
                    conTableMap.put( "CON_COLUMN", colmnNm[inx] );
                    conTableMap.put( "SVC_CD", serviceMgt.getSvcRateItmTypCd() );
                    conTableMap.put( "INACT_DT", serviceMgt.getActDt() );
                    
                    Map<String,String> tableCntMap = serviceMgtMapper.getConTableDtCnt(conTableMap);
                    cnt[inx] = String.valueOf(tableCntMap.get("DATA_CNT"));
                }
                
                String returnMsg = getMessage( cnt, msg );
                
                if (returnMsg.equals("")){
                	serviceMgt.setMstrFl("0");
                	count = serviceMgtMapper.modSvcRateItmTypInactDtion(serviceMgt);
                }
                else 
                {
                	count = -2;
                    //String[] returnException = { returnMsg };
                    //throw new ServiceException( "MSG.M14.MSG00015", returnException );                	
                }				
			}
			
		}
		
		return String.valueOf(count);
	}

	@Override
	public ServiceMgt getModSvcRateItmTypFctrInit(ServiceMgt serviceMgt) {
		return serviceMgtMapper.getModSvcRateItmTypFctrInit(serviceMgt);
	}

	@Override
	public String modSvcRateItmTypFctr(ServiceMgt serviceMgt) {
		int count = -1;
		
		if (serviceMgt.getInActYn().equals(Consts.SVC_MGT_CODE.EXPIRE_NO)) //EXPIRE_NO : 1 => 변경 / 2 -> 종료
		{
			if (serviceMgt.getActDt().equals(serviceMgt.getBaseActDt())){
				//count = serviceMgtMapper.modSvcRateItmTypFctr(serviceMgt);
				count =2;
			} else {
				serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
				Date tmpInactDt = null;
				try {
					tmpInactDt = transFormat.parse(serviceMgt.getActDt());
				} catch (ParseException e) {
					logger.error("error", e);
				}
				
				String baseInactDt = serviceMgt.getInactDt();
				serviceMgt.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
				count = serviceMgtMapper.modSvcRateItmTypFctrInactDt(serviceMgt);
				serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
				serviceMgt.setInactDt(baseInactDt);
				count = serviceMgtMapper.addSvcRateItmTypFctr(serviceMgt);	
				
			}
			
		}else{
			serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
			Date tmpInactDt = null;
			try {
				tmpInactDt = transFormat.parse(serviceMgt.getActDt());
			} catch (ParseException e) {
				logger.error("error", e);
			}
			
			String baseInactDt = serviceMgt.getInactDt();
			serviceMgt.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
			count = serviceMgtMapper.modSvcRateItmTypFctrInactDt(serviceMgt);
		}
		return String.valueOf(count);
	}

	@Override
	public String updateServiceMgtSaleItmListUpdate(ServiceMgt serviceMgt) {
		int saleItmCnt = serviceMgtMapper.getDualSaleItmNmCnt(serviceMgt);
		logger.debug("svcCnt : {}", saleItmCnt);
		int result = -1;
		String returnMsg = "";
		
		if (saleItmCnt == 0){
			if (serviceMgt.getInActYn().equals(Consts.SVC_MGT_CODE.EXPIRE_NO)) //EXPIRE_NO : 1 => 변경 / 2 -> 종료
			{
				//변경하고자 하는 적용시작일자와 기존의 적용시작일자가 동일할 경우
				if (serviceMgt.getActDt().equals(serviceMgt.getBaseActDt())) {
					
					result = serviceMgtMapper.updateSaleItm(serviceMgt);
				} else {
					serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
					//종료 일자를 유효시작일자 전 날로 설정
					Date tmpInactDt = null;
					try {
						tmpInactDt = transFormat.parse(serviceMgt.getActDt());
					} catch (ParseException e) {
						
						logger.error("error", e);
					}
					
					String inactDt = serviceMgt.getInactDt();
					
					serviceMgt.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
					result = serviceMgtMapper.updateSaleItmInactDt(serviceMgt);
					serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
					serviceMgt.setInactDt(inactDt);
					result = serviceMgtMapper.insertServiceMgtSaleItm(serviceMgt);
				}
				
			} else {	//종료 선택 시
                String[] cnt = new String[1];
                //조회 테이블, 컬럼
                String[] msg = {
	        		Consts.SVC_MGT_CODE.TPMPD_SVC_RATE_ITM_TYP,
	        		};

                String[] tableNm = { "TPMPD_SVC_RATE_ITM_TYP", //서비스과금항목유형
                        }; //대역
	            String[] colmnNm = {
	                "SALE_ITM_CD", 
	                };
                //해당 요소와 연관된 정보의 적용종료일 중 종료하고자 하는 적용종료일 이후 건수 조회
                //종료할 서비스코드가 현재 사용중인 다른 테이블이 있는지 체크한다.
                Map<String,String> conTableMap = new HashMap<String,String>();
                for ( int inx = 0; inx < 1; inx++ )
                {
                    conTableMap.put( "CON_TABLE", tableNm[inx] );
                    conTableMap.put( "CON_COLUMN", colmnNm[inx] );
                    conTableMap.put( "SVC_CD", serviceMgt.getSaleItmCd() );
                    conTableMap.put( "INACT_DT", serviceMgt.getActDt() );
                    
                    Map<String,String> tableCntMap = serviceMgtMapper.getConTableDtCnt(conTableMap);
                    cnt[inx] = String.valueOf(tableCntMap.get("DATA_CNT"));
                }
                
                returnMsg = getMessage( cnt, msg );
                serviceMgt.setMstrFl("0");
                if (returnMsg.equals("")){
                	result = serviceMgtMapper.updateServiceMgtSaleItmInactDt(serviceMgt);
                }
                else 
                {
                	result = -2;
                }
                
			}
			
		} 
		else
		{
			//throw new ServiceException( "MSG.M09.MSG00051" );
			result = -1;
		}	
		
		return String.valueOf(result);
	}

	@Override
	public String treatSvcRateItmTypAttr(HashMap<String, String> map, SessionUser sessionUser) {
		ServiceMgt serviceMgt = new ServiceMgt();
		
		String svcRateItmTypCd = map.get("svcRateItmTypAttrSearchSvcRateItmTypCd");
		String radioYn = map.get("svcRateItmTypAttrSearchRadioInActYn");
		String actDt = map.get("actDt");
		String soId = map.get("svcRateItmTypAttrSearchSoId");
		
		String usrId = sessionUser.getUserId();		
		
		serviceMgt.setSysdate(DateUtil.sysdate());
		serviceMgt.setChgrId(usrId);
		serviceMgt.setRegrId(usrId);
		
		serviceMgt.setSvcRateItmTypCd(svcRateItmTypCd);
		serviceMgt.setSoId(soId);
		
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();

		int result = -1;
	
		while (it.hasNext()) {
			Iterator<Entry<String, String>> it1 = set.iterator();
			Map.Entry<String, String> e = (Map.Entry<String, String>)it.next();
			if (!e.getKey().equals("svcRateItmTypAttrSearchSvcRateItmTypCd") && !e.getKey().equals("svcRateItmTypAttrSearchRadioInActYn")
					&& !e.getKey().equals("actDt") && !e.getKey().equals("svcRateItmTypAttrSearchSoId")) {
				
				if (!e.getKey().contains("InacT") && !e.getKey().contains("BasE") ) {
					serviceMgt.setAttrCd(e.getKey());
					serviceMgt.setAttrVal(e.getValue());
			
					String inactDt = "";
					String baseActDt = "";
					
					while (it1.hasNext()) {
						Map.Entry<String, String> e1 = (Map.Entry<String, String>)it1.next();
						
						if (e1.getKey().contains("InacT" + e.getKey())){
							inactDt = e1.getValue();
						}
						
						if (e1.getKey().contains("BasE" + e.getKey())){
							baseActDt = e1.getValue();
						}							
					}			
System.out.println("inactDt=========>"+inactDt);					
System.out.println("inactDt.length=========>"+inactDt.length());
System.out.println("baseActDt=========>"+baseActDt);
System.out.println("actDt=========>"+actDt);
					if (radioYn.equals("0"))
					{
						if (inactDt.length() < 1 ) {
							
							serviceMgt.setActDt(actDt);
							serviceMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
							serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
							
							result = serviceMgtMapper.addSvcRateItmTypAttr(serviceMgt);
						} else {
							if (actDt.equals(baseActDt)) {
								serviceMgt.setActDt(actDt);
								result = serviceMgtMapper.modSvcRateItmTypAttr(serviceMgt);
							} else {
								serviceMgt.setActDt(actDt);
								serviceMgt.setInactDt(Consts.SVC_MGT_CODE.MAX_DATE);
								serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
								serviceMgt.setBaseActDt(baseActDt);
								
								Date tmpInactDt = null;
								try {
									logger.debug("getActDt : {}", serviceMgt.getActDt());
									tmpInactDt = transFormat.parse(serviceMgt.getActDt());
								} catch (ParseException e2) {
									logger.error("error", e2);
								}
								
								String baseInactDt = inactDt;
								serviceMgt.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
								
								result = serviceMgtMapper.modSvcRateItmTypAttrInactDt(serviceMgt);
								serviceMgt.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
								serviceMgt.setInactDt(baseInactDt);
								result = serviceMgtMapper.addSvcRateItmTypAttr(serviceMgt);
							}
						}
						
					} else {
						serviceMgt.setActDt(baseActDt);
						serviceMgt.setInactDt(actDt);
						serviceMgt.setMstrFl("0");
						result = serviceMgtMapper.modSvcRateItmTypAttrInaction(serviceMgt);
					}
				}	
			}			
			
		}
		
		return String.valueOf(result);
	}
	
	
	
}
