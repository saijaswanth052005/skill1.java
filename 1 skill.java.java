import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class EmployeeManager {
    public static void main(String[] args) {
        // Step 2: Create a list of 10 employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 50000));
        employees.add(new Employee(2, "Bob", 60000));
        employees.add(new Employee(3, "Charlie", 55000));
        employees.add(new Employee(4, "David", 75000));
        employees.add(new Employee(5, "Eve", 70000));
        employees.add(new Employee(6, "Frank", 80000));
        employees.add(new Employee(7, "Grace", 72000));
        employees.add(new Employee(8, "Hank", 85000));
        employees.add(new Employee(9, "Ivy", 65000));
        employees.add(new Employee(10, "Jack", 90000));

        // Step 3: Perform filtering, sorting, finding highest salary, and average salary using Stream API

        // 1. Filter employees with salary greater than 60000
        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.getSalary() > 60000)
                .collect(Collectors.toList());
        System.out.println("Filtered Employees with salary > 60000:");
        filteredEmployees.forEach(System.out::println);

        // 2. Sort employees by salary in descending order
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        System.out.println("\nEmployees sorted by salary (descending):");
        sortedEmployees.forEach(System.out::println);

        // 3. Find the employee with the highest salary
        Optional<Employee> highestSalaryEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println("\nEmployee with the highest salary:");
        highestSalaryEmployee.ifPresent(System.out::println);

        // 4. Calculate the average salary of all employees
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage salary of employees: " + averageSalary);
    }
}
