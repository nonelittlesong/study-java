#include <opencv2/opencv.hpp>
 
void swap(unsigned char& a, unsigned char& b){
	char c = a;
	a = b;
	b = c;
}
 
int main() {
	cv::Mat img = cv::imread("test.jpg");
	int w = img.cols;
	int h = img.rows;
	for (int i = 0; i < w; i++) 
	{
		for (int j = 0; j < h; j++) 
		{
			int I = h - 1 - j;
			int J = i;
			while ((i*h + j) > (I*w + J))
			{
				int p = I*w + J;
				int tmp_i = p / h;
				int tmp_j = p % h;
				I = h - 1 - tmp_j;
				J = tmp_i;
			} 
			swap(*(img.data + i*h*3 + j*3 + 0), *(img.data + I*w*3 + J*3 + 0));
			swap(*(img.data + i*h*3 + j*3 + 1), *(img.data + I*w*3 + J*3 + 1));
			swap(*(img.data + i*h*3 + j*3 + 2), *(img.data + I*w*3 + J*3 + 2));
		}
	}
 
	img.cols = h;
	img.rows = w;
	img.step = h*3;
 
	cv::imshow("img", img);
	cv::waitKey();
	return 0;
}
