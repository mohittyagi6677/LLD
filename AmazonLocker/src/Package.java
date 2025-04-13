class Package {
    private final String id;
    private final Size size;
    private final long createdAt;
    private final long ttlMillis; // How long the package can remain in locker

    public Package(Size size, long ttlMillis) {
        this.id = UUID.randomUUID().toString();
        this.size = size;
        this.createdAt = System.currentTimeMillis();
        this.ttlMillis = ttlMillis;
    }

    public String getId() { return id; }
    public Size getSize() { return size; }
    public long getExpiryTime() { return createdAt + ttlMillis; }
}