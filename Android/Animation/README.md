## RequestLayout：
To initiate a layout, call requestLayout().This method is typically called by a view on itself when it believes that it can no longer fit within its current bounds.  
从上面这句话看出，当View的边界，也可以理解为View的宽高，发生了变化，不再适合现在的区域，可以调用requestLayout方法重新对View布局。View执行requestLayout方法，会向上递归到顶级父View中，再执行这个顶级父View的requestLayout，所以其他View的onMeasure，onLayout也可能会被调用。  
