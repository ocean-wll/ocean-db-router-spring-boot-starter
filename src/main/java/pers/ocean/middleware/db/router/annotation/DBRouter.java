package pers.ocean.middleware.db.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 数据库路由注解
 * @Author ocean_wll
 * @Date 2021/11/4 4:26 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DBRouter {

    /**
     * 分库分表字段
     *
     * @return 分库分表字段
     */
    String key() default "";
}
