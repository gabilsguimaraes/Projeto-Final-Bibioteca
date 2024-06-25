package br.edu.up.projetofinal.exceptions;

public class LivroNotFoundException extends Exception{

    public LivroNotFoundException(String message) {
        super(message);
    }

    public LivroNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
