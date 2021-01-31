package me.kolotilov.groupproject.configuration

import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.Traffic
import me.kolotilov.groupproject.domain.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
private class DatabaseMockInitializer {

    @Autowired
    private lateinit var clientService: ClientService

    @PostConstruct
    fun fillData() {
        clientService.apply {
            clear()
            createAll(
                    Client(
                            name = "Колотилов Ярослав Павлович",
                            balance = 19980906,
                            enabled = true,
                            tariff = "Тариф 'Молодость'",
                            contractName = "Договор от 30.01.2021",
                            contractData = "",
                            owner = "Колотилов Ярослав Павлович",
                            phone = "89167702012",
                            email = "yapkolotilov@gmail.com",
                            mac = "AA:AA:AA:AA:AA:AA",
                            ip = "255.255.255.255",
                            traffic = traffic()
                    ),
            )
        }
    }

    private fun traffic() = listOf<Traffic>(
            Traffic(Date(), 500),
            Traffic(dayAgo(1), 500),
            Traffic(dayAgo(2), 500),
            Traffic(dayAgo(3), 500)
    )

    private fun dayAgo(days: Int): Date {
        return Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) - days)
        }.time
    }
}