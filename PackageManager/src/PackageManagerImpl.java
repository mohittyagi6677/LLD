import java.util.*;

public class PackageManagerImpl implements PackageManager {
    private final Map<String, SoftwarePackage> packageMap = new HashMap<>();
    private final Set<String> installed = new HashSet<>();

    @Override
    public void addPackage(SoftwarePackage pkg) {
        packageMap.put(pkg.getName(), pkg);
    }

    @Override
    public void install(String packageName) {
        Set<String> visiting = new HashSet<>();
        dfsInstall(packageName, visiting);
    }

    private void dfsInstall(String packageName, Set<String> visiting) {
        if (installed.contains(packageName)) return;

        if (visiting.contains(packageName)) {
            throw new IllegalStateException("Cyclic dependency detected at: " + packageName);
        }

        SoftwarePackage pkg = packageMap.get(packageName);
        if (pkg == null) {
            throw new IllegalArgumentException("Package not found: " + packageName);
        }

        visiting.add(packageName);

        for (String dep : pkg.getDependencies()) {
            dfsInstall(dep, visiting);
        }

        visiting.remove(packageName);
        installed.add(packageName);
        System.out.println("Installed: " + packageName);
    }
}
