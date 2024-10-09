package in.ushatech.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)  // Retain in bytecode but not available at runtime
@Target(ElementType.TYPE)          // Can be applied to classes and interfaces
public @interface NotThreadSafe {
}
