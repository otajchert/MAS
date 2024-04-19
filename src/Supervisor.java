import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervisor implements Serializable {
public static int maxEmployees = 15;
//atrybut klasowy
private static List<Supervisor> supervisorsList = new ArrayList<>();

    public Supervisor() {
        supervisorsList.add(this);
    }

    public static void writeSupervisorsList(ObjectOutputStream stream){
        try{
            stream.writeObject(supervisorsList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public static void readSupervisorsList(ObjectInputStream stream) {
        try {
            supervisorsList = (ArrayList<Supervisor>) stream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showSupervisors(){
        for(Supervisor supervisor : supervisorsList){
            System.out.println(supervisor);
        }
    }
    //metoda klasowa

}
