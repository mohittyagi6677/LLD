package MiddlewareRouter;
import java.util.*;
import java.util.regex.Pattern;
class PatternWithValue{
    Pattern pattern;
    String value;

    public PatternWithValue(Pattern pat,String val){
        pattern=pat;
        value=val;
    }

}
public class WildcardRouter implements Router{
    Map<String,PatternWithValue> patternMap;
    private static WildcardRouter instance;
    private WildcardRouter(){
        this.patternMap=new HashMap<>();
    }

    public static WildcardRouter getInstance(){
        if(instance==null){
            synchronized(WildcardRouter.class){
                if(instance==null){
                    instance=new WildcardRouter();
                }
            }
        }
        return instance;
    }

    @Override
    public void addRoute(String path, String result) {
        String regex = path.replaceAll("\\*", "[^/]+")
                           .replaceAll("\\{[^/]+\\}", "([^/]+)");
                           //regex = "^" + regex + "$"; // Ensure it matches the entire path
        this.patternMap.put(path, new PatternWithValue(Pattern.compile(regex), result));
    }

    @Override
    public String callRoute(String path) {
        for(Map.Entry<String,PatternWithValue> entry: this.patternMap.entrySet()){
            if(entry.getValue().pattern.matcher(path).matches()){
                return entry.getValue().value;
            }
        }
        return null;
    }

}
