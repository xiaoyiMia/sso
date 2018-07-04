package org.mars.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A bean validation annotation to declare that annotated elements cannot be {@code null}.
 * Leverages JSR 303 annotations to indicate nullability in Java to common tools with
 * JSR 303 support.
 * 
 * <p>Should be used at parameter and field level. Method overrides should
 * repeat parent {@code @NotNull} annotations unless they behave differently.
 * 
 * @author Ting Wang
 *
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
}
