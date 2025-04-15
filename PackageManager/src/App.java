import java.util.*;

public class App {
    public static void main(String[] args) {
        PackageManager manager = new PackageManagerImpl();

        manager.addPackage(new SoftwarePackageImpl("A", List.of("B", "C")));
        manager.addPackage(new SoftwarePackageImpl("B", List.of("C", "D")));
        manager.addPackage(new SoftwarePackageImpl("C", List.of("D")));
        manager.addPackage(new SoftwarePackageImpl("D", List.of()));

        manager.install("A");

        // Uncomment to test cyclic dependencies
        /*
        manager.addPackage(new SoftwarePackageImpl("X", List.of("Y")));
        manager.addPackage(new SoftwarePackageImpl("Y", List.of("X")));
        manager.install("X"); // should throw exception
        */
    }
}
