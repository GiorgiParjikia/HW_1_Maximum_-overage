package ru.netology

import org.junit.Assert.assertEquals
import org.junit.Test

class MainKtTest {

    @Test
    fun Mastercard_NoCommission_IfTransferBelow75000() {
        val result = calculateCommission("Mastercard", 5000, 70_000)
        assertEquals("Комиссия: 0 руб.", result)
    }

    @Test
    fun Mastercard_CommissionApplied_IfTransfersBelow300() {
        val result = calculateCommission("Mastercard", 250, 1000)
        assertEquals("Комиссия: 26 руб.", result)
    }

    @Test
    fun Mastercard_CommissionAfterExceeding75000() {
        val result = calculateCommission("Mastercard", 75000, 100000)
        assertEquals("Комиссия: 620 руб.", result)
    }

    @Test
    fun Mastercard_StandardCommission() {
        val result = calculateCommission("Mastercard", 80_000, 10_000)
        assertEquals("Комиссия: 80 руб.", result)
    }

    @Test
    fun Maestro_StandardCommission() {
        val result = calculateCommission("Maestro", 80_000, 10_000)
        assertEquals("Комиссия: 80 руб.", result)
    }

    @Test
    fun Maestro_NoCommission_IfTransferBelow75000() {
        val result = calculateCommission("Maestro", 5000, 70_000)
        assertEquals("Комиссия: 0 руб.", result)
    }

    @Test
    fun UnknownCardType_Error() {
        val result = calculateCommission("American Express", 50_000, 70_000)
        assertEquals("Ошибка: неизвестный тип карты.", result)
    }

    @Test
    fun Visa_MinimumCommission35Rub() {
        val result = calculateCommission("Visa", 0, 4000)
        assertEquals("Комиссия: 35 руб.", result)
    }

    @Test
    fun Visa_StandardCommission() {
        val result = calculateCommission("Visa", 1000, 5000)
        assertEquals("Комиссия: 37 руб.", result)
    }

    @Test
    fun Mir_MinimumCommission35Rub() {
        val result = calculateCommission("Мир", 0, 4000)
        assertEquals("Комиссия: 35 руб.", result)
    }

    @Test
    fun Mir_StandardCommission() {
        val result = calculateCommission("Мир", 1000, 5000)
        assertEquals("Комиссия: 36 руб.", result)
    }

    // превышение месячного лимита для всех карт
    @Test
    fun Mastercard_ExceedingMonthlyLimit_Error() {
        val result = calculateCommission("Mastercard", 600_000, 10_000)
        assertEquals("Операция отклонена: превышен лимит перевода.", result)
    }

    @Test
    fun Maestro_ExceedingMonthlyLimit_Error() {
        val result = calculateCommission("Maestro", 600_000, 10_000)
        assertEquals("Операция отклонена: превышен лимит перевода.", result)
    }

    @Test
    fun Visa_ExceedingMonthlyLimit_Error() {
        val result = calculateCommission("Visa", 600_000, 10_000)
        assertEquals("Операция отклонена: превышен лимит перевода.", result)
    }

    @Test
    fun Mir_ExceedingMonthlyLimit_Error() {
        val result = calculateCommission("Мир", 600_000, 10_000)
        assertEquals("Операция отклонена: превышен лимит перевода.", result)
    }

    @Test
    fun VKPay_NoCommission() {
        val result = calculateCommission("VK Pay", 10_000, 10_000)
        assertEquals("Комиссия: 0 руб.", result)
    }

    @Test
    fun VKPay_ExceedingMonthlyLimit_Error() {
        val result = calculateCommission("VK Pay", 40_000, 10_000)
        assertEquals("Операция отклонена: превышен лимит перевода VK Pay.", result)
    }

    // превышение дневного лимита для всех карт
    @Test
    fun Mastercard_ExceedingDaylyLimit_Error() {
        val result = calculateCommission("Mastercard", 10_000, 155_000)
        assertEquals("Операция отклонена: превышен лимит перевода.", result)
    }

    @Test
    fun Maestro_ExceedingDaylyLimit_Error() {
        val result = calculateCommission("Maestro", 10_000, 155_000)
        assertEquals("Операция отклонена: превышен лимит перевода.", result)
    }

    @Test
    fun Visa_ExceedingDaylyLimit_Error() {
        val result = calculateCommission("Visa", 10_000, 155_000)
        assertEquals("Операция отклонена: превышен лимит перевода.", result)
    }

    @Test
    fun Mir_ExceedingDaylyLimit_Error() {
        val result = calculateCommission("Мир", 10_000, 155_000)
        assertEquals("Операция отклонена: превышен лимит перевода.", result)
    }

    @Test
    fun VKPay_ExceedingDailyLimit_Error() {
        val result = calculateCommission("VK Pay", 100_000, 10_000)
        assertEquals("Операция отклонена: превышен лимит перевода VK Pay.", result)
    }
}
