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
   ```java
   int[] configsCount = new int[1];
   EGLConfig[] configs = new EGLConfig[1];
   int[] configSpec = {
           EGL10.EGL_RENDERABLE_TYPE,
           EGL_OPENGL_ES2_BIT,
           EGL10.EGL_RED_SIZE, 8,
           EGL10.EGL_GREEN_SIZE, 8,
           EGL10.EGL_BLUE_SIZE, 8,
           EGL10.EGL_ALPHA_SIZE, 8,
           EGL10.EGL_DEPTH_SIZE, 0,
           EGL10.EGL_STENCIL_SIZE, 0,
           EGL10.EGL_NONE
   };

   EGLConfig eglConfig = null;
   if (!egl10.eglChooseConfig(eglDisplay, configSpec, configs, 1, configsCount)) {
       throw new IllegalArgumentException("eglChooseConfig failed " +
               android.opengl.GLUtils.getEGLErrorString(egl10.eglGetError()));
   } else if (configsCount[0] > 0) {
       eglConfig = configs[0];
   }
   if (eglConfig == null) {
       throw new RuntimeException("eglConfig not initialized");
   }
   ```
4. 创建Surface  
   ```java
   eglSurface = egl10.eglCreateWindowSurface(eglDisplay, eglConfig, texture, null);
   if (eglSurface == null || eglSurface == EGL10.EGL_NO_SURFACE) {
       int error = egl10.eglGetError();
       if (error == EGL10.EGL_BAD_NATIVE_WINDOW) {
           Log.e(TAG, "eglCreateWindowSurface returned EGL10.EGL_BAD_NATIVE_WINDOW");
           return;
       }
       throw new RuntimeException("eglCreateWindowSurface failed " +
               android.opengl.GLUtils.getEGLErrorString(error));
   }
   ```
5. 创建Context  
   ```java
   int[] attrib_list = {EGL_CONTEXT_CLIENT_VERSION, 2, EGL10.EGL_NONE};
   eglContext = egl10.eglCreateContext(eglDisplay, eglConfig, EGL10.EGL_NO_CONTEXT, attrib_list);
   ```
6. 指定当前的环境为绘制环境  
   ```java
   if (!egl10.eglMakeCurrent(eglDisplay, eglSurface, eglSurface, eglContext)) {
       throw new RuntimeException("eglMakeCurrent failed " +
               android.opengl.GLUtils.getEGLErrorString(egl10.eglGetError()));
   }
   ```
