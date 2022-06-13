# cronparser

Java version 1.8 and above supported

The script can be executed as follows:

java -jar cronparser.jar  "*/15 0 1,15 * 1-5 /usr/bin/find"

Output:

minute        0 15 30 45

hour          0

day of month  1 15

month         1 2 3 4 5 6 7 8 9 10 11 12

day of week   1 2 3 4 5

command       /usr/bin/find
