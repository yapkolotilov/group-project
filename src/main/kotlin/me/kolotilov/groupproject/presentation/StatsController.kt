package me.kolotilov.groupproject.presentation

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.kolotilov.groupproject.domain.services.ClientService
import me.kolotilov.groupproject.domain.services.StatsService
import me.kolotilov.groupproject.presentation.output.PaymentStatsDto
import me.kolotilov.groupproject.presentation.output.TrafficStatsDto
import me.kolotilov.groupproject.presentation.output.toPaymentStatsDto
import me.kolotilov.groupproject.presentation.output.toTrafficStatsDto
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

    @ApiOperation("Возвращает статистику по оплате.")
    @GetMapping("/payment")
    fun paymentStats(): PaymentStatsDto {
        return statsService.getPaymentStats(clientService.getAll()).toPaymentStatsDto()
    }

    @ApiOperation("Возвращает статистику по траффику.")
    @GetMapping("/traffic")
    fun trafficStats(
        @ApiParam("ID клиента.")
        @RequestParam("clientId") clientId: Int
    ): TrafficStatsDto {
        return statsService.getTrafficStats(clientService.get(clientId)).toTrafficStatsDto()
    }

    @ApiOperation("Возвращает статистику по приросту клиентов.")
    @GetMapping("/newClients")
    fun newClientsStats(): List<Pair<Date, Int>> {
        return statsService.getNewClientsStats(clientService.getAll()).map { it.replaceFirst { it.toDate() } }
    }
}