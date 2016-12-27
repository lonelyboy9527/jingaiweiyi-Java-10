package cc.openhome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.URL;
import java.util.Scanner;

import com.sun.corba.se.spi.activation.Server;
import com.sun.org.apache.xerces.internal.util.URI;

import cc.openhome.class1.IO;
import cc.openhome.class1.Member;
import cc.openhome.class1.Member2;

public class MyClass1 {
	// 串流设计的概念
	public static void exp1() {
		/* Java将输入输出抽象化为串流，数据有来源及目的地，衔接两者的是串流对象。
		 * 
		 * “数据好比水，串流好比水管，通过水管的衔接，水由一端到另一端。”
		 * 
		 * 在应用程序上：如果要将数据从来源取出，可以使用输入串流。
		 * 			如果要将数据写入目的地，可以使用输出串流。
		 * 
		 * 在Java中，输入串流代表的对象为 java.io.InputStream实例
		 * 			输入串流代表的对象为 java.io.outputStream实例
		 * 
		 * 无论数据源或者目的地为何地，只要设法取得InputStream或outputStream的实例，
		 * 接下来操作输入与输出的方式都是一致，无须理会来源或目的地真正的形式。
		 * 
		 * 例如：
		 * */
		System.out.println("用户目录:" + System.getProperty("user.dir"));
		
		File inpf = new File(System.getProperty("user.dir")  
                + "/src/cc/openhome/input.txt");  
		File outpf = new File(System.getProperty("user.dir")  
                + "/src/cc/openhome/output.txt");  
		try {
			IO.dump(
					new FileInputStream(inpf), 
					new FileOutputStream(outpf)
				);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		/* FileInputStream是InputStream的子类，用于衔接文档以读入数据。
		 * FileOutputStream是OutputStream的子类，用于衔接文档以写出数据。
		 * 
		 * 如果过要从HTTP服务器读取某个网页，并另存为文档，也可以使用dump()方法。
		 * 
		 * 例子：
		 * */
		try {
			URL url = new URL("https://www.baidu.com/index.php?tn=monline_6_dg");
			InputStream src = url.openStream();
			File outhttppf = new File(System.getProperty("user.dir")  
	                + "/src/cc/openhome/baidu.txt"); 
			FileOutputStream dest = new FileOutputStream(outhttppf);
			IO.dump(
				src, 
				dest
			);
		} catch (Exception e) {
			// TODO: handle exception
		}
		/* 虽然没有正式介绍到网络程序设计，不过java.net.URL的使用很简单，
		 * 只要指定URL实例会自动进行HTTP协议。
		 * 可以使用 InputStream()方法取得InputStream实例，
		 * 使用 OutputStream()方法取得OutputStream实例 来代表与网站链接的数据串流。
		 * */
		
		/* 无论来源或目的地实体形式为何，只要想办法取得InputStream 或OutputStream 
		 * 接下来都是调用 相关方法。
		 * 
		 * 例如：使用java.net.ServerSocket接受客户端联机的例子
		 * */
		/*
		ServerSocket server = null;
		Socket client = null;
		try {
			server = new ServerSocket(port);
			while (true) {
				client = server.accept();
				InputStream input = client.getInputStream();
				OutputStream output = client.getOutputStream();
				
				// 接下来就是操作 InputStream和OutputStream实例了
				// ...
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		*
		*/
		
		/* 如果将来学到Servlet，想将文档输出至浏览器，也会有类似操作：
		 * 
		 * response.setContentType("application/pdf");
		 * InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/jdbc.pdf");
		 * OutputStream out = response.getPutputStream();
		 * 
		 * ...
		 * 
		 * */
	}
	// 串流继承架构
	public static void exp2() {
		/* 在了解串流抽象化数据源与目的地的概念后，
		 * 
		 * 接下来要搞清楚Java中 InputStream、OutputStream的继承架构。
		 * 
		 * 
		 * InputStream(子类如下)：
		 * 	FileInputStream
		 * 	ByteArrayInputStream
		 *  (以下具有打包器作用：)
		 * 	FilterInputStream (子类如下): BufferedInputStream、DataInputStream
		 *  ObjectInputStream
		 *  
		 * OutputStream(子类如下):
		 * 	FileOutputStream
		 *  ByteArrayOutputStream
		 *  (以下具有打包器作用：)
		 *  FilterOutputStream (子类如下): BufferedOutputStream、DataOutputStream、PrintOutputStream
		 *  ObjectOutputStream
		 *  
		 *  了解了继承架构后，逐步说明相关类的使用方式。
		 * */
		
		/* <1>.标准输入/输出*/
		exp2_1();
		
		/* <2>.FileInputStream 与 FileOutputStream*/
//		exp2_2();
		
		/* <3>.ByteArrayInputStream与ByteArrayOutputStream*/
//		exp2_3();
	} 
	public static void exp2_3() {
		/* <3>.ByteArrayInputStream与ByteArrayOutputStream
		 * 
		 * ByteArrayInputStream是InputStream的子类，可以指定byte数组创建实例。
		 * 		一旦创建就可将byte数组当作数据源进行读取。
		 * ByteArrayOutputStream是InputStream的子类，可以指定byte数组创建实例。
		 * 		一旦创建就可将byte数组当作目的地进行写出数据。
		 * 
		 * ByteArrayInputStream 主要操作了 InputStream的read()抽象方法，使之可以从byte数组中读取数据。
		 * ByteArrayOutputStream 主要操作了 InputStream的write()抽象方法，使之可以写出数据至byte数组。
		 * */
	}
	
	public static void exp2_2() {
		/* <2>.FileInputStream 与 FileOutputStream
		 * 
		 * FileInputStream是 InputStream的子类，可以指定文件名创建实例。
		 * 		一旦创建文档就开启，接着就可用来读取数据。
		 * FileOutputStream是 OutputStream的子类，可以指定文件名创建实例。
		 * 		一旦创建文档就开启，接着就可以用来写出数据。
		 * 
		 * 不用时，都需要使用 close()关闭文档。
		 * 
		 * FileInputStream 主要操作了 InputStream的read()抽象方法，使之可以从文档中读取数据。
		 * FileOutputStream 主要操作了 InputStream的write()抽象方法，使之可以写出数据至文档。
		 * 
		 * FileInputStream、FileOutputStream在读取、写入文档时，是以字节为单位，通常会使用一些高阶类加以打包。进行一些高阶操作。
		 * 
		 * */
	}
	public static void exp2_1() {
		/* <1>.标准输入/输出 
		 * 
		 * 还记得System.in 和System.out吗？它们分别是 InputStream和OutputStream实例，
		 * 代表标准输入 和 标准输出 （通常对应至文本模式中的输入与输出）
		 * 
		 * 可以使用 System的 setIn()方法指定 InputStream实例，重新指定标准输入来源。
		 * 
		 * 将其他输入换成 标准输入：
		 * 例子： 通过setIn指定System.in 为 FileInputStream，可以读取指定文档并显示在文本模式：
		 * */ 
		File inputfp = new File(System.getProperty("user.dir") + "/src/cc/openhome/input.txt");
		try {
			InputStream inputStream = new FileInputStream(inputfp);
			// 更改输入为文件文本输入
			System.setIn(inputStream);
			try (Scanner scanner = new Scanner(System.in)) {
				while (scanner.hasNextLine()) {
					System.out.println(scanner.nextLine());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/* 将其他输入换成 标准输入：
		 * 例子：将上一节中读取的 网页从 标准输出（控制台显示）
		 * */
		try {
			URL url = new URL("https://www.baidu.com/index.php?tn=monline_6_dg");
			InputStream src = url.openStream();
			IO.dump(src, System.out);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/* 除了 System.in 和 System.out之外，还有个 System.err
		 * 
		 * 为PrintSream实例，称为标准错误输出串流，它是用来立即显示错误信息。
		 * 例如，在文本模式下，System.out输出的信息可以使用> 或者 >>重新导向至文档（输出到外部文件），
		 * 		但是System.err输出的信息一定显示在文本模式中（控制台显示），无法重新导向。
		 * 
		 * 也可以使用System.setErr()指定PrintSream，重新指定标准错误输出串流。
		 * 
		 * */
	}
	
	// 串流处理装饰器
	public static void exp3() {
		/* InputStream 、OutputStream提供串流的基本操作
		 * 
		 * 如果想要为输入、输出的数据做加工处理，则可以使用打包器类。
		 * （例如，前面提过的Scanner类就是作为打包器，其接受InputStream实例，
		 * 	你操作Scanner打包器相关方法，Scanner会实际操作打包的InputStream取得数据，并转换为你想要的数据类型。）
		 * 
		 * InputStream 、OutputStream的一些子类也具有打包器的作用，这些子类创建时，可以接受InputStream 、OutputStream实例。
		 * 
		 * 
		 * 常用的打包器：
		 * 
		 *  具有缓冲区作用的: BufferedInputStream、BufferedOutputStream
		 *  具备数据转换处理作用的：DataInputStream、DataOutputStream
		 *  具备对象串行化(序列化)能力的 ObjectInputStream、ObjectOutputStream
		 * 
		 *  由于这些类本身并没有改变InputStream 、OutputStream的行为，
		 *  只不过在InputStream 取得数据之后，再做一些处理。
		 *  或者是输出时做一些加工处理，再交由OutputStream真正的输出。
		 *  因此又称它们为 “装饰器”。
		 *    
		 *  下面介绍几个常用的串流装饰器类。
		 * */
		
		/* <1>.BufferedInputStream与 BufferedOutputStream*/
		exp3_1();
		
		/* <2>.DataInputStream与 DataOutputStream
		 * 
		 * 通过储存对象的成员变量，和读取成员变量
		 * */
//		exp3_2();
		
		/* <3>.ObjectInputStream与 ObjectOutputStream
		 * 
		 * 将对象直接储存和 读取
		 * */
		exp3_3();
	}
	public static void exp3_3() {
		/* <3>.ObjectInputStream与 ObjectOutputStream
		 * 
		 * 前面的例子是取得 Member的number，name，age数据进行储存读回时也是先取得number，name，age数据再创建 Member实例。
		 * 
		 * 实际上，可以将内存中的对象整个储存下来，之后再读入还原为对象。
		 * 可以使用ObjectInputStream与 ObjectOutputStream装饰 InputStream 与OutputStream来完成操作。
		 * */
		Member2[] members = { new Member2("A1234", "Justin", 90),
				new Member2("A5678", "Monica", 95),
				new Member2("A9876", "Irene", 88)
		};
		for (Member2 member : members) {
			// 会保存到工程根目录
			member.saveWithObject();
		}
		System.out.println("开始读取...");
		
		System.out.println(Member2.loadWithObject("A1234"));
		System.out.println(Member2.loadWithObject("A5678"));
		System.out.println(Member2.loadWithObject("A9876"));
		
		/* 上面的写法会报异常： java.io.NotSerializableException
		 * 
		 * 将对象转换为字节流保存起来，并在以后还原这个对象，这种机制叫做对象序列化。将一个对象保存到永久存储设备上称为持久化。
		 * 一个对象要想能够实现序列化，必须实现java.io.Serializable接口。该接口中没有定义任何方法，是一个标识性接口（Marker Interface），当一个类实现了该接口，就表示这个类的对象是可以序列化的。
		 * 序列化（serialization）是把一个对象的状态写入一个字节流的过程。当你想要把你的程序状态存到一个固定的存储区域，例如文件时，它是很管用的。
		 * 从程序到外面叫序列化，从外面读回来叫反序列化。
		 * 
		 * writeObject它被称为序列化一个对象。
		 * 所有这些方法在出错情况下引发IOException异常。
		 * 
		 * 
		 * 解决方法：
		 * 类需要实现Serializable接口，这个接口没有方法。
		 * */
		
		/* 注意：
		 * 成员变量不希望被写出，
		 * 加上transient关键字后将不被序列化
		 * */
		
	}
	public static void exp3_2() {
		/* <2>.DataInputStream与 DataOutputStream
		 * 
		 * DataInputStream与 DataOutputStream提供读取、写入Java基本类型数据的方法，
		 * 像是读写 int、double、boolean等的方法。
		 * 这些方法会自动在指定的列席与字节间转换，不用你亲自做字节与类型转换的动作。
		 * 
		 * 例子：下面的Member类可以调用sava()储存Member实例本身的数据，
		 * 		文件名为Member的会员号码，调用Member.laod()指定会员号码，则可以读取文档中的会员数据，封装为Member实例并返回：
		 * */
		
		Member[] members = { new Member("B1234", "Justin", 90),
				new Member("B5678", "Monica", 95),
				new Member("B9876", "Irene", 88)
		};
		for (Member member : members) {
			// 会保存到工程根目录
			member.save();
		}
		System.out.println(Member.load("B1234"));
		System.out.println(Member.load("B5678"));
		System.out.println(Member.load("B9876"));
		
	}
	public static void exp3_1() {
		/* <1>.BufferedInputStream与 BufferedOutputStream
		 * 
		 * 在前面的 IO.dump()方法中，调用 read()方法，都是直接向来源要求数据，
		 * 	每次调用write()，都会直接将数据写到目的地，这并不是个有效率的方式。
		 * 
		 * 如果InputStream第一次read()时可以尽量读取足够的数据至内存缓冲区，
		 * 后续调用read()时，先看看缓冲区是不是还有数据，有就从缓冲区读取，没有再从来源读取至缓冲区，
		 * 这样减少从来源直接读取数据的次数，对读取效率将会有帮助（内存的访问比硬盘块）
		 * 
		 * 同理：如果OutputStream每次write()时可将数据写入内存中的缓冲区，缓冲区满了再将缓冲区
		 * 的数据写入目的地，这样减少对目的地的写入次数，对写入效率将会有帮助。
		 * 
		 * BufferedInputStream与 BufferedOutputStream提供的就是缓冲区功能。
		 * 
		 * 改写前面的IO.dump()方法为 dumpWithBufferedIO:
		 * */
		System.out.println("用户目录:" + System.getProperty("user.dir"));
		
		File inpf = new File(System.getProperty("user.dir")  
                + "/src/cc/openhome/input.txt");  
		File outpf = new File(System.getProperty("user.dir")  
                + "/src/cc/openhome/outputWithBufferedIO.txt");  
		try {
			IO.dumpWithBufferedIO(
					new FileInputStream(inpf), 
					new FileOutputStream(outpf)
				);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
