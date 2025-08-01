package dev.riskiana;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packagesOf = Application.class, importOptions = DoNotIncludeTests.class)
public class DemoArchitectureTests {

  @ArchTest
  ArchRule ControllerNaming = classes()
      .that().areAnnotatedWith(RestController.class)
      .should().haveSimpleNameEndingWith("Controller");


}
