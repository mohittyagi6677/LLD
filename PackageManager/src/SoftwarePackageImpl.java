import java.util.*;

public class SoftwarePackageImpl implements SoftwarePackage {
    private final String name;
    private final List<String> dependencies;

    public SoftwarePackageImpl(String name, List<String> dependencies) {
        this.name = name;
        this.dependencies = new ArrayList<>(dependencies);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getDependencies() {
        return Collections.unmodifiableList(dependencies);
    }
}
