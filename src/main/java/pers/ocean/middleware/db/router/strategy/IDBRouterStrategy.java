package pers.ocean.middleware.db.router.strategy;

/**
 * @Description 路由策略
 * @Author ocean_wll
 * @Date 2021/11/4 4:54 下午
 */
public interface IDBRouterStrategy {

    /**
     * 路由
     *
     * @param dbKeyAttr 数据库配置参数
     */
    void doRouter(String dbKeyAttr);

    /**
     * 清空缓存
     */
    void clear();
}
