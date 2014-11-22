DataAPI
=======
DataAPI is a Java API for easy to read data serialization. DataAPI was written by Skionz to allow easy configuration and a way to allow users to easily configure the properties of programs.

<h3>Updates</h3>
- Increased speed by 800%
- Created a format exception
- Added Comments
- Added the MapFile class

Usage
=====
DataAPI consists of two main classes, DataFile, and ListFile. DataFile stores data in a key: value format while ListFile stores String lists line by line.

<h3>DataFile</h3>
When declaring a new instance of the DataFile class the constructor creates a new file if the file does not already exists. Here is some example usage:
<br>
<pre>
DataFile file = new DataFile("data", "txt");
file.set("key1", "value");
file.set("key2", 53);
file.addComment("This is a comment");
file.set("key3", true);
file.save();
</pre>
Will create or load an already existing file named data.txt and would look like this:
<pre>
key1: value
key2: 53
#This is a comment
key3: true
</pre>

You can also get data from a file using the get methods. Here is an example:

<pre>
DataFile file = new DataFile("data", "txt");
int value = file.getInt("key2");
System.out.println("The returned value is: " + value);
</pre>

This would output 53. If the key is not found the method will return null.
<h3>ListFile</h3>
The ListFile class is very simple and writes to a file line by line from a String ArrayList. Here is some example usage:

<pre>
ListFile file = new ListFile("list", "txt");
ArrayList<String> list = new ArrayList<String>();
list.add("This is line number one");
list.add("This is line number two");
file.write(list);
</pre>

The output of this would look like:
<pre>
This is line number one
This is line number two
</pre>

You can also turn a file into an ArrayList<String> by using the read method as shown here:

<pre>
ListFile file = new ListFile("list", "txt");
ArrayList<String> list = file.read();
for(String line : list) {
    System.out.println(line);
}
</pre>

Would print the following in the console:
<pre>
This is line number one
This is line number two
</pre>

<h3>MapFile</h3>
The MapFile class is a simple wrapper class that allows for quick and ledgible Map serilization. The MapFile class is simple and like the ListFile class it only consists of 5 methods. Here is some example usage:
<pre>
MapFile file = new MapFile("Map", "txt");
HashMap<String, Object> map = new HashMap<String, Object>();
map.put("Key 1", "This is a string value");
map.put("Key 2", 71);
map.put("Key 3", false);
file.write(map);
</pre>

A file would be created named Map.txt and would look similar to the following.
<pre>
Key 1: This is a string value
Key 2: 71
Key 3: false
</pre>

<h3>DataUtils</h3>
DataUtils is a utility class. Data contains static methods used for classic serialization and deserialization. You can use them like this:

<pre>
Data.serialize("This is a string object", "object", "ser");
System.out.println(Data.deserialize("object", "ser"));
//will print "This is a string object" in the console
</pre>

Classes and Methods
====================
Here is a list of all of the classes and their methods.

<h4>DataFile</h4>
- addComment(String)
- addLine()
- clear()
- delete()
- getBoolean()
- getByte()
- getDouble()
- getFloat()
- getInt()
- getList()
- getLong()
- getShort()
- getString()
- isBoolean()
- isByte()
- isDouble()
- isFloat()
- isInt()
- isLong()
- isShort()
- keyList()
- remove()
- save()
- set(String, String)
- valueList()

<h4>ListFile</h4>
- addLine(String)
- clear()
- delete()
- write()
- read(ArrayList&lt;String&gt;)

<h4>MapFile</h4>
- clear()
- delete()
- put(String, String)
- write()
- read(Map&lt;String, String&gt;)
 
<h4>DataUtils</h4>
- createDataFile(String, String)
- createListFile(String, String)
- createMapFile(String, String)
- deserialize(String, String)
- fileExists(String, String)
- serialize(Object, String, String)