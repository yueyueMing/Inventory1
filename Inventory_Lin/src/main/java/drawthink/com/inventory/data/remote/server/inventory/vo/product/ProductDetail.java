package drawthink.com.inventory.data.remote.server.inventory.vo.product;

import java.util.List;

/**
 * <b>类名称：</b> ProductDetail <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月27日 11:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
/*
获取商品详情
 */
public class ProductDetail {

    /**
     * stockId : 1
     * productId : 1
     * name : 商品名称商品名称
     * code : BH00001
     * image : ["http://www.baidu.com/GoodImg_1_201702271639318240_.jpg","http://www.baidu.com/GoodImg_1_201702271639321311_.jpg","http://www.baidu.com/GoodImg_1_201702271639322902_.jpg"]
     * price : 56
     * sellNum : 5
     * colors : ["红色","黄色","绿色","蓝色","白色","黑色"]
     * types : ["型号1","型号2","型号3","型号4","型号5","型号6"]
     */

    private int stockId;
    private long stockNum;
    private int productId;
    private String name;
    private String code;
    private int price;
    private int sellNum;
    private List<String> image;
    private List<String> colors;
    private List<String> types;

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public long getStockNum() {
        return stockNum;
    }

    public void setStockNum(long stockNum) {
        this.stockNum = stockNum;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
