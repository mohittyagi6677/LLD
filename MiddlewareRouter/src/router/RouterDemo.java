package router;

public class RouterDemo {
	
	public static void main(String[] args) {
        MiddlewareRouter router = new MiddlewareRouter();
        router.addRoute("/foo", "foo");
        router.addRoute("/bar/*/baz", "bar");
        router.addRoute("/foo/{id}", "foo");

        System.out.println(router.callRoute("/foo"));              // Output: foo
        System.out.println(router.callRoute("/bar/anything/baz")); // Output: bar
        System.out.println(router.callRoute("/foo/123"));          // Output: foo with param: 123
        System.out.println(router.callRoute("/unknown"));          // Output: 404 Not Found
    }

}
