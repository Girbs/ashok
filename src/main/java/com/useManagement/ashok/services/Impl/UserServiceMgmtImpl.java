package com.useManagement.ashok.services.Impl;

import com.useManagement.ashok.bindings.LoginForm;
import com.useManagement.ashok.bindings.UserForm;
import com.useManagement.ashok.entity.Cities;
import com.useManagement.ashok.entity.Countries;
import com.useManagement.ashok.entity.States;
import com.useManagement.ashok.repositories.CityRepo;
import com.useManagement.ashok.repositories.CountryRepo;
import com.useManagement.ashok.repositories.StateRepo;
import com.useManagement.ashok.utils.EmailController;
import com.useManagement.ashok.entity.Users;
import com.useManagement.ashok.repositories.UserRepo;
import com.useManagement.ashok.services.UserServiceMgmt;
import com.useManagement.ashok.utils.PasswordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class UserServiceMgmtImpl implements UserServiceMgmt {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CountryRepo countryRepo;


    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private EmailController emailController;

    @Override
    public String loginCheck(LoginForm loginForm) {
    String message ="";
       String validCredential ="success";
      Users u = userRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if(u==null){
           message= "invalidCredential";
        }else{
            message="Success";
        }
      return message;
    }


//    private String readForGo(Users user){
//        String body ="";
//        StringBuffer buffer = new StringBuffer();
//        Path filePath = Paths.get("registrationConfirmationEmailTemplate.txt");
//        try(Stream<String> stream = Files.lines(filePath)){
//            stream.forEach(line ->{
//                buffer.append(line);
//            });
//
//          body = buffer.toString();
//          body = body.replace("{firstName}", user.getFirstName());
//          body = body.replace("{lastName}", user.getFirstName());
//          body = body.replace("{password}", user.getFirstName());
//          body = body.replace("{email}", user.getFirstName());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return body;
//    }

    @Override
    public Users emailCheck(String emailId) {
      return userRepo.findByEmail(emailId);
    }

    @Override
    public Map<Integer, String> loadCities(Integer stateId){
        List<Cities> allCities= cityRepo.findByStateId(stateId);
        Map<Integer, String> cityMap = new HashMap<>();
        allCities.forEach(city->{
            cityMap.put(city.getId(), city.getCityName());
        });
        return cityMap;
    }

    @Override
    public Map<Integer, String> loadStates(Integer countryId) {

        List<States> allState= stateRepo.findByCountryId(countryId);
        Map<Integer, String> stateMap = new HashMap<>();
        allState.forEach(state->{
            stateMap.put(state.getId(), state.getStateName());
        });
        return stateMap;
    }

    @Override
    public Map<Integer, String> loadCountries () {
        List<Countries> allCountry = countryRepo.findAll();
        Map<Integer, String> countryM = new HashMap<>();
        allCountry.forEach(country->{
            countryM.put(country.getId(), country.getCountryName());
        });
        return countryM;
    }



    @Override
    public String saveUser(UserForm userForm) {
        String message;
        if(emailCheck(userForm.getEmail())==null){
           return "A user email is already existed with this email";
        }
        Users users = new Users();
        users.setStatus("LOCKED");
        String password = passwordGenerator.randomPassword();
        System.out.print(password);
        users.setPassword(password);
        BeanUtils.copyProperties(userForm, users);
        try{
            userRepo.save(users);
            emailController.newAccountConfirmation(users.getEmail(), users.getFirstName(), users.getLastName(), password );
        }catch(Exception e){
            return "The following error prevent your record to be saved:"+e;
        }
        return "User saved Succesfully";
    }

    @Override
    public String forgotPwd(String emailId) {
      if (emailCheck(emailId)==null) {
           return "Email invalid";
      }
          Users user = emailCheck(emailId);
          String tempPassword = passwordGenerator.randomPassword();
          user.setPassword(tempPassword);

          try{
              userRepo.save(user);
              emailController.sentTemporaryPassword(emailId, tempPassword );
          }catch(Exception e){
              return "The following error prevent your record to be saved:"+e;
          }

      return "A Temporary Password has been sent to your email";
    }
}
