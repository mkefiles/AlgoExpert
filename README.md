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
- If you need to round up an integer, then you need to convert the value(s) passed to `Math.ceil(x)` to *double* first
  - If not, then results may be wrong because the `int` / `Integer` prematurely truncates the decimals
  - Cast to int with `(int)` and double with `(double)`
- A great way to know what *non-primitive* data type you're working with is to append `.getClass().getName()`
- With regards to *type-casting*, if you have an *Object* data-type that has specific underlying values (e.g., `Integer` / `int`), then you will notice that you cannot simply cast to a different type (e.g., `Double` / `double`) directly as you will encounter a `ClassCastException`
  - To handle this, first cast to a `Number` (the parent-class of both), then convert to the desired value-type (e.g., `Double` / `double`)
    - Code snippet: `double d = ((Number) obj).doubleValue();` where `obj` was defined as `Object obj = 10;`
- Note the difference between a *primitive* Array and an *Object* `ArrayList` when it comes to outputting to a string:

```java
// DESC: Outputting an ArrayList to a String
// NOTE: `Arrays.toString()` does not work
ArrayList<ArrayList<Integer>> test01 = new ArrayList<ArrayList<Integer>>();
test01.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
for (ArrayList<Integer> singleArray : test01) {
    System.out.println(singleArray);
}

// DESC: Outputting an Array to a String
// NOTE: `Arrays.toString()` is required as it will,
// ... otherwise, output the memory location not the
// ... value itself
int[][] test02 = {{1, 2, 3}};
for (int[] singleArray : test02) {
    System.out.println(Arrays.toString(singleArray));
}
```

- IF you have an end T.C. of, say, n^2 (due to a nested loop), then you could, in theory, do other T.C. tasks (less than that) without altering the end T.C.
  - For example, if you know that you need a nested loop to iterate every value then you can output all values of an array to another data-set using a simple loop and even though that is iterating each value one it is 'n' T.C., which is less than n^2 and it gives you another data-structure that may be of additional help to solve the issue
- With Java specifically, when adding an array of integers to a `Set` of arrays that contains integers, it is best to have them added as `List<>` elements NOT as `Integer[]` because the `.contains()` misbehaves when trying it the logical way
- Using boolean variable(s), with ternarys (if necessary), prior to an If-Statement helps clean up the verbosity of the If-Statement while also providing clarity on what it is checking against
- A `List` itself does **not** allow for `.remove(x)` so you **must** create it as an `ArrayList`:
  - Ex.: `List<String> testInput01 = new ArrayList<>(Arrays.asList("yo", "act"));`
- An `ArrayList` does **not** allow for *concurrent* removal (i.e., removing an item while looping) in the *enhanced loop*

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
- Is it the 'norm' (or *best practice*) to define as many variables as possible upfront **or** do developers tend to define them as they need them (i.e., next to the code-block/region they are being used nearest)?
  - I've been trying to define all variables upfront so there is one clean/concise location for them... not sure if that is problematic
- Random question, however I am curious to learn about using VIM vs an IDE