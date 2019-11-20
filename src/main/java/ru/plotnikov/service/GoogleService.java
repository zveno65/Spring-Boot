//НЕУДАВШИЙСЯ ЭКСПЕРИМЕНТ С SCRIBEJAVA.
//package ru.plotnikov.service;
//
//import com.github.scribejava.apis.GoogleApi20;
//import com.github.scribejava.core.builder.ServiceBuilder;
//import com.github.scribejava.core.oauth.OAuth20Service;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Random;
//
//@Component
//public class GoogleService {
//
//    private OAuth20Service service;
//    private final String API_KEY = "361132149301-9oa14493cm120d687en6kfh7ji1fbkl2.apps.googleusercontent.com";
//    private final String API_SECRET = "HCEDrHZMm9q2yEsoM87-CKgM";
//    private final String CALLBACK = "http://localhost:8080/auth/google";
//
//    @PostConstruct
//    private void init(){
//        this.service  = new ServiceBuilder(API_KEY)
//                .apiSecret(API_SECRET)
//                .scope("profile") // replace with desired scope
//                //.state("secret" + new Random().nextInt(999_999))
//                .callback(CALLBACK)
//                .build(GoogleApi20.instance());
//    }
//
//
//    public OAuth20Service getService() {
//        return service;
//    }
//}