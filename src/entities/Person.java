package entities;

import services.RegistrationManager;
import services.ReportsManager;

import java.time.LocalDate;

public abstract class Person {
    ReportsManager reportsManager = new ReportsManager();
    protected int id;
    private String name;
    private String phone;
    private LocalDate birthday;
    private String cpf;

    public Person() {
        super();
    }

    public Person(int id, String name, String phone, LocalDate birthday, String cpf) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.cpf = cpf;
    }

    public Person(int id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //Gerador de id automatico

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name +
                ", phone='" + phone +
                ", birthday=" + birthday +
                ", cpf='" + cpf;
    }
}
