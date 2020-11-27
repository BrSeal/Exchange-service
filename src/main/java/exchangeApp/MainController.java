package exchangeApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = {"/", "/home"})
    public String home(){
        return "home";
    }

    @GetMapping("/exchange")
    public String exchange(){
        return "exchange";
    }

    @GetMapping("/stats")
    public String stats(){
        return "stats";
    }

    @GetMapping("/api")
    public String api(){
        return "redirect:swagger-ui/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
