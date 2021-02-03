package me.kolotilov.groupproject.controllers

import me.kolotilov.groupproject.controllers.models.PaymentStatsDto
import me.kolotilov.groupproject.controllers.models.TrafficStatsDto
import me.kolotilov.groupproject.controllers.models.toPaymentStatsDto
import me.kolotilov.groupproject.controllers.models.toTrafficStatsDto
import me.kolotilov.groupproject.domain.services.ClientService
import me.kolotilov.groupproject.domain.services.StatsService
import me.kolotilov.groupproject.utils.replaceFirst
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/stats")
class StatsController {

    @Autowired
    private lateinit var statsService: StatsService

    @Autowired
    private lateinit var clientService: ClientService

    @GetMapping("/payment")
    fun paymentStats(): PaymentStatsDto {
        return statsService.getPaymentStats(clientService.getAll()).toPaymentStatsDto()
    }

    @GetMapping("/traffic")
    fun trafficStats(@RequestParam("clientId") clientId: Int): TrafficStatsDto {
        return statsService.getTrafficStats(clientService.get(clientId)).toTrafficStatsDto()
    }

    @GetMapping("/newClients")
    fun newClientsStats(): List<Pair<Date, Int>> {
        return statsService.getNewClientsStats(clientService.getAll()).map { it.replaceFirst { it.toDate() } }
    }
}