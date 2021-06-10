package com.kiran.crud.controller;

import com.kiran.crud.users.User;
import com.kiran.crud.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    //This is instantiazed so that the received Input Values will be saved to DB.
    @Autowired
    private UserRepository userRepository;

    //Default Page Mapping
    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    //Login Page mapping

    @GetMapping("/login")
    public String userLogin(){
        return "login";
    }




    //User_Registration page Mapping

    /*
    Spring Bootのプロジェクトでは
コントローラーからビュー側へデータの値を渡す処理を行う場合、
Spring FrameworkのModelクラスもしくはModel And Viewクラスを利用して実現させます
     */

    //The Link associated with mapping is defined in Thymeleaf.
    @GetMapping("/register")
    public String showRegistrationForm(Model model){

        /*
        addAttribute()メソッドでView側へ渡すオブジェクトのデータを
         第一引数にテンプレートから参照する変数名、
        第二引数にオブジェクト名として格納している。
        */

        //This userrr should be same with register_form userrr
   model.addAttribute("user", new User());
    return "register_form";
    }


    //Post_mapping after the user Input

    //In thymeleaf, the inputs using method post is mapped in "process_register" by thymeleaf
    // and send as object "user"

    @PostMapping("/process_register")
    public String process_register(User user){
        //      "user" is sent by thymeleaf specified in /process_register

        //Password Encoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //Save to DB
        userRepository.save(user);

        return "register_success";

    }


}
