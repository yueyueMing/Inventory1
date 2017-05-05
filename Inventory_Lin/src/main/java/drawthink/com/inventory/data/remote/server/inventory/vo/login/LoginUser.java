package drawthink.com.inventory.data.remote.server.inventory.vo.login;

import java.util.List;

/**
 * <b>类名称：</b> LoginUser <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月07日 13:04<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class LoginUser {

    /**
     * id : 1
     * mobile : 17809518300
     * name : 王月明
     * stores : [{"id":2,"storeName":"北京东路创展中心","payImage":"ShopQR_1_201702241540012980_.jpg","startDate":1483200000000,"endDate":1580486400000}]
     */

    private int id;
    private String mobile;
    private String name;
    private List<StoresBean> stores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class StoresBean {
        /**
         * id : 2
         * storeName : 北京东路创展中心
         * payImage : ShopQR_1_201702241540012980_.jpg
         * startDate : 1483200000000
         * endDate : 1580486400000
         */

        private int id;
        private String storeName;
        private String payImage;
        private long startDate;
        private long endDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getPayImage() {
            return payImage;
        }

        public void setPayImage(String payImage) {
            this.payImage = payImage;
        }

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }
    }
}
