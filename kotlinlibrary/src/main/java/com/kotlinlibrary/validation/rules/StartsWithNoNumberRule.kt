package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.validation.ValidatedObservableField

class StartsWithNoNumberRule(var errorMsg: String = "Should not start with any number.") : BaseRule {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false

        return !ValidatedObservableField(text).regex("^(\\d+.*|-\\d+.*)").check()
    }

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}