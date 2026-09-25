/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sandbox.polymorphism.interface_inner_classes;

/**
 *
 * @author endovelico
 */
public interface InterfaceInnerClasses {
    
      PaymentResult process(PaymentRequest request);

    class PaymentRequest {
        private final double amount;

        public PaymentRequest(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }
    }

    class PaymentResult {
        private final boolean successful;

        public PaymentResult(boolean successful) {
            this.successful = successful;
        }

        public boolean isSuccessful() {
            return successful;
        }
    }
}
