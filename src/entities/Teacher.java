package entities;

import entities.enums.TeacherExperience;
import entities.enums.TeacherFormation;
import entities.enums.TeacherStatus;
import exceptions.*;
import utils.IdGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Teacher extends Person {
    Scanner sc = new Scanner(System.in);
    private TeacherFormation formation;
    private TeacherExperience experience;
    private TeacherStatus status;

    public Teacher(){
        super();
    }

    public Teacher(int id, String name, String phone, LocalDate birthday, String cpf, TeacherFormation formation, TeacherExperience experience, TeacherStatus status) {
        super(id, name, phone, birthday, cpf);
        this.formation = formation;
        this.experience = experience;
        this.status = status;
    }

    public TeacherFormation getFormation() {
        return formation;
    }

    public void setFormation(TeacherFormation formation) {
        this.formation = formation;
    }

    public TeacherExperience getExperience() {
        return experience;
    }

    public void setExperience(TeacherExperience experience) {
        this.experience = experience;
    }

    public TeacherStatus getStatus() {
        return status;
    }

    public void setStatus(TeacherStatus status) {
        this.status = status;
    }

    public void newTeacher(List<Teacher> teachers){
        try {
            System.out.println("TELA DE CADASTRO DE PROFESSORES:");
            System.out.println("nome: ");
            String name = sc.nextLine();

            if (name.matches(".*[0-9]{1,}.*")){
                throw new NotWordException("FORMATO DE NOME INVÁLIDO!\nInformação esperada: Sequência de letras");
            }

            System.out.println("telefone: ");
            String phone = sc.nextLine();

            if (phone.matches(".*[a-zA-Z]{1,}.*")){
                throw new OnlyNumbersException("FORMATO DE TELEFONE INVÁLIDO!\nInformação experada: Sequencia de números");
            }

            System.out.println("Data de nascimento: (dd/mm/aaaa)");
            String bday = sc.nextLine();
            DateTimeFormatter tmf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthday = LocalDate.parse(bday,tmf);
            System.out.println("CPF: ");
            String ssn = sc.nextLine();

            if (ssn.matches(".*[a-zA-Z]{1,}.*")){
                throw new OnlyNumbersException("FORMATO DE TELEFONE INVÁLIDO!\nInformação experada: Sequencia de números\n");
            }

            System.out.println("Formação: ");
            String formation = sc.nextLine().toUpperCase().trim();

            if(formation.equals("GRADUAÇÃO_INCOMPLETA") || formation.equals("GRADUACAO_INCOMPLETA") || formation.equals("GRADUAÇÃO INCOMPLETA") || formation.equals("GRADUACAO INCOMPLETA")){
                formation = "GRADUAÇÃO_INCOMPLETA";
            } else if (formation.equals("GRADUAÇÃO_COMPLETA") || formation.equals("GRADUACAO_COMPLETA") || formation.equals("GRADUAÇÃO COMPLETA") || formation.equals("GRADUACAO COMPLETA")){
                formation = "GRADUAÇÃO_COMPLETA";
            }
            else {
                if (!formation.equals("MESTRADO") && !formation.equals("DOUTORADO")) {
                    throw new FormationException("O FORMATO DE FORMAÇÃO INFORMADO É INVÁLIDO!\nInformação esperada: Graduação Completa, Graduação Incompleta, Mestrado ou Doutorado\n");
                }
            }

            System.out.println("Experiência: ");
            String experience = sc.nextLine().toUpperCase().trim();

            if(experience.equals("FRONT_END") || experience.equals("FRONT END") || experience.equals("FRONTEND") || experience.equals("FRONT-END")){
                experience = "FRONT_END";
            } else if (experience.equals("BACK_END") || experience.equals("BACK END") || experience.equals("BACKEND") || experience.equals("BACK-END")){
                experience = "BACK_END";
            } else if (experience.equals("FULL_STACK") || experience.equals("FULL STACK") || experience.equals("FULLSTACK") || experience.equals("FULL-STACK")) {
                experience = "FULL_STACK";
            } else {
                throw new ExperienceException("O FORMATO DE EXPERIÊNCIA INFORMADA É INVÁLIDA!\nInformação esperada: Front-End, Back-End ou Full-Stack\n");
            }

            System.out.println("Status: ");
            String status = sc.nextLine().trim().toUpperCase();

            if (!status.equals("ATIVO") && !status.equals("INATIVO") && !status.equals("IRREGULAR")){
                throw new TeacherStatusException("O FORMATO DE STATUS INFORMADO É INVÁLIDO!\nInformação esperada: Ativo ou Inativo\n");
            }

            IdGenerator.increaseIds();
            Teacher teacher = new Teacher(IdGenerator.getIds(), name, phone, birthday, ssn, TeacherFormation.valueOf(formation), TeacherExperience.valueOf(experience), TeacherStatus.valueOf(status));
            teachers.add(teacher);
        }
        catch (DateTimeParseException e){
            System.out.println();
            System.out.println("FORMATO DE DATA INVÁLIDO!");
            System.out.println("Informação esperada: data no formato (dd/mm/aaaa)");
            System.out.println();
        }
        catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("O ESTADO INFORMADO É INVÁLIDO");
            System.out.println("Informação esperada: Ativo ou Inativo");
            System.out.println();
        }
        catch (FormationException e) {
            System.out.println(e.getMessage());
        }
        catch (ExperienceException e) {
            System.out.println(e.getMessage());
        }
        catch (TeacherStatusException e) {
            System.out.println(e.getMessage());
        }
        catch (NotWordException e) {
            System.out.println(e.getMessage());
        }catch (OnlyNumbersException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "formation=" + formation +
                ", experience=" + experience +
                ", status=" + status +
                "} " + super.toString();
    }
}
