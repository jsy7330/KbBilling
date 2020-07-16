package com.ntels.ccbs.customer.service.statistics.statisticsMgt.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.customer.domain.statistics.statisticsMgt.StatisticsMgtVO;
import com.ntels.ccbs.customer.mapper.statistics.statisticsMgt.StatisticsMgtMapper;
import com.ntels.ccbs.customer.service.statistics.statisticsMgt.StatisticsMgtService;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.nisf.util.message.MessageUtil;
//import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;


@SuppressWarnings("deprecation")
@Service
public class StatisticsMgtServiceImpl implements StatisticsMgtService {
    private static final Log log = LogFactory.getLog(StatisticsMgtServiceImpl.class);
    
	@Autowired
	private StatisticsMgtMapper statisticsMgtMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
    @Autowired
    private DataSource dataSource = null;
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
    
    @Autowired
    private PlatformTransactionManager transactionManager = null;
    
	@Override
	public List<StatisticsMgtVO> getStatisticeList() {
		return statisticsMgtMapper.getStatisticeList();
	}
	@Override
	public StatisticsMgtVO getStatisticeDtl(String stmtCd) {		
		return statisticsMgtMapper.getStatisticeDtl(stmtCd);
	}	
	@Override
	public List<StatisticsMgtVO> getInitCodeList(String lng) {
		return statisticsMgtMapper.getInitCodeList(lng);
	}

	@Override
	public List<StatisticsMgtVO> getUseableList(String varDefn, String lng) {
		return statisticsMgtMapper.getUseableList(varDefn,lng);
	}
	@Override
	public List<StatisticsMgtVO> getSelectedList(String varDefn, String lng) {
		return statisticsMgtMapper.getSelectedList(varDefn,lng);
	}	
	
	@Override
	public int updateStatistics(StatisticsMgtVO statisticsMgtVO) {
		return statisticsMgtMapper.updateStatistics(statisticsMgtVO);
	}	
	@Override
	public int insertStatistics(StatisticsMgtVO statisticsMgtVO) {
		String seq = sequenceService.createNewSequence(Consts.SEQ_CODE.ST_STMT_CD, 10);
		System.out.println("seq====>"+seq);
		statisticsMgtVO.setStmtCd(seq);
		return statisticsMgtMapper.insertStatistics(statisticsMgtVO);
	}	
	
	@Override
	public List<StatisticsMgtVO> getStatisticeSoList(String searchSoId) {
		return statisticsMgtMapper.getStatisticeSoList(searchSoId);
		
	}
	
	@Override
	public List<StatisticsMgtVO> getStatistice(StatisticsMgtVO statisticsMgtVO) {
		return statisticsMgtMapper.getStatistice(statisticsMgtVO);
	}
	
    @Override
    public List<Map<String, Object>> test(String sql, int timeout) throws ServiceException {
        assert dataSource != null : "The dataSource must be set.";

        System.out.println("Test a statement. - " + sql);
        Connection connection = DataSourceUtils.getConnection(dataSource);
        boolean autocommit = false;
        PreparedStatement preparedStatement = null;
        List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();

        try {
            autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            System.out.println("\tThe timeout is set to " + timeout);
            preparedStatement.setQueryTimeout(timeout);
            System.out.println("\tThe MaxRows is set to " + 1);
            preparedStatement.setMaxRows(1);
            preparedStatement.setFetchSize(1);

            System.out.println("\tset null value to parameters");
            for (int index = 0; index < preparedStatement.getParameterMetaData().getParameterCount(); index++) {
                preparedStatement.setNull(index + 1, java.sql.Types.VARCHAR);
            }
            preparedStatement.execute();
            // 메타를 수집한다.
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
            for (int index = 0; resultSetMetaData != null && index < resultSetMetaData.getColumnCount(); index++) {
                if (resultSetMetaData.getColumnTypeName(index + 1).contains(" ") == true) {
                    throw new ServiceException(MessageUtil.getMessage("MSG.M15.MSG00053"), new Object[] { "Field-Name can't support blank." });
                }
                Map<String, Object> columnMeta = new HashMap<String, Object>();
                columnMeta.put("type", resultSetMetaData.getColumnTypeName(index + 1));
                columnMeta.put("name", resultSetMetaData.getColumnLabel(index + 1));
                columnMeta.put("index", index);

                System.out.println("\t" + columnMeta);
                result.add(columnMeta);
            }
            connection.rollback();
            System.out.println("Rollback a statement - " + sql);
            connection.setAutoCommit(autocommit);
            DataSourceUtils.releaseConnection(connection, dataSource);
            connection = null;
        } catch (SQLException e) {
             e.printStackTrace();
             String message = e.getMessage().replaceAll("[\",\\r,\\n]+", "");
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(autocommit);
                    DataSourceUtils.releaseConnection(connection, dataSource);
                    connection = null;
                }
            } catch (SQLException e1) {
            	throw new ServiceException(message);
            }
            throw new ServiceException("MSG.M15.MSG00053", new Object[] { message });
            //throw new ServiceException(message);
        }

        System.out.println("Test has been ended. - " + sql);

        return result;
    }	
    
    @Override
    public List<Map<String, Object>> execute(String sql, int timeout, Map<String, ?> args) throws ServiceException {
        assert dataSource != null : "The dataSource must be set.";
        assert namedParameterJdbcTemplate != null : "The jdbcTemplate must be set.";

        List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();

        System.out.println("Execute a statement. - " + sql);

        // 트랙잭션을 정의한다.
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setName(this.getClass().getSimpleName());
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

        System.out.println("setName===>"+this.getClass().getSimpleName());
        
        if (timeout > 0) {
        	System.out.println("\tThe timeout is set to " + timeout);
            transactionDefinition.setTimeout(timeout);
        }

        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);

        try {
            // 쿼리를 실행한다.
            if (log.isDebugEnabled() == true && args != null) {
                for (String key : args.keySet()) {
                	System.out.println("\tset " + args.get(key) + " to " + key + ".");
                	
                	if(key == "SO_ID" ){
                		sql = sql.replace("@SO_ID","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "ACNT_ID" ){
                		sql = sql.replace("@ACNT_ID","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "STRT_DD" ){
                		sql = sql.replace("@STRT_DD","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "END_DD" ){
                		sql = sql.replace("@END_DD","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "SVC_TEL_NO" ){
                		sql = sql.replace("@SVC_TEL_NO","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "BILL_BGN_MTH" ){
                		sql = sql.replace("@BILL_BGN_MTH","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "BILL_END_MTH" ){
                		sql = sql.replace("@BILL_END_MTH","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "CTRT_STAT" ){
                		sql = sql.replace("@CTRT_STAT","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "CUST_ID" ){
                		sql = sql.replace("@CUST_ID","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "OPEN_BGN_DD" ){
                		sql = sql.replace("@OPEN_BGN_DD","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "OPEN_END_DD" ){
                		sql = sql.replace("@OPEN_END_DD","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}else if(key == "SEARCH_DD" ){
                		sql = sql.replace("@SEARCH_DD","'"+args.get(key)+"'");
                		//System.out.println("replace_sql====>"+sql);
                	}
                }
                System.out.println("replace_sql====>"+sql);
            }

            if (sql.toUpperCase().startsWith("SELECT") == true) {
                result = namedParameterJdbcTemplate.queryForList(sql, args);
            } else {
             	namedParameterJdbcTemplate.update(sql, args);
            }
        } catch (DataAccessException e) {
        	String message = e.getMessage().replaceAll("[\",\\r,\\n]+", "");
        	System.out.println("\tRollback a statement. - " + sql);
            transactionManager.rollback(status);
            System.out.println("Execution has been ended. - " + sql);
            throw new ServiceException("MSG.M15.MSG00053", new Object[] { message });
        }

        transactionManager.commit(status);
        System.out.println("Execution has been ended. - " + sql);

        return result;

    }    
	@Override
	public int deleteStatistics(StatisticsMgtVO statisticsMgtVO) {
		return statisticsMgtMapper.deleteStatistics(statisticsMgtVO);
	}    
}