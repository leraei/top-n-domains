package com.kahoot;

public class Main {
    public static void main(String[] args) {
        if(args.length >= 1) {
            String[] emails = args[0].split("[\\\\n]+");

            DomainCounter domainCounter = new DomainCounter();
            domainCounter.countAndPrint(emails);
        }
    }
}
