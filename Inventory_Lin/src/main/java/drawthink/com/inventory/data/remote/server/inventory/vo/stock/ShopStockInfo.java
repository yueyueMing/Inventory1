package drawthink.com.inventory.data.remote.server.inventory.vo.stock;

/**
 * <b>类名称：</b> ShopStockInfo <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 17:14<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class ShopStockInfo {

    /**
     * date : 1488269058697
     * productName : 商品名称商品名称
     * productCode : BH00001
     * productTye : 型号1,型号2,型号3,型号4,型号5,型号6
     * productCategory : 服装
     * productColor : 红色,黄色,绿色,蓝色,白色,黑色
     * price : 56
     * inventoryNum : 28
     * sellNum : 5
     * lessNum : 23
     * total : 280
     */

    private long date;
    private String productName;
    private String productCode;
    private String productTye;
    private String productCategory;
    private String productColor;
    private double price;
    private int inventoryNum;
    private int sellNum;
    private int lessNum;
    private double total;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductTye() {
        return productTye;
    }

    public void setProductTye(String productTye) {
        this.productTye = productTye;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public int getLessNum() {
        return lessNum;
    }

    public void setLessNum(int lessNum) {
        this.lessNum = lessNum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
