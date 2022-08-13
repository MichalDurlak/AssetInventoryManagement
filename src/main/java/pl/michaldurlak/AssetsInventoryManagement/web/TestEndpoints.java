package pl.michaldurlak.AssetsInventoryManagement.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndpoints {

    //login site - at the moment default
    //logout site - at the moment default

    @GetMapping("/admin")
    public String getTest1(){
        return "admin";
    }

    @GetMapping("/read")
    public String getTest2(){
        return "read";
    }

    @GetMapping("/readwrite")
    public String getTest3(){
        return "readwrite";
    }
    @GetMapping("/all")
    public String getTest4(){
        return "all";
    }





}
