# Temporal Generators

Temporal generators provide the ability to generate [Java 8+ Temporal values](https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html). 
These are values related to time and dates.


### Temporal API
The Temporal generators follow the Temporal API. 
This API has three possible subsets depending on the type of temporal value being generated but all follow the same naming structure.
The Temporal API is as follows:

| Name           | Example Usage                      |
|----------------|------------------------------------|
| Distant Past   | `Generate.localDate.distantPast`   |
| Past           | `Generate.localDate.past`          |
| Recent         | `Generate.localDate.recent`        |
| Default        | `Generate.localDate.default`       |
| Soon           | `Generate.localDate.soon`          |
| Future         | `Generate.localDate.future`        |
| Distant Future | `Generate.localDate.distantFuture` |

<br>

The generation scale for each of these values is relative to the scale of Temporal value being generated.
For example: 
    
`Generate.localTime.distantPast` will be on the scale of time and therefore measured in hours.  
`Generate.localDateTime.distantPast` will be on the scale of dates and therefore measured in years.

As with all of the generators you can use the `apply` method to supply your own ranges.
In the case of the Temporal API the apply method takes two functions: `A => A`.
With `A` being the Temporal type you are generating. Use these to define relative based max and min:

```scala
Generate.localDate(min = _.minusDay(10), max = _.plusDays(20))
```


### Date Scale Based Generators
There are three date scale based generators.
These are temporal generators that include a date portion.
The date scale based generators are:

| Name          | Example Usage                    |
|---------------|----------------------------------|
| LocalDate     | `Generate.localDate.default`     |
| LocalDateTime | `Generate.localDateTime.default` |
| ZonedDateTime | `Generate.zonedDateTime.default` |

<br>

As discussed above the date scale based generators are measured in a scale of years (in the largest case).
Below are the ranges of the Temporal API for date scale based generators:

| Name           | Example Usage                                                         |
|----------------|-----------------------------------------------------------------------|
| Distant Past   | Greater than 2 years and less than or equal to 5 years in the past.   |
| Past           | Greater than 30 days and less than or equal to 2 years in the past.   |
| Recent         | Less than now and less than or equal to 30 days in the past.          |
| Default        | Between 30 days in the future and 30 days in the past.                |
| Soon           | Greater than now and less than or equal to 30 days in the future.     |
| Future         | Greater than 30 days and less than or equal to 2 years in the future. |
| Distant Future | Greater than 2 years and less than or equal to 5 years in the future. |


### Time Scale Based Generators
There is only one time scale based generator.
These are temporal generators that include time but no date portion.
The time scale based generators are:

| Name      | Example Usage                |
|-----------|------------------------------|
| LocalTime | `Generate.localTime.default` |

<br>

Time Scale generators are measured in a scale of hours (in the largest case).
Below are the ranges of the Temporal API for time scale based generators:

| Name           | Example Usage                                                               |
|----------------|-----------------------------------------------------------------------------|
| Distant Past   | Greater than 60 minutes and less than or equal to 6 hours in the past.      |
| Past           | Greater than 60 seconds and less than or equal to 60 minutes in the past.   |
| Recent         | Less than now and less than or equal to 60 seconds in the past.             |
| Default        | Between 60 seconds in the future and 60 seconds in the past.                |
| Soon           | Greater than now and less than or equal to 60 seconds in the future.        |
| Future         | Greater than 60 seconds and less than or equal to 60 minutes in the future. |
| Distant Future | Greater than 60 minutes and less than or equal to 6 hours in the future.    |


### Instant Generator
The instant generator is a unique case within the temporal generators.
It is the generator which powers all other temporal generators in TestCharged.
It does include both date and time but it is unique in the Java 8+ temporal values.
The instant generator is below:

| Name    | Example Usage              |
|---------|----------------------------|
| Instant | `Generate.instant.default` |

<br>

The instant generator is unique in its Temporal API ranges.
Below are the ranges for the instant generator:

| Name           | Example Usage                                                                |
|----------------|------------------------------------------------------------------------------|
| Distant Past   | Greater than 120 days and less than or equal to 365 days in the past.        |
| Past           | Greater than 30 days and less than or equal to 120 days in the past.         |
| Recent         | Less than now and less than or equal to 30 days in the past.                 |
| Default        | Between 30 days in the future and less than or equal to 30 days in the past. |
| Soon           | Greater than now and less than or equal to 30 days in the future.            |
| Future         | Greater than 30 days and less than or equal to 120 days in the future.       |
| Distant Future | Greater than 120 days and less than or equal to 365 days in the future.      |


## Now Provider
The default Temporal API is a relative based system. 
All dates are generated based off of `now`.
Now is determined by an object called a `NowProvider`.
By default the `NowProvider` will define now using `Clock.systemDefaultZone`.
This can be overridden using the `NowDsl`.

```scala
import com.garnercorp.testcharged.generators._

class TemporalTesting extends FunSuite with Matchers {
  // Define a NowProvider implicitly for this test suite
  // Generated will use this implicit NowProvider within this test
  implicit val nowForSuite = now(LocalDateTime.of(2018, Month.JUNE, 20, 10, 30))

  test("Override this test only based now provider") {
    // For this test I want to use a one time now provider for this case.
    // I can define it and use it right here
    Generate.localDateTime.distantPast(now(LocalDateTime.of(1990, Month.AUGUST, 20, 10, 10)))
  }
}
```

If you have a more complex case you can override NowProvider directly instead of using the `NowDsl` helper.
```scala
import com.garnercorp.testcharged.generators._

class TemporalTesting extends FunSuite with Matchers {
  // Define an entire NowProvider to handle any other cases you might encounter
  implicit val nowForSuite = new NowProvider {
    def apply(): Clock = 
      Clock.fixed(LocalDateTime.of(2018, Month.JUNE, 20, 10, 30).toInstant(ZoneOffset.UTC), ZoneOffset.UTC)
  }
}
```
