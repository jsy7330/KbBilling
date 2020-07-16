package com.ntels.ccbs.system.service.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.common.service.MaskRule;
import com.ntels.ccbs.system.mapper.common.service.MaskInterceptorMapper;
import com.ntels.ccbs.system.service.common.service.MaskInterceptorService;

@Service
public class MaskInterceptorServiceImpl implements MaskInterceptorService, InitializingBean {
 
	@Autowired
	private MaskInterceptorMapper maskInterceptorMapper;

	private Map<String,Map<String,MaskRule>> maskRule;
	
	
	@Override
    public Map<String,MaskRule> getMaskRuleList(String soId) {
		if(maskRule == null){
			return null;
		}else{
			return maskRule.get(soId);
		}
    }


	@Override
    public void afterPropertiesSet() throws Exception {
		this.getMaskRule();
	    
    }
	
	private void getMaskRule(){
		if(maskRule != null){
			maskRule.clear();
		}else{
			maskRule = new HashMap<String,Map<String, MaskRule>>();
		}
		List<String> soList = maskInterceptorMapper.getSoList();
		
		for(String soId : soList){
			List<MaskRule> maskRuleList = maskInterceptorMapper.getMaskRule(soId);
			if(maskRuleList.size() > 0){
				Map<String,MaskRule> rule = new HashMap<String,MaskRule>();
				for(MaskRule maskRule : maskRuleList){
					rule.put(maskRule.getFieldName(), maskRule);
				}
				maskRule.put(soId, rule);
			}
		}
	}
}
