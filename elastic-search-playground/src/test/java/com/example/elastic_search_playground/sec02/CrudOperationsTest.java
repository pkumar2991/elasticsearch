package com.example.elastic_search_playground.sec02;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;

import com.example.elastic_search_playground.AbstractTest;
import com.example.elastic_search_playground.sec02.entity.Employee;
import com.example.elastic_search_playground.sec02.repository.EmployeeRepository;

public class CrudOperationsTest extends AbstractTest{

    private static final Logger log  = LoggerFactory.getLogger(CrudOperationsTest.class);

    @Autowired
    EmployeeRepository repository;

    @Test
    public void curd(){
        var employee = this.createEmployee(1, "Sam", 30);
        // save
        repository.save(employee);
        this.printAll();

        //find
        employee =  repository.findById(1).orElseThrow();
        Assertions.assertEquals(30, employee.getAge());
        Assertions.assertEquals("Sam", employee.getName());
        this.printAll();

        //Update
        employee.setAge(35);
        employee = repository.save(employee);
        Assertions.assertEquals(35, employee.getAge());
        this.printAll();

        //Delete
        this.repository.delete(employee);
        Assertions.assertTrue(this.repository.findById(1).isEmpty());
        this.printAll();
    }

    @Test
    public void bulkInsert(){
       var empList = IntStream.rangeClosed(1,10)
       .mapToObj(i -> this.createEmployee(i, "name-"+i, 20+i))
       .toList();
        // bulk insert
        log.info("Employee List Inserted");  
        this.repository.saveAll(empList);
        Assertions.assertEquals(10, this.repository.count());
        this.printAll();

        // find by listofids
        log.info("Employee List Fetched");  
        var empIds = List.of(2,3,5,6);
        var empsIterable = this.repository.findAllById(empIds);
        var list = Streamable.of(empsIterable).toList();
        Assertions.assertEquals(4, list.size());
        list.forEach(e -> log.info("Employee: {}",e) );

        // update and save
        log.info("Employee List Saved");  
        list.forEach(e -> e.setAge(e.getAge()+5));
        this.repository.saveAll(list);
        this.repository.findAllById(empIds)
            .forEach(e -> Assertions.assertTrue(e.getAge() > 25));
        this.printAll();

        // delete and check count
        log.info("Employee List Deleted");  
        this.repository.deleteAllById(empIds);
        Assertions.assertEquals(6, this.repository.count());
        this.printAll();

    }

    private Employee createEmployee(int id, String nmae, int age){
        var employee = new Employee();
        employee.setId(id);
        employee.setName(nmae);
        employee.setAge(age);
        return employee;
    }

    private void printAll(){
        this.repository.findAll()
            .forEach(e -> log.info("Employee: {}",e));
    }

}
