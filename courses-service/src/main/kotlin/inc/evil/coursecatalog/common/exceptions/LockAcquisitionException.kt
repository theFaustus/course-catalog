package inc.evil.coursecatalog.common.exceptions;

class LockAcquisitionException(lockName: String, operation: String) :
    RuntimeException("[$lockName] could not be acquired for [$operation] operation. Please try again.")
