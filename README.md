# README

All of the code samples passed all test cases provided by AlgoExpert. The test-cases used are not included in the content of this repo. as this was simply intended to keep track of what I've done.

## Code References

- Look at the following challenges for examples of working with a nested ArrayList
  - Medium -> MergeOverlappingIntervals
  - Hard -> FourNumberSum

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
  - Use the *Peaks and Valleys* approach
    - This entails locating the *local minimums* (i.e., where the adjacent values both have higher values) then iterating out left and right until a *peak* is located (i.e., where the adjacent value on one side starts to decrease)

Also, as an extremely important yet generic tip... pay close attention to what the challenge verbiage states and, quite frankly, what it does not explicitly state as that can help determine how to solve it. For example, if it does **not** mention anything about **not** sorting or mutating the input, then it is likely that you can.

## Miscellaneous Notes

### Helpful Takeaways

> When you have eliminated the impossible, whatever remains, however improbable, must be the truth. ~ Sherlock Holmes

- In continuation of the quote, when confused as to why certain test-cases are not running properly, maybe the test-cases that **are** running properly are throwing false-positives and they actually have flawed logic (i.e., check the 'correct' test-cases for proper functionality)
- When working with _objects_ (**incl.** _wrapper classes_) in lieu of the _primitive_ equivalent just note that you are working with the address of the value **not** the value itself
  - Use `output.add(new Integer[]{currentNumber, leftCursor, rightCursor});` in lieu of updating an `Integer[]` array with _bracket notation_ then adding that array to the parent ArrayList array
- `Integer[]` / `int[]` vs `ArrayList<Integer>`
  - Use `Integer[]` when...
    - The size of the collection is known and fixed in advance
    - Performance for direct element access is critical
    - Memory efficiency is a primary concern
  - Use `ArrayList<Integer>` when...
    - The size of the collection is dynamic and may change during execution
    - Convenience and ease of use with built-in methods are desired
    - Flexibility in adding, removing, and manipulating elements is required

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

### Questions for Developers

- In the event that you find a solution that works for all *normal* cases, yet is broken for *edge-cases* do you modify the working logic to include the *edge-cases* **or** do you create separate code-blocks around your logic to separately locate and handle those *edge-cases*?
  - I've noticed that I find it easier to do the latter approach in lieu of tampering with working logic (i.e., why let the minority/unique events alter the logic that will work for the majority of the events mentality)