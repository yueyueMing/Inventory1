package drawthink.com.inventory.data.remote.func;


import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import rx.Observable;
import rx.functions.Func1;

/**
 * <b>类名称：</b> LocalResponseFunc <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 下午3:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

class LocalResponseFunc<T> implements Func1<LocalResponse<T>, Observable<T>> {
    @Override
    public Observable<T> call(LocalResponse<T> tResponse) {
        if (tResponse.getCode() == 200) {
            if (tResponse.getData() == null) {
                return Observable.just(null);
            }else {
                return Observable.just(tResponse.getData());
            }
        } else {
            return Observable.error(new Exception(tResponse.getMessage()));
        }
    }
}
