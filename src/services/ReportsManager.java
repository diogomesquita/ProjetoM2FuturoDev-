package services;

import entities.Pedagogue;
import entities.Student;
import entities.Teacher;
import java.util.List;
import java.util.Scanner;
import entities.enums.StudentStatus;
import entities.enums.TeacherExperience;
import services.interfaces.ReportGenerator;


public class ReportsManager implements ReportGenerator {
    public void reportManager(List<Student> students, List<Teacher> teachers, List<Pedagogue> pedagogues){
        Scanner sc = new Scanner(System.in);

        String opt = "";
        while (!opt.equals("3")) {
            System.out.println("\n\n");
            System.out.println("* * * MENU DE RELATÓRIOS DO SISTEMA * * *");
            System.out.println("Informe o número do Relatório desejado:\n1-Pessoas Cadastradas\n2-Dados dos Atendimentos Pedagógicos\n3-Voltar para o menu anterior:\n4-Sair");
            System.out.println("* * * * * * * * * * * * * * * * * * * * *");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    personReportSubMenu(students, teachers, pedagogues);
                    break;
                case "2":
                    pedagogicalServiceData(students, pedagogues);
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

    public void personReportSubMenu(List<Student> students, List<Teacher> teachers, List<Pedagogue> pedagogues){
        Scanner sc = new Scanner(System.in);
        String optsm = "";
        do {
            System.out.println("\n* * Informe o número do Relatório desejado: * *");
            System.out.println("1-Alunos:\n2-Professores\n3-Funcionários\n4-Todos\n5-Voltar ao menu anterior\n6-Sair");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * * *");
            optsm = sc.nextLine();
            switch (optsm){
                case "1":
                    generateStudentReport(students);
                    break;
                case "2":
                    generateTeacherReport(teachers);
                    break;
                case "3":
                    workersReport(teachers, pedagogues);
                    break;
                case "4":
                    everybodyReport(students, teachers, pedagogues);
                    break;
                case "5":
                    break;
                case "6":
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
                        optsm = " ";
                    }while(logout.charAt(0) != 'n');
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção Inválida!");
                    System.out.println("Informação esperada: um número do menu.");
                    System.out.println();
            }
        }while (!optsm.equals("5"));
    }

    public void pedagogicalServiceData(List<Student> students, List<Pedagogue> pedagogues){
        if (students.isEmpty() == false && pedagogues.isEmpty() == false){
            System.out.println("RELATÓRIO PEDAGÓGICO: ");
            boolean hasPedagogicalService = false;
            for (Student s : students){
                if(s.getPedagogicalServiceCounter() > 0){
                    System.out.println(" - Aluno - id:" + s.getId() + ", nome:" + s.getName() + ", CPF:" + s.getCpf() + " nª de Atendimentos:" + s.getPedagogicalServiceCounter() + ", status: " + s.getStatus().toString().toLowerCase());
                    hasPedagogicalService = true;
                }
            }
            for (Pedagogue p: pedagogues){
                if (p.getPedagogicalServiceCounter() > 0){
                    System.out.println(" - Pedagogo - id:" + p.getId() + ", nome:" + p.getName() + ", CPF:" + p.getCpf() + " nª de Atendimentos:" + p.getPedagogicalServiceCounter());
                    hasPedagogicalService = true;
                }
            }
            if(!hasPedagogicalService){
                System.out.println(" - Nenhum Atendimento Pedagógico foi realizado.\n");
            }
        }
        else {
            System.out.println("RELATÓRIO PEDAGÓGICO: ");
            System.out.println(" - Nenhum Atendimento Pedagógico foi realizado.\n");
            if(students.isEmpty()){
                System.out.println(" - Não há alunos cadastrados no Sistema");
            }
            if(pedagogues.isEmpty()){
                System.out.println(" - Não há pedagogos cadastrados no Sistema");
            }
            System.out.println();
        }

    }

    public void generateStudentReport(List<Student> students){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n* Informe o número do Status para gerar o Relatório: *\n1-Ativo\n2-Inativo\n3-Irregular\n4-Atendimento Pedagógico\n5-Todos\n6-Voltar ao menu anterior");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        String opt = sc.nextLine().trim();

        switch (opt){
            case "1":
                System.out.println("RELATÓRIO DE ALUNOS ATIVOS:");
                System.out.println("Aluno inativo:");
                boolean hasIt = false;
                for (Student x : students) {
                    if(x.getStatus() == StudentStatus.ATIVO){
                        System.out.println(" - Aluno - id: " + x.getId() + ", nome: " + x.getName() + ", nota: " + x.getSelectionGrade() + "nªde Atendimentos Pedagógicos: " + x.getPedagogicalServiceCounter() + ", status: " + x.getStatus().toString().toLowerCase());
                        hasIt = true;
                    }
                }
                if (hasIt == false) {
                    System.out.println(" - Não há aluno(s) Ativo(s) cadastrado(s)");
                }
                break;
            case "2":
                System.out.println("RELATÓRIO DE ALUNOS INATIVOS:");
                System.out.println("Aluno inativo:");
                hasIt = false;
                for (Student x : students) {
                    if(x.getStatus() == StudentStatus.INATIVO){
                        System.out.println(" - Aluno - id: " + x.getId() + ", nome: " + x.getName() + ", nota: " + x.getSelectionGrade() + "nªde Atendimentos Pedagógicos: " + x.getPedagogicalServiceCounter() + ", status: " + x.getStatus().toString().toLowerCase());
                        hasIt = true;
                    }
                }
                if (hasIt == false) {
                    System.out.println(" - Não há aluno(s) Inativo(s) cadastrado(s)");
                }
                break;
            case "3":
                System.out.println("RELATÓRIO DE ALUNOS IRREGULARES:");
                System.out.println("Aluno irregular:");
                hasIt = false;
                for (Student x : students)  {
                    if(x.getStatus() == StudentStatus.IRREGULAR){
                        System.out.println(" - Aluno - id: " + x.getId() + ", nome: " + x.getName() + ", nota: " + x.getSelectionGrade() + "nªde Atendimentos Pedagógicos: " + x.getPedagogicalServiceCounter() + ", status: " + x.getStatus().toString().toLowerCase());
                        hasIt = true;
                    }
                }
                if (hasIt == false) {
                    System.out.println(" - Não há aluno(s) Irregoular(es) cadastrado(s)");
                }
                break;
            case "4":
                System.out.println("RELATÓRIO DE ALUNOS EM ATENDIMENTO PEDAGÓGICO:");
                System.out.println("Aluno em Atendimento Pedagógico:");
                hasIt = false;
                for (Student x : students) {
                    if (x.getStatus() == StudentStatus.ATENDIMENTO_PEDAGÓGICO) {
                        System.out.println(" - Aluno - id: " + x.getId() + ", nome: " + x.getName() + ", nota: " + x.getSelectionGrade() + "nªde Atendimentos Pedagógicos: " + x.getPedagogicalServiceCounter() + ", status: " + x.getStatus().toString().toLowerCase());
                        hasIt = true;
                    }
                }
                if (hasIt == false) {
                    System.out.println(" - Não há aluno(s) em Atendimento(s) Pedagógico(s) cadastrado(s)");
                }
                break;
            case "5":
                System.out.println("Relação Total de Alunos:");
                students.forEach( x -> {
                    System.out.println(" - Aluno - id: " + x.getId() + ", nome: " + x.getName() + ", nota: " + x.getSelectionGrade() + "nªde Atendimentos Pedagógicos: " + x.getPedagogicalServiceCounter() + ", status: " + x.getStatus().toString().toLowerCase());
                    });
                break;
            case "6":
                break;
            default:
                System.out.println();
                System.out.println("Opção inválida!");
                System.out.println("Informação esperada: um número do menu.");
                System.out.println();
        }
    }

    public void generateTeacherReport(List<Teacher> teachers){
        Scanner scan = new Scanner(System.in);
        System.out.println("* Informe o número da experiência para gerar o Relatório: *\n1-Front-End\n2-Back-End\n3-Full-Stack\n4-Todos\n5-Voltar ao menu anterior");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        String opt = scan.nextLine().trim();

        switch (opt){
            case "1":
                System.out.println("RELATÓRIO DE PROFESSORES FRONT-END:");
                boolean hasIt = false;
                for (Teacher x : teachers) {
                    if (x.getExperience() == TeacherExperience.FRONT_END) {
                        System.out.println(" - Professor - id: " + x.getId() + ", nome: " + x.getName() + ", formação: " + x.getFormation().toString().toLowerCase() + ", status: " + x.getStatus().toString().toLowerCase());
                        hasIt = true;
                    }
                }
                if(hasIt == false) {
                    System.out.println(" - Não há professor(es) de Front-End cadastrado(s)");
                }
                break;
            case "2":
                System.out.println("RELATÓRIO DE PROFESSORES BACK-END:");
                hasIt = false;
                for (Teacher x : teachers) {
                    if (x.getExperience() == TeacherExperience.BACK_END) {
                        System.out.println(" - Professor - id: " + x.getId() + ", nome: " + x.getName() + ", formação: " + x.getFormation().toString().toLowerCase() + ", status: " + x.getStatus().toString().toLowerCase());
                        hasIt = true;
                    }
                }
                if(hasIt == false) {
                    System.out.println(" - Não há professor(es) de Back-End cadastrado(s)");
                }
                break;
            case "3":
                System.out.println("RELATÓRIO DE PROFESSORES FULL-STACK:");
                hasIt = false;
                for (Teacher x : teachers) {
                    if (x.getExperience() == TeacherExperience.FULL_STACK) {
                        System.out.println(" - Professor - id: " + x.getId() + ", nome: " + x.getName() + ", formação: " + x.getFormation().toString().toLowerCase() + ", status: " + x.getStatus().toString().toLowerCase());
                        hasIt = true;
                    }
                }
                if(hasIt == false) {
                    System.out.println(" - Não há professor(es) de Full-Stack cadastrado(s)");
                }
                break;
            case "4":
                System.out.println("RELATÓRIO TOTAL DE PROFESSORES:");
                teachers.forEach( x -> {
                    System.out.println(" - Professor - id: " + x.getId() + ", nome: " + x.getName() + ", formação: " + x.getFormation().toString().toLowerCase() + ", experiência: " + x.getExperience().toString().toLowerCase() + ", status: " + x.getStatus().toString().toLowerCase());
                    });
                break;
            case "5":
                break;
            default:
                System.out.println();
                System.out.println("Opção inválida!");
                System.out.println("Digite um número do menu.");
                System.out.println();
        }
    }

    public void everybodyReport(List<Student> students, List<Teacher> teachers, List<Pedagogue> pedagogues){
        System.out.println("RELATÓRIO DE TODAS AS PESSOAS CADASTRADAS NO SISTEMA:");
        if (students.isEmpty() && teachers.isEmpty() && pedagogues.isEmpty()){
            System.out.println("Opção indiponível!");
            System.out.println("- Não há Dados Cadastrados no Sistema.\n");
        }
        for (Student s : students){
            System.out.println(" - Aluno - id: " + s.getId() + ", nome: " + s.getName() + ", nota: " + s.getSelectionGrade() + "nªde Atendimentos Pedagógicos: " + s.getPedagogicalServiceCounter() + ", status: " + s.getStatus().toString().toLowerCase());
        }
        for (Teacher t : teachers){
            System.out.println(" - Professor - id: " + t.getId() + ", nome: " + t.getName() + ", formação: " + t.getFormation().toString().toLowerCase() + ", experiência: " + t.getExperience().toString().toLowerCase() + ", status: " + t.getStatus().toString().toLowerCase());
        }
        for (Pedagogue p: pedagogues){
            System.out.println(" - Pedagogo - id: " + p.getId() + ", nome: " + p.getName() + ", CPF: " + p.getCpf() + ", nªde Atendimentos Pedagógicos: " + p.getPedagogicalServiceCounter());
        }
    }

    public void workersReport(List<Teacher> teachers, List<Pedagogue> pedagogues){
        System.out.println("RELATÓRIO DE COLABORADORES:");
        if (teachers.isEmpty() && pedagogues.isEmpty()){
            System.out.println("Opção indiponível!");
            System.out.println("- Não há Dados Cadastrados no Sistema.\n");
        }
        teachers.forEach( x -> {
            System.out.println(" - Professor - id: " + x.getId() + ", nome: " + x.getName() + ", formação: " + x.getFormation().toString().toLowerCase() + ", experiência: " + x.getExperience().toString().toLowerCase() + ", status: " + x.getStatus().toString().toLowerCase());
        });
        pedagogues.forEach( x -> {
            System.out.println(" - Pedagogo - id: " + x.getId() + ", nome: " + x.getName() + ", CPF: " + x.getCpf() + ", nªde Atendimentos Pedagógicos: " + x.getPedagogicalServiceCounter());
        });
    }
}
