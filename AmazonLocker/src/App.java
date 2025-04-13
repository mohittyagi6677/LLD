public class App {
    public static void main(String[] args) throws Exception {
        public static void main(String[] args) {
            // Step 1: Initialize locker sizes at this pickup location
            Map<Size, Integer> lockerSizes = new HashMap<>();
            lockerSizes.put(Size.SMALL, 2);
            lockerSizes.put(Size.MEDIUM, 1);
            lockerSizes.put(Size.LARGE, 1);
    
            PickupLocation location = new PickupLocation(lockerSizes);
    
            // Step 2: Create some packages
            Package p1 = new Package(Size.SMALL);
            Package p2 = new Package(Size.SMALL);
            Package p3 = new Package(Size.MEDIUM);
            Package p4 = new Package(Size.LARGE);
            Package p5 = new Package(Size.LARGE); // Should fail, no locker left
    
            // Step 3: Assign packages to lockers
            location.assignPackage(p1);
            location.assignPackage(p2);
            location.assignPackage(p3);
            location.assignPackage(p4);
            location.assignPackage(p5); // No locker available
    
            // Step 4: Get access code for a package (e.g., p2)
            String accessCode = location.getAccessCode(p2.getId());
            System.out.println("Access code for p2: " + accessCode);
    
            // Step 5: Try to retrieve with wrong code
            location.getPackage(p2.getId(), "123456"); // Should fail
    
            // Step 6: Retrieve with correct code
            location.getPackage(p2.getId(), accessCode); // Should succeed
    
            // Step 7: Try to retrieve again
            location.getPackage(p2.getId(), accessCode); // Should fail, already picked up
        }
    }
}
