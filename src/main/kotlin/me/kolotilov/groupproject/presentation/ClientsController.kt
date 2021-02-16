package me.kolotilov.groupproject.presentation

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

    /**
     * Возвращает всех клиентов в системе.
     */
    @GetMapping
    fun getAll(): List<ClientOverviewDto> {
        return clientService.getAll().map { it.toClientOverview() }
    }

    /**
     * Пагинирует клиентов.
     *
     * @param pageNumber Номер страницы.
     * @param count Размер страницы.
     */
    @GetMapping("paged/{pageNumber},{count}")
    fun getPaged(pageNumber: Int, count: Int): List<ClientOverviewDto> {
        return clientService.getPaged(pageNumber, count).map { it.toClientOverview() }
    }

    /**
     * Возвращает клиента по id.
     *
     * @param id ID клиента.
     */
    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): ClientDetailsDto {
        return clientService.get(id).toClientDetailsDto()
    }

    /**
     * Ищет клиента по запросу.
     *
     * @param query Запрос.
     */
    @GetMapping("/search")
    fun search(@RequestParam("query") query: String): List<ClientOverviewDto> {
        return clientService.search(query).map { it.toClientOverview() }
    }

    /**
     * Меняет данные клиента.
     */
    @PostMapping("/{id}")
    fun edit(
        @PathVariable("id") id: Int,
        @RequestBody newClient: EditClientDto,
        @CurrentSecurityContext(expression = "authentication?.name") username: String
    ): Client {
        val user = userService.getByUsername(username)!!
        restrictRole(user, newClient.balance, Role.ADMIN)
        val client = clientService.get(id)
        return clientService.update(client.apply(newClient))
    }

    /**
     * Даёт пользователю кредит.
     *
     * @param id ID клиента.
     * @param loan Данные кредита.
     */
    @PostMapping("/{id}/giveCredit")
    fun giveCredit(@PathVariable("id") id: Int, @RequestBody loan: GiveLoanDto): Client {
        val client = clientService.get(id)
        val newClient = client.copy(
            loans = client.loans + loan.toLoan()
        )
        return clientService.update(newClient)
    }

    /**
     * Забирает кредит у пользователя.
     *
     * @param id ID пользователя.
     * @param loanId Id пользователя.
     */
    @PostMapping("{id}/retrieveCredit/{loanId}")
    fun retrieveCredit(@PathVariable("id") id: Int, @PathVariable("loanId") loanId: Int): Client {
        val client = clientService.get(id)
        val newClient = client.copy(
            loans = client.loans.filter { it.id != loanId }
        )
        return clientService.update(newClient)
    }
}