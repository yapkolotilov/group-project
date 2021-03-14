package me.kolotilov.groupproject.presentation

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.kolotilov.groupproject.domain.services.ClientService
import me.kolotilov.groupproject.domain.services.StatsService
import me.kolotilov.groupproject.domain.services.TariffService
import me.kolotilov.groupproject.presentation.output.*
import me.kolotilov.groupproject.utils.replaceFirst
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/stats")
class StatsController(
    private val statsService: StatsService,
    private val clientService: ClientService,
    private val tariffService: TariffService
) {

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

    @ApiOperation("Возвращает все доступные тарифы.")
    @GetMapping("/tariffs")
    fun getTariffs(): List<TariffDto> {
        return tariffService.getAll().map { it.toTariffDto() }
    }
}