package ru.otus;

import ru.otus.annotations.Log;

class TestLogging implements TestLoggingInterface {

    @Log
    public void calculation() {
        System.out.println("Sum 0");
    }

    @Log
    public void calculation(int firstParam) {
        System.out.printf("Sum %d\n", firstParam);
    }

    @Log
    public void calculation(int firstParam, int secondParam) {
        System.out.printf("Sum %d\n", firstParam + secondParam);
    }


    public void calculation(int firstParam, int secondParam, int thirdParam) {
        System.out.printf("Sum %d\n", firstParam + secondParam + thirdParam);
    }


}
