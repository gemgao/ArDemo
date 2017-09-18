# android vr全景图片初探（仿微博360全景图片的实现） #
最近逛微博的时候，看见有个360全景图很酷炫，想着自己实现一下，然后查资料发现实现的方法很多，，最终我决定用google开源的vr实现，毕竟我谷歌大法好，英文好的可以直接去看官网，官方地址：https://developers.google.com/vr/android/samples/vrview（需要科学上网）。
废话不多说，，先看效果图：


![](http://upload-images.jianshu.io/upload_images/3243383-4fb834de0d4d37b1.gif?imageMogr2/auto-orient/strip)


接下来就看是怎么实现的：

> 首先，添加依赖:

    compile 'com.google.vr:sdk-panowidget:1.80.0'

>然后，在布局xml里面用：

    <com.google.vr.sdk.widgets.pano.VrPanoramaView
      android:id="@+id/pano_view"
      android:layout_margin="5dip"
      android:layout_width="match_parent"
      android:scrollbars="@null"
      android:layout_height="250dip" />
>最后，在java中设置相关参数就好了：

	panoWidgetView.setEventListener(new ActivityEventListener());
        paNormalOptions = new VrPanoramaView.Options();
	//  mVrPanoramaView.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮
        panoWidgetView.setInfoButtonEnabled(false); //设置隐藏最左边信息的按钮
        panoWidgetView.setStereoModeButtonEnabled(false); //设置隐藏立体模型的按钮
        panoWidgetView.setEventListener(new ActivityEventListener()); //设置监听
        paNormalOptions.inputType = VrPanoramaView.Options.TYPE_MONO;
        //加载本地的图片源
        panoWidgetView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.andes2), paNormalOptions);

----------
经过上面的3步，我们就可以实现360全景图片的效果了，我们发现VrPanoramaView.Options这个参数有不同的数值，具体是什么意思呢？

下面是官方的解释：

> public static final int TYPE_MONO = 1;
图像被预期以覆盖沿着其水平轴360度，而垂直范围是根据图像的宽高比来计算。例如，如果一个1000x250像素的图像，给出所述全景将覆盖360x90度与垂直范围是-45至+45度。

----------

> public static final int TYPE_STEREO_OVER_UNDER = 2;
包含两个大小相等的投影 全景图垂直叠加。顶部图像被显示给左眼、底部图像被显示给右眼。图像将覆盖沿水平轴360度，而垂直范围是根据图像的宽高比来计算。例如，如果一个1000x500像素的图像中。

## 看完上面的解释是不是晕了，，接下来我会画个图让你一目了然。 ##

TYPE_MONO：

![1505721980(1).jpg](http://upload-images.jianshu.io/upload_images/3243383-c8eeabcff55612aa.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

TYPE_STEREO_OVER_UNDER：
 
![andes2.jpg](http://upload-images.jianshu.io/upload_images/3243383-525ea936b0b88cf4.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 这下子明白了吧，图片的不同，选择的不同的模式，就可以了。 #
[Demo下载](https://github.com/gemgao/ArDemo "ArDemo")
[简书](http://www.jianshu.com/p/aded92aa7140)
[csdn](http://blog.csdn.net/gemgaozhen/article/details/78021669)