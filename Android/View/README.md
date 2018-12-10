
## 一、 GestureDetector
View可以实现基本的触摸操作，更复杂的双击，滑动需要GestureDetector。  
构造函数：   
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
**boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)**  
```
/**
         * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
         * current move {@link MotionEvent}. The distance in x and y is also supplied for
         * convenience.
         *
         * @param e1 The first down motion event that started the scrolling.
         * @param e2 The move motion event that triggered the current onScroll.
         * @param distanceX The distance along the X axis that has been scrolled since the last
         *              call to onScroll. This is NOT the distance between {@code e1}
         *              and {@code e2}.
         * @param distanceY The distance along the Y axis that has been scrolled since the last
         *              call to onScroll. This is NOT the distance between {@code e1}
         *              and {@code e2}.
         * @return true if the event is consumed, else false
         */
```
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
