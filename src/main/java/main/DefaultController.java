package main;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class DefaultController {

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public static String getDate(){
        return new Date().toString();
    }
}
