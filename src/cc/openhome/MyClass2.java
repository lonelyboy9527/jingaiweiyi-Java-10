package cc.openhome;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringWriter;

import cc.openhome.class2.CharUtil;

public class MyClass2 {
	// Reader 与 Writer继承架构
	public static void exp1() {
		/* 针对字符数据的读取， Java SE提供了 java.io.Reader类，其抽象化了字符数据读入的来源。
		 * 
		 * 针对字符数据的写入，则提供了java.io.Writer类，其抽象了数据写出的目的地。
		 * 
		 * 举个例子：
		 * 
		 * 如果想从来源读入字符数据，或将字符数据写至目的地，都可以使用以下的 CharUtil.dump()方法
		 * */
		try {
			CharUtil.dump(null, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/* 同样的，了解Reader、 writer继承架构会有利于API的灵活运用。
		 * 
		 * 继承架构：
		 * 
		 * Reader（子类如下：）
		 * 
		 * StringReader
		 * CharArrayReader
		 * (以下具有打包器作用：)
		 * InputStreamReader (子类如下：) FileReader（打包器）
		 * BufferedReader（打包器）
		 * 
		 * Writer(子类如下：)
		 * 
		 * StringWriter
		 * CharArrayWriter
		 * (以下具有打包器作用：)
		 * PrintWriter
		 * OutputStreamWriter (子类如下：)FileWriter（打包器）
		 * BufferedWriter（打包器）
		 * 
		 * 
		 * 从上面可以看出，
		 * 
		 * FileReader是一种 Reader，主要用于读取文档并将读到的数据转换为字符：
		 * 	
		 * StringWriter是一种 Writer，可以将字符数据写至 StringWriter，最后
		 * 使用toString()方法取得字符串，代表所有写入的字符数据。
		 * 
		 * 例子：使用CharUtil.dump()读入文档、转为字符串并显示在文本模式中。
		 * */
		try {
			FileReader fileReader = new FileReader(new File(System.getProperty("user.dir") + "/src/cc/openhome/input.txt"));
			StringWriter writer = new StringWriter();
			CharUtil.dump(fileReader, writer);
			System.out.println(writer.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		/* FileReader、FileWriter可以对文档做读取与写入，读取或写入时会默认使用操作系统默认编码来做字符转换。*/
		
		/* 介绍其他几个子类：
		 * 
		 *  StringReader可以将字符串打包，当作读取来源。
		 *  StringWriter则可以作为写入目的地。最后用toString()方法取得所有写入对字符组成对字符串。
		 *  
		 *  CharArrayReader、CharArrayWriter则类似，将char数组当作读取来源以及写入目的地。
		 * */
		
	}
	
	// 字符处理装饰器
	public static void exp2() {
		System.out.println("------> 字符处理装饰器:");
		/* 如同InputStream、OutputStream有一些装饰器类，可以对InputStream、OutputStream打包器增加额外功能
		 * 
		 * Reader、 writer也有一些装饰器类可供使用；
		 * */
		
		/* <1>.InputStreamReader与 OutputStreamWriter*/
//		exp2_1();
		
		/* <2>.BufferedReader与 BufferedWriter*/
		exp2_2();
		
		/* <3>.PrintWriter与 PrintStream使用极为相似
		 * 
		 * 不过除了可对 OutStream打包外，PrintWriter还可以对Writer进行打包
		 * 提供 print()、println()、format()等方法。
		 * */
	}
	
	public static void exp2_2() {
		/* <2>.BufferedReader与 BufferedWriter
		 * 
		 * BufferedReader与 BufferedWriter可对 Reader、Writer提供缓冲区作用，
		 * 在处理字符输入/输出时，对效率也会有所帮助。
		 * 
		 * InputStreamReader将System.in读入对字节数据做编码转换，
		 * 而BufferedReader将编码转换后对数据做缓冲处理，以增加读取效率。
		 * 
		 * */
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String name = bufferedReader.readLine(); // 可读取一行数据（换行为依据，不包括换行符）
			System.out.printf("Hello, %s!", name);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/* System.in是InputStream实例，可以指定给 InputStreamReader创建之用，
		 * 
		 * InputStreamReader是一种Reader，所以可指定给 BufferedReader创建使用。
		 * */
	}
	
	public static void exp2_1() {
		System.out.println("------> InputStreamReader与 OutputStreamWriter:");
		/*<1>.InputStreamReader与 OutputStreamWriter
		 * 
		 * 如果串流处理的字节数据，实际上代表某些字符的编码数据，
		 * 而你想要将这些字节数据转换为对应的编码字符，可以使用
		 * InputStreamReader与 OutputStreamWriter对串流数据打包。
		 * 
		 * 在建立InputStreamReader与 OutputStreamWriter时，可以指定编码（否则取默认编码来做字符转换）
		 * 
		 * 例子：
		 * 采用默认编码，读取输出
		 * */
		try {
			CharUtil.dumpWithIOReaderCharCode(
					new FileInputStream(new File(System.getProperty("user.dir") + "/src/cc/openhome/input.txt")), 
					new FileOutputStream(new File(System.getProperty("user.dir") + "/src/cc/openhome/outputdumpWithIOReaderCharCode.txt")), 
					System.getProperty("file.encoding")
					);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/* 采用 UTF-8
		 * */
		try {
			CharUtil.dumpWithIOReaderCharCode(
					new FileInputStream(new File(System.getProperty("user.dir") + "/src/cc/openhome/input.txt")), 
					new FileOutputStream(new File(System.getProperty("user.dir") + "/src/cc/openhome/outputdumpWithIOReaderCharCode2.txt")), 
					"UTF-8"
					);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
