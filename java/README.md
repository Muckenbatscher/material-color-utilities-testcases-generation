# JSON testcase generation

Build and run the generator with an output directory:

```sh
mvn --batch-mode --no-transfer-progress package dependency:build-classpath \
  -Dmdep.outputFile=target/runtime-classpath.txt
java -cp "target/classes:$(cat target/runtime-classpath.txt)" MyTest ./generated
```

The generator writes one JSON array per variant, spec version, platform, mode,
and contrast level:

```text
<output-dir>/<variant>/spec<year>/<platform>/<mode>/<contrast-level>.json
```

Each array entry represents one testcase and has `name`, `inputs`, and
`outputs` properties. Color values are `#RRGGBB` strings, `spec_version` is a
year number, and `contrast_level` is the numeric value used to generate the
scheme (`0`, `0.5`, or `1`). CMF testcases that use a second source color also
include `second_color` in `inputs`.
