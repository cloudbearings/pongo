# Adding Custom Code #

To add custom code to one of your generated Pongo classes, you will need to use the `@customize` annotation in your Emfatic data model definition. For example

```
@customize
class Post {
	@searchable
	attr String title;
	attr String[*] tags;
	val Comment[*] comments;
	ref Author author;
	val Stats stats;
}
```

will generate the following code

```
...
import java.util.List;
import com.googlecode.pongo.runtime.Pongo;
import com.googlecode.pongo.runtime.PongoList;
import com.googlecode.pongo.runtime.PrimitiveList;
import com.googlecode.pongo.runtime.PongoFactory;

// protected region custom-imports on begin
// protected region custom-imports end

public class Post extends Pongo {
	
	protected List<String> tags = null;
	protected List<Comment> comments = null;
	protected Author author = null;
	protected Stats stats = null;
	
	// protected region custom-fields-and-methods on begin
	// protected region custom-fields-and-methods end
	...
```

Any code you add between the `// protected region begin/end` lines will be preserved when you regenerate the code. For example, you can override the preSave method of Pongo

```
...
import java.util.List;
import com.googlecode.pongo.runtime.Pongo;
import com.googlecode.pongo.runtime.PongoList;
import com.googlecode.pongo.runtime.PrimitiveList;
import com.googlecode.pongo.runtime.PongoFactory;

// protected region custom-imports on begin
// protected region custom-imports end

public class Post extends Pongo {
	
	protected List<String> tags = null;
	protected List<Comment> comments = null;
	protected Author author = null;
	protected Stats stats = null;
	
	// protected region custom-fields-and-methods on begin
	public void preSave() {
		System.err.println("About to save post " + getTitle());
	}
	// protected region custom-fields-and-methods end
	...
```