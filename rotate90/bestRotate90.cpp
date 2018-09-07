void mRotate90(Mat &img) {
    cv::Point2f center = cv::Point2f(img.rows/2, img.rows/2);
    double angle = 270;
    double scale = 1;
    cv::warpAffine(img, img, cv::getRotationMatrix2D(center, angle, scale), cv::Size(img.rows, img.cols));
}
