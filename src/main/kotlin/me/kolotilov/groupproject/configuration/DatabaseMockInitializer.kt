package me.kolotilov.groupproject.configuration

import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.Role
import me.kolotilov.groupproject.domain.models.Traffic
import me.kolotilov.groupproject.domain.models.User
import me.kolotilov.groupproject.domain.services.ClientService
import me.kolotilov.groupproject.domain.services.UserService
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
private class DatabaseMockInitializer {

    @Autowired
    private lateinit var clientService: ClientService

    @Autowired
    private lateinit var userService: UserService

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
                    mac = "AA:AA:AA:AA:AA:AA",
                    ip = "255.255.255.255",
                    traffic = traffic(),
                    lastPaymentAt = DateTime.now().minusDays(1),
                    contractName = "Договор от 30.01.2021",
                    contractData = "",
                    owner = "Колотилов Ярослав Павлович",
                    loans = emptyList(),
                    contractNumber = 1337
                ),
            )
        }

        userService.apply {
            clear()
            createAll(
                User(
                    "admin",
                    "admin",
                    Role.ADMIN
                ),
                User(
                    "monty",
                    "monty",
                    Role.MONTY
                )
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