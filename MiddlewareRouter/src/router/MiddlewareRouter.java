package router;

import java.util.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Router {
    void addRoute(String path, String result);
    String callRoute(String path);
}

class MiddlewareRouter implements Router {
    private final Map<String, String> exactRoutes = new HashMap<>();
    private final Map<Pattern, String> wildcardRoutes = new HashMap<>();
    private final Map<Pattern, String> paramRoutes = new HashMap<>();

    @Override
    public void addRoute(String path, String result) {
        if (path.contains("*")) {
            // Convert wildcard paths into regex
            String regex = path.replace("*", ".*");
            wildcardRoutes.put(Pattern.compile(regex), result);
        } else if (path.contains("{")) {
            // Convert path with parameters into regex
        		// 	// are used for escaping curly brace
            String regex = path.replaceAll("\\{[^/]+\\}", "([^/]+)");
            paramRoutes.put(Pattern.compile(regex), result);
        } else {
            exactRoutes.put(path, result);
        }
    }

    @Override
    public String callRoute(String path) {
        // 1. Exact match
        if (exactRoutes.containsKey(path)) {
            return exactRoutes.get(path);
        }

        // 2. Wildcard match
        for (Map.Entry<Pattern, String> entry : wildcardRoutes.entrySet()) {
            if (entry.getKey().matcher(path).matches()) {
                return entry.getValue();
            }
        }

        // 3. Parametrized match
        for (Map.Entry<Pattern, String> entry : paramRoutes.entrySet()) {
            Matcher matcher = entry.getKey().matcher(path);
            if (matcher.matches()) {
                // Optionally extract parameters
                return entry.getValue() + " with param: " + matcher.group(1);
            }
        }

        // 4. Route not found
        return "404 Not Found";
    }
}


