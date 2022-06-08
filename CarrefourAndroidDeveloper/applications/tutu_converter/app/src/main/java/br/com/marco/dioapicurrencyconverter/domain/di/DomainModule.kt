package dev.stoneworks.tutu_converter.domain.di

import dev.stoneworks.tutu_converter.domain.DeleteExchangeUseCase
import dev.stoneworks.tutu_converter.domain.GetExchangeValueUseCase
import dev.stoneworks.tutu_converter.domain.ListExchangeUseCase
import dev.stoneworks.tutu_converter.domain.SaveExchangeUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModules())
    }

    private fun useCaseModules(): Module {
        return module {
            factory { ListExchangeUseCase(get()) }
            factory { SaveExchangeUseCase(get()) }
            factory { GetExchangeValueUseCase(get()) }
            factory { DeleteExchangeUseCase(get()) }
        }
    }
}