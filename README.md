# study-java
**参考**  
* http://mvnrepository.com/
* https://docs.gradle.org/current/userguide/userguide.html

**wiki**  
* [build.gradle和JNI和NDK](https://github.com/nonelittlesong/study-java/wiki/build.gradle)
* [jni](https://github.com/nonelittlesong/study-java/wiki/jni)
* [AS的ImageView转Bitmap](https://github.com/nonelittlesong/study-java/wiki/ImageViewtoBitmap)
* [AS的Bitmap转Pixels](https://github.com/nonelittlesong/study-java/wiki/BitmaptoPixels)
* [Android权限](https://github.com/nonelittlesong/study-java/wiki/checkSelfPermission)

## troubleshooting
### More than one file was found with OS independent path 'lib/armeabi-v7a/xxxx.so'
从Android studio 2.2 升级到 3.0 +，gradle 也升级到3.0+, 恰好你也使用了CMakeList 来管理编译JNI代码，恰好你遇到了这个问题：More than one file was found with OS independent path 'lib/armeabi-v7a/xxxx.so'  
看看CMakeList 文件中有没有指定 OUTPUT目录到 jniLibs, 如下面的代码：  
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})  
直接把这行指定输出目录的代码干掉吧！希望能帮到你。  
网上搜了一大圈，大部分看到的是由于so库在libs目录下和jniLibs目录下的冲突，没有找到与我类似的问题。可能是gradle升级后，会默认将编译出来的so库打包进apk，而不需要像以前一样将编译后的so库输出到jniLibs下再进行打包。  

### AppCompatEditText cant resolve
build.gradle->dependencies
```
implementation 'com.android.support:appcompat-v7:28.0.0-rc01'
```
### Failed to load AppCompat ActionBar with unknown error. 
在项目Project中找到\app\src\main\res\values\styles.xml文件  
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
        #04 pc 000ac303  /system/lib/libart.so (_ZN3art9ArtMethod6InvokeEPNS_6ThreadEPjjPNS_6JValueEPKc+182)
        #05 pc 001f27fb  /system/lib/libart.so (_ZN3art11interpreter34ArtInterpreterToCompiledCodeBridgeEPNS_6ThreadEPNS_9ArtMethodEPKNS_7DexFile8CodeItemEPNS_11ShadowFrameEPNS_6JValueE+238)
        #06 pc 001edd71  /system/lib/libart.so (_ZN3art11interpreter6DoCallILb0ELb0EEEbPNS_9ArtMethodEPNS_6ThreadERNS_11ShadowFrameEPKNS_11InstructionEtPNS_6JValueE+576)
        #07 pc 003cb927  /system/lib/libart.so (MterpInvokeStatic+322)
        #08 pc 003d2d94  /system/lib/libart.so (ExecuteMterpImpl+14612)
        #09 pc 001d5351  /system/lib/libart.so (_ZN3art11interpreterL7ExecuteEPNS_6ThreadEPKNS_7DexFile8CodeItemERNS_11ShadowFrameENS_6JValueEb+340)
        #10 pc 001da6a3  /system/lib/libart.so (_ZN3art11interpreter33ArtInterpreterToInterpreterBridgeEPNS_6ThreadEPKNS_7DexFile8CodeItemEPNS_11ShadowFrameEPNS_6JValueE+142)
        #11 pc 001edd5b  /system/lib/libart.so (_ZN3art11interpreter6DoCallILb0ELb0EEEbPNS_9ArtMethodEPNS_6ThreadERNS_11ShadowFrameEPKNS_11InstructionEtPNS_6JValueE+554)
        #12 pc 003cb4a3  /system/lib/libart.so (MterpInvokeInterface+1210)
        #13 pc 003d2e14  /system/lib/libart.so (ExecuteMterpImpl+14740)
        #14 pc 001d5351  /system/lib/libart.so (_ZN3art11interpreterL7ExecuteEPNS_6ThreadEPKNS_7DexFile8CodeItemERNS_11ShadowFrameENS_6JValueEb+340)
        #15 pc 001da6a3  /system/lib/libart.so (_ZN3art11interpreter33ArtInterpreterToInterpreterBridgeEPNS_6ThreadEPKNS_7DexFile8CodeItemEPNS_11ShadowFrameEPNS_6JValueE+142)
        #16 pc 001edd5b  /system/lib/libart.so (_ZN3art11interpreter6DoCallILb0ELb0EEEbPNS_9ArtMethodEPNS_6ThreadERNS_11ShadowFrameEPKNS_11InstructionEtPNS_6JValueE+554)
        #17 pc 003ca871  /system/lib/libart.so (MterpInvokeVirtual+440)
        #18 pc 003d2c14  /system/lib/libart.so (ExecuteMterpImpl+14228)
        #19 pc 001d5351  /system/lib/libart.so (_ZN3art11interpreterL7ExecuteEPNS_6ThreadEPKNS_7DexFile8CodeItemERNS_11ShadowFrameENS_6JValueEb+340)
        #20 pc 001da6a3  /system/lib/libart.so (_ZN3art11interpreter33ArtInterpreterToInterpreterBridgeEPNS_6ThreadEPKNS_7DexFile8CodeItemEPNS_11ShadowFrameEPNS_6JValueE+142)
        #21 pc 001edd5b  /system/lib/libart.so (_ZN3art11interpreter6DoCallILb0ELb0EEEbPNS_9ArtMethodEPNS_6ThreadERNS_11ShadowFrameEPKNS_11InstructionEtPNS_6JValueE+554)
        #22 pc 003cb4a3  /system/lib/libart.so (MterpInvokeInterface+1210)
        #23 pc 003d2e14  /system/lib/libart.so (ExecuteMterpImpl+14740)
        #24 pc 001d5351  /system/lib/libart.so (_ZN3art11interpreterL7ExecuteEPNS_6ThreadEPKNS_7DexFile8CodeItemERNS_11ShadowFrameENS_6JValueEb+340)
        #25 pc 001da5f1  /system/lib/libart.so (_ZN3art11interpreter30EnterInterpreterFromEntryPointEPNS_6ThreadEPKNS_7DexFile8CodeItemEPNS_11ShadowFrameE+92)
        #26 pc 003c0fbd  /system/lib/libart.so (artQuickToInterpreterBridge+944)
        #27 pc 003e46f1  /system/lib/libart.so (art_quick_to_interpreter_bridge+32)
        #28 pc 003e0931  /system/lib/libart.so (art_quick_invoke_stub_internal+64)
        #29 pc 003e4ea3  /system/lib/libart.so (art_quick_invoke_stub+226)
        #30 pc 000ac2d9  /system/lib/libart.so (_ZN3art9ArtMethod6InvokeEPNS_6ThreadEPjjPNS_6JValueEPKc+140)
        #31 pc 00334bb1  /system/lib/libart.so (_ZN3artL18InvokeWithArgArrayERKNS_33ScopedObjectAccessAlreadyRunnableEPNS_9ArtMethodEPNS_8ArgArrayEPNS_6JValueEPKc+52)
        #32 pc 00335a2d  /system/lib/libart.so (_ZN3art35InvokeVirtualOrInterfaceWithJValuesERKNS_33ScopedObjectAccessAlreadyRunnableEP8_jobjectP10_jmethodIDP6jvalue+320)
        #33 pc 00353ae9  /system/lib/libart.so (_ZN3art6Thread14CreateCallbackEPv+892)
        #34 pc 000486cf  /system/lib/libc.so (_ZL15__pthread_startPv+22)
        #35 pc 0001b02f  /system/lib/libc.so (__start_thread+32)
```
造成这种错误的可能原因是native方法的java原型和cpp实现的`返回类型不同`  
### android权限申请失败
onRequestPermissionsResult只会调用一次，获取权限代码参考：https://www.jianshu.com/p/e84e8c9f9912  
### ArrayList<String> 转 String[]
https://www.cnblogs.com/kungfupanda/p/7747004.html  
https://blog.csdn.net/chuyouyinghe/article/details/72833502  
        
### Didn't find class "android.view.View$OnUnhandledKeyEventListener"
https://blog.csdn.net/qinkaiyuan94/article/details/81880019  
https://blog.csdn.net/weixin_37651459/article/details/80956366  

### Bitmap not large enough to support new configurations
Bitmap.setHeight() 和 Bitmap.setWidth()之后  
Bitmap的大小不能超过原来的大小  

### Failed to load AppCompat ActionBar with unknown error.
styles.xml  
```
<resources>
	<!-- Base application theme. -->
	<style name="AppTheme" parent="Base.Theme.AppCompat.Light.DarkActionBar">
		<!-- Customize your theme here. -->
	</style>
</resources>
```
