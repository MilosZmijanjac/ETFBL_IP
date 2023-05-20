package model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.Instant;

import model.dao.UserDao;
import model.enumeration.UserStatus;
import model.enumeration.UserType;

public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String avatarPath;
    private Instant created;
    private UserStatus status;
    private UserType type;
    private String address;
    private String city;
	private String country;
    
    
    
    public UserBean() {
    	super();
    }
        
	public UserBean(Long id, String firstName, String lastName, String username, String email, String password,
			String phone, String avatarPath, Instant created, UserStatus status, UserType type, String address,
			String city, String country) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.avatarPath = avatarPath;
		this.created = created;
		this.status = status;
		this.type = type;
		this.address = address;
		this.city = city;
		this.country = country;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatarPath() {
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	public Instant getCreated() {
		return created;
	}
	public void setCreated(Instant created) {
		this.created = created;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean login(String u,String p) throws SQLException {
		 return UserDao.loginUser(u, p);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
