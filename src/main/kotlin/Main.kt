package ru.netology

fun main() {
    println(calculateCommission("Mastercard", 5000, 70000))  // Без комиссии
    println(calculateCommission("Mastercard", 80000, 10000)) // С комиссией
    println(calculateCommission("Visa", 1000, 5000))         // Минимальная комиссия (35 руб.)
    println(calculateCommission("Visa", 10000, 20000))       // Стандартная комиссия 0.75%
    println(calculateCommission("VK Pay", 15000, 30000))     // Без комиссии
    println(calculateCommission("Мир", 5000, 10000))         // Комиссия 0.75%
    println(calculateCommission("Mastercard", 500000, 200000)) // Превышение лимита
}

fun calculateCommission(cardType: String, previousTransfers: Int, currentTransfer: Int): String {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000
    val vkPaySingleLimit = 15_000
    val vkPayMonthlyLimit = 40_000

    // Проверка лимитов VK Pay
    if (cardType == "VK Pay") {
        if (currentTransfer > vkPaySingleLimit || (previousTransfers + currentTransfer) > vkPayMonthlyLimit) {
            return "Операция отклонена: превышен лимит перевода VK Pay."
        }
        return "Комиссия: 0 руб."
    }

    // Проверка общих лимитов
    if (currentTransfer > dailyLimit || (previousTransfers + currentTransfer) > monthlyLimit) {
        return "Операция отклонена: превышен лимит перевода."
    }

    // Рассчет комиссии
    return when (cardType) {
        "Mastercard", "Maestro" -> {
            val freeLimit = 75_000
            val commissionRate = 0.006
            val fixedFee = 20

            when {
                previousTransfers >= 300 && previousTransfers + currentTransfer <= freeLimit -> "Комиссия: 0 руб."
                else -> "Комиссия: ${(currentTransfer * commissionRate + fixedFee).toInt()} руб."
            }
        }

        "Visa", "Мир" -> {
            val commissionRate = 0.0075
            val minCommission = 35
            val commission = (currentTransfer * commissionRate).toInt()
            "Комиссия: ${if (commission < minCommission) minCommission else commission} руб."
        }

        else -> "Ошибка: неизвестный тип карты."
    }




}