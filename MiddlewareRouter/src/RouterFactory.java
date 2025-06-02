package MiddlewareRouter;

import java.util.List;

public class RouterFactory {
    ExactMatchRouter exactMatchRouter;


    public RouterFactory(){

    }

    public Router getRouter(String path){
        if(path.contains("*") || path.contains("{")){
            return WildcardRouter.getInstance();
        } else {
            return ExactMatchRouter.getInstance();
        }

    }

    public List<Router> getAllRouters(){
        return List.of(ExactMatchRouter.getInstance(),WildcardRouter.getInstance());
        
    }

}
