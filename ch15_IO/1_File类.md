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

# Path

```java
public interface Path extends Comparable<Path>, Iterable<Path>, Watchable
```

An object that may be used to locate a file in a file system. It will typically represent a system dependent file path.

### Creating a Path Instance

```java
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {

    public static void main(String[] args) {

        Path path = Paths.get("c:\\data\\myfile.txt");

    }
}
```

### Creating a Relative Path

```java
// You create a relative path using the Paths.get(basePath, relativePath) method.
Path file = Paths.get("d:\\data", "projects\\a-project\\myfile.txt");
// 注意那个 点
Path currentDir = Paths.get("d:\\data\\projects\.\a-project");
d:\data\projects\a-project
// -----------------------------
String path = "d:\\data\\projects\\a-project\\..\\another-project";
Path parentDir2 = Paths.get(path);
d:\data\projects\another-project
```

### Path.normalize()

normalize的意思是除去路径中间的点

```java
String originalPath = "d:\\data\\projects\\a-project\\..\\another-project";
Path path1 = Paths.get(originalPath);
System.out.println("path1 = " + path1);
Path path2 = path1.normalize();
System.out.println("path2 = " + path2);
```



# Files

Files类提供一些操作file的方法.

```java
Files.exists();
Files.createDirectory();
Files.copy();
Files.move();
```

