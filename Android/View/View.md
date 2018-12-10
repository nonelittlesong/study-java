```java
    /**
     * 在所有子View添加之后调用
     *
     * Finalize inflating a view from XML.  This is called as the last phase
     * of inflation, after all child views have been added.
     *
     * <p>Even if the subclass overrides onFinishInflate, they should always be
     * sure to call the super method, so that we get called.
     */
    @CallSuper
    protected void onFinishInflate() {
    }
```
