package io.github.lucasmelo59.exception;

public class UserExceception extends Exception{
private Long id;


    public void usuario(Long id) {
    if ( id == null){
        throw new NullPointerException("Aluno nulo");
    }
    }


}
