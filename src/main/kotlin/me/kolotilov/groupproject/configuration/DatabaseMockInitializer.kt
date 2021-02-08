package me.kolotilov.groupproject.configuration

import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.Tariff
import me.kolotilov.groupproject.domain.models.Traffic
import me.kolotilov.groupproject.domain.services.ClientService
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
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
                            phone = "89167702012",
                            email = "yapkolotilov@gmail.com",
                            registeredAt = DateTime.now().minusDays(10),
                            tariffs = listOf(
                                    Tariff(
                                            mac = "AA:AA:AA:AA:AA:AA",
                                            ip = "255.255.255.255",
                                            traffic = traffic(),
                                            lastPaymentAt = DateTime.now().minusDays(1),

                                            name = "Тариф 'Молодость'",
                                            contractName = "Договор от 30.01.2021",
                                            contractData = "",
                                            owner = "Колотилов Ярослав Павлович",
                                    )
                            ),
                            loans = emptyList()
                    ),
            )
        }
    }

    private fun traffic() = listOf(
            Traffic(DateTime.now(), 500),
            Traffic(DateTime.now().minusDays(1), 500),
            Traffic(DateTime.now().minusDays(3), 500),
            Traffic(DateTime.now().minusWeeks(1), 500)
    )
}