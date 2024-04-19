import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private Supervisor supervisor;
    //atrybut zlozony
    private String proffesion;
    private double salary;
    //private double salaryModifier;
    //atrybut pochodny
    private static List<Employee> employeesList = new ArrayList<>();
    //ekstensja

    private String phoneNumber=null;
    //atrybut opcjonalny


    public Employee(Supervisor supervisor, String proffesion,double salary, String phoneNumber) {
        this.supervisor = supervisor;
        this.proffesion = proffesion;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        employeesList.add(this);
    }


    public String getPhoneNumber() {
        if (phoneNumber.equals(null)) {
            System.out.println("there is no such phone number in database");
        }
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //metody atryb opcjonalnego

    public double getSalaryModifier(){
        if (proffesion.equals("elektryk")){
            this.salary+=300;
        }
        else if (proffesion.equals("hydraulik")) {
            this.salary+=250;
        }
        else{
            this.salary=salary;
        }
        return salary;
    }

    public static void writeEmployeesList(ObjectOutputStream stream){
        try{
            stream.writeObject(employeesList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public static void readExtent(ObjectInputStream stream) {
        try {
            employeesList = (ArrayList<Employee>) stream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



        public static void showEmployees(){
        for(Employee employee : employeesList){
            System.out.println(employee);
        }
    }
    //metoda klasowa


    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public String getProffesion() {
        return proffesion;
    }

    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "supervisor=" + supervisor +
                ", proffesion='" + proffesion + '\'' +
                ", salary=" + salary +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
    //przesloniecie
}
