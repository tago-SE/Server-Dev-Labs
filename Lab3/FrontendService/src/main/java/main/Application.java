package main;

import clients.ClientUtil;
import clients.MessageClient;
import clients.PostClient;
import clients.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.webapp.FacesServlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class Application  extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        List<Integer> list = new ArrayList<>();

        int[] a = new int[500];

        Collections.sort(list);
        Arrays.sort(a);

        
        Arrays.parallelSort(a);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(servlet, "*.jsf");
        return servletRegistrationBean;
    }



}
