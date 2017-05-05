package drawthink.com.inventory.data.remote.server.inventory.vo.cash;

import java.util.List;

/**
 * <b>类名称：</b> CashDate <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月07日 15:38<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class CashDate {


    /**
     * statistics : [{"date":1488438776010,"payType":"0","sellName":"李四","sell":280,"cash":3000},{"date":1488526525803,"payType":"1","sellName":"张三","sell":0,"cash":500}]
     * sellTotal : 280
     * cashTotal : 3500
     */

    private int sellTotal;
    private int cashTotal;
    private List<StatisticsBean> statistics;

    public int getSellTotal() {
        return sellTotal;
    }

    public void setSellTotal(int sellTotal) {
        this.sellTotal = sellTotal;
    }

    public int getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(int cashTotal) {
        this.cashTotal = cashTotal;
    }

    public List<StatisticsBean> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StatisticsBean> statistics) {
        this.statistics = statistics;
    }

    public static class StatisticsBean {
        /**
         * date : 1488438776010
         * payType : 0
         * sellName : 李四
         * sell : 280
         * cash : 3000
         */

        private long date;
        private int payType;
        private String sellName;
        private double sell;
        private double cash;

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getSellName() {
            return sellName;
        }

        public void setSellName(String sellName) {
            this.sellName = sellName;
        }

        public double getSell() {
            return sell;
        }

        public void setSell(double sell) {
            this.sell = sell;
        }

        public double getCash() {
            return cash;
        }

        public void setCash(double cash) {
            this.cash = cash;
        }
    }
}
