package motorPHphase1;

import java.util.Scanner;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Main {

	public static void main(String[] args) {
	Scanner read = new Scanner(System.in);
	JTextArea myArea = new JTextArea(20,20);
	DecimalFormat f = new DecimalFormat("P0,000.00");
	DecimalFormat g = new DecimalFormat("P000.00");
		
		// Variable Declaration
		String employeeId;
		String name;
		String position;
		double hoursWorked;
		double hourlyRate;
		double basicPay; //basic pay is 1 month total wage
		double grossSalary;
		double netSalary;
		double contribSSS;
		double contribPhilhealth;
		double contribPagibig;
		double totalDeductions; //SSS+Pagibig+Philhealth
		double taxableIncome; // gross - total deduction
		double withholdingTax;
			
		// Input Employee ID, Name, Position, Hours Worked, Hourly Rate
		System.out.print("Enter Employee ID: ");
		employeeId = read.nextLine();

		System.out.print("Enter Employee Name: ");
		name = read.nextLine();

		System.out.print("Enter Employee Position: ");
		position = read.nextLine();

		System.out.print("Enter the Number of Hours Worked in 1 Month: ");
		hoursWorked = read.nextDouble();

		System.out.print("Enter Hourly Rate: ");
		hourlyRate = read.nextDouble();
			
		//basic pay
		basicPay = hourlyRate * 168; //1 month = 168 hours = 21 days * 8 hours
			
			
		//Pagibig Contribution
		if(basicPay <= 1500) {
			contribPagibig = basicPay * 0.01;
		}
		
		else if (basicPay <= 5000){
			contribPagibig = basicPay * 0.02;
		}
		
		else {
			contribPagibig = 100;
		}
			
		//Philhealth Contribution
		if(basicPay <=1000) {
			contribPhilhealth = 150;
		}
		
		else if (basicPay <=60000) {
			contribPhilhealth = basicPay * 0.015;
		}
		
		else {
			contribPhilhealth = 900;
		}
			
		//SSS Contribution
		if (basicPay <= 3250) {
			contribSSS = 135;
		} 
		
		else if (basicPay >= 24750) {
			contribSSS = 1125;
		} 
		
		else {
			contribSSS = Math.ceil((basicPay - 3250) / 500) * 22.5 + 135;
		}
			
		//deductions, grossSalary and taxable income
		totalDeductions = contribPagibig + contribPhilhealth + contribSSS;
		grossSalary = hoursWorked * hourlyRate;
		taxableIncome = grossSalary - totalDeductions;
			
		//withholding tax
		final double p = 20833;
		final double q = 33333;
		final double r = 66667;
		final double s = 166667;
		final double t = 666667;
			
		if(basicPay <=p) {
			withholdingTax = 0;
		}
			
		else if (basicPay <=q) {
			withholdingTax = (taxableIncome - p) * 0.20;
		}
			
		else if (basicPay <=r) {
			withholdingTax = ((taxableIncome - q) * 0.25) + 2500;
		}
			
		else if (basicPay <=s) {
			withholdingTax = ((taxableIncome - r) * 0.30) + 10833;
		}
			
		else if (basicPay <=t) {
			withholdingTax = ((taxableIncome - s) * 0.32) + 40833.33;
		}
			
		else {
			withholdingTax = ((taxableIncome - t) * 0.35) + 200833.33;
		}
			
		//Net Salary Computation
		netSalary = taxableIncome - withholdingTax;
			
		//Print Payslip
		myArea.setEditable(false);
		myArea.setText(
			"\tMotorPH\n\t PAYSLIP\n\n=============================" +
			"\nEmpID:\t\t" + employeeId +
			"\nEmpName:\t\t" + name +
			"\nEmpPosition:\t\t" + position +
			"\nHourlyRate:\t\t" + hourlyRate +
			"\nBasicPay:\t\t" + f.format(basicPay) +
			"\n\nPagibigContribution:\t" + g.format(contribPagibig) +
			"\nPhilhealthContribution:\t" + g.format(contribPhilhealth) +
			"\nSSSContribution:\t" + f.format(contribSSS) +
			"\nTotalDeduction:\t\t" + f.format(totalDeductions) +
			"\n\nGrossSalary:\t\t" + f.format(grossSalary) +
			"\nTaxableIncome:\t\t" + f.format(taxableIncome) +
			"\nWithholdingTax:\t" + f.format(withholdingTax) +
			"\nNetSalary:\t\t" + f.format(netSalary));
			JOptionPane.showMessageDialog(null,myArea, "PaySlip", JOptionPane.PLAIN_MESSAGE);
			
			
			}
		}

		

