package com.korczak.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Korczi on 2017-03-03.
 */
public class Person
{
    //imie, nazwisko, wiek, lista nationalisties, gender, local date hireDate, int insurancePerido
    @NotEmpty(message = "Name cannot be empty")
    @Length(min = 2, max = 10, message = "Name should have length between 2 and 10")







































































































































































    @Pattern(regexp = "[A-Z]+", message = "Name should have onlu upper cases")
    private String name;
    @Pattern(regexp = "[A-Z][a-z]+", message = "Bad surname")
    private String surname;
    @Range(min = 18, max = 65, message = "Bad age")
    private int age;
    @Size(min = 2, message = "Bad list")
    private List<Nationality> nation;
    private Gender gender;
    private LocalDate hireDate;
    private int insurancePeriod;
    //dodajemy dwie zmienne ktore beda odpowiadaly za multipart
    //pierwsza zmienna to obiekt klasy MultipartFile, ktory bedzie przechowywal pobrane dane
    //a druga to napis, ktory bedzie przechowywal sciezke do pobranego zasobu, zeby ja np pozniej zapisac w bazie danych
    private MultipartFile multipartFile;
    private String filePath;

    public Person()
    {
    }

    public Person(String name, String surname, int age, List<Nationality> nation, Gender gender, LocalDate hireDate, int insurancePeriod)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.nation = nation;
        this.gender = gender;
        this.hireDate = hireDate;
        this.insurancePeriod = insurancePeriod;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public List<Nationality> getNation()
    {
        return nation;
    }

    public void setNation(List<Nationality> nation)
    {
        this.nation = nation;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public LocalDate getHireDate()
    {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate)
    {
        this.hireDate = hireDate;
    }

    public int getInsurancePeriod()
    {
        return insurancePeriod;
    }

    public void setInsurancePeriod(int insurancePeriod)
    {
        this.insurancePeriod = insurancePeriod;
    }

    public MultipartFile getMultipartFile()
    {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile)
    {
        this.multipartFile = multipartFile;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", nation=" + nation +
                ", gender=" + gender +
                ", hireDate=" + hireDate +
                ", insurancePeriod=" + insurancePeriod +
                '}';
    }
}
