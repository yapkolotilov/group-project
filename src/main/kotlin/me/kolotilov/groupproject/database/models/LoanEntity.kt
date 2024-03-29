package me.kolotilov.groupproject.database.models

import me.kolotilov.groupproject.domain.models.Loan
import me.kolotilov.groupproject.utils.toDate
import me.kolotilov.groupproject.utils.toDateTime
import me.kolotilov.groupproject.utils.toDuration
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "loan")
data class LoanEntity(
        @Column(name = "amount")
        val amount: Int,
        @Column(name = "start_date")
        val startDate: Date,
        @Column(name = "duration")
        val duration: Date,
        @Id
        @GeneratedValue
        @Column(name = "id")
        val id: Int
)

fun LoanEntity.toLoan() = Loan(
        amount = amount,
        startDate = startDate.toDateTime(),
        duration = duration.toDuration(),
        id = id
)

fun Loan.toLoanEntity() = LoanEntity(
        amount = amount,
        startDate = startDate.toDate(),
        duration = duration.toDate(),
        id = id
)