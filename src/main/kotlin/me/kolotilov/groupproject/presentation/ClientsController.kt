package me.kolotilov.groupproject.presentation

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.Role
import me.kolotilov.groupproject.domain.services.ClientService
import me.kolotilov.groupproject.domain.services.UserService
import me.kolotilov.groupproject.presentation.input.EditClientDto
import me.kolotilov.groupproject.presentation.input.GiveLoanDto
import me.kolotilov.groupproject.presentation.input.apply
import me.kolotilov.groupproject.presentation.input.toLoan
import me.kolotilov.groupproject.presentation.output.ClientDetailsDto
import me.kolotilov.groupproject.presentation.output.ClientOverviewDto
import me.kolotilov.groupproject.presentation.output.toClientDetailsDto
import me.kolotilov.groupproject.presentation.output.toClientOverview
import me.kolotilov.groupproject.utils.restrictRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.CurrentSecurityContext
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients")
private class ClientsController {

    @Autowired
    private lateinit var clientService: ClientService
    @Autowired
    private lateinit var userService: UserService


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

    @ApiOperation("Меняет данные клиента.")
    @PostMapping("/{id}")
    fun edit(
        @ApiParam("ID клиента.")
        @PathVariable("id") id: Int,

        @ApiParam("Новые данные клиента.")
        @RequestBody newClient: EditClientDto,
        @CurrentSecurityContext(expression = "authentication?.name") username: String
    ): Client {
        val user = userService.getByUsername(username)!!
        restrictRole(user, newClient.balance, Role.ADMIN)
        val client = clientService.get(id)
        return clientService.update(client.apply(newClient))
    }

    @ApiOperation("Даёт пользователю кредит.")
    @PostMapping("/{id}/giveCredit")
    fun giveCredit(
        @ApiParam("ID клиента.")
        @PathVariable("id") id: Int,

        @ApiParam("Данные кредита.")
        @RequestBody loan: GiveLoanDto
    ): Client {
        val client = clientService.get(id)
        val newClient = client.copy(
            loans = client.loans + loan.toLoan()
        )
        return clientService.update(newClient)
    }

    @ApiOperation("Забирает кредит у пользователя.")
    @PostMapping("{id}/retrieveCredit/{loanId}")
    fun retrieveCredit(
        @ApiParam("ID пользователя.")
        @PathVariable("id") id: Int,

        @ApiParam("ID Кредита.")
        @PathVariable("loanId") loanId: Int
    ): Client {
        val client = clientService.get(id)
        val newClient = client.copy(
            loans = client.loans.filter { it.id != loanId }
        )
        return clientService.update(newClient)
    }
}