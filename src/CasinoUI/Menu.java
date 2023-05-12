package CasinoUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;

import Exceptions.MoneyException;

import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.*;
import JPA.JPAUserManager;
import POJOS.*;
import jdbc.*;
public class Menu {
	private static final int casinoId= 0;
	private static final int accountId= 0;
	private static Casino casino= new Casino(casinoId,accountId);
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
				try {
					clientMenu(u);
				}catch( MoneyException ex) {
					System.out.println(ex);
				}
				
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
		String password;
		password= readers.readLine();
		Role role= userManager.getRole("client");
		User user= new User(clientId,email,password,role);
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
		String password;
		JDBCWorker jdbcWorker= new JDBCWorker(jdbcManager);
		Occupation occupation;
		do {
			System.out.println("Introduce the worker's email");
			email = readers.readLine();
		}while(!checkEmail(email));
		System.out.println("Introduce the worker's password");
		password=readers.readLine();
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
		jdbcWorker.addWorker(new Worker(id, casino.getCasinoId(), name, surname, salary, address, occupation));
		
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
	private static void removeUser(User u) {
		switch(u.getRole().getName()) {
			case "client":
				JDBCClient jdbcClient= new JDBCClient(jdbcManager);
				List<Client> clients= jdbcClient.getListofClient();
				Iterator<Client> itC= clients.iterator();
				while(itC.hasNext()) {
					Client client= itC.next();
					if(u.getId()<client.getClientId()) {
						client.setClientId(client.getClientId()-1);
						jdbcClient.updateClient(client, client.getClientId()+1);
					}
					if(u.getId()==client.getClientId()) {
						jdbcClient.removeClient(client);
					}
				}
				List<User> usersC=u.getRole().getUsers();
				Iterator<User> ItUC= usersC.iterator();
				while(ItUC.hasNext()) {
					User userC= ItUC.next();
					if(u.getId()<userC.getId()) {
						userC.setId(userC.getId()-1);
					}
				}
				u.getRole().removeUser(u);
				userManager.removeUser(u);
				break;
			default:
				JDBCWorker jdbcWorker= new JDBCWorker(jdbcManager);
				List<Worker> workers= jdbcWorker.getListOfWorkers();
				Iterator<Worker> itW= workers.iterator();
				while(itW.hasNext()) {
					Worker worker= itW.next();
					if(u.getId()<worker.getWorkerId()) {
						worker.setWorkerId(worker.getWorkerId()-1);
						jdbcWorker.updateWorker(worker, worker.getWorkerId()+1);
					}
					if(u.getId()==worker.getWorkerId()) {
						jdbcWorker.removeWorker(worker);
					}
				}
				List<User> usersW=userManager.getRole("croupier").getUsers();
				usersW.addAll(userManager.getRole("security").getUsers());
				usersW.addAll(userManager.getRole("administration").getUsers());
				Iterator<User> ItUW= usersW.iterator();
				while(ItUW.hasNext()) {
					User userW= ItUW.next();
					if(u.getId()<userW.getId()) {
						userW.setId(userW.getId()-1);
					}
				}
				u.getRole().removeUser(u);
				userManager.removeUser(u); 
		}
	}
	private static void croupierMenu(User u) throws NumberFormatException, IOException {
		boolean bucle1=true;
		while(bucle1) {
			System.out.println("Choose an option");
			System.out.println("0. Return to login page");
			System.out.println("1. Check salary");
			System.out.println("2. Check client's state");
			System.out.println("3. Check worker list");
			System.out.println("4. Check shifts");
			int choice = Integer.parseInt(readers.readLine());
			JDBCWorker jdbcWorker= new JDBCWorker(jdbcManager);
			List<Worker> workers= jdbcWorker.getListOfWorkers();
			switch(choice)
			{
			case 0: 
				bucle1=false;
				break;
			case 1:
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
				jdbcWorker= new JDBCWorker(jdbcManager);
				workers= jdbcWorker.getListOfWorkers();
				Iterator<Worker> itW2= workers.iterator();
				while(itW2.hasNext()) {
					System.out.println(itW2.next());
				}
				break;
			case 4: 
				JDBCShift jdbcShift= new JDBCShift(jdbcManager);
				List<Shift> shifts= jdbcShift.getListOfShifts();
				Iterator<Shift> itS= shifts.iterator();
				while(itS.hasNext()) {
					Shift shift= itS.next();
					if(shift.getCroupierId()==u.getId()) {
						System.out.println("Timestamp: "+shift.getTimeStamp()+ " TableId: "+ shift.getTableId());
					}
				}
				break;
			}
				
		}
		
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
					JDBCWorker jdbcWorker= new JDBCWorker(jdbcManager);
					List<Worker> workers= jdbcWorker.getListOfWorkers();
					switch(choice) {
						case 0: 
							bucle2=false;
							break;
						case 1:
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
							jdbcWorker= new JDBCWorker(jdbcManager);
							workers= jdbcWorker.getListOfWorkers();
							Iterator<Worker> itW2= workers.iterator();
							while(itW2.hasNext()) {
								System.out.println(itW2.next());
							}
							break;
					}
				}
				break;
			case 2:
				while(bucle3) {
					System.out.println("Choose an option");
					System.out.println("0. Return to previus page");
					System.out.println("1. Client's state");
					System.out.println("2. Bancary account");
					System.out.println("3. Worker");
					System.out.println("4. Create shift");
					choice= Integer.parseInt(readers.readLine());
					switch(choice) {
						case 0: 
							bucle3=false;
							break;
						case 1:
							int clientId;
							JDBCClient jdbcClient= new JDBCClient(jdbcManager); 
							System.out.println("Introduce the client's id");
							clientId = Integer.parseInt(readers.readLine());
							List<Client> clients= jdbcClient.getClientByQuery("SELECT * FROM client WHERE client_id= "+clientId);
							Iterator<Client> itC= clients.iterator();
							Client client= itC.next();
							if(client.isCondition()) {
								client.setCondition(false);
							}else {
								client.setCondition(true);
							}
							jdbcClient.updateClient(client);
							break;
						case 2:
							float money;
							System.out.println("Introduce the new ammount of money");
							money = Float.parseFloat(readers.readLine());
							JDBCBancaryAccount jdbcBancaryAccount= new JDBCBancaryAccount(jdbcManager);
							BancaryAccount bancaryAccount= jdbcBancaryAccount.getBancaryAccount();
							bancaryAccount.setMoney(money);
							jdbcBancaryAccount.updateBancaryAccount(bancaryAccount);
							break;
						case 3:
							boolean bucle4= true;
							while(bucle4) {
								JDBCWorker jdbcWorker= new JDBCWorker(jdbcManager);
								List<Worker> workers= jdbcWorker.getListOfWorkers();
								Iterator<Worker> itW= workers.iterator();
								System.out.println("Choose an option");
								System.out.println("0. Return to previus page");
								System.out.println("1. Modify salary");
								System.out.println("2. Add Worker");
								System.out.println("3. Remove Worker");
								choice= Integer.parseInt(readers.readLine());
								switch(choice) {
									case 0:
										bucle4=false;
										break;
									case 1:
										System.out.println("Introduce workerId");
										Integer workerId= Integer.parseInt(readers.readLine());
										System.out.println("Introduce new salary");
										float salary= Float.parseFloat(readers.readLine());
										while(itW.hasNext()) {
											Worker worker= itW.next();
											if(workerId==worker.getWorkerId()) {
												worker.setSalary(salary);
												jdbcWorker.updateWorker(worker);
												break;
											}
										}
									case 2:
										registerWorker();
										break;
									case 3:
										System.out.println("Introduce workerId");
										Integer workerId2= Integer.parseInt(readers.readLine());
										while(itW.hasNext()) {
											Worker worker= itW.next();
											List<User> users= userManager.getRole(worker.getOccupationString()).getUsers();
											Iterator<User> itU= users.iterator();
											while(itU.hasNext()) {
												User uW= itU.next();
												if(worker.getWorkerId()==uW.getId()) {
													removeUser(uW);
												}
											}
										}
										
										break;
										
									}
							}
							break;
						case 4:
							JDBCShift jdbcShift= new JDBCShift(jdbcManager);
							System.out.println("Introduce workerId");
							Integer workerId= Integer.parseInt(readers.readLine());
							System.out.println("Introduce tableId");
							Integer tableId= Integer.parseInt(readers.readLine());
							Timestamp timestamp;
							System.out.println("Introduce month");
							int month= Integer.parseInt(readers.readLine());
							System.out.println("Introduce day");
							int day= Integer.parseInt(readers.readLine());
							System.out.println("Introduce hour");
							int hour= Integer.parseInt(readers.readLine());
							System.out.println("Introduce minute");
							int minute= Integer.parseInt(readers.readLine());
							jdbcShift.addShift(new Shift(tableId,workerId, new Timestamp(2023,month,day,hour,minute,0,0)));
							break;
					}
				}
				break;
			default:
				break;
			}
		}
		
	}
	private static void securityMenu(User u) throws NumberFormatException, IOException {
		boolean bucle1 = true;
		while(bucle1) {
			System.out.println("Choose an option");
			System.out.println("0. Return to login page");
			System.out.println("1. Access a client info");
			System.out.println("2. Check salary");
			System.out.println("3. Check worker list");
			System.out.println("4. Check shifts");
			//System.out.println("2. Ban a client from the database");
			int choice = Integer.parseInt(readers.readLine());
			JDBCWorker jdbcWorker= new JDBCWorker(jdbcManager);
			List<Worker> workers= jdbcWorker.getListOfWorkers();
			switch(choice)
			{
			case 0: 
				bucle1=false;
				break;
			case 1:
				String email;
				System.out.println("Introduce the client's email");
				email = readers.readLine();
				User sus = userManager.getUserByEmail(email); //Que la función te diga el nombre apellido y si es ludo
				removeUser(sus);
				break;
			case 2:
				Iterator<Worker> itW= workers.iterator();
				while(itW.hasNext()) {
					Worker w=itW.next();
					if(w.getWorkerId()==u.getId()) {
						System.out.println(w.getSalary());
					}
				}
				break;
			case 3:
				jdbcWorker= new JDBCWorker(jdbcManager);
				workers= jdbcWorker.getListOfWorkers();
				Iterator<Worker> itW2= workers.iterator();
				while(itW2.hasNext()) {
					System.out.println(itW2.next());
				}
				break;
			case 4:
				JDBCShift jdbcShift= new JDBCShift(jdbcManager);
				List<Shift> shifts= jdbcShift.getListOfShifts();
				Iterator<Shift> itS= shifts.iterator();
				while(itS.hasNext()) {
					Shift shift= itS.next();
					if(shift.getCroupierId()==u.getId()) {
						System.out.println("Timestamp: "+shift.getTimeStamp()+ " TableId: "+ shift.getTableId());
					}
				}
				break;
			}
		}	
	}
		
	
		
	
	private static void clientMenu(User u) throws NumberFormatException, IOException, MoneyException {
		boolean bucle1=true;
		boolean bucle2=false;
		boolean bucle3=true;
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
						if(c.getClientId()==u.getId()) {
							System.out.println("Current money: "+c.getMoney());}}
					System.out.println("Choose an option");
					System.out.println("0. Return");
					System.out.println("1. Deposit money");
					System.out.println("2. Extract money");
					int choice2 = Integer.parseInt(readers.readLine());
					switch(choice2) {
					case 0:
						bucle2=false;
						break;
					case 1:
						int deposit = Integer.parseInt(readers.readLine());
						Iterator<Client> itC2= client.iterator();
						while(itC2.hasNext()) {
							Client c=itC2.next();
							if(c.getClientId()==u.getId()) {
								c.setMoney(c.getMoney()-deposit);
								bucle3=false;}}
					case 2:
						while(bucle3) {
							int extraction = Integer.parseInt(readers.readLine());
							Iterator<Client> itC3= client.iterator();
							while(itC3.hasNext()) {
								Client c=itC.next();
								if(c.getClientId()==u.getId()) {
									if(c.getMoney()<extraction) {
										throw new MoneyException("It is not possible to extract that amount of money");
									}
									c.setMoney(c.getMoney()-extraction);
									bucle3=false;
								}
							}
						}
						
						
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
					case 0:
						bucle2=false;
						break;
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