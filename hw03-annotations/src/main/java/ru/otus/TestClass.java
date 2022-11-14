package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestClass {
    @Before
    private void firstBeforeMethod() {
        System.out.println("First before method is called");
        //throw new RuntimeException();
    }

    @Before
    public void secondBeforeMethod() {
        System.out.println("Second before method is called");
    }

    @Before
    public void thirdBeforeMethod() {
        System.out.println("Third before method is called");
    }

    @Test
    public void firstTestMethod() {
        System.out.println("First test method is called");
        //throw new RuntimeException();
    }

    @Test
    public void secondTestMethod() {
        System.out.println("Second test method is called");
        //throw new RuntimeException();;
    }

    @Test
    public void thirdTestMethod() {
        System.out.println("Third test method is called");
        //throw new RuntimeException();
    }

    @After
    public void firstAfterMethod() {
        System.out.println("First after method is called");
        //throw new RuntimeException();
    }

    @After
    public void secondAfterMethod() {
        System.out.println("Second after method is called");
    }
}
