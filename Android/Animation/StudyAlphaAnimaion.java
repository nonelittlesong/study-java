// https://blog.csdn.net/shibin1990_/article/details/51602498    
/**
     * 和透明度相关的动画
     * Constructor to use when building an AlphaAnimation from code
     * 
     * @param fromAlpha Starting alpha value for the animation, where 1.0 means
     *        fully opaque and 0.0 means fully transparent.
     * @param toAlpha Ending alpha value for the animation.
     */
    public AlphaAnimation(float fromAlpha, float toAlpha) {
        mFromAlpha = fromAlpha;
        mToAlpha = toAlpha;
    }
    
    /**
     * 完成后执行
     * If fillAfter is true, the transformation that this animation performed
     * will persist when it is finished. Defaults to false if not set.
     * Note that this applies to individual animations and when using an {@link
     * android.view.animation.AnimationSet AnimationSet} to chain
     * animations.
     *
     * @param fillAfter true if the animation should apply its transformation after it ends
     * @attr ref android.R.styleable#Animation_fillAfter
     *
     * @see #setFillEnabled(boolean)
     */
    public void setFillAfter(boolean fillAfter) {
        mFillAfter = fillAfter;
    }
    
/**
 * 使用步骤
 */
AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, alpha);
// make it instant
alphaAnimation.setDuration(0);
alphaAnimation.setFillAfter(true);
view.startAnimation(alphaAnimation);

