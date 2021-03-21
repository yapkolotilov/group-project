package me.kolotilov.groupproject.presentation

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.kolotilov.groupproject.domain.models.Role
import me.kolotilov.groupproject.domain.services.ClientService
import me.kolotilov.groupproject.domain.services.TariffService
import me.kolotilov.groupproject.domain.services.UserService
import me.kolotilov.groupproject.presentation.input.*
import me.kolotilov.groupproject.presentation.output.ClientDetailsDto
import me.kolotilov.groupproject.presentation.output.ClientOverviewDto
import me.kolotilov.groupproject.presentation.output.toClientDetailsDto
import me.kolotilov.groupproject.presentation.output.toClientOverview
import me.kolotilov.groupproject.utils.restrictRole
import org.springframework.security.core.annotation.CurrentSecurityContext
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients")
private class ClientsController(
    private val clientService: ClientService,
    private val userService: UserService,
    private val tariffService: TariffService
) {

    @ApiOperation("Возвращает всех клиентов в системе.")
    @GetMapping
    fun getAll(): List<ClientOverviewDto> {
        return clientService.getAll().map { it.toClientOverview() }
    }

    @ApiOperation("Пагинирует клиентов.")
    @GetMapping("paged/{pageNumber},{count}")
    fun getPaged(
        @ApiParam("Номер страницы.")
        @PathVariable("pageNumber")
        pageNumber: Int,

        @ApiParam("Размер страницы.")
        @PathVariable("count")
        count: Int
    ): List<ClientOverviewDto> {
        return clientService.getPaged(pageNumber, count).map { it.toClientOverview() }
    }

    @ApiOperation("Возвращает клиента по id.")
    @GetMapping("/{id}")
    fun get(
        @ApiParam("ID клиента.")
        @PathVariable("id") id: Int
    ): ClientDetailsDto {
        return clientService.get(id).toClientDetailsDto()
    }

    @ApiOperation("Ищет клиента по запросу.")
    @GetMapping("/search")
    fun search(
        @ApiParam("Запрос.")
        @RequestParam("query") query: String
    ): List<ClientOverviewDto> {
        return clientService.search(query).map { it.toClientOverview() }
    }

    @ApiOperation("Создаёт нового клиента.")
    @PostMapping
    fun create(
        @ApiParam("Данные клиента.")
        @RequestBody client: CreateClientDto
    ): ClientDetailsDto {
        val tariff = tariffService.getByName(client.tariff)
        return clientService.create(client.toClient(tariff)).toClientDetailsDto()
    }

    @ApiOperation("Меняет данные клиента.")
    @PostMapping("/{id}")
    fun edit(
        @ApiParam("ID клиента.")
        @PathVariable("id") id: Int,

        @ApiParam("Новые данные клиента.")
        @RequestBody newClient: EditClientDto,
        @CurrentSecurityContext(expression = "authentication?.name") username: String
    ): ClientDetailsDto {
        val user = userService.getByUsername(username)!!
        restrictRole(user, newClient.balance, Role.ADMIN)
        val client = clientService.get(id)
        val tariff = newClient.tariff?.let { tariffService.getByName(newClient.tariff) }
        return clientService.update(client.apply(newClient, tariff)).toClientDetailsDto()
    }

    @ApiOperation("Даёт пользователю кредит.")
    @PostMapping("/{id}/giveCredit")
    fun giveCredit(
        @ApiParam("ID клиента.")
        @PathVariable("id") id: Int,

        @ApiParam("Данные кредита.")
        @RequestBody loan: GiveLoanDto
    ): ClientDetailsDto {
        val client = clientService.get(id)
        val newClient = client.copy(
            loans = client.loans + loan.toLoan()
        )
        return clientService.update(newClient).toClientDetailsDto()
    }

    @ApiOperation("Забирает кредит у пользователя.")
    @DeleteMapping("{id}/retrieveCredit/{loanId}")
    fun retrieveCredit(
        @ApiParam("ID пользователя.")
        @PathVariable("id") id: Int,

        @ApiParam("ID Кредита.")
        @PathVariable("loanId") loanId: Int
    ): ClientDetailsDto {
        val client = clientService.get(id)
        val newClient = client.copy(
            loans = client.loans.filter { it.id != loanId }
        )
        return clientService.update(newClient).toClientDetailsDto()
    }
}