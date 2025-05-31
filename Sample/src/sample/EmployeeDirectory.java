import java.util.*;
 class Employee {
    String name;
    String parentGroup;
}

class GroupNode{
    String name;
    Set<GroupNode> childGroups = new HashSet<>();
    Set<Employee> employees = new HashSet<>();
}

public class EmployeeDirectory{
    Map<String,String> parentMap=new HashMap<>();
    GroupNode root;
    public EmployeeDirectory(GroupNode root){
        this.root=root;
        initializeParentMap(this.root);

    }

    public void initializeParentMap(GroupNode root){
        for(Employee employee: root.employees){
            parentMap.put(employee.name,root.name);
        }
        for(GroupNode node: root.childGroups){
            parentMap.put(node.name,root.name);
            initializeParentMap(node);
        }
    }

    public String getClosestGroupForEmployees(List<String> employeeNames){
        
        String employee1=employeeNames.get(0);
        String employee2=employeeNames.get(1);
        String commonGroup = getClosestGroup(employee1,employee2);
        for(int i=2;i<employeeNames.size();i++){
            commonGroup = getClosestGroup(commonGroup, employeeNames.get(i));
        }
        return commonGroup;


    }


    public String getClosestGroup(String employee1,String employee2){
        List<String> employee1Path = new ArrayList<>();
        List<String> employee2Path = new ArrayList<>();
        employee1Path.add(employee1);
        employee2Path.add(employee2);
        String parent1 = this.parentMap.get(employee1);
        while(parent1!=null){
            employee1Path.add(parent1);
            parent1=this.parentMap.get(parent1);
        }

        String parent2 = this.parentMap.get(employee2);
        while(parent2!=null){
            employee2Path.add(parent2);
            parent2=this.parentMap.get(parent2);
        }
        Set<String> employee1Paths = new HashSet<>();
        employee1Paths.addAll(employee1Path);

        for(String path: employee2Path){
            if(employee1Paths.contains(path)){
                return path;
            }
        }
        return null;

    }

    // Alice->BE->Engg->Company
    // Lisa->FE->XYZ->Engg->Company

    // Mona->HR->Company


}
