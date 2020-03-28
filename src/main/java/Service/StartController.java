package Service;

import DAO.DAOCity;
import lombok.RequiredArgsConstructor;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class StartController {
    static {
        ApiContextInitializer.init();
    }
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private DAOCity daoCity;

    @GetMapping("/start")
    public String start(){
        return "start";
    }

    @PostMapping("/start")
    public String start(HttpServletRequest request) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
