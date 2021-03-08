package com.knoldus.learning.controller;

import com.knoldus.learning.entity.Employee;
import com.knoldus.learning.service.SaveEmployeeService;
import com.knoldus.learning.service.SaveEnployeeServiceImpl;
import com.knoldus.learning.service.ValidateEmployeeService;
import com.knoldus.learning.service.ValidateEmployeeServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class EmployeeController {

    public static void main(String[] a) throws IOException {
        ValidateEmployeeService validateEmployeeService = new ValidateEmployeeServiceImpl();
        SaveEmployeeService saveEmployeeService = new SaveEnployeeServiceImpl();
        List<Employee> emplList = new ArrayList<>();

        //remove below for loop and
        // read data from employee.csv, csv have id, and name in each row.
        // focus on Functional Interface and mark them Functional if it is.
        // focus on Single responsibility principle.
        // -1,Jiten
        // 2,Ram
        /*for(int i = -5;i<10;i++){
            Employee employee = new Employee(i,"Name"+i);
            emplList.add(employee);
        }*/
        Stream<String> filename = Files.lines(Paths.get("empdata.csv"));
        List<List<String>> empdataList = filename.skip(1)
                .map(line -> Arrays.asList(line.split(",")))
                .collect(Collectors.toList());


        for( int i=0; i < empdataList.size() ; i++){

            List<String>EmpName = empdataList.get(i).stream()
                    .filter(name-> name.length() > 1).collect(Collectors.toList());

            List<Integer> empID = empdataList.get(i).stream()
                    .filter(name-> name.length() == 1).map(str -> new Integer(str)).collect(Collectors.toList());

            int empid = empID.get(0);
            String empname = EmpName.get(0);
            emplList.add(new Employee(empid ,empname));

        }
        System.out.println("Total employee size  :  "+emplList.size());
        //emplList iterate and call validate service, then save.
        for (Employee e : emplList) {
            if(validateEmployeeService.validateEmployee(e))
                saveEmployeeService.saveEmployee(e);
        }
        //Remove this error.
        System.out.println("Saved employee  : "+saveEmployeeService.getEmployeeCount());

    }
}
