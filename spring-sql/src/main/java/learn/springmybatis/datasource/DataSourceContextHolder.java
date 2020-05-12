package learn.springmybatis.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 数据源上下文
 */
@Slf4j
public class DataSourceContextHolder {

    /**
     * 存储已经注册的数据源的key
     */
    public static List<String> dataSourceIds = new ArrayList<>();

    /**
     * 线程级别的私有变量
     */
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    /**
     * 线程当前数据源
     */
    public static String getDataSourceRouterKey () {
        return HOLDER.get();
    }

    /**
     * 线程切换到指定数据源
     */
    public static void setDataSourceRouterKey (String dataSourceRouterKey) {
        log.info("切换至{}数据源", dataSourceRouterKey);
        HOLDER.set(dataSourceRouterKey);
    }

    /**
     * 设置数据源之前一定要先移除
     */
    public static void removeDataSourceRouterKey () {
        HOLDER.remove();
    }

    /**
     * 判断指定DataSource当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }

}
