package ru.job4j.ood.srp.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
/**
 * Отчеты
 *
 * смотри Store
 *
 * @author Alex_life
 * @version 2.0
 * @since 27.09.2022
 */
public class Employee {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;

    private List<Employee> employees = new ArrayList<>();

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public Employee(List<Employee> employeeList) {
        this.employees = employeeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
