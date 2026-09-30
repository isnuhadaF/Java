public class Customer {
    private String customerName;
    private String customerAccountNumber;
    private String customerUserID;
    private String customerPassword;
    private Account customerAccount;

    public Customer() {
        this.customerAccount = customerAccount;
        this.customerName = customerName;
        this.customerAccountNumber = customerAccountNumber;
        this.customerUserID = customerUserID;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getCustomerAccountNumber() {
        return this.customerAccountNumber;
    }

    public String getCustomerUserID() {
        return customerUserID;
    }

    public Account getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Account customerAccount) {
        this.customerAccount = customerAccount;
    }
    
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}
