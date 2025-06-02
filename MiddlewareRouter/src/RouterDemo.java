package MiddlewareRouter;

public class RouterDemo {

    public static void main(String[] args) {
        // Create a new router
        Router router = new MiddlewareRouter();

        // Add some routes
        router.addRoute("/foo", "foo");
        router.addRoute("/bar/*/baz", "bar");
        router.addRoute("/car/{carId}/caz", "path");

        // Handle requests
        System.out.println(router.callRoute("/foo"));  // Output: foo
        System.out.println(router.callRoute("/bar/123/baz")); // Output: bar
        System.out.println(router.callRoute("/car/456/caz")); // Output: path

        router.addRoute("/bar/*/baz", "barNew");
        router.addRoute("/car/{carId}/caz", "pathNew");

        System.out.println(router.callRoute("/bar/123/baz")); // Output: barNew
        System.out.println(router.callRoute("/car/456/caz")); // Output: pathNew
    }

}
