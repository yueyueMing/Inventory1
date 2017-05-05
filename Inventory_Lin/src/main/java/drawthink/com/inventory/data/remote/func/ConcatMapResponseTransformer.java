package drawthink.com.inventory.data.remote.func;


import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import retrofit2.Response;
import rx.Observable;

/**
 * <b>类名称：</b> ResponseTransformer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 下午3:25<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class ConcatMapResponseTransformer<T>
        implements Observable.Transformer<Response<LocalResponse<T>>, T> {

    @Override
    public Observable<T> call(Observable<Response<LocalResponse<T>>> responseObservable) {
        return responseObservable
                .concatMap(new ResponseFunc<>())
                .concatMap(new LocalResponseFunc<>());
    }
}
