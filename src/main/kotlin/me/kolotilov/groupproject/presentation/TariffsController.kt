package me.kolotilov.groupproject.presentation

import me.kolotilov.groupproject.domain.services.TariffService
import me.kolotilov.groupproject.presentation.input.EditTariffDto
import me.kolotilov.groupproject.presentation.input.apply
import me.kolotilov.groupproject.presentation.output.TariffDto
import me.kolotilov.groupproject.presentation.output.toTariffDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients/{clientId}")
class TariffsController {

    @Autowired
    private lateinit var tariffService: TariffService

    /**
     * Редактирование данных тарифа.
     */
    @PostMapping("/tariffs/{tariffId}")
    fun edit(
        @PathVariable("tariffId") tariffId: Int,
        @RequestBody newTariff: EditTariffDto
    ): TariffDto {
        val tariff = tariffService.get(tariffId)
        return tariffService.update(tariff.apply(newTariff)).toTariffDto()
    }
}