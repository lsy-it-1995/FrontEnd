package com.company.defaults;

public class ClientAD implements A, D{

    public void A(){
        System.out.println("A" + ClientAD.class);
    }
    public static void main(String[] args) {
        ClientAD clientAD = new ClientAD();
        clientAD.A();
    }
}
