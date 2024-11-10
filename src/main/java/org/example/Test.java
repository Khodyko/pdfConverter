package org.example;

public class Test {

    private String name;

    Test(String name) {
        this.name =  name;
    }

    public void test(Test test) {
        test = new Test("three");
    }

    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Test t1 = new Test("one");
        Test t2 = new Test("two");
        t1.test(t2);
        System.out.println(t2);
    }
}
