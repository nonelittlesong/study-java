就是右上角三个点  

**1.重载**  
加载menu
```java
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.filter, menu);
    return true;
}
```

**2.重载**  
public boolean onOptionsItemSelected(MenuItem item)  
实现menu被点击时的操作  

