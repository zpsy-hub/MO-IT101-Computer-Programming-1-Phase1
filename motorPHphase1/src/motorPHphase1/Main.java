package motorPHphase1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    	@SuppressWarnings("resource")
		public static void main(String[] args) {
        Scanner scanner = null;

        // Prompt user to enter employee ID and month
        Scanner input = new Scanner(System.in);
        System.out.print("Enter employee ID: ");
        String employeeID = input.next();
        System.out.print("Enter month (1-12): ");
        int month = input.nextInt();

        // Initialize list of employees, total hours worked, and days worked to 0
        ArrayList<Employee> employees = new ArrayList<>();
        double totalHours = 0.0;
        int daysWorked = 0;

        try {
            // Create a new Scanner to read the employee data file
            File employeeFile = new File("/C:/Users/zyrac/OneDrive/Desktop/employee_data.csv");
            scanner = new Scanner(employeeFile);

            // Loop through the lines in the employee data file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                // Parse the fields from the line
                String id = fields[0];
                String lastName = fields[1];
                String firstName = fields[2];
                String birthday = fields[3];
                double basicSalary = Double.parseDouble(fields[4]);
                double hourlyRate = Double.parseDouble(fields[5]);

                // Create a new employee object and add it to the list of employees
                employees.add(new Employee(id, lastName, firstName, birthday, basicSalary, hourlyRate));
            }

            // Create a new Scanner to read the attendance data file
            File attendanceFile = new File("/C:/Users/zyrac/OneDrive/Desktop/employee_attendance.csv");
            scanner = new Scanner(attendanceFile);

            // Loop through the lines in the attendance data file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                // Check if the line matches the employee ID and month
                if (fields[0].equals(employeeID) && month == Integer.parseInt(fields[3].substring(3, 5))) {
                    // Parse the date, time in, and time out from the fields
                    LocalDate date = LocalDate.parse(fields[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalTime timeIn = LocalTime.parse(fields[4]);
                    LocalTime timeOut = LocalTime.parse(fields[5]);

                    // Calculate the hours worked for the day, deducting 1 hour for lunch break
                    double hoursWorked = (Duration.between(timeIn, timeOut).toMinutes() - 60) / 60.0;

                    // Add the hours worked for the day to the total for the month
                    totalHours += hoursWorked;

                    // Increment the days worked counter for each loop
                    daysWorked++;
                }
            }

            // Find the employee with the specified ID
            Employee employee = null;
            for (Employee e : employees) {
                if (e.getId().equals(employeeID)) {
                    employee = e;
                    break;
                }
            }

            // Calculate the gross salary
            double grossSalary = totalHours * employee.getHourlyRate();
            
            //Calculate Philhealth Contribution
	        double contribPhilhealth;
	        if(employee.getBasicSalary() <=1000) {
				contribPhilhealth = 150;
			}
	        
			else if (employee.getBasicSalary() <=60000) {
				contribPhilhealth = employee.getBasicSalary() * 0.015;
			}
	        
			else {
				contribPhilhealth = 900;
			}
	        
            //Calculate Pag-ibig Contribution
			double contribPagibig;
			
			if(employee.getBasicSalary() <= 1500) {
				contribPagibig = employee.getBasicSalary() * 0.01;
			}
			
			else if (employee.getBasicSalary() <= 5000){
				contribPagibig = employee.getBasicSalary() * 0.02;
			}
			
			else {
				contribPagibig = 100;
			}
			
			//Calculate SSS Contribution
			double contribSSS;
			
			if (employee.getBasicSalary() <= 3250) {
				contribSSS = 135;
			} 
			
			else if (employee.getBasicSalary() >= 24750) {
				contribSSS = 1125;
			} 
			
			else {
				contribSSS = Math.ceil((employee.getBasicSalary() - 3250) / 500) * 22.5 + 135;
			}
			
			//Calculate total deductions
			double totalDeductions = contribPagibig + contribPhilhealth + contribSSS;
			
			//Calculate taxable income
			double taxableIncome = grossSalary - totalDeductions; 
			
			//Calculate withholding tax
		      double withholdingTax;
		      final double p = 20833;
		      final double q = 33333;
		      final double r = 66667;
		      final double s = 166667;
		      final double t = 666667;
				
				if(employee.getBasicSalary() <=p) {
					withholdingTax = 0;
				}
				
				else if (employee.getBasicSalary() <=q) {
					withholdingTax = (taxableIncome - p) * 0.20;
				}
				
				else if (employee.getBasicSalary() <=r) {
					withholdingTax = ((taxableIncome - q) * 0.25) + 2500;
				}
				
				else if (employee.getBasicSalary() <=s) {
					withholdingTax = ((taxableIncome - r) * 0.30) + 10833;
				}
				
				else if (employee.getBasicSalary() <=t) {
					withholdingTax = ((taxableIncome - s) * 0.32) + 40833.33;
				}
				
				else {
					withholdingTax = ((taxableIncome - t) * 0.35) + 200833.33;
				}
				
				//net salary
				double netSalary = taxableIncome - withholdingTax;	
				
			//formatter
			DecimalFormat f = new DecimalFormat("0,000.00");
			DecimalFormat g = new DecimalFormat("000.00");
			
            // Print the employee information and gross salary
			System.out.println("\nEmployee ID: " + employee.getId());
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Birthday: " + employee.getBirthday());
            System.out.println("\nHours worked in month " + month + ": " + totalHours + " hours");
            System.out.println("Days worked in month " + month + ": " + daysWorked + " days");
            System.out.println("Gross salary: " + f.format(grossSalary));
            System.out.println("\nPhilhealth Contribution: " + g.format(contribPhilhealth));
            System.out.println("Pag-ibig Contribution: " + g.format(contribPagibig));
            System.out.println("SSS Contribution: " + f.format(contribSSS));
            System.out.println("Total deductions: " + f.format(totalDeductions));
        	System.out.println("Withholding Tax: " + f.format(withholdingTax));
			System.out.println("Net Salary: " + f.format(netSalary));
        } 
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        
        finally {
            if (scanner != null) {
                scanner.close();
        }
     }
   }
}
	
