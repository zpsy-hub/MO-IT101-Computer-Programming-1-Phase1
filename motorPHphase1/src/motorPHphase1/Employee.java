package motorPHphase1;

public class Employee {
	private String id;
    private String lastName;
    private String firstName;
    private String birthday;
    private double basicSalary;
    private double hourlyRate;

    public Employee(String id, String lastName, String firstName, String birthday, double basicSalary, double hourlyRate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.basicSalary = basicSalary;
        this.hourlyRate = hourlyRate;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
