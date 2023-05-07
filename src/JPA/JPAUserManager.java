package JPA;


import java.security.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Interfaces.UserManager;
import POJOS.Client;
import POJOS.Role;
import POJOS.User;


public class JPAUserManager implements UserManager{

private EntityManager em;
	
	
	public JPAUserManager() {
		this.connect();
	}
	
	public void connect() {
		// TODO Auto-generated method stub
		
		em = Persistence.createEntityManagerFactory("casino.db").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		if( this.getRoles().isEmpty()) {
			Role client = new Role("client");
			Role croupier = new Role("croupier");
			Role administration = new Role("administration");
			Role security = new Role("security");
			this.newRole(client);
			this.newRole(croupier);
			this.newRole(administration);
			this.newRole(security);
		}
		
	}
	
	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		em.close();
	}

	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void newRole(Role r) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String name) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name=?", Role.class); 
		q.setParameter(1, name);
		Role role= (Role) q.getSingleResult();
		return role;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		
		return roles;
	}

	@Override
	public User checkPassword(String email, String passwd) {
		// TODO Auto-generated method stub
		User u = null;
		
		Query q = em.createNativeQuery("Select * from users where email =? AND password = ?", User.class);
		q.setParameter(1, email);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			q.setParameter(2,digest);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		try {
			u = (User) q.getSingleResult();
			
		}catch(NoResultException e) {}
		
		return u;
	}

	@Override
	public User getUserByEmail(String email) {
		
		User u = null;
		Query q = em.createNativeQuery("Select * from users where email =?", User.class);
		q.setParameter(1, email);
		try {
			u = (User) q.getSingleResult();
			
		}catch(NoResultException e) {}

		return u;
	}

	@Override
	public void removeUser(User u) {
			em.remove(u);
	}
	

}
