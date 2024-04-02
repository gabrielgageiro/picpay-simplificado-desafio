package com.example.picpaysimplificado.authorization;

public record Authorization(

        String _id, //somente pq o crudcrud retonrna o id junto
        String message
) {
    public boolean isAuthorized(){
        return message.equals("autorizado");
    }
}
