
package br.com.pilates.academiaPilates.models.enums;
/**
 *
 * @author junior
 */

public enum Perfil {
    SYSTEM_ADMIN(1,"ROLE_SYSTEM_ADMIN"),
    ADMINISTRADOR(2,"ROLE_ADMINISTRADOR"),
    SUPERVISOR(3,"ROLE_PROFESSOR"),
    OPERADOR(4,"ROLE_OPERADOR"),
    CLIENTE(5,"ROLE_CLIENTE");
    
    private int cod;
    private String descricao;

    private Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }   
    
    
    public static Perfil toEnum(Integer cod){
        
        if( cod == null){
            return null;
        }
        
        for(Perfil x : Perfil.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }
    
}
