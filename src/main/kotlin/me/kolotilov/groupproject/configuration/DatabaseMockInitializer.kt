package me.kolotilov.groupproject.configuration

import me.kolotilov.groupproject.domain.models.*
import me.kolotilov.groupproject.domain.services.ClientService
import me.kolotilov.groupproject.domain.services.TariffService
import me.kolotilov.groupproject.domain.services.UserService
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
private class DatabaseMockInitializer(
    private val clientService: ClientService,
    private val userService: UserService,
    private val tariffService: TariffService
) {

    @PostConstruct
    fun fillData() {
        val tariffs = listOf(
            Tariff(
                name = "GePON-10",
                speed = 10,
                price = 629
            ),
            Tariff(
                name = "GePON-15",
                speed = 15,
                price = 690
            ),
            Tariff(
                name = "GePON-30",
                speed = 30,
                price = 890
            ),
            Tariff(
                name = "GePON-50",
                speed = 50,
                price = 990
            ),
            Tariff(
                name = "GePON-100",
                speed = 100,
                price = 1290
            ),
            Tariff(
                name = "Добровольная блокировка",
                speed = 0,
                price = 180
            )
        )
        tariffService.apply {
            clear()
            createAll(*tariffs.toTypedArray())
        }

        clientService.apply {
            clear()
            createAll(
                Client(
                    name = "Колотилов Ярослав",
                    balance = 9999,
                    enabled = true,
                    phone = "8(916)-330-123-54",
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
                    contractNumber = 1337,
                    tariff = tariffs[0]
                ),
                Client(
                    name = "Белавенцев Валерий",
                    balance = 4321,
                    enabled = true,
                    phone = "8(916)-135-431-87",
                    email = "vbelaventsev@gmail.com",
                    registeredAt = DateTime.now().minusDays(5),
                    mac = "AA:AA:AA:AA:AA:AA",
                    ip = "255.255.255.255",
                    traffic = traffic(),
                    lastPaymentAt = DateTime.now().minusDays(1),
                    contractName = "Договор от 12.01.2021",
                    contractData = "",
                    owner = "Белавенцев Валерий",
                    loans = emptyList(),
                    contractNumber = 1338,
                    tariff = tariffs[1]
                ),
                Client(
                    name = "Корниенко Олег",
                    balance = 1234,
                    enabled = true,
                    phone = "8(916)-546-54-65",
                    email = "okornienko@gmail.com",
                    registeredAt = DateTime.now().minusDays(4),
                    mac = "AA:AA:AA:AA:AA:AA",
                    ip = "255.255.255.255",
                    traffic = traffic(),
                    lastPaymentAt = DateTime.now().minusDays(1),
                    contractName = "Договор от 13.01.2021",
                    contractData = "",
                    owner = "Корниенко Олег",
                    loans = emptyList(),
                    contractNumber = 1339,
                    tariff = tariffs[2]
                ),
                Client(
                    name = "Новичкова Анастасия",
                    balance = 4312,
                    enabled = true,
                    phone = "8(916)-546-87-09",
                    email = "anovichkova@gmail.com",
                    registeredAt = DateTime.now().minusDays(3),
                    mac = "AA:AA:AA:AA:AA:AA",
                    ip = "255.255.255.255",
                    traffic = traffic(),
                    lastPaymentAt = DateTime.now().minusDays(1),
                    contractName = "Договор от 14.01.2021",
                    contractData = "",
                    owner = "Новичкова Анастасия",
                    loans = emptyList(),
                    contractNumber = 1340,
                    tariff = tariffs[3]
                )
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