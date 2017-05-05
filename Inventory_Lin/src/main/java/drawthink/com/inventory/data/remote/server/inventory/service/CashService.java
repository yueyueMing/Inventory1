package drawthink.com.inventory.data.remote.server.inventory.service;

import drawthink.com.inventory.data.remote.server.inventory.service.form.CashForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import drawthink.com.inventory.data.remote.server.inventory.vo.cash.CashDate;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <b>类名称：</b> CashService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 17:22<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface CashService {
    String OTHER_SERVICE_URL = "cash";

    /*
    根据日期计算统计销售金额
     */
    @GET(OTHER_SERVICE_URL + "/sell")
    //yyyy-MM-dd
    Observable<Response<LocalResponse<Double>>> calcMoney(@Query("date") String date);

    /*
    记录现金
     */
    @GET(OTHER_SERVICE_URL + "/statistics/{shopId}")
    Observable<Response<LocalResponse<CashDate>>> getPayInfo(
                                                             @Path("shopId") String shopId,
                                                             @Query("startDate") String startDate,
                                                             @Query("endDate") String endDate,
                                                             @Query("sellerId") String sellerId,
                                                             @Query("payType")String payType
                                                             );
/*
现金记录添加
 */

    @POST(OTHER_SERVICE_URL)
    Observable<Response<LocalResponse<Void>>> addCash(@Body CashForm cashForm);


}
