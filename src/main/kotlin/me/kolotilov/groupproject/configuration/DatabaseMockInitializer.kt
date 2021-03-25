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
        fillDataImpl()
    }

    private fun fillDataImpl() {
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
        return

        val tariffs = arrayOf(
            Tariff(
                name = "GePON-10",
                speed = 10,
                price = 629,
            ),
            Tariff(
                name = "GePON-15",
                speed = 15,
                price = 690,
            ),
            Tariff(
                name = "GePON-30",
                speed = 30,
                price = 890,
            ),
            Tariff(
                name = "GePON-50",
                speed = 50,
                price = 990,
            ),
            Tariff(
                name = "GePON-100",
                speed = 100,
                price = 1290,
            ),
            Tariff(
                name = "Добровольная блокировка",
                speed = 0,
                price = 180,
            )
        )

        tariffService.apply {
            clear()
            createAll(*tariffs)
        }

        clientService.apply {
            clear()
            createAll(
                Client(
                    name = "Колотилов Ярослав",
                    balance = 19980906,
                    enabled = true,
                    phone = "89167702020",
                    email = "yapkolotilov@gmail.com",
                    registeredAt = DateTime.now().minusDays(10),
                    mac = "AA:AA:AA:AA:AA:AA",
                    ip = "255.255.255.255",
                    traffic = traffic(),
                    tariff = tariffs[0],
                    lastPaymentAt = DateTime.now().minusDays(1),
                    contractName = "Договор от 30.01.2021",
                    contractData = "",
                    owner = "Колотилов Ярослав Павлович",
                    loans = emptyList(),
                    contractNumber = 1337
                ),
                Client(
                    name = "Белавенцев Валерий",
                    balance = 20310,
                    enabled = true,
                    phone = "8197702021",
                    email = "vbelaventsev@gmail.com",
                    registeredAt = DateTime.now().minusDays(20),
                    mac = "AA:AA:AA:AA:AA:AA",
                    ip = "255.255.255.255",
                    traffic = traffic(),
                    tariff = tariffs[1],
                    lastPaymentAt = DateTime.now().minusDays(3),
                    contractName = "Договор от 12.01.2021",
                    contractData = "",
                    owner = "Белавенцев Валерий",
                    loans = emptyList(),
                    contractNumber = 1338
                ),
                Client(
                    name = "Корниенко Олег",
                    balance = 123456,
                    enabled = true,
                    phone = "89167702008",
                    email = "okornienko@gmail.com",
                    registeredAt = DateTime.now().minusDays(4),
                    mac = "AA:AA:AA:AA:AA:AA",
                    ip = "255.255.255.255",
                    traffic = traffic(),
                    tariff = tariffs[2],
                    lastPaymentAt = DateTime.now().minusDays(2),
                    contractName = "Договор от 12.01.2021",
                    contractData = "",
                    owner = "Корниенко Олег",
                    loans = emptyList(),
                    contractNumber = 1339
                ),
                Client(
                    name = "Новичкова Анастасия",
                    balance = 201310,
                    enabled = true,
                    phone = "8197702021",
                    email = "anovichkova@gmail.com",
                    registeredAt = DateTime.now().minusDays(32),
                    mac = "AA:AA:AA:AA:AA:AA",
                    ip = "255.255.255.255",
                    traffic = traffic(),
                    tariff = tariffs[3],
                    lastPaymentAt = DateTime.now().minusDays(2),
                    contractName = "Договор от 01.01.2021",
                    contractData = "",
                    owner = "Новичкова Анастасия",
                    loans = emptyList(),
                    contractNumber = 1340
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