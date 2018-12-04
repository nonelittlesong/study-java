生产者，消费者模式：  
应用程序会先创建一个SurfaceTexture，然后将SurfaceTexture传递给图形生产者对象（比如Camera，通过调用setPreviewTexture传递），  
图形生产者对象生产一帧数据后，会回调onFrameAvailable通知应用程序有新的图像数据可以使用，应用程序就可以调用updateTexImage将图像数据先送到Texture，  
之后就可以调用opengl接口做些具体的业务了。  
