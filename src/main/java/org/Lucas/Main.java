package org.Lucas;

public class Main {
    public static void main(String[] args) {

//        System.out.println("--- Testes com InetAddress ---");
//
//        IPAddress ip1 = new IPAddress("192.168.1.10");
//        System.out.println("IP: " + ip1.toIpv4() + " -> Bits: " + ip1.toBits());
//        System.out.println("É máscara? " + ip1.isMask() + " | Bits da máscara: " + ip1.maskBits());
//
//        IPAddress ip2 = new IPAddress("11111111111111111111111100000000"); // 255.255.255.0
//        System.out.println("IP: " + ip2.toIpv4() + " -> Bits: " + ip2.toBits());
//        System.out.println("É máscara? " + ip2.isMask() + " | Bits da máscara: " + ip2.maskBits());
//
//
//        IPAddress ip3 = new IPAddress("255.255.255.0");
//        System.out.println("IP: " + ip3.toIpv4() + " -> Bits: " + ip3.toBits());
//        System.out.println("É máscara? " + ip3.isMask() + " | Bits da máscara: " + ip3.maskBits());

        System.out.println("--- Testes da Classe IPToolIF ---");

        IPAddress ip1 = new IPAddress("192.168.1.10");
        IPAddress ip2 = new IPAddress("192.168.1.25");
        IPAddress ip3 = new IPAddress("10.0.0.5");
        IPAddress mask24 = new IPAddress("255.255.255.0"); // Máscara /24
        IPAddress mask16 = new IPAddress("255.255.0.0"); // Máscara /16
        IPAddress invalidMask = new IPAddress("255.255.254.1"); // Máscara inválida

        // Teste isValid
        System.out.println("\n--- isValid ---");
        System.out.println("IP 192.168.1.10 é válido? " + IPToolIF.isValid(ip1));
        System.out.println("Objeto nulo é válido? " + IPToolIF.isValid(null));

        // Teste areSameNet
        System.out.println("\n--- areSameNet ---");
        System.out.println("192.168.1.10 e 192.168.1.25 na rede /24: " + IPToolIF.areSameNet(ip1, ip2, mask24));
        System.out.println("192.168.1.10 e 10.0.0.5 na rede /24: " + IPToolIF.areSameNet(ip1, ip3, mask24));
        System.out.println("192.168.1.10 e 10.0.0.5 na rede /16: " + IPToolIF.areSameNet(ip1, ip3, mask16));

        // Teste network
        System.out.println("\n--- network ---");
        IPAddress network1_24 = IPToolIF.network(ip1, mask24);
        System.out.println("Rede de 192.168.1.10 com /24: " + network1_24.toIPv4() + " (" + network1_24.toBits() + ")");

        IPAddress network3_16 = IPToolIF.network(ip3, mask16);
        System.out.println("Rede de 10.0.0.5 com /16: " + network3_16.toIPv4() + " (" + network3_16.toBits() + ")");

        // Teste broadcast
        System.out.println("\n--- broadcast ---");
        IPAddress broadcast1_24 = IPToolIF.broadcast(ip1, mask24);
        System.out.println("Broadcast de 192.168.1.10 com /24: " + broadcast1_24.toIPv4() + " (" + broadcast1_24.toBits() + ")");

        IPAddress broadcast3_16 = IPToolIF.broadcast(ip3, mask16);
        System.out.println("Broadcast de 10.0.0.5 com /16: " + broadcast3_16.toIPv4() + " (" + broadcast3_16.toBits() + ")");
    }
}