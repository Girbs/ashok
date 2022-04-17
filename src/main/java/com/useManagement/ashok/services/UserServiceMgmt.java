package com.useManagement.ashok.services;

import com.useManagement.ashok.bindings.LoginForm;
import com.useManagement.ashok.bindings.UserForm;
import com.useManagement.ashok.entity.Users;

import java.util.Map;

public interface UserServiceMgmt {

    // login screen related methods
    public String loginCheck(LoginForm loginForm);
    // Registration screen related methods
    public Users emailCheck(String emailId);

    public Map<Integer, String> loadCountries();

    public Map<Integer, String> loadStates(Integer countryId);

    public Map<Integer, String> loadCities(Integer stateId);

    public String saveUser(UserForm userForm);

    // unlock account screen related methods
   // public String unlockAcc(UnlockAccForm unlockAccForm);

    // forgot pwd screen related methods
    public String forgotPwd(String emailId);
}
