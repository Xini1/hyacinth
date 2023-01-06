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

3. Execute `run` task providing path to json file with groups as first argument

```bash
./gradlew :hyacinth-cli:run --args="$PWD/groups.json"
```

Note: you can provide seed to control randomness as second argument

```bash
./gradlew :hyacinth-cli:run --args="$PWD/groups.json 123"
```
