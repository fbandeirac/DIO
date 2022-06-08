package dev.stoneworks.tutu_converter.domain

import dev.stoneworks.tutu_converter.core.UseCase
import dev.stoneworks.tutu_converter.data.model.ExchangeResponseValue
import dev.stoneworks.tutu_converter.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class ListExchangeUseCase(
    private val repository: CoinRepository
) : UseCase.NoParam<List<ExchangeResponseValue>>() {

    override suspend fun execute(): Flow<List<ExchangeResponseValue>> {
        return repository.list()
    }
}