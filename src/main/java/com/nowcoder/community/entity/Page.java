package com.nowcoder.community.entity;

/**
 * 封装分页相关信息
 * @author JBJ
 * @date 2022-07-25 14:09
 */
public class Page {
    //当前页码
    private int current = 1;
    //单页显示数据上限
    private int limit = 10;
    //数据总数(计算总页数)
    private int rows;
    //查询路径（复用分页链接）
    private String path;

    public int getCurrent() {
        return current;
    }
    //current由前端给的路径里由SpringMVC直接封装到page里
    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @Description 获取当前页的起始行, 用于sql的offset
     * @author JBJ
     * @date 2022-07-25 14:14
     */
    public int getOffset() {
        return current * limit - limit;
    }

    /**
     * @Description 获取总页数
     * @author JBJ
     * @date ] 14:22
     */

    public int getTotal() {
        //每页都是满的
        if (rows % limit == 0) {
            return rows / limit;
        } else return rows / limit + 1;
    }

    /**
     * @Description 获取起始页码 (左边界)
     * @author JBJ
     * @date 2022-07-25 14:26
     */
    public int getFrom() {
        int from = current - 2;//最多显示前两页
        if (from <= 1) {
            return 1;
        } else return from;

    }

    /**
     * @Description 获取终止页码  (右边界)
     * @author JBJ
     * @date 2022-07-25 14:26
     */
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
//        if (to > total) {
//            return total;
//        } else return to;
        return to > total ? total : to;
    }
}
