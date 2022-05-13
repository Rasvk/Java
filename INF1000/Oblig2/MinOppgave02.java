import java.util.Scanner;

public class MinOppgave2 {


    public static void main(String[]args) {
	Scanner sc = new Scanner(System.in);
	String password = "";
	String secAnswer ="";
	String secretCheck = "";
	String username = "";
	int menuChoice;
	menu();
	menuChoice = Integer.parseInt(sc.nextLine());
	menucheck();
	    
	    menu();
	    menuChoice = Integer.parseInt(sc.nextLine());
	    menuCheck();
    }
	
        public static void menu() {
	System.out.println("1. Register new user.");
	System.out.println("2. Change password for existing user.");
	//	System.out.println("3. Delete user.");
    }

    public static void registerNewUser() {
	System.out.println("Type in username press enter, then enter your preffered password, then press enter.");
	

    }

    public static void secretAnswer() {
	System.out.println("Keep the answer to this question a secret, you can use this answer to reset your password");
	System.out.print("What is your bestfriends name? ");
	
    }

    public static void changePassword (){
	System.out.println("Please type the answer to your secret question.");
        
    }
    public static void menuCheck(){
    if (menuChoice == 1) {
	    registerNewUser();
	    username = sc.nextLine();
	    password = sc.nextLine();
	    secretAnswer();
	    secAnswer = sc.nextLine();
	    
	}
  
	if (menuChoice == 2){
	    changePassword();
	    secretCheck = sc.nextLine();
	}
	    if (secretCheck == secAnswer){
	    System.out.print("Enter a new password: ");
	    password = sc.nextLine();
	}
	    else {
	    System.out.println("The secret answer you have entered is incorrect.");
	}
    }
}
