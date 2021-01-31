package me.kolotilov.groupproject.domain.services

import me.kolotilov.groupproject.database.repositories.ClientRepository
import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.toClient
import me.kolotilov.groupproject.domain.models.toClientEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface ClientService {

    fun getAll(): List<Client>

    fun search(query: String): List<Client>

    fun get(id: Int): Client

    fun create(client: Client)

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

    override fun search(query: String): List<Client> {
        return clientRepository.findAllByNameContaining(query).map { it.toClient() }
    }

    override fun get(id: Int): Client {
        return clientRepository.findById(id).get().toClient()
    }

    override fun create(client: Client) {
        clientRepository.save(client.toClientEntity())
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