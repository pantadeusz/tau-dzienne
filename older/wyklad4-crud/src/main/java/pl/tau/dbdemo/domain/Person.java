package pl.tau.dbdemo.domain;

public class Person {

	private long id;

	private String name;
	private int yob;

	public Person() {
	}

	public Person(String name, int yob) {
		this.name = name;
		this.yob = yob;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}

	@Override
	public boolean equals(Object o) {
		Person other = (Person) o;
		boolean ret = other.getName().equals(this.getName()) &&
		(other.getId() == this.getId()) &&
		(other.getYob() == this.getYob());
		return ret;
	}

	@Override
	public String toString() {
		return "[" + id+ ", "
			 + name + ", " + yob + "]";
	}
}
