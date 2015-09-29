# 5-minute tutorial #

This tutorial assumes that you have [MongoDB](http://www.mongodb.org/) up and running, you've [downloaded](https://code.google.com/p/pongo/downloads/list) a copy of Pongo, you know how to run Java JAR files from the command line, and you're working in Eclipse.

  * Create an Eclipse Java project called `com.googlecode.pongo.examples.miniblog`
  * Create a folder called `lib` into it and place there a copy of `pongo-and-dependencies.jar`
  * Right-click `pongo-runtime-and-dependencies.jar` and select `Build path->Add to Build Path`
  * Create a package called `com.googlecode.pongo.examples.miniblog.model`
  * Inside the package create a file called `miniblog.emf` and copy-paste the following code into it

```
package com.googlecode.pongo.examples.miniblog.model;

@db
class Blog {
    val Post[*] posts;
    val Author[*] authors;
}

class Post {
    @searchable
    attr String title;
    attr String body;
    ref Author author;
}

class Comment {
    attr String from;
    attr String body;
    val Comment[*] replies;
}

class Author {
    @searchable
    attr String name;
    attr String email;
}
```
  * Run the Pongo generator from the command line as follows: `java -jar pongo.jar miniblog.emf` (you'll need to specify the full path of `miniblog.emf` here)
  * Right-click your Java project in Eclipse and select `Refresh`. If everything has gone well, you should see a bunch of Java classes next to `miniblog.emf`
  * Create a new class called `App` under the `com.googlecode.pongo.examples.miniblog` package
  * Copy and paste the following code into your `App` class

```
package com.googlecode.pongo.examples.minigblog;

import java.util.*;
import com.mongodb.*;
import com.googlecode.pongo.runtime.*;
import com.googlecode.pongo.examples.miniblog.model.*;

public class App {
    
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.writeStuff();
        app.readStuff();
    }
    
    public void writeStuff() throws Exception {
        Mongo mongo = new Mongo();
        mongo.dropDatabase("blog");
        Blog blog = new Blog(mongo.getDB("blog"));

        Post post = new Post();
        post.setTitle("A post");
        
        Author author = new Author();
        author.setName("Joe Doe");
        blog.getAuthors().add(author);
        post.setAuthor(author);
        
        Comment comment = new Comment();
        comment.setText("A comment");
        post.getComments().add(comment);
        
        Comment reply = new Comment();
        reply.setText("A reply");
        comment.getReplies().add(reply);
        
        blog.getPosts().add(post);

        // Syncs with the underlying database
        blog.sync(true);    	
    }
    
    public void readStuff() throws Exception {

    	Mongo mongo = new Mongo();
        Blog blog = new Blog(mongo.getDB("blog"));
        
    	System.out.println(blog.getPosts().size());
    	
    	for (Post post : blog.getPosts()) {
    	    System.out.println(post.getTitle() + " - " + post.getAuthor().getName());
    	    System.out.println(post.getComments().size() + " comment(s)");
    	}
    	
        // Generated from the @searchable title attribute of Post
        System.out.println(blog.getPosts().findOneByTitle("A post").getAuthor().getName());
    }
    
}
```

  * Run the `App` class