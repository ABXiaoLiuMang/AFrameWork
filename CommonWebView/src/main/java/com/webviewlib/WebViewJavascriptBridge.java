/*

*/
package com.webviewlib;


/**
 * <pre>
 *     desc  : js接口
 *     revise:
 * </pre>
 */
public interface WebViewJavascriptBridge {
	
	void send(String data);
	void send(String data, CallBackFunction responseCallback);

}
