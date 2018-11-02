参考：  
* https://github.com/saki4510t/UVCCamera  

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
