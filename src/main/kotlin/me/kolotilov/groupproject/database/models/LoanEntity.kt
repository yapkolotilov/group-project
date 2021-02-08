package me.kolotilov.groupproject.database.models

import me.kolotilov.groupproject.domain.models.Loan
import me.kolotilov.groupproject.utils.toDate
import me.kolotilov.groupproject.utils.toDateTime
import me.kolotilov.groupproject.utils.toDuration
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "loan")
data class LoanEntity(
        @Column(name = "amount")
        val amount: Int,
        @Column(name = "startDate")
        val startDate: Date,
        @Column(name = "duration")
        val duration: Date,
        @Id
        @Column(name = "id")
        val id: Int
)

fun LoanEntity.toLoan() = Loan(
        amount, startDate.toDateTime(), duration.toDuration(), id
)

fun Loan.toLoanEntity() = LoanEntity(
        amount, startDate.toDate(), duration.toDate(), id
)