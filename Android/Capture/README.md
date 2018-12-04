照相功能的简单实现  

1. Environment.getExternalStorageDirectory().toString()  
   得到外部存储空间的绝对路径。  
2. 创建文件  
   ```
   File imageFile = new File(mPath);
   if (imageFile.exists()) {
       imageFile.delete();
   }
   ```
3. 获取TextureView的Bitmap  
   ```
   Bitmap bitmap = textureView.getBitmap();
   ```
4. `outputStream = new FileOutputStream(imageFile);`  
5. `bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);`  
6. 关闭流  
   ```
   outputStream.flush();
   outputStream.close();
   ```
