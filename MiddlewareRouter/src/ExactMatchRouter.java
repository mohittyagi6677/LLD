package MiddlewareRouter;

import java.util.*;

public class ExactMatchRouter implements Router{
    Map<String,String> pathMap;
    private static ExactMatchRouter instance;

    private ExactMatchRouter(){
        this.pathMap=new HashMap<>();

    }

    public static ExactMatchRouter getInstance(){
        if(instance==null){
            synchronized(ExactMatchRouter.class){
                if(instance==null){
                    instance=new ExactMatchRouter();
                }
            }
        }
        return instance;
    }




    @Override
    public void addRoute(String path, String result) {
        this.pathMap.put(path, result);
    }

    @Override
    public String callRoute(String path) {
        return this.pathMap.get(path);
    }

}
