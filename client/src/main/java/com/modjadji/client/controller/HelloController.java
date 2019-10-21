package com.modjadji.client.controller;

import com.modjadji.client.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String getHello(){
            String result = null;
        try{
            result = helloService.getHelloWithSSL();
        } catch (Exception e){
            System.out.println(e);
            System.out.println("Error retrieving data with getHelloWithNoSSL");
//
//            try {
//                result = helloService.getHelloWithSSL();
//            } catch (Exception f){
//                System.out.println(f);
//                System.out.println("Error retrieving data with getHelloWithSSL");
//            }
        }
        return result;
    }
}
