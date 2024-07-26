package com.example.picpaysimplificado.authorization;

public record Authorization(

        String status,
        AuthorizationData data //the API provided for the test returns a body
) {
    public boolean isAuthorized(){
        return data.authorization();
    }
}
