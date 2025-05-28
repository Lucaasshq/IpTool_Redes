package org.Lucas; // Define o pacote onde a classe está localizada.

/**
 * A classe IPAddress gerencia endereços IP (IPv4), permitindo conversões
 * entre formatos e verificações de propriedades de máscara de rede.
 */
public class IPAddress {
    // Armazena o endereço IP como uma string de 32 bits binários (0s e 1s).
    private String ipAddressBits;

    /**
     * Construtor para criar um objeto IPAddress.
     * Aceita IPs em formato binário de 32 bits ou AAA.BBB.CCC.DDD.
     * @param ip A string do endereço IP.
     * @throws IllegalArgumentException Se o formato do IP for inválido ou nulo/vazio.
     */
    public IPAddress(String ip){
        // Valida se a string IP não é nula ou vazia.
        if (ip == null || ip.isEmpty()){
            throw new IllegalArgumentException("O endereço IP não pode ser nulo ou vazio.");
        }

        // Se a string for 32 caracteres de '0' ou '1', armazena diretamente.
        if (ip.matches("[01]{32}")){
            this.ipAddressBits = ip;
        }
        // Se a string for do formato AAA.BBB.CCC.DDD, converte para bits e armazena.
        else if (ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
            this.ipAddressBits = converterIpv4ParaBits(ip);
        } else {
            // Lança exceção se o formato não for reconhecido.
            throw new IllegalArgumentException("Formato de endereço IP inválido. Deve ser binário de 32 bits ou AAA.BBB.CCC.DDD.");
        }
    }

    /**
     * Converte um endereço IPv4 decimal pontuado para sua representação binária de 32 bits.
     * @param ipv4 A string do IPv4 no formato AAA.BBB.CCC.DDD.
     * @return A string de 32 bits binários.
     */
    private String converterIpv4ParaBits(String ipv4) {
        StringBuilder bits = new StringBuilder();
        // Divide o IPv4 em octetos e os converte para binário de 8 bits.
        String[] octetos = ipv4.split("\\.");
        for (String octeto : octetos){
            int decimal = Integer.parseInt(octeto);
            String octetoBinario = String.format("%8s", Integer.toBinaryString(decimal)).replace(' ', '0');
            bits.append(octetoBinario);
        }
        return bits.toString();
    }

    /**
     * Retorna o endereço IP como uma string de 32 bits binários.
     * @return A string de 32 bits.
     */
    public String toBits(){
        return this.ipAddressBits;
    }

    /**
     * Retorna o endereço IP no formato decimal pontuado (AAA.BBB.CCC.DDD).
     * @return O endereço IPv4 no formato padrão.
     */
    public String toIPv4(){
        StringBuilder ipv4 = new StringBuilder();
        // Converte os bits binários de 8 em 8 para decimal e forma a string IPv4.
        for (int i = 0; i < 32; i+=8){
            String octetoBits = this.ipAddressBits.substring(i, i + 8);
            int decimal = Integer.parseInt(octetoBits, 2);
            ipv4.append(decimal);

            // Adiciona pontos entre os octetos.
            if (i < 24){
                ipv4.append(".");
            }
        }
        return ipv4.toString();
    }

    /**
     * Verifica se o endereço IP atual é uma máscara de rede válida (sequência de 1s seguida por 0s).
     * @return true se for uma máscara válida, false caso contrário.
     */
    public boolean isMask() {
        // Encontra o índice do primeiro '0' na representação binária.
        int indicePrimeiro = this.ipAddressBits.indexOf('0');

        // Se não há '0's (todos '1's), é uma máscara válida.
        if (indicePrimeiro == -1){
            return true;
        }

        // Verifica se todos os bits após o primeiro '0' são também '0's.
        for (int i = indicePrimeiro; i < 32; i++){
            if (this.ipAddressBits.charAt(i) == '1') {
                return false; // '1' encontrado após um '0' invalida a máscara.
            }
        }
        return true; // É uma máscara válida.
    }

    /**
     * Retorna o número de bits '1's contíguos no início da máscara de rede.
     * @return O número de bits da máscara (0-32) ou -1 se não for uma máscara válida.
     */
    public int maskBits() {
        // Retorna -1 se o IP não for uma máscara válida.
        if (!isMask()){
            return -1;
        }

        int contador = 0;
        // Conta os '1's contínuos no início da string de bits.
        for (char bit : this.ipAddressBits.toCharArray()){
            if (bit == '1'){
                contador++;
            } else {
                break; // Para ao encontrar o primeiro '0'.
            }
        }
        return contador;
    }
}