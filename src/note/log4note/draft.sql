QC_LabwareInspectionType lit ON lw.couqLabwareID= lit.uqLabwareID ( 1 2  3)
tbl_QC_InspectionType uqInspectionTypeID (1 2 3)
QC_LabwareInspection


SELECT DISTINCT
	lw.couqlLabwareID as id,
	lw.colUserLabwareID as labwareUserId,
	dep.colDeptName AS department,
	la.colAssignName AS assigName,
	lit.LastInspectionDate AS lastInspectTime,
	lit.InspectionFrequency AS inspectPeriod,
	qli.Status AS inspectResult,
	lit.NextInspectionDate AS nextInspectTime
FROM
	tbl_QC_Labware lw
	INNER JOIN tbl_QC_LAB_Department tmp1 ON tmp1.coluqLabwareID = lw.couqlLabwareID
	INNER JOIN tbl_Department dep ON tmp1.colDeptID = dep.colDeptID
	LEFT JOIN QC_LabwareInspectionType lit ON lw.coluqLabwareID= lit.uqLabwareID 
-- 	LEFT JOIN QC_LabwareInspectionType lit ON lw.couqlLabwareID= lit.uqLabwareID 
	LEFT JOIN tbl_QC_LAB_AssignName la  ON la.coluqAssignName = lw.coluqAssignedNameID
	LEFT JOIN QC_LabwareInpection qli 
	ON lit.uqLabwareInspectionTypeID = qli.uqLabwareInspectionTypeID 


浏览的数据
SELECT
	lw.couqlLabwareID as id,
	lw.colUserLabwareID as labwareUserId,
	dep.colDeptName AS department,
	la.colAssignName AS assigName,
	lit.LastInspectionDate AS lastInspectTime,
	lit.InspectionFrequency AS inspectPeriod,
	qli.Status AS inspectResult,
	lit.NextInspectionDate AS nextInspectTime
FROM
	QC_LabwareInpection li
	LEFT JOIN QC_LabwareInspectionType  lit on lit.uqLabwareInspectionTypeID = li.uqLabwareInspectionTypeID
	INNER JOIN tbl_QC_LAB_Department tmp1 ON tmp1.coluqLabwareID =lit.uqLabwareID
	INNER JOIN tbl_Department dep ON tmp1.colDeptID = dep.colDeptID
	LEFT JOIN tbl_QC_LAB_AssignName la  ON la.coluqAssignName = lw.coluqAssignedNameID
	LEFT JOIN QC_LabwareInpection qli ON lit.uqLabwareInspectionTypeID = qli.uqLabwareInspectionTypeID 



SELECT DISTINCT
 	lit.uqLabwareID,
 	la.colAssignName AS assigName,
	dep.colDeptName AS department,
	lit.LastInspectionDate AS lastInspectTime,
	lit.InspectionFrequency AS inspectPeriod,
	qli.Status AS inspectResult,
	lit.NextInspectionDate AS nextInspectTime
FROM
	SELECT

	QC_LabwareInpection li
	INNER JOIN QC_LabwareInspectionType lit ON lit.uqLabwareInspectionTypeID=li.uqLabwareInspectionTypeID
	INNER JOIN tbl_QC_LAB_Department tmp1 ON tmp1.coluqLabwareID = lit.uqLabwareID
	INNER JOIN tbl_Department dep ON tmp1.colDeptID = dep.colDeptID
	INNER JOIN tbl_QC_Labware lw ON lw.coluqLabwareID= lit.uqLabwareID
	INNER JOIN tbl_QC_LAB_AssignName la  ON la.coluqAssignName = lw.coluqAssignedNameID
	LEFT JOIN QC_LabwareInpection qli ON lit.uqLabwareInspectionTypeID = qli.uqLabwareInspectionTypeID


键入的数据

SELECT 
	lit.uqLabwareID,
 	la.colAssignName AS assigName,
	dep.colDeptName AS department,
	lit.LastInspectionDate AS lastInspectTime,
	lit.InspectionFrequency AS inspectPeriod,
	qli.Status AS inspectResult,
	lit.NextInspectionDate AS nextInspectTime
FROM QC_LabwareInspectionType lit
	LEFT JOIN tbl_QC_Labware lw ON lw.coluqLabwareID= lit.uqLabwareID 
	INNER JOIN tbl_QC_LAB_Department tmp1 ON tmp1.coluqLabwareID = lit.uqLabwareID
	INNER JOIN tbl_Department dep ON tmp1.colDeptID = dep.colDeptID
	INNER JOIN tbl_QC_LAB_AssignName la  ON la.coluqAssignName = lw.coluqAssignedNameID
	LEFT JOIN QC_LabwareInpection qli ON lit.uqLabwareInspectionTypeID = qli.uqLabwareInspectionTypeID 
	-- WHERE lit.uqInspectionTypeID= '2'
-----------------END------------------


样品编号20190410

SELECT DISTINCT
			sampling.colRegistrationID AS taskID,
	pname.colProjectName AS projectName,
			login.colUserJobID AS workID,
	bring.coluqUserID AS deliverID,
	u1.colUserName AS deliverPerson,
	u2.colUserName AS recipient,
	detail.colReceivedDate AS receiveDate,
	element.colElementName AS sampleType,
	detail.colNoOfSamples AS sampleNumber,
	detail.colNoOfContainers AS containerNumber 
FROM
		 	tbl_ENV_SampleLogin login
			LEFT JOIN tbl_SM_REG_Sampling sampling 
				ON login.coluqSamplingID = sampling.coluqSamplingID
			LEFT JOIN tbl_SM_REG_TaskRegistration tr
				ON tr.coluqTaskRegistrationID= sampling.coluqTaskRegistrationID

	LEFT JOIN tbl_PLM_ClientProjectName pname 
		ON login.colCPNameID = pname.colCPNameID
	LEFT JOIN tbl_env_JobIDSampleDetail detail 
		ON login.coluqJobID = detail.coluqJobID
	LEFT JOIN tbl_ENV_JobDetailBroughtBy bring 
		ON detail.coluqJobIDSampleDetailID = bring.coluqJobIDSampleDetailID
	LEFT JOIN tbl_User u1 
		ON bring.coluqUserID = u1.colUserID
	LEFT JOIN tbl_User u2 
		ON detail.colReceivedBy = u2.colUserID
	LEFT JOIN tbl_SM_REG_SamplingElementDetails eledetail 
		ON sampling.coluqSamplingID = eledetail.coluqSamplingID
	LEFT JOIN tbl_SM_REG_SamplingElement element 
		ON eledetail.coluqSamplingElementID = element.coluqSamplingElementID 

SELECT DISTINCT
		station.colStationID AS stationID,
		sample.colSampleID AS sampleID,
		element.colElementName AS sampleType,
		test.colTestName AS monitoringProgram,
				sample.colTimeStart AS samplingStartDate,
				sample.colTimeEnd AS samplingEndDate,
		u.colUserID AS personID,
		u.colUserName AS samplingPerson  
	FROM
		tbl_ENV_SampleLogin login
		LEFT JOIN tbl_SM_REG_SamplingElementDetails detail 
			ON login.coluqSamplingID = detail.coluqSamplingID
		LEFT JOIN tbl_SM_REG_SamplingStation station 
			ON detail.coluqSamplingElementDetailsID = station.coluqSamplingElementDetailsID

				LEFT JOIN tbl_SM_REG_SamplingSample sample 
					ON station.coluqSamplingStationID = sample.coluqSamplingStationID

		LEFT JOIN tbl_SM_REG_SamplingTest samplingTest 
			ON sample.coluqSamplingSampleID = samplingTest.coluqSamplingSampleID
		LEFT JOIN tbl_ENV_TestMethod method 
			ON samplingTest.coluqTestMethodID = method.coluqTestMethodID
		LEFT JOIN tbl_ENV_Test test 
			ON method.coluqTestID = test.coluqTestID
		LEFT JOIN tbl_SM_REG_SamplingElement element 
			ON detail.coluqSamplingElementID = element.coluqSamplingElementID
		LEFT JOIN tbl_SM_REG_SamplingStationWorkAssignTo assignTo 
			ON sample.coluqSamplingSampleID = assignTo.coluqSamplingSampleID
		LEFT JOIN tbl_User u 
			ON assignTo.colAssignTo = u.colUserID


-------------START-------------
SELECT DISTINCT
			tr.colTaskRegistrationID AS projectID,
			sampling.colRegistrationID AS taskID,
			login.colUserJobID AS workID,
			sample.colTimeStart AS samplingStartDate,
			sample.colTimeEnd AS samplingEndDate
FROM
	 	tbl_ENV_SampleLogin login
		LEFT JOIN tbl_SM_REG_Sampling sampling 
		ON login.coluqSamplingID = sampling.coluqSamplingID
		LEFT JOIN tbl_SM_REG_TaskRegistration tr
		ON tr.coluqTaskRegistrationID= sampling.coluqTaskRegistrationID
			
		LEFT JOIN tbl_SM_REG_SamplingElementDetails detail 
		ON login.coluqSamplingID = detail.coluqSamplingID
		LEFT JOIN tbl_SM_REG_SamplingStation station 
		ON detail.coluqSamplingElementDetailsID = station.coluqSamplingElementDetailsID
		LEFT JOIN tbl_SM_REG_SamplingSample sample 
		ON station.coluqSamplingStationID = sample.coluqSamplingStationID

------------------Test-----------------------

FROM
		 	tbl_ENV_SampleLogin login
			LEFT JOIN tbl_SM_REG_Sampling sampling 
			ON login.coluqSamplingID = sampling.coluqSamplingID
			LEFT JOIN tbl_SM_REG_TaskRegistration tr
			ON tr.coluqTaskRegistrationID= sampling.coluqTaskRegistrationID
 
			tbl_SM_REG_TaskRegistration tr
			LEFT JOIN tbl_SM_REG_Sampling sampling 
			ON tr.coluqTaskRegistrationID= sampling.coluqTaskRegistrationID
			LEFT JOIN tbl_ENV_SampleLogin login
			ON login.coluqSamplingID = sampling.coluqSamplingID

			LEFT JOIN tbl_SM_REG_SamplingElementDetails detail 
			ON login.coluqSamplingID = detail.coluqSamplingID
			LEFT JOIN tbl_SM_REG_SamplingStation station 
			ON detail.coluqSamplingElementDetailsID = station.coluqSamplingElementDetailsID
			LEFT JOIN tbl_SM_REG_SamplingSample sample 
			ON station.coluqSamplingStationID = sample.coluqSamplingStationID


----------------END-------------------


----------------------START 20190416----------------------------
----------------------------------------
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
			) AND	sl.colUserJobID = #{jobID}
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
------------------------------------------END 20190416

------------------------

<resultMap id="taskBaseInfoMap" type="TaskBaseInfo">
   		<result property="uqProjectID" column="uqProjectID"/>
   		<result property="projectID" column="projectID"/>
   		<result property="projectName" column="projectName"/>
   		<result property="taskID" column="taskID"/>
   		<result property="taskAssignDate" column="taskAssignDate"/>
   		<collection property="expectSamplingDates" ofType="MyDateResultMap">
   			<id property="locID" column="locID"/>
   			<result property="date" column="date"/>
   		</collection>
   		<collection property="signers" ofType="MyResultMap">
   			<id property="connID" column="connID"/>
   			<result property="value" column="person"/>
   		</collection>
   </resultMap>

SELECT DISTINCT
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
