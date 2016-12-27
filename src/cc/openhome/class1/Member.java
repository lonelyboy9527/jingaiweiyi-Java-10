package cc.openhome.class1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Member {
	private String number;
	private String name;
	private int age;
	
	public Member(String number, String name, int age) {
		// TODO Auto-generated constructor stub
		this.age = age;
		this.name = name;
		this.number = number;
	}
	// Getter、Setter方法
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}
	public String getNumber() {
		return number;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("(%s, %s, %d)", number, name, age);
	}
	
	// ***************使用DataInputStream与 DataOutputStream装饰***************
	
	// 保存一个对象(输出到文件)
	public void save() {
		/* 建立DataOutputStream打包 FileOutputStream*/
		try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(number))) {
			// 根据不同的类型调用writexxx()方法
			outputStream.writeUTF(number);
			outputStream.writeUTF(name);
			outputStream.writeInt(age);
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	// 根据number读取一个对象(从文件获取)
	public static Member load(String number) {
		Member member = null;
		/* 建立DataInputStream打包 FileInputStream*/
		try (DataInputStream inputStream = new DataInputStream(new FileInputStream(number))) {
			// 根据不同的类型调用 readxxx()方法
			member = new Member(
					inputStream.readUTF(), 
					inputStream.readUTF(), 
					inputStream.readInt()
					);
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return member;
	}
}
