package bankingProject.java;
import java.util.*;

public class BankingSystemApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankingService service = new BankingServiceImpl();

        while (true) {
            System.out.println("\nBanking System");
            System.out.println("1. Add Customers");
            System.out.println("2. Add Accounts");
            System.out.println("3. Add Beneficiary");
            System.out.println("4. Add Transaction");
            System.out.println("5. Find Customer by Id");
            System.out.println("6. List all Accounts of specific Customer");
            System.out.println("7. List all transactions of specific Account");
            System.out.println("8. List all beneficiaries of specific customer");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter Customer Details");
                    System.out.print("Customer Id: ");
                    int cid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Address: ");
                    String addr = sc.nextLine();
                    System.out.print("Contact No: ");
                    String cont = sc.nextLine();
                    service.addCustomer(new Customer(cid, name, addr, cont));
                    break;

                case 2:
                    System.out.println("Enter Account Details");
                    System.out.print("Account Id: ");
                    int aid = sc.nextInt();
                    System.out.print("Customer Id: ");
                    int acid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Account Type (Saving/Current): ");
                    String type = sc.nextLine();
                    System.out.print("Balance: ");
                    double bal = sc.nextDouble();
                    service.addAccount(new Account(aid, acid, type, bal));
                    break;

                case 3:
                    System.out.println("Enter Beneficiary Details");
                    System.out.print("Customer Id: ");
                    int bcID = sc.nextInt();
                    System.out.print("Beneficiary Id: ");
                    int bid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Beneficiary Name: ");
                    String bname = sc.nextLine();
                    System.out.print("Beneficiary Account No: ");
                    String bacc = sc.nextLine();
                    System.out.print("Beneficiary Bank details: ");
                    String bbank = sc.nextLine();

                    service.addBeneficiary(new Beneficiary(bid, bcID, bname, bacc, bbank));
                    break;

                case 4:
                    System.out.println("Enter Transaction Details");
                    System.out.print("Account Id: ");
                    int taid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Type (Deposit/Withdrawal): ");
                    String ttype = sc.nextLine();
                    System.out.print("Amount: ");
                    double tamt = sc.nextDouble();
                    service.addTransaction(new Transaction(taid, ttype, tamt));
                    break;

                case 5:
                    System.out.print("Customer Id: ");
                    int fid = sc.nextInt();
                    Customer c = service.findCustomerById(fid);
                    System.out.println(c != null ? c : "Customer not found");
                    break;

                case 6:
                    System.out.print("Customer Id: ");
                    int cid1 = sc.nextInt();
                    List<Account> accList = service.getAccountsByCustomerId(cid1);
                    for (Account acc : accList) System.out.println(acc);
                    break;

                case 7:
                    System.out.print("Account Id: ");
                    int accid = sc.nextInt();
                    List<Transaction> txList = service.getTransactionsByAccountId(accid);
                    for (Transaction t : txList) System.out.println(t);
                    break;

                case 8:
                    System.out.print("Customer Id: ");
                    int bc2 = sc.nextInt();
                    List<Beneficiary> benList = service.getBeneficiariesByCustomerId(bc2);
                    for (Beneficiary b : benList) System.out.println(b);
                    break;

                case 9:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
