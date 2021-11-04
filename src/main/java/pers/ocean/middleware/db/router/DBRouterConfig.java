package pers.ocean.middleware.db.router;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 数据路由配置
 * @Author ocean_wll
 * @Date 2021/11/4 3:41 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBRouterConfig {

    /**
     * 分库数量
     */
    private Integer dbCount;

    /**
     * 表数量
     */
    private Integer tableCount;

    /**
     * 路由key
     */
    private String routerKey;
}
