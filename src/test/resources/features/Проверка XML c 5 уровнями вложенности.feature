# language: ru
@getResult
Функция: Получение выходного json-файла
    Предыстория:
        Пусть на вход программы подан xml-файл с 5 уровнями вложенности depth_5_levels.xml
    Сценарий: Получение выходного json с 5 уровнями вложенности
        Тогда на выходе получаем json с 5 уровнями вложенности