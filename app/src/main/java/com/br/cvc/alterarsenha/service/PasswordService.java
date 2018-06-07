package com.br.cvc.alterarsenha.service;

import com.br.cvc.alterarsenha.api.PasswordApi;
import com.br.cvc.alterarsenha.api.model.PasswordModelApi;

public class PasswordService {

    public boolean Reset(String UserId, String newPassword) {

        PasswordModelApi obj = new PasswordModelApi();
        obj.setUserId(UserId);
        obj.setNewPassword(newPassword);

        PasswordApi api = new PasswordApi();
        return api.Reset(obj);
    }
}
