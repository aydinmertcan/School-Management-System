package com.marathon.menu;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Person objesinden 2 adet obje türetilmektedir: 1. Employees , 2. Students(Oluşturulamadı.)
 * Person objesinin içinde hepsinde bulunan ortak özellikler oluşturuldu.
 * Oluşturulan özellikler
 * Employees'in içinde çalışanlara özgü özellikler yazıldı.
 * Employees objesinden türetilen Officer, Janitor ve Teacher classları oluşturuldu.
 * Bulunduğumuz classta yapıcı metotlar çağırıldı.
 * Menü oluşturuldu.
 * Menüdeki gerekli yerlere defaultEmployees'te oluşturulan bilgiler gönderildi.
 */

public class Menu {
	public static Scanner scan =  new Scanner(System.in);
	
	public static void main(String[] args) {
		BAUtils.header("SCHOOL MANAGEMENT SYSTEM MENU");
		menu();
		BAUtils.footer();
	}	
	
	// Database
	public static List<Employees> defaultEmployees () {
		LocalDate birthDay = LocalDate.of(1995, 03, 29);
		List<Employees> list = new ArrayList<Employees>();
		
		Teacher teacher1 = new Teacher("Mert Can", "Aydın", LocalDate.of(1995, 03, 29), LocalDate.of(2001, 06, 12), null, "123456", "T", 1, false, 1000.0, 2000.0, "Erkek");
		Teacher teacher2 = new Teacher("Ayşe", "Sevinç", LocalDate.of(1985, 10, 18), LocalDate.of(2012, 05, 29), null, "1234356", "T", 2, true, 1500.0, 2100.0, "Kadın");
		Teacher teacher3 = new Teacher("Fatma", "Yılmaz", LocalDate.of(1985, 11, 19), LocalDate.of(2010, 05, 29), LocalDate.of(2020, 03, 22), "5456825602", "T", 3, false, 1350.0, 2200.0,"Kadın");
		Officer officer1 = new Officer("Sara", "Demir", LocalDate.of(1990, 10, 25), LocalDate.of(2005, 04, 12),null, "12345678", "O", 4, true, 1000.0, 1050.0,"Kadın");
		Officer officer2 = new Officer("Yasemin", "Günebakan", LocalDate.of(1991, 05, 26), LocalDate.of(2015, 05, 29), null, null, "O", 5, true, 950.0, 1200.0,"Erkek");
		Officer officer3 = new Officer("Rıza", "Koç", LocalDate.of(1988, 10, 26), LocalDate.of(2010, 12, 22), LocalDate.of(2020, 12, 02), "12344114", "O", 6, false, 950.0, 0,"Erkek");
		Janitor janitor1 = new Janitor("Yasemin", "Günebakan", LocalDate.of(1992, 06, 12), LocalDate.of(2012, 10, 25), LocalDate.of(2020, 01, 29), "123123123", "J", 7, true, 750.0, 0,"Kadın");
		Janitor janitor2 = new Janitor("Hasan", "Yener", LocalDate.of(1980, 03, 11), LocalDate.of(2002, 10, 22), null, "123123123", "J", 8, false, 800.0, 950.0,"Erkek");
		list.add(teacher1);
		list.add(teacher2);
		list.add(teacher3);
		list.add(officer1);
		list.add(officer2);
		list.add(officer3);
		list.add(janitor1);
		list.add(janitor2);
		
		return list;
	}

	// Menu
	public static void menu() {
		
		boolean isContinue;
		do {
			System.out.println("(1) Add new employee\n(2) Employee List\n(3) Former Employees\n(4) Create New Course\n(5) Appoint Student\n(6) Class List\n(7) Current Courses\n(8) Gift List\n(9) Wage List\n(0) Exit");
			int userChoice = scan.nextInt();
			scan.nextLine();
			switch (userChoice) {
				case 1:
					defaultEmployees();
					int count = 1;
					for (Employees employees : defaultEmployees()) {
						System.out.println(count +". Person is added to the list.");
						count++;
					}
					break;
				case 2:
					showEmployees(defaultEmployees());
					break;
				case 3:
					formerEmployees(defaultEmployees());
					break;
				case 8:
					giveGift2Person(defaultEmployees());
					break;
				case 9:
					personWageList(defaultEmployees());
					break;
				case 0:
				System.exit(0);
				
				default:
					System.out.println("Coming soon...");
					
					
			}
			System.out.println("Do you want to continue (y,n)");
			String want2Continue = scan.nextLine();
			if(want2Continue.equalsIgnoreCase("y")) {
				isContinue = true;
			}
			else if (want2Continue.equalsIgnoreCase("n")) {
				isContinue = false;
			}
			else {
				System.out.println("Please enter y or n");
				isContinue = true;
			}
		} while (isContinue == true);
		
		
	}
	
	public static void showMenu() {
		BAUtils.header("SCHOOL MANAGEMENT SYSTEM MENU");
		System.out.println();
		System.out.println("(1) Add new employee\n"
				+ "(2) Employee List\n"
				+ "(3) Former Employees\n"
				+ "(4) Create New Course\n"
				+ "(5) Appoint Student\n"
				+ "(6) Class List\n"
				+ "(7) Current Courses\n"
				+ "(8) Gift List\n"
				+ "(9) Wage List\n(0) Exit");
		scan.nextInt();
		
	}
	
	
	// showEmployees shows the employees that currently working
	public static void showEmployees (List<Employees> list) {
		for (Employees temp : list) {
			if (temp.getEndDate() == null)
			System.out.println(temp);
		}
	}
	
	// This method prints former employees by taking defaultEmployees's output
	public static void formerEmployees(List<Employees> employees) {
		List<Employees> formerEmployees = new ArrayList<Employees>();
		for (Employees temp : employees) {
			if(temp.getEndDate() != null) {
				formerEmployees.add(temp);
			}

		}
			for (Employees temp : formerEmployees) {
				System.out.println(temp.getName() + " " + temp.getSurname() + " Start in " + temp.getStartDate() + " Ends in " + temp.getEndDate() + " Total work time: " 
			+ Period.between(temp.getStartDate(),temp.getEndDate()).getYears() + " years " + Period.between(temp.getStartDate(), temp.getEndDate()).getMonths() + " months" );
			
		}
	}
	
	// Same input enters the method and gives comming anniversary of started work and also gives birthdays of woman.
	private static void giveGift2Person(List<Employees> employees) {
		LocalDate local = LocalDate.now();
		List<Employees> birthDay = new ArrayList<Employees>();
		List<Employees> comingGifts = new ArrayList<Employees>();
		for (Employees temp : employees) {
		    if (temp.getBirthDay().getMonth() == local.getMonth() && temp.getBirthDay().getDayOfMonth() == local.getDayOfMonth() && temp.getGender() == "Kadın") {
			birthDay.add(temp);
		    }
		    if (temp.getStartDate().getMonth() == local.getMonth() || (temp.getBirthDay().getMonth() == local.getMonth()&& temp.getGender() == "Kadın")){
			
			comingGifts.add(temp);
		    }
		}
		for (Object object : birthDay) {
		    System.out.println(object);
		}
		System.out.println("The employees that started work or have birthday in this month are:");
		for (Object object : comingGifts) {
			System.out.println(object);
		}
	    }
	
	// Gives curruent wage of employees
	private static void personWageList(List<Employees> employee) {
		List<Employees> wage = new ArrayList<Employees>();
		for (Employees temp : employee) {
		    if (temp.getCurrSalary() != 0) {
			wage.add(temp);
		    } else {
			continue;
		    }
		}
		for (Object object : wage) {
		    System.out.println(object);
		}
	}
}
