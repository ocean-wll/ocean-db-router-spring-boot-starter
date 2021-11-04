package pers.ocean.middleware.db.router.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import pers.ocean.middleware.db.router.DBContextHolder;
import pers.ocean.middleware.db.router.DBRouterConfig;
import pers.ocean.middleware.db.router.strategy.IDBRouterStrategy;

/**
 * @Description hashCode路由策略
 * @Author ocean_wll
 * @Date 2021/11/4 5:20 下午
 */
@Slf4j
public class DBRouterStrategyHashCode implements IDBRouterStrategy {

    private DBRouterConfig dbRouterConfig;

    public DBRouterStrategyHashCode(DBRouterConfig dbRouterConfig) {
        this.dbRouterConfig = dbRouterConfig;
    }

    @Override
    public void doRouter(String dbKeyAttr) {
        int size = dbRouterConfig.getDbCount() * dbRouterConfig.getTableCount();

        // 扰动函数
        int idx = (size - 1) & ((dbKeyAttr.hashCode()) ^ (dbKeyAttr.hashCode() >>> 16));

        // 库表索引
        int dbIdx = idx / dbRouterConfig.getTableCount() + 1;
        int tableIdx = idx - dbRouterConfig.getTableCount() * (dbIdx - 1);

        // 设置ThreadLocal
        DBContextHolder.setDbKey(String.format("%02d", dbIdx));
        DBContextHolder.setTableKey(String.format("%03d", tableIdx));
        log.info("dbIndex: {}, tableIndex: {}", dbIdx, tableIdx);

    }

    @Override
    public void clear() {
        DBContextHolder.clearDbKey();
        DBContextHolder.clearTableKey();
    }
}
