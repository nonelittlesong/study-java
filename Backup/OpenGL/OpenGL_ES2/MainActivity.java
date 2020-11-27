mBackEnv=new GLES20BackEnv(mBmpWidth,mBmpHeight);
mBackEnv.setThreadOwner(getMainLooper().getThread().getName());
mBackEnv.setFilter(new GrayFilter(getResources()));
mBackEnv.setInput(bmp); 
saveBitmap(mBackEnv.getBitmap());
