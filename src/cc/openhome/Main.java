package cc.openhome;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(args);
//		exp1();
		exp2();
	}
	/* ********************10.2 字符串处理类******************** */
	public static void exp2() {
		/* InputStream、OutputStream是用来读入与写出数据的
		 * 
		 * 若实际上处理的是字符数据，使用InputStream、OutputStream就得对照编码表，在字符与字节之间转换。
		 * 所幸Java SE API已提供相关输入输出字符处理类，让我们不用亲自处理字节与字符编码转换的枯燥工作。
		 * */
		
		MyClass2.exp1(); // 10.2.1 Reader 与 Writer继承架构
		MyClass2.exp2(); // 10.2.2 字符处理装饰器
	}
	/* ********************10.1 InputStream与OutputStream******************** */
	public static void exp1() {
		/* 想活用输入输出API，一定要先了解Java中如何以串流（Stream）抽象化输入输出概念
		 * 以及 InputStream、OutputStream继承架构。
		 * 
		 * 如此一来，无论：
		 * 标准输入输出、
		 * 文档输入输出、
		 * 网络输入输出、
		 * 数据库输入输出等都可用一致的操作处理。
		 * */
		MyClass1.exp1(); // 10.1.1 串流设计的概念
		MyClass1.exp2(); // 10.1.2 串流继承架构
		MyClass1.exp3(); // 10.1.3 串流处理装饰器
	}

}
