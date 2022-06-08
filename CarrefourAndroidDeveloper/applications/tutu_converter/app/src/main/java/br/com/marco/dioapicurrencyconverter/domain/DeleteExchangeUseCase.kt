package dev.stoneworks.tutu_converter.domain

import dev.stoneworks.tutu_converter.core.UseCase
import dev.stoneworks.tutu_converter.data.model.ExchangeResponseValue
import dev.stoneworks.tutu_converter.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteExchangeUseCase (
    private val repository: CoinRepository
) : UseCase.NoSource<ExchangeResponseValue>() {

    override suspend fun execute(param: ExchangeResponseValue): Flow<Unit> {
        return flow {
            repository.delete(param)
            emit(Unit)
        }
    }
}