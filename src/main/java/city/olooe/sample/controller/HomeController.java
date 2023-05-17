package city.olooe.sample.controller;

import city.olooe.sample.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "myName");
        model.addAttribute("time", homeService.getTime());
        return "index";
    }

    @GetMapping("list")
    public void getList(Model model) {
        model.addAttribute("list", homeService.getList());
    }

    @PostMapping("list")
    public String create(String name, Integer score){
        log.info("{}", name);
        log.info("{}", score);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("score", score);

        homeService.create(map);
        return "redirect:/list";
    }

    @GetMapping("remove")
    public String rename(Integer no){
        log.info("{}", no);
        homeService.remove(no);
        return "redirect:/list";
    }
}
