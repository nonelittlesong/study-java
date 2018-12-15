* 在爷View设置android：clipChildren。  

## RequestLayout：
To initiate a layout, call requestLayout().This method is typically called by a view on itself when it believes that it can no longer fit within its current bounds.  
从上面这句话看出，当View的边界，也可以理解为View的宽高，发生了变化，不再适合现在的区域，可以调用requestLayout方法重新对View布局。View执行requestLayout方法，会向上递归到顶级父View中，再执行这个顶级父View的requestLayout，所以其他View的onMeasure，onLayout也可能会被调用。  
## android:gravity
FrameLayout此属性无效。  
可以在子视图中使用`android:layout_gravity`达到想要的效果。  
## [在不传父View的情况下Infate最外层layout设置宽高失效](https://www.cnblogs.com/tieba/p/4844744.html)
给最外层layout设置固定宽高，然后使用inflate(layoutId, null )方式加载，则layoutId的最外层的控件的宽高是没有效果的。  
inflate对外主要有两种函数实现：  
public View inflate(int resource, ViewGroup root);  
public View inflate(int resource, ViewGroup root, boolean attachToRoot);  
这两个函数最终都会调用public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot)函数。  
只有在root!=null的情况下才会根据xml参数（attrs）创建并设置LayoutParams。  
## [代码中设置layout_gravity](https://blog.csdn.net/jmflovezlf/article/details/41049791)  
```java
final View contentView = View.inflate(context, contentID, null);// contentView是FrameLayout的子View
contentView.measure(0,0);
FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(contentView.getMeasuredWidth(), contentView.getMeasuredHeight());
params.gravity = Gravity.END;
contentView.setLayoutParams(params);
```
