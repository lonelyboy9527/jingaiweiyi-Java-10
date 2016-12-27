package cc.openhome.class2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class CharUtil {
	public static void dump(Reader src, Writer dest) throws IOException {
		try (Reader input = src; Writer output = dest) {
			char[] data = new char[1024]; // 尝试每次从来源读取1024字节
			int length = 0;
			while ((length = input.read(data)) != -1) { // 读取数据
				output.write(data, 0, length); // 写出数据
			}
		}
	}
	// 使用打包器InputStreamReader、OutputStreamWriter，指定编码格式。
	public static void dumpWithIOReaderCharCode(InputStream src, OutputStream dest, String charset) throws IOException {
		// 先装饰，再调用
		dump(
				new InputStreamReader(src, charset), 
				new OutputStreamWriter(dest, charset)
			);
	}
}

