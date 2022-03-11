package org.cms.bloggyblog.annotation;

import org.cms.bloggyblog.validator.NoHtmlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoHtmlValidator.class)
public @interface NoHtml {

  String message() default "No HTML is allowed";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
