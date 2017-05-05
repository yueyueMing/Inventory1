package drawthink.com.inventory.data.remote.server.inventory.vo.statistics;

import java.util.List;

/**
 * <b>类名称：</b> Statistic <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月07日 14:01<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class Statistic {


    /**
     * count : 0
     * statistics : [{"num":0,"price":0,"productCode":"string","productColor":"string","productName":"string","productType":"string","sellDate":"2017-03-07T00:50:22.703Z","seller":"string","total":0}]
     * total : 0
     */

    private int count;
    private int total;
    private List<StatisticsBean> statistics;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<StatisticsBean> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StatisticsBean> statistics) {
        this.statistics = statistics;
    }

    public static class StatisticsBean {
        /**
         * num : 0
         * price : 0
         * productCode : string
         * productColor : string
         * productName : string
         * productType : string
         * sellDate : 2017-03-07T00:50:22.703Z
         * seller : string
         * total : 0
         */

        private int num;
        private double price;
        private String productCode;
        private String productColor;
        private String productName;
        private String productType;
        private long sellDate;
        private String seller;
        private double total;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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

        public long getSellDate() {
            return sellDate;
        }

        public void setSellDate(long sellDate) {
            this.sellDate = sellDate;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }
}
