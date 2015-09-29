# Break down big objects into multiple collections #

Each Pongo cannot be larger than 16MB (Mongo's restriction). Bear that in mind when you're designing the break-down of your objects and use multiple collections and non-containment references instead.

For example instead of keeping all the contents of a blog in one document:

```
@db
class BlogHostingService {
  val Blog[*] blogs;
}

class Blog {
  val Post[*] posts;
}

class Post {
  ...
}
```

you should consider using 2 collections and a non-containment reference (ref instead of val) instead:

```
@db
class BlogHostingService {
  val Blog[*] blogs;
  val Post[*] posts;
}

class Blog {
  ref Post[*] posts;
}

class Post {

}
```

Of course then you'll need to do a bit of house-keeping manually (i.e. delete all Posts when a Blog is deleted) but this shouldn't be that hard - and we may even provide [automated support for this at some point](https://code.google.com/p/pongo/issues/detail?id=6).

# Create collections for objects you want to refer to externally #

Nested objects (i.e. values of val features) cannot be referred to from external objects.

# Data types supported #

The following primitive data types are supported for your attrs

  * int
  * String
  * float
  * long
  * boolean

Custom datatypes are not supported yet.