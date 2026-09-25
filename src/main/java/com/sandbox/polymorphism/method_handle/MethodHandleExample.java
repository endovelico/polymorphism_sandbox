package com.sandbox.polymorphism.method_handle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleExample {

    public static void main(String[] args) throws Throwable {

        MethodHandles.Lookup lookup = MethodHandles.lookup();

        Calculator calculator = new Calculator();

        // ---------------------------------------------------------
        // 1. MethodHandle for an instance method
        // ---------------------------------------------------------

        MethodHandle addHandle = lookup.findVirtual(
                Calculator.class,
                "add",
                MethodType.methodType(
                        int.class,
                        int.class,
                        int.class
                )
        );

        int result = (int) addHandle.invokeExact(
                calculator,
                10,
                20
        );

        System.out.println("add(10, 20) = " + result);


        // ---------------------------------------------------------
        // 2. MethodHandle for a static method
        // ---------------------------------------------------------

        MethodHandle multiplyHandle = lookup.findStatic(
                Calculator.class,
                "multiply",
                MethodType.methodType(
                        int.class,
                        int.class,
                        int.class
                )
        );

        int multiplicationResult =
                (int) multiplyHandle.invokeExact(10, 20);

        System.out.println(
                "multiply(10, 20) = " + multiplicationResult
        );


        // ---------------------------------------------------------
        // 3. MethodHandle for a getter
        // ---------------------------------------------------------

        MethodHandle nameGetter = lookup.findGetter(
                Calculator.class,
                "name",
                String.class
        );

        String name = (String) nameGetter.invokeExact(calculator);

        System.out.println("name = " + name);


        // ---------------------------------------------------------
        // 4. MethodHandle for a setter
        // ---------------------------------------------------------

        MethodHandle nameSetter = lookup.findSetter(
                Calculator.class,
                "name",
                String.class
        );

        nameSetter.invokeExact(calculator, "Scientific Calculator");

        System.out.println(
                "new name = " + calculator.name
        );


        // ---------------------------------------------------------
        // 5. Inspect the MethodHandle type
        // ---------------------------------------------------------

        System.out.println();
        System.out.println("addHandle type: "
                + addHandle.type());

        System.out.println("multiplyHandle type: "
                + multiplyHandle.type());

        System.out.println("nameGetter type: "
                + nameGetter.type());


        // ---------------------------------------------------------
        // 6. Adapt a MethodHandle
        // ---------------------------------------------------------

        MethodHandle adaptedAdd =
                addHandle.asType(
                        MethodType.methodType(
                                Number.class,
                                Calculator.class,
                                Integer.class,
                                Integer.class
                        )
                );

        Number adaptedResult =
                (Number) adaptedAdd.invokeExact(
                        calculator,
                        Integer.valueOf(5),
                        Integer.valueOf(7)
                );

        System.out.println(
                "adapted add(5, 7) = " + adaptedResult
        );
    }


    // -------------------------------------------------------------
    // Class containing methods/fields we'll access dynamically
    // -------------------------------------------------------------

    static class Calculator {

        public String name = "Calculator";

        public int add(int a, int b) {
            return a + b;
        }

        public static int multiply(int a, int b) {
            return a * b;
        }
    }
}
