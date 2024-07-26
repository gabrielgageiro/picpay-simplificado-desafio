package com.example.picpaysimplificado.authorization;

public record Authorization(

        String status,
        boolean authorization
) {
    public boolean isAuthorized(){
        return authorization;
    }
}
