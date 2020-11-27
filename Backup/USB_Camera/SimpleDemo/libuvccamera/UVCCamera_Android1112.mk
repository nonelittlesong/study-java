#/*
# * UVCCamera
# * library and sample to access to UVC web camera on non-rooted Android device
# * 
# * Copyright (c) 2014-2017 saki t_saki@serenegiant.com
# * 
# * File name: Android.mk
# * 
# * Licensed under the Apache License, Version 2.0 (the "License");
# * you may not use this file except in compliance with the License.
# *  You may obtain a copy of the License at
# * 
# *     http://www.apache.org/licenses/LICENSE-2.0
# * 
# *  Unless required by applicable law or agreed to in writing, software
# *  distributed under the License is distributed on an "AS IS" BASIS,
# *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# *  See the License for the specific language governing permissions and
# *  limitations under the License.
# * 
# * All files in the folder are under this Apache License, Version 2.0.
# * Files in the jni/libjpeg, jni/libusb, jin/libuvc, jni/rapidjson folder may have a different license, see the respective files.
#*/

######################################################################
# Make shared library libUVCCamera.so
######################################################################
LOCAL_PATH	:= $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := opencv_java
LOCAL_SRC_FILES := /home/song/OpenCV-android-sdk/sdk/native/libs/$(TARGET_ARCH_ABI)/libopencv_java.so
LOCAL_EXPORT_C_INCLUDES := \
        /home/song/OpenCV-android-sdk/sdk/native/jni/include \
        /home/song/OpenCV-android-sdk/sdk/native/jni/include/opencv
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
NCNN_DIR := /home/song/ncnn/build-android/install
LOCAL_MODULE := ncnn
LOCAL_SRC_FILES := $(NCNN_DIR)/lib/libncnn.a
LOCAL_EXPORT_C_INCLUDES := $(NCNN_DIR)/include
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
######################################################################
# Make shared library libUVCCamera.so
######################################################################
CFLAGS := -Werror

LOCAL_C_INCLUDES := \
		$(LOCAL_PATH)/ \
		$(LOCAL_PATH)/../ \
		$(LOCAL_PATH)/../rapidjson/include

LOCAL_CFLAGS := $(LOCAL_C_INCLUDES:%=-I%)
LOCAL_CFLAGS += -DANDROID_NDK
LOCAL_CFLAGS += -DLOG_NDEBUG
LOCAL_CFLAGS += -DACCESS_RAW_DESCRIPTORS
LOCAL_CFLAGS += -O3 -fstrict-aliasing -fprefetch-loop-arrays -fopenmp

LOCAL_CPPFLAGS += -std=c++11 -fopenmp

LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -ldl
LOCAL_LDLIBS += -llog
LOCAL_LDLIBS += -landroid -fopenmp

LOCAL_SHARED_LIBRARIES += usb100 uvc opencv_java
LOCAL_STATIC_LIBRARIES += ncnn

LOCAL_ARM_MODE := arm

LOCAL_SRC_FILES := \
		_onload.cpp \
		utilbase.cpp \
		UVCCamera.cpp \
		UVCPreview.cpp \
		UVCButtonCallback.cpp \
		UVCStatusCallback.cpp \
		Parameters.cpp \
		serenegiant_usb_UVCCamera.cpp \
		mtcnnAttribute.cpp \
		native-lib.cpp

LOCAL_MODULE    := UVCCamera
include $(BUILD_SHARED_LIBRARY)
