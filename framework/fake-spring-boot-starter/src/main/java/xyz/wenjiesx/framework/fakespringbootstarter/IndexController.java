package xyz.wenjiesx.framework.fakespringbootstarter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wenji
 * @Date 2020/4/16
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {

        System.out.println("success");
        return "success";
    }


}
