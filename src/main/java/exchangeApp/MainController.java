package exchangeApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = {"/", "/home"})
    public String home(){
        return "home";
    }

    @GetMapping("/exchangePage")
    public String exchange(){
        return "exchange";
    }

    @GetMapping("/statsPage")
    public String stats(){
        return "stats";
    }

    @GetMapping("/api")
    public String api(){
        return "swagger-ui";
    }
}
