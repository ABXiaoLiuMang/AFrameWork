基于ARouter路由框架的MVP快速组件化方案
框架基于JessYan开源的MVPArms 升级改造 MVP架构模式与传统的各类mvp框架写法模式都大同小异，比较特殊的地方使用了dagger作为注入解耦工具，
框架中ConfigModule，Delegate，代理各种基类生命周期的实现很独特，扩展性很强，需要了解熟悉，参考地址 https://github.com/JessYanCoding/MVPArms/blob/master/MVPArms.md

UI相关 https://qmuiteam.com/android/
QMUI Android 的设计目的是用于辅助快速搭建一个具备基本设计还原效果的 Android 项目，
同时利用自身提供的丰富控件及兼容处理，
让开发者能专注于业务需求而无需耗费精力在基础代码的设计上。
不管是新项目的创建，或是已有项目的维护，
均可使开发效率和项目质量得到大幅度提升。

config.gradle 文件统一三方库的版本管理文件(必须了解的几个库附相关链接)：
（重要）Dagger2 依赖注入框架 https://juejin.im/post/5971d28f6fb9a06bb474bc9d 框架中以dagger为各层级相互注入 以做到依赖解耦
RxJava 异步 https://github.com/ReactiveX/RxJava
MMKV 数据存储 https://juejin.im/entry/5ba4ad126fb9a05d0f16d991
Room 数据存储 https://developer.android.com/training/data-storage/room/index.html
Rxlifecycle 生命周期 https://github.com/trello/RxLifecycle
Retrofit Okhttp 网络 https://github.com/square/retrofit
AndroidAutoSize 屏幕适配 https://github.com/JessYanCoding/AndroidAutoSize
UtilCode 安卓工具类库 https://github.com/Blankj/AndroidUtilCode
Stetho 抓包 https://github.com/facebook/stetho（Chrome浏览器调试 地址栏输入chrome://inspect/）
...

App 壳工程 宿主
CommonBase 基础框架
CommonRes 资源
CommonImageloader 图片加载配置模块可切换图片加载框架
CommonSDK 公共工具集合
CommonService 存放各个模块的接口，然后再在各个模块实现接口，实现App模块与其他功能模块的交流 接口下沉

demo示例模块 java 以及 kotlin，供参考，正式开发 剔除 两个模块引用
module_demo_java
module_demo_kotlin

MVP
M层需要持有P层对象，P层需要持有M和V层对象，V层需要持有P层对象 通过Dagger进行依赖注入
Contract 中定义 MVP 类的接口, 便于管理, 本框架无需定义 Presenter 接口, 所以在 Contract 中只定义 View 和 Model 的接口
Activity 或 Fragment 实现 Contract 中定义的 View 接口, 供 Presenter 调用对应方法响应 UI
Model（注意不要跟Module搞混） 必须实现 Contract 的 Model 接口, 并且继承 BaseModel, 然后通过 IRepositoryManager 拿到需要的 Service 和 Cache, 为 Presenter 提供需要的数据 
Presenter 业务逻辑代码, 从 Model 层获取数据, 在调用 View 层显示数据, 实现 BasePresenter, 并指定 View 和 Model 的范型,使用 Dagger2 来注入 解耦

项目根目录下 MVPTemplate.rar 一键生成 mvp 页面级模板（可选择java或者kotlin） 把 MVPTemplate 解压把整个文件夹复制到：
Windows : AS安装目录/plugins/android/lib/templates/activities
Mac : /Applications/Android Studio.app/Contents/plugins/android/lib/templates/activities
重启 AndroidStudio生效 ps：如果有导包异常，文件合并异常等问题，请手动修改一下相关文件以及引用，请以大写字母开头创建模板

ConfigModule (AppComponent接口)
可在不修改框架源码的情况下对 Retrofit, Okhttp, RxCache, Gson 等框架的特有属性进行自定义化配置,
可在不修改框架源码的情况下向 BaseApplication, BaseActivity, BaseFragment 的对应生命周期中插入任意代码, 
可在不修改框架源码的情况下为框架轻松扩展任何新增功能 
例如：动态更改不同模块的 host
HttpUrl :访问网络请求
BaseUrl :访问网络请求
BaselmageLoaderStrategy :图片加栽的时的接口
GlobalHttpHandler:处理Http请求和响应结果的处理类
List<Interceptor> : OKhttp的器
ResponseErrorListener:响应的监听
File :绶序的文件
RetrofitConfiguration : Retrofit
OkhttpConfiguration : OKhttp
RxCacheConfiguration : rxjava 缓序参数设寅
GsonConfiguration : gson的参数
Requestlnterceptor.Level 打印日志的控制
FormatPrinter:对OkHttp的请求和响应信思进行更规范^消晰的打印
Cache.Factory :用于绶存框架组件
 