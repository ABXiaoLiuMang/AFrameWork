/*

*/
package com.webviewlib;


/**
 * <pre>
 *     desc  : 默认的BridgeHandler
 *     revise:
 * </pre>
 */
public class DefaultHandler implements BridgeHandler{

	@Override
	public void handler(String data, CallBackFunction function) {
		if(function != null){
			function.onCallBack("DefaultHandler response data");
		}
	}

}
