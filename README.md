# README

All of the code samples passed all test cases provided by AlgoExpert. The test-cases used are not included in the content of this repo. as this was simply intended to keep track of what I've done.

## Code References

- Look at Medium -> MergeOverlappingIntervals for an example of working with a nested ArrayList

## Thinking Algorithmically

Throughout the AlgoExpert course, these are the common approaches I encountered:

- Arrays
  - Use multiple *cursors* and a `while` loop
    - This works in lieu of using nested loops or even multiple subsequent loops
  - Sort the array (where possible) prior to working the challenge
  - Use *Bitwise Operators*
    - The following challenges have a solution that uses *Bitwise Operators*
      - *Missing Numbers* (under "Medium") uses `^` (xor) operator [**not** used for the my solution]
      - *Majority Element* (under "Medium") uses `&` (and) operator [**not** used for the my solution]

Also, as an extremely important yet generic tip... pay close attention to what the challenge verbiage states and, quite frankly, what it does not explicitly state as that can help determine how to solve it. For example, if it does **not** mention anything about **not** sorting or mutating the input, then it is likely that you can.

## Miscellaneous Notes

### Helpful Takeaways

- When working with _objects_ (**incl.** _wrapper classes_) in lieu of the _primitive_ equivalent just note that you are working with the address of the value **not** the value itself
  - Use `output.add(new Integer[]{currentNumber, leftCursor, rightCursor});` in lieu of updating an `Integer[]` array with _bracket notation_ then adding that array to the parent ArrayList array

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

### AlgoExpert Error(s) Encountered

- On *MergeOverlappingIntervals*, I noticed that the Array List `.getLast()` method was not working so I replaced it with `get(outputArrayList.size() - 1)` in all areas of my code
  - Note that `.getLast()` was working in IntelliJ IDEA **and** the Java version of AlgoExpert appears to check out (i.e., it is Java 21)