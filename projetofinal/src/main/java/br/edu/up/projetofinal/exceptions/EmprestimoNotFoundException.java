package br.edu.up.projetofinal.exceptions;

public class EmprestimoNotFoundException extends Exception{

    public EmprestimoNotFoundException(String message) {
        super(message);
    }

    public EmprestimoNotFoundException(String message, Throwable cause) { super(message, cause); }

}
