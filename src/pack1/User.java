package pack1;

public abstract class User {

    // attributes
    static int empcount = 1;
    static int clicount = 1;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String address;
    private String telephoneNumber;

    // getters and setters
//    public int getEmpcount() {
//        return empcount;
//    }
//
//    public void setEmpcount(int empcount) {
//        this.empcount = empcount;
//    }
//
//    public int getClicount() {
//        return clicount;
//    }
//
//    public void setClicount(int clicount) {
//        this.clicount = clicount;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        this.username = this.firstName + "-" + this.lastName;
    }

    public void setUsername(String username) {
        this.username = this.firstName + "-" + this.lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


}
