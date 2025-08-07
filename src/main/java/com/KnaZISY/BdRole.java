package com.KnaZISY;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BdRole extends JavaPlugin {
    
    private final Map<UUID, RoleMenu> playerMenus = new HashMap<>();
    private final Map<UUID, Integer> playerLastMessageId = new HashMap<>();
    
    @Override
    public void onEnable() {
        getCommand("role").setExecutor(this);
    }
    
    @Override
    public void onDisable() {
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Эта команда только для игроков!", NamedTextColor.RED));
            return true;
        }
        
        if (command.getName().equalsIgnoreCase("role")) {
            if (args.length == 0) {
                showMainMenu(player);
            } else {
                showRoleDescription(player, args[0]);
            }
            return true;
        }
        
        return false;
    }
    
    private void showMainMenu(Player player) {
        Component title = Component.text("Нажмите чтобы узнать что означает каждая ●", NamedTextColor.GOLD, TextDecoration.BOLD);
        
        Component message = Component.empty()
                .append(title)
                .append(Component.newline())
                .append(Component.newline())
                .append(Component.text("● ", NamedTextColor.RED).hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW))).clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role admin")))
                .append(Component.text("● ", NamedTextColor.GOLD).hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW))).clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role vip")))
                .append(Component.text("● ", NamedTextColor.BLUE).hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW))).clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role moderator")))
                .append(Component.text("● ", NamedTextColor.DARK_BLUE).hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW))).clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role headmod")))
                .append(Component.text("● ", NamedTextColor.GREEN).hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW))).clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role helper")))
                .append(Component.text("● ", NamedTextColor.DARK_PURPLE).hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW))).clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role eventmaster")))
                .append(Component.text("● ", NamedTextColor.LIGHT_PURPLE).hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW))).clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role media")))
                .append(Component.text("● ", NamedTextColor.GRAY).hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW))).clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role developer")));
        
        player.sendMessage(Component.text(""));
        player.sendMessage(Component.text(""));
        player.sendMessage(message);
        playerMenus.put(player.getUniqueId(), RoleMenu.MAIN);
    }
    
    private void showRoleDescription(Player player, String role) {
        Component description = switch (role.toLowerCase()) {
            case "admin" -> Component.text("Администрация имеет красный кружок в игре.\n", NamedTextColor.RED)
                    .append(Component.text("Обладают всеми правами как на ДС сервере, так и на Майнкрафт сервере.\n", NamedTextColor.WHITE))
                    .append(Component.text("Администраторы руководят всем сервером, набирают стафф и рассматривают жалобы на него, являются высшим органом сервера.", NamedTextColor.WHITE));
            
            case "headmod" -> Component.text("Главный Модератор имеет тёмно-синий кружок около ника в майнкрафте.\n", NamedTextColor.DARK_BLUE)
                    .append(Component.text("Он обладает еще более продвинутыми правами, чем Модератор контролирует модераторов, следит за хелперами.", NamedTextColor.WHITE));
            
            case "moderator" -> Component.text("Модераторы имеют синий кружок около ника в майкрафте.\n", NamedTextColor.BLUE)
                    .append(Component.text("Они, как и Хелперы, разрешают конфликты игроков (репорты), следят за дискорд сервером, следят за читерами, рассматривают заявки на ивенты и СМП.\n", NamedTextColor.WHITE))
                    .append(Component.text("Модераторы обладают более продвинутыми возможностями, чем Хелперы на сервере. Они обладают возможностью отката ресурсов, территории в майнкрафте, креатив на Быстрой Королевской Битве.\n", NamedTextColor.WHITE))
                    .append(Component.text("В их обязанности входит надзор за хелперами. Модератор может взять под свой присмотр хелпера на испытательный срок.", NamedTextColor.WHITE));
            
            case "helper" -> Component.text("Хелперы имеют зелёный кружок около ника в майкрафте.\n", NamedTextColor.GREEN)
                    .append(Component.text("Они имеют базовые административные права в игре, разрешают конфликты игроков (репорты), следят за дискорд сервером, следят за читерами, рассматривают заявки на ивенты и СМП.\n", NamedTextColor.WHITE))
                    .append(Component.text("Вы можете подать заявку на Хелпера в дискорде", NamedTextColor.YELLOW));
            
            case "eventmaster" -> Component.text("Ивент Мастеры имеют тёмно-фиолетовый кружок в игре.\n", NamedTextColor.DARK_PURPLE)
                    .append(Component.text("Они создают и проводят уникальные ивенты от лица администрации.\n", NamedTextColor.WHITE))
                    .append(Component.text("Ивент Мастеры имеют все необходимые права для создания ивентов на сервере.", NamedTextColor.WHITE));
            
            case "media" -> Component.text("Медиа-партнеры имеют розовый кружок около ника.\n", NamedTextColor.LIGHT_PURPLE)
                    .append(Component.text("Обладает возможностями VIP\n", NamedTextColor.WHITE))
                    .append(Component.text("Выдается тем, кто снял видео по серверу либо более чем на 10 тысяч просмотров на ютубе в полном формате, либо более чем на 200 тысяч просмотров в шортс/тикток; и обязательно оставил ссылку на дискорд сервер в описании. Либо ютуберам чей канал с преимущественно видео в полном формате насчитывает более 15 тысяч подписчиков.", NamedTextColor.WHITE));
            
            case "developer" -> Component.text("Разработчики имеют серый кружок около ника в майнкрафте\n", NamedTextColor.GRAY)
                    .append(Component.text("Они разрабатывают плагины, модельки или другое для нашего сервера", NamedTextColor.WHITE));
            
            case "vip" -> Component.text("Випы имеют золотой кружок около ника.\n", NamedTextColor.GOLD)
                    .append(Component.text("При покупке они получают:\n", NamedTextColor.WHITE))
                    .append(Component.text("• Гарантированный проход на крупные ивенты\n", NamedTextColor.YELLOW))
                    .append(Component.text("• Флай в хабе\n", NamedTextColor.YELLOW))
                    .append(Component.text("• Кейсы с уникальной косметикой каждый месяц\n", NamedTextColor.YELLOW))
                    .append(Component.text("• Золотой кружок около ника\n", NamedTextColor.YELLOW))
                    .append(Component.text("Купить: (ссылка)\n", NamedTextColor.GREEN))
                    .append(Component.text("Мы благодарны всем VIP за поддержку сервера.", NamedTextColor.WHITE));
            
            default -> Component.text("Роль не найдена", NamedTextColor.RED);
        };
        
        Component backButton = Component.text("\n◁ НАЗАД", NamedTextColor.GOLD, TextDecoration.BOLD)
                .hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(Component.text("Нажмите", NamedTextColor.YELLOW)))
                .clickEvent(net.kyori.adventure.text.event.ClickEvent.runCommand("/role"));
        
        player.sendMessage(Component.text(""));
        player.sendMessage(Component.text(""));
        player.sendMessage(description.append(backButton));
        playerMenus.put(player.getUniqueId(), RoleMenu.DESCRIPTION);
    }
    

    
    private enum RoleMenu {
        MAIN, DESCRIPTION
    }
} 