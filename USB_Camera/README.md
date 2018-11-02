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
