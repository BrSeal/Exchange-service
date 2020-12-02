package exchangeApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerToTestSecuritySettings {

    @GetMapping("/")
    public String allCanSee(){
        return "<h1>Public page</h1>";
    }

    @GetMapping("/admin")
    public String adminOnly(){
        return "<h1>Admin only stats overall</h1>";
    }

    @GetMapping("/user")
    public String usersCanSee(){
        return "<h1>User can see Stats rating</h1>";
    }
}
