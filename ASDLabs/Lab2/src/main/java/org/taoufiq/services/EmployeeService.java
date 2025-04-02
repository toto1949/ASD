package org.taoufiq.services;



import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.taoufiq.JsonConfig;
import org.taoufiq.models.Employee;
import org.taoufiq.models.PensionPlan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50, 
            new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00)));
        employees.add(new Employee(2, "Benard", "Shaw", LocalDate.of(2018, 10, 3), 197750.00, null));
        employees.add(new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75, 
            new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50)));
        employees.add(new Employee(4, "Wesley", "Schneider", LocalDate.of(2018, 11, 2), 74500.00, null));
    }

    public String getAllEmployeesJson() throws IOException {
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());
        return convertToJson(sortedEmployees);
    }

    public String getUpcomingEnrolleesJson() throws IOException {
        LocalDate nextQuarterStart = LocalDate.of(LocalDate.now().getYear(), Month.OCTOBER, 1);
        LocalDate nextQuarterEnd = LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31);

        List<Employee> eligibleEmployees = employees.stream()
                .filter(emp -> emp.isEligibleForPension(nextQuarterStart, nextQuarterEnd))
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());

        return convertToJson(eligibleEmployees);
    }

  private String convertToJson(Object obj) throws IOException {
    ObjectMapper objectMapper = JsonConfig.getMapper(); // Use the configured ObjectMapper
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    return objectMapper.writeValueAsString(obj);
}   

}
