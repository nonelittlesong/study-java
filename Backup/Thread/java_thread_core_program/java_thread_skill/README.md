Java多线程技能  
### 显示线程名
```
Thread.currentThread().getName();
```
## 2. 继承Thread类
Thread类结构：  
```
public class Thread implements Runnable
```
### 实现Runnable接口
```
Runnable runnable = new MyRunnable();
Thread thread = new Thread(runnable);
thread.start();
```
### 共享数据
synchronized可以在任意对象和方法上加锁，而加锁的这段代码称为“互斥区”或“临界区”。  
### 留意i--与System.out.println()的异常
虽然println()方法内部是同步的，但i--的操作是在进入println（）之前进行的，所以会发生非线程安全的问题。  

## 3. currentThread()方法
## 4. isAlive()方法
