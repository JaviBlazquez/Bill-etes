package CasinoUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import Interfaces.*;
import POJOS.*;
import jdbc.*;
public class Menu {
	
	private static BufferedReader readers = new BufferedReader (new InputStreamReader(System.in));
	
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
				case 2:
					//registerNewClient();
					break;
				case 1:
					//loginAsAdmin();
					break;
				case 0: 
					//jdbcManager.disconnect();
					//userManager.disconnect();
					System.exit(0);
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