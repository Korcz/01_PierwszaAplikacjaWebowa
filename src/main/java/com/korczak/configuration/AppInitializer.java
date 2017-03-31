package com.korczak.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Created by Korczi on 2017-01-27.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //----------------------------------------------------------------------------------
    //w tej klasie odbedzie sie pierwsza czesc konfiguracji multiparta
    //MULTIPART SECTION
    private static final String LOCATION = "C:/mytemp/"; //katalog tymczasowy dla przesylanych plikow o rozmiarze wiekszym niz w zmiennej FILE_SIZE_THRESHOLD
    //kiedy w tej zmiennej dasz 0 to kazdy plik jest tam na chwile umieszczany
    private static final long MAX_FILE_SIZE = 5242880; //max rozmiar przesylanego pliku, wartosc w bajtach
    private static final long MAX_REQUEST_SIZE = 20971520; //max rozmiar wszystkich przesylanych plikow w ramach multiparta
    private static final int FILE_SIZE_THRESHOLD = 0;

    //powyzsze parametry zostana wykorzystane w konfiguracji multiparta ponizej
    //ponizsza metoda wykorzystuje obiekt ktory zostal zainicjalizowany parametrami podanymi powyzej
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    //tworzymy obiekt ktory konfigurue multiparta na podstawie ustalonych wczesniej parametrow
    private MultipartConfigElement getMultipartConfigElement()
    {
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
}
