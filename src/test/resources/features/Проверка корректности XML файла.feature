# language: ru
@getDocument
Функция: Парсинг XML-документа
    Предыстория:
        Пусть на вход программы подан некорректный invalid_file.xml
    Сценарий: Парсинг некорректного XML-документа
        Тогда должна возникнуть ошибка парсинга XML