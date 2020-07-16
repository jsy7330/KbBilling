package com.ntels.ccbs.system.service.common.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.domain.common.common.PostMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.mapper.common.common.PostMngMapper;
import com.ntels.ccbs.system.service.common.common.PostMngService;
import com.ntels.ccbs.system.service.common.service.CommonDataService;

@Service
public class PostMngServiceImpl implements PostMngService {

	/** CommonDataMapper  Autowired. */
	@Autowired
	private PostMngMapper postMngMapper;
	@Autowired
	private CommonDataService commonDataService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> list(String sidx, int page, int rows, String lng, PostMngVO postMng) {
		Map<String,Object> soInfo = new HashMap<String,Object>();
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		Integer totalCount=0;
		totalCount= postMngMapper.count(postMng,sessionUser.getSoAuthList());
		if(totalCount.intValue() == 0){
			soInfo.put("totalCount", totalCount);
			soInfo.put("totalPages", new Integer(0));
			soInfo.put("page", new Integer(1));
			soInfo.put("str1", postMng.getStr1());
			soInfo.put("str2", postMng.getStr2());
			soInfo.put("str3", postMng.getStr3());
			soInfo.put("str4", postMng.getStr4());
			soInfo.put("str5", postMng.getStr5());
			soInfo.put("str6", postMng.getStr6());
			soInfo.put("str7", postMng.getStr7());
		}else{
		
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			List<PostMngVO> postList = postMngMapper.list(sidx, start, end, lng,postMng,sessionUser.getSoAuthList());
			soInfo.put("postList", postList);
			soInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			soInfo.put("totalPages", totalPages);
			soInfo.put("page", new Integer(page));
			soInfo.put("str1", postMng.getStr1());
			soInfo.put("str2", postMng.getStr2());
			soInfo.put("str3", postMng.getStr3());
			soInfo.put("str4", postMng.getStr4());
			soInfo.put("str5", postMng.getStr5());
			soInfo.put("str6", postMng.getStr6());
			soInfo.put("str7", postMng.getStr7());
		}
		return soInfo;
	}

	@Override
	public Map<String, Object> searchCount(PostMngVO postMng) {
		Map<String,Object> soInfo = new HashMap<String,Object>();
		
		String[] arrNm=postMng.getSrchNm().trim().split(" ");
		int arrSize = arrNm.length;
		
		List<CommonCodeVO> commonCodeList = commonDataService.getCommonCodeList("SY00002", postMng.getLng());

		
		//검색 키워드가 하나일 경우
		if(arrSize == 1){
			for(CommonCodeVO codeData : commonCodeList){
				if(codeData.getCommonCdNm().equals(arrNm[0])){
					arrNm[0] = codeData.getRefCode();
					break;
				}
			}
			//시도 검색
			int sidoCheckCnt = postMngMapper.getSidoNm(arrNm[0]);
			if(sidoCheckCnt > 0){
				soInfo.put("str1", "");
				soInfo.put("str2", "");
				soInfo.put("str3", "");
				soInfo.put("str4", "");
				soInfo.put("str5", "");
				soInfo.put("str6", "");
				soInfo.put("str7", "");
			}else{
				//구군 검색
				int gugunCheckCnt = postMngMapper.getGugunNm(arrNm[0]);
				if(gugunCheckCnt > 0){
					soInfo.put("str1", "");
					soInfo.put("str2", arrNm[0]);
					soInfo.put("str3", "");
					soInfo.put("str4", "");
					soInfo.put("str5", "");
					soInfo.put("str6", "");
					soInfo.put("str7", "");
				}else{
					//동명 검색
					int dongCheckCount = postMngMapper.getDongNm(arrNm[0]);
					if(dongCheckCount > 0){
						soInfo.put("str1", "");
						soInfo.put("str2", "");
						soInfo.put("str3", "");
						soInfo.put("str4", arrNm[0]);
						soInfo.put("str5", "");
						soInfo.put("str6", "");
						soInfo.put("str7", "");
					}else{
						//동면 검색
						int dongMyonCheckCount = postMngMapper.getDongMyonNm(arrNm[0]);
						if(dongMyonCheckCount > 0){
							soInfo.put("str1", "");
							soInfo.put("str2", "");
							soInfo.put("str3", "");
							soInfo.put("str4", "");
							soInfo.put("str5", arrNm[0]);
							soInfo.put("str6", "");
							soInfo.put("str7", "");
						}else{
							if(arrNm[0].indexOf("-") > 0){
								String bldg[] = arrNm[0].split("-");
								soInfo.put("str1", "");
								soInfo.put("str2", "");
								soInfo.put("str3", "");
								soInfo.put("str4", "");
								soInfo.put("str5", "");
								soInfo.put("str6", bldg[0]);
								soInfo.put("str7", bldg[1]);
							}else{
								soInfo.put("str1", "");
								soInfo.put("str2", "");
								soInfo.put("str3", "");
								soInfo.put("str4", "");
								soInfo.put("str5", "");
								soInfo.put("str6", arrNm[0]);
								soInfo.put("str7", "");
							}
						}
					}
				}
			}
		}
			
		
		if(arrSize == 2){
			for(CommonCodeVO codeData : commonCodeList){
				if(codeData.getCommonCdNm().equals(arrNm[0])){
					arrNm[0] = codeData.getRefCode();
					break;
				}
			}
			//시도 검색
			int sidoCheckCnt = postMngMapper.getSidoNm(arrNm[0]);
			if(sidoCheckCnt > 0){
				findStrWithOneValue(soInfo, arrNm, 1);
				soInfo.put("str1", arrNm[0]);
			}else{
				findStrWithTwoValue(soInfo, arrNm, 0, 1);
			}
		}
		
		if(arrSize == 3){
			for(CommonCodeVO codeData : commonCodeList){
				if(codeData.getCommonCdNm().equals(arrNm[0])){
					arrNm[0] = codeData.getRefCode();
					break;
				}
			}
			//시도 검색
			int sidoCheckCnt = postMngMapper.getSidoNm(arrNm[0]);
			if(sidoCheckCnt > 0){
				findStrWithTwoValue(soInfo, arrNm, 1, 2);
				soInfo.put("str1", arrNm[0]);
			}else{ //시도에서 없을 경우
				findStrWithThreeValue(soInfo, arrNm, 0, 1, 2);
			}
		}
		
		if(arrSize == 4){
			for(CommonCodeVO codeData : commonCodeList){
				if(codeData.getCommonCdNm().equals(arrNm[0])){
					arrNm[0] = codeData.getRefCode();
					break;
				}
			}
			//시도 검색
			int sidoCheckCnt = postMngMapper.getSidoNm(arrNm[0]);
			if(sidoCheckCnt > 0){
				findStrWithThreeValue(soInfo, arrNm, 1, 2, 3);
				soInfo.put("str1", arrNm[0]);
			}else{ //시도에서 없을 경우
				findStrWithFourValue(soInfo, arrNm, 0, 1, 2, 3);
				
			}
		}
		
		if(arrSize == 5){
			for(CommonCodeVO codeData : commonCodeList){
				if(codeData.getCommonCdNm().equals(arrNm[0])){
					arrNm[0] = codeData.getRefCode();
					break;
				}
			}
			//시도 검색
			int sidoCheckCnt = postMngMapper.getSidoNm(arrNm[0]);
			if(sidoCheckCnt > 0){
				findStrWithFourValue(soInfo, arrNm, 1, 2, 3, 4);
				soInfo.put("str1", arrNm[0]);
			}else{ //시도에서 없을 경우
				findStrWithFiveValue(soInfo, arrNm, 0, 1, 2, 3, 4);
				
			}
		}
		
		if(arrSize > 5){
			for(CommonCodeVO codeData : commonCodeList){
				if(codeData.getCommonCdNm().equals(arrNm[0])){
					arrNm[0] = codeData.getRefCode();
					break;
				}
			}
			//시도 검색
			int sidoCheckCnt = postMngMapper.getSidoNm(arrNm[0]);
			if(sidoCheckCnt > 0){
				findStrWithFiveValue(soInfo, arrNm, 1, 2, 3, 4, 5);
				soInfo.put("str1", arrNm[0]);
			}else{ //시도에서 없을 경우
				findStrWithFiveValue(soInfo, arrNm, 0, 1, 2, 3, 4);
				
			}
		}
		
		return soInfo;
	}
	
	private void findStrWithOneValue(Map<String,Object> soInfo, String[] arry, int idx1){
		//구군 검색
		int gugunCheckCnt = postMngMapper.getGugunNm(arry[idx1]);
		if(gugunCheckCnt > 0){
			soInfo.put("str1", "");
			soInfo.put("str2", arry[idx1]);
			soInfo.put("str3", "");
			soInfo.put("str4", "");
			soInfo.put("str5", "");
			soInfo.put("str6", "");
			soInfo.put("str7", "");
		}else{ //구군에서 없을 경우
			soInfo.put("str1", "");
			soInfo.put("str2", "");
			soInfo.put("str3", "");
			int dongCheckCount = postMngMapper.getDongNm(arry[idx1]);
			if(dongCheckCount > 0){
				soInfo.put("str4", arry[idx1]);
				soInfo.put("str5", "");
				soInfo.put("str6", "");
				soInfo.put("str7", "");
			}else{ //동에서 없을 경우
				soInfo.put("str4", "");
				soInfo.put("str5", arry[idx1]);
				soInfo.put("str6", "");
				soInfo.put("str7", "");
			}
		}
	}
	
	private void findStrWithTwoValue(Map<String,Object> soInfo, String[] arry, int idx1, int idx2){
		//구군 연결 검색
		int gugunCancatCheckCnt = postMngMapper.getGugunNmConcatString(arry[idx1],arry[idx2]);
		if(gugunCancatCheckCnt > 0){
			soInfo.put("str1", "");
			soInfo.put("str2", arry[idx1]);
			soInfo.put("str3", arry[idx2]);
			soInfo.put("str4", "");
			soInfo.put("str5", "");
			soInfo.put("str6", "");
			soInfo.put("str7", "");
		}else{
			//구군 검색
			int gugunCheckCnt = postMngMapper.getGugunNm(arry[idx1]);
			if(gugunCheckCnt > 0){
				soInfo.put("str1", "");
				soInfo.put("str2", arry[idx1]);
				soInfo.put("str3", "");
				
				int dongCheckCount = postMngMapper.getDongNm(arry[idx2]);
				if(dongCheckCount > 0){
					soInfo.put("str4", arry[idx2]);
					soInfo.put("str5", "");
					soInfo.put("str6", "");
					soInfo.put("str7", "");
				}else{
					int dongMyonCheckCount = postMngMapper.getDongMyonNm(arry[idx2]);
					if(dongMyonCheckCount > 0){
						soInfo.put("str5", arry[idx2]);
						soInfo.put("str6", "");
						soInfo.put("str7", "");
					}else{
						soInfo.put("str5", "");
						if(arry[idx2].indexOf("-") > 0){
							String bldg[] = arry[idx2].split("-");
							soInfo.put("str6", bldg[0]);
							soInfo.put("str7", bldg[1]);
						}else{
							soInfo.put("str6", arry[idx2]);
							soInfo.put("str7", "");
						}
					}
				}
			}else{ //구군에서 없을 경우
				soInfo.put("str1", "");
				soInfo.put("str2", "");
				soInfo.put("str3", "");
				int dongCheckCount = postMngMapper.getDongNm(arry[idx1]);
				if(dongCheckCount > 0){
					soInfo.put("str4", arry[idx1]);
					soInfo.put("str5", arry[idx2]);
					soInfo.put("str6", "");
					soInfo.put("str7", "");
				}else{ //동에서 없을 경우
					soInfo.put("str4", "");
					soInfo.put("str5", arry[idx1]);
					if(arry[idx2].indexOf("-") > 0){
						String bldg[] = arry[idx2].split("-");
						soInfo.put("str6", bldg[0]);
						soInfo.put("str7", bldg[1]);
					}else{
						soInfo.put("str6", arry[idx2]);
						soInfo.put("str7", "");
					}
				}
			}
		}
	}
	
	private void findStrWithThreeValue(Map<String,Object> soInfo, String[] arry, int idx1, int idx2, int idx3){
		//구군 연결 검색
		int gugunCancatCheckCnt = postMngMapper.getGugunNmConcatString(arry[idx1],arry[idx2]);
		if(gugunCancatCheckCnt > 0){
			soInfo.put("str1", "");
			soInfo.put("str2", arry[idx1]);
			soInfo.put("str3", arry[idx2]);
			soInfo.put("str4", "");
			soInfo.put("str5", "");
			soInfo.put("str6", "");
			soInfo.put("str7", "");
			
			int dongCheckCount = postMngMapper.getDongNm(arry[idx3]);
			if(dongCheckCount > 0){
				soInfo.put("str4", arry[idx3]);
				soInfo.put("str5", "");
				soInfo.put("str6", "");
				soInfo.put("str7", "");
			}else{
				soInfo.put("str4", "");
				soInfo.put("str5", arry[idx3]);
				soInfo.put("str6", "");
				soInfo.put("str7", "");
			}
		}else{
			//구군 검색
			int gugunCheckCnt = postMngMapper.getGugunNm(arry[idx1]);
			if(gugunCheckCnt > 0){
				soInfo.put("str1", "");
				soInfo.put("str2", arry[idx1]);
				soInfo.put("str3", "");
				
				int dongCheckCount = postMngMapper.getDongNm(arry[idx2]);
				if(dongCheckCount > 0){
					soInfo.put("str4", arry[idx2]);
					soInfo.put("str5", arry[idx3]);
				}else{
					soInfo.put("str4", "");
					soInfo.put("str5", arry[idx2]);
					if(arry[idx3].indexOf("-") > 0){
						String bldg[] = arry[idx3].split("-");
						soInfo.put("str6", bldg[0]);
						soInfo.put("str7", bldg[1]);
					}else{
						soInfo.put("str6", arry[idx3]);
						soInfo.put("str7", "");
					}
				}
			}else{ //구군에서 없을 경우
				soInfo.put("str1", "");
				soInfo.put("str2", "");
				soInfo.put("str3", "");
				soInfo.put("str4", arry[idx1]);
				soInfo.put("str5", arry[idx2]);
				if(arry[idx3].indexOf("-") > 0){
					String bldg[] = arry[idx3].split("-");
					soInfo.put("str6", bldg[0]);
					soInfo.put("str7", bldg[1]);
				}else{
					soInfo.put("str6", arry[idx3]);
					soInfo.put("str7", "");
				}
			}
		}
	}
	
	
	private void findStrWithFourValue(Map<String,Object> soInfo, String[] arry, int idx1, int idx2, int idx3, int idx4){
		//구군 연결 검색
		int gugunCancatCheckCnt = postMngMapper.getGugunNmConcatString(arry[idx1],arry[idx2]);
		if(gugunCancatCheckCnt > 0){
			soInfo.put("str1", "");
			soInfo.put("str2", arry[idx1]);
			soInfo.put("str3", arry[idx2]);
			soInfo.put("str4", "");
			soInfo.put("str5", "");
			soInfo.put("str6", "");
			soInfo.put("str7", "");
			
			int dongCheckCount = postMngMapper.getDongNm(arry[idx3]);
			if(dongCheckCount > 0){
				soInfo.put("str4", arry[idx3]);
				soInfo.put("str5", arry[idx4]);
				soInfo.put("str6", "");
				soInfo.put("str7", "");
			}else{
				soInfo.put("str4", "");
				soInfo.put("str5", arry[idx3]);
				if(arry[idx4].indexOf("-") > 0){
					String bldg[] = arry[idx4].split("-");
					soInfo.put("str6", bldg[0]);
					soInfo.put("str7", bldg[1]);
				}else{
					soInfo.put("str6", arry[idx4]);
					soInfo.put("str7", "");
				}
			}
		}else{
			//구군 검색
			int gugunCheckCnt = postMngMapper.getGugunNm(arry[idx1]);
			if(gugunCheckCnt > 0){
				soInfo.put("str1", "");
				soInfo.put("str2", arry[idx1]);
				soInfo.put("str3", "");
				soInfo.put("str4", arry[idx2]);
				soInfo.put("str5", arry[idx3]);
				if(arry[idx4].indexOf("-") > 0){
					String bldg[] = arry[idx4].split("-");
					soInfo.put("str6", bldg[0]);
					soInfo.put("str7", bldg[1]);
				}else{
					soInfo.put("str6", arry[idx4]);
					soInfo.put("str7", "");
				}
			}else{ //구군에서 없을 경우
				soInfo.put("str1", "");
				soInfo.put("str2", "");
				soInfo.put("str3", "");
				soInfo.put("str4", arry[idx1]);
				soInfo.put("str5", arry[idx2]);
				if(arry[idx3].indexOf("-") > 0){
					String bldg[] = arry[idx3].split("-");
					soInfo.put("str6", bldg[0]);
					soInfo.put("str7", bldg[1]);
				}else{
					soInfo.put("str6", arry[idx3]);
					soInfo.put("str7", "");
				}
			}
		}
	}
	
	
	
	private void findStrWithFiveValue(Map<String,Object> soInfo, String[] arry, int idx1, int idx2, int idx3, int idx4, int idx5){
		//구군 연결 검색
		int gugunCancatCheckCnt = postMngMapper.getGugunNmConcatString(arry[idx1],arry[idx2]);
		if(gugunCancatCheckCnt > 0){
			soInfo.put("str1", "");
			soInfo.put("str2", arry[idx1]);
			soInfo.put("str3", arry[idx2]);
			soInfo.put("str4", "");
			soInfo.put("str5", "");
			soInfo.put("str6", "");
			soInfo.put("str7", "");
			
			int dongCheckCount = postMngMapper.getDongNm(arry[idx3]);
			if(dongCheckCount > 0){
				soInfo.put("str4", arry[idx3]);
				soInfo.put("str5", arry[idx4]);
				if(arry[idx5].indexOf("-") > 0){
					String bldg[] = arry[idx5].split("-");
					soInfo.put("str6", bldg[0]);
					soInfo.put("str7", bldg[1]);
				}else{
					soInfo.put("str6", arry[idx5]);
					soInfo.put("str7", "");
				}
			}else{
				soInfo.put("str4", "");
				soInfo.put("str5", arry[idx3]);
				if(arry[idx4].indexOf("-") > 0){
					String bldg[] = arry[idx4].split("-");
					soInfo.put("str6", bldg[0]);
					soInfo.put("str7", bldg[1]);
				}else{
					soInfo.put("str6", arry[idx4]);
					soInfo.put("str7", "");
				}
			}
		}else{
			//구군 검색
			int gugunCheckCnt = postMngMapper.getGugunNm(arry[idx1]);
			if(gugunCheckCnt > 0){
				soInfo.put("str1", "");
				soInfo.put("str2", arry[idx1]);
				soInfo.put("str3", "");
				soInfo.put("str4", arry[idx2]);
				soInfo.put("str5", arry[idx3]);
				if(arry[idx4].indexOf("-") > 0){
					String bldg[] = arry[idx4].split("-");
					soInfo.put("str6", bldg[0]);
					soInfo.put("str7", bldg[1]);
				}else{
					soInfo.put("str6", arry[idx4]);
					soInfo.put("str7", "");
				}
			}else{ //구군에서 없을 경우
				soInfo.put("str1", "");
				soInfo.put("str2", "");
				soInfo.put("str3", "");
				soInfo.put("str4", arry[idx1]);
				soInfo.put("str5", arry[idx2]);
				if(arry[idx3].indexOf("-") > 0){
					String bldg[] = arry[idx3].split("-");
					soInfo.put("str6", bldg[0]);
					soInfo.put("str7", bldg[1]);
				}else{
					soInfo.put("str6", arry[idx3]);
					soInfo.put("str7", "");
				}
			}
		}
	}
}