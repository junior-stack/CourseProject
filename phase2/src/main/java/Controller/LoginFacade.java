package Controller;

import Dao.UserDao;
import Presenter.LoginPresenter;
import UseCase.UserAccountManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * This class represents an LoginFacade.
 *
 * @author Jun Xing
 * @version 1.0
 */
@Controller
public class LoginFacade {

  @RequestMapping("/login")
  public String login(Model model){
    model.addAttribute("END", "login");
    return "index";
  }

  @RequestMapping("/test")
  public String test(Model model){
    model.addAttribute("END", "test");
    return "index";
  }

  @RequestMapping("/hello")
  public String hello(Model model){
    model.addAttribute("END", "hello");
    return "index";
  }

}
