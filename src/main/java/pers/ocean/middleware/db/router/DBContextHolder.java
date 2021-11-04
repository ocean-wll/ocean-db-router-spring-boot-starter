package pers.ocean.middleware.db.router;

/**
 * @Description 数据源上下文
 * @Author ocean_wll
 * @Date 2021/11/4 4:47 下午
 */
public class DBContextHolder {

    /**
     * db名称
     */
    private static final ThreadLocal<String> dbKey = new ThreadLocal<>();

    /**
     * table名称
     */
    private static final ThreadLocal<String> tableKey = new ThreadLocal<>();

    public static String getDbKey() {
        return dbKey.get();
    }

    public static void setDbKey(String dbAddr) {
        dbKey.set(dbAddr);
    }

    public static String getTableKey() {
        return tableKey.get();
    }

    public static void setTableKey(String tableAddr) {
        tableKey.set(tableAddr);
    }

    public static void clearDbKey() {
        dbKey.remove();
    }

    public static void clearTableKey() {
        tableKey.remove();
    }
}
