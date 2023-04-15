import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {
    private String name;
    private int password;
    private int balance;
    private ArrayList<String> history;

    public User(String name, int password) {
        this.name = name;
        this.password = password;
        this.balance = 0;
        this.history = new ArrayList<>();

    }

    public String getName() {
        return this.name;
    }

    public int getPassword() {
        return this.password;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public int setBalanceDeposit(int money) {
        String word = "You have made a deposit of " + money+" euro";
        setHistory(word);
        return this.balance += money;

    }

    public int setBalanceDepositTransfer(int money, String name) {
        String word = name + " has deposited you " + money + " euro ";
        this.setHistory(word);
        return this.balance += money;

    }

    public void setBalanceWithdrawal(int money) {
        String word = "You have made a withdrawal of " + money;
        if (this.balance >= money) {
            this.balance -= money;
            System.out.println("You have made a withdrawal of " + money+" succsesfully");
            setHistory(word);
        } else {
            System.out.println(
                    "The Withdrawal can't be done because you only have " + this.balance + " euro in your account");
        }

    }

    public void setBalanceTransfer(User user, int money) {
        String word = "You have made a transfer of " + money + " euro to " + user.getName();
        if (this.balance >= money) {
            this.balance -= money;
            System.out.println("You have made a transfer of " + money+" succsesfully");
            user.setBalanceDepositTransfer(money,this.getName());
            setHistory(word);
        } else {
            System.out.println(
                    "The Transfer can't be done because you only have " + this.balance + " euro in your account");
        }

    }

    public void setHistory(String word) {
        String history = "";
        String date = String.valueOf(getCurrentDate());
        String time = String.valueOf(getCurrentTime());

        history = "----------------------------------"
                +"\n"+ date + "||" + time
                + "\n" + word+"\n";

        this.history.add(history);
    }

    public void printHistory() {
        for (String history : this.history) {
            System.out.println(history);
        }
    }

    public String toString() {
        return "*" + this.name + " you have " + this.balance + " euro in your bank account";
    }

}
