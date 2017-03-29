# SimpleHashMap

## Introduction
This is an implementation of Simple HashMap using Java array and Java Linked data sturcture.
## Tricky Part
Java use an array, let's say the name of the array is "table", to store key value pairs.
The tricky part is how Java decides at which position(i.e. index) Java puts them.
The answer to this quesiton is a simple line of code.
```java
int hash = key == null ? 0 : key.hashCode();
int len = table.length;
table[hash & len - 1]// This is the position to put spesific (key, value) pair.
```
But why?
Acutally, this is a simple math problem. We know that the result of 
```java
hash & len - 1
```
is smaller than len so that we will not get ArrayIndexOutOfBounds Exception.

But, still, why not use 
```java
hash % len
```
This is probably because the designer of original hash map wants to make sure that the first element of table should always be null and null only.
In other words, there will be no nodes linked afte null if we do have null as key.

