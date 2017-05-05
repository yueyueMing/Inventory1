package drawthink.com.inventory.data.remote.server.inventory.vo.product;

/**
 * <b>类名称：</b> ProductCategory <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 14:39<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

/*
获取商品分类
 */
public class ProductCategory {


    /**
     * id : 鞋帽
     * title : 鞋帽
     * sortNum : 0
     */

    private String id;
    private String title;
    private int sortNum;
    private boolean selected = false;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
