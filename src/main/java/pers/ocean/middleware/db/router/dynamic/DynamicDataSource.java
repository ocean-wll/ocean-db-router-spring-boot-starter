package pers.ocean.middleware.db.router.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import pers.ocean.middleware.db.router.DBContextHolder;

/**
 * @Description 动态数据源
 * @Author ocean_wll
 * @Date 2021/11/4 5:28 下午
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return "db" + DBContextHolder.getDbKey();
    }
}
