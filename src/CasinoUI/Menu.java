package CasinoUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.*;
import JPA.JPAUserManager;
import POJOS.*;
import jdbc.*;
public class Menu {
	
	private static BufferedReader readers = new BufferedReader (new InputStreamReader(System.in));
	private static JDBCManager jdbcManager= new JDBCManager();
	private static JPAUserManager userManager= new JPAUserManager();
	private static void login() throws IOException {
		System.out.println("Introduce your email");
		String email = readers.readLine();
		System.out.println("Introduce your password");
		String password = readers.readLine();
		User u= userManager.checkPassword(email, password);
		switch(u.getRole().getName()) {
			case "croupier":{
				croupierMenu();
				break;
			}
			case "administration":{
				administrationMenu();
				break;
			}
			case "security":{
				securityMenu();
				break;
			}
			default:{
				clientMenu();
			}
		}
	}
	private static void registerClient() throws IOException {
		String email ;
		do {
			System.out.println("Introduce your email");
			email = readers.readLine();
		}while(!checkEmail(email));
		System.out.println("Introduce your password");
		byte[] password = new byte[10];
		password[0]=Byte.parseByte( readers.readLine());
		Role role= userManager.getRole("client");
		User user= new User((Integer) 0,email,password,role);
		userManager.newUser(user);
		role.addUSer(user);
	}
	private static boolean checkEmail(String email) {
		List<Role> roles= userManager.getRoles();
		Iterator<Role> itR= roles.iterator();
		List<User> users= itR.next().getUsers();
		while(itR.hasNext()) {
			users.addAll(itR.next().getUsers());
		}
		Iterator<User> itU= users.iterator();
		while(itU.hasNext()) {
			if(email.equals(itU.next().getEmail())) {
				return false;
			}
		}
		return true;
	}
	private static void croupierMenu() {
		
	}
	private static void administrationMenu() {
		
	}
	private static void securityMenu() {
	
	}
	private static void clientMenu() {
		
	}
	public static void main(String[] args) {
		try {
			do {
				System.out.println("Choose an option");
				System.out.println("0. exit");
				System.out.println("1. Login");
				System.out.println("2. Register as a new client");
				

				int choice = Integer.parseInt(readers.readLine());
				switch(choice)
				{
				case 0: 
					jdbcManager.disconnect();
					userManager.disconnect();
					System.exit(0);
				case 1:
					login();
					break;
				case 2:
					registerClient();
					break;
				default:
					break;
				}
			}while(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	
	
}