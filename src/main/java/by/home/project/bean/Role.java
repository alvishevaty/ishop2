package by.home.project.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role implements Serializable {

	private static final long serialVersionUID = -898829730505425512L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="type")
	private String roleType;
	
	@OneToMany(mappedBy="role",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<RegistrationUser> registrationUser;

	public Role() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleType() {
		return roleType;
	}

	public List<RegistrationUser> getRegistrationUser() {
		return registrationUser;
	}

	public void setRegistrationUser(List<RegistrationUser> registrationUser) {
		this.registrationUser = registrationUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((registrationUser == null) ? 0 : registrationUser.hashCode());
		result = prime * result + ((roleType == null) ? 0 : roleType.hashCode());
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
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (registrationUser == null) {
			if (other.registrationUser != null)
				return false;
		} else if (!registrationUser.equals(other.registrationUser))
			return false;
		if (roleType == null) {
			if (other.roleType != null)
				return false;
		} else if (!roleType.equals(other.roleType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleType=" + roleType + ", registrationUser=" + registrationUser + "]";
	}


}
