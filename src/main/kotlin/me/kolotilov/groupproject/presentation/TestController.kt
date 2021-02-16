package me.kolotilov.groupproject.presentation

import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @ApiOperation("Проверка работоспособности сервиса.")
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }
}