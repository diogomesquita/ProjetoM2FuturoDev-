package services;

import entities.Pedagogue;
import entities.Person;
import entities.Student;
import entities.Teacher;
import entities.enums.StudentStatus;
import exceptions.IdNotFoundException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RegistrationManager extends Person{

    //Funções utilitárias
    Scanner sc = new Scanner(System.in);

    //Listas - Armazenamento de Dados do programa
    List<Student> students = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    List<Pedagogue> pedagogues = new ArrayList<>();

    //Objetos
    Student student = new Student();
    Teacher teacher = new Teacher();
    Pedagogue pedagogue = new Pedagogue();
    ReportsManager reportsmanager = new ReportsManager();

    public void newRegistration(){

        //Métodos chamados no menu primário da Aplicação
        String opt = "";
        while (!opt.equals("4")) {
            System.out.println("\n\n");
            System.out.println("* * MENU DE REGISTRO DE DADOS DO SISTEMA * *");
            System.out.println("- Informe o número da opção desejada:\n1-Novo Aluno\n2-Novo Professor\n3-Novo Pedagogo\n4-Voltar para o menu anterior:\n5-Sair");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    student.newStudent(students);
                    break;
                case "2":
                    teacher.newTeacher(teachers);
                    break;
                case "3":
                    pedagogue.newPedagogue(pedagogues);
                    break;
                case "4":
                    break;
                case "5":
                    String logout;
                    do {
                        System.out.println("Confirma Sair? (s/n)");
                        logout = sc.nextLine().toLowerCase().trim();
                        if (logout.charAt(0) == 's') {
                            System.exit(0);
                        } else if (logout.charAt(0) != 'n') {
                            System.out.println();
                            System.out.println("Opção inválida!");
                            System.out.println("Digite S ou N: (s/n)");
                            System.out.println();
                        }
                        opt = " ";
                    }while(logout.charAt(0) != 'n');
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida!");
                    System.out.println("Informação esperada: um número do menu.");
                    System.out.println();
            }
        }
    }

    public void changeRegister(){
        Scanner sc = new Scanner(System.in);
        String opt = "";

        while (!opt.equals("2")) {
            System.out.println("\n\n");
            System.out.println("* * MENU DE ALTERAÇÃO DE DADOS DO SISTEMA * *");
            System.out.println("Informe o número da opção que desejada:\n1-Alterar Stauts do Aluno\n2-Voltar para o menu anterior:\n3-Sair");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    if(!students.isEmpty()){
                        try {
                            System.out.println("Informe o id do Aluno: ");
                            int idStudent = sc.nextInt();
                            sc.nextLine();
                            Boolean isOk = false;
                            for (Student x : students) {
                                if (x.getId() == idStudent) {
                                    isOk = true;
                                }
                            }
                            if (isOk == true) {
                                String status;
                                do {
                                    System.out.println("* Informe o número do estado do aluno: (1,2,3 ou 4)* \n1-Ativo\n2-Inativo\n3-Irregular\n4-Atentimento Pedagógico:");
                                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * *");
                                    status = sc.nextLine();
                                    switch (status) {
                                        case "1":
                                            Student.pedagodicalServiceStatus(students, idStudent, StudentStatus.ATIVO);
                                            break;
                                        case "2":
                                            Student.pedagodicalServiceStatus(students, idStudent, StudentStatus.INATIVO);
                                            break;
                                        case "3":
                                            Student.pedagodicalServiceStatus(students, idStudent, StudentStatus.IRREGULAR);
                                            break;
                                        case "4":
                                            Student.pedagodicalServiceStatus(students, idStudent, StudentStatus.ATENDIMENTO_PEDAGÓGICO);
                                            break;
                                        default:
                                            System.out.println();
                                            System.out.println("Opção inválida!");
                                            System.out.println("Informação esperada: um número do menu.");
                                            System.out.println();
                                    }

                                }while (!status.equals("1") && !status.equals("2") && !status.equals("3") && !status.equals("4"));
                            } else {
                                throw new IdNotFoundException("Id não Encontrado!\nVerifique o ID informado.");
                            }
                        }
                        catch (IdNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        catch (InputMismatchException e) {
                            System.out.println("ID não encontrado");
                            System.out.println("Informação esperada: Número de ID");
                        }
                    } else {
                        System.out.println("Opção indiponível!");
                        System.out.println("Não há dados de alunos cadastrados no Sistema.\n");
                    }

                    break;
                case "2":
                    break;
                case "3":
                    String logout;
                    do {
                        System.out.println("Confirma Sair? (s/n)");
                        logout = sc.nextLine().toLowerCase().trim();
                        if (logout.charAt(0) == 's') {
                            System.exit(0);
                        } else if (logout.charAt(0) != 'n') {
                            System.out.println("Opção inválida!");
                            System.out.println("Digite S ou N: (s/n)");
                        }
                        opt = " ";
                    }while(logout.charAt(0) != 'n');
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida!");
                    System.out.println("Informação esperada: um número do menu.");
                    System.out.println();
            }
        }
    }

    public void pedagogicalService(){
        String opt = "";
        while (!opt.equals("3")) {
            System.out.println("\n\n");
            System.out.println("* * MENU - AGENDAMENTO DE ATENDIMENTO PEDAGÓGICO * *");
            System.out.println("- Selecione o número da opção desejada:\n1-Agendar Atendimento:\n2-Finalizar Atendimento Pedagógico\n3-Voltar ao menu anterior:\n4-Sair");
            System.out.println("* * * * * * * * * * * *  * * * * * * * * * * * * * *");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    if (students.isEmpty() == false && pedagogues.isEmpty() == false){
                        System.out.println("Informe a id do aluno: ");
                        int idStudent = sc.nextInt();
                        System.out.println("Informe a id do pedagogo: ");
                        int idPedagogue = sc.nextInt();

                        Boolean isOk = false;
                        Boolean isOkay = false;

                        for (Student s : students){
                            if(s.getId() == idStudent){
                                isOk = true;
                            }
                        }
                        for (Pedagogue p : pedagogues) {
                            if (p.getId() == idPedagogue){
                                isOkay = true;
                            }
                        }
                        if (isOk == false || isOkay == false){
                            System.out.println("AGENDAMENTO NÃO REALIZADO!\n");
                            sc.nextLine();
                        } else {
                            student.increasePedagogicalServiceCounter(students, idStudent);
                            pedagogue.increasePedagogicalServiceCounter(pedagogues, idPedagogue);
                            Student.pedagodicalServiceStatus(students, idStudent, StudentStatus.ATENDIMENTO_PEDAGÓGICO);
                            System.out.println("ATENDIMENTO AGENDADO COM SUCESSO!\n");
                            sc.nextLine();
                        }
                    }
                    else {
                        System.out.println("Não é possível Agendar Atendimentos Pedagógicos");
                        if(students.isEmpty()){
                            System.out.println("Não há alunos cadastrados");
                        }
                        if(pedagogues.isEmpty()){
                            System.out.println("Não há pedagogos cadastrados");
                        }
                        System.out.println();
                    }
                    break;
                case "2":
                    try {
                        if (students.isEmpty() == false) {
                            System.out.println("Informe o id do Aluno: ");
                            int idStudent = sc.nextInt();
                            sc.nextLine();
                            Boolean isOk = false;
                            for (Student s : students) {
                                if (s.getId() == idStudent) {
                                    isOk = true;
                                }
                            }
                            if (isOk == true) {
                                String status;
                                do {
                                    System.out.println("* Informe o número do Novo Estado do aluno: (1, 2 ou 3) *\n1-Ativo\n2-Inativo\n3-Irregular");
                                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
                                    status = sc.nextLine();
                                    switch (status) {
                                        case "1":
                                            Student.pedagodicalServiceStatus(students, idStudent, StudentStatus.ATIVO);
                                            break;
                                        case "2":
                                            Student.pedagodicalServiceStatus(students, idStudent, StudentStatus.INATIVO);
                                            break;
                                        case "3":
                                            Student.pedagodicalServiceStatus(students, idStudent, StudentStatus.IRREGULAR);
                                            break;
                                        default:
                                            System.out.println();
                                            System.out.println("Opção inválida!");
                                            System.out.println("Informação esperada: um número do menu.");
                                            System.out.println();
                                    }
                                }while (!status.equals("1") && !status.equals("2") && !status.equals("3"));
                            }
                            else {
                                System.out.println("Aluno não encontrado!\nVerifique o ID Informado.");
                                System.out.println("AGENDAMENTO NÃO REALIZADO!\n");
                            }
                        } else {
                            System.out.println("Opção indiponível!");
                            System.out.println("Não há dados de alunos cadastrados no Sistema.\n");
                        }
                    } catch (InputMismatchException e){
                        System.out.println("ID não encontrado");
                        System.out.println("Informação esperada: Número de ID");
                    }

                    break;
                case "3":
                    break;
                case "4":
                    String logout;
                    do {
                        System.out.println("Confirma Sair? (s/n)");
                        logout = sc.nextLine().toLowerCase().trim();
                        if (logout.charAt(0) == 's') {
                            System.exit(0);
                        } else if (logout.charAt(0) != 'n') {
                            System.out.println();
                            System.out.println("Opção inválida!");
                            System.out.println("Digite S ou N: (s/n)");
                            System.out.println();
                        }
                        opt = " ";
                    }while(logout.charAt(0) != 'n');
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida!");
                    System.out.println("Informação esperada: um número do menu.");
                    System.out.println();
            }
        }
    }
    public void registry(){
        reportsmanager.reportManager(this.students, this.teachers, this.pedagogues);
    }

    public void resetLists(){
        this.students.clear();
        this.teachers.clear();
        this.pedagogues.clear();
    }
}
