package com.sandbox.polymorphism.covariant_return;

public class Dog extends Animal implements Being {

    @Override
    public Dog getAnimal() {
        return this;
    }


    @Override
    public Dog create() {
        return null;
    }
}
