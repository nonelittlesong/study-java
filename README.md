# study-java
**参考**  
* http://mvnrepository.com/
* https://docs.gradle.org/current/userguide/userguide.html

**wiki**  
* [build.gradle配置](https://github.com/nonelittlesong/study-java/wiki/build.gradle)
* [jni](https://github.com/nonelittlesong/study-java/wiki/jni)

##troubleshooting
More than one file was found with OS independent path 'lib/armeabi-v7a/xxxx.so'
从Android studio 2.2 升级到 3.0 +，gradle 也升级到3.0+, 恰好你也使用了CMakeList 来管理编译JNI代码，恰好你遇到了这个问题：More than one file was found with OS independent path 'lib/armeabi-v7a/xxxx.so'  
看看CMakeList 文件中有没有指定 OUTPUT目录到 jniLibs, 如下面的代码：  
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})  
直接把这行指定输出目录的代码干掉吧！希望能帮到你。  
网上搜了一大圈，大部分看到的是由于so库在libs目录下和jniLibs目录下的冲突，没有找到与我类似的问题。可能是gradle升级后，会默认将编译出来的so库打包进apk，而不需要像以前一样将编译后的so库输出到jniLibs下再进行打包。  
