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
				croupierMenu(u);
				break;
			}
			case "administration":{
				administrationMenu(u);
				break;
			}
			case "security":{
				securityMenu(u);
				break;
			}
			default:{
				clientMenu(u);
			}
		}
	}
	private static void registerClient() throws IOException {
		JDBCClient jdbcclient= new JDBCClient(jdbcManager);
		String email, name, surname;
		int phone;
		Integer clientId= (Integer) userManager.getRole("client").getUsers().size();
		do {
			System.out.println("Introduce the client's email");
			email = readers.readLine();
		}while(!checkEmail(email));
		System.out.println("Introduce the client's password");
		byte[] password = new byte[10];
		password[0]=Byte.parseByte( readers.readLine());
		Role role= userManager.getRole("client");
		User user= new User((Integer) userManager.getRole("client").getUsers().size(),email,password,role);
		userManager.newUser(user);
		role.addUSer(user);
		System.out.println("Introduce the client's name");
		name = readers.readLine();
		System.out.println("Introduce the client's surname");
		surname = readers.readLine();
		System.out.println("Introduce the client's phone");
		phone = Integer.parseInt(readers.readLine());
		jdbcclient.addClient(new Client(clientId, phone, 0, name, surname, false));
	}
	private static void registerWorker() throws IOException {
		String email, role_string, name, surname, address;
		int salary;
		byte[] password = new byte[10];
		JDBCWorker jdbcWorker= new JDBCWorker(jdbcManager);
		Occupation occupation;
		do {
			System.out.println("Introduce the worker's email");
			email = readers.readLine();
		}while(!checkEmail(email));
		System.out.println("Introduce the worker's password");
		password[0]=Byte.parseByte( readers.readLine());
		System.out.println("Introduce the worker's role");
		role_string = readers.readLine();
		Role role= userManager.getRole(role_string);
		Integer id= (Integer) userManager.getRole("administration").getUsers().size()+ (Integer) userManager.getRole("croupier").getUsers().size()+ (Integer) userManager.getRole("security").getUsers().size();
		User user= new User(id,email,password,role);
		userManager.newUser(user);
		role.addUSer(user);
		System.out.println("Introduce the worker's name");
		name = readers.readLine();
		System.out.println("Introduce the worker's surname");
		surname = readers.readLine();
		System.out.println("Introduce the worker's salary");
		salary = Integer.parseInt(readers.readLine());
		System.out.println("Introduce the worker's address");
		address = readers.readLine();
		switch(role.getName()) {
			case "Security":{
				occupation= Occupation.SECURITY;
			}
			case "Croupier":{
				occupation= Occupation.CROUPIER;
			}
			default:{
				occupation= Occupation.ADMINISTRATION;
			}
		}
		jdbcWorker.addWorker(new Worker(id, 0, name, surname, salary, address, occupation));
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
	private static void croupierMenu(User u) {

		
	}
	private static void administrationMenu(User u) throws NumberFormatException, IOException {
		boolean bucle1=true;
		boolean bucle2=false;
		boolean bucle3=false;
		while(bucle1) {
			System.out.println("Choose an option");
			System.out.println("0. Return to login page");
			System.out.println("1. Check database info");
			System.out.println("2. Modify database info");
			int choice = Integer.parseInt(readers.readLine());
			switch(choice)
			{
			case 0: 
				bucle1=false;
				break;
			case 1:
				bucle2=true;
				while(bucle2) {
					System.out.println("Choose an option");
					System.out.println("0. Return to previus page");
					System.out.println("1. Salary");
					System.out.println("2. Client's state");
					System.out.println("3. Money won");
					System.out.println("4. Bancary account");
					System.out.println("5. Worker list");
					choice= Integer.parseInt(readers.readLine());
					switch(choice) {
						case 0: 
							bucle2=false;
							break;
						case 1:
							JDBCWorker jdbcWorker= new JDBCWorker(jdbcManager);
							List<Worker> workers= jdbcWorker.getListOfWorkers();
							Iterator<Worker> itW= workers.iterator();
							while(itW.hasNext()) {
								Worker w=itW.next();
								if(w.getWorkerId()==u.getId()) {
									System.out.println(w.getSalary());
								}
							}
							break;
						case 2:
							JDBCClient jdbcClient= new JDBCClient(jdbcManager);
							String name, surname;
							System.out.println("Introduce the client's name");
							name = readers.readLine();
							System.out.println("Introduce the client's surname");
							surname = readers.readLine();
							List<Client> clients= jdbcClient.getClientByQuery("SELECT condition FROM client WHERE name="+name+" AND surname="+ surname);
							Iterator<Client> itC= clients.iterator();
							while(itC.hasNext()) {
								System.out.println(name+ " " + surname+ ": "+itC.next().isCondition());
							}
							break;
						case 3:
							System.out.println("Choose an option");
							System.out.println("0. Machine");
							System.out.println("1. Table");
							choice= Integer.parseInt(readers.readLine());
							switch(choice) {
								case 0:
									JDBCMachine jdbcMachine= new JDBCMachine(jdbcManager);
									List<Machine> machines= jdbcMachine.getListofMachine();
									Iterator<Machine> itM= machines.iterator();
									while(itM.hasNext()) {
										Machine machine= itM.next();
										System.out.println(machine.getMachineId()+ ":" +machine.getMoneyWon());
									}
									break;
								case 1:
									JDBCTable jdbcTable= new JDBCTable(jdbcManager);
									List<Table> tables= jdbcTable.getListofTable();
									Iterator<Table> itT= tables.iterator();
									while(itT.hasNext()) {
										Table table= itT.next();
										System.out.println(table.getTableId()+ ":" +table.getMoneyWon());
									}
									break;
							}
							break;
						case 4:
							JDBCBancaryAccount jdbcBancaryAccount= new JDBCBancaryAccount(jdbcManager);
							System.out.println(jdbcBancaryAccount.getBancaryAccount());
							break;
						case 5:
							//Preguntar por inicializacion de variables
							jdbcWorker= new JDBCWorker(jdbcManager);
							workers= jdbcWorker.getListOfWorkers();
							itW= workers.iterator();
							while(itW.hasNext()) {
								System.out.println(itW.next());
							}
							break;
					}
				}
				break;
			case 2:
				break;
			default:
				break;
			}
		}
		
	}
	private static void securityMenu(User u) throws NumberFormatException, IOException {
		boolean bucle1 = true;
		JDBCClient cliente = new JDBCClient(jdbcManager);
		while(bucle1) {
			System.out.println("Choose an option");
			System.out.println("0. Return to login page");
			System.out.println("1. Access a client info");
			//System.out.println("2. Ban a client from the database");
			int choice = Integer.parseInt(readers.readLine());
			switch(choice)
			{
			case 0: 
				bucle1=false;
				break;
			case 1:
				JDBCClient jdbcClient= new JDBCClient(jdbcManager);
				String email;
				System.out.println("Introduce the client's email");
				email = readers.readLine();
				Client sus = userManager.getUserByMail(email); //Que la función te diga el nombre apellido y si es ludo
															    //
				System.out.println("Choose an option");
				System.out.println("1. Delete Client");
				System.out.println("2. Return to menu");
				int option = Integer.parseInt(readers.readLine());
					switch(option) {
					case 1:
						cliente.removeClient(sus);
						break;
					case 2:
						bucle1 = false;
						break;
						
					
					}
				
				break;
			
				
				
			}
			
			
			
		}
		
	
		
	}
	private static void clientMenu(User u) throws NumberFormatException, IOException {
		boolean bucle1=true;
		boolean bucle2=false;
		while(bucle1){
			System.out.println("Choose an option");
			System.out.println("0. Return to menu");
			System.out.println("1. Administrate account");
			System.out.println("2. View game record");
			int choice = Integer.parseInt(readers.readLine());
			switch(choice) {
			case 0:
				bucle1=false;
				break;
			case 1:
				bucle2=true;
				while(bucle2) {
					JDBCClient jdbcClient= new JDBCClient(jdbcManager);
					List<Client> client= jdbcClient.getListofClient();
					Iterator<Client> itC= client.iterator();
					while(itC.hasNext()) {
						Client c=itC.next();
						if(g.getClientId()==u.getId()) {
							System.out.println("Current money: "+c.getMoney());
					System.out.println("Choose an option");
					System.out.println("0. Return");
					System.out.println("1. Deposit money");
					System.out.println("2. Extract money");
					int choice2 = Integer.parseInt(readers.readLine());
					switch(choice2) {
					case 1:
						
						
						
					}
					}
			case 2:
				bucle2=true;
				while(bucle2) {
					System.out.println("Choose an option");
					System.out.println("0. Return");
					System.out.println("1. Game record");
					System.out.println("2. Match record");
					int choice2 = Integer.parseInt(readers.readLine());
					switch(choice2) {
					case 1:
						JDBCGame jdbcGame= new JDBCGame(jdbcManager);
						List<Game> game= jdbcGame.getListOfGames();
						Iterator<Game> itG= game.iterator();
						while(itG.hasNext()) {
							Game g=itG.next();
							if(g.getClientId()==u.getId()) {
								System.out.println("Machine: "+g.getMachineId()+" Date: "+g.getTimeStamp());
							}
						}
						break;

					case 2:
						JDBCMatch jdbcMatch= new JDBCMatch(jdbcManager);
						List<Match> match= jdbcMatch.getListOfMatches();
						Iterator<Match> itM= match.iterator();
						while(itM.hasNext()) {
							Match m=itM.next();
							if(m.getClientId()==u.getId()) {
								System.out.println("Table: "+m.getTableId()+" Date: "+m.getTimeStamp());
							}
						}
						break;
					}
				
			}
		}
		
		
	}
		
	public static void main(String[] args) {
		try {
			do {
				System.out.println("Choose an option");
				System.out.println("0. exit");
				System.out.println("1. Login");
				//System.out.println("2. Register as a new client");
				

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
					//registerClient();
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