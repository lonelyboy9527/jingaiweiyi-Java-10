package cc.openhome.class1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//这个类需要实现Serializable接口
// 否则在序列化时会抛出java.io.NotSerializableException
public class Member2 implements Serializable {
	private String number;
	private String name;
	private transient int age;
	
	public Member2(String number, String name, int age) {
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
	
	// ***************使用ObjectInputStream与 ObjectOutputStream装饰***************
		public void saveWithObject() {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(number))) {
				objectOutputStream.writeObject(this);
			} catch (IOException e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
		}
		public static Member2 loadWithObject(String number) {
			Member2 member = null;
			try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(number))) {
				member = (Member2) objectInputStream.readObject();
			} catch (IOException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			return member;
		}
}
