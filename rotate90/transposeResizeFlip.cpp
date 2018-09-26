int matRotateClockWise90(Mat &src, int height, int width)
{
	if (src.empty())
	{
		LOGD("RorateMat src is empty!");
        return -1;
	}

  cv::Mat imgT(height, width, CV_8UC4);
  cv::Mat imgF(height, width, CV_8UC4);

	// 矩阵转置
	transpose(src, imgT);
  
  resize(imgT, imgF, Size(width, height));
  
  // 翻转模式，flipCode == 0垂直翻转（沿X轴翻转），flipCode>0水平翻转（沿Y轴翻转），flipCode<0水平垂直翻转（先沿X轴翻转，再沿Y轴翻转，等价于旋转180°）
	flip(imgF, src, 1);
  
	return 0;
}
