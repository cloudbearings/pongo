# Querying: Zoo Example #

This tutorial assumes that you have [MongoDB](http://www.mongodb.org/) up and running, you've [downloaded](https://code.google.com/p/pongo/downloads/list) a copy of Pongo, you know how to run Java JAR files from the command line, and you're working in Eclipse.

  * Create an Eclipse Java project called `com.googlecode.pongo.examples.zoo`
  * Create a folder called `lib` into it and place there a copy of `pongo-and-dependencies.jar`
  * Right-click `pongo-runtime-and-dependencies.jar` and select `Build path->Add to Build Path`
  * Create a package called `com.googlecode.pongo.examples.zoo.model.
  * Inside the package create a file called `zoo.emf` and copy-paste the following code into it

```
package com.googlecode.pongo.examples.zoo.model;

@db
class Zoo {
	val Animal[*] animals;
	val Building[*] buildings;
	val Staff[*] staff;
}

abstract class Animal {
	attr String species;
	@searchable
	attr String name;
	attr double weight;
	attr double height;
	attr int age;
	attr String origin;
}

abstract class Mammal extends Animal { }
abstract class Amphibian extends Animal { }
abstract class Reptile extends Animal { }
abstract class Bird extends Animal { }
abstract class Fish extends Animal { }

class Monkey extends Mammal { }
class Tiger extends Mammal { }
class Zebra extends Mammal { }
class Shark extends Fish { }

abstract class Building {
	attr String name;
}

class Cafe extends Building { }
class TicketOffice extends Building { }
class Enclosure extends Building { }

abstract class Staff {
	attr String name;
	attr String address;
	attr int age;
}

class Keeper extends Staff {
	ref Animal[*] animals;
}

class GeneralStaff extends Staff {
	ref Building[*] buildings;
}
```

  * Run the Pongo generator from the command line as follows: `java -jar pongo.jar zoo.emf` (you'll need to specify the full path of `miniblog.emf` here)
    * Right-click your Java project in Eclipse and select `Refresh`. If everything has gone well, you should see a bunch of Java classes next to `zoo.emf`
    * Create a new class called `App` under the `com.googlecode.pongo.examples.zoo` package
    * Copy and paste the following code into your `App` class

```
public class App {
	public static void main(String[] args) throws Exception {
		// Initialise MongoDB
		Mongo mongo = new Mongo();
		mongo.dropDatabase("zoo"); // Ensure a clean run
		
		// Create our zoo database
		Zoo zoo = new Zoo(mongo.getDB("zoo"));
		
		// Add some animals
		Monkey monkey= new Monkey();
		monkey.setName("Steve");
		monkey.setWeight(5);
		monkey.setOrigin("Africa");
		zoo.getAnimals().add(monkey);
		
		Monkey monkey2= new Monkey();
		monkey2.setName("John");
		monkey2.setWeight(4);
		monkey2.setOrigin("Asia");
		zoo.getAnimals().add(monkey2);
		
		Tiger tiger = new Tiger();
		tiger.setName("Steve");
		tiger.setWeight(15);
		tiger.setOrigin("India");
		zoo.getAnimals().add(tiger);
		
		Zebra zebra = new Zebra();
		zebra.setName("George");
		zebra.setWeight(12);
		zebra.setOrigin("Africa");
		zoo.getAnimals().add(zebra);
		
		Shark shark = new Shark();
		shark.setName("Steve");
		shark.setWeight(20);
		shark.setOrigin("Pacific Ocean");
		zoo.getAnimals().add(shark);
		
		zoo.sync();
		
		// Q1: Find all animals from Africa
		zoo.getAnimals().find(Animal.ORIGIN.eq("Africa")); // Returns two
		
		// Q2: Find all animals from Africa who are heavier than 10
		zoo.getAnimals().find(Animal.ORIGIN.eq("Africa"), Animal.WEIGHT.greaterThan(10)); // Returns one
		
		// Q3: Find all animals named Steve
		zoo.getAnimals().find(Animal.NAME.eq("Steve")); // Returns three
		
		// Q4: Find all mammals named Steve
		zoo.getAnimals().find(Mammal.NAME.eq("Steve")); // Returns two 
		
		// Q5: Find all mammals named Steve who are heavier than 10
		zoo.getAnimals().find(Mammal.NAME.eq("Steve"), Mammal.WEIGHT.greaterThan(10)); // Returns one
		
		// Q6: Find all mammals named Steve, who weigh 5 or more
		zoo.getAnimals().find(Mammal.NAME.eq("Steve"), Mammal.WEIGHT.greaterThanOrEqualTo(5)); // Returns two
		
		// Q7: Find all mammals named Steve, who weigh more than 5, and are from Africa 
		zoo.getAnimals().find(Mammal.NAME.eq("Steve"), Mammal.WEIGHT.greaterThanOrEqualTo(5), Mammal.ORIGIN.eq("Africa")); // Returns one
	}
	
}

```

  * Run the `App` class