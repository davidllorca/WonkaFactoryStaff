package com.wonka.staff.domain

import io.reactivex.Single

abstract class BaseUseCase<in Params, Results> {

    abstract fun execute(params: Params): Single<Results>

}
