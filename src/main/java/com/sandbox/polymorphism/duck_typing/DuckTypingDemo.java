package com.sandbox.polymorphism.duck_typing;

interface Quackable {
    void quack();
}

class Duck implements Quackable {
    public void quack() {
        System.out.println("Quack!");
    }
}

class Person implements Quackable {
    public void quack() {
        System.out.println("I'm pretending to be a duck!");
    }
}

public class DuckTypingDemo {
    static void makeItQuack(Quackable q) {
        q.quack();
    }

    public static void main(String[] args) {
        makeItQuack(new Duck());
        makeItQuack(new Person());
    }
}