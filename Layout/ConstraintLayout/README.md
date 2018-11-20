约束布局  
参考：  
* [实战篇ConstraintLayout的崛起之路](https://www.jianshu.com/p/a74557359882)  

添加依赖：  
```
implemention 'com.android.support.constraint:constraint-layout:1.1.2'
```
## 1. Circular positioning (圆形定位)
以一定的角度和距离，用一个控件的中心去约束另一个控件的中心。  
```
<Button android:id="@+id/buttonA" .../>
<Button android:id="@+id/buttonB" ...
    // 约束控件ID
    app:layout_constraintCircle="@id/buttonA"
    // 半径
    app:layout_constraintCircleRadius="100dp"
    // 角度 水平向右为0， 逆时针旋转
    app:layout_constraintCircleAngle="45" />
```
## 2. WRAP_CONTENT: enforcing constraints (强制约束)
控件的长/宽设置为wrap_content时，如果实际宽度大于约束的最大宽度，约束将无效。  
为了防止约束失效，可以添加以下属性：  
```
app:layout_constraintWidth="true|false" // 默认false
app:layout_constraintHeight="true:false" // 会导致渲染的时间变慢
```
## 3. Chains (链)
链使我们能够对一组水平或垂直方向的相关控件的属性进行统一的管理。  
```
app:layout_constraintHorizontal_chainStyle="spread" // Spread Chain 默认样式
app:layout_constraintHorizontal_chainStyle="spread_inside" // Spread Inside Chain
app:layout_constraintHorizontal_chainStyle="packed" // Packed Chain
app:layout_constraintHorizontal_bias="0" // Packed Chain with Bias
```
![images](https://github.com/nonelittlesong/study-java/blob/master/Layout/ConstraintLayout/imgs/Screenshot%20from%202018-11-20%2009-53-31.png)  
Weighted样式下，宽或高的维度应设置为match_parent(0dp)  
## 4. MATCH_CONSTRAINT dimensions (填充父窗体约束)
在约束布局中宽/高的维度match_parent被0dp替代，默认生成的大小占所有可用空间。  
```
layout_constraintWidth_min / layout_constraintHeight_min // 设置最小尺寸
layout_constraintWidth_max / layout_constraintHeight_max // 设置最大尺寸
layout_constraintWidth_percent / layout_constraintHeight_percent // 设置相对于父控件的比例
```
## 5. goneMargin (隐藏边距)
当约束目标的可见性为View.GONE时，还可以通过以下属性设置不同的边距值。  
```
layout_goneMarginStart
layout_goneMarginEnd
layout_goneMarginTop
layout_goneMarginBottom
layout_goneMarginLeft
layout_goneMarginRight
```
## 6. 百分比布局
我们经常会遇到这样的需求，个人主页的顶部的背景图片宽高比为16:9  
要使用`app:layout_constraintDimensionRatio="H,16:9"`，至少要将宽/高之一设为0dp  
```
<!-- "W,9:19" 同样的效果 -->
<ImageView
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:scaleType="centerCrop"
    android:src="@mipmap/icon"
    app:layout_constraintDimensionRatio="H,16:9"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"/>
```
* 要将宽度设为屏幕的一般，新增`app:layout_constraintWidth_percent="0.5"`  
* 左对齐，新增`app:layout_constraintHorizontal_bias="0"`  
调整控件到顶部的距离：  
```
<android.support.constraint.Guideline
    android:id="@+id/guide"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintGuide_percent="0.2"
    android:orientation="horizontal" />
<ImageView
    ...
    app:layout_constraintTop_toBottomOf="@id/guide"/>
```
## 6. Guideline (辅助线）
Guideline具有以下三种定位方式：  
* layout_constraintGuide_begin
* layout_constraintGuide_end
* layout_constraintGuide_percent

## 7. Barrier
