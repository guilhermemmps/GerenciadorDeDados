public class Dados {
    private String uf;
    private String municipio;
    private String restricao;
    private int quantVeiculos;

    public Dados(String uf, String municipio, String restricao, int quantVeiculos) {
        this.uf = uf;
        this.municipio = municipio;
        this.restricao = restricao;
        this.quantVeiculos = quantVeiculos;
    }


    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getRestricao() {
        return restricao;
    }

    public void setRestricao(String restricao) {
        this.restricao = restricao;
    }

    public int getQuantVeiculos() {
        return quantVeiculos;
    }

    public void setQuantVeiculos(int quantVeiculos) {
        this.quantVeiculos = quantVeiculos;
    }

    public String toString() {
        return "\nUnidade Federativa: " + uf + " - Município: " + municipio + " - Tipo de restrição: "+ restricao + " - Quantidade de veículos: "+quantVeiculos;
    }


}
