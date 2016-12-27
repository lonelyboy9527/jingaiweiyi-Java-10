package cc.openhome.class1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {
	/* 该方法接受InputStream 和OutputStream实例，代表数据来源和目的地
	 * 
	 * 还使用了尝试自动关闭语法 关闭 串流。
	 * 
	 * 每次从InputStream读取数据，都会先读取到data数组中(每次最多读取1024字节，循环中可以多次读取)。
	 * 
	 * InputStream的read()方法每次尝试读入data数组长度的数据，并返回实际读入的字节
	 * 只要不是-1，就表示读取到数据。
	 * 
	 * 可以使用OutputStream的write()方法，指定要写出的data数组，初始索引与数据长度。
	 *  */
	public static void dump(InputStream src, OutputStream dest) throws IOException {
		try (InputStream inputStream = src; OutputStream outputStream = dest) {
			byte[] data = new byte[1024];
			int length = -1;
			while ((length = inputStream.read(data)) != -1) {
				// 可能会写入多次...
				System.out.println("开始写入...");
				outputStream.write(data, 0, length);
			}
		}
	}
	
	// 使用缓冲区（打包器）
	public static void dumpWithBufferedIO(InputStream src, OutputStream dest) throws IOException {
//		try (InputStream inputStream = new BufferedInputStream(src); OutputStream outputStream = new BufferedOutputStream(dest)) {
//			byte[] data = new byte[1024];
//			int length = -1;
//			while ((length = inputStream.read(data)) != -1) {
//				outputStream.write(data, 0, length);
//			}
//		}
		// 改进，先装饰，再调用dump
		dump(
				new BufferedInputStream(src), 
				new BufferedOutputStream(dest)
		);
	}
}
