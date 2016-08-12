# SDK
Altamob AD SDK 使用说明
一、申请AppKey
开发者需要提供要集成sdk的app包名，由altamob生成唯一的AppKey。
例如：提供com.altamob.sample --> AppKey：qguoFn。

二、注册AppKey
添加 name 为 AppKey，value 为 altamob 提供AppKey 的 meta 标签到清单文件中。
<meta-data
    android:name="appKey"
    android:value="@string/app_key" />

三、集成aar
开发者可以把SDK的aar作为一个module导入工程中，在需要使用SDK的项目build.gradle文件中增加依赖。

四、调用
1.初始化
使用时首先需要在项目中对SDK进行初始化,建议开发者在Application创建时调用以下代码：
AltamobAdSDK.getInstance().init(this.getApplicationContext());

2.广告调用

设置监听
/**
 * 广告监听接口
 */
public interface AltamobAdListener {

    /**
     * 广告调用成功
     *
     * @param ads         广告对象列表
     * @param placementId 广告PlacementID
     */
    void onLoaded(List<AD> ads, String placementId);

    /**
     * 广告调用失败
     *
     * @param error       错误信息
     * @param placementId 广告PlacementID
     */
    void onError(AltamobError error, String placementId);

    /**
     * 广告点击监听
     *
     * @param ad          广告对象
     * @param placementId 广告PlacementID
     */
    void onClick(AD ad, String placementId);

    /**
     * 广告展示监听
     *
     * @param ad          广告对象
     * @param placementId 广告PlacementID
     */
    void onShowed(AD ad,  String placementId);
}

设置广告对象
NativeAd nativeAd = new NativeAd(context, "Your PlacementID");

拉取广告
nativeAd.loadAd(listener, int amount);

广告绑定单个View
@Override
public void onLoaded(List<AD> ads, String placementId) {
    ImageView icon = (ImageView) findViewById(R.id.app_icon);
    nativeAd.bindToView(ads.get(0), icon);
   }

广告绑定多个View
@Override
public void onLoaded(List<AD> ads, String placementId) {
      List<View> views  = new ArrayList<>();
    views.add(icon);
    views.add(title);
    nativeAd.bindToViews(ads.get(0),views);
 }

广告对象参数
app_info_id;//广告APP ID
title;//APP 名称
icon_url;//icon图片地址
cover_url;//cover图片地址
desc;//APP内容描述信息
category;//APP分类信息
favors;//Google Play点赞数量
package_name;//APP 包名
rating;//APP 评级



备注

●依赖：
compile 'org.jsoup:jsoup:1.9.2'
compile 'com.squareup.okhttp3:okhttp:3.4.1'

●混淆：
-keep class  com.altamob.sdk.*{*;}
-keep class  com.clicklib.sdk.*{*;}

●涉及权限列表：
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />

