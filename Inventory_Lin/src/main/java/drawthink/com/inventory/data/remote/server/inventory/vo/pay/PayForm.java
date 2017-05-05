package drawthink.com.inventory.data.remote.server.inventory.vo.pay;

import java.io.Serializable;

/**
 * <b>类名称：</b> PayForm<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b> 马文礼 <br/>
 * <b>修改时间:</b> 2017年 03月07日 18:01<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class PayForm implements Serializable {

    /**
     * payType : string
     * price : 0
     * productCode : string
     * productColor : string
     * productId : 0
     * productName : string
     * productType : string
     * sellNum : 0
     * sellerId : 0
     * sellerName : string
     * shopId : 0
     * shopName : string
     * stockId : 0
     * userId : string
     * userName : string
     */

    private String payType;
    private double price;
    private String productCode;
    private String productColor;
    private int productId;
    private String productName;
    private String productType;
    private int sellNum;
    private int sellerId;
    private String sellerName;
    private int shopId;
    private String shopName;
    private int stockId;
    private String userId;
    private String userName;

    public PayForm(String shopName,
                   String code,
                   String name,
                   int productId,
                   int stockId,
                   String userId,
                   String userName,
                   int shopId) {
        this.shopName = shopName;
        this.productCode = code;
        this.productName = name;
        this.productId = productId;
        this.stockId = stockId;
        this.userId = userId;
        this.userName = userName;
        this.shopId = shopId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "PayForm{" +
                "payType='" + payType + '\'' +
                ", price=" + price +
                ", productCode='" + productCode + '\'' +
                ", productColor='" + productColor + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", sellNum=" + sellNum +
                ", sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", stockId=" + stockId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
