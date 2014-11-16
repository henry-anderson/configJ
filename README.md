<h1>DataAPI</h1>
DataAPI is a Java API for easy to read data serialization. DataAPI was written by Skionz to allow easy configuration and a way to allow users to change properties of the program.
<h1>Usage</h1>
DataAPI consists of two main classes, DataFile, and ListFile. DataFile stores data in a key: value format while ListFile stores String lists line by line.
<h3>DataFile</h3>
When declaring a new instance of the DataFile class the constructor creates a new file if the file does not already exists. Here is some example usage:
<br>
```
DataFile file = new DataFile("data", "txt");
file.set("key1", "value");
file.set("key2", 53);
file.addComment("This is a comment");
file.set("key3", true);
```
Will create or load an already existing file named data.txt and would look like this:
<pre>
key1: value
key2: 53
#This is a comment
key3: true
</pre>

You can also get data from a file using the get methods. Here is an example:
```
DataFile file = new DataFile("data", "txt");
int value = file.getInt("key2");
System.out.println("The returned value is: " + value);
```
This would output 53. If the key is not found the method will return null.
<h3>ListFile</h3>
The ListFile class is very simple and writes to a file line by line from a String ArrayList. Here is some example usage:
```
ListFile file = new ListFile("list", "txt");
ArrayList<String> list = new ArrayList<String>();
list.add("This is line number one");
list.add("This is line number two");
file.write(list);
```
The output of this would look like:
<pre>
This is line number one
This is line number two
</pre>

You can also turn a file into an ArrayList<String> by using the read method as shown here:
```
ListFile file = new ListFile("list", "txt");
ArrayList<String> list = file.read();
for(String line : list) {
    System.out.println(line);
}
```
Would print the following in the console:
<pre>
This is line number one
This is line number two
</pre>

<h3>Data</h3>
Data is the final class. Data contains two simple static methods for serialization and deserialization. You can use them like this:
```
Data.serialize("This is a string object", "object", "ser");
System.out.println(Data.deserialize("object", "ser"));
//will print "This is a string object" in the console
```