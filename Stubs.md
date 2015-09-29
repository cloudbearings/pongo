# Stubs #

To enable referring to Pongos defined outside the Emfatic domain model, Pongo provides support for `stubs`. A stub is an Emfatic EClass that has been annotated with `@stub(javaClass="...")`. Wherever the stub is referenced/extended in the domain model, it will be replaced with the respective `javaClass` (which must extend the Pongo base class) in the generated code. For example the following domain model

```
package com.googlecode.pongo.tests.stubs.model;

@db
class GoogleCode {
   val GoogleProject[*] projects;
   val Developer[*] developers;
}

class GoogleProject extends Project {
   attr String name;
   ref Developer[*] developers;
}

@stub(javaClass="com.googlecode.pongo.tests.stubs.model.ProjectFromAnotherLibrary")
class Project {
   
}

@stub(javaClass="com.googlecode.pongo.tests.stubs.model.DeveloperFromAnotherLibrary")
class Developer {
   
}
```

generates code like this:

```
package com.googlecode.pongo.tests.stubs.model;

import java.util.List;

import com.googlecode.pongo.runtime.PongoList;
import com.mongodb.BasicDBList;


public class GoogleProject extends com.googlecode.pongo.tests.stubs.model.ProjectFromAnotherLibrary {
	
	protected List<com.googlecode.pongo.tests.stubs.model.DeveloperFromAnotherLibrary> developers = null;
	
	
	public GoogleProject() { 
		super();
		dbObject.put("developers", new BasicDBList());
	}
	
	public String getName() {
		return parseString(dbObject.get("name")+"", "");
	}
	
	public GoogleProject setName(String name) {
		dbObject.put("name", name + "");
		notifyChanged();
		return this;
	}
	
	
	public List<com.googlecode.pongo.tests.stubs.model.DeveloperFromAnotherLibrary> getDevelopers() {
		if (developers == null) {
			developers = new PongoList<com.googlecode.pongo.tests.stubs.model.DeveloperFromAnotherLibrary>(this, "developers", false);
		}
		return developers;
	}
	
	
}
```