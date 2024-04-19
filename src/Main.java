import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static String serializationFile = "data.ser";
    private static boolean stop = false;
    public static void main(String[] args) {
        classAtribute();
        //atrybut klasowy
        Supervisor supervisor = new Supervisor();
        classAtribute(supervisor);
        //przeciazenie metody

        Scanner in = new Scanner(System.in);
        while(!stop){
            System.out.println("1) Create renovation");
            System.out.println("2) Show renovation");
            System.out.println("3) Serialize objects");
            System.out.println("4) Deserialize objects");
            System.out.println("5) Exit program");
            int option = in.nextInt();
                /*
                trwałość ekstensji(obiekty przeżyły wyłączenie systemu):
                1.utworzenie remontów
                2.wyświetlenie utworzonych obiektow
                3.serializacja obiektów
                5.wyjście z programu
                ------ponowne uruchomienie programu-----
                2.wyświetlenie remontów (nie powinno wyświetlić obiektów przez ich serializacje)
                4.deserializacja obkietów
                2.wyświetlenie remontów (po deserializacji obiekty powinny się wyświetlić)
                5.wyjście z programu
                */
            switch (option) {
                case 1:
                    createRenovations();
                    break;
                case 2:
                    Renovation.showRenovations();
                    //użycie metody klasowej
                    //wyświetlanie remontów przy pomocy przesłonięcia toString()
                    break;
                case 3:
                    serializeObjects();
                    break;
                case 4:
                    deseralizeObjects();
                    break;
                default:
                    stop = true;
                    break;
            }
        }
    }
    public static void serializeObjects(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(serializationFile));
            Renovation.writeRenovationsList(out);
            //ekstensja
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deseralizeObjects(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(serializationFile));
            Renovation.readRenovationsList(in);
            //ekstensja
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createRenovations(){
        Supervisor s1 = new Supervisor();
        Employee e1 = new Employee(s1,"elektryk",2000,null);
        //wykorzystanie atrybutu zlożonego s1
        Employee e2 = new Employee(s1,"murarz",2000,"409888742");
        //wykorzystanie atrybutu opcjonalnego (tel)
        e1.getSalaryModifier();
        e2.getSalaryModifier();
        //wykorzystanie atrybutu pochodnego (wyliczanie pensji)
        Renovation r1 = new Renovation(new ArrayList<Employee>(Arrays.asList(e1,e2)));
        //wykorzystanie atrybutu powtarzalnego (listy pracownikow)

    }
    public static void classAtribute() {
        Supervisor s1 = new Supervisor();
        Supervisor s2 = new Supervisor();
        System.out.println("Supervisors s1 max number of workers: " + String.valueOf(s1.maxEmployees));
        System.out.println("Supervisors s2 max number of workers: " +String.valueOf(s2.maxEmployees));
        //wypisanie argumentu klasowego - maxEmployees
    }
    public static void classAtribute(Supervisor supervisor) {
        System.out.println("Supervisors supervisor max number of workers: " +String.valueOf(supervisor.maxEmployees));
    }
        //przeciazenie metody (i arg klasowy)
}
