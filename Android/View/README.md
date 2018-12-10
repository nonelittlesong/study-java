
## 一、 GestureDetector
View可以实现基本的触摸操作，更复杂的双击，滑动需要GestureDetector。  
```
/**
     * Creates a GestureDetector with the supplied listener.
     * You may only use this constructor from a {@link android.os.Looper} thread.
     * @see android.os.Handler#Handler()
     *
     * @param context the application's context
     * @param listener the listener invoked for all the callbacks, this must
     * not be null.
     *
     * @throws NullPointerException if {@code listener} is null.
     */
    public GestureDetector(Context context, OnGestureListener listener) {
        this(context, listener, null);
    }
```
### 1. OnGestureListener

### 2. OnDoubleTapListener
### 3. OnContextClickListener
### 4. SimpleOnGestureListener
### 5. setIsLongpressEnabled  
是否开启长按事件？默认开启。  
### 6. onTouchEvent

## 二、 OnTouchListener
```
/**
* Called when a touch event is dispatched to a view. This allows listeners to
* get a chance to respond before the target view.
*
* @param v The view the touch event has been dispatched to.
* @param event The MotionEvent object containing full information about
*        the event.
*/
boolean onTouch(View v, MotionEvent event);
```
## 三、 MotionEvent
