# configJ

configJ is a Java library for easy to read data serialization. It was written to help in the creation of configuration files.

[View the Javadocs here](http://henry-anderson.github.io/configJ/)

Usage
=====
configJ consists of two main classes, ConfigFile, and ListFile. ConfigFile stores data in a key: value format while ListFile stores String lists line by line.

<h3>ConfigFile</h3>
When declaring a new instance of the ConfigFile class the constructor creates a new file if the file does not already exists. Here is some example usage:

```java
ConfigFile file = new ConfigFile("data", "txt");
file.set("key1", "value");
file.set("key2", 53);
file.set("key3", true);
file.save();
```

Will create or load an already existing file named data.txt and would look like this:

```
key1: value
key2: 53
key3: true
```

You can also get data from a file using the get methods. Here is an example:

```java
ConfigFile file = new ConfigFile("data", "txt");
int value = file.getInt("key2");
System.out.println("The returned value is: " + value);
```

This would output 53.
<h3>ListFile</h3>
The ListFile class is very simple and writes to a file line by line from a String ArrayList. Here is some example usage:

```java
ListFile file = new ListFile("list", "txt");
List<String> list = new ArrayList<String>();
list.add("This is line number one");
list.add("This is line number two");
file.write(list);
```

The output of this would look like:

```
This is line number one
This is line number two
```

You can also turn a file into a List<String> by using the read method as shown here:

```java
ListFile file = new ListFile("list", "txt");
List<String> list = file.read();
for(String line : list) {
    System.out.println(line);
}
```

Would print the following in the console:

```
This is line number one
This is line number two
```

<h3>MapFile</h3>
The MapFile class is a simple wrapper class that allows for quick and ledgible Map serilization. The MapFile class is simple and like the ListFile class it only consists of 5 methods. Here is some example usage:

```java
MapFile file = new MapFile("Map", "txt");
HashMap<String, Object> map = new HashMap<String, Object>();
map.put("Key 1", "This is a string value");
map.put("Key 2", 71);
map.put("Key 3", false);
file.write(map);
```

A file would be created named Map.txt and would look similar to the following.

```
Key 1: This is a string value
Key 2: 71
Key 3: false
```

<h3>DataUtils</h3>
DataUtils is a utility class. It contains static methods used for classic serialization and deserialization. You can use them like this:

```java
Data.serialize("This is a string object", "object", "ser");
System.out.println(Data.deserialize("object", "ser"));
```
