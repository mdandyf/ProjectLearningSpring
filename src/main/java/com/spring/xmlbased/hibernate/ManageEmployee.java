package com.spring.xmlbased.hibernate;

import com.spring.model.Employee;
import com.spring.xmlbased.hibernate.dao.EmployeeDAO;
import com.spring.xmlbased.hibernate.dao.EmployeeDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployee {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("BeansHibernate.xml");

        System.out.println("======== Insert List Data ========");
        Employee emp1 = new Employee("Rifky", "Herliyadi", 10000000);
        Employee emp2 = new Employee("Jui Chitra", "Gani", 60000000);
        Employee emp3 = new Employee("Anis", "Mubarik", 50000000);
        Employee emp4 = new Employee("Ingsihwati", "Setiawan", 5000000);
        List<Employee> list = new ArrayList<>();
        list.add(emp1);list.add(emp2);list.add(emp3);list.add(emp4);

        EmployeeDAO employeeDAO = (EmployeeDAO) context.getBean("employeeDao");
        employeeDAO.insertBatch(list);

        System.out.println("======== Insert One Data ========");

        Employee emp5 = new Employee("Ina", "Darmawan", 4000000);
        employeeDAO.insert(emp5);

        System.out.println("======== List Data ========");

        List<Employee> results = employeeDAO.findAll();
        for(Employee employee : results) {
            System.out.printf("%d. firstName: %s, lastName: %s, salary: %d \n",
                    employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }

    }

}
