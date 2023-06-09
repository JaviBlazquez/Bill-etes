package POJOS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table (name = "users")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8236513013305065850L;
	
	@Id
	@GeneratedValue( generator = "users")
	@TableGenerator (name = "users" , table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "users" )
	private String email;
	private Integer id;
	@Lob
	private String password;
	@ManyToOne
	@JoinColumn (name = "role_id")
	private Role role;
	
	
	public User() {
		super(); 
	}
	public User(Integer id, String email, String password, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(password);
		result = prime * result + Objects.hash(email, id, role);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role);
	}

	
}