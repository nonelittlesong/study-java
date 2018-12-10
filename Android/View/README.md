
## 一、 GestureDetector
### 1. OnGestureListener
### 2. OnDoubleTapListener
### 3. OnContextClickListener
### 4. SimpleOnGestureListener
### 5. setIsLongpressEnabled  
是否开启长按事件？默认开启。  
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
