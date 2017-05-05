package drawthink.com.inventory.data.remote.server.inventory.service;

import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import drawthink.com.inventory.data.remote.server.inventory.vo.other.getCode;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * <b>类名称：</b> OtherService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月28日 17:42<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public interface OtherService {
/*
获取手机验证码
 */
    String OTHER_SERVICE_URL = "other";

    @GET(OTHER_SERVICE_URL + "/validCode/{mobile}")
    Observable<Response<LocalResponse<getCode>>> getValidCode(@Path("mobile") String mobile);



}
