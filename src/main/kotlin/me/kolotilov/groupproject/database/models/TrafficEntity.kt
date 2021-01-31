package me.kolotilov.groupproject.database.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "traffic")
data class TrafficEntity(
        @Column(name = "date")
        val date: Date,
        @Column(name = "amount")
        val amount: Int,
        @Id
        @Column(name = "id")
        @GeneratedValue
        val id: Int = 0
)
