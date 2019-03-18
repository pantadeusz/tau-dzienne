package pl.tau.dbdemo.domain;

public class Person {

	private Long id;

	private String name;
	private Integer yob;

	public Person() {
	}

	public Person(String name, Integer yob) {
		this.id = null;
		this.name = name;
		this.yob = yob;
	}

	public Long getId() {
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

	public Integer getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}

	@Override
	public boolean equals(Object o) {
		Person other = (Person) o;
		boolean ret = other.getName().equals(this.getName()) &&
				((other.getId() == this.getId()) || (other.getId().longValue() == this.getId().longValue())) &&
				((other.getYob() == this.getYob()) || (other.getYob().intValue() == this.getYob().intValue()));
		return ret;
	}

	@Override
	public String toString() {
		return "[" + id+ ", "
			 + name + ", " + yob + "]";
	}
}
