package lailabd;

public class Funcionario {
    private int codFuncionario;
    private String funcao;
    private String turno;
    private String nome;
    private String cpf; // Alterado de int para String
    private String telefone;

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() { // Alterado para retornar String
        return cpf;
    }

    public void setCpf(String cpf) { // Alterado para receber String
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Funcionario(int codFuncionario, String funcao, String turno, String nome, String cpf, String telefone) {
        this.codFuncionario = codFuncionario;
        this.funcao = funcao;
        this.turno = turno;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Funcionario() {
    }

    @Override
    public String toString() {
        return "Funcionario [codFuncionario=" + codFuncionario + ", funcao=" + funcao + ", turno=" + turno + ", nome="
                + nome + ", cpf=" + cpf + ", telefone=" + telefone + "]";
    }
}
