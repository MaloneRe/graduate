package mongodbtest;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class People implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = 274438633858062441L;
	private String id;
	private String name;
	private String sex;
	private Integer age;
	
	public People(String name) {
		super();
		this.name = name;
	}
	public People(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	public People(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public People(String name, String sex, Integer age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public People() {
		super();
	}
	public People(String id, String name, String sex, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
