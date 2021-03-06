
package commonservice.test.service;

import com.alibaba.android.arouter.facade.template.IProvider;

import commonservice.test.bean.KTInfo;

/**
 * ================================================
 * 向外提供服务的接口, 在此接口中声明一些具有特定功能的方法提供给外部, 即可让一个组件与其他组件或宿主进行交互
 * ================================================
 */
public interface KTInfoService extends IProvider {
    KTInfo getInfo();
}
