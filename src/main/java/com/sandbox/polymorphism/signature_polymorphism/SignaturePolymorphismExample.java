package com.sandbox.polymorphism.signature_polymorphism;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class SignaturePolymorphismExample {

    public static void main(String[] args) throws Throwable {

        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // ---------------------------------------------------------
        // 1. Create a MethodHandle for:
        //
        //     int add(int, int)
        //
        // ---------------------------------------------------------

        MethodHandle addHandle = lookup.findStatic(
                SignaturePolymorphismExample.class,
                "add",
                MethodType.methodType(
                        int.class,
                        int.class,
                        int.class
                )
        );

        System.out.println("Handle type:");
        System.out.println(addHandle.type());

        // The invocation has the signature:
        //
        //     (int, int) -> int
        //
        // The compiler knows this from the call site.

        int result = (int) addHandle.invokeExact(10, 20);

        System.out.println("Result = " + result);


        // ---------------------------------------------------------
        // 2. Another invocation of invokeExact()
        //
        // The SAME Java method:
        //
        //     invokeExact(...)
        //
        // is now invoked with a different signature.
        // ---------------------------------------------------------

        MethodHandle greetHandle = lookup.findStatic(
                SignaturePolymorphismExample.class,
                "greet",
                MethodType.methodType(
                        String.class,
                        String.class
                )
        );

        String greeting =
                (String) greetHandle.invokeExact("John");

        System.out.println(greeting);


        // ---------------------------------------------------------
        // 3. Notice the two invocation signatures
        // ---------------------------------------------------------

        /*
         *
         * First call:
         *
         *     (int, int) -> int
         *
         * Second call:
         *
         *     (String) -> String
         *
         *
         * Both calls use:
         *
         *     invokeExact(...)
         *
         *
         * This is signature polymorphism.
         */


        // ---------------------------------------------------------
        // 4. invokeExact() is strict
        // ---------------------------------------------------------

        // This works:

        int value =
                (int) addHandle.invokeExact(5, 7);

        System.out.println("Exact result = " + value);


        // This does NOT work:
        //
        // long value =
        //     (long) addHandle.invokeExact(5, 7);
        //
        // Why?
        //
        // The MethodHandle expects:
        //
        //     (int, int) -> int
        //
        // But the call site requests:
        //
        //     (int, int) -> long
        //
        // invokeExact() requires an exact match.


        // ---------------------------------------------------------
        // 5. invoke() allows adaptation
        // ---------------------------------------------------------

        Number number =
                (Number) addHandle.invoke(5, 7);

        System.out.println("invoke() result = " + number);


        // ---------------------------------------------------------
        // 6. Different invocation signature
        // ---------------------------------------------------------

        Object object =
                greetHandle.invoke("Alice");

        System.out.println("invoke() result = " + object);
    }


    // -------------------------------------------------------------
    // Methods that will be referenced by MethodHandles
    // -------------------------------------------------------------

    private static int add(int a, int b) {
        return a + b;
    }

    private static String greet(String name) {
        return "Hello, " + name;
    }
}