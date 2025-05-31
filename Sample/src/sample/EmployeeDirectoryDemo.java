import java.util.*;

public class EmployeeDirectoryDemo {
    public static void main(String[] args) {
        System.out.println("Hello world!");
      GroupNode root = new GroupNode();
        root.name="Company";

        GroupNode hr  = new GroupNode();
        hr.name="HR";

        GroupNode engg  = new GroupNode();
        engg.name="Engg";

        GroupNode fe  = new GroupNode();
        fe.name="FE";

        GroupNode be  = new GroupNode();
        be.name="BE";

        root.childGroups.add(hr);
        root.childGroups.add(engg);

        Employee Mona = new Employee();
        Mona.name="Mona";

        Employee Lisa = new Employee();
        Lisa.name="Lisa";

        Employee Alice = new Employee();
        Alice.name="Alice";

        hr.employees.add(Mona);

        engg.childGroups.add(be);
        engg.childGroups.add(fe);

        be.employees.add(Alice);
        fe.employees.add(Lisa);

        EmployeeDirectory employeeDirectory = new EmployeeDirectory(root);
        //assertEquals(engg.name, employeeDirectory.getClosestGroup(Alice.name, Lisa.name));
        List<String> names = new ArrayList<>();
        names.add(Alice.name);
        names.add(Mona.name);
        names.add(Lisa.name);
        //assertEquals(root.name, employeeDirectory.getClosestGroupForEmployees(names));
    }
}
