package ro.orange.eshop.userordermanagement.common

@Target(AnnotationTarget.FUNCTION)
annotation class RunSql(val scripts: Array<String>)
