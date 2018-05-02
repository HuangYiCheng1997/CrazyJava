# 面向对象下



## 单例类

单例类:始终只能创建一个实例的类

### 实现单例类:

① 把构造器私有化

② 提供使用构造器的方法

③ 记录实例

```java
class Singleton
{
	private static Singleton instance; //保存实例
	public String name;
    private Singleton() //构造器
    {
    	this.name = "黄大仁";
    } 
    
    public static Singleton getInstance()
    {
        if (instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }   
}
```

### 单例类优化

① 使用同步保证线程安全

② 使用volatile关键字

​	volatile修饰的变量,程序每次存储或读取这个变量的时候,都会直接从变量地址中读取数据.

③ 防止反射调用私有构造方法

④ 让单例类序列化安全

```java
class Singleton implements Serializable {
    private volatile static Singleton singleton = null;

    private Singleton() {
        // 处理反射
        if (singleton != null) {
            throw new RuntimeException("该单例类已被实例化");
        }
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
```



## final修饰符



## 抽象类

## 接口

## 内部类

## 枚举类

## Lambda表达式

## 对象和与垃圾回收