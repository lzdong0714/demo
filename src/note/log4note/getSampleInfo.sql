----------------------获取采样信息--------------
SELECT
    station.colStationID AS stationID,
    dbo.plm_sampleid(login.colUserJobID, sa.colSampleNo) AS sampleID,
    sample.colSampleName AS sampleName,
    ele.colElementName AS sampleType,
    assignTo.colAssignTo AS connID,
    sample.colWorkAssignMonitoringDate AS samplingAssignDate,
    u.colUserName AS person 
    station.colStationID AS stationID,
    dbo.plm_sampleid(login.colUserJobID, sa.colSampleNo) AS sampleID,
    sample.colSampleName AS sampleName,
    ele.colElementName AS sampleType,
    assignTo.colAssignTo AS connID,
    sample.colWorkAssignMonitoringDate AS samplingAssignDate,
    u.colUserName AS person 
FROM 
    tbl_SM_REG_Sampling sampling
    LEFT JOIN tbl_SM_REG_SamplingElementDetails eleDetails 
      ON sampling.coluqSamplingID = eleDetails.coluqSamplingID
    LEFT JOIN tbl_SM_REG_SamplingStation station 
      ON eleDetails.coluqSamplingElementDetailsID = station.coluqSamplingElementDetailsID
    LEFT JOIN tbl_SM_REG_SamplingSample sample 
      ON station.coluqSamplingStationID = sample.coluqSamplingStationID
    LEFT JOIN tbl_ENV_Sample sa 
      ON sample.coluqSamplingSampleID = sa.coluqSamplingSampleID
    LEFT JOIN tbl_ENV_SampleLogin login 
      ON login.coluqSampleJobID = sa.coluqSampleJobID
    LEFT JOIN tbl_SM_REG_SamplingElement ele 
      ON eleDetails.coluqSamplingElementID = ele.coluqSamplingElementID
    INNER JOIN tbl_SM_REG_SamplingStationWorkAssignTo assignTo 
      ON sample.coluqSamplingSampleID = assignTo.coluqSamplingSampleID
    INNER JOIN tbl_User u 
      ON assignTo.colAssignTo = u.colUserID 
    WHERE
      sampling.colRegistrationID =  #{taskID}
      AND sample.colSampleID = #{sampleID}
-------------------------------------------------------

--------------获取样品交接信息------------------

SELECT DISTINCT 
  ss.colStationID AS StationID,
  ss.colStationLocation AS StationLocation,
  ssa.colSampleID AS SampleID,
  tr.colTaskRegistrationID AS projectID,
  ssa.colSampleName AS SampleName,
  sbn.colTestSummery AS Test, 
  smsl.coluserjobid AS jobID,
  dbo.plm_sampleid ( smsl.coluserjobid, sms.colsampleno ) AS JobSampleID,
  sbn.colSampleBottleID AS BottleID,
  sbn.colReceivedDate AS ReceivedDate,
    
      case
  when JSD.colBroughtByType =0 then
  (select colPDSCarrierName from tbl_PDS_Carrier where tbl_PDS_Carrier.colPDSCarrierID =
  JSD.colReceivedVia)
  else
  dbo.GetAssignedEmployeeName(JSD.coluqJobIDSampleDetailID,'JobDetailsBroughtBy')
  end
  as [BroughtBy],
    ( SELECT colUserName FROM tbl_USer WHERE colUserID = sbn.colReceivedBy ) AS ReceivedBy,
    s.colRegistrationID AS RegistrationID,
    se.colElementName AS Element
    FROM
      tbl_SM_REG_Sampling s
      INNER JOIN tbl_SM_REG_TaskRegistration tr ON tr.coluqTaskRegistrationID = s.coluqTaskRegistrationID 
      AND s.colValidatedDate IS NOT NULL 
      
      INNER JOIN tbl_SM_REG_SamplingElementDetails sed ON s.coluqSamplingID = sed.coluqSamplingID
      INNER JOIN tbl_SM_REG_SamplingElement se ON se.coluqSamplingElementID = sed.coluqSamplingElementID
      INNER JOIN tbl_env_matrix m ON m.coluqMatrixID = se.coluqMatrixID
      INNER JOIN tbl_SM_REG_SamplingStation ss ON ss.coluqSamplingElementDetailsID = sed.coluqSamplingElementDetailsID
      INNER JOIN tbl_SM_REG_SamplingSample ssa ON ssa.coluqSamplingStationID = ss.coluqSamplingStationID
      INNER JOIN tbl_SM_REG_SamplingTest st ON st.coluqSamplingSampleID = ssa.coluqSamplingSampleID
      INNER JOIN tbl_SM_REG_SamplingParameter sp ON sp.coluqSamplingTestID = st.coluqSamplingTestID
      INNER JOIN tbl_SM_REG_SamplingBottleNumber sbn ON sbn.coluqSamplingSampleID = ssa.coluqSamplingSampleID
      INNER JOIN tbl_SM_REG_SamplingTestBottleID stb ON stb.coluqSamplingTestID = st.coluqSamplingTestID 
      AND stb.coluqBottleID = sbn.coluqBottleID 
      
      INNER JOIN tbl_PLM_ClientAddress ca ON tr.colCAAID= ca.colCAAID
      INNER JOIN tbl_PLM_Client c ON ca.colClientID= c.colClientID 
      
      LEFT JOIN tbl_PLM_ClientProject cp ON tr.colCPAID= cp.colCPAID
      LEFT JOIN tbl_PLM_ClientProjectName cpn ON tr.colCPNameID= cpn.colCPNameID
      LEFT JOIN tbl_PM_ProjectCategory cpc ON tr.coluqProjectCategoryID= cpc.coluqProjectCategoryID
      LEFT JOIN tbl_Env_Sample sms ON sms.coluqSamplingSampleID = ssa.coluqSamplingSampleID
      LEFT JOIN tbl_env_SampleLogin smsl ON smsl.coluqSampleJobID = sms.coluqSampleJobID
      LEFT JOIN tbl_Env_SampleStatus sast ON sast.coluqSampleStatusID = st.coluqSampleStatusID 
      LEFT JOIN tbl_ENV_JobIdsampleDetail JSD ON JSD.coluqJobIDSampleDetailID = sbn.coluqJobIDSampleDetailID
      
    WHERE
      sbn.colReceivedDate is NOT NULL AND
       NOT EXISTS ( SELECT * FROM tbl_Env_FieldTest WHERE coluqTestParameterID = sp.coluqTestParameterID ) 
      AND ( sast.colHold IS NULL OR sast.colHold = 0 ) 
--      AND smsl.coluserjobid='LETC190300'
    ORDER BY
      RegistrationID,
      Element,
    StationID,
  SampleID

------------------------选取已获取批次的样品------------
SELECT CONVERT
  ( BIT, 1 ) AS [Select],
  q.colUserQCBatchID [QCBatchID],
  l.colUserJobID [Job ID],
  [SampleID] = dbo.plm_sampleid ( l.colUserJobID, s.colSampleNo ),
  m.colAnalyticalMatrix [Matrix],
  ( SELECT TOP 1 colUnit FROM tbl_ENV_TestParameter tp, tbl_ENV_Unit u WHERE tp.coluqTestMethodID = tm.coluqTestMethodID AND tp.coluqUnitID = u.coluqUnitID ) AS [Units],
  t.colTestName [Test],
  mn.colMethodNumber [Method],
  c.colCompListName [CompList],
  dbo.Select_BottleID ( st.coluqSampleTestID ) AS [AllBottleID],
  ISNULL( st.colDepth, '' ) AS Depth,
  ISNULL( s.colStation, '' ) AS Station,
  a.colRefQcBatchID [ReferenceID],
  st.coluqSampleTestID [SampleTestID],
  a.coluqQCBatchAttachID [QCBatchAttachID],
  -- a.colPrepAmount AS [PrepAmount],
  -- a.colFinalVolume AS [FinalVolume],
  -- a.colNumberOfRun AS NumberOfRun,
  -- CONVERT ( NVARCHAR ( 50 ), a.colSortOrder ) [SortOrder] 
FROM
  tbl_ENV_QCBatch q
  INNER JOIN tbl_ENV_QCBatchAttachSample a ON 
      a.coluqQCBatchID = q.coluqQCBatchID
  INNER JOIN tbl_ENV_SampleTest st ON st.coluqSampleTestID = a.coluqSampleTestID
  INNER JOIN tbl_ENV_Sample s ON s.coluqSampleID = st.coluqSampleID
  INNER JOIN tbl_ENV_SampleLogin l ON l.coluqSampleJobID = s.coluqSampleJobID
  INNER JOIN tbl_ENV_Matrix m ON m.coluqMatrixID = s.coluqMatrixID
  INNER JOIN tbl_ENV_TestMethod tm ON tm.coluqTestMethodID = st.coluqTestMethodID
  INNER JOIN tbl_ENV_Test t ON t.coluqTestID = tm.coluqTestID
  INNER JOIN tbl_QC_METHOD_MethodName mn ON mn.coluqMethodNameID = tm.coluqMethodID
  INNER JOIN tbl_ENV_TestCompListName c ON c.coluqCompListNameID = st.coluqCompListNameID 
WHERE l.colUserJobID = #{jobID} 
  -- AND s.colSampleNo = #{sampleNo}
ORDER BY
  [Job ID],
  s.colSampleNo,
  [Matrix],
  [Test],
  [Method];
---------------------------选取等待取批次样品--------------------------------------
SELECT DISTINCT
  sl.colUserJobID AS [Job ID],
  dbo.plm_sampleid ( sl.colUserJobID, s.colSampleNo ) AS [SampleID],
  s.colClientSampleID AS [ClientSampleID],
  m.colAnalyticalMatrix AS [Matrix],
  t.colTestName AS [Test],
  ( SELECT TOP 1 colUnit FROM tbl_ENV_TestParameter tp, tbl_ENV_Unit u WHERE st.coluqTestMethodID = tp.coluqTestMethodID AND tp.coluqUnitID = u.coluqUnitID ) AS [Units],
  mn.colMethodNumber AS [Method],
  tcln.colCompListName AS [CompList],
  ISNULL( st.colDepth, '' ) AS Depth,
  ISNULL( s.colStation, '' ) AS Station,
  st.coluqSampleTestID AS [SampleTestID],
  s.colDateReceived AS [DateReceived],
  MAX ( ebft.[NumberOfRun] ) AS [NumberOfRun],
  ( SELECT TOP 1 colSignOffDate FROM tbl_ENV_BottleNo bln WHERE bln.coluqSampleTestID = st.coluqSampleTestID ) AS SignoffDate,
  s.colsampleno AS Random 
FROM
  tbl_ENV_SampleLogin sl
  INNER JOIN tbl_ENV_Sample s ON sl.coluqSampleJobID = s.coluqSampleJobID 
    AND sl.colSignOffDate IS NOT NULL 
    AND s.colHoldStatus = 0
  INNER JOIN tbl_ENV_SampleTest st ON s.coluqSampleID = st.coluqSampleID 
    AND st.colHold = 0 
    AND st.colSubOut = 0 
    AND EXISTS ( SELECT 1 FROM tbl_ENV_BottleNo bln WHERE bln.coluqSampleTestID = st.coluqSampleTestID AND bln.colSignOffDate IS NOT NULL ) 
    AND NOT EXISTS ( SELECT 1 FROM tbl_ENV_QCBatchAttachSample QBS WHERE QBS.coluqSampleTestID = st.coluqSampleTestID )
    
  INNER JOIN tbl_ENV_TestMethod tm ON st.coluqTestMethodID = tm.coluqTestMethodID
  INNER JOIN tbl_QC_METHOD_MethodName mn ON mn.coluqMethodNameID = tm.coluqMethodID
  INNER JOIN tbl_ENV_Test t ON tm.coluqTestID = t.coluqTestID
  INNER JOIN tbl_ENV_Matrix m ON m.coluqMatrixID = t.coluqMatrixID
  INNER JOIN tbl_ENV_ReportingMatrix rm ON s.coluqReportingMatrixID = rm.coluqReportingMatrixID
  LEFT JOIN tbl_ENV_TestCompListName tcln ON tcln.coluqCompListNameID = st.coluqCompListNameID
  LEFT JOIN ( SELECT aw.coluqSampleTestID , aw.coluqFunctionTypeID , awe.colEmployee FROM tbl_ENV_AssignWork aw INNER JOIN tbl_ENV_AssignWorkEmployee awe ON awe.coluqAssignWorkID = aw.coluqAssignWorkID ) mc ON mc.coluqFunctionTypeID = 4 
  AND mc.coluqSampleTestID = st.coluqSampleTestID
  LEFT JOIN tbl_ENV_SampleParameter SP ( NOLOCK ) ON SP.coluqSampleTestID = st.coluqSampleTestID
  LEFT JOIN tbl_ENV_TestParameter TP ( NOLOCK ) ON TP.coluqTestParameterID = SP.coluqTestParameterID
  LEFT JOIN (
    SELECT
      ebftp.coluqTestParameterID ,
    CASE
        
        WHEN sebft.colResultMultientry IS NOT NULL 
        AND sebft.colReading IS NOT NULL 
        AND sebft.colResultMultientry = sebft.colReading THEN
          sebft.colResultMultientry 
          WHEN sebft.colResultMultientry > sebft.colReading THEN
          sebft.colResultMultientry 
          WHEN sebft.colResultMultientry < sebft.colReading THEN
          sebft.colReading 
        END AS [NumberOfRun] 
      FROM
        tbl_EBF_Test sebft ( NOLOCK )
        INNER JOIN tbl_EBF_TestParameter ebftp ( NOLOCK ) ON ebftp.coluqEBFTestID = sebft.coluqEBFTestID UNION
      SELECT
        sebft.coluqTestParameterID ,
      CASE
          
          WHEN sebft.colResultMultientry IS NOT NULL 
          AND sebft.colReading IS NOT NULL 
          AND sebft.colResultMultientry = sebft.colReading THEN
            sebft.colResultMultientry 
            WHEN sebft.colResultMultientry > sebft.colReading THEN
            sebft.colResultMultientry 
            WHEN sebft.colResultMultientry < sebft.colReading THEN
            sebft.colReading 
          END AS [NumberOfRun] 
        FROM
          tbl_EBF_Test sebft ( NOLOCK ) 
        ) ebft ON ebft.coluqTestParameterID = TP.coluqTestParameterID 
      WHERE
            (
              (
                mc.coluqSampleTestID IS NULL 
                AND EXISTS (
                SELECT TOP
                  1 1 
                FROM
                  tbl_ENV_TestFunctionType tft ( NOLOCK )
                  INNER JOIN tbl_ENV_TestFunctionTypeAnalyst tfta ON tft.coluqtestmethodid = st.coluqTestMethodID 
                  AND tft.coluqFunctionTypeID = 4 
                  AND tfta.coluqTestFunctionTypeID = tft.coluqTestFunctionTypeID 
                ) 
              ) 
              OR ( mc.coluqSampleTestID IS NOT NULL )       
            ) 
--      AND sl.colUserJobID = #{jobID} 
--      AND s.colSampleNo = #{sampleNo}
    GROUP BY
      st.coluqSampleTestID ,
      st.coluqTestMethodID ,
      sl.colUserJobID ,
      s.colSampleNo ,
      s.colClientSampleID ,
      m.colAnalyticalMatrix ,
      t.colTestName ,
      mn.colMethodNumber ,
      tcln.colCompListName ,
      s.colDateTimeCollected ,
      st.colDepth ,
      st.colRegistrationID ,
      sl.colProjectID ,
      sl.colCPNameID ,
      s.colStation ,
      s.colDateReceived ,
      st.colQC 
    ORDER BY
    [Job ID],
  random

----------------------------------------------------------------------------

------------------------------获取基本样品信息---------------------------
Select DISTINCT
  tr.colTaskRegistrationID AS projectID,
  sampling.colRegistrationID AS taskID,
  sl.colUserJobID AS JobID,
  sampleID = dbo.plm_sampleid ( sl.colUserJobID, sample.colSampleNo ),
  -- ssa.colSampleID AS colSampleID
FROM   tbl_env_samplelogin sl
  INNER JOIN tbl_env_sample s ON sl.coluqsamplejobid = s.coluqsamplejobid
  --                             AND sl.colsignoffdate IS NOT NULL
  --                             AND s.colHoldStatus = 0

  LEFT JOIN tbl_SM_REG_Sampling sampling ON sl.coluqSamplingID = sampling.coluqSamplingID 
  LEFT JOIN tbl_SM_REG_TaskRegistration tr   ON tr.coluqTaskRegistrationID= sampling.coluqTaskRegistrationID
  INNER JOIN tbl_ENV_Sample sample
    ON sample.coluqSamplingSampleID = samplingSample.coluqSamplingSampleID

  LEFT JOIN tbl_SM_REG_SamplingElementDetails sed ON sampling.coluqSamplingID = sed.coluqSamplingID
  LEFT JOIN tbl_SM_REG_SamplingStation ss ON ss.coluqSamplingElementDetailsID = sed.coluqSamplingElementDetailsID
  LEFT JOIN tbl_SM_REG_SamplingSample ssa ON ssa.coluqSamplingStationID = ss.coluqSamplingStationID
WHERE ssa.colSampleID = #SampleNo  tbl_ENV_SampleLogin.JobID=#JobID 

tbl_env_samplelogin sl
        INNER JOIN tbl_env_sample s ON sl.coluqsamplejobid = s.coluqsamplejobid
        AND sl.colsignoffdate IS NOT NULL
        AND s.colHoldStatus = 0
        INNER JOIN tbl_SM_REG_Sampling sampling ON sl.coluqSamplingID = sampling.coluqSamplingID
        LEFT JOIN tbl_SM_REG_TaskRegistration tr ON tr.coluqTaskRegistrationID= sampling.coluqTaskRegistrationID


--------------------------------------------------------------------
  

-------------------------------------获取现场采样信息---------------------------


-----------------------------------------
Select
       sampling.colRegistrationID AS taskID,
       rls.colTaskReleaseDate AS taskAssignDate,
       sas.coluqSamplingSampleID AS locID,
       sas.colWorkAssignMonitoringDate AS date,
       rsldep.colSignOffBy AS connID,
       u.colUserName AS person,
    taskrg.coluqTaskRegistrationID AS uqProjectID,
    taskrg.colTaskRegistrationID AS projectID,
    pcpn.colProjectName AS projectName 
FROM
      tbl_SM_REG_TaskRegistration taskrg
      LEFT JOIN tbl_SM_REG_Sampling sampling 
        ON sampling.coluqTaskRegistrationID = taskrg.coluqTaskRegistrationID
      LEFT JOIN tbl_SM_REG_TaskRelease rls 
        ON sampling.coluqSamplingID = rls.coluqSamplingID
      INNER JOIN tbl_SM_REG_SamplingElementDetails sed 
        ON sed.coluqSamplingID = rls.coluqSamplingID
      INNER JOIN tbl_SM_REG_SamplingElement se 
        ON se.coluqSamplingElementID = sed.coluqSamplingElementID
      INNER JOIN tbl_SM_REG_SamplingStation ss 
        ON ss.coluqSamplingElementDetailsID = sed.coluqSamplingElementDetailsID
      INNER JOIN tbl_SM_REG_SamplingSample sas 
        ON ss.coluqSamplingStationID = sas.coluqSamplingStationID
      LEFT JOIN tbl_PLM_ClientProjectName pcpn 
        ON taskrg.colCPNameID = pcpn.colCPNameID
      LEFT JOIN tbl_SM_REG_TaskReleaseDepartment rsldep 
        ON rls.coluqTaskReleaseID = rsldep.coluqTaskReleaseID
      LEFT JOIN tbl_User u 
        ON rsldep.colSignOffBy = u.colUserID
    WHERE
      sampling.colRegistrationID =  #{taskID}



    station.colStationID AS stationID,
    sample.colSampleNumber AS sampleID,
       sample.colSampleName AS sampleName,
       test.colTestName AS monitoringProgram,
      sample.colTimeStart AS samplingStartDate,
      sample.colTimeEnd AS samplingEndDate,
    lib.colParameterName AS parameter,
       assginName.colAssignName AS deviceName,
    unit.colUnit AS unit,
    sp.colResult AS [value]
  FROM
      tbl_SM_REG_Sampling sampling
      LEFT JOIN tbl_SM_REG_SamplingElementDetails eleDetails 
        ON sampling.coluqSamplingID = eleDetails.coluqSamplingID
      LEFT JOIN tbl_SM_REG_SamplingStation station 
        ON eleDetails.coluqSamplingElementDetailsID = station.coluqSamplingElementDetailsID
      LEFT JOIN tbl_SM_REG_SamplingSample sample 
        ON station.coluqSamplingStationID = sample.coluqSamplingStationID
      LEFT JOIN tbl_SM_REG_SamplingTest stest 
        ON sample.coluqSamplingSampleID = stest.coluqSamplingSampleID
      LEFT JOIN tbl_SM_REG_SamplingParameter sp 
        ON stest.coluqSamplingTestID = sp.coluqSamplingTestID
      LEFT JOIN tbl_ENV_TestParameter tp 
        ON sp.coluqTestParameterID = tp.coluqTestParameterID
      LEFT JOIN tbl_Env_FieldTest fieldTest 
        ON tp.coluqTestParameterID = fieldTest.coluqTestParameterID
      LEFT JOIN tbl_ENV_TestMethod tmethod 
        ON stest.coluqTestMethodID = tmethod.coluqTestMethodID
      LEFT JOIN tbl_ENV_Test test 
        ON tmethod.coluqTestID = test.coluqTestID
      LEFT JOIN tbl_Public_ParameterLibrary lib 
        ON tp.coluqParameterID = lib.coluqParameterID 
      LEFT JOIN tbl_ENV_Unit unit 
        ON unit.coluqUnitID= tp.coluqUnitID
      LEFT JOIN tbl_QC_LAB_AssignName assginName 
        ON assginName.coluqAssignName= sp.coluqAssignName
    WHERE
      sampling.colRegistrationID =  #{taskID}

--------------------------新建qrtz_alart_input_test---------------
  create table qrtz_alart_input_test(
    id int primary key auto_increment,
    name varchar(200),
    description varchar(250),
    model varchar(100),
    run_model varchar(100) NOT NULL,
    cateory varchar(200),
    cron_entry varchar(120),
    data_fields_id int ,
    data_format varchar(100),
    fetcher_id int NOT NULL,
    analyzer_id int NOT NULL,
    transmit_id int NOT NULL
  )ENGINE=InnoDB DEFAULT CHARSET=utf8; 

  -- alter table qrtz_alart_input_test add column run_model varchar(100) NOT NULL after model;
  -- alter table qrtz_alart_input_test change cron_extry cron_entry
  create table qrtz_alart_fetcher_test(
    id int primary key auto_increment,
    fetcher_type varchar(200) NOT NULL,
    fetcher_target varchar(200) NOT NULL,
    fetcher_mappings varchar(250),
    fetcher_filter varchar(200),
    fetcher_path varchar(250)
  )

  create table qrtz_alart_analyzer_test(
    id int primary key auto_increment,
    analyzer_type varchar(200) NOT NULL,
    analyzer_expression varchar(250)
  )

  create table qrtz_alart_transmit_test(
    id int primary key auto_increment,
    transmit_id int NOT NULL,
    subscriptions_id int,
    transmit_type varchar(200) NOT NULL,
    transmit_style varchar(200) NOT NULL,
    wrapper varchar(200)
  )

  create table qrtz_alart_data_feilds_test(
      id int primary key auto_increment,
      analyzer_id int NOT NULL,
      input_id int ,
      data_field varchar(200)
  )

  create table qrtz_alart_subscriptions_test(
    id int primary key auto_increment,
    transmit_id int NOT NULL,
    input_id int,
    subscript varchar(200)
  )

  -- 建立多对多关系表
  create table qrtz_input_relation_test(
    input_id int,
    fetcher_id int,
    analyzer_id int,
    transmit_id int,
    primary key (input_id,fetcher_id,anaylzer_id,transmit_id)
  )

  alter table qrtz_input_relation_test ADD CONSTRAINT pk primary KEY(input_id,fetcher_id,anaylzer_id,transmit_id)
  alter table qrtz_input_relation_test ADD CONSTRAINT 
      pk FOREIGN KEY(input_id) REFERENCES qrtz_alart_input_test(id) ON DELETE CASCADE

-- 未知原因没有绑定到 qrtz_alart_fetcher_test 的 fetcher_id
  alter table qrtz_input_relation_test ADD CONSTRAINT 
      fk_fetcher FOREIGN KEY(fetcher_id) REFERENCES qrtz_alart_fetcher_test(id)  ON DELETE CASCADE   

  alter table qrtz_input_relation_test ADD CONSTRAINT
      fk_anaylzer FOREIGN KEY(anaylzer_id) REFERENCES qrtz_alart_analyzer_test(id)  ON DELETE CASCADE

  alter table qrtz_input_relation_test ADD CONSTRAINT 
      fk_transmit FOREIGN KEY(transmit_id) REFERENCES qrtz_alart_transmit_test(id)  ON DELETE CASCADE
      
  --查看表结构 
    desc table_name
    show create table table_name

   --删除自增 
   alter table qrtz_alart_anaylzer_test modify id int not null;
   --删除主键
   alter table qrtz_alart_anaylzer_test drop primary key;
   --重新添加主键
   alter table qrtz_input_relation_test add primary key(id, fetcher_id);
   --重新添加自增
   alter table table_name modify column_name auto_increment;
   --删除外键
   alter table table_name drop foreign key fk_transmit;
   --列的重命名
   alter table table_name change old_column_name new_column_name int;
   --表的重命名
   alter table table_name RENAME TO table_name_new
   --增加一列
   ALTER TABLE table_name ADD COLUMN column_name int
   --删除一列
   ALTER TABLE table_name DROP COLUMN column_name 
   关联表被外键约束，那么insert与update的内容，必须满足对应的约束表的内容存在。

-----------------------------