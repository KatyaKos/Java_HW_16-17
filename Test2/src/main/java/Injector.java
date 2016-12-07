import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author KatyaKos
 * Initializes object with given dependencies.
 */
public class Injector{

    /**
     * Create and initialize object of `rootClassName` class using classes from
     * `implementationClassNames` for concrete dependencies.
     *
     */

    /**
     * Create and initialize object of `rootClassName` class using classes from `implementationClassNames` for concrete dependencies.
     * @param rootClassName initializing object
     * @param implementationClassNames list of class for dependencies
     * @return initialized object
     * @throws AmbiguousImplementationException if different implementations suit one dependency
     * @throws ImplementationNotFoundException if there is no implementation for some dependency
     * @throws InjectionCycleException if dependencies make cycle
     */
    public static Object initialize(String rootClassName, List<String> implementationClassNames) throws Exception {
        Class<?> rootClass = Class.forName(rootClassName);
        List<Class<?>> implementations = new ArrayList<>();
        for (String name : implementationClassNames) {
            implementations.add(Class.forName(name));
        }

        Constructor constructor = Arrays.asList(rootClass.getConstructors()).stream().findFirst().orElse(null);
        Class[] constructorParams = constructor.getParameterTypes();

        //Check cycle.
        int n = constructorParams.length;
        for (int i = 0; i < n; i++) {
            Class param1 = constructorParams[i];
            for (int j = i; j < n; j++) {
                Class param2 = constructorParams[j];
                if (param1.isAssignableFrom(param2) && param2.isAssignableFrom(param1)) {
                    throw new InjectionCycleException();
                }
            }
        }

        //Check multiple implementations.
        int m = implementations.size();
        int[] implementationNumbers = new int[n];
        for (int j = 0; j < n; j++) {
            Class param = constructorParams[j];
            int implementationNumber = -1;
            for (int i = 0; i < m; i++) {
                Class implementation = implementations.get(i);
                if (param.isAssignableFrom(implementation)) {
                    if (implementationNumber == -1) {
                        implementationNumber = i;
                    } else {
                        throw new AmbiguousImplementationException();
                    }
                }
            }
            if (implementationNumber == -1) {
                throw new ImplementationNotFoundException();
            }
            implementationNumbers[j] = implementationNumber;
        }

        //initialization
        return dfs(rootClass);
    }

    private static Object dfs(Class clazz) throws Exception{
        Constructor constructor = Arrays.asList(clazz.getConstructors()).stream().findFirst().orElse(null);
        Class[] params = constructor.getParameterTypes();
        if (params.length == 0) {
            return clazz.newInstance();
        }

        ArrayList<Object> dependecies = new ArrayList<>();
        for (Class param : params) {
            dependecies.add(dfs(param));
        }
        return constructor.newInstance(dependecies);
    }
}
