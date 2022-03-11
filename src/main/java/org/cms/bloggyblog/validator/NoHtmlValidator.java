package org.cms.bloggyblog.validator;

import org.cms.bloggyblog.annotation.NoHtml;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {

  private static final PolicyFactory policy = new HtmlPolicyBuilder().toFactory();

  @Override
  public void initialize(NoHtml constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String input, ConstraintValidatorContext context) {
    return policy.sanitize(input).equals(input);
  }
}
