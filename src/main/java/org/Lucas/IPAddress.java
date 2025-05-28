package org.Lucas;

public class IPAddress {
    private String ipAddressBits;


    public IPAddress(String ip){
        if (ip == null || ip.isEmpty()){
            throw new IllegalArgumentException("O endereço IP não pode ser nulo ou vazio.");
        }

        // Verifica se é uma string binária de 32 bits
        if (ip.matches("[01]{32}")){
            this.ipAddressBits = ip;
        }

        // Verifica se está no formato AAA.BBB.CCC.DDD
        else if (ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
            this.ipAddressBits = converterIpv4ParaBits(ip);
        } else {
            throw new IllegalArgumentException("Formato de endereço IP inválido. Deve ser binário de 32 bits ou AAA.BBB.CCC.DDD.");
        }
    }

    private String converterIpv4ParaBits(String ipv4) {
        StringBuilder bits = new StringBuilder();
        String[] octetos = ipv4.split("\\.");
        for (String octeto : octetos){
            int decimal = Integer.parseInt(octeto);
            String octetoBinario = String.format("%8s", Integer.toBinaryString(decimal)).replace(' ', '0');
            bits.append(octetoBinario);
        }
        return bits.toString();
    }

    public String toBits(){
        return this.ipAddressBits;
    }

    public String toIpv4(){
        StringBuilder ipv4 = new StringBuilder();
        for (int i = 0; i < 32; i+=8){
            String octetoBits = this.ipAddressBits.substring(i, i + 8);
            int decimal = Integer.parseInt(octetoBits, 2);
            ipv4.append(decimal);

            if (i < 24){
                ipv4.append(".");
            }
        }
        return ipv4.toString();
    }

    public boolean isMask() {
        int indicePrimeiro = this.ipAddressBits.indexOf('0');

        if (indicePrimeiro == -1){
            return true;
        }

        for (int i = indicePrimeiro; i < 32; i++){
            if (this.ipAddressBits.charAt(i) == '1') {
                // Encontrou um '1' depois de um '0', então não é uma máscara
                return false;
            }
        }
        return true;
    }

    public int maskBits() {
        if (!isMask()){
            return -1; // Não é uma máscara válida
        }

        int contador = 0;
        // Conta os '1's contiguos no inicio da strinf de bits
        for (char bit  : this.ipAddressBits.toCharArray()){
            if (bit == '1'){
                contador++;
            } else {
                break;
            }
        }
        return contador;
    }
}
