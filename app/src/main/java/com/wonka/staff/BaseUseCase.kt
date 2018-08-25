package com.wonka.staff

abstract class BaseUseCase<in Params, out Results> {

    abstract fun execute(params: Params): Results

}
