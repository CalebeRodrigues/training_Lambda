package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		List<Employee> employee;
		Double limite;
		char letra = 'M';

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();

		System.out.print("Enter salary: ");
		limite = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			employee = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				employee.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}

			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			List<String> listEmail = employee.stream().filter(p -> p.getSalary() > limite).map(p -> p.getEmail())
					.sorted(comp).collect(Collectors.toList());

			double soma = employee.stream().filter(p -> p.getName().toUpperCase().charAt(0) == letra)
					.map(p -> p.getSalary()).reduce(0.0, (x, y) -> x + y);

			listEmail.forEach(System.out::println);
			System.out.println("Sum of salary people whose name starts with '" + letra + "': " + soma);
		} 
		catch (Exception e) {

		}

		sc.close();
	}
}
