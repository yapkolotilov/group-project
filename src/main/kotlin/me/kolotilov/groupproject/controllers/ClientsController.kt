package me.kolotilov.groupproject.controllers

import me.kolotilov.groupproject.controllers.models.*
import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients")
private class ClientsController {

    @Autowired
    private lateinit var clientService: ClientService

    @GetMapping
    @Secured
    fun getAll(): List<ClientOverviewDto> {
        return clientService.getAll().map { it.toClientOverview() }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): ClientDetailsDto {
        return clientService.get(id).toClientDetails()
    }

    @GetMapping("/search")
    fun search(@RequestParam("query") query: String): List<ClientOverviewDto> {
        return clientService.search(query).map { it.toClientOverview() }
    }

    @PutMapping("/{id}")
    fun edit(@PathVariable("id") id: Int, @RequestBody newClient: ClientEditDto): Client {
        val client = clientService.get(id)
        return clientService.update(newClient.toClient(client))
    }
}