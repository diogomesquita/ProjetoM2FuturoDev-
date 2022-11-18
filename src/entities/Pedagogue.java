package entities;

import exceptions.NotWordException;
import exceptions.OnlyNumbersException;
import utils.IdGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Pedagogue extends Person {
    Scanner sc = new Scanner(System.in);
    IdGenerator idGen = new IdGenerator();
    private int pedagogicalServiceCounter;

    public Pedagogue() {
        super();
    }

    public Pedagogue(int id, String name, String phone, LocalDate birthday, String cpf) {
        super(id, name, phone, birthday, cpf);
    }

    public Pedagogue(int id, String name, String cpf, int pedagogicalServiceCounter) {
        super(id, name, cpf);
        this.pedagogicalServiceCounter = pedagogicalServiceCounter;
    }

    public Integer getPedagogicalServiceCounter() {
        return pedagogicalServiceCounter;
    }

    public void setPedagogicalServiceCounter(int pedagogicalServiceCounter) {
        this.pedagogicalServiceCounter = pedagogicalServiceCounter;
    }

    public void increasePedagogicalServiceCounter(List<Pedagogue> pedagogues, int idPedagogue){
        for (Pedagogue p : pedagogues){
            if (p.getId() == idPedagogue){
                int aux = p.getPedagogicalServiceCounter() + 1;
                p.setPedagogicalServiceCounter(aux);
            }
        }
    }

    public void newPedagogue(List<Pedagogue> pedagogues){
        try {
            System.out.println("TELA DE CADASTRO DE PEDAGOGOS:");
            System.out.println("nome: ");
            String name = sc.nextLine();

            if (name.matches(".*[0-9]{1,}.*")){
                throw new NotWordException("FORMATO DE NOME INVÁLIDO!\nInformação esperada: Sequência de letras\n");
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

            IdGenerator.increaseIds();
            Pedagogue pedagogue = new Pedagogue(IdGenerator.getIds(), name, phone, birthday, ssn);
            pedagogues.add(pedagogue);
        }
        catch (DateTimeParseException e){
            System.out.println();
            System.out.println("FORMATO DE DATA INVÁLIDO!");
            System.out.println("Informação esperada: data no formato (dd/mm/aaaa)");
            System.out.println();
        }
        catch (NotWordException e) {
            System.out.println(e.getMessage());
        }
        catch (OnlyNumbersException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Pegagogue{" +
                "pedagogicalServiceCounter=" + pedagogicalServiceCounter +
                "} " + super.toString();
    }
}
