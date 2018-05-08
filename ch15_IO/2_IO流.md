# IO流

把数据流动看作流水.

## 流的分类

### 按流动方法分

① 输入流

② 输出流

输入、输出是从内存的角度来划分的. 如果从内存流向硬盘,那就是输出流.反之则是输入流.

### 按流的类型分

① 字节流 主要由InputStream和OutputStream作为基类.

② 字符流 主要由Reader 和 Writer 作为基类.

### 按照流是否直接与特定的存储设备相连分

① 节点流:可以从或向一个特定的地方（节点）读写数据.

② 处理流:是对一个已存在的流的连接和封装，通过所封装的流的功能调用实现数据读写,

![](https://github.com/HuangYiCheng1997/create-picture-url/blob/master/java/CrazyJava/Java%20IO.png?raw=true)



## **操作节点流**

### **字节流**

```java
//字节输入流
FileInputStream fis = new FileInputStream(String fileName);
//字节输出流
FileOutPutStream fos = new FileOutputStream(String fileName);
package File;

import java.io.*;

public class TestFile {

	public static void main(String[] args) throws IOException {

		// 获取文件路径
		File oldFile = new File("C:\\Users\\Administrator\\Desktop\\区块链.txt");
		File newFile = new File("C:\\Users\\Administrator\\Desktop\\新区块链.txt");
        // 检查文件是否存在
		if (!newFile.exists()) {
			newFile.createNewFile();
		}

		FileInputStream ofs = new FileInputStream(oldFile);
		FileOutputStream nfs = new FileOutputStream(newFile);

		byte[] b = new byte[1024];
		while (ofs.read(b) > 0)// 把内容装进数组
		{	
			nfs.write(b);
		}
		System.out.println("done");
	}

```

### **字符流**

```java
//字符输入流
FileReader fr = new FileReader(String fileName);
//字符输出流
FileWriter fw = new FileWriter(String fileName);
package File;

import java.io.*;

public class TestFile {

	public static void main(String[] args) throws IOException {

		// 获取文件路径
		File oldFile = new File("C:\\Users\\Administrator\\Desktop\\区块链.txt");
		File newFile = new File("C:\\Users\\Administrator\\Desktop\\新区块链.txt");
		if (!newFile.exists()) {
			newFile.createNewFile();
		}

		FileReader fr = new FileReader(oldFile);
		FileWriter fw = new FileWriter(newFile);

		char[] c = new char[1024];

		while (fr.read(c) > 0)// 把内容装进数组
		{
			fw.write(c);
		}
		System.out.println("done");
	}

}
```

**操作节点流一般比较麻烦,不常用,我们来学点常用的**

## **使用处理流**

**使用处理流包装节点流,让处理流执行输入输出,让节点流与底层IO设备、文件 交互.**



## 重点向标准输入/输出

System类里提供了三个重定向标准输入\输出的方法.

```java
static void setErr(PrintStream err);
static void setIn(InputStream in);
static void setOut(PrintStream out);
```

```java
package File;

import java.io.*;

public class TestFile {

	public static void main(String[] args) throws IOException {

		// 获取文件路径
		File oldFile = new File("C:\\Users\\Administrator\\Desktop\\区块链.txt");
		PrintStream ps = new PrintStream(new FileOutputStream(oldFile));
		System.setOut(ps);
		System.out.println("helllllllllllllllllllllllllllllllll");
	}

}
```

## Java虚拟机读写其他进程的数据

使用Runtime对象的exec()方法可以运行平台上的其他程序,该方法产生一个Process对象,Process对象代表由该Java程序启动的子进程.有三个方法用于让程序和其子进程进行通信.

```java
InputStream getErrorStream();
InputStream getInputStream();
OutputStream getOutputStream();
```

```java
package File;

import java.io.*;

public class TestFile {

	public static void main(String[] args) throws IOException {

		Process p = Runtime.getRuntime().exec("java -version");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String content;

		while ((content = br.readLine()) != null) {
			System.out.println(content);
		}

	}

}
```

