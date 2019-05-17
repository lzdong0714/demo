tbl_SM_REG_TaskRegistration  [colTaskRegistrationID:"projectID",coluqTaskRegistrationID:"关联int KEY"]
————> tbl_SM_REG_Sampling [colRegistrationID:"taskID",coluqTaskRegistrationID:"关联int",coluqSamplingID:"关联int  KEY"]
————> tbl_ENV_SampleLogin [colUserJobID:'jobID',coluqSamplingID:"关联int",coluqSampleJobID:"关联int  KEY"]


Select distinct
tr.colTaskRegistrationID AS projectID,
sampling.colRegistrationID AS taskID,
login.colUserJobID AS jobID,

sampleID = dbo.plm_sampleid ( login.colUserJobID, sample.colSampleNo ),
element.colElementName AS sampleType,
methodName.colMethodNumber AS methodID,

test.colTestName AS monitoringProgram,
samplingSample.colTimeStart AS samplingStartDate,
samplingSample.colTimeEnd AS samplingEndDate,

batch.colUserQCBatchID AS batchID,
u.colUserName AS batchBy,
u1.colUserName AS samplingBroughtBy,
u2.colUserName AS samplingReceiveBy,
sampleResult.ApprovedBy AS approvedBy

 FROM
 -- 项目表
    tbl_SM_REG_TaskRegistration tr
    --任务表
    INNER JOIN tbl_SM_REG_Sampling sampling
    ON tr.coluqTaskRegistrationID= sampling.coluqTaskRegistrationID
    -- 工作表
    INNER JOIN tbl_ENV_SampleLogin login
    ON login.coluqSamplingID = sampling.coluqSamplingID

   

-- 获取站点信息，
	LEFT JOIN tbl_SM_REG_SamplingElementDetails detail 
		ON login.coluqSamplingID = detail.coluqSamplingID
	LEFT JOIN tbl_SM_REG_SamplingStation station 
		ON detail.coluqSamplingElementDetailsID = station.coluqSamplingElementDetailsID
	LEFT JOIN tbl_SM_REG_SamplingSample samplingSample 
		ON station.coluqSamplingStationID = samplingSample.coluqSamplingStationID

	 INNER JOIN tbl_ENV_Sample sample
		ON sample.coluqSamplingSampleID = samplingSample.coluqSamplingSampleID
-- 获取监测人员名字
    LEFT JOIN tbl_User mb 
    	ON mb.colUserID = samplingSample.colMonitoredBy

-- 获取送样人的名字
	LEFT JOIN tbl_env_JobIDSampleDetail JobIDSampleDetail 
		ON login.coluqJobID = JobIDSampleDetail.coluqJobID
	LEFT JOIN tbl_ENV_JobDetailBroughtBy bring 
		ON JobIDSampleDetail.coluqJobIDSampleDetailID = bring.coluqJobIDSampleDetailID
	LEFT JOIN tbl_User u1 
		ON bring.coluqUserID = u1.colUserID
	LEFT JOIN tbl_User u2 
		ON JobIDSampleDetail.colReceivedBy = u2.colUserID

-- 获取监测方法
	LEFT JOIN tbl_SM_REG_SamplingTest samplingTest 
		ON samplingSample.coluqSamplingSampleID = samplingTest.coluqSamplingSampleID
	LEFT JOIN tbl_ENV_TestMethod method 
		ON samplingTest.coluqTestMethodID = method.coluqTestMethodID
	LEFT JOIN tbl_ENV_Test test 
		ON method.coluqTestID = test.coluqTestID

	LEFT JOIN tbl_SM_REG_SamplingElement element 
		ON detail.coluqSamplingElementID = element.coluqSamplingElementID

--获取监测方法名称
	INNER JOIN tbl_QC_METHOD_MethodName methodName 
		ON methodName.coluqMethodNameID = method.coluqMethodID

-- 翻译项目名
    LEFT JOIN tbl_PLM_ClientProjectName pname
    	ON login.colCPNameID = pname.colCPNameID

-- 获取监测批次
	LEFT JOIN tbl_ENV_SampleTest sTest 
		ON sample.coluqSampleID = sTest.coluqSampleID
	LEFT JOIN tbl_ENV_QCBatchAttachSample athSample 
		ON sTest.coluqSampleTestID = athSample.coluqSampleTestID
   	LEFT JOIN tbl_ENV_QCBatch batch
   		ON athSample.coluqQCBatchID = batch.coluqQCBatchID
	LEFT JOIN tbl_User u ON u.colUserID = batch.colEmployee

-- 样品监测完成与否
	Left JOIN (select distinct result.uqQCBatchID AS uqQCBatchID,result.ApprovedBy AS ApprovedBy FROM DW_SampleAndQCResults result) sampleResult
		ON  batch.coluqQCBatchID = sampleResult.uqQCBatchID

-----------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------638---------------------------------------------------------------------------------


Select distinct
tr.colTaskRegistrationID AS projectID,
sampling.colRegistrationID AS taskID,
login.colUserJobID AS jobID,

sampleID = dbo.plm_sampleid ( login.colUserJobID, sample.colSampleNo ),
element.colElementName AS sampleType,
-- methodName.colMethodNumber AS methodID,
-- test.colTestName AS monitoringProgram
-- samplingSample.colTimeStart AS samplingStartDate,
-- samplingSample.colTimeEnd AS samplingEndDate,
-- 
batch.colUserQCBatchID AS batchID,
-- -- u.colUserName AS batchBy,
u1.colUserName AS samplingBroughtBy,
u2.colUserName AS samplingReceiveBy
-- sampleResult.ApprovedBy AS approvedBy

 FROM
 -- 项目表
    tbl_SM_REG_TaskRegistration tr
    --任务表
    INNER JOIN tbl_SM_REG_Sampling sampling
    ON tr.coluqTaskRegistrationID= sampling.coluqTaskRegistrationID
    -- 工作表
    INNER JOIN tbl_ENV_SampleLogin login
    ON login.coluqSamplingID = sampling.coluqSamplingID

   

-- 获取站点信息，
	LEFT JOIN tbl_SM_REG_SamplingElementDetails detail 
		ON login.coluqSamplingID = detail.coluqSamplingID
	LEFT JOIN tbl_SM_REG_SamplingStation station 
		ON detail.coluqSamplingElementDetailsID = station.coluqSamplingElementDetailsID
	LEFT JOIN tbl_SM_REG_SamplingSample samplingSample 
		ON station.coluqSamplingStationID = samplingSample.coluqSamplingStationID

	 INNER JOIN tbl_ENV_Sample sample
		ON sample.coluqSamplingSampleID = samplingSample.coluqSamplingSampleID
-- 获取监测人员名字
    LEFT JOIN tbl_User mb 
    ON mb.colUserID = samplingSample.colMonitoredBy

-- 获取送样人的名字
	LEFT JOIN tbl_env_JobIDSampleDetail JobIDSampleDetail 
			ON login.coluqJobID = JobIDSampleDetail.coluqJobID
  LEFT JOIN tbl_ENV_JobDetailBroughtBy bring 
		ON JobIDSampleDetail.coluqJobIDSampleDetailID = bring.coluqJobIDSampleDetailID
	LEFT JOIN tbl_User u1 
		ON bring.coluqUserID = u1.colUserID
	LEFT JOIN tbl_User u2 
		ON JobIDSampleDetail.colReceivedBy = u2.colUserID

-- 获取监测方法
	LEFT JOIN tbl_SM_REG_SamplingTest samplingTest 
		ON samplingSample.coluqSamplingSampleID = samplingTest.coluqSamplingSampleID
	LEFT JOIN tbl_ENV_TestMethod method 
		ON samplingTest.coluqTestMethodID = method.coluqTestMethodID
	LEFT JOIN tbl_ENV_Test test 
		ON method.coluqTestID = test.coluqTestID
	LEFT JOIN tbl_SM_REG_SamplingElement element 
		ON detail.coluqSamplingElementID = element.coluqSamplingElementID

--获取监测方法名称
	INNER JOIN tbl_QC_METHOD_MethodName methodName 
		ON methodName.coluqMethodNameID = method.coluqMethodID

-- 翻译项目名
    LEFT JOIN tbl_PLM_ClientProjectName pname
    	ON login.colCPNameID = pname.colCPNameID

-- 获取监测批次
	LEFT JOIN tbl_ENV_SampleTest sTest 
		ON sample.coluqSampleID = sTest.coluqSampleID
	LEFT JOIN tbl_ENV_QCBatchAttachSample athSample 
		ON sTest.coluqSampleTestID = athSample.coluqSampleTestID
   	LEFT JOIN tbl_ENV_QCBatch batch
   		ON athSample.coluqQCBatchID = batch.coluqQCBatchID
	LEFT JOIN tbl_User u ON u.colUserID = batch.colEmployee

-- 样品监测完成与否
	Left JOIN (select distinct result.JobID AS JobID,result.ApprovedBy AS ApprovedBy FROM DW_SampleAndQCResults result) sampleResult
		ON  login.coluserJobID = sampleResult.JobID

