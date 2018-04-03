# File

File类代表文件和目录

File类可以创建 删除 重命名文件和目录,但是不能访问文件的内容.

## 路径相关

```java
String getName(); //返回该File对象代表的文件或目录的名字.
String getPath();//返回该File对象对应的路径
String getAbsolutePath();//返回该File对象对应的绝对路径.
String getParent(); //返回此File对象的父目录的名字
File getAbsoluteFile(); // 返回代表此File对象的绝对路径的File对象.
boolean isAbsolute();
```

## 文件操作

```java
boolean creatNewFile();//当该File对象对应的文件不存在时,创建这个对象.
static File createTempFile(String prefix,String suffix);
// 在默认的路径下创建指定的文件,并返回代表该文件的File对象.
static File createTempFile(String prefix,String suffix,File director);
// 在指定的文件夹下创建指定的文件,并返回代表该文件的File对象.
boolean delete();
void deleteOnExit();
```

## 目录操作

```java
boolean mkdir();
String[] list();
File listFiles();
static File[] listRoots();//WTF???????
```

## 其他

```java
boolean exists();
boolean isFile();
boolean isDirectory();
boolean canRead();
boolean canWrite();
long lastModified();
long length(); // 返回文件内容长度
```

## 文件过滤器

list()方法可以接受一FilenameFilter参数,FilenameFilter是各函数式接口,你懂的

```java
package File;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class TestFile {

	public static void main(String[] args) throws IOException {

		File desktop = new File("C:\\Users\\Administrator\\Desktop");

		FilenameFilter ff = (dir, name) -> name.equals("English");
		for (String f : desktop.list(ff)) {
			System.out.println(f);
		}
	}

}
```

