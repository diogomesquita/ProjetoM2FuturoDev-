package entities;

import entities.enums.StudentStatus;
import exceptions.NotWordException;
import exceptions.OnlyNumbersException;
import exceptions.PedagogicalServiceStatusException;
import utils.IdGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Student extends Person {
    Scanner sc = new Scanner(System.in);

    private StudentStatus status;
    private Double selectionGrade;
    private int pedagogicalServiceCounter;

    public Student(){
        super();
    }

    public Student(int id, String name, String phone, LocalDate birthday, String cpf, StudentStatus status, Double selectionGrade) {
        super(id, name, phone, birthday, cpf);
        this.status = status;
        this.selectionGrade = selectionGrade;
    }

    public Student(int id, String name, String cpf, int pedagogicalServiceCounter) {
        super(id, name, cpf);
        this.pedagogicalServiceCounter = pedagogicalServiceCounter;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public Double getSelectionGrade() {
        return selectionGrade;
    }

    public Integer getPedagogicalServiceCounter() {
        return pedagogicalServiceCounter;
    }

    public void setPedagogicalServiceCounter(int pedagogicalServiceCounter) {
        this.pedagogicalServiceCounter = pedagogicalServiceCounter;
    }

    public void increasePedagogicalServiceCounter(List<Student> students, int idStudent){
        for (Student s : students){
            if (s.getId() == idStudent){
               int aux = s.getPedagogicalServiceCounter() + 1;
                s.setPedagogicalServiceCounter(aux);
            }
        }
    }

    public void newStudent(List<Student> students){
        try {
            System.out.println("TELA DE CADASTRO DE ALUNOS: ");
            System.out.println("nome: ");
            String name = sc.nextLine();

            if (name.matches(".*[0-9]{1,}.*")){
                throw new NotWordException("FORMATO DE NOME INVÁLIDO!\nInformação esperada: sequência de letras\n");
            }

            System.out.println("telefone: ");
            String phone = sc.nextLine();

            if (phone.matches(".*[a-zA-Z]{1,}.*")){
                throw new OnlyNumbersException("FORMATO DE TELEFONE INVÁLIDO!\nInformação experada: Sequencia de números\n");
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

            System.out.println("Nota no Processo Seletivo: ");
            Double grade = sc.nextDouble();
            sc.nextLine();
            System.out.println("Status: ");
            String status = sc.nextLine().trim().toUpperCase();

            if(status.equals("ATENDIMENTO_PEDAGÓGICO") || status.equals("ATENDIMENTO_PEDAGOGICO") || status.equals("ATENDIMENTO PEDAGOGICO") || status.equals("ATENDIMENTO PEDAGÓGICO")){
                status = "ATENDIMENTO_PEDAGÓGICO";
            } else {
                if (!status.equals("ATIVO") && !status.equals("INATIVO") && !status.equals("IRREGULAR")){
                    throw new PedagogicalServiceStatusException("O FORMATO DE STATUS INFORMADO É INVÁLIDO!\nInformação esperada: Ativo, Inativo, Irregular ou Atendimento Pedagógico\n");
                }
            }

            IdGenerator.increaseIds();
            Student student = new Student(IdGenerator.getIds(), name, phone, birthday, ssn, StudentStatus.valueOf(status), grade);
            students.add(student);
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
            System.out.println("Informação esperada: Ativo, Inativo, Irregular ou Atendimento Pedagógico");
            System.out.println();
        }
        catch (InputMismatchException e) {
            System.out.println("FORMATO DE NOTA INVÁLIDO!\nInformação esperada: valor numérico\n");
        }
        catch (PedagogicalServiceStatusException e){
            System.out.println(e.getMessage());
        }
        catch (NotWordException e) {
            System.out.println(e.getMessage());
        }
        catch (OnlyNumbersException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void pedagodicalServiceStatus(List<Student> students, int idStudent, StudentStatus status){
        if (students != null){
            for (Student s : students){
                if (s.getId() == idStudent){
                    s.setStatus(status);
                    System.out.println("Status de aluno alerado para " + status + ".\n");
                }
            }
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "status=" + status +
                ", selectionGrade=" + selectionGrade +
                ", pedagogicalServiceCounter=" + pedagogicalServiceCounter +
                "} " + super.toString();
    }
}
