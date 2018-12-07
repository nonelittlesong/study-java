
## 一、Paint
**1. Paint的Flag**  
1. Paint.ANTI_ALIAS_FLAG ：抗锯齿标志
1. Paint.FILTER_BITMAP_FLAG : 使位图过滤的位掩码标志
1. Paint.DITHER_FLAG : 使位图进行有利的抖动的位掩码标志
1. Paint.UNDERLINE_TEXT_FLAG : 下划线
1. Paint.STRIKE_THRU_TEXT_FLAG : 中划线
1. Paint.FAKE_BOLD_TEXT_FLAG : 加粗
1. Paint.LINEAR_TEXT_FLAG : 使文本平滑线性扩展的油漆标志
1. Paint.SUBPIXEL_TEXT_FLAG : 使文本的亚像素定位的绘图标志
1. Paint.EMBEDDED_BITMAP_TEXT_FLAG : 绘制文本时允许使用位图字体的绘图标志

## 二、ValueAnimator
[Android属性动画之ValueAnimator的介绍](https://www.cnblogs.com/huolongluo/p/6792362.html)  
它简单的来说，就是一个数值发生器，它可以产生你想要的各种数值。其实，在Android属性动画中，如何产生每一步具体实现动画效果，都是通过ValueAnimator计算出来的。  
比如我们现在要实现一个从0\~100的位移动画，ValueAnimator会根据动画持续的总时间产生一个0\~1时间因子，有了这样一个时间因子。通过相应的变幻，就可以根据你的startValue和endValue来生成相应的值。同时，通过插值器的使用，我们还可以进一步控制每一个时间因子它产生值的一个变化速率。如果我们使用线性插值器，那么它生成数值的时候，就会形成一个线性变化，只要时间相同，它的增量也相同。如果我们使用一个加速度的插值器，那么它的增量变化就会呈现一个二次曲线图。  
