
package commonsdk.http;

/**
 * ================================================
 * CommonSDK 的 Api 可以定义公用的关于 API 的相关常量, 比如说请求地址, 错误码等, 每个组件的 Api 可以定义组件自己的私有常量
 * ================================================
 */
public interface Api {
    //默认host
    String APP_DOMAIN = "https://api.github.com";

    //状态码
    String REQUEST_SUCCESS = "200";

    // 错误码
    String ERROR_REQUEST = "1001";

}
