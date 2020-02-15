package demo.firstdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// This annotation allows entity manager to use this class and puts it in context.
@Entity
//Associates class with a table in the database
@Table(name = "users")
public class User{
    //says that this field is the primary key
    @Id
    //defines strategy for generating primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //denotess column  in the db which this field os associated with
    @Column(name = "age")
    private Integer age;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    public User(){

    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstname){
        this.firstName = firstname;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastname){
        this.lastName = lastname;
    }

    @Override
	public String toString() {
		return "Person{" + "id=" + id + ", age=" + age + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
				+ '\'' + '}';
	}
}