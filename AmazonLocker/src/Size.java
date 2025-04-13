enum Size {
    SMALL, MEDIUM, LARGE;

    public boolean canFitIn(Size lockerSize) {
        return lockerSize.ordinal() >= this.ordinal();
    }
}

enum LockerStatus {
    AVAILABLE, OCCUPIED, OUT_OF_SERVICE
}