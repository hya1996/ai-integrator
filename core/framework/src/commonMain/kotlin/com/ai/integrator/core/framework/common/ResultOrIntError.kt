package com.ai.integrator.core.framework.common

sealed interface ResultOrIntError<out T> {
    data class Success<T>(val result: T) : ResultOrIntError<T>

    data class Failure(
        val code: Int,
        val message: String = ""
    ) : ResultOrIntError<Nothing>
}

inline fun <T, R> ResultOrIntError<T>.map(
    transform: (T) -> R
): ResultOrIntError<R> {
    return when (this) {
        is ResultOrIntError.Success -> ResultOrIntError.Success(transform(result))
        is ResultOrIntError.Failure -> this
    }
}

inline fun <T> ResultOrIntError<T>.onSuccess(
    action: (T) -> Unit
): ResultOrIntError<T> {
    if (this is ResultOrIntError.Success) {
        action(result)
    }
    return this
}

inline fun <T> ResultOrIntError<T>.onFailure(
    action: (Int, String) -> Unit
): ResultOrIntError<T> {
    if (this is ResultOrIntError.Failure) {
        action(code, message)
    }
    return this
}