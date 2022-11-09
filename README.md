# cron-expression-parser
Java command line application which parses a cron string and expands each field to show the times at which it will run.

## Project Structure
```
Application code in src/main directory
Unit Tests in src/test directory
```

## Instructions

1. Install JDK 11 or above on system
2. Build the project .jar using mvn clean package from src directory
3. Execute the following command on terminal from target directory

```
$ java -jar cron-expression-parser-1.0-SNAPSHOT.jar */15 0 1,15 * 1-5 /usr/bin/find
```

## Output

The output should be formatted as a table with the field name taking the first 14 columns and the times as a space-separated list following it.

For example, the following input argument:
```
*/15 0 1,15 * 1-5 /usr/bin/find
```

Should yield the following output:
```
minute         0 15 30 45
hour           0
day of month   1 15
month          1 2 3 4 5 6 7 8 9 10 11 12
day of week    1 2 3 4 5
command        /usr/bin/find
```

