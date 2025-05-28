package org.Lucas;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Testes com InetAddress ---");

        IPAddress ip1 = new IPAddress("192.168.1.10");
        System.out.println("IP: " + ip1.toIpv4() + " -> Bits: " + ip1.toBits());
        System.out.println("É máscara? " + ip1.isMask() + " | Bits da máscara: " + ip1.maskBits());

        IPAddress ip2 = new IPAddress("11111111111111111111111100000000"); // 255.255.255.0
        System.out.println("IP: " + ip2.toIpv4() + " -> Bits: " + ip2.toBits());
        System.out.println("É máscara? " + ip2.isMask() + " | Bits da máscara: " + ip2.maskBits());


        IPAddress ip3 = new IPAddress("255.255.255.0");
        System.out.println("IP: " + ip3.toIpv4() + " -> Bits: " + ip3.toBits());
        System.out.println("É máscara? " + ip3.isMask() + " | Bits da máscara: " + ip3.maskBits());

    }
}