public interface PackageManager {
    void addPackage(SoftwarePackage pkg);
    void install(String packageName);
}
