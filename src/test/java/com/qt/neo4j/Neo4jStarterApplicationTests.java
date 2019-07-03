package com.qt.neo4j;

import com.qt.neo4j.entitiy.Employee;
import com.qt.neo4j.entitiy.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jStarterApplicationTests {

    @Test
    public void contextLoads() {

            ArrayList<String> list = new ArrayList<>();
            list.add("aaa");
            list.add("bbb");
            list.add("ccc");
            list.add("ddd");
            list.add("eee");

            System.out.println(list.indexOf("ccc"));
        System.out.println("List的size为："+list.size());

    }


    @Test
    public void testSet(){
        HashSet<Employee> set = new HashSet<>();
        Employee employee1 = new Employee("10001");
        Employee employee2 = new Employee("10001");
        Employee employee3 = new Employee("10002");
        set.add(employee1);
        set.add(employee2);
        set.add(employee3);
        for (Employee s: set        ) {
            System.out.println(s);
        }
    }

    @Test
    public void getClassName(){
        User user = new User();
        String className = user.getClass().getSimpleName();
        System.out.println(className);
        System.out.println(User.class.getSimpleName());
    }

    public static void set(Object obj){
        System.out.println(obj.getClass().getSimpleName());
    }
    @Test
    public void  judgeObject(){
        Neo4jStarterApplicationTests.set(new User());
    }

}
