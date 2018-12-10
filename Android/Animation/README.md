
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
