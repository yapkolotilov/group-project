package me.kolotilov.groupproject.database.models

import me.kolotilov.groupproject.domain.models.Traffic
import org.joda.time.DateTime
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

fun TrafficEntity.toTraffic() = Traffic(
        date = DateTime(date),
        amount = amount,
        id = id
)

fun Traffic.toTrafficEntity() = TrafficEntity(
        date = date.toDate(),
        amount = amount,
        id = id
)