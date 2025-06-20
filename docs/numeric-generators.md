# Numeric Generators

Numeric generators provide an extra ability to choose the sign of the generated value.
The following methods are provided to do so:

```scala
Generate.int.default.positive     // Generates a positive numeric value.
Generate.int.default.negative     // Generates a negative numeric value.
Generate.int.default.default      // The generated value could be positive or negative.
```

Below are the provided basic numeric generators.

| Name   | Description               | Code              |
|--------|---------------------------|-------------------|
| Short  | Generates short values.   | `Generate.short`  |
| Int    | Generates integer values. | `Generate.int`    |
| Long   | Generates long values.    | `Generate.long`   |
| Double | Generates double values.  | `Generate.double` |
| Float  | Generates float values.   | `Generate.float`  |

### Example
An example of generating a positive integer value:
```scala
Generate.int.default.positive.value
```
