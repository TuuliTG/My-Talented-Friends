package projekti.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    @GetMapping("*")
    public String helloWorld(Model model) {
        model.addAttribute("message", "World!");
        return "index";
    }
    
    /*
    @ResponseBody
    @GetMapping("/perform_logout")
    public String logout() {
        return "logged out";
    }
*/
}
