package drawthink.com.inventory.data.remote.server.inventory.vo.product;

import java.util.List;

/**
 * <b>类名称：</b> ProductAll <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 14:34<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

/**
 *获取商品列表
 */
public class ProductAll {
    /**
     * code : string
     * image : ["string"]
     * name : string
     * price : 0
     * productId : 0
     * sellNum : 0
     * stockId : 0
     */

    private String code;
    private String name;
    private String price;
    private int productId;
    private String sellNum;
    private int stockId;
    private List<String> image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSellNum() {
        return sellNum;
    }

    public void setSellNum(String sellNum) {
        this.sellNum = sellNum;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }



}
