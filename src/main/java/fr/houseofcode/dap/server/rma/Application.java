package fr.houseofcode.dap.server.rma;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//TODO GKE by Djer |Audit Code| Prends en comtpe les remarques de CheckStyle !
//TODO GKE by Djer |Audit Code| PMD etait d�sactiv� sur ton projet ! (je te l'ai activ�)

/**
 * @author rma
 * 9 juil. 2019
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //TODO GKE by Djer |Spring| Cette m�thode sert � afficher le contenu du contenur IOC de Spring, elle n'est plus utile. Elle servait � v�rifier que Spring est bien configur�.
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
