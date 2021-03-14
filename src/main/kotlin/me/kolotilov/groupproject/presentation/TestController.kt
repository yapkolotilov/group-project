package me.kolotilov.groupproject.presentation

import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/test")
class TestController {

    @ApiOperation("Проверка работоспособности сервиса.")
    @GetMapping("/time")
    fun date(): Date {
        return Date()
    }
}