package ma.fstm.ilisi2.libraryapp.model.services;


import ma.fstm.ilisi2.libraryapp.model.bo.Livre;

public class ServiceLivre {
    public boolean validate(Livre L){
        return L.getIsbn().length() >= 5;
    }
}