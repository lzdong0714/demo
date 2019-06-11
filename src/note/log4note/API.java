--------------------------样品信息流程------------------------------
	样品信息流程：
		样品采样——>样品交接——>样品分批——（实际检测）——>结果批准

	贯穿的是jobID(workID),冲这里查询。

	所有样品在上述流程中有 1 等待状态 2 通过状态

		目前可以做的事从BTLIMES的每个流程状态中，查出当前状态的样品。

		需求是对样品处于那种状态进行信息提取与判断，相当于样品溯源。

	获取信息方案：
		1 获取每个状态的样品信息，在controller聚合 ，通过projectID，taskID,jobID,SampleID遍历查询
			明确可控，但是遍历效果慢，
		

		2 写一个sql，联合所有相关的表，通过字段的状态判断
			底层数据库，操作快，但是无法验证准确性



	样品采样：
		文档报警需求：
			采样超时：sampleDetailIInfo.samplingEndDate > 当前时间

	样品交接：
		Web报警需求：
			样品退样：损坏或异常
		文档报警需求：
			运输超时：时长超过18小时
			样品容器异常：损坏，不保温

	样品分批：
		等待分批：
		已经分批并给与编号：


	结果批准：
		等待批准：
		批准通过|不通过：


	样品分批信息：
	{
		"projectID":"TR",
		"taskID":"SR",
		"jobID":"C190",
		"sample":
			{
				"ID":"C190.01",
				"clientSampleID":"",
				"Units":"",
				"matrix":"",
				"Test":"",
				"Method":"",
				"DateRecieved":"",
				"signoffDate":"",
				"compList":""
			}
	}

--------------现场采样报警信息----------------

	任务

	时间

	地点

	人物

	仪器

	样品


	taskID (string, optional): 任务单ID
		
	taskAssignDate (string, optional): 任务下达时间 ,

	expectSamplingDate (Array[string], optional): 预期采样时间 ,

	stationID (string, optional): 测点ID

	samplingPerson (string, optional): 采样人员
	samplingEndDate (string, optional): 采样结束时间 ,
	samplingStartDate (string, optional): 采样开始时间 , 

	ln.colAssignName AS [colResultInstrument],

	monitoringProgram (string, optional): 监测项目 ,
	sampleName (string, optional): 样品名称 

-----------------------BTLIMS现场信息-----------------------------------

	点位信息： 
		共同：风向，风速，大气压，温度，相对湿度，天气；
				预计经纬度， 实际经纬度；

		水：透明度K1，K2,K,层深，河宽，流速，
		空气：。。。
		噪声：。。。
		废水：。。。
		烟气：。。。
		固体：。。。
		废气：。。。

	采样信息：
		共同：样品点位，样品名称，监测项目，分析仪器
       
		水：透明度K1，K2,K,层深，河宽，流速，
		空气：。。。
		噪声：。。。
		废水：。。。
		烟气：。。。
		固体：。。。
		废气：。。。

		共同：
			监测日期 - 监测人 - 共同监测人
			键入者 - 键入时间
			审核人 - 审核时间
			批准人 - 批准时间

	现场监测记录：
		共同：
			样品名称
			监测项目
			方法依据
				检测参数，结果，单位(如果这些结果可以是现场获取得到才能存在)
			分析仪器
			分析时间

	现场采样报警信息挖掘：
		《环境监测判别与预警规则库(rule fact需求信息)》
			二 现场信息类
				1 采样员到达采样现场通知 
				2 采样点偏离报警			仪器定位信息提取（海纳云中），预取点位设定（报警系统的设定）
				3 现场环境异常报警		
				4 现场仪器测量/采样指标报警

		 

---------------hnty-alarter--------------------
	读取指定路径下的文件规则，然后加载到 -> SynRule rule : synRules.values();
	对每个文件实例化规则引擎 -> ruleEngine = SynRuleEngine.genFromRule(config, rule);
	将规则引擎注册到代理中 -> ruleEngineMgr.reg(rule.getName(), ruleEngine);// 注册
	对每个规则信息添加到定时任务的中 -> addNewRuleTask(rule) {
								->JobDetail jobDetail = JobBuilder.newJob(SynRuleEngineJob.class)
								-> scheduler.scheduleJob(jobDetail, trigger);
							}
	执行定时任务 -> SynRuleEngineJob {
								run(TaskExecutionContext context){
									//获取启动任务的上下文，获取任务名taskNmae，得到代理中的规则引擎
									ruleEngine=ruleEngineMgr.getRuleEngine(taskname);
									// 规则引擎执行
									ruleEngine.start();
									sendSuccess = true;
								}
							}
					ruleEngine.start() -> {
						//执行获取fether 获取API提供的Json
						//执行analyiz 规则判断条件
						//执行transmitter 发送结果消息
					}

	1 rules文件中存放规则文件：
		加载文件application.yml -> alarm.properties -> rule_name.yml
	
	2 加载到SynRule 类中，形成synrules的Map集合


	3 runAllRuleEngines()，遍历Map集合，运行规则
		//加载规则代理，就是SynRuleEngine的集合。
		SynRuleEngineManagement ruleEngineMgr = SpringApplicationContext
				.getBean(SynRuleEngineManagement.class);

		//从读取文件的synRules集合中获取SynRule，
		//SynRule匹配ymal中设定的各种参数，以及返回结果
		for (SynRule rule : synRules.values()) 
			SynRuleEngine ruleEngine; // 每个rule带有一个启动引擎
			//SynRuleEngine  
			try 
				ruleEngine = SynRuleEngine.genFromRule(config, rule);//根据规则文件加载了fetch analyzer transmit的实体类
				ruleEngine.getFetcher().setRuleEngine(ruleEngine);//
				//rule.setSynRuleEngine(ruleEngine); //在实时计算中需要
				ruleEngineMgr.reg(rule.getName(), ruleEngine);// 注册
				String runMode = rule.getRunMode();
				switch (runMode) 
				case ComConstants.RUN_PERIOD_MODE:
					if (addNewRuleTask(rule))//将rule转化为job，添加进入定时任务
					break;
				case ComConstants.RUN_REALTIME_MODE: // 实时任务中 在fetch中应该是stomp ，emq 等
					exePool.execute(ruleEngine );
					//ruleEngine.start();
				

		3-1 开启线程池
		3-2 遍历集合，执行规则

		@要点：
			1 规则文件的解析与执行,规则文件实际是一个map集合。
            2 对规则文件的写入，修改，达到操作规则的目的，可以操作文件，也可以转化为数据库。 
            3 解析规则，如果是实时任务，那么实时执行；定时任务（类似cron *），启用quartz调度器，轮询。
            4 public class QlExpressUtil implements ApplicationContextAware
            	自定义函数方法转换
            	自定义的操作符转换，用中文替换实际的函数操作（比较方法）
            	对于yml中的 sql

        	5 public class QLExpressAnalyzer extends Analyze{} 分析模块，
        		ruleFileParse(String ruleFile){}

        	yml 文件为MAP类型，读取为Map，
        		private void loadYmlRules() {}：中转化为rule对象
        		private void runAllRuleEngines()：
        			//将rule转化为规则引擎
        			中ruleEngine = SynRuleEngine.genFromRule(config, rule);
        			@SynRuleEngine.genFromRule(config, rules);
        				实例化fetcher analyzer transmitter 
        				fetcher 抽象类，是包含rule属性，并且继承Processor接口(????这是那种设计模式？)
        			// 添加代理注册
        			ruleEngineMgr.reg(rule.getName(), ruleEngine);// 注册



    4 Quzart 获取定时，执行操作;
    	//定义执行的Job类 
    	JobDetail jobDetail = JobBuilder.newJob(SynRuleEngineJob.class)
				.withIdentity(taskName, taskGroup).withDescription(description).build();

		//触发器
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger()
				.withIdentity(taskName, taskGroup).usingJobData(dataMap)
				.withDescription(description);

		//获取定时字段
		String scheduleOptions = synRule.getCronEntry();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleOptions);

		//执行定时任务
		CronTrigger trigger = triggerBuilder.withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, trigger);
		triggerBuilder.startNow(); // startAt endAt 现在启动


		//那么执行SynRuleEngineJob.class的工作
		public class SynRuleEngineJob extends JobComponent //对JobComponent的封装
		public abstract class JobComponent implements Job //对Job的封装
			实际还是执行Job中 
			@Override
			public void execute(JobExecutionContext context) throws JobExecutionException 

		封装后：
			@Override
			public boolean run(TaskExecutionContext context) throws Exception{
				// 获取规则引擎
				String taskname=taskParam.getString("name"); //获取名称
       			ruleEngine=ruleEngineMgr.getRuleEngine(taskname);
       			try {
		        	ruleEngine.start();
					sendSuccess = true;
				} catch (Exception e) {
					sendSuccess = false;
					e.printStackTrace();
				}
			}

			//规则引擎中
			SynRuleEngine ruleEngine
			ruleEngine.start() ->{
				process()
			}

			public void process() throws Exception{

			}

			public class ApiFetcher extends Fetcher {
				// 获取API数据
			}

    5 Easy rule 基础:
        	RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        	RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);
        	Rules rules = new Rules();
        	rules.register(new FizzRule());
        	rules.register(new BuzzRule());
       	 	rules.register(new RuleClass.FizzBuzzRule(new RuleClass.FizzRule(), new RuleClass.BuzzRule()));
        	rules.register(new NonFizzBuzzRule());

        	Facts facts = new Facts();
	        for (int i = 1; i <= 100; i++) 
	            facts.put("number", i);
	            fizzBuzzEngine.fire(rules, facts);
	            System.out.println();

----------------hnty-alarter 项目解析-----------------
	1 定时任务

	2 文件监听

	3 规则匹配 规则文件字段详细解释
		#基本信息
		name: lims_instrument_correct_expression_lzd #退样
		description: "获取仪器检定信息"
		mode: simple      #主要用于区分是pojo方式的，还是简单的基于表达式的方式，默认该项可以不配置，默认为simple。
		run_mode: period    #规则运行方式。可选：period | realtime，默认period 
		category: instrument_correct #主要用日期类，数字类别文本类别
		cron_entry: "0 0/3 * * * ? " #CRON表达式，run_mode为period时有效
		data_fields: #数据属性定义。用${field}引用属性值。系统预定义属性：count->数据条数，now->当前时间，用^{count}引用属性值
		  - assigName
		  - department
		  - lastInspectTime
		  - currentDate
		  - nextInspectTime		  
		data_format: json  


		#获取数据
		fetcher_type:   api #数据获取方式，可选：api | rdb | tsdb | cache | ms | lpc | inject
		fetcher_target: http://47.92.28.186:8003/hnty/lims/v1/repo/alarm_origin_info/device
		fetcher_mappings:   #mapping后的添加参数
		filter:         lastInspectTime>0 && taskEndTime<^{now}  #数据过滤条件。 &:与，|：或， !：非
		path:           

		#分析匹配
		analyzer_type: qlexpress #触发匹配类型：mvel(仅支持一种方式) |  any | flatline | frequency | spike | range | cardinality
		expression:   时间比较(时间转换(nextInspectTime), 现在) > 15           #storeNumber-alarmNumber >0
		timeframe: 


		#组装发送
		transmitter_type: stomp  #报警方式，可选：sms | email | db | stomp | api | lpc | debug | custom ...
		transmitter_style: origion # 两种，原始信息或者  包装信息
		wrapper: 尊敬的{subscriptions}，{assigName}上次校准日期为{lastInspectTime}，即将于{nextInspectTime}到期,请及时进行校准 #报警内容    
		subscriptions: #报警订阅者
		  - /alert/warn


		解释说明： 
			1 fetcher_type analyzer_type transmitter_type： 
				与 加载的额alert-config.yml 映射对照，获取className
				然后通过className反射加载为指定的执行Oject，即决定了执行方法
				已上为例，即是： ApiFetcher QLExpressAnalyzer StompTransmitter

			2 mode 控制为对expression的解析方式，一个是sample为pojo？？
					主要是analyzer模块对判定表达式的加载方式问题

			3 name 任务名称，同文件名称相同，目前是追踪规则的主键，对于分组还没有命名规则
			  description 任务说明

			4* run_mode 运行规则方式，主要划分工作流程的参数 period | realtime 
				决定是添加到quartz，还是启动线程工作


			5 fetcher_target ：获取资源数据
				ApiFetcher 模式中：
					response = restTemplate.getForObject(rule.getFetcherTarget(), JsonNode.class);
				StompFetcher 模式中：
					this.connect(rule.getFetcherTarget());//http://47.92.33.38:8801/WebSocketServer

			   path : 根据设定的 key 值从返回的 json串 中获取指定的 value 值; key1,key2,key3

			   fetcher_mappings：对 API 的字段与解析字段的对应，猜想与path一一对于应的。


			5  expression ：在analyzer的加载中，调用对应的执行类，执行表达式，
				对表达式的引用参数解析，按照 a_b_c 的格式解析 A.B.C 属性值，本例中对参数: nextInspectTime, 现在
				进行解析 。

			7 subscriptions 订阅者列表，发送对象
			  transmitter_style : 对分析结果的包装分类，如果是 origin，那么原样加载，
			  					加载 category 属性到每一条结果数据中
			  

	4 消息发送


---------------hnty-alarter 项目目标--------------
	1 UI化规则输入
		1-1
		1-2
	2 结果留存
		2-1
	3 报警反馈
		3-2 


	        
------------------Quzart 笔记------------------------
	//  scheduler 会执行 job 
	//  	下面就程序中出现的几个参数，看一下Quartz框架中的几个重要参数：

	1	Job和JobDetail
			Job是Quartz中的一个接口，接口下只有execute方法，在这个方法中编写业务逻辑；
			obDetail用来绑定Job，为Job实例提供许多属性：name, group, jobClass, JobDataMap;
			obDetail绑定指定的Job，每次Scheduler调度执行一个Job的时候，首先会拿到对应的Job，然后创建该Job实例，
			再去执行Job中的execute()的内容，任务执行结束后，关联的Job对象实例会被释放，且会被JVM GC清除。

			为什么设计成JobDetail + Job，不直接使用Job?
				JobDetail定义的是任务数据，而真正的执行逻辑是在Job中。 
				这是因为任务是有可能并发执行，如果Scheduler直接使用Job，
				就会存在对同一个Job实例并发访问的问题。而JobDetail & Job 方式，
				Sheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以规避并发访问的问题

	2	JobExecutionContext
			JobExecutionContext中包含了Quartz运行时的环境以及Job本身的详细数据信息。 
			当Schedule调度执行一个Job的时候，就会将JobExecutionContext传递给该Job的execute()中，
			Job就可以通过JobExecutionContext对象获取信息。

	3	JobDataMap
			JobDataMap实现了JDK的Map接口，可以以Key-Value的形式存储数据。 
			JobDetail、Trigger都可以使用JobDataMap来设置一些参数或信息， 
			Job执行execute()方法的时候，JobExecutionContext可以获取到JobExecutionContext中的信息
				jobExecutionContext.getJobDetail().getJobDataMap().get("jobDetail1")
				jobExecutionContext.getTrigger().getJobDataMap().get("trigger1")

	4	Trigger、SimpleTrigger、CronTrigger
			4-1 Trigger是Quartz的触发器，会去通知Scheduler何时去执行对应Job
				new Trigger().startAt():表示触发器首次被触发的时间;
				new Trigger().endAt():表示触发器结束触发的时间;

			4-2 SimpleTrigger可以实现在一个指定时间段内执行一次作业任务或一个时间段内多次执行作业任务
				Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
			                .usingJobData("trigger1", "这是jobDetail1的trigger")
			                .startNow()//立即生效
			                .startAt(startDate)
			                .endAt(endDate)
			                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
			                .withIntervalInSeconds(1)//每隔1s执行一次
			                .repeatForever()).build();//一直执行



			4-3 CronTrigger功能非常强大，是基于日历的作业调度，而SimpleTrigger是精准指定间隔，
				所以相比SimpleTrigger，CroTrigger更加常用。CroTrigger是基于Cron表达式的
				CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
						                .usingJobData("trigger1", "这是jobDetail1的trigger")
						                .startNow()//立即生效
						                .startAt(startDate)
						                .endAt(endDate)
						                .withSchedule(CronScheduleBuilder.cronSchedule("* 30 10 ? * 1/5 2018"))
						                .build();

            4-4 添加 quartz后可以直接加载@Scheduled(cron = "0 0 2 * * ?")//定时器去定时执行任务
	5 任务的CRUD，以及持久化：
		5-1	Quartz建表语句：
			{}
				// quartz的sql
				// DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
				DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
				DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
				DROP TABLE IF EXISTS QRTZ_LOCKS;
				DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
				DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
				DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
				DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
				DROP TABLE IF EXISTS QRTZ_TRIGGERS;
				DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
				DROP TABLE IF EXISTS QRTZ_CALENDARS;

				CREATE TABLE QRTZ_JOB_DETAILS(
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  JOB_NAME VARCHAR(200) NOT NULL,
				  JOB_GROUP VARCHAR(200) NOT NULL,
				  DESCRIPTION VARCHAR(250) NULL,
				  JOB_CLASS_NAME VARCHAR(250) NOT NULL,
				  IS_DURABLE VARCHAR(1) NOT NULL,
				  IS_NONCONCURRENT VARCHAR(1) NOT NULL,
				  IS_UPDATE_DATA VARCHAR(1) NOT NULL,
				  REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
				  JOB_DATA BLOB NULL,
				  PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_TRIGGERS (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  TRIGGER_NAME VARCHAR(200) NOT NULL,
				  TRIGGER_GROUP VARCHAR(200) NOT NULL,
				  JOB_NAME VARCHAR(200) NOT NULL,
				  JOB_GROUP VARCHAR(200) NOT NULL,
				  DESCRIPTION VARCHAR(250) NULL,
				  NEXT_FIRE_TIME BIGINT(13) NULL,
				  PREV_FIRE_TIME BIGINT(13) NULL,
				  PRIORITY INTEGER NULL,
				  TRIGGER_STATE VARCHAR(16) NOT NULL,
				  TRIGGER_TYPE VARCHAR(8) NOT NULL,
				  START_TIME BIGINT(13) NOT NULL,
				  END_TIME BIGINT(13) NULL,
				  CALENDAR_NAME VARCHAR(200) NULL,
				  MISFIRE_INSTR SMALLINT(2) NULL,
				  JOB_DATA BLOB NULL,
				  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
				  FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
				  REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  TRIGGER_NAME VARCHAR(200) NOT NULL,
				  TRIGGER_GROUP VARCHAR(200) NOT NULL,
				  REPEAT_COUNT BIGINT(7) NOT NULL,
				  REPEAT_INTERVAL BIGINT(12) NOT NULL,
				  TIMES_TRIGGERED BIGINT(10) NOT NULL,
				  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
				  FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
				  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_CRON_TRIGGERS (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  TRIGGER_NAME VARCHAR(200) NOT NULL,
				  TRIGGER_GROUP VARCHAR(200) NOT NULL,
				  CRON_EXPRESSION VARCHAR(120) NOT NULL,
				  TIME_ZONE_ID VARCHAR(80),
				  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
				  FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
				  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_SIMPROP_TRIGGERS
				(
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  TRIGGER_NAME VARCHAR(200) NOT NULL,
				  TRIGGER_GROUP VARCHAR(200) NOT NULL,
				  STR_PROP_1 VARCHAR(512) NULL,
				  STR_PROP_2 VARCHAR(512) NULL,
				  STR_PROP_3 VARCHAR(512) NULL,
				  INT_PROP_1 INT NULL,
				  INT_PROP_2 INT NULL,
				  LONG_PROP_1 BIGINT NULL,
				  LONG_PROP_2 BIGINT NULL,
				  DEC_PROP_1 NUMERIC(13,4) NULL,
				  DEC_PROP_2 NUMERIC(13,4) NULL,
				  BOOL_PROP_1 VARCHAR(1) NULL,
				  BOOL_PROP_2 VARCHAR(1) NULL,
				  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
				  FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
				  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_BLOB_TRIGGERS (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  TRIGGER_NAME VARCHAR(200) NOT NULL,
				  TRIGGER_GROUP VARCHAR(200) NOT NULL,
				  BLOB_DATA BLOB NULL,
				  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
				  INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),
				  FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
				  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_CALENDARS (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  CALENDAR_NAME VARCHAR(200) NOT NULL,
				  CALENDAR BLOB NOT NULL,
				  PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  TRIGGER_GROUP VARCHAR(200) NOT NULL,
				  PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_FIRED_TRIGGERS (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  ENTRY_ID VARCHAR(95) NOT NULL,
				  TRIGGER_NAME VARCHAR(200) NOT NULL,
				  TRIGGER_GROUP VARCHAR(200) NOT NULL,
				  INSTANCE_NAME VARCHAR(200) NOT NULL,
				  FIRED_TIME BIGINT(13) NOT NULL,
				  SCHED_TIME BIGINT(13) NOT NULL,
				  PRIORITY INTEGER NOT NULL,
				  STATE VARCHAR(16) NOT NULL,
				  JOB_NAME VARCHAR(200) NULL,
				  JOB_GROUP VARCHAR(200) NULL,
				  IS_NONCONCURRENT VARCHAR(1) NULL,
				  REQUESTS_RECOVERY VARCHAR(1) NULL,
				  PRIMARY KEY (SCHED_NAME,ENTRY_ID))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_SCHEDULER_STATE (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  INSTANCE_NAME VARCHAR(200) NOT NULL,
				  LAST_CHECKIN_TIME BIGINT(13) NOT NULL,
				  CHECKIN_INTERVAL BIGINT(13) NOT NULL,
				  PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))
				  ENGINE=InnoDB;

				CREATE TABLE QRTZ_LOCKS (
				  SCHED_NAME VARCHAR(120) NOT NULL,
				  LOCK_NAME VARCHAR(40) NOT NULL,
				  PRIMARY KEY (SCHED_NAME,LOCK_NAME))
				  ENGINE=InnoDB;

				CREATE INDEX IDX_QRTZ_J_REQ_RECOVERY ON QRTZ_JOB_DETAILS(SCHED_NAME,REQUESTS_RECOVERY);
				CREATE INDEX IDX_QRTZ_J_GRP ON QRTZ_JOB_DETAILS(SCHED_NAME,JOB_GROUP);

				CREATE INDEX IDX_QRTZ_T_J ON QRTZ_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
				CREATE INDEX IDX_QRTZ_T_JG ON QRTZ_TRIGGERS(SCHED_NAME,JOB_GROUP);
				CREATE INDEX IDX_QRTZ_T_C ON QRTZ_TRIGGERS(SCHED_NAME,CALENDAR_NAME);
				CREATE INDEX IDX_QRTZ_T_G ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
				CREATE INDEX IDX_QRTZ_T_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE);
				CREATE INDEX IDX_QRTZ_T_N_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
				CREATE INDEX IDX_QRTZ_T_N_G_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
				CREATE INDEX IDX_QRTZ_T_NEXT_FIRE_TIME ON QRTZ_TRIGGERS(SCHED_NAME,NEXT_FIRE_TIME);
				CREATE INDEX IDX_QRTZ_T_NFT_ST ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
				CREATE INDEX IDX_QRTZ_T_NFT_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
				CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
				CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

				CREATE INDEX IDX_QRTZ_FT_TRIG_INST_NAME ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME);
				CREATE INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
				CREATE INDEX IDX_QRTZ_FT_J_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
				CREATE INDEX IDX_QRTZ_FT_JG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_GROUP);
				CREATE INDEX IDX_QRTZ_FT_T_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
				CREATE INDEX IDX_QRTZ_FT_TG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);

				commit;


				// #集群配置  
				org.quartz.scheduler.instanceName: DefaultQuartzScheduler  
				org.quartz.scheduler.rmi.export: false  
				org.quartz.scheduler.rmi.proxy: false  
				org.quartz.scheduler.wrapJobExecutionInUserTransaction: false  

				org.quartz.threadPool.class: org.quartz.simpl.SimpleThreadPool  
				org.quartz.threadPool.threadCount: 10  
				org.quartz.threadPool.threadPriority: 5  
				org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread: true  

				org.quartz.jobStore.misfireThreshold: 60000  

				// // #============================================================================  
				// # Configure JobStore  
				// #============================================================================  

				// #默认配置，数据保存到内存  
				#org.quartz.jobStore.class: org.quartz.simpl.RAMJobStore  
				// #持久化配置  
				org.quartz.jobStore.class:org.quartz.impl.jdbcjobstore.JobStoreTX  
				org.quartz.jobStore.driverDelegateClass:org.quartz.impl.jdbcjobstore.StdJDBCDelegate  
				org.quartz.jobStore.useProperties:true  
				// #数据库表前缀  
				#org.quartz.jobStore.tablePrefix:qrtz_  
				#org.quartz.jobStore.dataSource:qzDS  

				// #============================================================================  
				// # Configure Datasources  
				// #============================================================================  
				#JDBC驱动  
				#org.quartz.dataSource.qzDS.driver:com.mysql.jdbc.Driver  
				#org.quartz.dataSource.qzDS.URL:jdbc:mysql://localhost:3306/quartz  
				#org.quartz.dataSource.qzDS.user:root  
				#org.quartz.dataSource.qzDS.password:christmas258@  
				#org.quartz.dataSource.qzDS.maxConnection:10  
				
			{}

		5-2 properties 配置：
			如果写在 application.yml中{
				spring:
				  quartz:
				    #相关属性配置
				    properties:
				      org:
				        quartz:
				          scheduler:
				            instanceName: clusteredScheduler
				            instanceId: AUTO
				          jobStore:
				            class: org.quartz.impl.jdbcjobstore.JobStoreTX
				            driverDelegateClass: org.quartz.impl.jdbcjobstore.StdJDBCDelegate
				            tablePrefix: QRTZ_
				            isClustered: true
				            clusterCheckinInterval: 10000
				            useProperties: false
				          threadPool:
				            class: org.quartz.simpl.SimpleThreadPool
				            threadCount: 10
				            threadPriority: 5
				            threadsInheritContextClassLoaderOfInitializingThread: true
				    #数据库方式
				    job-store-type: jdbc
				    #初始化表结构
				    #jdbc:
				      #initialize-schema: never
			}则会通过：
			@Bean
			@ConditionalOnMissingBean
			public SchedulerFactoryBean quartzScheduler() {}
			去构建属性对象，这里有注入失败经历；

			所以写在 quartz.properties 文件中：{
				org.quartz.scheduler.instanceName = DefaultQuartzScheduler
				org.quartz.scheduler.rmi.export = false
				org.quartz.scheduler.rmi.proxy = false
				org.quartz.scheduler.wrapJobExecutionInUserTransaction = false

				# 实例化ThreadPool时，使用的线程类为SimpleThreadPool
				org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool

				# threadCount和threadPriority将以setter的形式注入ThreadPool实例
				# 并发个数
				org.quartz.threadPool.threadCount = 5
				# 优先级
				org.quartz.threadPool.threadPriority = 5
				org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread = true

				org.quartz.jobStore.misfireThreshold = 5000

				# 默认存储在内存中
				#org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore

				#持久化
				org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX

				org.quartz.jobStore.tablePrefix = QRTZ_

				org.quartz.jobStore.dataSource = qzDS

				org.quartz.dataSource.qzDS.driver = com.mysql.cj.jdbc.Driver

				#org.quartz.dataSource.qzDS.URL = jdbc:mysql://190.0.1.88:3306/hello_test?useUnicode=true&characterEncoding=UTF-8
				org.quartz.dataSource.qzDS.URL = jdbc:mysql://localhost:3306/quartz?serverTimezone=GMT%2B8

				org.quartz.dataSource.qzDS.user = root

				org.quartz.dataSource.qzDS.password = hntywork

				org.quartz.dataSource.qzDS.maxConnections = 10
			}

		5-3 

----------------------springboot-----------------------

	mybaits：
		mapper-locations:
		// 设定sql.xml的扫描包
		- classpath:mapper/*.xml  */
		// 设定实体关联包
		type-aliases-package: com.example.demo.entity
		// 设定驼峰命名规则，即entity中的属性命名proName
		mybatis.configuration.mapUnderscoreToCamelCase: true
		// #驼峰下划线转换,将sql中的aaa_bbb变为aaaBbb,由于数据库不区分大小写，
		// 用'_' 分割，所以有这个配置属性
    	db-column-underline: true
	// 使用dev的环境配置
	spring:
		profile:
			active: dev
	---
	spring:
		profiles: dev

	---

	spring:
		profiles: test


  	mybatis的insert,update,delete 返回值，成功值返回1，失败值返回0，错误就抛异常。
  	mybatis的

  	默认下,使用select xxx where in(xx,xx)查询,返回结果是按主键排序的,如果要按in()中值的排列顺序,可以这样做:
	select * from talbe where id in(3,2,4,1) ORDER BY FIND_IN_SET( id, '3,2,4,1') 

	条件判断
------------------------------------------------------------------------
