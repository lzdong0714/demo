{
	/**
	 * 关于sql的case when
	 */
	case option
	WHEN dbo.SM_REG_SampleJoinMonitoredBy(sample.coluqSamplingSampleID) != ''
	THEN dbo.SM_REG_SampleJoinMonitoredBy(sample.coluqSamplingSampleID) 
	ELSE	
	dbo.SM_REG_ParameterJoinMonitoredBy(sp.coluqSamplingParameterID) END	
	as colJoinMonitoredBy,
}

{
	/**
	 * hadoop安装中的要点理解
	 */
	1 机器（硬件的资源）
	2 节点 （装系统）
	3 网络 （ip分配）
	4 主从 （备份管理等）
	5 集群管理


	/////////////linx安装
	///查看/etc/profile 文件查看JAVA环境 
	export JAVA_HOME=/usr/local/jdk1.8
	//安装解压包
	tar -zxvf hadoop-2.7.3.tar.gz -C /usr/local/hadoop
	//修改hadoop-evn.sh 添加JAVA环境变量
	export JAVA_HOME=/usr/local/jdk1.8

	//在/etc/profile文件中加入：
	export PATH=$PATH:/usr/local/hadoop/hadoop-2.7.3/bin:/usr/local/hadoop/hadoop-2.7.3/sbin

	//执行/etc/profile文件：
	source /etc/profile
	
	///////////////这里hadoop已经可以启动了
	///配置文件的路径
	/usr/local/hadoop/hadoop-2.7.3/etc/hadoop
	//修改节点名称与关系 core-site.xml
	
	//主机的ip重新命名
	在进行Hadoop集群配置中，需要在”/etc/hosts”文件中添加集群中所有机器的IP与主机名，
	这样Master与所有的Slave机器之间不仅可以通过IP进行通信，
	而且还可以通过主机名进行通信。所以在所有的机器上的”/etc/hosts”文件中都要添加如下内容：

	192.168.1.141 Master.Hadoop

	192.168.1.142 Slave1.Hadoop

	192.168.1.137 Slave2.Hadoop

	core-site中修改：
	
}

{
	--------draft---------------
	http://localhost:8081/job/deletejob?jobClassName=com.example.demo.job.NewJob&jobGroupName=3
	http://localhost:8081/job/addjob?jobClassName=com.hntycloud.web.handle.job.HelloJob&jobGroupName=ccc&cronExpression=0/5 * * * * ?
	http://localhost:8081/job/pausejob?jobClassName=com.hntycloud.web.handle.job.HelloJob&jobGroupName=ccc
	http://localhost:8081/job/resumejob?jobClassName=com.hntycloud.web.handle.job.HelloJob&jobGroupName=ccc
	http://localhost:8081/job/pausejob?jobClassName=com.hntycloud.web.handle.job.HelloJob&jobGroupName=ccc
}