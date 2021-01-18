package com.wugui.datax.admin.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import com.wugui.datax.admin.util.SpringUtils;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>使用步骤：</p>
 * <blockquote><pre>
 *     DynamicDataSource.dataSourcesMap.put(dataSourceKey, druidDataSource);
 *     DynamicDataSource.setDataSource(dataSourceKey);
 *     调用业务代码</i>
 *     DynamicDataSource.clear();
 * </pre></blockquote>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final ThreadLocal<String> dataSourceKey = ThreadLocal.withInitial(() -> "defaultDataSource");

    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

    static {
        dataSourcesMap.put("defaultDataSource", SpringUtils.getBean("defaultDataSource"));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSource.dataSourceKey.get();
    }

    public static void setDataSource(String dataSource) {
        logger.debug("[将当前数据源改为]：{}", dataSource);
        createDataSource(dataSource);
        DynamicDataSource.dataSourceKey.set(dataSource);
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringUtils.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
    }

    private static void createDataSource(String beanName){
        if(!dataSourcesMap.containsKey(beanName)){
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setUrl("jdbc:mysql://192.168.9.93:3306/datax-web?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true");
            druidDataSource.setUsername("root");
            druidDataSource.setPassword("123456");
            DynamicDataSource.dataSourcesMap.put(beanName, druidDataSource);
            logger.debug("[创建新的数据源]：{}", beanName);
        }
    }

    public static String getDataSource() {
        String dataSource = DynamicDataSource.dataSourceKey.get();
        logger.debug("[获取当前数据源的类型为]：{}", dataSource);
        return dataSource;
    }

    public static void clear() {
        String dataSource = DynamicDataSource.dataSourceKey.get();
        logger.debug("[获取当前数据源的类型为]：{}", dataSource);
        DynamicDataSource.dataSourceKey.remove();
    }
}