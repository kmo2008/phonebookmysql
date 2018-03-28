package pl.kmo2008;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Logic {
    private ArrayList<Person> persons = null;

    private DatabaseService databaseService = new DatabaseService();

    private Scanner scanner = new Scanner(System.in);

    public Logic() throws Exception {
    }


    public void add() throws SQLException {
        Person p = null;
        String name = inputName();
        String number = inputNumber();
        if (!number.equals("-1")) {
            System.out.println("Dodano " + name + " -> " + number);
            databaseService.save(new Person(name, number));
        } else System.out.println("Wprowadz dane jescze raz.");
    }

    public void show() throws SQLException {
        databaseService.findAll();
    }

    public void show(String name) throws SQLException {
        databaseService.findAny(name);
    }

    public void delete(String name) throws SQLException {
        databaseService.delete(name);
    }

    public void update(String name) throws SQLException {
        String newname = inputName();
        String number = inputNumber();
        if (!number.equals("-1")) {
            System.out.println("Zaktualizowano " + newname + " -> " + number);
            databaseService.update(new Person(newname, number), name);
        } else System.out.println("Wprowadz dane jescze raz.");
    }


    /**
     * This method takes name from user.
     *
     * @return String of name
     */
    public String inputName() {
        System.out.print("Podaj personalia: ");
        String name = scanner.next();
        return name;
    }

    /**
     * This method takes telephone number from user.
     * Working formats:
     * +48 123 456 789
     * 123456789
     * 123 456 789
     * 123-456-789
     * (+48) 123 456 789
     * +48123456789
     * 0048123456789
     * <p>
     * Things not to capture:
     * 12 345 67 89
     * 1234567899876543211
     * 654564654654654654
     * spam
     * 1231312asdasdf1231231
     * 123321
     * +4863227124
     *
     * @return String of number or "-1" when wrong input
     */
    public String inputNumber() {

        String pattern = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)";
        System.out.print("Podaj numer: ");
        try {
            String number = scanner.next(pattern);
            return number;
        } catch (InputMismatchException e) {
            System.out.println("Podane dane nie są numerem telefonu.");
        }
        return "-1";
    }
}
