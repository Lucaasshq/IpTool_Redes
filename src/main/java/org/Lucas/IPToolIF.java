package org.Lucas;

public class IPToolIF {

    public static boolean isValid(IPAddress ip){
        // Verifica se ip é null
        return ip != null;
    }

    /**
     * Verifica se dois endereços IP pertencem à mesma rede, dada uma máscara de rede.
     *
     * @param ip1 O primeiro endereço IP.
     * @param ip2 O segundo endereço IP.
     * @param mask A máscara de rede.
     * @return true se ambos os IPs estiverem na mesma rede, false caso contrário.
     * @throws IllegalArgumentException se a máscara fornecida não for uma máscara de rede válida.
     */
    public static boolean areSameNet(IPAddress ip1, IPAddress ip2, IPAddress mask){
        if ( ip1 == null || ip2 == null || mask == null ){
            throw new IllegalArgumentException("Todos os endereços IP e a máscara devem ser fornecidos");
        }

        if (!mask.isMask()){
            throw  new IllegalArgumentException("A máscara fornecida não é uma máscara válida");
        }

        IPAddress network1 = network(ip1, mask);
        IPAddress network2 = network(ip2, mask);
        return network1.toBits().equals(network2.toBits());
    }

    /**
     * Calcula o endereço de broadcast para um dado endereço IP e máscara de rede.
     * O endereço de broadcast é obtido aplicando a operação OR bit a bit entre
     * o endereço de rede e o complemento (NOT) da máscara.
     *
     * @param ip O endereço IP.
     * @param mask A máscara de rede.
     * @return Um objeto IPAddress representando o endereço de broadcast.
     * @throws IllegalArgumentException se a máscara fornecida não for uma máscara de rede válida.
     */
    public static IPAddress broadcast(IPAddress ip, IPAddress mask){
        if (ip == null || mask == null){
            throw new IllegalArgumentException("O endereço IPe a máscara devem ser fornecidos.");
        }

        if (!mask.isMask()){
            throw new IllegalArgumentException("A máscara fornecida não é uma máscara de rede valida");
        }

        String ipBits = ip.toBits();
        String maskBits = mask.toBits();
        StringBuilder broadcastBits = new StringBuilder(32);
        String networkBits = network(ip, mask).toBits();

        for (int i = 0; i < 32; i++){
            if (maskBits.charAt(i) == '1'){
                broadcastBits.append(networkBits.charAt(i));
            } else {
                broadcastBits.append('1');
            }
        }
        return new IPAddress(broadcastBits.toString());
    }

    /**
     * Calcula o endereço de rede para um dado endereço IP e máscara de rede.
     * O endereço de rede é obtido aplicando a operação AND bit a bit entre o IP e a máscara.
     *
     * @param ip O endereço IP.
     * @param mask A máscara de rede.
     * @return Um objeto IPAddress representando o endereço de rede.
     * @throws IllegalArgumentException se a máscara fornecida não for uma máscara de rede válida.
     */
    public static IPAddress network(IPAddress ip, IPAddress  mask){
        if (ip == null || mask == null){
            throw new IllegalArgumentException("O endereço de IP e a máscara deve ser fornecidos.");
        }

        if (!mask.isMask()){
            throw new IllegalArgumentException("A máscara fornecida não é umas máscara valida");
        }

        String ipBits = ip.toBits();
        String maskBits = mask.toBits();
        StringBuilder networkBits = new StringBuilder(32);

        for (int i = 0; i < 32; i++){
            if (ipBits.charAt(i) == '1' && maskBits.charAt(i) == '1') {
                networkBits.append('1');
            } else {
                networkBits.append('0');
            }
        }
        return new IPAddress(networkBits.toString());
    }

}
