package me.kolotilov.groupproject.domain.services

import me.kolotilov.groupproject.database.models.toClient
import me.kolotilov.groupproject.database.models.toClientEntity
import me.kolotilov.groupproject.database.repositories.ClientRepository
import me.kolotilov.groupproject.domain.models.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

interface ClientService {

    fun getAll(): List<Client>

    fun getPaged(startIndex: Int, count: Int): List<Client>

    fun search(query: String): List<Client>

    fun get(id: Int): Client

    fun create(client: Client): Client

    fun createAll(vararg clients: Client)

    fun clear()

    fun update(client: Client): Client
}

@Service
private class ClientServiceImpl : ClientService {

    @Autowired
    private lateinit var clientRepository: ClientRepository

    override fun getAll(): List<Client> {
        return clientRepository.findAll().map { it.toClient() }
    }

    override fun getPaged(startIndex: Int, count: Int): List<Client> {
        return clientRepository.findAll(PageRequest.of(startIndex, count)).toList().map { it.toClient() }
    }

    override fun search(query: String): List<Client> {
        return clientRepository.findAllByNameContaining(query).map { it.toClient() }
    }

    override fun get(id: Int): Client {
        return clientRepository.findById(id).get().toClient()
    }

    override fun create(client: Client): Client {
        return clientRepository.save(client.toClientEntity()).toClient()
    }

    override fun createAll(vararg clients: Client) {
        clientRepository.saveAll(clients.map { it.toClientEntity() })
    }

    override fun clear() {
        clientRepository.deleteAll()
    }

    override fun update(client: Client): Client {
        return clientRepository.save(client.toClientEntity()).toClient()
    }
}