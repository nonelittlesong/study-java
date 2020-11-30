# Troubleshootings

收集问题和解决方法

### More than one file was found with OS independent path 'lib/armeabi-v7a/xxxx.so'

**环境**

- Android Studio 3.0+
- Gradle 3.0+
- CMakeList 管理编译 JNI 代码

```diff
- More than one file was found with OS independent path 'lib/armeabi-v7a/xxxx.so'
```

看看 CMakeList 文件中有没有指定 OUTPUT 目录到 jniLibs, 如下面的代码：

```
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})
```

直接把这行指定输出目录的代码干掉吧！希望能帮到你。

网上搜了一大圈，大部分看到的是由于 so 库在 libs 目录下和 jniLibs 目录下的冲突，没有找到与我类似的问题。可能是 gradle 升级后，会默认将编译出来的 so 库打包进 apk，而不需要像以前一样将编译后的 so 库输出到 jniLibs 下再进行打包。

### AppCompatEditText cant resolve

**Solutions**

添加依赖，build.gradle -> dependencies

```
implementation 'com.android.support:appcompat-v7:28.0.0-rc01'
```

### Failed to load AppCompat ActionBar with unknown error. 

在项目 Project 中找到 `\app\src\main\res\values\styles.xml` 文件，  
把  
`<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">`  
改为  
`<style name="AppTheme" parent="Base.Theme.AppCompat.Light.DarkActionBar">`  

### A/DEBUG: backtrace:
```
--------- beginning of crash
09-03 14:06:53.394 1837-1837/? A/DEBUG: backtrace:
        #00 pc 0000144a  /data/app/com.faceattributes-ZfGInDEl3wVcT8yaomkQNw==/lib/arm/libnative-lib.so (Java_com_faceattributes_NativeFunction_nativeRgba+509)
        #01 pc 003e4679  /system/lib/libart.so (art_quick_generic_jni_trampoline+40)
        #02 pc 003e0931  /system/lib/libart.so (art_quick_invoke_stub_internal+64)
        #03 pc 003e4fa9  /system/lib/libart.so (art_quick_invoke_static_stub+224)
        ···
        #33 pc 00353ae9  /system/lib/libart.so (_ZN3art6Thread14CreateCallbackEPv+892)
        #34 pc 000486cf  /system/lib/libc.so (_ZL15__pthread_startPv+22)
        #35 pc 0001b02f  /system/lib/libc.so (__start_thread+32)
```
造成这种错误的可能原因是 native 方法的 java 原型和 cpp 实现的**返回类型不同**！

### android 权限申请失败

`onRequestPermissionsResult` 只会调用一次，获取权限代码参考：https://www.jianshu.com/p/e84e8c9f9912  

### ArrayList\<String> 转 String[]

https://www.cnblogs.com/kungfupanda/p/7747004.html  
https://blog.csdn.net/chuyouyinghe/article/details/72833502  
        
### Didn't find class "android.view.View$OnUnhandledKeyEventListener"

https://blog.csdn.net/qinkaiyuan94/article/details/81880019  
https://blog.csdn.net/weixin_37651459/article/details/80956366  

### Bitmap not large enough to support new configurations

`Bitmap.setHeight()` 和 `Bitmap.setWidth()`之后，Bitmap 的大小不能超过原来的大小！  
