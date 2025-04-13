class PickupLocation {
    private final String locationId;
    private final Map<Size, Queue<Locker>> availableLockers = new ConcurrentHashMap<>();
    private final Map<String, Locker> packageToLocker = new ConcurrentHashMap<>();

    public PickupLocation(String locationId, Map<Size, Integer> lockerConfig) {
        this.locationId = locationId;
        for (Size size : lockerConfig.keySet()) {
            Queue<Locker> queue = new ConcurrentLinkedQueue<>();
            for (int i = 0; i < lockerConfig.get(size); i++) {
                queue.add(new Locker(size));
            }
            availableLockers.put(size, queue);
        }
    }

    public synchronized Locker assignPackage(Package p) {
        for (Size sz : Size.values()) {
            if (!p.getSize().canFitIn(sz)) continue;
            Queue<Locker> q = availableLockers.get(sz);
            Locker locker = q.poll();
            if (locker == null || !locker.assignPackage(p)) continue;
            packageToLocker.put(p.getId(), locker);
            return locker;
        }
        return null;
    }

    public synchronized Package retrievePackage(String packageId, String accessCode) {
        Locker locker = packageToLocker.get(packageId);
        if (locker == null) return null;
        Package p = locker.retrievePackage(accessCode);
        availableLockers.get(locker.getSize()).add(locker);
        packageToLocker.remove(packageId);
        return p;
    }

    public String getLocationId() { return locationId; }
    public boolean hasAvailableLocker(Size requestedSize) {
        for (Size sz : Size.values()) {
            if (!requestedSize.canFitIn(sz)) continue; // Skip smaller sizes
            Queue<Locker> q = availableLockers.get(sz);
            if (q != null && !q.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
