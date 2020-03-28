package Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Data
@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    private GuideService guideService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println(update.getMessage().toString());
            if(update.getMessage().getText().equals("/start")) {
                SendMessage message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Enter the name of the city");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                String str = update.getMessage().getText();
                if (guideService.isValidData(str)) {
                    SendMessage message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(guideService.getCityInfoByName(update.getMessage().getText()));
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();

                    }
                } else {
                    SendMessage message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText("There is no such command or city in the repository...");
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "guide_telegram_bot";
    }

    @Override
    public String getBotToken() {
        return "1120659823:AAHpkva9kNkVFH87NH3-IT4lXbOqMVQcIOc";
    }
}