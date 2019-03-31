package cn.itsource.aigou.util;

public class BaseQuery {

    //int current, int size

    private Integer page=0;// 当前页int ===== integer
    private Integer rows=10;// 每页多少条

    private String q;//查询关键字

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                "page=" + page +
                ", rows=" + rows +
                ", q='" + q + '\'' +
                '}';
    }
}
