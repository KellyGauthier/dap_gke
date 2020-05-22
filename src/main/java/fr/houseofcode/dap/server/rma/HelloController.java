package fr.houseofcode.dap.server.rma;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO GKE by Djer |Audit Code| Prends en comtpe les remarques de CheckStyle !

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Welcome Home";
    }
}
