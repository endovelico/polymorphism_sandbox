package com.sandbox.polymorphism.duck_typing;

import java.lang.reflect.Method;

class Duck2 {
    public void quack() {
        System.out.println("Duck: Quack!");
    }
}

class Robot {
    public void quack() {
        System.out.println("Robot: Electronic Quack!");
    }
}

class Dog {
    public void bark() {
        System.out.println("Woof!");
    }
}

public class ReflectionDuckTyping {

    static void makeItQuack(Object obj) {
        try {
            Method m = obj.getClass().getMethod("quack");
            m.invoke(obj);
        } catch (Exception e) {
            System.out.println(obj.getClass().getSimpleName()
                    + " cannot quack.");
        }
    }

    public static void main(String[] args) {
        makeItQuack(new Duck2());
        makeItQuack(new Robot());
        makeItQuack(new Dog());
    }
}
