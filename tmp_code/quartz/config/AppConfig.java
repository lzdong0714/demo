package com.example.quartz.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class AppConfig {
    @Value("${app.quartz.scheduler.instance-name}")
    private String quartzSchedulerInstanceName;
    @Value("${app.quartz.scheduler.instance-id}")
    private String quartzSchedulerInstanceId;
    @Value("${app.quartz.scheduler.skip-update-check}")
    private String quartzSchedulerSkipUpdateCheck;
    @Value("${app.quartz.scheduler.job-factory.class}")
    private String quartzSchedulerJobFactoryClass;
    @Value("${app.quartz.job-store.class}")
    private String quartzJobStoreClass;
    @Value("${app.quartz.job-store.driver-delegate-class}")
    private String quartzJobStoreDriverDelegateClass;
    @Value("${app.quartz.job-store.datasource}")
    private String quartzJobStoreDatasource;
    @Value("${app.quartz.job-store.table-prefix}")
    private String quartzJobStoreTablePrefix;
    @Value("${app.quartz.job-store.is-clustered}")
    private String quartzJobStoreIsClustered;
    @Value("${app.quartz.thread-pool.class}")
    private String quartzThreadPoolClass;
    @Value("${app.quartz.thread-pool.thread-count}")
    private String quartzThreadPoolThreadCount;

    @Value("${app.quartz.datasource.quartzDataSource.driver}")
    private String quartzDatasourceQuartzDataSourceDriver;
    @Value("${app.quartz.datasource.quartzDataSource.url}")
    private String quartzDatasourceQuartzDataSourceUrl;
    @Value("${app.quartz.datasource.quartzDataSource.user}")
    private String quartzDatasourceQuartzDataSourceUser;
    @Value("${app.quartz.datasource.quartzDataSource.password}")
    private String quartzDatasourceQuartzDataSourcePassword;
    @Value("${app.quartz.datasource.quartzDataSource.maxConnections}")
    private String quartzDatasourceQuartzDataSourceMaxConnections;
    @Value("${app.quartz.datasource.quartzDataSource.connection-provider.class}")
    private String quartzDatasourceQuartzDataSourceConnectionProviderClass;
}
