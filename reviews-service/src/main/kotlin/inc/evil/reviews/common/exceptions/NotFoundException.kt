package inc.evil.reviews.common.exceptions;

import kotlin.reflect.KClass

class NotFoundException(clazz: KClass<*>, property: String, propertyValue: String) :
    RuntimeException("${clazz.java.simpleName} with $property equal to [$propertyValue] could not be found!")
