class Locker {
    private final String id;
    private final Size size;
    private LockerStatus status;
    private Package currentPackage;
    private String accessCode;

    public Locker(Size size) {
        this.id = UUID.randomUUID().toString();
        this.size = size;
        this.status = LockerStatus.AVAILABLE;
    }

    public synchronized boolean assignPackage(Package p) {
        if (status != LockerStatus.AVAILABLE) return false;
        this.currentPackage = p;
        this.status = LockerStatus.OCCUPIED;
        this.accessCode = LockerUtils.generateCode();
        return true;
    }

    public synchronized Package retrievePackage(String code) {
        if (!Objects.equals(accessCode, code)) throw new SecurityException("Invalid code");
        Package p = currentPackage;
        currentPackage = null;
        accessCode = null;
        status = LockerStatus.AVAILABLE;
        return p;
    }

    public synchronized boolean isAvailable() {
        return status == LockerStatus.AVAILABLE;
    }

    public Size getSize() { return size; }
    public String getId() { return id; }
    public LockerStatus getStatus() { return status; }
    public String getAccessCode() { return accessCode; }
}