package com.company.defaults;

public class ClientABC implements A,B,C{
    public void A(){
        System.out.println("A" + ClientABC.class);
    }
    public static void main(String[] args) {
        ClientABC clientABC = new ClientABC();
        clientABC.A();
        clientABC.B();
        clientABC.C();
    }
}
