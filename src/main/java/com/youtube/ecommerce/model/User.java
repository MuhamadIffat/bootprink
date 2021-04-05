package com.youtube.ecommerce.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="users")
@JsonIgnoreProperties(
        value = {"createdAt"},
        allowGetters = true
)
public class User extends AuditModel{
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
        private String email;
	private String password;
        private String login_token,
	type,
	address,
	is_email_verified,
	mobile;

    public User() {
        
        
    }

    public User(String name, String email, String password, String login_token, String type, String address, String is_email_verified, String mobile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.login_token = login_token;
        this.type = type;
        this.address = address;
        this.is_email_verified = is_email_verified;
        this.mobile = mobile;
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
	public String getLogin_token() {
		return login_token;
	}
	public void setLogin_token(String login_token) {
		this.login_token = login_token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIs_email_verified() {
		return is_email_verified;
	}
	public void setIs_email_verified(String is_email_verified) {
		this.is_email_verified = is_email_verified;
	}
}