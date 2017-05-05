package drawthink.com.inventory.data.remote.server.inventory.vo.login;

import java.util.List;

/**
 * <b>类名称：</b> User <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年02月13日 15:06<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
/*
登录接口
 */
public class User {


    /**
     * id : 0
     * mobile : string
     * name : string
     * stores : [{"endDate":"2017-03-02T08:13:30.404Z","id":0,"payImage":"string","startDate":"2017-03-02T08:13:30.404Z","storeName":"string"}]
     */

    private String id;
    private String mobile;
    private String name;
    private List<StoresBean> stores;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
         * endDate : 2017-03-02T08:13:30.404Z
         * id : 0
         * payImage : string
         * startDate : 2017-03-02T08:13:30.404Z
         * storeName : string
         */

        private long endDate;
        private int id;
        private String payImage;
        private long startDate;
        private String storeName;
        private boolean checked;

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
