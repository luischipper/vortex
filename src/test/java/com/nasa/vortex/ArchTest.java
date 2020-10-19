package com.nasa.vortex;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.nasa.vortex");

        noClasses()
            .that()
            .resideInAnyPackage("com.nasa.vortex.service..")
            .or()
            .resideInAnyPackage("com.nasa.vortex.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.nasa.vortex.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
