package MiddlewareRouter;

import java.util.*;




interface Router {
void addRoute(String path, String result);
String callRoute(String path) ;
}


public class MiddlewareRouter implements Router{
    RouterFactory routerFactory;
    public MiddlewareRouter(){
        this.routerFactory=new RouterFactory();
    }

    @Override
    public void addRoute(String path, String result) {
        this.routerFactory.getRouter(path).addRoute(path, result);
    }

    @Override
    public String callRoute(String path) {
        List<Router> allRouters = this.routerFactory.getAllRouters();
        for(Router router: allRouters){
            String value = router.callRoute(path);
            if(value!=null){
                return value;
            }
        }
        return null;
    }

}
