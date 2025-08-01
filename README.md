# README

All of the code samples passed all test cases provided by AlgoExpert. The test-cases used are not included in the content of this repo. as this was simply intended to keep track of what I've done.

## Miscellaneous Notes

### Reflection API

While working through the *Coding Interview Questions*, I found the Reflection API to be useful in determining the underlying structural information (albeit excessive/unnecessary).

```java
import java.lang.reflect.*;

/**
 * The following code is a snippet (i.e., parent method/class not included).
 * Also, these examples are referencing a 'static' class with an instance
 * ... name of 'tree' (i.e., there is no need to instantiate in these snippets)
 */

// DESC: Get fields in class
Field[] fields = tree.getClass().getDeclaredFields();
for (Field field : fields) {
    System.out.println("Field Name: " + field.getName());
}

// DESC: Get 'super' class
Class<?> treeSuper = tree.getClass().getSuperclass();
System.out.println(treeSuper);

// DESC: Get methods in class
Method[] methods = tree.getClass().getDeclaredMethods();
for (Method method : methods) {
    System.out.println("Method Name: " + method.getName());
}
```

