import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Renovation implements Serializable {
    private List<Employee> employees;
    //atrybut powtarzalny

    private static List<Renovation> renovationsList = new ArrayList<>();



    public Renovation(List<Employee> employees) {
        this.employees = employees;
        addRenovation(this);
    }
    private void addRenovation(Renovation renovation ){
        renovationsList.add(renovation);
    }
    private void removeRenovation(Renovation renovation){
        renovationsList.remove(renovation);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }
    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }

    public static void writeRenovationsList(ObjectOutputStream stream){
        try{
            stream.writeObject(renovationsList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public static void readRenovationsList(ObjectInputStream stream) {
        try {
            renovationsList = (ArrayList<Renovation>) stream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void showRenovations(){
        for(Renovation renovation : renovationsList){
            System.out.println(renovation);
        }
    }
    //metoda klasowa


    @Override
    public String toString() {
        return "Renovation{" +
                "employees=" + employees +
                '}';
    }
}
