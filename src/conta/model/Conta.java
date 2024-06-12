package conta.model;

public class Conta {

    private int numero;
    private int tipo;
    private String titular;
    private float saldo;
    private int agencia;

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public boolean sacar(float valor){
        if(getSaldo() < valor){
            System.out.println("Saldo insuficiente");
            return false;
        }
        setSaldo(getSaldo() - valor);
        visualizar();
        return true;
    }

    public void depositar(float valor){
        setSaldo(getSaldo()+valor);
        visualizar();
    }

    public void visualizar() {

        String tipo = "";

        switch(this.tipo) {
            case 1:
                tipo = "Conta Corrente";
                break;
            case 2:
                tipo = "Conta Poupança";
                break;
        }

        System.out.println("\n\n***********************************************************");
        System.out.println("Dados da Conta:");
        System.out.println("***********************************************************");
        System.out.println("Numero da Conta: " + numero);
        System.out.println("Agência: " + agencia);
        System.out.println("Tipo da Conta: " + tipo);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);

    }


    public Conta(int numero, int tipo, String titular, float saldo, int agencia) {
        this.numero = numero;
        this.tipo = tipo;
        this.titular = titular;
        this.saldo = saldo;
        this.agencia = agencia;
    }
}
