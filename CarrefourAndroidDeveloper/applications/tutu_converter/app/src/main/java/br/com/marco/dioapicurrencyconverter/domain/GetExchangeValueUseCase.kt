package dev.stoneworks.tutu_converter.domain

import dev.stoneworks.tutu_converter.core.UseCase
import dev.stoneworks.tutu_converter.data.model.ExchangeResponseValue
import dev.stoneworks.tutu_converter.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class GetExchangeValueUseCase(
    private val repository: CoinRepository
) : UseCase<String, ExchangeResponseValue>() {

    override suspend fun execute(param: String): Flow<ExchangeResponseValue> {
        return repository.getExchangeValue(param)
    }

}