
package com.webviewlib;

/**
 * <pre>
 *     desc  : 自定义接口，处理消息回调逻辑
 *     revise:
 * </pre>
 */
public interface BridgeHandler {

	/**
	 * 处理消息
	 * @param data						消息内容
	 * @param function					回调
	 */
	void handler(String data, CallBackFunction function);

}
