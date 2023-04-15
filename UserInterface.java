import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scan;
    private ArrayList<User> user;

    public UserInterface(Scanner scan) {
        this.scan = scan;
        this.user = new ArrayList<>();
    }

    public void start() {
        while (true) {
            System.out.println("\t\tWelcome\n->Sing In\n->Sing Up\n->Exit");
            String option = this.scan.nextLine();
            if (option.equalsIgnoreCase("Exit")) {
                break;
            } else if (option.equalsIgnoreCase("Sing In")) {
                System.out.println("Name of account");
                String accountName = this.scan.nextLine();
                System.out.println("Password of account");
                int accountPassword = Integer.valueOf(this.scan.nextLine());
                if (userLogInTrueFalse(accountName, accountPassword)) {
                    int index = findUserIndex(accountName);
                    logIn(index);
                } else {
                    System.out.println("The name or password is incorrect");
                }
            } else if (option.equalsIgnoreCase("Sing Up")) {
                singUp();
            }
        }
    }

    public void logIn(int index) {
        while (true) {
            System.out.println(
                    "\t\tWelcome " + this.user.get(index).getName()
                            + "\n->1.Deposit\n->2.Withdrawal\n->3.Transfer\n->4.Check Balance\n->5.Transaction History\n->6.Exit");

            int option = Integer.valueOf(this.scan.nextLine());
            if (option == 6) {
                break;
            } else if (option == 1) {
                deposit(index);
            } else if (option == 2) {
                withdrawal(index);
            } else if (option == 3) {
                transfer(index);
            } else if (option == 4) {
                balance(index);
            } else if (option == 5) {
                transactionHistory(index);
            }
        }
    }

    public void singUp() {
        System.out.println("Write your name");
        String name = this.scan.nextLine();
        System.out.println("Write your password");
        int password = Integer.valueOf(this.scan.nextLine());
        this.user.add(new User(name, password));
        System.out.println("You have singed up succsesfully");
    }

    public void deposit(int index) {
        System.out.println("How much money do you want to deposit");
        int money = Integer.valueOf(this.scan.nextLine());
        this.user.get(index).setBalanceDeposit(money);
        System.out.println("You have deposited " + money + " euro succsesfully");
    }

    public void withdrawal(int index) {
        System.out.println("How much money do you want to withdrawal");
        int money = Integer.valueOf(this.scan.nextLine());
        this.user.get(index).setBalanceWithdrawal(money);
    }

    public void transfer(int index) {
        System.out.println("Write the name of the person you want to transfer money");
        String name = this.scan.nextLine();
        if (userExistInUserList(name)) {
            int index2 = findUserIndex(name);
            System.out.println("How much money do you want to transfer");
            int money = Integer.valueOf(this.scan.nextLine());
            this.user.get(index).setBalanceTransfer(user.get(index2), money);
        } else {
            System.out.println("Name is not found error!");
        }

    }

    public void balance(int index) {
        System.out.println(this.user.get(index));
    }

    public void transactionHistory(int index) {
        this.user.get(index).printHistory();
    }

    public boolean userLogInTrueFalse(String name, int password) {
        for (User user : this.user) {
            if (user.getName().equals(name) &&
                    user.getPassword() == password) {
                return true;
            }
        }
        return false;
    }

    public int findUserIndex(String name) {
        int index = 0;
        for (int i = 0; i < this.user.size(); i++) {
            if (this.user.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    public boolean userExistInUserList(String name) {
        for (User user : this.user) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
