package application;

import java.util.Locale;
import java.util.Scanner;
import services.RegistrationManager;

public class LabSchoolApp {
    public static void main(String[]args){
        //Funções utilitárias
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        //Objetos instaciados
        RegistrationManager registrationManager = new RegistrationManager();
        registrationManager.resetLists();

        //Menu principal da Aplicação
        String opt = " ";
        while(!opt.equals("5")) {
            System.out.println("\n\n");
            System.out.println("* * * * * * LABSCHOOL - MENU * * * * * * *");

            System.out.println("Informe o número da opção desejada:\n1-Novo Cadastro\n2-Alterar Cadastro\n3-Gerar Relatórios\n4-Atendimento Pedagógico\n5-Sair");
            System.out.println("* * * * * * * * * * ** * * * * * * * * * *");
            opt = sc.nextLine().trim();
            switch (opt){
                case "1":
                    registrationManager.newRegistration();
                    break;
                case "2":
                    registrationManager.changeRegister();
                    break;
                case "3":
                    registrationManager.registry();
                    break;
                case "4":
                    registrationManager.pedagogicalService();
                    break;
                case "5":
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
        sc.close();
    }
}
