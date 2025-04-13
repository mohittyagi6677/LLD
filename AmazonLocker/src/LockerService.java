class LockerService {
    private final Map<String, PickupLocation> locationMap = new ConcurrentHashMap<>();

    public void addPickupLocation(PickupLocation location) {
        locationMap.put(location.getLocationId(), location);
    }

    public Locker assignPackage(Package p, String preferredZipCode) {
        PickupLocation nearest = findNearestLocation(preferredZipCode, p.getSize());
        if (nearest == null) return null;
        return nearest.assignPackage(p);
    }

    public Package retrievePackage(String packageId, String accessCode) {
        for (PickupLocation loc : locationMap.values()) {
            try {
                Package p = loc.retrievePackage(packageId, accessCode);
                if (p != null) return p;
            } catch (SecurityException e) {
                throw e;
            }
        }
        return null;
    }

    private PickupLocation findNearestLocation(String zip, Size packageSize) {
        // Dummy logic. Replace with geolocation logic based on zip.
        for (PickupLocation loc : locationMap.values()) {
            if (loc.hasAvailableLocker(packageSize)) {
                return loc;
            }
        }
        return null;
    }
}