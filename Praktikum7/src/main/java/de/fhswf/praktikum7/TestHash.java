package de.fhswf.praktikum7;


import org.apache.commons.codec.digest.Crypt;

public class TestHash {
    public static void main(String[] args) {
        System.out.println("ck:");
        System.out.println(Crypt.crypt("ck123", "$5$salt1"));

    }
}