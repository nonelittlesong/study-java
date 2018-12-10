    /**
     * Helper for getTransformation. Subclasses should implement this to apply
     * their transforms given an interpolation value.  Implementations of this
     * method should always replace the specified Transformation or document
     * they are doing otherwise.
     *
     * 每一个frame进行的操作
     *
     * @param interpolatedTime The value of the normalized time (0.0 to 1.0)
     *        after it has been run through the interpolation function.
     * @param t The Transformation object to fill in with the current
     *        transforms.
     */
    protected void applyTransformation(float interpolatedTime, Transformation t) {
    }
    
    /**
     * Initialize this animation with the dimensions of the object being
     * animated as well as the objects parents. (This is to support animation
     * sizes being specified relative to these dimensions.)
     *
     * <p>Objects that interpret Animations should call this method when
     * the sizes of the object being animated and its parent are known, and
     * before calling {@link #getTransformation}.
     *
     *
     * @param width Width of the object being animated
     * @param height Height of the object being animated
     * @param parentWidth Width of the animated object's parent
     * @param parentHeight Height of the animated object's parent
     */
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        reset();
        mInitialized = true;
    }
    
    /**
     * 动画是否会影响View的大小
     * <p>Indicates whether or not this animation will affect the bounds of the
     * animated view. For instance, a fade animation will not affect the bounds
     * whereas a 200% scale animation will.</p>
     *
     * @return true if this animation will change the view's bounds
     */
    public boolean willChangeBounds() {
        // assume we will change the bounds
        return true;
    }

    /**
     * 动画开始，结束和重复时调用，
     * <p>An animation listener receives notifications from an animation.
     * Notifications indicate animation related events, such as the end or the
     * repetition of the animation.</p>
     */
    public static interface AnimationListener {
        /**
         * <p>Notifies the start of the animation.</p>
         *
         * @param animation The started animation.
         */
        void onAnimationStart(Animation animation);

        /**
         * <p>Notifies the end of the animation. This callback is not invoked
         * for animations with repeat count set to INFINITE.</p>
         *
         * @param animation The animation which reached its end.
         */
        void onAnimationEnd(Animation animation);

        /**
         * <p>Notifies the repetition of the animation.</p>
         *
         * @param animation The animation which was repeated.
         */
        void onAnimationRepeat(Animation animation);
    }
