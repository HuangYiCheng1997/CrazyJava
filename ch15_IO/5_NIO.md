# NIO

从JDK1.4开始,Java提供了一系列改进的输入输出处理的新功能,这些功能被称为NIO.

NIO 与 传统IO的区别在于,数据打包和传输的方法.

传统IO以流的方式处理数据,而NIO以块的方式处理数据.

## NIO原理

### NIO与IO的区别

首先来讲一下传统的IO和NIO的区别，传统的IO又称BIO，即阻塞式IO，NIO就是非阻塞IO了。还有一种[AIO](http://stevex.blog.51cto.com/4300375/1284437)就是异步IO，这里不加阐述了。

Java IO的各种流是阻塞的。这意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情了。 

Java NIO的非阻塞模式，使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取。而不是保持线程阻塞，所以直至数据变的可以读取之前，该线程可以继续做其他的事情。 非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。 线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程现在可以管理多个输入和输出通道（channel）。

### NIO的工作原理

在系统文件与缓冲区(buffer)之间建立通道(channel),程序从缓冲区中读取或写入数据,从而实现文件的读写.

## 缓冲区Buffer

Buffer是一个抽象类,其常用的子类是ByteBuffer 和 CharBuffer.

Buffer对象是一个固定数量的数据的容器,在这里数据可被存储并在之后用于检索。

Buffer有四个重要的属性:

- 容量（Capacity）：缓冲区能够容纳的数据元素的最大数量。这一个容量在缓冲区创建时被设定，并且永远不能改变。
- 上界(Limit)：缓冲区的第一个不能被读或写的元素。或者说,缓冲区中现存元素的计数。
- 位置(Position)：下一个要被读或写的元素的索引。位置会自动由相应的 get( )和 put( )函数更新。
- 标记(Mark)：做个标志,使用reset()方法把position转到这个标记位置.

```java
package NIO;

import java.nio.ByteBuffer;

public class BufferDemo {

	public static void main(String[] args) {
		
		// 创建一个字节缓冲区,容量是1024个字节.
		ByteBuffer buf = ByteBuffer.allocate(1024);
		// 把数据放入缓冲区
		buf.put((byte)10);
		buf.put((byte)20);
		buf.put((byte)30);
		System.out.println("position:"+buf.position());
		System.out.println("limit:"+buf.limit());
		System.out.println("capacity:"+buf.capacity());
		
		System.out.println("----------------");
		
		// 缓冲区反转
		buf.flip();
		/*
		 * 这是干嘛的呢?
		 * 其实就是 limit = position; position = 0;
		 */
		System.out.println("position:"+buf.position());
		System.out.println("limit:"+buf.limit());
		System.out.println("capacity:"+buf.capacity());
		
		System.out.println("----------------");
		// 获取数据
		if(buf.hasRemaining()) // 判断缓冲区是否有数据
		{
			for(int i=0; i<buf.remaining();i++)
			{
				Byte b = buf.get(i);
				System.out.println(b);
			}
		}

	}

}

```

Buffer 常用方法

```java
int capacity(); //获取Buffer的容量
boolean hasRemaining(); // 判断position到limit之间是否还有元素可处理
int limit(); 
Buffer limit(int newLt); //获取具有新的limit的缓冲区对象.
Buffer mark(); // 设置mark
Buffer reset(); // 将position弄到mark的位置
int postion();
Buffer position(int newPs);
int remaining();
Buffer rewind(); //将position设置为0,清除mark
Buffer clear(); //清楚Buffer的内容.
```

## channel

```java
package NIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {

	public static void main(String[] args) throws Exception {
		//创建文件通道
		FileChannel fcIn = new FileInputStream("C:\\Users\\Administrator\\Desktop\\test.doc").getChannel();
		FileChannel fcOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test2.doc").getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		while(fcIn.read(buf)!=-1)
		{
			buf.flip();  //防止buffer没有被填满
			fcOut.write(buf);
			buf.clear();
		}
		fcIn.close();
		fcOut.close();
		System.out.println("done");

	}

}
```

