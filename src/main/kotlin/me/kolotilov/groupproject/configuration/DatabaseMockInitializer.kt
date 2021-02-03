package me.kolotilov.groupproject.configuration

import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.Traffic
import me.kolotilov.groupproject.domain.services.ClientService
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
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
                            traffic = traffic(),
                            lastPaymentAt = DateTime.now().minusDays(1),
                            registeredAt = DateTime.now().minusDays(10)
                    ),
            )
        }
    }

    private fun traffic() = listOf<Traffic>(
            Traffic(DateTime.now(), 500),
            Traffic(DateTime.now().minusDays(1), 500),
            Traffic(DateTime.now().minusDays(3), 500),
            Traffic(DateTime.now().minusWeeks(1), 500)
    )


}