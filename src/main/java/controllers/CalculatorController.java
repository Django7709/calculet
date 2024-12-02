package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @GetMapping
    public String showGreetings() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/plus")
    public String sumNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
        Integer result = calculatorService.sum(num1, num2);
        return generateMessage(num1, num2, '+', result);
    }

    @GetMapping("/minus")
    public String subtractNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
        Integer result = calculatorService.subtract(num1, num2);
        return generateMessage(num1, num2, '-', result);
    }

    @GetMapping("/multiply")
    public String multiplyNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
        Integer result = calculatorService.multiply(num1, num2);
        return generateMessage(num1, num2, '*', result);
    }

    @GetMapping("/divide")
    public String divideNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("деление на 0 невозможно");
        }
        Integer result = calculatorService.divide(num1, num2);
        return generateMessage(num1, num2, '/', result);

    }

    private String generateMessage(Integer num1, Integer num2, char action, int result) {
        return String.format("<h1>%d %c %d = %d</h1>", num1, action, num2, result);
    }
}
