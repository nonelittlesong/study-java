cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fopenmp")
set(CMAKE_VERBOSE_MAKEFILE on)

# set(OpenCV_DIR /home/song/OpenCV-android-sdk/sdk/native/jni)
set(OpenCV_DIR ${CMAKE_CURRENT_SOURCE_DIR}/../OpenCVLibrary/src/main/jni/opencv/jni)
set(NCNN_DIR /home/song/ncnn/build-android/install)

# opencv
include_directories(${OpenCV_DIR}/include
                    ${OpenCV_DIR}/include/opencv)
add_library(opencv_java SHARED IMPORTED)
set_target_properties(opencv_java
                      PROPERTIES
                      IMPORTED_LOCATION ${OpenCV_DIR}/../libs/${ANDROID_ABI}/libopencv_java.so)

# ncnn
include_directories(${NCNN_DIR}/include)
add_library(ncnn STATIC IMPORTED)
set_target_properties(ncnn
                      PROPERTIES
                      IMPORTED_LOCATION ${NCNN_DIR}/lib/libncnn.a)

find_library( log-lib
              log )

add_library(native-lib
            SHARED
            src/main/cpp/native-lib.cpp
            src/main/cpp/mtcnnAttribute.cpp
            )

target_link_libraries(native-lib
                      opencv_java
                      ncnn
                      ${log-lib})
