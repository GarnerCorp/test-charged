# TestCharged
[![Scala Versions](https://img.shields.io/badge/scala-2.12%20%7C%202.13%20%7C%203.6-blue.svg?style=flat-square)](https://github.com/GarnerCorp/test-charged/blob/73a618b69fbed9f6bb5b1bb75874d3d44efe171c/build.sbt#L11)

A small library with helpers for generating test data through a simple DSL.

You can super charge water.  You should also super charge your tests.

## Quick Start

Simply add that dependency to your SBT file:

```scala 
libraryDependencies += "com.garnercorp" %% "test-charged" % "0.1.17"
```

## Documentation

### Required Import
```scala
import com.garnercorp.testcharged.generators._
```

### Concrete Values
I often found I wanted generated values for my tests because I did not want to care about the actual data. The TestCharged generator DSL allows you to do this quickly and easily from any ScalaCheck Gen object.

```scala
Generate.alpha.value               // String
Generate.alpha.option              // Option[String] - Could be Some or None
Generate.alpha.some                // Some(String) - Will always evaluate to Some
Generate.alpha.seq                 // Seq[String] - Length is arbitrary
Generate.alpha.nonEmptySeq         // Seq[String] - Will never be empty
Generate.alpha.seqOf(size = 3)     // Seq[String] - Will be of size 3
```

This same DSL can be used to transform a simple `Gen[String]` into more complex generators:

```scala
Generate.alpha.gen.option              // Gen[Option[String]] - Generated option could be Some or None
Generate.alpha.gen.some                // Gen[Some(String)] - Generated option will always evaluate to Some
Generate.alpha.gen.seq                 // Gen[Seq[String]] - Generated sequence's length is arbitrary
Generate.alpha.gen.nonEmptySeq         // Gen[Seq[String]] - Generated sequence will never be empty
Generate.alpha.gen.seqOf(size = 3)     // Gen[Seq[String]] - Generated sequence will be of size 3
```

### Provided Generators
There are many common use cases for generated data.  TestCharged attempts to provide a number of them.
You can access these generators through the Generate object which is part of the generators package.

For more information, see [Generator Examples](docs/README.md).

#### Available Generators
* [ID Generators](docs/id-generators.md)
* [Numeric Generators](docs/numeric-generators.md)
* [Other Generators](docs/other-generators.md)
* [String Generators](docs/string-generators.md)
* [Temporal Generators](docs/temporal-generators.md)

#### Size API
All basic generators conform to the SizeApi which defines 5 default generation sizes:

```scala
Generate.alpha.tiny       // Smallest set of data
Generate.alpha.small      // Data is still readable but larger than tiny.
Generate.alpha.default    // If you don't know what to use, use this.
Generate.alpha.large      // Data is large.  Not human readable.
Generate.alpha.huge       // Largest possible data generation.
```

## Releasing
TestCharged uses the [sonatype SBT plugin](https://github.com/xerial/sbt-sonatype) to do releases to Maven Central.

See [.github/workflows/ci.yml](.github/workflows/ci.yml) for steps on how to release a version
