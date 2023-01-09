# How to use

1. Create an example json file `groups.json` containing groups with students

```json
[
  {
    "name": "green",
    "studentNames": [
      "Max",
      "Ann"
    ]
  },
  {
    "name": "red",
    "studentNames": [
      "Matt",
      "John"
    ]
  },
  {
    "name": "blue",
    "studentNames": [
      "Alex"
    ]
  }
]
```

3. Build a fat jar

```bash
./gradlew :hyacinth-cli:fatJar
```

4. Run created jar
```bash
java -jar ./hyacinth-cli/build/libs/hyacinth-cli-1.0-SNAPSHOT-fat.jar groups.json
```

Example of output:

```text
seed: 1482392023
pairs:
1) Max (green) vs Alex (blue)
2) John (red) vs Ann (green)
remaining: Matt (red)
```

Note: you can provide seed to control randomness as second argument

```bash
java -jar ./hyacinth-cli/build/libs/hyacinth-cli-1.0-SNAPSHOT-fat.jar groups.json 123

```
