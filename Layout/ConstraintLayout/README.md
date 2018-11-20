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
