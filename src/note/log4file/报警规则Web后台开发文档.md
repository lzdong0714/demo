## 规则管理与控制子系统

### 规则增删改查

#### 数据结构表

整个规则输入由以下4部分组成：

1. 基本规则

2. 获取模块规则

3. 分析模块规则

4. 发送模块规则

   规则组成的数据结构表：

   1. 基本规则表

   |    字段     |     类型     |      含义      |
   | :---------: | :----------: | :------------: |
   |     id      |   int(11)    |      主键      |
   |    name     | varchar(200) |    规格名称    |
   | description | varchar(250) |    规则说明    |
   |    model    | varchar(100) |   表达式方式   |
   |  run_model  | varchar(100) |    运行方式    |
   |   cateory   | varchar(200) |    规则类别    |
   | cron_entry  | varchar(100) |   定时表达式   |
   | data_format | varchar(100) |    数据格式    |
   | fetcher_id  |   int(11)    | 数据获取表主键 |
   | analyzer_id |   int(11)    | 分析匹配表主键 |
   | transmit_id |   int(11)    | 组装流转表主键 |

   2. 数据获取表

      |       字段       |     类型     |   含义   |
      | :--------------: | :----------: | :------: |
      |        id        |     int      |   主键   |
      |   fetcher_type   | varchar(200) | 获取方式 |
      |  fetcher_target  | varchar(200) | 资源地址 |
      | fetcher_mappings | varchar(250) | 映射参数 |
      |  fetcher_filter  | varchar(200) | 数据过滤 |
      |   fetcher_path   | varchar(250) |   路径   |

   3. 分析匹配表

      |        字段         |     类型     |    含义    |
      | :-----------------: | :----------: | :--------: |
      |         id          |   int(11)    |    主键    |
      |    analyzer_type    | varchar(200) |  分析方式  |
      | analyzer_expression | varchar(250) | 分析表达式 |

   4. 组装流转表

      |      字段      |     类型     |   含义   |
      | :------------: | :----------: | :------: |
      |       id       |   int(11)    |   主键   |
      | transmit_type  | varchar(200) | 发送类型 |
      | transmit_style | varchar(200) | 发送方式 |
      |    wrapper     | varchar(200) | 组装字段 |

#### 增删改查API

![img](C:\Users\联调13\AppData\Local\Temp\企业微信截图_15584927638471.png)

 1. 规则管理

    ​	查询：alart/rules/select  获取所有规则列表

    ​	增加：/rules/insert  要求所有的规则模块数据的json格式,例子如下：

    ```json
    {
    
    	"input":{
    
    		"name": "lims_project_proposal_warn_rule_lzd",
    		"description":"方案变更提示",
    		"model": "simple",
    		"runModel": "period", 
    		"category": "project_proposal", 
    		"cronEntry": "0 0/2 * * * ? ",
    		"dataFormat": "json"
    
    	},
    
    
    	"fetcher":
    	{
    		"fetcherType": "api",
    		"fetcherTarget":"http://47.92.28.186:8003/hnty/lims/v1/repo/alarm_origin_info/device",
    		"fetcherMappings":"",
    		"fetcherFilter":"taskId>0 && taskEndTime<^{now}",
    		"fetcherPath":null
    	},
    
    	"analyzer":
    	{
    
    		"analyzerType":"qlexpress",
    		"analyzerExpression":"1==1 "
    	},
    
    	"transmit":
    	{
    
    		"subscriptId":3,
    		"transmitType":"stomp",
    		"transmitStyle":"origion",
    		"wrapper":"尊敬的{subscriptions}，当前任务编号{registerId}，委托方客户是{clientName}，检验员{authorName}于{authorDate}更改方案状态为{status}，请关注"
    	},
    
    
    		"dataFields":
    		[
    			{
    				"dataField":"authorDate"
    			},
    			{
    				"dataField":"status"
    			},
    			{
    				"dataField":"registerId"
    			},
    			
    			{
    				"dataField":"projectName"
    			},
    			{
    
    				"dataField":"authorName"
    			}
    		],
    
    	
    	"subScripts":
    	[
    		{
    			"subScript":"/alert/warn"
    		}
    	]
    }
    ```

   2. 获取模块

   3. 分析模块

   4. 流转模块

      （ 可以用swagger直接补充？？）

### 规则控制模块

 1. 规则暂停恢复

    

 2. 规则的启动停止

### 规则编辑UI

​	![img](C:\Users\联调13\AppData\Local\Temp\企业微信截图_15584903493050.png)

![img](C:\Users\联调13\AppData\Local\Temp\企业微信截图_15584902993731.png)

![img](C:\Users\联调13\AppData\Local\Temp\企业微信截图_15584904036364.png)