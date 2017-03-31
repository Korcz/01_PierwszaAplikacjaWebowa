package com.korczak.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Created by Korczi on 2017-01-27.
 */

@Configuration
@ComponentScan("com.korczak")
@EnableWebMvc //dzieki niej dzialaja inne adotacje web mvc
public class AppConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware
{
    //=================================THYMELEAF============================================
    private ApplicationContext applicationContext;

    //METODA Z ApplicationContextAware
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) /*throws BeansException*/ {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ViewResolver viewResolver()
    {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public TemplateEngine templateEngine()
    {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true); //w thymeleaf uzywasz specjalnej skladni tzw
        //Spring Expression Langueage, ktory pozwala nam odwolywac sie do zmiennych w
        //aplikacja java z poziomu html
        engine.addDialect(new Java8TimeDialect()); //obsluga daty java 8, do zestawu klas thymeleaf
        //ktore uzywamy w aplikcaji dodany zostanie obiekt temporals z poziomu ktorego bedziemy
        //zarzadzac data java 8
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    private ITemplateResolver templateResolver()
    {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext); //odwolanie sie do kontwkstu aplikacji
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    //w pliku html umieszczasz tab link, ktory ma atrybut rel rownystylesheet, pozniej podajesz sciezke do pliku
    //css, ta sciezka jest przechywtywana przez ResourceHandlerRegistry i mapowana na fizyczny katalog w folderze twojej aplikacji
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    //=========================================================================================
    //===================================MULTIPART=============================================
    //=========================================================================================
    @Bean
    public StandardServletMultipartResolver multipartResolver()
    {
        return new StandardServletMultipartResolver();
    }
}
