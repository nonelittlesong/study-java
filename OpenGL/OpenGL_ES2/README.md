参考：  
[Android OpenGL ES从零开始的Demo](https://github.com/doggycoder/AndroidOpenGLDemo)  
## EGL使用步骤
1. 取得EGL实例`egl10 = (EGL10) EGLContext.getEGL();`  
2. 选择Display  
   ```java
   eglDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
   if (eglDisplay == EGL10.EGL_NO_DISPLAY) {
       throw new RuntimeException("eglGetDisplay failed " +
               android.opengl.GLUtils.getEGLErrorString(egl10.eglGetError()));
   }
   int[] version = new int[2];
   if (!egl10.eglInitialize(eglDisplay, version)) {
       throw new RuntimeException("eglInitialize failed " +
               android.opengl.GLUtils.getEGLErrorString(egl10.eglGetError()));
   }
   ```
3. 选择Config  
4. 创建Surface  
5. 创建Context  
6. 指定当前的环境为绘制环境  

## EGL10
## EGLDisplay
## EGLSurface
## EGLContext
