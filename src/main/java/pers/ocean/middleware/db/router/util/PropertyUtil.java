package pers.ocean.middleware.db.router.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.core.env.Environment;

/**
 * @Description 属性工具类
 * @Author ocean_wll
 * @Date 2021/11/4 3:42 下午
 */
public class PropertyUtil {

    /**
     * 获取配置文件中的属性值
     *
     * @param environment Environment
     * @param prefix      前缀
     * @param targetClass 目标类
     * @param <T>         目标类
     * @return T
     */
    public static <T> T handle(final Environment environment, final String prefix, final Class<T> targetClass) {
        try {
            Class<?> binderClass = Class.forName("org.springframework.boot.context.properties.bind.Binder");
            Method getMethod = binderClass.getDeclaredMethod("get", Environment.class);
            Method bindMethod = binderClass.getDeclaredMethod("bind", String.class, Class.class);
            Object binderObject = getMethod.invoke(null, environment);
            String prefixParam = prefix.endsWith(".") ? prefix.substring(0, prefix.length() - 1) : prefix;
            Object bindResultObject = bindMethod.invoke(binderObject, prefixParam, targetClass);
            Method resultGetMethod = bindResultObject.getClass().getDeclaredMethod("get");
            return (T)resultGetMethod.invoke(bindResultObject);
        } catch (final ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
