参考：  
* https://github.com/saki4510t/UVCCamera  

## 导入.aar
https://blog.csdn.net/maiduoudo/article/details/79217151  
在app目录下的build.gradle文件中添加以下代码：  
```
android {
    repositories {
        flatDir {
            dirs 'libs' 
        } 
    } 
} 
dependencies {
    implementation(name: 'lyx-library-1.0.3', ext: 'aar')
}
```

## 导入module
需要添加project.properties文件，内容是`android.library=true`

## 添加依赖
project的build.gradle加入  
```
maven { url 'http://raw.github.com/saki4510t/libcommon/master/repository/' }
```

## Troubleshooting
### Execution failed for task ':app:transformClassesWithDexBuilderForDebug'.
https://stackoverflow.com/questions/49891730/invoke-customs-are-only-supported-starting-with-android-0-min-api-26  
在app的build.gradle中添加  
```
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```
### You need to use a Theme.AppCompat theme
https://blog.csdn.net/lincyang/article/details/42673151  
```
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.AppCompat" >
```
### module版本高于项目
https://www.jianshu.com/p/f7778668c93f  
https://www.cnblogs.com/rabtor/p/4410057.html  
