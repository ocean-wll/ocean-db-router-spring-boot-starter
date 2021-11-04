package pers.ocean.middleware.db.router.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import pers.ocean.middleware.db.router.DBRouterConfig;
import pers.ocean.middleware.db.router.annotation.DBRouter;
import pers.ocean.middleware.db.router.strategy.IDBRouterStrategy;

/**
 * @Description 数据库路由切面
 * @Author ocean_wll
 * @Date 2021/11/4 5:40 下午
 */
@Aspect
@Slf4j
public class DBRouterJoinPoint {

    private DBRouterConfig dbRouterConfig;

    private IDBRouterStrategy dbRouterStrategy;

    public DBRouterJoinPoint(DBRouterConfig dbRouterConfig, IDBRouterStrategy dbRouterStrategy) {
        this.dbRouterConfig = dbRouterConfig;
        this.dbRouterStrategy = dbRouterStrategy;
    }

    @Pointcut("@annotation(pers.ocean.middleware.db.router.annotation.DBRouter)")
    public void point() {
    }

    @Around("point() && @annotation(dbRouter)")
    public Object doRouter(ProceedingJoinPoint jp, DBRouter dbRouter) throws Throwable {
        String dbKey = dbRouter.key();
        if (StringUtils.isBlank(dbKey) && StringUtils.isBlank(dbRouterConfig.getRouterKey())) {
            throw new RuntimeException("annotation DBRouter key is null！");
        }
        dbKey = StringUtils.isNotBlank(dbKey) ? dbKey : dbRouterConfig.getRouterKey();
        // 路由属性
        String dbKeyAttr = getAttrValue(dbKey, jp.getArgs());
        // 路由策略
        dbRouterStrategy.doRouter(dbKeyAttr);
        // 返回结果
        try {
            return jp.proceed();
        } finally {
            dbRouterStrategy.clear();
        }
    }

    /**
     * 获取路由属性值
     *
     * @param attr Key
     * @param args 参数
     * @return 属性值
     */
    public String getAttrValue(String attr, Object[] args) {
        if (1 == args.length) {
            Object arg = args[0];
            if (arg instanceof String) {
                return arg.toString();
            }
        }

        String filedValue = null;
        for (Object arg : args) {
            try {
                if (StringUtils.isNotBlank(filedValue)) {
                    break;
                }
                filedValue = BeanUtils.getProperty(arg, attr);
            } catch (Exception e) {
                log.error("获取路由属性值失败 attr：{}", attr, e);
            }
        }
        return filedValue;
    }
}
