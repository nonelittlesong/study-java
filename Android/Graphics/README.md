

## 一、Paint
**1. Paint的Flag**  
1. Paint.ANTI_ALIAS_FLAG ： 抗锯齿标志
1. Paint.FILTER_BITMAP_FLAG : 使位图过滤的位掩码标志
1. Paint.DITHER_FLAG : 使位图进行有利的抖动的位掩码标志
1. Paint.UNDERLINE_TEXT_FLAG : 下划线
1. Paint.STRIKE_THRU_TEXT_FLAG : 中划线
1. Paint.FAKE_BOLD_TEXT_FLAG : 加粗
1. Paint.LINEAR_TEXT_FLAG : 使文本平滑线性扩展的油漆标志
1. Paint.SUBPIXEL_TEXT_FLAG : 使文本的亚像素定位的绘图标志
1. Paint.EMBEDDED_BITMAP_TEXT_FLAG : 绘制文本时允许使用位图字体的绘图标志

**2. Paint的Style**  
1. Paint.Style.FILL : 内部填充  
1. Paint.Style.FILL_AND_STROKE : 填充和描边
1. Paint.Style.STROKE : 描边

**3. Paint的Cap和Join**  
```
paint.setStrokeJoin(Paint.Join.ROUND); // 设置连接处为圆形
paint.setStrokeCap(Paint.Cap.ROUND); // 设置线冒为圆形
```

**4. Paint的Dither**  
```
paint.setDither(true); // 防抖动
```
