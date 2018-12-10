
## 一、Paint
https://github.com/nonelittlesong/study-java/blob/master/Android/Graphics/README.md  

## 二、ValueAnimator
[Android属性动画之ValueAnimator的介绍](https://www.cnblogs.com/huolongluo/p/6792362.html)  
它简单的来说，就是一个数值发生器，它可以产生你想要的各种数值。其实，在Android属性动画中，如何产生每一步具体实现动画效果，都是通过ValueAnimator计算出来的。  
比如我们现在要实现一个从0\~100的位移动画，ValueAnimator会根据动画持续的总时间产生一个0\~1时间因子，有了这样一个时间因子。通过相应的变幻，就可以根据你的startValue和endValue来生成相应的值。同时，通过插值器的使用，我们还可以进一步控制每一个时间因子它产生值的一个变化速率。如果我们使用线性插值器，那么它生成数值的时候，就会形成一个线性变化，只要时间相同，它的增量也相同。如果我们使用一个加速度的插值器，那么它的增量变化就会呈现一个二次曲线图。  
#### 1. ValueAnimator ofFloat(float... values)
构造函数。返回在给定的values间animate的ValueAnimator。
#### 2. setFloatValues(float... values)
```java
public void setFloatValues(float... values) {
    if (values == null || values.length == 0) {
        return;
    }
    if (mValues == null || mValues.length == 0) {
        setValues(PropertyValuesHolder.ofFloat("", values));
    } else {
        PropertyValuesHolder valuesHolder = mValues[0];
        valuesHolder.setFloatValues(values);
    }
    // New property/values/target should cause re-initialization prior to starting
    mInitialized = false;
    }
```
#### 3. addUpdateListener(AnimatorUpdateListener listener)
```java
/**
 * Adds a listener to the set of listeners that are sent update events through the life of
 * an animation. This method is called on all listeners for every frame of the animation, 
 * after the values for the animation have been calculated.
 *  
 * @param listener the listener to be added to the current set of listeners for this animation.
 */
public void addUpdateListener(AnimatorUpdateListener listener) {
    if (mUpdateListeners == null) {
        mUpdateListeners = new ArrayList<AnimatorUpdateListener>();
    }
    mUpdateListeners.add(listener)   
}
```
#### 4. setInterpolator(TimeInterpolator value)
插入器。  
计算动画经过的片段。  
可以是线性或非线性。例如acceleration和deceleration。  
默认是android.view.animation.AccelerateDecelerateInterpolator。  
```java
    /**
     * The time interpolator used in calculating the elapsed fraction of this animation. The
     * interpolator determines whether the animation runs with linear or non-linear motion,
     * such as acceleration and deceleration. The default value is
     * {@link android.view.animation.AccelerateDecelerateInterpolator}
     *
     * @param value the interpolator to be used by this animation. A value of <code>null</code>
     * will result in linear interpolation.
     */
    @Override
    public void setInterpolator(TimeInterpolator value) {
        if (value != null) {
            mInterpolator = value;
        } else {
            mInterpolator = new LinearInterpolator();
        }
    }
```
#### 5. setDuration(long duration)
设置动画时长。默认是300毫秒。
```java
    /**
     * Sets the length of the animation. The default duration is 300 milliseconds.
     *
     * @param duration The length of the animation, in milliseconds. This value cannot
     * be negative.
     * @return ValueAnimator The object called with setDuration(). This return
     * value makes it easier to compose statements together that construct and then set the
     * duration, as in <code>ValueAnimator.ofInt(0, 10).setDuration(500).start()</code>.
     */
    @Override
    public ValueAnimator setDuration(long duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " +
                    duration);
        }
        mDuration = duration;
        return this;
    }
```
#### 6. start(boolean playBackwards)
开始动画。  
调用它的线程Looper不能为空。  
```java

    /**
     * Start the animation playing. This version of start() takes a boolean flag that indicates
     * whether the animation should play in reverse. The flag is usually false, but may be set
     * to true if called from the reverse() method.
     *
     * <p>The animation started by calling this method will be run on the thread that called
     * this method. This thread should have a Looper on it (a runtime exception will be thrown if
     * this is not the case). Also, if the animation will animate
     * properties of objects in the view hierarchy, then the calling thread should be the UI
     * thread for that view hierarchy.</p>
     *
     * @param playBackwards Whether the ValueAnimator should start playing in reverse.
     */
    private void start(boolean playBackwards) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        mReversing = playBackwards;
        mSelfPulse = !mSuppressSelfPulseRequested;
        // Special case: reversing from seek-to-0 should act as if not seeked at all.
        if (playBackwards && mSeekFraction != -1 && mSeekFraction != 0) {
            if (mRepeatCount == INFINITE) {
                // Calculate the fraction of the current iteration.
                float fraction = (float) (mSeekFraction - Math.floor(mSeekFraction));
                mSeekFraction = 1 - fraction;
            } else {
                mSeekFraction = 1 + mRepeatCount - mSeekFraction;
            }
        }
        mStarted = true;
        mPaused = false;
        mRunning = false;
        mAnimationEndRequested = false;
        // Resets mLastFrameTime when start() is called, so that if the animation was running,
        // calling start() would put the animation in the
        // started-but-not-yet-reached-the-first-frame phase.
        mLastFrameTime = -1;
        mFirstFrameTime = -1;
        mStartTime = -1;
        addAnimationCallback(0);

        if (mStartDelay == 0 || mSeekFraction >= 0 || mReversing) {
            // If there's no start delay, init the animation and notify start listeners right away
            // to be consistent with the previous behavior. Otherwise, postpone this until the first
            // frame after the start delay.
            startAnimation();
            if (mSeekFraction == -1) {
                // No seek, start at play time 0. Note that the reason we are not using fraction 0
                // is because for animations with 0 duration, we want to be consistent with pre-N
                // behavior: skip to the final value immediately.
                setCurrentPlayTime(0);
            } else {
                setCurrentFraction(mSeekFraction);
            }
        }
    }
```
