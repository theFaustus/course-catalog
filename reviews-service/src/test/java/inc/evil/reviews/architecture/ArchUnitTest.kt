package inc.evil.reviews.architecture

import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.GeneralCodingRules
import org.springframework.data.relational.core.mapping.Table
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController

@AnalyzeClasses(packages = ["inc.evil"], importOptions = [ImportOption.DoNotIncludeTests::class])
class ArchUnitTest {

    @ArchTest
    val jpaRepositoriesMustBeAnnotatedWithTheRepositoryAnnotation: ArchRule = ArchRuleDefinition.classes()
        .that()
        .areInterfaces()
        .and()
        .haveNameMatching(".*Repository")
        .and()
        .areAssignableTo(org.springframework.data.repository.Repository::class.java)
        .should()
        .beAnnotatedWith(Repository::class.java)

    @ArchTest
    val jpaRepositoriesClassNameMustHaveRepositorySuffix: ArchRule = ArchRuleDefinition.classes()
        .that()
        .areInterfaces()
        .and()
        .areAssignableTo(org.springframework.data.repository.Repository::class.java)
        .should()
        .haveNameMatching(".*Repository")

    @ArchTest
    val servicesClassNamesMustHaveServiceOrServiceImplSuffix: ArchRule = ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(Service::class.java)
        .should()
        .haveNameMatching("(.*ServiceImpl)|(.*Service)")

    @ArchTest
    val controllerClassNamesMustHaveControllerOrControllerImplSuffix: ArchRule = ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(Controller::class.java)
        .should()
        .haveNameMatching("(.*ControllerImpl)|(.*Controller)")

    @ArchTest
    val graphqlControllersAreInTheControllerPackage: ArchRule = ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(Controller::class.java)
        .should()
        .resideInAPackage("..web.graphql")

    @ArchTest
    val servicesAreInTheServicePackage: ArchRule = ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(Service::class.java)
        .should()
        .resideInAPackage("..service.impl")

    @ArchTest
    val repositoriesAreInTheRepositoryPackage: ArchRule = ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(Repository::class.java)
        .should()
        .resideInAPackage("..repo")

    @ArchTest
    val entitiesAreInTheModelPackage: ArchRule = ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(Table::class.java)
        .should()
        .resideInAPackage("..model")

    @ArchTest
    val controllersShouldNotTalkToRepositories: ArchRule = ArchRuleDefinition.noClasses()
        .that()
        .resideInAPackage("..web..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("..repo..")

    @ArchTest
    val controllersShouldNotTalkToEntities: ArchRule = ArchRuleDefinition.noClasses()
        .that()
        .areAnnotatedWith(RestController::class.java).or().areAnnotatedWith(Controller::class.java)
        .should()
        .dependOnClassesThat()
        .areAnnotatedWith(Table::class.java)

    @ArchTest
    val loggingLibraryShouldBeUsedInsteadOfSystemOut = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
        .because("The preferred way of logging is via a logging library like logback")

}
