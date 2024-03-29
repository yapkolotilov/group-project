package me.kolotilov.groupproject.domain.models

/**
 * Тариф.
 *
 * @param name Название.
 * @param speed Скорость (мбит/сек).
 * @param price Стоимость (руб/мес).
 */
data class Tariff(
    val name: String,
    val speed: Int,
    val price: Int
)