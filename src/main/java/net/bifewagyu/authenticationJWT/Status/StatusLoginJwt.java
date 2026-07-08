package net.bifewagyu.authenticationJWT.Status;

public enum StatusLoginJwt {
    SUCESSO("ENTRADA CONCLUIDA COM SUCESSO"),
    ERRO("ERRO ACESSO NÃO AUTORIZADO")

    ;

    public String getDescription() {
        return description;
    }

    private String description;

    StatusLoginJwt(String description) {
        this.description = description;
    }


}
