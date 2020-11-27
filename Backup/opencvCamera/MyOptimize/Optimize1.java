//将原来的mCameraFrameReady参数修改为以下两个，分别对应两帧画面，具体的参数值定义与修改跟mCameraFrameReady相同
//private boolean mCameraFrameReady = false;
private boolean mCameraFrameReadyOne = false;
private boolean mCameraFrameReadyTwo = false;

@Override
    public void onPreviewFrame(byte[] frame, Camera arg1) {
        synchronized (this) {
            if (mChainIdx == 0 && !mCameraFrameReadyOne) {
                mFrameChain[0].put(0, 0, frame);
                mCameraFrameReadyOne = true;

            }
            if (mChainIdx == 1 && !mCameraFrameReadyTwo) {
                mFrameChain[1].put(0, 0, frame);
                mCameraFrameReadyTwo = true;
            }
            this.notify();
        }
        if (mCamera != null)
            mCamera.addCallbackBuffer(mBuffer);
    }

private class CameraWorkerOne implements Runnable {

        @Override
        public void run() {
            do {
                boolean hasFrame = false;
                synchronized (JavaCameraView.this) {
                    try {
                        while (!mCameraFrameReadyOne && !mStopThread) {
                            JavaCameraView.this.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (mCameraFrameReadyOne) {
                        mCameraFrameReadyOne = false;
                        hasFrame = true;
                    }
                }

                if (!mStopThread && hasFrame) {
                    if (!mFrameChain[0].empty()) {
                        mChainIdx = 1;
                        deliverAndDrawFrame(mCameraFrame[0]);
                    }
                }

            } while (!mStopThread);
            Log.d(TAG, "Finish processing thread");
        }
    }

    private class CameraWorkerTwo implements Runnable {

        @Override
        public void run() {
            do {
                boolean hasFrame = false;
                synchronized (JavaCameraView.this) {
                    try {
                        while (!mCameraFrameReadyTwo && !mStopThread) {
                            JavaCameraView.this.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (mCameraFrameReadyTwo) {
                        mCameraFrameReadyTwo = false;
                        hasFrame = true;
                    }
                }

                if (!mStopThread && hasFrame) {
                    if (!mFrameChain[1].empty()) {
                        mChainIdx = 0;
                        deliverAndDrawFrame(mCameraFrame[1]);
                    }
                }

            } while (!mStopThread);
            Log.d(TAG, "Finish processing thread");
        }
    }


//修改CameraBridgeViewBase文件中的：

protected void deliverAndDrawFrame(CvCameraViewFrame frame) {
        Mat modified;
        if (mListener != null) {
            modified = mListener.onCameraFrame(frame);
        } else {
            modified = frame.rgba();
        }
        boolean bmpValid = true;
        if (modified != null) {
            try {
                Mat mGrayT = new Mat(mFrameHeight, mFrameWidth, CvType.CV_8UC1);
                Core.transpose(modified, mGrayT);
                Core.flip(mGrayT, mGrayT, 1);
                Utils.matToBitmap(mGrayT, mCacheBitmap);
                mGrayT.release();
            } catch (Exception e) {
                Log.e(TAG, "Bitmap type: " + mCacheBitmap.getWidth() + "*" + mCacheBitmap.getHeight());
                Log.e(TAG, "Utils.matToBitmap() throws an exception: " + e.getMessage());
                bmpValid = false;
            }
        }
        if (bmpValid && mCacheBitmap != null) {
            Canvas canvas = getHolder().lockCanvas();
            if (canvas != null) {
                canvas.drawBitmap(mCacheBitmap, null, new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), null);
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }
