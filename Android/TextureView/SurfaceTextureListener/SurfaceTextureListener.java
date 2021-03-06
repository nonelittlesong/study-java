    /**
     * This listener can be used to be notified when the surface texture
     * associated with this texture view is available.
     */
    public static interface SurfaceTextureListener {
        /**
         * Invoked when a {@link TextureView}'s SurfaceTexture is ready for use.
         *
         * @param surface The surface returned by
         *                {@link android.view.TextureView#getSurfaceTexture()}
         * @param width The width of the surface
         * @param height The height of the surface
         */
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height);

        /**
         * Invoked when the {@link SurfaceTexture}'s buffers size changed.
         *
         * @param surface The surface returned by
         *                {@link android.view.TextureView#getSurfaceTexture()}
         * @param width The new width of the surface
         * @param height The new height of the surface
         */
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height);

        /**
         * Invoked when the specified {@link SurfaceTexture} is about to be destroyed.
         * If returns true, no rendering should happen inside the surface texture after this method
         * is invoked. If returns false, the client needs to call {@link SurfaceTexture#release()}.
         * Most applications should return true.
         *
         * @param surface The surface about to be destroyed
         */
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface);

        /**
         * Invoked when the specified {@link SurfaceTexture} is updated through
         * {@link SurfaceTexture#updateTexImage()}.
         *
         * @param surface The surface just updated
         */
        public void onSurfaceTextureUpdated(SurfaceTexture surface);
    }
