
利元的BTLIMS登录
	username: service
	key: 123
database login:
	ip: 120.79.194.112
	databasename: lims
	username: sa
	key: Let@2018

Test BTLIMTS login
	username: admin
	key: ccic
database login:
	ip: 39.98.46.119
	databasename: BTLIMS
	username: sa
	key: Testtest()





SELECT
 a.name AS 存储过程名称,
 a.create_date AS 创建日期,
 a.modify_date AS 修改日期,
 b.last_execution_time AS 最后执行日期,
 b.execution_count AS 执行次数 
FROM
 sys.procedures a
 LEFT JOIN sys.dm_exec_procedure_stats b ON a.object_id = b.object_id 
 AND b.database_id = '7' 
WHERE
 a.is_ms_shipped = 0 --去掉系统存储过程 
ORDER BY
 b.last_execution_time DESC



库存报警表格：
	物品代码	 item.colItemID	
	物品名称	item.colItemName 		
	规格型号	item.colItemSpecification	
	包装单位	ic.colCategoryName			
	库存数量	item.StoredQty	
	警戒数量 item.colAlertQty
							
tbl_ICM_Measurement : item
tbl_ICM_Category : ic

	select
		item.coluqItemID AS materieluqID,
		item.colItemName AS materielName,
		item.colItemSpecification AS materielSpecification,
        im.colMeasurement AS wrapUnit,
		CONVERT ( nvarchar, item.StoredQty ) AS materielStore,
		item.colAlertQty AS alarmNumber
	from tbl_ICM_ItemDetails item
    LEFT JOIN tbl_ICM_Category ic
    ON item.colCategoryID = ic.colCategoryID
    LEFT JOIN tbl_ICM_Measurement im
    ON item.coluqUnitofMeasureID = im.colUnitID

sqlserver 
	表结构查询的语句: sp_columns + 表名
	Like查询匹配中文： LIKE N'%中文%'
	COALESCE() 函数使用，聚合行列;

"项目进度报警表格"：
	项目名称 ：	tbl_PLM_ClientProjectName.colProjectName
	委托单位 ：	tbl_PLM_Client.colClientName
	任务登记编号 ： tbl_SM_REG_TaskRegisteration.colTaskRegistrationID
	登记时间 ：	tbl_SM_REG_TaskRegisteration.colRegisteredDate
	当前完成状态 : 项目完成 | 未完成  #20190411 修改用来判断项目时候超期
	$(超期时间) ：
	计划周期	：	tbl_SM_REG_TaskRegisteration.colCompReportDate
select 
name.colProjectName as projectName, client.colClientName as clientName,
task.colTaskRegistrationID as taskID,task.colRegisteredDate as registerDate,
task.colCompReportDate as planDate
from tbl_SM_REG_TaskRegisteration task 
left join tbl_PLM_ClientProjectName  name on task.colCPNameID = name.colCPNameID
left join tbl_PLM_Client client on name.colClientID =  client.colClientID 



方案变更报警表格（任务管理模块）：
	变更日期（授权时间） ：tbl_SM_REG_SamplingChangeRequestDetails.colAuthorizedDate
	项目名称 ： tbl_PLM_ClientProjectName.colProjectName
	监测登记编号 （SR XXX）： tbl_SM_REG_Sampling.colRegistrationID
	变更人(授权人) ： tbl_SM_REG_Sampling.colLastValidatedBy
	变更种类 (等待 null ，批准 1 ，拒绝 0)： tbl_SM_REG_SamplingChangeRequest.colAcceptance

	select s.date,n.name,s.id,s.validateBy 
	from tbl_SM_REG_Sampling s 
	left join tbl_SM_REG_TaskRegisteration t on s.coluqTaskRegistrationID

tbl_SM_REG_TaskRegisteration

SELECT 
 	srd_out.AuthorizedDate,
	# srd_out.coluqSamplingRequestChangeID,
	srd_out.colAcceptance,
 	sampling.colRegistrationID,
 	name.colProjectName,
 	usr.colUserName
FROM	
	tbl_SM_REG_SamplingChangeRequest  sr
	LEFT JOIN tbl_SM_REG_Sampling sampling ON sampling.coluqSamplingID = sr.coluqSamplingID
	LEFT JOIN tbl_SM_REG_TaskRegistration tr ON tr.coluqTaskRegistrationID = sampling.coluqTaskRegistrationID
	LEFT JOIN tbl_PLM_ClientProjectName  name on tr.colCPNameID = name.colCPNameID
	LEFT JOIN
	(SELECT srd.colAuthorizedDate as AuthorizedDate,srd.coluqSamplingRequestChangeID AS id,
srd.colAuthorizedBy as colAuthorizedBy, srd.coluqSamplingRequestChangeID AS coluqSamplingRequestChangeID ,srd.colAcceptance AS colAcceptance
FROM
(select  max(colEnteredDate) as enterDate, coluqSamplingRequestChangeID AS id
From tbl_SM_REG_SamplingChangeRequestDetails  GROUP BY coluqSamplingRequestChangeID) srd_tmp
LEFT JOIN tbl_SM_REG_SamplingChangeRequestDetails srd 
ON srd_tmp.enterDate = srd.colEnteredDate) srd_out
ON srd_out.id = sr.coluqSamplingRequestChangeID
LEFT JOIN tbl_User usr ON usr.colUserID = srd_out.colAuthorizedBy
# WHERE srd_out.colAcceptance=1

仪器检定（仪器检定模块）：
	仪器编号授予名 ：tbl_QC_LAB_AssignName.colAssignName
	科室部门 ： tbl_Department.colProjectName
	上次鉴定日期 （SR XXX）：QC_LabwareInspectionType.LastInspectionDate
	下次鉴定日期(授权人) ： QC_LabwareInspectionType.NextInspectionDate


		lit.LastInspectionDate AS lastInspectTime,
		lit.InspectionFrequency AS inspectPeriod,
		lit.NextInspectionDate AS nextInspectTime 

SELECT * 
FROM
(SELECT DISTINCT
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
	WHERE lit.uqInspectionTypeID= '2'
	) tmp
-- 	WHERE tmp.assigName=N'B&K 声级计SS-22'
WHERE tmp.labwareUserId='LWR0649' 			




---------------------------------------------------------------------
SELECT DISTINCT
	jd.colUserJobID AS jobID,
	tr.colTaskRegistrationID as projectID,
	s.colRegistrationID as taskID,
	case
	when JSD.colBroughtByType =0 then
	(select colPDSCarrierName from tbl_PDS_Carrier where tbl_PDS_Carrier.colPDSCarrierID =
	JSD.colReceivedVia)
	else
	dbo.GetAssignedEmployeeName(JSD.coluqJobIDSampleDetailID,'JobDetailsBroughtBy')
	end
	as [BroughtBy],
    (select colusername from tbl_user where coluserid =
    JSD.colReceivedBy) as [ReceivedBy]  ,
    JSD.colReceivedDate as [ReceivedDate],
    JSD.colSCCComment as  SCCComment
from tbl_ENV_JobDetail Jd
	LEFT JOIN tbl_ENV_JobIdsampleDetail JSD ON Jd.coluqJobID=JSD.coluqJobID
	LEFT JOIN tbl_ENV_SampleLogin SL ON Jd.colUserJobID=SL.colUserJobID
	LEFT JOIN tbl_SM_REG_Sampling s ON SL.coluqSamplingID = s.coluqSamplingID
	LEFT JOIN tbl_SM_REG_TaskRegistration tr ON tr.coluqTaskRegistrationID = s.coluqTaskRegistrationID
	WHERE JSD.colSCCComment is NOT NULL and JSD.colSCCComment!= ''


---------------------------------------------------------------------

//聚合函数自定义
ALTER FUNCTION Select_LabwareDepartment_FN (@colUserLabwareID nvarchar(50))
returns nvarchar(1000)
AS
BEGIN
DECLARE @labdept nVARCHAR(1000)
set @labdept = N''

Select @labdept = @labdept + d.colDeptName + N', ' from  tbl_QC_Labware lb
inner join tbl_QC_LAB_Department ld on ld.coluqLabwareID=lb.coluqLabwareID
		and lb.colUserLabwareID=@colUserLabwareID
inner join tbl_Department d on d.colDeptID=ld.colDeptID

if len(@labdept)>0
   set @labdept = left(@labdept,len(@labdept)-1)

  RETURN(@labdept)
  
END


# ----------------------------------------
	业务中获取任务号，人员信息,通过仪器号获取监测现场信息。